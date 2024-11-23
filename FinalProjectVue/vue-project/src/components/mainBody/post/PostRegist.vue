<template>
  <div>
    <h2>게시물 수정</h2>
      <form @submit.prevent="registPost">
          <div class="container">
      <span class="title">
          <span class="item">제목</span>
          <span class="body">
          <input clas="update-title" type="text" v-model="newPostTitle" :placeholder = "'제목을 입력하세요.'">
          </span>
      </span>
      <span class="create">
          <span class="item">등록일</span>
          <span class="body"></span>
      </span>
    </div>
    <div class="container">

      <span class="writer">
          <span class="item">작성자</span>
          <span class="body"></span>
      </span>
      <span class="update">
        <span class="item">수정일</span>
        <span class="body"></span>
      </span>
    </div>

    <div class="container">
      <span class="views">
        <span class="item">조회수</span>
        <span class="body"></span>
      </span>
    </div>

    <div class="container">
      <span class="content">
        <span class="item">내용</span>
        <textarea class="update-content" type="text" v-model="newPostContent" :placeholder = "'내용을 입력하세요.'"></textarea>
      </span>
    </div>
    <div class="buttons">

      <RouterLink class="regist-button" :to="{ name: 'postList'}" @click.native.prevent="registPost">
        등록
      </RouterLink>
      <RouterLink class="post-button" :to="{name: 'postList'}">목록</RouterLink>
    </div>
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
  const newPostTitle = ref('');
  const newPostContent = ref('');
  const userNo = ref('1');
  const registPost = async () => {
    await axios.post(`http://localhost:8080/post/post/${userNo.value}`, {
      postTitle: newPostTitle.value,
      postContent: newPostContent.value,
    });
    alert('게시물이 성공적으로 되었습니다.');

    window.location.reload();
};



  watch(()=>route.params.category,(newcategory)=>{
      category.value = newcategory
  })
  watch(()=>route.params.currentView,(newcurrentView)=>{
      currentView.value = newcurrentView
  })
</script>

<style scoped>
  .buttons{
    margin-top: 20px;
    display: flex;
    margin-left: auto;
    margin-right: 30%;
    justify-content: flex-end;
  }
  .regist-button,.post-button{
    text-decoration: none;
    cursor: pointer;
    display: flex;
    align-items: center;
    justify-content: center;
    box-shadow: 1px 1px 5px rgba(0, 0, 0, 0.3);
    color: white;
    background-color: #97d4e9;
    border-radius: 35px;
    width: 64px;
    height: 27px;
    margin: 10px;
    
    /* flex-shrink: 0; */
  }
  .regist-button{
    color: blue;
  }
  .detail-button{
    color: black;
  }
  .post-button{
    color: gray;
  }


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
 h2{
  display: flex;
  justify-content: center;
  margin-bottom: 60px;
 }
</style>