<template>
  <div style="position:relative;
                height:76%;margin:3px;
                border-radius:5px">
    <div style="position: absolute;top:5px;left:5px;
                  width:98%;height: 50vh;
                  border-radius: 10px;
                  background-color: white">
      <Upload @sendurl="Getpicture" class="center"></Upload>
    </div>
    <div style="position: absolute;top: 375px">
      <el-form :inline="true" ref="question" :model="question" :rules="rules" label-width="100px"
               hide-required-asterisk="true">
        <el-form-item label="题目标题" prop="topic">
          <el-input class="id1" v-model="question.topic" placeholder="请输入你的题目标题"></el-input>
        </el-form-item>
        <el-form-item label="题目章节" prop="chapter">
          <el-input class="id2" v-model="question.chapter" placeholder="请输入题目所属章节"></el-input>
        </el-form-item>
        <!--          <el-form-item style="margin-top: 15px;margin-left: -2px" label="添加要素" prop="factor">-->
        <!--            <el-input class="id3" v-model="question.factor" placeholder="输入题目要素"></el-input>-->
        <!--          </el-form-item>-->
        <el-form-item>
          <el-button style="width:100%;margin-left: 18px;margin-top: 1px;" type="primary" @click="create">添加</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>

</template>

<script>
import Upload from "/src/components/Addpage/Upload.vue";

export default {
  name: "Addtopic",
  components: {Upload},
  data() {
    return {
      question: {
        topic: '',
        chapter: '',
        picture: '',
      },
      rules: {
        topic: [
          {required: true, message: '请输入题目名称', trigger: 'blur'}
        ],
        chapter: [
          {required: true, message: '请输入题目章节  注意 请输入数字', trigger: 'blur'}
        ],
        // factor: [
        //   {required: true, message: '请输入元素保证不为空', trigger: 'blur'}
        // ]
      }
    }
  },
  methods: {
    create() {
      if (this.question.topic) {
        this.$notify({
          title: '添加成功',
          message: '开始编辑你的批语',
          type: 'success',
          position: 'bottom-left'
        });
        this.$request.post('/question/create', this.question).then(res => {
          this.$emit('send', res.data.id)//向Addpage父组件传factor参数,给添加批语使用
        })
      } else {
        this.$notify({
          title: '添加失败',
          message: '检查是否已经添加必要要素',
          type: "error",
          position: 'bottom-left'
        });
        console.log('error submit!!');
        return false;
      }
    },
    Getpicture(imageUrl) {
      this.question.picture = imageUrl;
      console.log('topic收到的', imageUrl)
    }
  }
}
</script>

<style>
.id1 {
  width: 320px;
}

.id2 {
  width: 203px;
}

.id3 {
  width: 320px;
}
</style>