<template>
  <div class="box">
    <el-tabs type="border-card" class="demo-tabs">
      <el-tab-pane>
        <template #label>
          <!-- 添加 custom-tabs-label 类 -->
          <span class="custom-tabs-label">
           <el-icon><Van /></el-icon>
            <span>物流公司</span>
          </span>
        </template>
        <tb_logistics_company/>
      </el-tab-pane>
      <el-tab-pane>
        <template #label>
          <!-- 添加 custom-tabs-label 类 -->
          <span class="custom-tabs-label">
            <el-icon><CreditCard /></el-icon>
            <span>物流价格</span>
          </span>
        </template>
        <tb_logistics_price/>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script  setup>
import { ref } from 'vue'
import tb_logistics_company from './tb_logistics_company/index.vue';
import tb_logistics_price from './tb_logistics_price/index.vue';







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
