<template>
    <div class="header">
        <div class="weight-manage" v-if="category === 'weightManage'">
            <RouterLink  class="link" :class="{ select : currentView==='weightManage'}" :to="{name: 'weightManage' ,params: {category:'weightManage',currentView:'weightManage'}}">체중관리</RouterLink>         
            <RouterLink class="link" :class="{ select : currentView==='bodyRecord'}" :to="{name: 'main', params: {category:'weightManage',currentView:'bodyRecord'}}" >체조성 기록</RouterLink>         
            <RouterLink class="link" :class="{ select : currentView==='activityManage'}"  :to="{name: 'activityManage', params: {category:'weightManage',currentView:'activityManage'}}" >활동 관리</RouterLink>         
        </div>
        <div class="diet-manage" v-if="category === 'dietManage'">
            <RouterLink class="link" :class="{ select : currentView==='dietPlan'}"  :to="{name: 'dietPlan', params: {category:'dietManage',currentView:'dietPlan'}}" >식단 짜기</RouterLink>         
            <RouterLink class="link" :class="{ select : currentView==='millprepAssist'}" :to="{name: 'main', params: {category:'dietManage',currentView:'millprepAssist'}}" >밀프렙 도우미</RouterLink>         
            <RouterLink class="link" :class="{ select : currentView==='supUse'}" :to="{name: 'main', params: {category:'dietManage',currentView:'supUse'}}" >보충제 활용</RouterLink>         
        </div>
        <div class="product-recommend" v-if="category === 'productRecommend'">
            <RouterLink class="link" :class="{ select : currentView==='sup'}" :to="{name: 'sup' , params: {category:'productRecommend',currentView:'sup'}}" >보충제</RouterLink>         
            <RouterLink class="link" :class="{ select : currentView==='chickenBreast'}"  :to="{name: 'chickenBreast' , params: {category:'productRecommend',currentView:'chickenBreast'}}" >닭가슴살</RouterLink>         
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
    .weight-manage,.diet-manage,.product-recommend,.post {
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