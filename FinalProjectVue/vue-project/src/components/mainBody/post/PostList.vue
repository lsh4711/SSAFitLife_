<template>
    <div class="list" >
        <div class="header">
            <span class="header-title"> 제목 </span>
            <span class="header-line">|</span>
            <span class="header-content"> 내용 </span>
            <span class="header-line">|</span>
            <span class="header-writer"> 작성자 </span>
            <span class="header-line">|</span> 
            <span class="header-create"> 작성일 </span>
            <span class="header-line">|</span>
            <span class="header-update"> 수정일 </span>
            <span class="header-line">|</span>
            <span class="header-views"> 조회수 </span> 
        </div>

        <div>

            <RouterLink class="post-detail">
                <div class="post" v-for="list in lists" :key="list.memNo" @click="postDetail(list.memNo)">
                    <span class="post-title">{{ list.postTitle }}</span>
                    <span class="post-line">|</span>
                    <span class="post-content">{{ list.postContent }}</span>
                    <span class="post-line">|</span>
                    <span class="post-writer">{{ list.memNo }}</span>
                    <span class="post-line">|</span>
                    <span class="post-create">{{ list.postCreatedDate }}</span>
                    <span class="post-line">|</span>
                    <span class="post-update">{{ list.postUpdatedDate }}</span>
                    <span class="post-line">|</span>
                    <span class="post-views">{{ list.postViews }}</span>
                </div>
            </RouterLink>
        </div>
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

    const requestPostList = async()=>{
        const { data } =await axios.get("http://192.168.210.62:8080/api-post/post");
        lists.value = data;
    }
    const postDetail = async(postNo)=>{
        const { data } =await axios.get(`http://192.168.210.62:8080/api-post/post/${postNo}`);
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
    .header,.post{
        display: flex;
        font-size: 18px;
    }
    
    .header span,.post span{
        display: flex;
        justify-content: center;
        align-items: center;
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
    border-bottom: 2px solid #ddd; /* 구분선 추가 */
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