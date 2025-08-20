<script setup>
import { ref, onMounted } from "vue";
import { ElMessage } from "element-plus";
import { updateApi, getDetailApi } from "@/api/warehousingEntryDetails";
import { getAllApi as getSupplierAllApi } from "@/api/supplier";
import { getAllApi as getWarehousingEntryAllApi } from "@/api/warehousingEntry";

// 定义响应式数据
const dialogFormVisible = ref(false); //控制对话框的显示与隐藏
const title = ref("修改入库明细");
const ruleFormRef = ref(null);

// 入库明细表单数据
const warehousingEntryDetailsForm = ref({
  id: '',
  skuId: "",
  count: "",
  supplierId: "",
  price: "",
  warehousingEntryId: "",
  realArrivalTime: "",
  predictedArrivalTime: "",
  unloadEmpId: "",
  unloadTime: "",
  status:""
});

// 获取所有供应商数据
const supplierList = ref([]);
const getSupplierAll = async () => {
  try {
    const res = await getSupplierAllApi();
    supplierList.value = res.data || [];
  } catch (error) {
    ElMessage.error("获取供应商数据失败");
  }
};

// 获取所有入库单数据
const warehousingEntryList = ref([]);
const getWarehousingEntryAll = async () => {
  try {
    const res = await getWarehousingEntryAllApi();
    warehousingEntryList.value = res.data || [];
  } catch (error) {
    ElMessage.error("获取入库单数据失败");
  }
};

// 表单验证规则
const rules = ref({
  skuId: [
    { required: true, message: "请输入规格ID", trigger: "blur" },
    { pattern: /^\d+$/, message: "请输入数字", trigger: "blur" }
  ],
  count: [
    { required: true, message: "请输入数量", trigger: "blur" },
    { pattern: /^\d+$/, message: "请输入数字", trigger: "blur" }
  ],
  supplierId: [
    { required: true, message: "请选择供应商", trigger: "change" }
  ],
  price: [
    { required: true, message: "请输入单价", trigger: "blur" },
    { pattern: /^\d+$/, message: "请输入数字", trigger: "blur" }
  ],
  warehousingEntryId: [
    { required: true, message: "请选择入库单", trigger: "change" }
  ],
  predictedArrivalTime: [
    { required: true, message: "请选择预测到达时间", trigger: "change" }
  ],
});

// 打开修改入库明细对话框并回显数据
const editWarehousingEntryDetails = async (row) => {
  dialogFormVisible.value = true;
  title.value = "修改入库明细";
  
  // 获取详细信息并回显
  try {
    const res = await getDetailApi(row.id)
    if (res.data) {
      warehousingEntryDetailsForm.value = { ...res.data }
      
      // 时间字段处理，将时间字符串转换为Date对象
      if (warehousingEntryDetailsForm.value.realArrivalTime) {
        warehousingEntryDetailsForm.value.realArrivalTime = new Date(warehousingEntryDetailsForm.value.realArrivalTime);
      }
      
      if (warehousingEntryDetailsForm.value.predictedArrivalTime) {
        warehousingEntryDetailsForm.value.predictedArrivalTime = new Date(warehousingEntryDetailsForm.value.predictedArrivalTime);
      }
      
      if (warehousingEntryDetailsForm.value.unloadTime) {
        warehousingEntryDetailsForm.value.unloadTime = new Date(warehousingEntryDetailsForm.value.unloadTime);
      }
    }
  } catch (error) {
    ElMessage.error('获取入库明细信息失败')
  }
};

// 保存修改信息
const save = async () => {
  if (!ruleFormRef.value) return
  
  try {
    await ruleFormRef.value.validate();
    
    // 创建提交数据对象
    const submitData = { ...warehousingEntryDetailsForm.value };
    
    // 时间格式处理，确保Date对象转换为ISO字符串
    if (submitData.realArrivalTime && submitData.realArrivalTime instanceof Date) {
      submitData.realArrivalTime = submitData.realArrivalTime.toISOString();
    }
    
    if (submitData.predictedArrivalTime && submitData.predictedArrivalTime instanceof Date) {
      submitData.predictedArrivalTime = submitData.predictedArrivalTime.toISOString();
    }
    
    if (submitData.unloadTime && submitData.unloadTime instanceof Date) {
      submitData.unloadTime = submitData.unloadTime.toISOString();
    }
    
    const res = await updateApi(submitData)
    if (res.code == 1) {
      ElMessage.success('入库明细修改成功')
      dialogFormVisible.value = false
      // 通知父组件刷新列表
      emit('refresh')
    } else {
      ElMessage.error('修改入库明细失败')
    }
  } catch (error) {
    // 验证失败或网络错误
    if (error instanceof Error) {
      ElMessage.error('请检查表单填写是否正确')
    } else {
      ElMessage.error('修改入库明细异常')
    }
  }
};

