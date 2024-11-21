
import { createRouter, createWebHistory } from 'vue-router';
import FrontView from '@/views/FrontView.vue';
import MainView from '@/views/MainView.vue';
import MainLeftView from '@/components/main/MainLeftView.vue';
import MainHeaderView from '@/components/main/MainHeaderView.vue';
import MainBodyView from '@/components/main/MainBodyView.vue';
import WeightView from '@/components/mainBody/WeightView.vue';
import DietView from '@/components/mainBody/DietView.vue';
import ProductView from '@/components/mainBody/ProductView.vue';
import PostView from '@/components/mainBody/PostView.vue';
import PostListView from '@/components/mainBody/post/PostList.vue';
import PostDetailView from '@/components/mainBody/post/PostDetail.vue';
import PostRegistView from '@/components/mainBody/post/PostRegist.vue';
import {useAuth} from "@/composables/useAuth.js";
import {onMounted} from "vue";

const routes = [
  {
    path: '/',
    name: 'front',
    component: FrontView,
  },
  {
    path: '/main/:category/:currentView',
    name: 'main',
    component: MainView,
    children: [
      {
        path:'main-left',
        name:'mainLeft',
        component: MainLeftView,
      },
      { 
        path:'main-header',
        name:'mainHeader',
        component: MainHeaderView,
      },
      {
        path:'main-body',
        name:'mainBody',
        component: MainBodyView,
        children:[
          {
            path:'weight',
            name:'weight',
            component: WeightView,
          },
          {
            path:'diet',
            name:'diet',
            component: DietView,
          },
          {
            path:'product',
            name:'product',
            component: ProductView,
          },
          {
            path:'post',
            name:'post',
            component: PostView,
            children:[
              {
                path:'post-list',
                name:'postList',
                component: PostListView,
              },
              {
                path:'post-detail/:postNo',
                name:'postDetail',
                component: PostDetailView,
              },
              {
                path:'post-regist',
                name:'postRegist',
                component: PostRegistView,
              }
            ]
          }
        ]
      }
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