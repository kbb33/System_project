<template>
  <div>
    <input
        style="width: calc(100% - 10px); height: 50px; font-size: 25px; margin: 5px 5px;
               border-radius: 5px; background-color: #76a2db"
        placeholder="输入题目考察要素"
        v-model="factor"
    />
    <div>
      <el-input style="width: 60%; margin: 10px 5px" v-model="type"></el-input>
      <el-button type="primary" style="width: 17%; margin-left: 5px" @click="addType">创建类型</el-button>
      <el-button type="warning" style="width: 17%; margin-left: 5px" @click="submit">保存</el-button>
    </div>
    <div
        style="width: calc(100% - 10px); height: 73vh; overflow-y: scroll; margin: 0 5px 5px 5px;
               border-radius: 5px; background-color: #51bbc4">
      <el-collapse>
        <el-collapse-item v-for="(item, index) in typeList" :key="index" :name="index">
          <template slot="title">
            <!--  ？的作用判断Type存不存在，如果存在，那就打印，如果不存在不报错          -->
            {{ item?.type }}
            <el-button type="danger" size="mini" @click="deltype(index)">删除</el-button>
          </template>
          <div>
            <div style="width: calc(100% - 20px); margin: 0 10px; background-color: white">
              <el-input style="width: 68%" v-model="item.sentence" placeholder="请输入批语"></el-input>
              <el-input style="width: 15%" v-model="item.point" placeholder="分数"></el-input>
              <el-button type="primary" size="medium" style="margin-left: 10px" @click="addDetails(item, index)">
                添加批语
              </el-button>
            </div>
            <el-table :data="item.typeDetails">
              <el-table-column label="批语" prop="sentence"></el-table-column>
              <el-table-column label="分值" prop="point" width="120%"></el-table-column>
              <el-table-column label="操作" width="120%">
                <template slot-scope="scope">
                  <el-button type="danger" size="mini" @click="del(scope.$index,item.typeDetails)">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
        </el-collapse-item>
      </el-collapse>
    </div>
  </div>
</template>
<script>
import {integer} from "@vee-validate/rules";

export default {
  name: 'Apptype',
  data() {
    return {
      // 类型
      forfactor: {
        topicid: 0,
        factor: '',
        type: '',
        sentence: '',
        point: '',
      },
      factor: '',
      type: '',
      // 类型数据结构
      typeList: []
    }
  },
  props: {
    topicid: Number
  },
  mounted() {
  },
  methods: {
    // 添加类型
    addType() {
      // 为空则不增加
      if (!this.type) return
      // 调用后端接口增加类型
      this.typeList.push({type: this.type, sentence: '', point: '', typeDetails: []})
      this.type = ''
      // 重新调用后端接口，看是否添加上
    },
    // 添加类型详细数据
    addDetails(item, index) {
      // 数据或者批语为空则不增加
      if (!item.sentence || !item.point) return
      if (!Number.isFinite(+item.point)) {
        this.$message.error("请输入数字型分数哦")
        return
      }
      this.typeList[index].typeDetails.push({sentence: item.sentence, point: item.point})
      item.sentence = ''
      item.point = ''
    },
    del(index, typeDetails) {
      this.$confirm("您确定要删除吗", "确定删除", {type: 'error'}).then(() => {
        typeDetails.splice(index, 1)
        this.$message.success("成功删除")
      }).catch(() => {
        this.$message.info("取消删除")
      })
    },
    deltype(index) {
      this.$confirm("您确定要删除吗", "确定删除", {type: 'error'}).then(() => {
        this.typeList.splice(index, 1)
        this.$message.success("成功删除")
      }).catch(() => {
        this.$message.info("取消删除")
      })
    },
    submit() {
      this.$confirm("即将保存", "确定保存", {type: 'success'}).then(() => {
        this.forfactor.topicid = this.topicid
        this.forfactor.factor = this.factor
        for (let i = 0; i < this.typeList.length; i++) {
          for (let j = 0; j < this.typeList[i].typeDetails.length; j++) {
            this.forfactor.type = this.typeList[i].type
            this.forfactor.sentence = this.typeList[i].typeDetails[j].sentence
            this.forfactor.point = this.typeList[i].typeDetails[j].point//封装数据发给后端
            this.$request.post('/factor/add', this.forfactor).then(res => {
              console.log(res)
            }).catch(error => {
              console.error(error)
            })
          }
        }
        this.$message.success("保存成功")
        this.factor = ''
        this.typeList = []//清空数组
      }).catch(() => {
        this.$message.info("取消保存")
      })

    }
  }
}
</script>

<style scoped></style>
