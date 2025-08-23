<script setup>
import { ref } from 'vue'
import ListWarehousingEntryUp from './list.vue'

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
  入库上架表
    
    <!-- 列表 -->
    <div class="list"> 
      <ListWarehousingEntryUp ref="listRef" /> 
    </div>
 </div>
</template>

<style scoped>
.add{
    margin: 10px;
}
</style>