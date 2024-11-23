<template>
  <div class="main-container">
    <div class="main-left">
      <MainLeftView />
    </div>

    <div class="main-right">

      <div class="main-header">
        <MainHeaderView />
      </div>

      <div class="main-body">
        <RouterView></RouterView>
      </div>

    </div>
  </div>

</template>

<script setup>
import MainHeaderView from '@/components/main/MainHeaderView.vue';
import MainLeftView from '@/components/main/MainLeftView.vue';
</script>

<style scoped>

.main-container {
  display: flex;
  height: 100vh; /* 화면 전체 높이를 사용 */
}

.main-left {
  margin: 12px;
  width: 160px;
  height: 980px;
  border-radius: 25px;
  background-color: #98d3e9;
  display: flex;
  flex-direction: column; /*자식 요소를 세로로 정렬*/
  align-items: center; /* 자식 요소 수평 중앙 정렬 */
  justify-content: flex-start; /* 자식 요소 위쪽 정렬 */
  padding: 10px; /* 내부 여백 */
}

.main-right {
  /* display: flex; */
  flex-direction: column;
  flex: 1;
}

.main-header {
  margin-top: 65px;
  margin-left: 20px;
  width: 90%;
  height: 90px;
  /* background-color: rgb(144, 189, 241); */
}

.main-body {
  /* background-color: #90cfe6; */
  margin-top: 10px;
  margin-left: 30px;
  width: 90%;
}
</style>
