<script  setup>
import { ref } from 'vue'
import outboundOrder_fhd from './outboundOrder_1/index.vue';
import outboundOrder_yfh from './outboundOrder_2/index.vue';
import outboundOrder_3 from './outboundOrder_3/index.vue';
import outboundOrder_ck from './outboundOrder_ck/index.vue';
import outboundOrder_qs from './outboundOrder_qs/index.vue';
import outboundOrderDetails_4 from './outboundOrderDetails_4/index.vue';
import outboundOrderDetails_5 from './outboundOrderDetails_5/index.vue';
import outboundOrderPackage from './outboundOrderPackage/index.vue';
import outboundOrderWeights from './outboundOrderWeights/index.vue';


</script>
<template>
  <div class="box">
    <el-tabs type="border-card" class="demo-tabs">
      <el-tab-pane>
        <template #label>
          <!-- 添加 custom-tabs-label 类 -->
          <span class="custom-tabs-label">
            <el-icon><List /></el-icon>
            <span>入库单ᅟᅠᅟᅠ </span>
          </span>
        </template>
        <outboundOrder_fhd/>
      </el-tab-pane>
      <el-tab-pane>
        <template #label>
          <!-- 添加 custom-tabs-label 类 -->
          <span class="custom-tabs-label">
            <el-icon><DocumentAdd /></el-icon>
            <span>预发货ᅟᅠᅟᅠ </span>
          </span>
        </template>
        <outboundOrder_yfh/>
      </el-tab-pane>
       <el-tab-pane>
        <template #label>
          <!-- 添加 custom-tabs-label 类 -->
          <span class="custom-tabs-label">
            <el-icon><Checked /></el-icon>
            <span>新发货ᅟᅠᅟᅠ </span>
          </span>
        </template>
        <outboundOrder_3/>
      </el-tab-pane>
       <el-tab-pane>
        <template #label>
          <!-- 添加 custom-tabs-label 类 -->
          <span class="custom-tabs-label">
            <el-icon><Edit /></el-icon>
            <span>待检货ᅟᅠᅟᅠ </span>
          </span>
        </template>
        <outboundOrderDetails_4/>
      </el-tab-pane>
       <el-tab-pane>
        <template #label>
          <!-- 添加 custom-tabs-label 类 -->
          <span class="custom-tabs-label">
            <el-icon><Histogram /></el-icon>
            <span>已拣货ᅟᅠᅟᅠ </span>
          </span>
        </template>
        <outboundOrderDetails_5/>
      </el-tab-pane>
       <el-tab-pane>
        <template #label>
          <!-- 添加 custom-tabs-label 类 -->
          <span class="custom-tabs-label">
            <el-icon><Box /></el-icon>
            <span>打包ᅟᅠᅟᅠ </span>
          </span>
        </template>
        <outboundOrderPackage/>
      </el-tab-pane>
       <el-tab-pane>
        <template #label>
          <!-- 添加 custom-tabs-label 类 -->
          <span class="custom-tabs-label">
            <el-icon><Briefcase /></el-icon>
            <span>称重ᅟᅠᅟᅠ </span>
          </span>
        </template>
        <outboundOrder_yfh/>
      </el-tab-pane>
       <el-tab-pane>
        <template #label>
          <!-- 添加 custom-tabs-label 类 -->
          <span class="custom-tabs-label">
            <el-icon><Van /></el-icon>
            <span>出库ᅟᅠᅟᅠ </span>
          </span>
        </template>
        <outboundOrder_yfh/>
      </el-tab-pane>
       <el-tab-pane>
        <template #label>
          <!-- 添加 custom-tabs-label 类 -->
          <span class="custom-tabs-label">
            <el-icon><Promotion /></el-icon>
            <span>已签收ᅟᅠᅟᅠ </span>
          </span>
        </template>
        <outboundOrderWeights/>
      </el-tab-pane>
      
    </el-tabs>
  </div>
</template>



<style>
.demo-tabs {
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
  font-size: 30px;
  display: block;
  text-align: center;
  margin: 0 auto !important;
}

.demo-tabs .custom-tabs-label span {
  display: block;
  font-size: 16px;
  margin-top: 8px !important;
  text-align: center;
}

.demo-tabs .custom-tabs-label {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 100%;
}

.demo-tabs.el-tabs--border-card .el-tabs__item.is-active .custom-tabs-label span,
.demo-tabs.el-tabs--border-card .el-tabs__item:hover .custom-tabs-label span {
  color: #409eff;
}

.demo-tabs.el-tabs--border-card .el-tabs__item.is-active {
  border-bottom: 3px solid #409eff !important;
}

/* 调整标签高度以适应更大的图标 */
.demo-tabs.el-tabs--border-card > .el-tabs__header {
  height: auto !important;
}

.demo-tabs.el-tabs--border-card .el-tabs__item {
  height: 80px !important;
  line-height: normal !important;
  margin-right: 5px !important; /* 修改此处的间距 */
}

.el-tabs__nav-scroll {
    height: 80px;
}

.box {
  padding: 20px; 
  background-color: rgba(255, 255, 255, 0.6);
  border-radius: 20px;
  overflow: hidden;
}
</style>