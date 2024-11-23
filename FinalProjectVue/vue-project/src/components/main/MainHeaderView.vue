<template>
  <div class="header">
    <div class="menu">
      <RouterLink
          v-for="view in menus[currentMenu]"
          class="link"
          :class="{select: currentView === view.path}"
          :to="view.path"
      >{{ view.label }}
      </RouterLink>
    </div>
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

const menus = {
  'weight-manage': [
    {label: '체중관리', path: 'weight-manage'},
    {label: '체조성 기록', path: 'body-record'},
    {label: '활동관리', path: 'activity-manage'},
  ],
  'diet-manage': [
    {label: '식단 짜기', path: 'diet-plan'},
    {label: '밀프렙 도우미', path: 'millprep-assist'},
    {label: '보충제 활용', path: 'sup-use'},
  ],
  'product-recommend': [
    {label: '보충제 추천', path: 'sup'},
    {label: '닭가슴살 추천', path: 'chicken-breast'},
  ],
  'board': [
    {label: '게시판', path: 'posts'},
  ],
};
</script>

<style scoped>
.header {
  margin: 30px;
  display: flex;
  justify-content: start;
  align-items: center;
  width: 100%;
  /* background-color: bisque; */
}

.menu {
  display: flex;
  gap: 13vw;
}

.link {
  text-decoration: none;
  font-size: 25px;
  font-weight: 100;
  color: black;
}

.select {
  font-weight: bold;
}
</style>
