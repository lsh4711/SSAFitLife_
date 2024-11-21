import axios from 'axios';

const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080', // Spring API 기본 URL
    withCredentials: true, // 쿠키 전송 허용
});

// axiosInstance.interceptors.request.use(
//     (config) => {
//         // 예시: Authorization 헤더를 설정 (토큰이 있을 경우)
//         const token = localStorage.getItem('accessToken'); // 또는 쿠키에서 가져오기
//         if (token) {
//             config.headers['Authorization'] = token;
//         }
//         return config;
//     },
//     (error) => {
//         return Promise.reject(error);
//     }
// );
//
// axiosInstance.interceptors.response.use(
//     (response) => response,
//     (error) => {
//         // 응답 오류 처리
//         return Promise.reject(error);
//     }
// );

export default axiosInstance;
