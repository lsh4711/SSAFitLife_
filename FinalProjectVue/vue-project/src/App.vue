<template>
  <div>
    <RouterView/>
  </div>
  <div id="app">
      <button v-if="!isLoggedIn" @click="loginSidePanel" class="login"><a class="login-content">로그인</a><a class="plus">+</a></button>
      <button v-if="!isLoggedIn" @click="signupSidePanel" class="signup"><a class="signup-content">회원가입</a><a class="plus">+</a></button> -->
      <button v-if="isLoggedIn" @click="mypageSidePanel" class="mypage"><a class="mypage-content">마이페이지</a><a class="plus">+</a></button>
      
      <transition name="slide">
        <!-- 로그인 패널 -->
        <div v-if="isLoginVisible" class="login-side-panel">
          <button class="login-close-button" @click="loginSidePanel">닫기</button>
          <h2>로그인 화면</h2>
          <form @submit.prevent="login">
            <div class="input-group">
              <input v-model="username" type="text" placeholder="이메일" required><br>
              <span v-if="username && !username.includes('@')" class="error-message">올바른 이메일을 입력하세요.</span>
            </div>
            <div class="input-group">
              <input v-model="password" type="password" placeholder="비밀번호" required><br>
            </div>
            <div>
              <span v-if="checkLoginFlag" class="error-message">아이디 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요.</span>
            </div>
            <br>
            <button type="submit">로그인</button>
            <span class="forgot-password"><a href="#">비밀번호를 잊으셨나요?</a></span>
          </form>
        </div>
      </transition>
      <transition name="slide">
        <!-- 회원가입 패널 -->
        <div v-if="isSignupVisible" class="signup-side-panel">
          <button class="signup-close-button" @click="signupSidePanel">닫기</button>
          <h2>회원가입 화면</h2>
          <form @submit.prevent="handleSignup">
            <div class="input-group">
              <h3 class="join_title">이메일</h3>
              <input v-model="user.email" type="email" placeholder="이메일" @blur="checkEmail" required><br>
              <span v-if="emailError" class="error-message">{{ emailError }}</span>
            </div>
            <div class="input-group">
              <h3 class="join_title">비밀번호</h3>
              <input v-model="user.password" type="password" placeholder="비밀번호" required @input="checkPasswordMatch"><br>
              <input v-model="confirmPassword" type="password" placeholder="비밀번호 확인" required @input="checkPasswordMatch"><br>
              <span v-if="passwordError" class="error-message">{{ passwordError }}</span>
            </div>
            <div class="input-group">
              <h3 class="join_title">이름</h3>
              <input v-model="user.name" type="text" placeholder="이름" required><br>
            </div>
            <div class="input-group">
              <h3 class="join_title">닉네임</h3>
              <input v-model="user.nickname" type="text" placeholder="닉네임" @blur="checkNickname" required><br>
              <span v-if="nicknameError" class="error-message">{{ nicknameError }}</span>
            </div>
            <div class="input-group">
              <h3 class="join_title">키(cm)</h3>
              <input v-model="user.height" type="number" step="0.1" placeholder="키(cm)" required><br>
            </div>
            <div class="input-group">
              <h3 class="join_title">성별</h3>
              <select v-model="user.gender" required>
                <option value="" selected disabled>성별</option>
                <option value="0">남성</option>
                <option value="1">여성</option>
              </select><br>
            </div>
            <div class="input-group">
              <h3 class="join_title">생년월일</h3>
              <div class="bir_wrap">
                <div class="bir_yy">
                  <span class="ps_box">
                    <select id="yyyy" class="sel" v-model="signup.yyyy">
                      <option value="" disabled>년</option>
                      <option v-for="(item, index) in yyyyList" :key="index" :value="item.value">
                        {{ item.text }}
                      </option>
                    </select>
                  </span>
                </div>
                <div class="bir_mm">
                  <span class="ps_box">
                    <select id="mm" class="sel" v-model="signup.mm">
                      <option value="" disabled>월</option>
                      <option v-for="(item, index) in mmlist" :key="index" :value="item.value">
                        {{ item.text }}
                      </option>
                    </select>
                  </span>
                </div>
                <div class="bir_dd">
                  <span class="ps_box">
                    <input v-model="signup.dd"
                           @input="validateDay"
                           placeholder="일"
                           type="text"
                           class="int"
                           maxlength="2"
                           oninput="javascript: this.value = this.value.replace(/[^0-9]/g, '');"/>
                  </span>
                </div>
              </div>
              <span class="error_next_box" v-if="checkFlag && (!signup.yyyy || !signup.mm || !signup.dd)" >생년월일을 입력하세요</span>
            </div>
            <div class="input-group">
              <h3 class="join_title">전화번호</h3>
              <input v-model="user.phoneNumber" type="text" placeholder="전화번호" @input="formatPhoneNumber" required><br>
            </div>
            <br>
            <button type="submit" :disabled="isEmailExists || isNicknameExists || isPasswordDiff">회원가입</button>
          </form>
        </div>
      </transition>
      <transition name="slide">
        <!-- 마이페이지 패널 -->
        <div v-if="isMypageVisible" class="mypage-side-panel">
          <button class="mypage-close-button" @click="mypageSidePanel">닫기</button>
          <h2>마이페이지 화면</h2>
          <button @click="logout">로그아웃</button>
          <p>여기에 원하는 내용을 추가하세요.</p>
        </div>
      </transition>
    </div>
