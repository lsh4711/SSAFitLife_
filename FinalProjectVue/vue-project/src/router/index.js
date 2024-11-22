
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
import PostUpdateView from '@/components/mainBody/post/PostUpdate.vue';
import WeightManageView from '@/components/mainBody/weight/weightManage.vue';
import ActivityManageView from '@/components/mainBody/weight/activityManage.vue';
import DietPlanView from '@/components/mainBody/diet/dietPlan.vue';
import SupView from '@/components/mainBody/product/sup.vue';
import ChickenBreastView from '@/components/mainBody/product/chickenBreast.vue';

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
            children:[
              {
                path:'weight-manage',
                name:'weightManage',
                component: WeightManageView,
              },
              {
                path:'activity-manage',
                name:'activityManage',
                component: ActivityManageView,
              }
            ]
          },
          {
            path:'diet',
            name:'diet',
            component: DietView,
            children:[
              {
                path:'diet-plan',
                name:'dietPlan',
                component: DietPlanView,
              },
            ]
          },
          {
            path:'product',
            name:'product',
            component: ProductView,
            children:[
              {
                path:'sup',
                name:'sup',
                component: SupView,
              },
              {
                path:'chicken-breast',
                name:'chickenBreast',
                component: ChickenBreastView,
              }
            ]
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
              },
              {
                path:'post-update/:postNo',
                name:'postUpdate',
                component: PostUpdateView,
              },
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