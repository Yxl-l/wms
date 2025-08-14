<script setup>
import { ref } from 'vue'
import addGoods from './addGoods.vue';
import List  from './list.vue';

</script>

<template>
<div>
<router-link to="/addGoods">  <el-button type="success" plain class="bt1">添加商品</el-button></router-link>

    <List />



</div>
  
</template>

<style scoped>
.bt1{
    margin: 20px;
    

}
</style>