</template>

<script setup>
  // Vue Composition API 사용
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { useAuth } from '@/composables/useAuth';
  import axios from "axios";

const { isLoggedIn, handleLogin, handleLogout, checkToken } = useAuth();

const instance = axios.create({
  baseURL: 'http://localhost:8080', // Spring API 기본 URL
  withCredentials: true, // 쿠키 전송 허용
});

// 토큰 만료 여부와 로그인 상태 확인
onMounted(() => {
  checkToken();
});

// 로그인, 회원가입, 마이페이지 상태 변수
const isLoginVisible = ref(false);
const isSignupVisible = ref(false);
const isMypageVisible = ref(false);

const router = useRouter();
// 패널 토글 함수
const loginSidePanel = () => {
  isLoginVisible.value = !isLoginVisible.value;
  username.value = '';
  password.value = '';
};

const signupSidePanel = () => {
  isSignupVisible.value = !isSignupVisible.value;
  user.value = {
    email: '',
    password: '',
    name: '',
    height: '',
    gender: '',
    birthday: '',
    phoneNumber: '',
    nickname: '',
  };
  emailError.value = '';
  nicknameError.value = '';
  confirmPassword.value ='';
  isEmailExists.value = false;
  isNicknameExists.value = false;
  isPasswordDiff.value = false;
};

const mypageSidePanel = () => {
  isMypageVisible.value = !isMypageVisible.value;
};

//로그인 처리
const username = ref('');
const password = ref('');
const checkLoginFlag = ref(false);

const login = async () => {
  const loginFail = await handleLogin(username.value, password.value);
  isLoginVisible.value = loginFail;
  checkLoginFlag.value = loginFail;
};

// 로그아웃 처리
const logout = () => {
  handleLogout();
  isMypageVisible.value = false;
};

//회원가입 처리
const user = ref({
  email: '',
  password: '',
  name: '',
  height: '',
  gender: '',
  birthday: '',
  phoneNumber: '',
  nickname: '',
});

const confirmPassword = ref(''); // 비밀번호 확인 입력 필드 추가
const emailError = ref('');
const nicknameError = ref('');
const passwordError = ref('');
const isEmailExists = ref(false);
const isNicknameExists = ref(false);
const isPasswordDiff = ref(false);

// 이메일 중복 체크
const checkEmail = async () => {
  try {
    const response = await instance.get(`/user/check-email`, {
      params: { email: user.value.email },
    });

    if (response.data) {
      emailError.value = '이미 존재하는 이메일입니다.';
      isEmailExists.value = true;
    } else {
      emailError.value = '';
      isEmailExists.value = false;
    }
  } catch (err) {
    console.error(err);
    emailError.value = '이메일 확인에 실패했습니다.';
  }
};

