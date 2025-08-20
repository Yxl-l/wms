<!-- 引入所需的功能模块 -->
<script setup>
// 从 vue 中引入需要的响应式和生命周期函数
import { onMounted, ref, computed } from 'vue'

// 定义 emit
const emit = defineEmits(['search'])

// 定义搜索表单的响应式数据对象
const searchForm = ref({
    companyName: '',           
})
const handleSearch = () => {
    // 触发父组件的 search 事件，并传递 searchForm 数据
    emit('search', searchForm.value)
}
const handleRefreshZi = () => {
    // 触发父组件的 search 事件
    emit('shuaX')
}




// 重置表单函数
const reset = () => {
    searchForm.value.companyName = ''      
    handleRefreshZi()
}




// 暴露 searchForm 方法给父组件使用
defineExpose({ searchForm }) 





</script>

<template>
  <div id="box">
    <el-form :inline="true" :model="searchForm" >
      <el-form-item label="物流公司名称"> 
        <el-input v-model="searchForm.companyName" placeholder="请输入物流公司名称" clearable />
      </el-form-item>
      
      <!-- 按钮区域 -->
      <el-form-item>
        <!-- 查询按钮，点击时调用 getPage 函数 -->
        <el-button type="primary" @click="handleSearch">查询</el-button>
        <!-- 重置按钮，点击时调用 reset 函数 -->
        <el-button @click="reset">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>