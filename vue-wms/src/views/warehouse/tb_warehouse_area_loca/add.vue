<script setup>
import { ref, onMounted, computed } from "vue";
import { ElMessage } from "element-plus";
import {
  getWarehouseAllApi,
  addAreaApi,
  getDictApi,
} from "@/api/warehouse";


// 定义响应式数据
const dialogFormVisible = ref(false); //控制对话框的显示与隐藏
const title = ref("添加库区");
const ruleFormRef = ref(null);

// 库区表单数据
const warehouseAreaForm = ref({
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
    { required: true, message: "请输入仓库名称", trigger: "blur" },
    { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" },
  ],

});

// 打开添加库区对话框
const addWarehouseArea = () => {
  dialogFormVisible.value = true;
  // 重置表单数据
  Object.assign(warehouseAreaForm.value, {
   name: "",
  warehouseId: "",
  category: "",
  });
};
// 保存仓库信息
const save = async () => {
  // 调用API保存数据
  console.log(warehouseAreaForm.value)
  let res = await addAreaApi(warehouseAreaForm.value)
  if(res){
    ElMessage.success('库区添加成功')
  }else{
    ElMessage.error('库区添加失败')
  }   
  dialogFormVisible.value = false
}

// 组件挂载时获取地址数据
onMounted(() => {
  getDict();
  getWarehouseAll();
});
</script>

<template>
  <div>
    <el-button type="primary" @click="addWarehouseArea">添加库位</el-button>

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
  </div>
</template>

<style scoped>
.dialog-footer {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}
</style>
