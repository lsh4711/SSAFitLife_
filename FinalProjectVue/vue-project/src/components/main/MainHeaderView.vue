<template>
    <div class="header">
        <div class="weight-mange" v-if="category === 'weightMange'">
            <RouterLink  class="link" :class="{ select : currentView==='weightMange'}" :to="{name: 'main' ,params: {category:'weightMange',currentView:'weightMange'}}">체중관리</RouterLink>         
            <RouterLink class="link" :class="{ select : currentView==='bodyRecord'}" :to="{name: 'main', params: {category:'weightMange',currentView:'bodyRecord'}}" >체조성 기록</RouterLink>         
            <RouterLink class="link" :class="{ select : currentView==='activityMange'}"  :to="{name: 'main', params: {category:'weightMange',currentView:'activityMange'}}" >활동 관리</RouterLink>         
        </div>
        <div class="diet-mange" v-if="category === 'dietMange'">
            <RouterLink class="link" :class="{ select : currentView==='dietPlan'}"  :to="{name: 'main', params: {category:'dietMange',currentView:'dietPlan'}}" >식단 짜기</RouterLink>         
            <RouterLink class="link" :class="{ select : currentView==='millprepAssist'}" :to="{name: 'main', params: {category:'dietMange',currentView:'millprepAssist'}}" >밀프렙 도우미</RouterLink>         
            <RouterLink class="link" :class="{ select : currentView==='supUse'}" :to="{name: 'main', params: {category:'dietMange',currentView:'supUse'}}" >보충제 활용</RouterLink>         
        </div>
        <div class="product-recommend" v-if="category === 'productRecommend'">
            <RouterLink class="link" :class="{ select : currentView==='sup'}" :to="{name: 'main' , params: {category:'productRecommend',currentView:'sup'}}" >보충제</RouterLink>         
            <RouterLink class="link" :class="{ select : currentView==='chickenBreast'}"  :to="{name: 'main' , params: {category:'productRecommend',currentView:'chickenBreast'}}" >닭가슴살</RouterLink>         
        </div>
        <div class="post" v-if="category === 'post'">
            <RouterLink class="link" :class="{ select : currentView==='post'}"  :to="{name: 'main' , params: {category:'post',currentView:'post'}}" >게시판</RouterLink>         
        </div>
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
    .header{
        margin: 30px;
        display: flex;
        justify-content: start;
        align-items: center;
        width: 100%;
        /* background-color: bisque; */
    }
    .weight-mange,.diet-mange,.product-recommend,.post {
    display: flex;
    gap: 13vw;
    }
    .link{
        text-decoration: none;
        font-size: 25px;
        font-weight: 100;
        color: black;
    }
    .select{
        font-weight: bold;
    }
    
</style>