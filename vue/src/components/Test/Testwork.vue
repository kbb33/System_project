<template>
  <div>
    <div>
      <el-input style="width: 80%;margin: 10px 5px" v-model="type"></el-input>
      <el-button type="primary" style="width: 17%; margin-left: 5px" @click="sendtype(type)" z-index="2">创建类型
      </el-button>
    </div>
      <el-collapse>
        <el-collapse-item v-for="item in Qfactor" :key="item.type" :name="item.type" :title="item.type">
          <div>
            <div style="width: calc(100% - 20px);
                               margin: 0 10px;
                               background-color: white;">
              <el-input style="width: 68%" v-model="sentence" placeholder="请输入批语"></el-input>
              <el-input style="width: 15%" v-model="point" placeholder="分数"></el-input>
              <el-button type="primary" size="medium" style="margin-left: 10px" @click="submit(sentence,point)">添加批语
              </el-button>
            </div>
          </div>
          <ul>
            <li v-for="factor in qFactors" :key="factor.type">
              类型：{{ factor.type }}
              <ul>
                <li>句子：{{ factor.piyu.sentence }}</li>
                <li>点：{{ factor.piyu.point }}</li>
              </ul>
            </li>
          </ul>
        </el-collapse-item>
      </el-collapse>
    </div>
</template>
<script>

export default {
  name: "Apptype",
  data() {
    return {
      type:'',
      sentence:'',
      point:'',
      Qfactor:{
        "type":'',
        "piyu":{
          "sentence":'',
          "point":'',
        }
      }
    }
  },
  methods:{
    sendtype(type){
      this.Qfactor.type.push(type)
    },
    submit(sentence,point){
      this.Qfactor.piyu.sentence.push(sentence)
      this.Qfactor.piyu.point.push(point)
    }
  }
}
</script>

<style scoped>

</style>