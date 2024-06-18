<template>
  <el-upload
      class="uploader"
      action="http://localhost:9090/file/upload"
      :show-file-list="false"
      :on-success="handlePictureSuccess"
      :before-upload="beforePictureUpload">
    <img v-if="imageUrl" :src="imageUrl" class="picture">
    <i v-else class="el-icon-plus picture-uploader-icon"></i>
  </el-upload>
</template>

<script>
export default {
  name: "upload",
  data() {
    return {
      imageUrl: ''
    };
  },
  methods: {
    handlePictureSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
      this.$emit('sendurl',this.imageUrl)
      console.log(this.imageUrl)
    },
    beforePictureUpload(file) {//判断上传的是否是图片
      const isJPG = file.type.startsWith('image/');

      if (!isJPG) {
        this.$message.error('上传只能是 图片 格式!');
      }
      return isJPG ;
    }
  }
}

</script>

<style scoped>
.uploader{
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.picture-uploader-icon {
  font-size: 180px;
  color: #797373;
  margin-top: 45%;
}
.picture{
  width: calc(100% - 6px);
  height: calc(50vh - 6px);
  object-fit: contain;
  margin: 3px;
}
</style>