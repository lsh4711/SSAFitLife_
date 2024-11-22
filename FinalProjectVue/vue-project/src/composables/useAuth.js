import { ref } from 'vue';
import axios from "axios";

const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080', // Spring API 기본 URL
    withCredentials: true, // 쿠키 전송 허용
});

let isReissueInProgress = false;

export function useAuth() {
    const isLoggedIn = ref(false);

    // 토큰 만료 여부 확인 함수
    function isTokenExpired(token) {
        if (!token) {
            return true;
        }
        const payload = JSON.parse(atob(token.split('.')[1]));
        const currentTime = Math.floor(Date.now() / 1000);
        return payload.exp < currentTime;
    }

    // 새로운 액세스 토큰을 재발급 받는 함수
    const handleReissueToken = async () => {
        if (isReissueInProgress) return; // 이미 요청 중인 경우 중복 요청 방지
        isReissueInProgress = true;

        try {
            const response = await axiosInstance.post('/reissue', {}, {});
            return response.headers['authorization'];
        } catch (error) {
            console.error("Token reissue failed:", error);
        } finally {
            isReissueInProgress = false; // 요청이 끝난 후 상태 초기화
        }
    };

    // 토큰 만료 여부와 로그인 상태 확인
    const checkToken = async () => {
        const token = localStorage.getItem('accessToken');
        if (!token) {
            isLoggedIn.value = false;
            return;
        }
        if (!isTokenExpired(token)) {
            axiosInstance.defaults.headers.common['Authorization'] = token; // Axios 기본 헤더 설정
            isLoggedIn.value = true;
        } else {
            // 만약 토큰이 만료되었다면 새로운 액세스 토큰을 발급받음
            const newToken = await handleReissueToken();
            if (!newToken) {
                isLoggedIn.value = false;
                document.cookie = 'refresh=; path=/; expires=Thu, 01 Jan 1970 00:00:00 GMT; HttpOnly; Secure; SameSite=Strict';
                axiosInstance.defaults.headers.common['Authorization'] = null; // Authorization 헤더 초기화
                localStorage.removeItem('accessToken');
                window.location.href = '/'
            } else {
                isLoggedIn.value = true;
                axiosInstance.defaults.headers.common['Authorization'] = newToken;
                localStorage.setItem('accessToken', newToken);
            }
        }
    };

    // 로그인 처리
    const handleLogin = async (username, password) => {
        try {
            const response = await axiosInstance.post('/login', { username, password });
            // 'Authorization' 헤더에서 'Bearer' 토큰 추출
            const token = response.headers['authorization'];

            if (token) {
                axiosInstance.defaults.headers.common['Authorization'] = token;
                // 로컬 스토리지에 토큰 저장
                localStorage.setItem('accessToken', token);
                isLoggedIn.value = true;
                return false; // 로그인 성공
            }
            throw new Error('Access Token not received');
        } catch (error) {
            console.error('로그인 실패:', error);
            alert('로그인 실패');
            return true; // 로그인 실패
        }
    };

    // 로그아웃 처리
    const handleLogout = async () => {
        // document.cookie = 'refresh=; path=/; expires=Thu, 01 Jan 1970 00:00:00 GMT; HttpOnly; Secure; SameSite=Strict';
        try {
            const response = await axiosInstance.post('/logout', {}, {});
            // 로그아웃 후 상태 초기화
            isLoggedIn.value = false;
            axiosInstance.defaults.headers.common['Authorization'] = null; // Authorization 헤더 초기화
            localStorage.removeItem('accessToken');
            window.location.href = '/'; // 메인 페이지로 이동
        } catch (err) {
            console.log('로그아웃 실패:', err);
            alert('로그아웃 실패');
        }
    };

    return {
        isLoggedIn,
        checkToken,
        handleLogin,
        handleLogout,
    };
}
