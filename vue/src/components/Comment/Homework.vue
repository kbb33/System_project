<template>
  <div style="position: relative;display: flex">
    <div style="position: absolute;top:10px;left: 5px;
                width: calc(100% - 10px);height: calc(57vh - 5px);
                background-color: white;
                justify-content: center;align-content: center">
      <img :src="imgList[imgIndex]" class="picture">
      <span>{{ nameList[imgIndex] }}</span>
    </div>
    <div style="position: absolute;top: 5px;left: 92.5%;width: 14%">
      <!--      批量上传文件-->
      <el-upload
          action="http://localhost:9090/file/upload"
          :show-file-list="false"
          :on-success="handleFileupload"
          @click="uploadhomework"
          multiple>
        <el-button title="上传" type="primary" size="mini" style="margin:2%;font-size: 20px"
                   icon="el-icon-folder-add"></el-button>
      </el-upload>
      <el-button title="题目图片" type="primary" size="mini" style="margin:2%;font-size: 20px"
                 icon="el-icon-picture-outline"></el-button>
      <el-button title="上一张" type="primary" size="mini" style="margin:2%;font-size: 20px" icon="el-icon-back"
                 @click="last"></el-button>
      <el-button title="下一张" type="primary" size="mini" style="margin:2%;font-size: 20px" icon="el-icon-right"
                 @click="next"></el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "Homework",
  data() {
    return {
      imgList: [],
      nameList: [],
      imgIndex: 0
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {

    },
    handleFileupload(response, file, fileList) {
      this.nameList = fileList.map(item => item?.name)
      this.imgList = fileList.map(item => item.response?.data)
      this.$emit("send", this.nameList, this.imgList, this.imgIndex)
    },
    uploadhomework() {
      for (let i = 0; i < this.nameList.length; i++) {
        this.$request.post("/score/insertStu", '', {
          params: {
            Stuname: this.nameList[i],
            homework: this.imgList[i]
          }
        }).then(res => {
          if (res.code === 200) {
            this.$message.success("上传成功")
          } else {
            this.$message.error(res.msg)
          }
        })
      }
    },
    next() {
      let temp = this.nameList.length - 1
      if (this.imgIndex < temp) {
        this.imgIndex = this.imgIndex + 1
        this.$emit("sendindex", this.imgIndex)
      }
    },
    last() {
      if (this.imgIndex !== 0) {
        this.imgIndex = this.imgIndex - 1
        this.$emit("sendindex", this.imgIndex)
      }
    }
  }
}
</script>


<style scoped>
.picture {
  width: calc(100% - 6px);
  height: calc(50vh - 6px);
  object-fit: contain;
  margin: 3px;
}
</style>