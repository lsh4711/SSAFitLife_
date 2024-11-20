import { ref } from 'vue';
import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://localhost:8080', // Spring API 기본 URL
    withCredentials: true, // 쿠키 전송 허용
});

export function useAuth() {
    const isLoggedIn = ref(false);
    const accessToken = ref(null);

    // 토큰 만료 여부 확인 함수
    function isTokenExpired(token) {
        if (!token) return true;
        const payload = JSON.parse(atob(token.split('.')[1]));
        const currentTime = Math.floor(Date.now() / 1000);
        return payload.exp < currentTime;
    }

    // 쿠키에서 토큰 가져오기
    function getCookie(name) {
        const value = `; ${document.cookie}`;
        const parts = value.split(`; ${name}=`);
        if (parts.length === 2) return parts.pop().split(';').shift();
        return null;
    }

    // 토큰 만료 여부와 로그인 상태 확인
    function checkToken() {
        const token = getCookie('access');
        if (token && !isTokenExpired(token)) {
            isLoggedIn.value = true;
            accessToken.value = token;
        } else {
            isLoggedIn.value = false;
        }
    }

    // 로그인 처리
    const handleLogin = async (username, password) => {
        try {
            const response = await instance.post('/login', { username, password });
            const token = response.headers['access'];
            document.cookie = `access=${token}; path=/`; // 토큰을 쿠키에 저장
            isLoggedIn.value = true;
            accessToken.value = token;
            return false;
        } catch (error) {
            alert('로그인 실패')
            console.error('로그인 실패:', error);
            return true;
        }
    };

    // 로그아웃 처리
    const handleLogout = async () => {
        const refreshToken = getCookie('refresh'); // 쿠키에서 토큰 가져오기
        document.cookie = 'access=; path=/'; // 쿠키 삭제

        try {
            const response = await instance.post('/logout', {}, {
                headers: {
                    'refresh': `${refreshToken}` // 토큰을 헤더에 추가
                }
            });
            isLoggedIn.value = false;
            accessToken.value = null;
            // 로그아웃 성공 후 메인 페이지로 리다이렉트
            window.location.href = '/';  // 메인 페이지로 이동
        } catch (err) {
            console.log('로그아웃 실패:', err);
            alert('로그아웃 실패');
        }
    }

    return {
        isLoggedIn,
        accessToken,
        checkToken,
        handleLogin,
        handleLogout,
    };
}
