
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

export default router;