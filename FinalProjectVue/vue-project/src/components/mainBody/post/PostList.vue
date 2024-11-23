<template>
    <div class="list" >
        <div class="header">
            <span class="header-title"> 제목 </span>
            <span class="header-content"> 내용 </span>
            <span class="header-writer"> 작성자 </span>
            <span class="header-create"> 작성일 </span>
            <span class="header-update"> 수정일 </span>
            <span class="header-views"> 조회수 </span>
        </div>

        <div>
            <div v-for="list in lists" :key="list.postNo">
                <RouterLink :to="{ name: 'postDetail' , params:{postNo: list.postNo}}" custom>
                    <template #default="{ navigate }">
                        <div class="post" @click="navigate">
                            <span class="post-title">{{ list.postTitle }}</span>
                            <span class="post-content">{{ list.postContent }}</span>
                            <span class="post-writer">{{ list.memNo }}</span>
                            <span class="post-create">{{ list.postCreatedDate }}</span>
                            <span class="post-update">{{ list.postUpdatedDate }}</span>
                            <span class="post-views">{{ list.postViews }}</span>
                        </div>
                    </template>
                </RouterLink>
            </div>
        </div>
        
    </div>
    <div class="footer">
    <span class="button">
        <RouterLink class="regist-button" :to="{ name: 'postRegist'}">등록</RouterLink>
    </span>
    <span class="search-standard">
        <select v-model="searchStandard">
            <option>제목</option>
            <option>내용</option>
            <option>작성자</option>
        </select>
        <input type="text" :placeholder="`검색할 ${searchStandard} 입력`" />
        <button>검색</button>
    </span>
    </div>
    
</template>

<script setup>
    import { ref, watch } from 'vue';
    import { useRoute } from 'vue-router';
    import axios from 'axios';

    const route = useRoute()
    const category = ref(route.params.category)
    const currentView = ref(route.params.currentView)
    const lists = ref([]);
    const searchStandard = ref('제목');

    const requestPostList = async()=>{
        const { data } =await axios.get("http://localhost:8080/post/post");
        lists.value = data;
    }



    requestPostList();

    watch(()=>route.params.category,(newcategory)=>{
        category.value = newcategory
    })
    watch(()=>route.params.currentView,(newcurrentView)=>{
        currentView.value = newcurrentView
    })
</script>

<style scoped>
    input{
        width: 200px;
    }
    .footer{
        display: flex;
        margin-top: 10px;
    }
    .button{
    display: flex;
    margin-right: auto;
    justify-content: flex-end;
  }
  .regist-button{
    color: blue;
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
  }

    .header,.post{
        display: flex;
        font-size: 18px;
    }
    
    .header span,.post span{
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .post-detail{
        text-decoration: none;
        color: black;
    }
    /* .header-title, .post-title,
    .header-content, .post-content,
    .header-writer, .post-writer,
    .header-create, .post-create,
    .header-update, .post-update,
    .header-views, .post-views{
        display: flex;
        justify-content: center;
        align-items: center;
    } */
.header-title, .post-title {
    flex: 2;
}
.header-content, .post-content {
    flex: 3;
}
.header-writer, .post-writer {
    flex: 1;
}
.header-create, .post-create {
    flex: 1.5;
}
.header-update, .post-update {
    flex: 1.5;
}
.header-views, .post-views {
    flex: 1;
}
.header {
    font-weight: bold;
    /* border-bottom: 2px solid #ddd; */
    padding: 10px 0;
    background-color: #b1def0;
    border-radius: 10px;
}
.post {
    padding: 8px 0;
    border-bottom: 1px solid #eee;
    cursor: pointer;
}
</style>