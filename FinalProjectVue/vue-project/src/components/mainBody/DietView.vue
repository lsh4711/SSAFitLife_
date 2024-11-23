<template>
    <div>
        식단관리 뷰
      <RouterLink
          :to="{ name: 'DietTemp', params: { category: 'dietMange', currentView: 'dietPlan' } }">
        Go to Diet Temp
      </RouterLink>
      <RouterView/>
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