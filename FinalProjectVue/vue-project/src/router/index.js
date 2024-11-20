
import { createRouter, createWebHistory } from 'vue-router';
import FrontView from '@/views/FrontView.vue';
import MainView from '@/views/MainView.vue';
import MainLeftView from '@/components/main/MainLeftView.vue';

const routes = [
  {
    path: '/',
    name: 'front',
    component: FrontView,
  },
  {
    path: '/main',
    name: 'main',
    component: MainView,
    children: [
      {
        path:'/main-left',
        name:'mainLeft',
        component: MainLeftView,
      }
    ]
  }


];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
});

export default router;