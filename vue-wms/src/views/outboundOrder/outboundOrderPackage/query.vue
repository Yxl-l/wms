<script setup>
import { onMounted, ref } from 'vue'

// 定义 emit
const emit = defineEmits(['search'])

// 定义搜索表单的响应式数据对象
const searchForm = ref({
           
    title: '',
    packageStatus: ''
    ,invoiceCode: '',
    packageCode: ''
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
    searchForm.value.packageStatus = ''      
    searchForm.value.title = ''
    searchForm.value.invoiceCode = ''
    searchForm.value.packageCode = ''
    handleRefreshZi()
}

// 暴露 searchForm 方法给父组件使用
defineExpose({ searchForm }) 

onMounted(() => {
 
})
</script>

<template>
  <div id="box">
    <el-form :inline="true" :model="searchForm" >
      <el-form-item label="商品名称"> 
        <el-input v-model="searchForm.title" placeholder="请输入商品名称" clearable />
      </el-form-item>
      <el-form-item label="发货单单号"> 
        <el-input v-model="searchForm.invoiceCode" placeholder="请输入发货单单号" clearable />
      </el-form-item>
      <el-form-item label="打包单单号"> 
        <el-input v-model="searchForm.packageCode" placeholder="请输入打包单单号" clearable />
      </el-form-item>
      <el-form-item label="状态" style="width: 200px;"> 
        <el-select v-model="searchForm.packageStatus" placeholder="请选择状态" clearable>
          <el-option label="待出库" value="1"></el-option>
          <el-option label="已出库" value="2"></el-option>
        </el-select>
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