// 닉네임 중복 체크
const checkNickname = async () => {
  try {
    const response = await instance.get(`/user/check-nickname`, {
      params: { nickname: user.value.nickname },
    });

    if (response.data) {
      nicknameError.value = '이미 존재하는 닉네임입니다.';
      isNicknameExists.value = true;
    } else {
      nicknameError.value = '';
      isNicknameExists.value = false;
    }
  } catch (err) {
    console.error(err);
    nicknameError.value = '닉네임 확인에 실패했습니다.';
  }
};

// 비밀번호 확인 검사
const checkPasswordMatch = () => {
  if (user.value.password !== confirmPassword.value) {
    passwordError.value = '비밀번호가 일치하지 않습니다.';
    isPasswordDiff.value = true;
  } else {
    passwordError.value = '';
    isPasswordDiff.value = false;
  }
};

// 전화번호 포맷팅 함수
const formatPhoneNumber = (event) => {
  // 입력값 가져오기
  let value = event.target.value;

  // 숫자만 남기기
  value = value.replace(/[^0-9]/g, '');

  // 000-0000-0000 형식으로 변환
  if (value.length <= 3) {
    event.target.value = value; // 000
  } else if (value.length <= 7) {
    event.target.value = `${value.slice(0, 3)}-${value.slice(3)}`; // 000-0000
  } else {
    event.target.value = `${value.slice(0, 3)}-${value.slice(3, 7)}-${value.slice(7, 11)}`; // 000-0000-0000
  }

  // 사용자 입력 데이터 반영
  user.value.phoneNumber = event.target.value;
  };

//생일 처리
  const signup = ref({
    yyyy: '',
    mm: '',
    dd: '',
  });

  const yyyyList = ref([]);
  const mmlist = ref([]);
  const checkFlag = ref(false); // 생년월일 체크 플래그

  // 생년월일 관련 데이터 초기화
  onMounted(() => {
    const nowYear = new Date().getFullYear();

    for (let i = 0; i < 100; i++) {
      let date = nowYear - i;
      yyyyList.value.push({ value: date, text: date });
    }

    for (let i = 1; i <= 12; i++) {
      mmlist.value.push({
        value: i,
        text: i,
      });
    }
  });

  const validateDay = () => {
    // signup.dd가 undefined일 경우 빈 문자열로 설정
    let day = signup.value.dd || '';
    // 숫자만 남기기
    day = day.replace(/[^0-9]/g, '');
    // 숫자 값이 31을 초과하지 않도록 처리
    if (parseInt(day) > 31) {
      signup.value.dd = '31'; // 최대값을 31로 제한
    }
  };

const handleSignup = async () => {
  try {
    if (!signup.yyyy || !signup.mm || !signup.dd) {
      checkFlag.value = true; // 생년월일이 제대로 입력되지 않으면 오류 표시
      return;
    }

    const response = await instance.post('/user/join', user.value);

    if (response.status === 200) {
      alert('회원가입 성공');
      // 회원가입 후 로그인 화면으로 전환
      isSignupVisible.value = false; // 회원가입 패널 숨기기
      isLoginVisible.value = true; // 로그인 패널 보이기
    }
  } catch (err) {
    console.error(err);
    alert('회원가입 실패');
  }
};
</script>

<style scoped>
/* 오른쪽 패널 스타일 */
.login-side-panel {
  position: fixed;
  top: 0;
  right: 0;
  width: 400px;
  height: 100%;
  background-color: white;
  box-shadow: -2px 0 5px rgba(0, 0, 0, 0.5);
  padding: 20px;
  transition: transform 0.3s ease;
  z-index: 10;
}
.signup-side-panel {
  position: fixed;
  top: 0;
  right: 0;
  width: 400px;
  height: 100%;
  background-color: white;
  box-shadow: -2px 0 5px rgba(0, 0, 0, 0.5);
  padding: 20px;
  transition: transform 0.3s ease;
  z-index: 10;
}
.mypage-side-panel {
  position: fixed;
  top: 0;
  right: 0;
  width: 600px;
  height: 100%;
  background-color: white;
  box-shadow: -2px 0 5px rgba(0, 0, 0, 0.5);
  padding: 20px;
  transition: transform 0.3s ease;
}

