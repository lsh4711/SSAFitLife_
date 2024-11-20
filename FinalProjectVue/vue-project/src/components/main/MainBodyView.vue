<template>
    <div class="main" >
        <div class="weight" v-if="category === 'weightMange'">
            <WeightView/>
        </div>
        <div class="diet" v-if="category === 'dietMange'">
            <DietView/>
        </div>
        <div class="product" v-if="category === 'productRecommend'">
            <ProductView/>
        </div>
        <div class="post" v-if="category === 'post'">
            <PostView/>
        </div>
    </div>
</template>

<script setup>
    import WeightView from '@/components/mainBody/WeightView.vue';
    import DietView from '@/components/mainBody/DietView.vue';
    import ProductView from '@/components/mainBody/ProductView.vue';
    import PostView from '@/components/mainBody/PostView.vue';

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