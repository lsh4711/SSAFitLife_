<template>
    <div>
        <form>
            <div class="container">
        <span class="title">
            <span class="item">제목</span>
            <span class="body">
            <input clas="update-title" type="text" v-model="list.postTitle" :placeholder = "list.postTitle">
            </span>
        </span>
        <span class="create">
            <span class="item">등록일</span>
            <span class="body">{{ list.postCreatedDate }}</span>
        </span>
      </div>
      <div class="container">

        <span class="writer">
            <span class="item">작성자</span>
            <span class="body">{{ list.memNo }}</span>
        </span>
        <span class="update">
          <span class="item">수정일</span>
          <span class="body">{{ list.postUpdatedDate }}</span>
        </span>
      </div>

      <div class="container">
        <span class="views">
          <span class="item">조회수</span>
          <span class="body">{{ list.postViews }}</span>
        </span>
      </div>

      <div class="container">
        <span class="content">
          <span class="item">내용</span>
          <textarea class="update-content" type="text" v-model="list.postContent" :placeholder = "list.postContent"></textarea>
        </span>
      </div>
            <RouterLink></RouterLink>
        </form>
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
    const list = ref({});
    const requestPostDetail = async()=>{
    const { data } = await axios.get(`http://localhost:8080/post/post/${postNo.value}`);
        list.value = data;
    }
    requestPostDetail();
    
    watch(()=>route.params.category,(newcategory)=>{
        category.value = newcategory
    })
    watch(()=>route.params.currentView,(newcurrentView)=>{
        currentView.value = newcurrentView
    })
</script>

<style scoped>
    .container{
        position: relative;
        display: flex;
        align-items: center;
  }
  .container span{
    margin: 8px;
    display: flex;
  }
  .item{
    display: flex;
    flex-shrink: 0;
    align-items: center;
    justify-content: center;
    font-weight: bold;
    color: white;
    background-color: #97d4e9;
    border-radius: 35px;
    width: 64px;
    height: 27px;
  }
  .title{
    flex:1;
  }
  .writer{
    flex:1;
  }
  .create{
    flex:1;
  }
  .update{
    flex:1;
  }
  .body{
    display: flex;
    flex-shrink: 0;
  }
  .update-content {
    width: 50vw;
    height: 50vh;
}
</style>