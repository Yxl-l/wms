<template>
    <div class="box">
  <el-tabs type="border-card" class="demo-tabs">
    <el-tab-pane>
      <template #label>
        <span class="custom-tabs-label">
          <el-icon><HomeFilled /></el-icon>
          <span >仓库设置</span>
        </span>
      </template>
      <tb_warehouse/>
    </el-tab-pane>
       <el-tab-pane>
      <template #label>
        <span class="custom-tabs-label">
          <el-icon><LocationInformation /></el-icon>
          <span>库区设置</span>
        </span>
      </template>
   <tb_warehouse_area/>
    </el-tab-pane>
       <el-tab-pane>
      <template #label>
        <span class="custom-tabs-label">
          <el-icon><MessageBox /></el-icon>
          <span>库位设置</span>
        </span>
      </template>
     <tb_warehouse_area_loca/>
    </el-tab-pane>
    
   

  </el-tabs>
  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import tb_warehouse from './tb_warehouse/index.vue';
import tb_warehouse_area from './tb_warehouse_area/index.vue';
import tb_warehouse_area_loca from './tb_warehouse_area_loca/index.vue';






</script>

<style>
.demo-tabs{
  opacity: 0.8;
   border-radius: 20px;
  overflow: hidden;
}
.demo-tabs > .el-tabs__content {
  padding: 32px;
  background-color: rgba(180, 224, 234, 0.6);
  font-size: 32px;
  font-weight: 600;
  
}

.demo-tabs .custom-tabs-label .el-icon {
  vertical-align: middle;
  font-size: 30px; /* 增大图标尺寸 */
}

.demo-tabs .custom-tabs-label span {
  vertical-align: middle;
  margin-left: 50px;
  font-size: 30px;
}

/* 调整标签高度以适应更大的图标 */
.demo-tabs.el-tabs--border-card > .el-tabs__header {
  height: auto !important;
}

.demo-tabs.el-tabs--border-card .el-tabs__item {
  height: 60px !important;
  line-height: 60px !important;
   margin-right: 30px !important;
}

.demo-tabs .custom-tabs-label { 
  display: flex;
  align-items: center;
  height: 60px;
}
.el-tabs__nav-scroll{
    height: 60px;
}

.box {
  padding: 20px; 
  background-color: rgba(255, 255, 255, 0.6);
  border-radius: 20px;
  overflow: hidden;
}
</style>
