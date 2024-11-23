<template>
    <div>
        <h1>체중관리</h1>
        <canvas ref="chartCanvas"></canvas>
    </div>
</template>

<script setup>
import { Chart, LineController, LineElement, PointElement, LinearScale, CategoryScale, Title, Tooltip, Legend } from "chart.js";
import { ref, onMounted, onBeforeUnmount, reactive } from "vue";

// 필요한 Chart.js 컴포넌트 등록
Chart.register(LineController, LineElement, PointElement, LinearScale, CategoryScale, Title, Tooltip, Legend);

// Chart.js 캔버스 및 인스턴스
const chartCanvas = ref(null);
const chartInstance = ref(null);

// 그래프 데이터와 옵션
const chartData = reactive({
  labels: ["January", "February", "March"],
  datasets: [
    {
      label: "Sales",
      data: [65, 59, 80],
      backgroundColor: "rgba(75, 192, 192, 0.2)",
      borderColor: "rgba(75, 192, 192, 1)",
      borderWidth: 1,
    },
  ],
});

const chartOptions = reactive({
  responsive: true,
  maintainAspectRatio: false,
  scales: {
    x: {
      type: "category", // x축 스케일
    },
    y: {
      type: "linear", // y축 스케일
    },
  },
});

// Chart.js 초기화
const renderChart = () => {
  if (chartCanvas.value) {
    const ctx = chartCanvas.value.getContext("2d");
    chartInstance.value = new Chart(ctx, {
      type: "line",
      data: chartData,
      options: chartOptions,
    });
  }
};

onMounted(() => {
  renderChart();
});

onBeforeUnmount(() => {
  if (chartInstance.value) {
    chartInstance.value.destroy();
  }
});
</script>





<style scoped>
canvas {
  max-width: 100%;
  max-height: 400px;
}
</style> 


