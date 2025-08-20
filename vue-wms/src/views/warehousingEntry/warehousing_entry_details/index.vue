<script setup>
import { ref } from 'vue'
import AddWarehousingEntryDetails from './add.vue'
import ListWarehousingEntryDetails from './list.vue'

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
    入库明细管理
    
    <!-- 添加 -->
    <div class="add"> 
      <AddWarehousingEntryDetails @refresh="handleRefresh" /> 
    </div>

    <!-- 列表 -->
    <div class="list"> 
      <ListWarehousingEntryDetails ref="listRef" /> 
    </div>
 </div>
</template>

<style scoped>
.add{
    margin: 10px;
}
</style>