/* 닫기 버튼 스타일 */

/* Transition 스타일 */
.slide-enter-active,
.slide-leave-active {
  transition: transform 0.3s ease; /* 진입 및 퇴장 모두 애니메이션 적용 */
}

.slide-enter {
  transform: translateX(100%); /* 오른쪽 화면 밖에서 시작 */
}

.slide-enter-to {
  transform: translateX(0); /* 화면 안으로 부드럽게 이동 */
}

.slide-leave {
  transform: translateX(0); /* 화면 안에서 시작 */
}

.slide-leave-to {
  transform: translateX(100%); /* 오른쪽 화면 밖으로 나감 */
}

.login,.signup,.mypage{
    font-size: 17px;
    font-weight: bold;
    color: white;
    background-color: #97d4e9;
    position: absolute;
    top:45px;
    height: 55px;
    width: 150px;
    border-radius: 35px;
    display: flex; /* 내용 정렬을 위해 Flexbox 사용 */
    justify-content: center; /* 가로 가운데 정렬 */
    align-items: center; /* 세로 가운데 정렬 */
    box-shadow: 1px 1px 5px rgba(0, 0, 0, 0.3);
    cursor: pointer;
    border: none;
  }
  .login{
    right: 240px;
  }
  .signup{
    right: 70px;
  }
  .mypage{
    right: 70px;
  }
  .login-content{
    position: absolute;
    left:35px
  }
  .signup-content{
    position: absolute;
    left:30px
  }
  .mypage-content{
    position: absolute;
    left:30px
  }
  .plus{
    position: absolute;
    right:15px
  }
  input[type="text"],
  input[type="email"],
  input[type="password"],
  input[type="number"],
  input[type="date"]{
    width: 95%;
    padding: 10px;
    font-size: 16px;
    margin: 5px 0;
    border: 1px solid #ddd;
    border-radius: 5px;
  }

  select{
    width: 100%;
    padding: 10px;
    font-size: 16px;
    margin: 5px 0;
    border: 1px solid #ddd;
    border-radius: 5px;
  }

  button {
    width: 100%;
    padding: 15px;
    font-size: 18px;
    background-color: #97d4e9;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  button:disabled {
    background-color: #cccccc;
  }
  .forgot-password {
    display: block;
    margin-top: 10px;
    text-align: center;
  }

  .forgot-password a {
    text-decoration: none;
    color: #97d4e9;
  }

  .error-message {
    color: red;
    font-size: 12px;
  }

  .login-close-button,
  .signup-close-button {
    font-size: 16px;
    background: none;
    border: none;
    color: #97d4e9;
    cursor: pointer;
    padding: 10px;
  }

  .login, .signup {
    font-size: 18px;
    color: white;
    position: absolute;
    top: 50px;
    border-radius: 30px;
    padding: 15px 25px;
    cursor: pointer;
    transition: background-color 0.3s;
  }

  .login:hover,
  .signup:hover {
    background-color: #3c9ecf;
  }

  .login-content, .signup-content {
    font-weight: bold;
    margin-right: 10px;
  }

  .plus {
    font-size: 20px;
  }
  
  /* Transition Effects */
  .slide-enter-active,
  .slide-leave-active {
    transition: transform 0.3s ease;
  }

  .slide-enter {
    transform: translateX(100%);
  }

  .slide-enter-to {
    transform: translateX(0);
  }

  .slide-leave {
    transform: translateX(0);
  }

  .slide-leave-to {
    transform: translateX(100%);
  }

/* 생일 입력 관련 스타일 */
.bir_wrap {
  display: flex;
  justify-content: space-between;
}

.bir_yy, .bir_mm, .bir_dd {
  flex: 1;
}

.ps_box {
  display: flex;
  justify-content: center;
  align-items: center;
}

.sel {
  width: 80%;
  padding: 8px;
  font-size: 16px;
}

.int {
  width: 80%;
  padding: 8px;
  font-size: 16px;
}

.error_next_box {
  color: red;
  font-size: 14px;
  margin-top: 10px;
}
</style>
