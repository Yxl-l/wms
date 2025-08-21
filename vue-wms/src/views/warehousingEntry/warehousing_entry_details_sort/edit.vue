<script setup>
import { ref, onMounted, watch } from "vue";
import { ElMessage } from "element-plus";
import { updateApi, getDetailApi } from "@/api/warehousingEntryDetailsSort";
import { getPageApi as getWarehousingEntryDetailsPageApi } from "@/api/warehousingEntryDetails";
import { getSkuAllApi } from "@/api/goods";

// 定义响应式数据
const dialogFormVisible = ref(false); //控制对话框的显示与隐藏
const title = ref("修改分拣明细");
const ruleFormRef = ref(null);

// 分拣明细表单数据
const warehousingEntryDetailsSortForm = ref({
  id: '',
  count: "",
  warehousingEntryDetailsId: "",
  skuId: "",
  status: 1,
  damageCount: 0,
  surpassCount: 0,
  lackCount: 0
});

// 获取所有入库单明细数据（只获取状态为4或5的）
const warehousingEntryDetailsList = ref([]);
const getWarehousingEntryDetailsAll = async () => {
  try {
    // 获取状态为4（拣货中）或5（已上架）的入库单明细
    const res4 = await getWarehousingEntryDetailsPageApi(1, 9999, '', '', '', '', 4);
    const res5 = await getWarehousingEntryDetailsPageApi(1, 9999, '', '', '', '', 5);
    
    // 合并两个结果
    const data4 = res4.rows || [];
    const data5 = res5.rows || [];
    warehousingEntryDetailsList.value = [...data4, ...data5];
  } catch (error) {
    ElMessage.error("获取入库单明细数据失败");
  }
};

// 获取所有sku数据
const skuList = ref([]);
const getSkuAll = async () => {
  try {
    const res = await getSkuAllApi();
    skuList.value = res.data || [];
  } catch (error) {
    ElMessage.error("获取sku失败");
  }
};

// 当选择入库单明细时，自动设置对应的skuId
watch(() => warehousingEntryDetailsSortForm.value.warehousingEntryDetailsId, (newVal) => {
  if (newVal) {
    const selectedDetail = warehousingEntryDetailsList.value.find(item => item.id == newVal);
    if (selectedDetail) {
      warehousingEntryDetailsSortForm.value.skuId = selectedDetail.skuId;
    }
  }
});

// 表单验证规则
const rules = ref({
  count: [
    { required: true, message: "请输入分拣数量", trigger: "blur" },
    { pattern: /^\d+$/, message: "请输入数字", trigger: "blur" }
  ],
  warehousingEntryDetailsId: [
    { required: true, message: "请选择入库单明细", trigger: "change" }
  ],
  skuId: [
    { required: true, message: "请选择规格", trigger: "change" }
  ]
});

// 打开修改分拣明细对话框并回显数据
const editWarehousingEntryDetailsSort = async (row) => {
  dialogFormVisible.value = true;
  title.value = "修改分拣明细";
  
  // 获取详细信息并回显
  try {
    const res = await getDetailApi(row.id)
    if (res.data) {
      warehousingEntryDetailsSortForm.value = { ...res.data }
    }
  } catch (error) {
    ElMessage.error('获取分拣明细信息失败')
  }
};

// 保存修改信息
const save = async () => {
  if (!ruleFormRef.value) return
  
  try {
    await ruleFormRef.value.validate();
    
    // 创建提交数据对象
    const submitData = { ...warehousingEntryDetailsSortForm.value };
    
    const res = await updateApi(submitData)
    if (res.code == 1) {
      ElMessage.success('分拣明细修改成功')
      dialogFormVisible.value = false
      // 通知父组件刷新列表
      emit('refresh')
    } else {
      ElMessage.error('修改分拣明细失败')
    }
  } catch (error) {
    // 验证失败或网络错误
    if (error instanceof Error) {
      ElMessage.error('请检查表单填写是否正确')
    } else {
      ElMessage.error('修改分拣明细异常')
    }
  }
};

// 定义emit事件，用于通知父组件刷新数据
const emit = defineEmits(['refresh'])

// 暴露方法给父组件使用
defineExpose({
  editWarehousingEntryDetailsSort
})

// 组件挂载时获取数据
onMounted(() => {
  getWarehousingEntryDetailsAll();
  getSkuAll();
});
</script>

<template>
  <el-dialog style="width: 700px" v-model="dialogFormVisible" :title="title">
    <el-form
      :model="warehousingEntryDetailsSortForm"
      :rules="rules"
      ref="ruleFormRef"
      label-width="auto"
    >
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="入库单明细" prop="warehousingEntryDetailsId">
            <el-select 
              v-model="warehousingEntryDetailsSortForm.warehousingEntryDetailsId" 
              placeholder="请选择入库单明细"
              style="width: 100%"
            >
              <el-option
                v-for="item in warehousingEntryDetailsList"
                :key="item.id"
                :label="item.id"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
        
        <el-col :span="12">
          <el-form-item label="规格" prop="skuId">
            <el-select 
              v-model="warehousingEntryDetailsSortForm.skuId" 
              placeholder="请选择规格"
              style="width: 100%"
              disabled
            >
              <el-option
                v-for="item in skuList"
                :key="item.id"
                :label="item.title"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="分拣数量" prop="count">
            <el-input v-model.number="warehousingEntryDetailsSortForm.count" placeholder="请输入分拣数量" />
          </el-form-item>
        </el-col>
        
        <el-col :span="12">
          <el-form-item label="破损个数">
            <el-input v-model.number="warehousingEntryDetailsSortForm.damageCount" placeholder="请输入破损个数" />
          </el-form-item>
        </el-col>
      </el-row>
      
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="超出数量">
            <el-input v-model.number="warehousingEntryDetailsSortForm.surpassCount" placeholder="请输入超出数量" />
          </el-form-item>
        </el-col>
        
        <el-col :span="12">
          <el-form-item label="缺少数量">
            <el-input v-model.number="warehousingEntryDetailsSortForm.lackCount" placeholder="请输入缺少数量" />
          </el-form-item>
        </el-col>
      </el-row>
      
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="状态"> 
            <el-select disabled v-model="warehousingEntryDetailsSortForm.status" placeholder="选择状态" style="width: 150px;" clearable>
              <el-option label="分拣中" :value="1" />
              <el-option label="已完成分拣" :value="2" />
              <el-option label="已上架" :value="3" />
            </el-select>
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