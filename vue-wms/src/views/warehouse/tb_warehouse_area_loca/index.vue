<script setup>
import { ref } from 'vue'
import addWarehouse from './add.vue'
import listWarehouse from './list.vue'



</script>

<template>
 <div>
    库位管理 
    
<!-- 添加 -->
<div class="add"> <add-warehouse /> </div>

 <!-- 列表 -->
<div class="list"> <listWarehouse /> </div>


 
 </div>

</template>

<style scoped>
.add{
    margin: 10px;
}
</style>