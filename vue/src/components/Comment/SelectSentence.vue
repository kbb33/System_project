<template>
  <div style="    width: calc(100% - 8px);height: calc(100% - 8px);
                  margin: 0 4px 4px 4px;border-radius: 3px;
                  background-color: white;
                  overflow-y: scroll">
    <div style="  flex: 1;
                  width: calc(100% - 8px);height: 8%;
                  margin: 4px 4px;border-radius: 3px;
                  background-color: white">
      <input style="width: 80%;height: 80%;margin-top: 5px">
      <el-button style="margin-left: 15px;margin-top: 5px" type="primary">新增</el-button>
    </div>
    <el-tabs type="border-card">
      <el-tab-pane
          v-for="(items,index) in Piyu"
          :key="items.index"
          :label="items.factor"
          :name="items.index"
      >
        <!-- 标签页内容 -->
        <el-collapse :max-height="200">
          <el-collapse-item v-for="(item, index) in items.typeList" :key="index" :name="index">
            <template slot="title">
              <!--  ？的作用判断Type存不存在，如果存在，那就打印，如果不存在不报错          -->
              {{ item?.type }}
            </template>
            <div>
              <el-table :row-style="isRed" :show-header="false" :data="item?.typeDetails" style="font-size: 13px"
                        @row-click="openDetails">
                <el-table-column prop="sentence" width="400%"></el-table-column>
                <el-table-column prop="point" width="20%"></el-table-column>
              </el-table>
            </div>
          </el-collapse-item>
        </el-collapse>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
export default {
  name: "selectSentence",
  data() {
    return {
      selectedArrData: [],
      Piyu: [],
      // Piyu: [{
      //   factor: '',
      //   typeList: [{
      //     type: '',
      //     typeDetails: [{
      //       id: '',
      //       sentence: '',
      //       point: ''
      //     }]
      //   }]
      // }]//Piyu数据类型
    }
  },
  props: {
    id: Number
  },
  created() {
    this.load()
  },
  methods: {
    quchong(arr) {
      let arr1 = arr.map(item => {
        return JSON.stringify(item)
      })
      let arr2 = [...new Set(arr1)]
      let arr3 = arr2.map(item => {
        return JSON.parse(item)
      })
      return arr3
    },//JSON数组去重
    load() {//对后端传来的数组做Piyu数据结构封装
      this.Piyu.length = 0//每次渲染之前先清空一次Piyu对象
      this.$request.get('/factor/getPiyu', {params: {topicid: this.id}}).then(res => {
        for (let i = 0; i < res.data.length; i++) {
          this.Piyu.push({factor: res.data[i].factor, typeList: []})
        }
        this.Piyu = this.quchong(this.Piyu)
        for (let i = 0; i < this.Piyu.length; i++) {
          for (let j = 0; j < res.data.length; j++) {
            if (this.Piyu[i].factor === res.data[j].factor) {
              this.Piyu[i].typeList.push({type: res.data[j].type, typeDetails: []})
            }
          }
          this.Piyu[i].typeList = this.quchong(this.Piyu[i].typeList)
        }
        for (let i = 0; i < this.Piyu.length; i++) {
          for (let j = 0; j < this.Piyu[i].typeList.length; j++) {
            for (let k = 0; k < res.data.length; k++) {
              if (this.Piyu[i].factor === res.data[k].factor && this.Piyu[i].typeList[j].type === res.data[k].type) {
                this.Piyu[i].typeList[j].typeDetails.push({
                  id: res.data[k].id,
                  sentence: res.data[k].sentence, point: res.data[k].point
                })
              }
            }
          }
          this.Piyu[i].typeList = this.quchong(this.Piyu[i].typeList)
        }
      })
    },
    isRed({row}) {
      const checkIdList = this.selectedArrData.map((item) => item.id);
      if (checkIdList.includes(row.id)) {
        return {
          backgroundColor: "#DE6",
          color: "red",
        };
      }
    },
    openDetails(row) {
      this.selectedArrData = [row];
      this.$emit("sendid", row.id)//向父组件发送批语id
    }
  }
}
</script>

<style scoped>
.el-table tbody tr:hover > td {
  background-color: transparent !important;
}

</style>