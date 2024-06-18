<template xmlns="">
  <div style="width: 100%; height: 100vh;
              background-color: #333333;
              display: flex">
    <div style="width:60%">
      <div style=" width: calc(100% - 5px);height: calc(60% - 5px);
                   margin-left: 5px;
                   margin-top: 5px;
                   margin-bottom: 5px;
                   border-radius: 5px;
                   background-color: #77C4C7">
        <Homework @send="getList" @sendindex="getindex"></Homework>
      </div>
      <div style="width: calc(100% - 5px);height: calc(40vh - 10px);
                  margin-bottom: 5px;
                  margin-left: 5px;
                  border-radius: 5px;
                  background-color: #9FCCCF">
        <div style="position: relative;
              width: 100%;height: 100%">
          <div style="position: absolute;top: 10px;left: 10px;
                width:calc(100% - 20px);height: calc(82% - 10px);
                border-radius: 3px;
                background-color:white;
                overflow-y: scroll">
            <div v-for="(item,index) in piyu">
              {{ item?.word}}
              <el-button type="danger" size="mini" icon="el-icon-close" @click="delword(index,item.id)"></el-button>
            </div>
          </div>
          <div style="position: absolute;top: 82%;left: 1.5%;
                width:10%">
            <router-link :to="{path:'/home'}">
              <i class="el-icon-s-home" style="padding-top:15px; font-size: 20px;"></i>
              <span style="font-size: 20px">首页</span>
            </router-link>
          </div>
          <div style="position: absolute;top: 85%;left: 76%">
            <el-button size="small" type="primary" @click="onecopy">一键复制</el-button>
          </div>
          <el-popover
              placement="top"
              width="800"
              trigger="click">
            <div style="width: 100%;height: 50vh;background-color: #51cba7;">
              <div style="height: 5vh">批语汇总：</div>
              <div v-for="(item,index) in piyu">
                {{ item?.word}}
              </div>
              <div style="margin-top: 10px">
              <span>分数：{{pointList[pointList.length-1].point}}(满分一百)</span></div>
            </div>
              <el-button style="position: absolute;top: 85%;left: 88%"
                         slot="reference" size="small" type="primary" @click="getResult">一键导出</el-button>
          </el-popover>
        </div>
      </div>
    </div>
    <!--    右边显示批语区域-->
    <div style="width:calc(40% - 10px);height: calc(100% - 10px);
                 margin: 5px ;border-radius: 5px;
                 background-color: #88CCCF">
      <SelectSentence :id="topicid" @sendid="addList"></SelectSentence>
    </div>
  </div>
</template>

<script>
import SelectSentence from "@/components/Comment/SelectSentence.vue";
import Homework from "@/components/Comment/Homework.vue";


export default {
  components: {SelectSentence, Homework},
  data() {
    return {
      visible: true,
      topicid: 0,
      piyuList: [],
      piyu: [],
      stuList: [],
      imgurls: [],
      index: 0,
      Stuscore:100,
      Stupiyu:'',
      pointList:[{point:0}],
      factorList: [],
      student: {
        Stuname: '',
        homework: '',
        topicid: 0,
        Piyu: '',
      }
    }
  },
  created() {
    this.topicid = this.$route.query.id
    this.load()
  },
  watch: {
    index() {
      this.$confirm("你确定该改变吗，发生变化会自动存储当前批改", "确认变化", {type: "info"}).then(response => {
        this.student.topicid = this.topicid
        this.student.Stuname = this.stuList[this.index]
        this.student.homework = this.imgurls[this.index]
        this.piyuList = this.quchong(this.piyuList)//先去重一遍
        this.student.Piyu = this.piyuList.join(',')
        console.log(this.student)
        this.$request.post('/score/insertStu', this.student).then(res => {
        })
      }).catch(() => {
        this.$message.info("取消更改")
      })
      this.piyuList = []
    },
    piyuList() {
      if (this.piyuList.length > 0) {
        let i = this.piyuList.length - 1
        this.$request.get(`/factor/getWord/${this.piyuList[i]}`).then(res => {
          let word=res.data.factor+"[ "+res.data.type+"："+res.data.sentence+" -"+res.data.point+" ]"
          this.piyu.push({id:res.data.id,word:word})
        })
      } else {
        this.$request.get(`/factor/getWord/${this.piyuList[i]}`).then(res => {
          let word=res.data.factor+"[ "+res.data.type+"："+res.data.sentence+" -"+res.data.point+" ]"
          this.piyu.push({id:res.data.id,word:word})
        })
      }
    }
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
    },//JSON数组去重方法
    load() {
      this.$request.get('/factor/getFactor', {params: {topicid: this.topicid}}).then(res => {
        this.factorList = this.quchong(res.data)
      })
    },
    addList(id) {
      this.piyuList.push(id)
    },
    getList(nameList, imgList, imgIndex) {
      this.stuList = nameList
      this.imgurls = imgList
      this.index = imgIndex
    },
    getindex(index) {
      this.index = index
    },
    getResult() {
      this.student.topic = this.topic
      this.$request.post('/score/insertStu', this.student).then(res => {
        console.log(res)
      })
      let temp=100
      for(let i=0;i<this.piyu.length;i++){
        this.Stupiyu=this.Stupiyu+this.piyu[i].word+"\n"
        this.$request.get(`/factor/getWord/${this.piyuList[i]}`).then(res => {
          temp=temp-res.data.point
          this.pointList.push({point:temp})
        })
      }
    },
    delword(index,id){
      this.piyu=this.piyu.splice(index,1)//这里删不尽，应该与选择高亮问题有关
      this.piyuList=this.piyuList.splice(index,1)
    },
    onecopy(){//一键复制的方法
      let text=''
      for(let i=0;i<this.piyu.length;i++){
        text=text+this.piyu[i].word+"\n"
      }
      navigator.clipboard.writeText(text).then(()=>{
        this.$message.success("复制成功")
      })
    }
  }
};

</script>

<style>

</style>