<template>
  <div style="width: 100%; height: 100%;
              background-color: #7fffd4">
      <el-calendar class="id">
        <!-- 这里使用的是 2.5 slot 语法，对于新项目请使用 2.6 slot 语法-->
        <template
            slot="dateCell"
            slot-scope="{date, data}">
          <p :class="data.isSelected ? 'is-selected' : ''">
            {{ data.day.split('-').slice(1).join('-') }} {{ data.isSelected ? '✔️' : '' }}
          </p>
        </template>
      </el-calendar>
  </div>
</template>

<script>
export default {
  data() {
  },
};

</script>

<!--<style>-->
<!--.fillet {-->
<!--  border-radius: 5px;-->
<!--}-->
<!--</style>-->
<style>
.is-selected {
  color: #1989FA;
}

.el-calendar__body {
  padding: 0 10px;
}
.id{
  background-color: aquamarine;
}
</style>