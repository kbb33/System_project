<template>
  <div style="width: 100%;height: 100%;background-color:#7fffd4;">
    <div>
      <el-calendar class="id" v-model="currentDate">
        <template
            slot="dateCell"
            slot-scope="{date, data}">
          <p :class="data.isSelected ? 'is-selected' : ''">
            {{ data.day.split('-').slice(1).join('-') }} {{ data.isSelected ? '✔️' : '' }}
          </p>
        </template>
      </el-calendar>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      value: new Date()
    }
  },
  methods: {
  }
}
</script>

<style>
.is-selected {
  color: #1989FA;
},
.custom-content {
  text-align: center;
  height: 100%;
  /* 可以添加更多样式来自定义日历单元格的外观 */
}
</style>