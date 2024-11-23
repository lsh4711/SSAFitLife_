<template>
  <div class="container">
    <div class="winter">WINTER</div>
    <div class="sleeping">SLEEPING</div>

    <RouterLink
        v-for="menu in menus"
        :key="menu.to"
        :class="{ select: menu.to === `${currentMenu}/${currentView}` }"
        class="link"
        :to="menu.to"
    >
      <img :src="menu.src" :alt="menu.alt" />
      <br />
      {{ menu.label }}
    </RouterLink>
  </div>
</template>

<script setup>

import {ref, watch} from 'vue';
import {useRoute} from 'vue-router';

const route = useRoute()
const currentMenu = ref('');
const currentView = ref('');

watch(() => route.path, (path) => {
  const [_, menu, view] = path.split('/');
  currentMenu.value = menu;
  currentView.value = view;
}, {immediate: true});

const menus = [
  {
    label: '홈',
    to: '/',
    src: '/homeButton.png',
    alt: 'Home',
  },
  {
    label: '체중관리',
    to: '/weight-manage/weight-manage',
    src: '/weightMangeButton.png',
    alt: 'WeightMange',
  },
  {
    label: '식단관리',
    to: '/diet-manage/diet-plan',
    src: '/dietMangeButton.png',
    alt: 'DietMange',
  },
  {
    label: '제품추천',
    to: '/product-recommend/sup',
    src: '/productRecommendButton.png',
    alt: 'ProductRecommend',
  },
  {
    label: '게시판',
    to: '/board/posts',
    src: '/PostButton.png',
    alt: 'Post',
  },
];
</script>

<style scoped>
.container {
  color: white;
  font-weight: bold;
  display: flex;
  flex-direction: column; /* 자식 요소를 세로로 정렬 */
  align-items: center; /* 자식 요소 수평 중앙 정렬 */
  justify-content: flex-start; /* 자식 요소 위쪽 정렬 */
}

.winter {
  font-size: 19px;
  margin-top: 70px;
}

.sleeping {
  margin-bottom: 75px;
}

/* .body{
    background-color: black;
    display: flex;
    justify-content: center;
    align-items: center;
} */

.link {
  text-decoration: none;
  color: white;
  /* background-color: black; */
  margin: 10px;
  width: 125px;
  height: 125px;
  border-radius: 25px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.link:hover {
  background-color: #b1def0;
  transition: 0.4s;
}

.select {
  background-color: #b1def0;
}

</style>
