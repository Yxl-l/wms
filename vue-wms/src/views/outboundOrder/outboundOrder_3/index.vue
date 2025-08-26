<script setup>
import { ref } from 'vue'

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
    新发货
    
  

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