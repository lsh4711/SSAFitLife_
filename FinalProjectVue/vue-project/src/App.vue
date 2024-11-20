<template>
  <div>
    <RouterView/>
    
  </div>




  <div id="app">
      <!-- <button @click="loginSidePanel" class="login"><a class="login-content">로그인</a><a class="plus">+</a></button> -->
      <!-- <button @click="signupSidePanel" class="signup"><a class="signup-content">회원가입</a><a class="plus">+</a></button> --> -->
      <button @click="mypageSidePanel" class="mypage"><a class="mypage-content">마이페이지</a><a class="plus">+</a></button>
      
      <transition name="slide">
        <!-- 로그인 패널 -->
        <div v-if="isLoginVisible" class="login-side-panel">
          <button class="login-close-button" @click="loginSidePanel">닫기</button>
          <h2>로그인 화면</h2>
          <p>여기에 원하는 내용을 추가하세요.</p>
        </div>
      </transition>
      <transition name="slide">
        <!-- 회원가입 패널 -->
        <div v-if="isSignupVisible" class="signup-side-panel">
          <button class="signup-close-button" @click="signupSidePanel">닫기</button>
          <h2>회원가입 화면</h2>
          <p>여기에 원하는 내용을 추가하세요.</p>
        </div>
      </transition>
      <transition name="slide">
        <!-- 마이페이지 패널 -->
        <div v-if="isMypageVisible" class="mypage-side-panel">
          <button class="mypage-close-button" @click="mypageSidePanel">닫기</button>
          <h2>마이페이지 화면</h2>
          <p>여기에 원하는 내용을 추가하세요.</p>
        </div>
      </transition>
    </div>
</template>

<script setup>
  // Vue Composition API 사용
import { ref } from 'vue';
import { useRouter } from 'vue-router';
// 로그인, 회원가입, 마이페이지 상태 변수
const isLoginVisible = ref(false);
const isSignupVisible = ref(false);
const isMypageVisible = ref(false);

const router = useRouter();
// 패널 토글 함수
const loginSidePanel = () => {
  isLoginVisible.value = !isLoginVisible.value;
};

const signupSidePanel = () => {
  isSignupVisible.value = !isSignupVisible.value;
};

const mypageSidePanel = () => {
  isMypageVisible.value = !isMypageVisible.value;
};


</script>

<style scoped>
  
/* 오른쪽 패널 스타일 */
.login-side-panel {
  position: fixed;
  top: 0;
  right: 0;
  width: 370px;
  height: 100%;
  background-color: white;
  box-shadow: -2px 0 5px rgba(0, 0, 0, 0.5);
  padding: 20px;
  transition: transform 0.3s ease;
}
.signup-side-panel {
  position: fixed;
  top: 0;
  right: 0;
  width: 1460px;
  height: 100%;
  background-color: white;
  box-shadow: -2px 0 5px rgba(0, 0, 0, 0.5);
  padding: 20px;
  transition: transform 0.3s ease;
}
.mypage-side-panel {
  position: fixed;
  top: 0;
  right: 0;
  width: 840px;
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
</style>
