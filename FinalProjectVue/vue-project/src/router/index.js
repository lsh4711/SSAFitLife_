import DietView from '@/components/mainBody/DietView.vue';
import PostDetail from "@/components/mainBody/post/PostDetail.vue";
import PostList from "@/components/mainBody/post/PostList.vue";
import PostRegist from "@/components/mainBody/post/PostRegist.vue";
import PostView from "@/components/mainBody/PostView.vue";
import ProductView from "@/components/mainBody/ProductView.vue";
import WeightView from '@/components/mainBody/WeightView.vue';
import FrontView from '@/views/FrontView.vue';
import MainView from '@/views/MainView.vue';
import {createRouter, createWebHistory} from 'vue-router';

const routes = [
    {
        path: '/',
        name: 'front',
        component: FrontView,
    },
    {
        path: '/',
        name: 'menus',
        component: MainView,
        children: [
            {
                path: 'weight-manage',
                component: WeightView,
                children: [
                    {path: 'weight-manage', component: WeightView},
                    {path: 'body-record', component: WeightView},
                    {path: 'activity-manage', component: WeightView},
                ]
            },
            {
                path: 'diet-manage',
                component: DietView,
                children: [
                    {path: 'diet-plan', component: DietView},
                    {path: 'millprep-assist', component: DietView},
                    {path: 'sup-use', component: DietView},
                ]
            },
            {
                path: 'product-recommend',
                component: ProductView,
                children: [
                    {path: 'sup', component: ProductView},
                    {path: 'chicken-breast', component: ProductView},
                ]
            },
            {
                path: 'board',
                component: PostView,
                children: [
                    {path: 'posts', component: PostList},
                    {path: 'posts/regist', component: PostRegist},
                    {path: 'posts/:postId', component: PostDetail}
                ]
            },
        ]
    }
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
});

// 라우터 가드에서 checkToken 실행
// const { isLoggedIn, checkToken } = useAuth();
// router.beforeEach(async (to, from, next) => {
//   // 메인 페이지 이후의 경로인지 확인
//   if (to.path.startsWith('/main')) {
//     await checkToken(); // `main` 경로 이후부터 실행
//
//     // 로그인 필요 여부에 따라 라우트 제어
//     // if (to.meta.requiresAuth && !isLoggedIn.value) {
//     if (!isLoggedIn.value) {
//       alert('로그인이 필요합니다.');
//       return next({ name: 'front' }); // 로그인 페이지로 리다이렉트
//     }
//   }
//   next(); // 라우트 진행
// });

export default router;
