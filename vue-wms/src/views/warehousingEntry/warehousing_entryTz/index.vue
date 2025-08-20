<script setup>
import { ref } from 'vue'
import addWarehouse from './add.vue'
import listWarehouse from './list.vue'

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
    入库管理
    
    <!-- 添加 -->
    <div class="add"> 
      <addWarehouse @refresh="handleRefresh" /> 
    </div>

    <!-- 列表 -->
    <div class="list"> 
      <listWarehouse ref="listRef" /> 
    </div>
 </div>
</template>

<style scoped>
.add{
    margin: 10px;
}
</style>