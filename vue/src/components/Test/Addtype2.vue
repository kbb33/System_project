<template>
  <div>
    <input
        style="width: calc(100% - 10px); height: 50px; font-size: 25px; margin: 5px 5px; border-radius: 5px; background-color: #76a2db"
        v-model="factor"
    />
    <div>
      <el-input style="width: 80%; margin: 10px 5px" v-model="type"></el-input>
      <el-button type="primary" style="width: 17%; margin-left: 5px" @click="addType">创建类型</el-button>
    </div>
    <div
        style="width: calc(100% - 10px); height: 73vh; overflow-y: scroll; margin: 0px 5px 5px 5px; background-color: #51bbc4">
      <el-collapse>
        <el-collapse-item v-for="(item, index) in typeList" :key="index" :name="index" :title="item?.type">
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
              <el-table-column label="分值" prop="point"></el-table-column>

            </el-table>
          </div>
        </el-collapse-item>
      </el-collapse>
    </div>
  </div>
</template>
<script>
export default {
  name: 'Apptype2',
  data() {
    return {
      // 类型
      forfactor:{
        factor:'',
        type:'',
        sentence:'',
        point:'',
      },
      type: '',
      // 类型数据结构
      typeList: []
    }
  },
  props: {
    factor: String
  },
  mounted() {
    getData()
  },
  methods: {
    getData() {
      // 首次进入获取后端数据 赋值给  typeList
    },
    // 添加类型
    addType() {
      // 为空则不增加
      if (!this.type) return
      // 调用后端接口增加类型
      this.typeList.push({type: this.type, sentence: '', point: '', typeDetails: []})
      this.type = ''
      // 重新调用后端接口，看是否添加上
      getData()
    },
    // 添加类型详细数据
    addDetails(item, index) {
      // 数据或者批语为空则不增加
      if (!item.sentence || !item.point) return
      // 调用后端接口增加类型得详细数据
      this.typeList[index].typeDetails.push({sentence: item.sentence, point: item.point})
      console.log(this.factor,this.typeList[index].type,item.sentence,item.point)
      this.forfactor.factor=this.factor
      this.forfactor.type=this.typeList[index].type
      this.forfactor.sentence=item.sentence
      this.forfactor.point=item.point
      this.$request.post('/factor/add',this.forfactor).then(res=>{
        console.log(res.data)
      }).catch(error=>{
        console.error(error)
      })
      item.sentence = ''
      item.point = ''
      // 重新调用后端接口，看是否添加上
      getData()
    },
  }
}
</script>

<style scoped></style>
