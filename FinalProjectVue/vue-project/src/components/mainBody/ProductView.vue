<template>
    <div>
        제품추천 뷰
    </div>
</template>

<script setup>
import { ref, watch } from 'vue';
import { useRoute } from 'vue-router';

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