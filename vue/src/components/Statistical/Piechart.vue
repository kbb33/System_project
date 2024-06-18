<template>
  <div style="width: 1000px;height: 400px" id="pie"></div>
</template>
<script>
import * as echarts from "echarts";

const option = {

  series: [
    {
      name: 'Access From',
      type: 'pie',
      radius: '80%',
      data: [
        { value: 10, name: '尺寸缺失' },
        { value: 35, name: '标注不规范' },
        { value: 80, name: '重复标注' },
        { value: 44, name: '封闭的尺寸链' },
      ],
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      }
    }
  ]
};
export default {
  name: "Piechart",
  mounted() {
    let pieDom = document.getElementById('pie');
    let pieChart = echarts.init(pieDom);
    pieChart.setOption(option)
  }
}

</script>


<style scoped>

</style>