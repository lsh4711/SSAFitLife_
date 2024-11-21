<template>
    <div class="post">
          <RouterView/>
    </div>
</template>

<script setup>
import { ref, watch } from 'vue';
import { useRoute } from 'vue-router';
import PostList from './post/PostList.vue';
import PostDetail from './post/PostDetail.vue';
import PostRegist from './post/PostRegist.vue';

  const route = useRoute()
  const category = ref(route.params.category)
  const currentView = ref(route.params.currentView)

  watch(()=>route.params.category,(newcategory)=>{
    category.value = newcategory
  })
  watch(()=>route.params.currentView,(newcurrentView)=>{
    currentView.value = newcurrentView
  })

</script>

<style scoped>

</style>