<template>
  <div>
    <el-header style="position: relative;
                      width:100%;height: 6vh;
                      border-radius:5px;
                      background-color:#77C4B7;display: flex">
      <div style="position: absolute; top:1%; width:10%; display: flex">
        <router-link to="/home" class="homelink">
          <i class="el-icon-s-home" style="padding-top:1px; font-size: 20px;"></i>
          <span style="font-size: 20px">首页</span>
        </router-link>
      </div>
      <div style="background-color:#77C4B7;
                  width: 100%;height:30px;
                  margin-left: 35%;">
        <h2 style="margin: 0"> 编辑你的题目<<<<{{ this.topic }}>>>></h2>
      </div>
    </el-header>
    <el-main>
      <div style="width: 100%;height: 94vh;
                  background-color: white;
                  display: flex;">
        <div style="position:relative;width: calc(100% - 6px);height: calc(94vh - 6px);margin: 3px;
                    border-radius:5px;background-color: #51BBC4">
          <el-tabs type="border-card">
            <el-tab-pane
                v-for="(items,index) in Piyu"
                :key="items.index"
                :label="items.factor"
                :name="items.index"
            >
              <!-- 标签页内容 -->
              <el-collapse>
                <el-collapse-item v-for="(item, index) in items.typeList" :key="index" :name="index">
                  <template slot="title">
                    <!--  ？的作用判断Type存不存在，如果存在，那就打印，如果不存在不报错          -->
                    {{ item?.type }}
                    <el-popover
                        placement="right"
                        width="500"
                        trigger="click"
                        style="display: flex">
                      <el-input v-model="newType" placeholder="输入修改的类型"
                                style="width: 80%;margin-right: 10px"></el-input>
                      <el-button type="success" @click="eidttype(items.factor,item.type,newType)">提交</el-button>
                      <el-button type="warning" size="mini" style="margin: 0 5px 0 5px" slot="reference">编辑
                      </el-button>
                    </el-popover>
                    <el-button type="danger" size="mini" @click="deltype(items.factor,item.type)">删除</el-button>
                  </template>
                  <div>
                    <el-table :data="item.typeDetails">
                      <el-table-column label="批语" prop="sentence" width="900%"></el-table-column>
                      <el-table-column label="分值" prop="point"></el-table-column>
                      <el-table-column label="操作">
                        <template slot-scope="scope">
                          <el-popover
                              placement="left"
                              width="500"
                              trigger="click"
                              style="display: flex">
                            <el-input v-model="newSentence" style="width: 80%;margin-right: 10px"
                                      placeholder="输入修改的批语">
                            </el-input>
                            <el-button type="warning" @click="eidtSen(newSentence,scope.row)">提交
                            </el-button>
                            <el-button type="warning" size="mini" slot="reference">更改批语</el-button>
                          </el-popover>
                          <el-popover
                              placement="left"
                              width="200"
                              trigger="click"
                              style="display: flex">
                            <el-input v-model="newPoint" placeholder="分数"
                                      style="width: 50%;margin-right: 10px"></el-input>
                            <el-button type="warning" @click="eidtPoint(newPoint,scope.row)">提交
                            </el-button>
                            <el-button type="info" size="mini" slot="reference">更改分数</el-button>
                          </el-popover>
                          <el-button type="danger" size="mini" @click="del(scope.$index,scope.row)">删除批语
                          </el-button>
                        </template>
                      </el-table-column>
                    </el-table>
                  </div>
                </el-collapse-item>
              </el-collapse>
            </el-tab-pane>
          </el-tabs>
        </div>
      </div>
    </el-main>
  </div>
</template>
<script>
export default {
  name: "Editpage",
  data() {
    return {
      newType: '',
      newSentence: '',
      newPoint: '',
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
      this.topicid = this.$route.query.id
      console.log(this.topicid)
      this.$request.get('/factor/getPiyu', {params: {topicid: this.topicid}}).then(res => {
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
        console.log(this.Piyu)
      })
    },
    //下面的是编辑部分
    eidttype(factor, type, newType) {
      this.$confirm("你确定要修改吗", "确认", {type: "success"}).then(response => {
        this.$request.put('/factor/updateType', '', {
          params: {
            factor: factor,
            type: type,
            newType: newType
          }
        }).then(res => {
          if (res.code === '200') {
            this.$message.success("操作成功")
          } else {
            this.$message.error(res.msg)
          }
          this.load()
        })
      }).catch(() => this.$message({
        type: 'info',
        message: '已取消修改'
      }))
    },
    eidtPoint(newPoint, row) {
      this.$confirm("你确定要修改吗", "确认", {type: "success"}).then(response => {
        this.$request.put(`/factor/updatePoint/${row.id}`, '', {params: {newPoint: newPoint}}).then(res => {
          if (res.code === '200') {
            this.$message.success("操作成功")
          } else {
            this.$message.error(res.msg)
          }
          this.load()
        })
      }).catch(() => this.$message({
        type: 'info',
        message: '已取消修改'
      }))
    },
    eidtSen(newSen, row) {
      this.$confirm("你确定要修改吗", "确认", {type: "success"}).then(response => {
        this.$request.post(`/factor/updateSen`, '', {params: {id: row.id, newSentence: newSen}}).then(res => {
          console.log()
          console.log(res)//提示数据（data）并不是预期中的配置对象（config）
          if (res.code === '200') {
            this.$message.success("操作成功")
          } else {
            this.$message.error(res.msg)
          }
          this.load()
        })
      }).catch(() => this.$message({
        type: 'info',
        message: '已取消修改'
      }))
    },
    //下面的是删除部分
    deltype(factor, type) {
      console.log(factor, type)
      this.$confirm("你确定要删除吗", "确认", {type: "warning"}).then(response => {
        this.$request.delete('/factor/deleteType', {params: {factor: factor, type: type}}).then(res => {
          if (res.code === '200') {
            this.$message.success("操作成功")
          } else {
            this.$message.error(res.msg)
          }
          this.load()
        })
      }).catch(() => this.$message({
        type: 'info',
        message: '已取消删除'
      }))
    },
    del(index, row) {
      this.$confirm("你确定要删除吗", "确认", {type: "success"}).then(response => {
        this.$request.delete(`/factor/deletePiyu/${row.id}`
        ).then(res => {
          if (res.code === '200') {
            this.$message.success("操作成功")
          } else {
            this.$message.error(res.msg)
          }
          this.load()
        })
      }).catch(() => this.$message({
        type: 'info',
        message: '已取消删除'
      }))
    }
  }
}
</script>

<style scoped>

</style>