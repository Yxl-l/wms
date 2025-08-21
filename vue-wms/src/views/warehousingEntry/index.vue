<script  setup>
import { ref } from 'vue'
import warehousing_entry from './warehousing_entry/index.vue';
import warehousing_entry_details from './warehousing_entry_details/index.vue';
import warehousing_entry_details_dfj from './warehousing_entry_details_dfj/index.vue';
import warehousing_entry_details_jd from './warehousing_entry_details_jd/index.vue';
import warehousing_entry_details_sort from './warehousing_entry_details_sort/index.vue';
import warehousing_entry_up from './warehousing_entry_up/index.vue';
import warehousing_entry_tz from './warehousing_entryTz/index.vue';

</script>
<template>
  <div class="box">
    <el-tabs type="border-card" class="demo-tabs">
      <el-tab-pane>
        <template #label>
          <!-- 添加 custom-tabs-label 类 -->
          <span class="custom-tabs-label">
            <el-icon><CreditCard /></el-icon>
            <span>入库通知</span>
          </span>
        </template>
        <warehousing_entry_tz/>
      </el-tab-pane>
      <el-tab-pane>
        <template #label>
          <!-- 添加 custom-tabs-label 类 -->
          <span class="custom-tabs-label">
           <el-icon><Van /></el-icon>
            <span>入库单</span>
          </span>
        </template>
        <warehousing_entry/>
      </el-tab-pane>
      <el-tab-pane>
        <template #label>
          <!-- 添加 custom-tabs-label 类 -->
          <span class="custom-tabs-label">
            <el-icon><CreditCard /></el-icon>
            <span>入库明细</span>
          </span>
        </template>
        <warehousing_entry_details/>
      </el-tab-pane>
      <el-tab-pane>
        <template #label>
          <!-- 添加 custom-tabs-label 类 -->
          <span class="custom-tabs-label">
            <el-icon><CreditCard /></el-icon>
            <span>待分拣</span>
          </span>
        </template>
        <warehousing_entry_details_dfj/>
      </el-tab-pane>
      <el-tab-pane>
        <template #label>
          <!-- 添加 custom-tabs-label 类 -->
          <span class="custom-tabs-label">
            <el-icon><CreditCard /></el-icon>
            <span> 分拣货物进度</span>
          </span>
        </template>
        <warehousing_entry_details_jd/>
      </el-tab-pane>
       <el-tab-pane>
        <template #label>
          <!-- 添加 custom-tabs-label 类 -->
          <span class="custom-tabs-label">
            <el-icon><CreditCard /></el-icon>
            <span> 分拣货物明细</span>
          </span>
        </template>
        <warehousing_entry_details_sort/>
      </el-tab-pane>
      <el-tab-pane>
        <template #label>
          <!-- 添加 custom-tabs-label 类 -->
          <span class="custom-tabs-label">
            <el-icon><CreditCard /></el-icon>
            <span>入库上架</span>
          </span>
        </template>
        <warehousing_entry_up/>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>



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
  margin-left: 30px;
  font-size: 30px;
}

/* 调整标签高度以适应更大的图标 */
.demo-tabs.el-tabs--border-card > .el-tabs__header {
  height: auto !important;
}

.demo-tabs.el-tabs--border-card .el-tabs__item {
  height: 60px !important;
  line-height: 60px !important;
   margin-right: 10px !important;
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
