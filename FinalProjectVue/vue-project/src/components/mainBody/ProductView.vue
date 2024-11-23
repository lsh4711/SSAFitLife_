<template>
    <div>
        제품추천 뷰
    </div>
  <RouterView/>
</template>

<script setup>
import { ref, watch } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute()
const currentMenu = ref('');
const currentView = ref('');

watch(() => route.path, (path) => {
  const [_, menu, view] = path.split('/');
  currentMenu.value = menu;
  currentView.value = view;
}, {immediate: true});
</script>

<style scoped>

</style>
