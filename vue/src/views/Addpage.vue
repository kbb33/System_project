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
                  width: 15%;height:30px;
                  margin-left: 45%;">
        <h2 style="margin: 0"> 编辑你的题目</h2>
      </div>
    </el-header>
    <el-main>
      <div style="width: 100%;height: 94vh;
                  background-color: #333333;
                  display: flex;">
        <div style="width: 40%;margin: 3px;
                    border-radius:5px;background-color: #88CCCF">
          <Addtopic @send="add"></Addtopic>
        </div>
        <div style="position:relative;
                    width: 60%;margin:3px 3px 3px 0;
                    border-radius:5px;
                    background-color: #9FCCCF"
             v-if="showPage">
          <Addtype :topicid="tempt"></Addtype>
        </div>
      </div>
    </el-main>
  </div>
</template>


<script>
import Addtype from "@/components/Addpage/Addtype.vue";
import Addtopic from "@/components/Addpage/Addtopic.vue";


export default {
  name: "Addpage",
  components: {Addtopic, Addtype},
  data() {
    return {
      temp: '',
      tempt: 0,
      showPage: false
    }
  },
  props: {
    type: String
  },
  methods: {
    add(id) {
      this.tempt = id
      this.showPage = true
    },
  }
}
</script>

<style>
.el-header {
  border-radius: 5px;
}

.el-card__body, .el-main {
  padding: 0;
}

.homelink {
  line-height: 40px;
}

/*这三个用来调节输入框的宽度*/
</style>