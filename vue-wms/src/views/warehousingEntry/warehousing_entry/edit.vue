
<script setup>
import { ref } from "vue";
import { ElMessage } from "element-plus";
import { updateApi, getDetailApi } from "@/api/warehousingEntry";
import { getWarehouseAllApi } from "@/api/warehouse";

// 定义响应式数据
const dialogFormVisible = ref(false); //控制对话框的显示与隐藏
const title = ref("修改入库单");
const ruleFormRef = ref(null);

// 入库单表单数据
const warehousingEntryForm = ref({
  id: '',
  consignorId: "",
  batchNumber: "",
  totalCount: "",
  predictedArrivalTime: "",
  warehouseId: "",
  damageCount: 0,
  surpassCount: 0,
  lackCount: 0,
});

// 获取所有仓库数据
const warehouseList = ref([]);
const getWarehouseAll = async () => {
  try {
    const res = await getWarehouseAllApi();
    warehouseList.value = res.data || [];
  } catch (error) {
    ElMessage.error("获取仓库数据失败");
  }
};

// 表单验证规则
const rules = ref({
  consignorId: [
    { required: true, message: "请输入货主ID", trigger: "blur" },
    { pattern: /^\d+$/, message: "请输入数字", trigger: "blur" }
  ],
  batchNumber: [
    { required: true, message: "请输入批次号", trigger: "blur" },
    { min: 1, max: 50, message: "长度在 1 到 50 个字符", trigger: "blur" }
  ],
  totalCount: [
    { required: true, message: "请输入批次商品数量", trigger: "blur" },
    { pattern: /^\d+$/, message: "请输入数字", trigger: "blur" }
  ],
  predictedArrivalTime: [
    { required: true, message: "请选择预测到达时间", trigger: "change" }
  ],
  warehouseId: [
    { required: true, message: "请选择仓库", trigger: "change" }
  ],
});

// 打开修改入库单对话框并回显数据
const editWarehousingEntry = async (row) => {
  dialogFormVisible.value = true;
  title.value = "修改入库单";
  
  // 获取详细信息并回显
  try {
    const res = await getDetailApi(row.id)
    if (res.data) {
      warehousingEntryForm.value = { ...res.data }
      // 如果有时间字段，确保它是正确的格式
      if (warehousingEntryForm.value.predictedArrivalTime) {
        warehousingEntryForm.value.predictedArrivalTime = new Date(warehousingEntryForm.value.predictedArrivalTime);
      }
    }
  } catch (error) {
    ElMessage.error('获取入库单信息失败')
  }
};

// 保存修改信息
const save = async () => {
  if (!ruleFormRef.value) return
  
  try {
    await ruleFormRef.value.validate();
    
    // 创建提交数据对象
    const submitData = { ...warehousingEntryForm.value };
    
    // 确保时间格式正确
    if (submitData.predictedArrivalTime) {
      // 如果是日期对象，转换为ISO字符串；如果是字符串，保持原样
      if (submitData.predictedArrivalTime instanceof Date) {
        submitData.predictedArrivalTime = submitData.predictedArrivalTime.toISOString();
      }
    }
    
    const res = await updateApi(submitData)
    if (res.code == 1) {
      ElMessage.success('入库单修改成功')
      dialogFormVisible.value = false
      // 通知父组件刷新列表
      emit('refresh')
    } else {
      ElMessage.error('修改入库单失败')
    }
  } catch (error) {
    // 验证失败或网络错误
    if (error instanceof Error) {
      ElMessage.error('请检查表单填写是否正确')
    } else {
      ElMessage.error('修改入库单异常')
    }
  }
};

// 定义emit事件，用于通知父组件刷新数据
const emit = defineEmits(['refresh'])

// 暴露方法给父组件使用
defineExpose({
  editWarehousingEntry
})

// 组件挂载时获取数据
getWarehouseAll();
</script>

<template>
  <el-dialog style="width: 700px" v-model="dialogFormVisible" :title="title">
    <el-form
      :model="warehousingEntryForm"
      :rules="rules"
      ref="ruleFormRef"
      label-width="auto"
    >
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="货主ID" prop="consignorId">
            <el-input v-model.number="warehousingEntryForm.consignorId" placeholder="请输入货主ID" />
          </el-form-item>
        </el-col>
        
        <el-col :span="12">
          <el-form-item label="批次号" prop="batchNumber">
            <el-input v-model="warehousingEntryForm.batchNumber" placeholder="请输入批次号" />
          </el-form-item>
        </el-col>
      </el-row>
      
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="商品数量" prop="totalCount">
            <el-input v-model.number="warehousingEntryForm.totalCount" placeholder="请输入商品数量" />
          </el-form-item>
        </el-col>
        
        <el-col :span="12">
          <el-form-item label="预测到达时间" prop="predictedArrivalTime">
            <el-date-picker
              v-model="warehousingEntryForm.predictedArrivalTime"
              type="datetime"
              placeholder="请选择预测到达时间"
              style="width: 100%"
            />
          </el-form-item>
        </el-col>
      </el-row>
      
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="仓库" prop="warehouseId">
            <el-select 
              v-model="warehousingEntryForm.warehouseId" 
              placeholder="请选择仓库"
              style="width: 100%"
              disabled
            >
              <el-option
                v-for="item in warehouseList"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
        
        <el-col :span="12">
          <el-form-item label="破损数量" prop="damageCount">
            <el-input v-model.number="warehousingEntryForm.damageCount" placeholder="请输入破损数量" />
          </el-form-item>
        </el-col>
      </el-row>
      
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="超出数量" prop="surpassCount">
            <el-input v-model.number="warehousingEntryForm.surpassCount" placeholder="请输入超出数量" />
          </el-form-item>
        </el-col>
        
        <el-col :span="12">
          <el-form-item label="缺少数量" prop="lackCount">
            <el-input v-model.number="warehousingEntryForm.lackCount" placeholder="请输入缺少数量" />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="save">提交</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<style scoped>
.dialog-footer {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}
</style>