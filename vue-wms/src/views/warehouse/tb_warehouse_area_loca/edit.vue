<script setup>
import { ref, onMounted } from "vue";
import { ElMessage } from "element-plus";
import {
  getWarehouseAllApi,
  getAreaByWarehouseIdApi,
  updateAreaLocaApi,
  getAreaLocaDetailApi
} from "@/api/warehouse";

// 定义响应式数据
const dialogFormVisible = ref(false); //控制对话框的显示与隐藏
const title = ref("修改库位");
const ruleFormRef = ref(null);

// 库位表单数据
const warehouseAreaLocaForm = ref({
  id: '',
  areaId: "",
  locaCode: "",
  locaLen: "",
  locaWidth: "",
  locaHeight: "",
  localVolume: "",
  localBearingCapacity: "",
  tunnelNumber: "",
  shelfNumber: "",
  layerNumber: "",
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

// 获取库区数据（根据仓库ID）
const areaList = ref([]);
const getAreaByWarehouseId = async (warehouseId) => {
  try {
    if (!warehouseId) {
      areaList.value = [];
      return;
    }
    const res = await getAreaByWarehouseIdApi(warehouseId);
    areaList.value = res.rows || [];
  } catch (error) {
    ElMessage.error("获取库区数据失败");
  }
};

// 表单验证规则
const rules = ref({
  areaId: [
    { required: true, message: "请选择所属库区", trigger: "change" }
  ],
  locaCode: [
    { required: true, message: "请输入库位编号", trigger: "blur" },
    { min: 1, max: 200, message: "长度在 1 到 200 个字符", trigger: "blur" }
  ],
  locaLen: [
    { required: true, message: "请输入库位长度", trigger: "blur" },
    { pattern: /^\d+$/, message: "请输入数字", trigger: "blur" }
  ],
  locaWidth: [
    { required: true, message: "请输入库位宽度", trigger: "blur" },
    { pattern: /^\d+$/, message: "请输入数字", trigger: "blur" }
  ],
  locaHeight: [
    { required: true, message: "请输入库位高度", trigger: "blur" },
    { pattern: /^\d+$/, message: "请输入数字", trigger: "blur" }
  ],
  localVolume: [
    { required: true, message: "请输入库位容积", trigger: "blur" },
    { pattern: /^\d+$/, message: "请输入数字", trigger: "blur" }
  ],
  localBearingCapacity: [
    { required: true, message: "请输入库位承重", trigger: "blur" },
    { pattern: /^\d+$/, message: "请输入数字", trigger: "blur" }
  ],
  tunnelNumber: [
    { required: true, message: "请输入巷道号", trigger: "blur" },
    { min: 1, max: 30, message: "长度在 1 到 30 个字符", trigger: "blur" }
  ],
  shelfNumber: [
    { required: true, message: "请输入货架号", trigger: "blur" },
    { min: 1, max: 30, message: "长度在 1 到 30 个字符", trigger: "blur" }
  ],
  layerNumber: [
    { required: true, message: "请输入层号", trigger: "blur" },
    { min: 1, max: 30, message: "长度在 1 到 30 个字符", trigger: "blur" }
  ],
});

// 打开修改库位对话框并回显数据
const editAreaLoca = async (row) => {
  dialogFormVisible.value = true;
  title.value = "修改库位";
  
  // 获取详细信息并回显
  try {
    const res = await getAreaLocaDetailApi(row.id)
    if (res.data) {
      warehouseAreaLocaForm.value = { ...res.data }
      // 加载库区列表
      await getAreaByWarehouseId(res.data.warehouseId);
    }
  } catch (error) {
    ElMessage.error('获取库位信息失败')
  }
};

// 保存修改信息
const save = async () => {
  if (!ruleFormRef.value) return
  
  await ruleFormRef.value.validate(async (valid) => {
    if (valid) {
      try {
        const res = await updateAreaLocaApi(warehouseAreaLocaForm.value)
        if (res.code == 1) {
          ElMessage.success('库位修改成功')
          dialogFormVisible.value = false
          // 通知父组件刷新列表
          emit('refresh')
        } else {
          ElMessage.error('修改库位失败')
        }
      } catch (error) {
        ElMessage.error('修改库位异常')
      }
    }
  })
};

// 监听仓库选择变化
const handleWarehouseChange = (warehouseId) => {
  getAreaByWarehouseId(warehouseId);
  warehouseAreaLocaForm.value.areaId = ""; // 重置库区选择
};

// 定义emit事件，用于通知父组件刷新数据
const emit = defineEmits(['refresh'])

// 暴露方法给父组件使用
defineExpose({
  editAreaLoca
})

// 组件挂载时获取数据
onMounted(() => {
  getWarehouseAll();
});
</script>

<template>
  <el-dialog style="width: 500px" v-model="dialogFormVisible" :title="title">
    <el-form
      :model="warehouseAreaLocaForm"
      :rules="rules"
      ref="ruleFormRef"
      label-width="auto"
    >
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="所属仓库" prop="warehouseId">
            <el-select 
              v-model="warehouseAreaLocaForm.warehouseId" 
              placeholder="请选择仓库"
              @change="handleWarehouseChange"
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
          <el-form-item label="所属库区" prop="areaId">
            <el-select 
              v-model="warehouseAreaLocaForm.areaId" 
              placeholder="请选择库区"
              style="width: 100%"
            >
              <el-option
                v-for="item in areaList"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="库位编号" prop="locaCode">
            <el-input v-model="warehouseAreaLocaForm.locaCode" placeholder="请输入库位编号" />
          </el-form-item>
        </el-col>
        
        <el-col :span="12">
          <el-form-item label="巷道号" prop="tunnelNumber">
            <el-input v-model="warehouseAreaLocaForm.tunnelNumber" placeholder="请输入巷道号" />
          </el-form-item>
        </el-col>
      </el-row>
      
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="货架号" prop="shelfNumber">
            <el-input v-model="warehouseAreaLocaForm.shelfNumber" placeholder="请输入货架号" />
          </el-form-item>
        </el-col>
        
        <el-col :span="12">
          <el-form-item label="层号" prop="layerNumber">
            <el-input v-model="warehouseAreaLocaForm.layerNumber" placeholder="请输入层号" />
          </el-form-item>
        </el-col>
      </el-row>
      
      <el-row :gutter="20">
        <el-col :span="8">
          <el-form-item label="长度(cm)" prop="locaLen">
            <el-input v-model="warehouseAreaLocaForm.locaLen" placeholder="请输入长度" />
          </el-form-item>
        </el-col>
        
        <el-col :span="8">
          <el-form-item label="宽度(cm)" prop="locaWidth">
            <el-input v-model="warehouseAreaLocaForm.locaWidth" placeholder="请输入宽度" />
          </el-form-item>
        </el-col>
        
        <el-col :span="8">
          <el-form-item label="高度(cm)" prop="locaHeight">
            <el-input v-model="warehouseAreaLocaForm.locaHeight" placeholder="请输入高度" />
          </el-form-item>
        </el-col>
      </el-row>
      
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="容积(cm³)" prop="localVolume">
            <el-input v-model="warehouseAreaLocaForm.localVolume" placeholder="请输入容积" />
          </el-form-item>
        </el-col>
        
        <el-col :span="12">
          <el-form-item label="承重(kg)" prop="localBearingCapacity">
            <el-input v-model="warehouseAreaLocaForm.localBearingCapacity" placeholder="请输入承重" />
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