// 定义emit事件，用于通知父组件刷新数据
const emit = defineEmits(['refresh'])

// 暴露方法给父组件使用
defineExpose({
  editWarehousingEntryDetails
})

// 组件挂载时获取数据
onMounted(() => {
  getSupplierAll();
  getWarehousingEntryAll();
});
</script>

<template>
  <el-dialog style="width: 700px" v-model="dialogFormVisible" :title="title">
    <el-form
      :model="warehousingEntryDetailsForm"
      :rules="rules"
      ref="ruleFormRef"
      label-width="auto"
    >
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="规格ID" prop="skuId">
            <el-input v-model.number="warehousingEntryDetailsForm.skuId" placeholder="请输入规格ID" />
          </el-form-item>
        </el-col>
        
        <el-col :span="12">
          <el-form-item label="数量" prop="count">
            <el-input v-model.number="warehousingEntryDetailsForm.count" placeholder="请输入数量" />
          </el-form-item>
        </el-col>
      </el-row>
      
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="供应商" prop="supplierId">
            <el-select 
              v-model="warehousingEntryDetailsForm.supplierId" 
              placeholder="请选择供应商"
              style="width: 100%"
            >
              <el-option
                v-for="item in supplierList"
                :key="item.id"
                :label="item.supplierName"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
        
        <el-col :span="12">
          <el-form-item label="单价" prop="price">
            <el-input v-model.number="warehousingEntryDetailsForm.price" placeholder="请输入单价" />
          </el-form-item>
        </el-col>
      </el-row>
      
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="入库单" prop="warehousingEntryId">
            <el-select 
              v-model="warehousingEntryDetailsForm.warehousingEntryId" 
              placeholder="请选择入库单"
              style="width: 100%"
          
            >
              <el-option
                v-for="item in warehousingEntryList"
                :key="item.id"
                :label="item.batchNumber"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
        
        <el-col :span="12">
          <el-form-item label="卸货员工ID" prop="unloadEmpId">
            <el-input v-model.number="warehousingEntryDetailsForm.unloadEmpId" placeholder="请输入卸货员工ID" />
          </el-form-item>
        </el-col>
      </el-row>
      
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="预测到达时间" prop="predictedArrivalTime">
            <el-date-picker
              v-model="warehousingEntryDetailsForm.predictedArrivalTime"
              type="datetime"
              placeholder="请选择预测到达时间"
              style="width: 100%"
            />
          </el-form-item>
          
        </el-col>
        
        <el-col :span="12">
          <el-form-item label="实际到达时间" prop="realArrivalTime">
            <el-date-picker
              v-model="warehousingEntryDetailsForm.realArrivalTime"
              type="datetime"
              placeholder="请选择实际到达时间"
              style="width: 100%"
            />
          </el-form-item>
        </el-col>
      </el-row>
      
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="卸货时间" prop="unloadTime">
            <el-date-picker
              v-model="warehousingEntryDetailsForm.unloadTime"
              type="datetime"
              placeholder="请选择卸货时间"
              style="width: 100%"
            />
            
          </el-form-item>
          
        </el-col>
        <el-form-item label="状态"> 
        <el-select v-model="warehousingEntryDetailsForm.status" placeholder="选择状态默认1" style="width: 150px;" clearable>
          <el-option label="未到货" :value="1" />
          <el-option label="已到货" :value="2" />
          <el-option label="已卸货" :value="3" />
          <el-option label="拣货中" :value="4" />
          <el-option label="已上架" :value="5" />
        </el-select>
      </el-form-item>
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