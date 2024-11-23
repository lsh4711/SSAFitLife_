<template>
  <div>
    <nav class="container">
      <div class="category1">
        <h2 class="wintersleeping">WINTER SLEEPING</h2>
        <h1 class="title">체중관리</h1>
        <div class="content1">
          <div class="content">
            <RouterLink :to="{name: 'main' ,params: {category:'weightMange',currentView:'main'}}" class="link">체중 관리</RouterLink> <a class="arrow">></a>
          </div>
          <div class="content">
            <RouterLink :to="{name: 'main', params: {category:'weightMange',currentView:'bodyRecord'}}" class="link">체조성 기록</RouterLink> <a class="arrow">></a>
          </div>
          <div class="content">
            <RouterLink :to="{name: 'main', params: {category:'weightMange',currentView:'activityMange'}}" class="link">활동관리</RouterLink> <a class="arrow">></a>
          </div>
        </div>
      </div>
      <div class="category2">
        <h1 class="title">식단 관리</h1>
        <div class="content1">
          <div class="content">
            <RouterLink :to="{name: 'main', params: {category:'dietMange',currentView:'dietPlan'}}" class="link">식단 짜기</RouterLink> <a class="arrow">></a>
          </div>
          <div class="content">
            <RouterLink :to="{name: 'main', params: {category:'dietMange',currentView:'millprepAssist'}}" class="link">밀프렙 도우미</RouterLink> <a class="arrow">></a>
          </div>
          <div class="content">
            <RouterLink :to="{name: 'main', params: {category:'dietMange',currentView:'supUse'}}" class="link">보충제 활용</RouterLink> <a class="arrow">></a>
          </div>
        </div>
      </div>
      <div class="category3">
        <h1 class="title">제품추천</h1>
        <div class="content1">
          <div class="content">
            <RouterLink :to="{name: 'main' , params: {category:'productRecommend',currentView:'sup'}}" class="link">보충제</RouterLink> <a class="arrow">></a>
          </div>
          <div class="content">
            <RouterLink :to="{name: 'main' , params: {category:'productRecommend',currentView:'chickenBreast'}}" class="link">닭가슴살</RouterLink> <a class="arrow">></a>
          </div>
        </div>
      </div>
      <div class="category4">
        <h1 class="title">게시판</h1>
        <div class="content1">
          <div class="content">
            <RouterLink :to="{name: 'postList' , params: {category:'post',currentView:'post'}}" class="link">게시판</RouterLink> <a class="arrow">></a>
          </div>
        </div>
      </div>
    </nav>

    
  </div>

  <router-view></router-view>
</template>

<script setup>


</script>

<style scoped>




.wintersleeping{
    margin-top: 25px;
    font-size: 28px;
    color: #97d4e9;
    margin-left: 25px;
  }
  .title{
    position: absolute;
    margin-left: 25px;
    font-size: 28px;
    top: 100px;
  }
  .content1{
    /* background-color: #05202b; */

    width: 100%;
    position: absolute;
    top: 200px;
    
  }
  .content{
    margin-top: 20px;
    
  }
  .link{
    margin-left: 25px;
    font-size: 25px;
    text-decoration: none; /* 링크 밑줄 없애기 */
    color: gray; /* 색상 설정 */
  }
  .arrow{
    color: gray;
    font-size: 23px;
    position: absolute;
    font-weight: bold;
    right: 25px;
  }
  .container :hover{
    color: white;
    background-color: #a6daec;
    transition: 0.2s;
  }
  .container :hover.wintersleeping{
    color: white;
    transition: 0.2s;
  }
  .container :hover .link{
    color: white;
    transition: 0.2s;
    font-weight: bold;
  }
  .container :hover .arrow{
    color: white;
    transition: 0.2s;
  }
  .container :hover .wintersleeping{
    color: white;
    transition: 0.2s;
  }
  .content1:hover {
    color: white;
    cursor: pointer;
    transition: 0.2s;
  }
  
  .category1 {
    /* margin-left: 20px; */
    background-color: #e5f2fb;
    height: 100vh;  
    width: 100vh;
    margin: 8px;
    margin-left: 20px;
    border-radius: 30px;
    position: relative;
  }
  .category1,.category2,.category3,.category4 {
    /* margin-left: 20px; */
    background-color: #e5f2fb;
    height: 100vh;  
    width: 100vh;
    margin: 8px;
    margin-left: 20px;
    border-radius: 30px;
    position: relative;
  }

  .container{
    position: relative;
  /* background-color: #05202b; */
  display: flex;
  /* flex-direction: row; */
    justify-content: center;
    align-items: center;
    height: 100vh;  
    /* width: 100vh; */
  }

</style>
