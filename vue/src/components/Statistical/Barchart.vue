<template>
  <div style="width: 1000px;height: 400px" id="bar"></div>
</template>
<script>
import * as echarts from 'echarts';

const option = {
  xAxis: {
    type: 'category',
    data: ['60以下', '70-80', '80-90', '90-95', '95-100']
  },
  yAxis: {
    type: 'value'
  },
  series: [
    {
      data: [10, 6, 5, 4, 15, 3, 1],
      type: 'bar'
    }
  ]
};
export default {
  name: "Figure",
  mounted() {
    let barDom = document.getElementById('bar');
    let barChart = echarts.init(barDom);
    barChart.setOption(option)
  }
}
</script>


<style scoped>

</style>