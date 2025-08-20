<script setup>
import addCompany from './add.vue'
import listCompany from './list.vue'
import { ref } from 'vue'

const listRef = ref()

// 刷新列表的方法
const handleRefresh = () => {
  if (listRef.value) {
    listRef.value.handleRefresh()
  }
}
</script>

<template>
 <div>
    <el-icon><Van /></el-icon> 物流价格配置
    <!-- 添加 -->
    <div class="add"> 
      <addCompany @refresh="handleRefresh" /> 
    </div>

    <!-- 列表 -->
    <div class="list"> 
      <listCompany ref="listRef" /> 
    </div>
 </div>
</template>

<style scoped>
</style>