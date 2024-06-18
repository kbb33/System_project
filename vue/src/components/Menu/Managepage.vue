<template>
  <div style="position:relative;
              width: 100%;height: 100%;
              background-color:#7fffd4;
              overflow-y: scroll">
    <el-card style="width: calc(100% - 20px);height: calc(10vh - 20px);margin:10px;">
      <el-input placeholder="查找题目" style="width: 40%;margin: 3px"></el-input>
      <el-button type="primary">Search</el-button>
      <el-select v-model="value" style="margin-left: 310px" placeholder="请选择章节" @change="selectTopic(value)">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.label"
            width="10%"
            style="font-size: 30px">
        </el-option>
      </el-select>
    </el-card>
    <div style=" position:absolute;top:10vh;left: 10px;
                 width: calc(100% - 20px);
                 overflow: auto;
                 border-radius: 10px;
                 ">
      <el-card>
        <el-table :data="questions">
          <el-table-column label="序号" type="index" width="50%"></el-table-column>
          <el-table-column label="标题" prop="topic" width="750%"></el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button
                  size="mini"
                  type="warning" plain
                  @click="commet(scope.$index, scope.row)">进入批改
              </el-button>
              <el-button
                  size="mini"
                  type="success" plain
                  @click="handleEdit(scope.$index, scope.row)">编辑
              </el-button>
              <el-button
                  size="mini"
                  type="danger"
                  @click="handleDelete(scope.$index, scope.row)">删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </div>
  </div>
</template>

<script>

export default {
  name: "Managepage",
  data() {
    return {
      options: [],
      questions: []
    }
  },
  created() {
    this.load()
  },
  mounted() {
    this.$request.get('/question/selectChapter').then(res => {
      for (let i = 0; i < res.data.length; i++) {
        this.options.push({value: '选项' + i, label: res.data[i]})
      }
    })
  },
  methods: {
    load(chapter) {
      this.$request.get(`/question/selectTopic/${chapter}`).then(res => {
        this.questions = res.data
      })
    },
    selectTopic(value) {
      if (!value) return
      this.$request.get(`/question/selectTopic/${value}`).then(res => {
        this.questions = res.data
      })
    },
    commet(index, row){
      this.$router.push({path: '/comment', query: {id: row.id}})
    },
    handleEdit(index, row) {
      this.$router.push({path: '/edit', query: {id: row.id}})
    },
    handleDelete(index, row) {
      this.$confirm("您确认要删除吗", "确认删除", {type: "warning"}).then(response => {
        this.$request.delete(`/question/delete/${row.id}`).then(res => {
          if (res.code === '200') {
            this.$message.success("操作成功")
          } else {
            this.$message.error(res.msg)
          }
          this.load(row.chapter)
        })
      }).catch(() => this.$message({
        type: 'info',
        message: '已取消删除'
      }))
    }
  }
}
</script>

<style>

</style>