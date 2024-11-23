<template>
    <div class="post">
    </div>
  <RouterView/>
</template>

<script setup>
import { ref, watch } from 'vue';
import { useRoute } from 'vue-router';
import PostList from './post/PostList.vue';
import PostDetail from './post/PostDetail.vue';
import PostRegist from './post/PostRegist.vue';

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
