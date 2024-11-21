
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

// // 글로벌 navigation guard 설정
// router.beforeEach((to, from, next) => {
//   const token = localStorage.getItem('accessToken');
//   console.log(token)
//
//   if (token) {
//     if (isTokenExpired(token)) {
//       localStorage.removeItem('accessToken');
//       next('/'); // 토큰 만료시 로그인 페이지로 이동
//     } else {
//       next(); // 유효한 토큰이 있으면 요청한 페이지로 이동
//     }
//   } else {
//     if (to.path !== '/') {
//       next('/'); // 토큰이 없으면 로그인 페이지로 이동
//     }
//   }
// });
//
// function isTokenExpired(token) {
//   const payload = JSON.parse(atob(token.split('.')[1]));
//   const currentTime = Math.floor(Date.now() / 1000);
//   return payload.exp < currentTime;
// }


export default router;