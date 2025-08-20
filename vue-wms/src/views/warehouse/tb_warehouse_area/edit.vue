<script setup>
import { ref, onMounted } from "vue";
import { ElMessage } from "element-plus";
import {
  getWarehouseAllApi,
  updateAreaApi,
  getDictApi,
  getAreaDetailApi
} from "@/api/warehouse";

// 定义响应式数据
const dialogFormVisible = ref(false); //控制对话框的显示与隐藏
const title = ref("修改库区");
const ruleFormRef = ref(null);

// 库区表单数据
const warehouseAreaForm = ref({
  id: '',
  name: "",
  warehouseId: "",
  category: "",
});

// 获取数据字典里的库区类型
const Dict = ref([]);
const getDict = async () => {
  const res = await getDictApi();
  Dict.value = res.data;
};

//获取全部仓库
const warehouse = ref([]);
const getWarehouseAll = async () => {
  const res = await getWarehouseAllApi();
  warehouse.value = res.data;
};

// 表单验证规则
const rules = ref({
  name: [
    { required: true, message: "请输入库区名称", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" },
  ],
  warehouseId: [
    { required: true, message: "请选择所属仓库", trigger: "change" }
  ],
  category: [
    { required: true, message: "请选择库区类型", trigger: "change" }
  ]
});

// 打开修改库区对话框并回显数据
const editArea = async (row) => {
  dialogFormVisible.value = true;
  title.value = "修改库区";
  
  // 获取详细信息并回显
  try {
    const res = await getAreaDetailApi(row.id)
    if (res.data) {
      warehouseAreaForm.value = { ...res.data }
    }
  } catch (error) {
    ElMessage.error('获取库区信息失败')
  }
};

// 保存修改信息
const save = async () => {
  if (!ruleFormRef.value) return
  
  await ruleFormRef.value.validate(async (valid) => {
    if (valid) {
      try {
        const res = await updateAreaApi(warehouseAreaForm.value)
        if (res.code == 1) {
          ElMessage.success('库区修改成功')
          dialogFormVisible.value = false
          // 通知父组件刷新列表
          emit('refresh')
        } else {
          ElMessage.error('修改库区失败')
        }
      } catch (error) {
        ElMessage.error('修改库区异常')
      }
    }
  })
};

// 定义emit事件，用于通知父组件刷新数据
const emit = defineEmits(['refresh'])

// 暴露方法给父组件使用
defineExpose({
  editArea
})

// 组件挂载时获取地址数据
onMounted(() => {
  getDict();
  getWarehouseAll();
});
</script>

<template>
  <el-dialog style="width: 400px" v-model="dialogFormVisible" :title="title">
    <el-form
      :model="warehouseAreaForm"
      :rules="rules"
      ref="ruleFormRef"
      label-width="auto"
    >
      <el-form-item label="库区名字" prop="name">
        <el-input v-model="warehouseAreaForm.name" placeholder="请输入库区名称" />
      </el-form-item>
      <el-form-item label="库区所属仓库" prop="warehouseId">
        <el-select v-model="warehouseAreaForm.warehouseId" placeholder="请选择仓库">
          <el-option
            v-for="item in warehouse"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
       <el-form-item label="库区类型" prop="category">
        <el-select v-model="warehouseAreaForm.category" placeholder="请选择库区类型">
          <el-option
            v-for="item in Dict"
            :key="item.id"
            :label="item.dictValue"
            :value="item.dictCode"
          />
        </el-select>
      </el-form-item>
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