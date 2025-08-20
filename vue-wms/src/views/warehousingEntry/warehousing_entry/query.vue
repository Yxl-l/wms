<script setup>
import { onMounted, ref } from 'vue'
import { getAllApi } from "@/api/warehousingEntry";

// 获取全部入库单
const warehousingEntryList = ref([]);
const getAllWarehousingEntry = async () => {
  try {
    const res = await getAllApi();
    warehousingEntryList.value = res.data || [];
  } catch (error) {
    console.error("获取入库单数据失败:", error);
  }
};

// 定义 emit
const emit = defineEmits(['search'])

// 定义搜索表单的响应式数据对象
const searchForm = ref({
    batchNumber: '',           
    status: '',
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
    searchForm.value.batchNumber = ''      
    searchForm.value.status = ''
    handleRefreshZi()
}

// 暴露 searchForm 方法给父组件使用
defineExpose({ searchForm }) 

onMounted(() => {
   getAllWarehousingEntry()
})
</script>

<template>
  <div id="box">
    <el-form :inline="true" :model="searchForm" >
      <el-form-item label="批次号"> 
        <el-input v-model="searchForm.batchNumber" placeholder="请输入批次号" clearable />
      </el-form-item>
      
      <el-form-item label="状态"> 
        <el-select v-model="searchForm.status" placeholder="请选择状态" style="width: 200px;" clearable>
          <el-option label="未确认" :value="1" />
          <el-option label="部分已确认" :value="2" />
          <el-option label="全部确认" :value="3" />
          <el-option label="拣货中" :value="4" />
          <el-option label="已上架" :value="5" />
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