<template>
  <div class="main-container">
    <div class="main-left">
      <MainLeftView/>
    </div>
    <div class="main-right">
      <div class="main-header">
        <RouterLink>1번목록</RouterLink>
        <RouterLink>2번목록</RouterLink>
        <RouterLink>3번목록</RouterLink>
      </div>
      <div class="main-body">
        <div>메인화면</div>
      </div>
    </div>
  </div>
  
</template>

<script>
import MainLeftView from '@/components/main/MainLeftView.vue';
export default {
  name: 'MainView',
  components: {
    MainLeftView
  },
};





</script>

<style scoped>

.main-container {
  display: flex;
  height: 100vh; /* 화면 전체 높이를 사용 */
}
.main-left{
  margin: 12px;
  width: 160px;
  height: 980px;
  border-radius: 25px;
  background-color: #98d3e9; /* 배경 색상 (필요에 따라 변경) */
  display: flex;
  flex-direction: column; /*자식 요소를 세로로 정렬*/
  align-items: center; /* 자식 요소 수평 중앙 정렬 */
  justify-content: flex-start; /* 자식 요소 위쪽 정렬 */
  padding: 10px; /* 내부 여백 */
}
.main-right {
  display: flex;
  flex-direction: column; /* 세로 정렬 */
  flex: 1; /* 남은 공간 사용 */
}
.main-header{
  margin-top: 90px;
  margin-left: 20px;
  width: 90%;
  height: 90px;
  background-color: rgb(144, 189, 241);
}
.main-body{
  background-color: #90cfe6;
  margin: 30px;
  width: 90%;
}
</style>
