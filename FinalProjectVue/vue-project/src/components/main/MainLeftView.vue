<template>
    <div class="container">
        <div class ="winter">WINTER</div>
        <div class ="sleeping">SLEEPING</div>
        
        <RouterLink class="link">
            <img src="/homeButton.png" alt="Home" /><br>홈
        </RouterLink>
        <RouterLink class="link">
            <img src="/weightMangeMutton.png" alt="WeightMange" /><br>체중관리
        </RouterLink>
        <RouterLink class="link">
            <img src="/dietMangeButton.png" alt="DietMange" /><br>식단관리
        </RouterLink>
        <RouterLink class="link">
            <img src="/productRecommendButton.png" alt="ProductRecommend" /><br>제품추천
        </RouterLink>
        <RouterLink class="link">
            <img src="/PostButton.png" alt="Post" /><br>게시판
        </RouterLink>
        

    </div>
</template>

<script setup>

</script>

<style scoped>
    .container{
        color: white;
        font-weight: bold;
        display: flex;
        flex-direction: column; /* 자식 요소를 세로로 정렬 */
        align-items: center; /* 자식 요소 수평 중앙 정렬 */
        justify-content: flex-start; /* 자식 요소 위쪽 정렬 */
    }
    .winter{
        font-size: 19px;
        margin-top: 70px;
    }
    .sleeping{
        margin-bottom: 75px;
    }
    .body{
        background-color: black;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .link{
        text-decoration: none;
        color: white;
        /* background-color: black; */
        margin: 10px;
        width: 125px;
        height: 125px;
        border-radius: 25px;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }
    .link:hover{
        background-color: #b1def0;
        transition: 0.4s;
    }

</style>