<template>
  <div>
    <h2>12321</h2>
  </div>
</template>

<script setup>
import {ref, watch} from 'vue';
import {useRoute} from 'vue-router';

const route = useRoute()
const category = ref(route.params.category)
const currentView = ref(route.params.currentView)
const lists = ref([]);

watch(() => route.params.category, (newcategory) => {
  category.value = newcategory
})
watch(() => route.params.currentView, (newcurrentView) => {
  currentView.value = newcurrentView
})
</script>

<style scoped>

</style>