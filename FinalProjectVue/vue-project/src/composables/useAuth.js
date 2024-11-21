import { ref } from 'vue';
import axiosInstance from '@/plugins/axios';  // axios.js에서 인스턴스 가져오기

export function useAuth() {
    const isLoggedIn = ref(false);

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
        const parts = value.split(`; ${name}=`);  // 'name' 쿠키를 찾음
        if (parts.length === 2) return parts.pop().split(';').shift();
        return null;
    }

    // 새로운 액세스 토큰을 재발급 받는 함수
    const handleReissueToken = async () => {
        const refreshToken = getCookie('refresh'); // 쿠키에서 Refresh Token 가져오기
        try {
            const response = await axiosInstance.post('/reissue', {}, {
                headers: {
                    'refresh': refreshToken,  // Refresh 토큰을 Authorization 헤더에 포함
                },
            });

            // 서버에서 새로운 토큰을 받아온 경우
            const authHeader = response.headers['authorization'];

            if (authHeader && authHeader.startsWith('Bearer ')) {
                const newAccessToken = authHeader.split(' ')[1]; // 'Bearer' 이후의 토큰을 가져옴
                // Axios 기본 헤더 설정
                axiosInstance.defaults.headers['Authorization'] = `Bearer ${newAccessToken}`;
                // 로컬 스토리지에 새로운 액세스 토큰 저장
                localStorage.setItem('accessToken', `Bearer ${newAccessToken}`);
                isLoggedIn.value = true;
                return newAccessToken; // 새로운 액세스 토큰 반환
            }
            throw new Error('Reissue token failed');
        } catch (error) {
            console.error('Token reissue failed:', error);
            isLoggedIn.value = false;
            localStorage.removeItem('accessToken');  // 로컬 스토리지에서 토큰 삭제
            window.location.href = '/';  // 로그인 페이지로 이동
        }
    };

    // 토큰 만료 여부와 로그인 상태 확인
    const checkToken = async () => {
        // const token = axiosInstance.defaults.headers['Authorization']?.split(' ')[1]; // Authorization 헤더에서 토큰 추출
        const token = localStorage.getItem('accessToken');

        if (!token) {
            return;
        }

        if (token && !isTokenExpired(token)) {
            isLoggedIn.value = true;
        } else {
            isLoggedIn.value = false;
            // 만약 토큰이 만료되었다면 새로운 액세스 토큰을 발급받음
            const newToken = await handleReissueToken();
            if (newToken) {
                isLoggedIn.value = true; // 새로운 토큰으로 로그인 상태 업데이트
            }
        }
    };

    // 로그인 처리
    const handleLogin = async (username, password) => {
        try {
            const response = await axiosInstance.post('/login', { username, password });

            // 'Authorization' 헤더에서 'Bearer' 토큰 추출
            const authorizationHeader = response.headers['authorization'];
            if (authorizationHeader) {
                const token = authorizationHeader.split(' ')[1]; // "Bearer " 제거
                axiosInstance.defaults.headers['Authorization'] = `Bearer ${token}`; // Axios 기본 헤더 설정

                // 로컬 스토리지에 토큰 저장
                localStorage.setItem('accessToken', `Bearer ${token}`);

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
        const refreshToken = getCookie('refresh'); // 쿠키에서 Refresh Token 가져오기
        document.cookie = 'refresh=; path=/; HttpOnly; Secure; SameSite=Strict'; // Refresh Token 쿠키 삭제

        try {
            const response = await axiosInstance.post('/logout', {}, {
                headers: {
                    'refresh': refreshToken, // Refresh Token 전달
                },
            });

            // 로그아웃 후 상태 초기화
            isLoggedIn.value = false;
            axiosInstance.defaults.headers['Authorization'] = null; // Authorization 헤더 초기화
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
