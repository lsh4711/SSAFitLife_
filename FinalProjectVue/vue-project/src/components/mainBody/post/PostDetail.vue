<template>
    <div>
        {{list}}
    </div>
</template>

<script setup>
import axios from 'axios';
import { ref, watch } from 'vue';
import { useRoute } from 'vue-router';

  const route = useRoute()
  const category = ref(route.params.category)
  const currentView = ref(route.params.currentView)
  const postNo = ref(route.params.postNo)
  const list = ref(list);

  const requestPostDetail = async()=>{
      const { data } = await axios.get(`http://localhost:8080/api-post/post/${postNo}`);
      list.value = data;
  }
  requestPostDetail();


  watch(()=>route.params.category,(newcategory)=>{
    category.value = newcategory
  })
  watch(()=>route.params.currentView,(newcurrentView)=>{
    currentView.value = newcurrentView
  })
  watch(()=>route.params.postNo,(newpostNo)=>{
    postNo.value = newpostNo
  })
</script>

<style scoped>

</style>