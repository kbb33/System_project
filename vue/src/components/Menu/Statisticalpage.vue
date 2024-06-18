<template>
  <div style="width:100%; height:100%;
              background-color: #7fffd4;display: flex">
    <div style="width: calc(100% - 40px);height: calc(88vh - 10px);
                margin: 20px;
                border-radius: 5px;
                background-color: white">
      <el-input style="width: 45%;height: 30px;margin-top: 10px;margin-left: 10px;" value="圆筒2号"></el-input>
      <el-button type="primary" style="margin-left: 10px">查询题目</el-button>
      <div style="margin: 10px">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="分数情况" name="first">本题目的得分情况
            <Barchart></Barchart>
          </el-tab-pane>
          <el-tab-pane label="错误情况" name="second">本题目的错误情况
            <Piechart></Piechart>
          </el-tab-pane>
        </el-tabs>
      </div>
    </div>

  </div>
</template>

<script>
import Barchart from "@/components/Statistical/Barchart.vue";
import Piechart from "@/components/Statistical/Piechart.vue";
export default {
  name: "Statisticalpage",
  components:{Barchart,Piechart},
  data() {
    return {}
  },
  methods: {}
}
</script>

<style>

</style>