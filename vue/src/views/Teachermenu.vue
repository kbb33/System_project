<template>
  <div style="width:100%; height:100%;
              background-color: #333333">
    <el-container>
      <el-aside :width="asideWidth" style="min-height: 100vh;
                                           background-color: #77C4C7">
        <div style="font-weight: 700; height: 50px;
                    color: #333333;
                    display: flex; align-items: center; justify-content: center">
          <img src="@/assets/xiaohui.png" style="width: 50px">
        </div>
        <el-menu :collapse="isCollapse"
                 collapse-transition="true"
                 router style="border: none"
                 background-color="#5f9ea0"
                 active-text-color="#ffffff"
                 text-color="#333333"
                 :default-active="$route.path"
                 @open="handleOpen" @close="handleClose">
          <el-menu-item index="/home">
            <i class=" el-icon-house"></i>
            <span slot="title">系统首页</span>
          </el-menu-item>
          <el-submenu>
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>批改管理</span>
            </template>
            <el-menu-item index="/manage">题目管理</el-menu-item>
            <el-menu-item index="/add">添加题目</el-menu-item>
          </el-submenu>
          <el-menu-item index="/stat">
            <i class="el-icon-s-data"></i>
            <span slot="title">统计情况</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <!--    头部区域-->
        <el-header style="height: 50px;
                          background-color: #77C4C7">
          <i class="el-icon-menu" style="font-size: 30px; margin-bottom: 10px" @click="handleCollapse"></i>
        </el-header>
        <!--    主体区域    -->
        <el-main>
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Home",
  data(){
    return{
      isCollapse: false,
      asideWidth: '150px',
      value: new Date()
    }
  },
  methods:{
    handleCollapse(){
      this.isCollapse=!this.isCollapse
      this.asideWidth= this.isCollapse ? '64px':'150px'
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>

<style>
.el-menu--inline .el-menu-item{
  background-color: #719899 !important;
}/*下拉菜单背景色设置*/
.el-menu-item.is-active{
  background-color:#7fffd4 !important;
  border-radius: 0;
  /*  width: calc(100% - 4px);
    margin-left: 2px;*/
}/*菜单选择颜色变化*/
/*.el-menu-item.is-active i, .el-menu-item.is-active.tooltip{
  margin-left: -2px;
}*/
.el-submenu .el-menu-item {
  min-width: 140px;
}
.el-header{
  border-radius: 0 !important;
  padding: 10px 20px;
}
.id{
  background-color: #7fffd4;
}
.is-selected {
  color: #1989FA;
}
</style>