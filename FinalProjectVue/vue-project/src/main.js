import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axiosInstance from '@/plugins/axios';

// 앱 시작 시 로컬 스토리지에서 토큰 가져와 Axios 헤더에 설정
// const token = localStorage.getItem('accessToken');
// if (token) {
//     axiosInstance.defaults.headers['Authorization'] = `Bearer ${token}`;
// }

const app = createApp(App)
app.use(router)
app.mount('#app')
