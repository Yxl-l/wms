<script setup>
import { ref } from "vue";
import { ElMessage } from "element-plus";
import { addApi } from "@/api/outboundOrder.js";
import { getSkuAllApi } from "@/api/goods.js";

// 定义响应式数据
const dialogFormVisible = ref(false); //控制对话框的显示与隐藏
const goodsDialogVisible = ref(false); // 控制商品选择对话框的显示与隐藏
const title = ref("添加出库单");
const ruleFormRef = ref(null);

// 出库单表单数据
const warehousingEntryForm = ref({
  customerName: "",
  status: 1,
  totalCount: 0, //总数量
  totalWeight: 0, //总重量
  totalVolume: 0, //总体积
  createBy: "蔡徐坤",
  empId: 1,
  // 出库单明细字段
  outboundOrderDetails: []
});

// 已选择的商品列表
const selectedGoodsList = ref([]);

// 商品列表数据
const goodsTableData = ref([]);

// 表单验证规则
const rules = ref({
  customerName: [
    { required: true, message: "请输入客户名称", trigger: "blur" }
  ]
});

// 打开添加出库单对话框
const addWarehousingEntry = () => {
  dialogFormVisible.value = true;
  // 重置表单数据
  Object.assign(warehousingEntryForm.value, {
    customerName: "",
    status: 1,
    totalCount: 0,
    totalWeight: 0,
    totalVolume: 0,
    createBy: "蔡徐坤",
    empId: 1,
    outboundOrderDetails: []
  });
  
  // 重置已选择的商品列表
  selectedGoodsList.value = [];
};

// 保存出库单信息
const save = async () => {
  if (!ruleFormRef.value) return
    await ruleFormRef.value.validate();
    
    // 创建提交数据对象
    const submitData = { ...warehousingEntryForm.value };
    
    const res = await addApi(submitData)
    if (res.code == 1) {
      ElMessage.success('出库单添加成功')
      dialogFormVisible.value = false
      // 通知父组件刷新列表
      emit('refresh')
    } else {
      ElMessage.error('添加出库单失败')
    }
  
};

// 打开商品选择对话框
const openGoodsDialog = () => {
  goodsDialogVisible.value = true;
  getGoodsList();
};

// 获取商品列表
const getGoodsList = async () => {
  try {
    // 使用 getSkuAllApi 接口获取所有商品
    const res = await getSkuAllApi();
    
    if (res.code === 1) {
      goodsTableData.value = res.data;
    }
  } catch (error) {
    ElMessage.error('获取商品列表失败');
  }
};

// 添加商品到列表
const addGoodsToList = (row) => {
  // 检查商品是否已添加
  const existingItem = selectedGoodsList.value.find(item => item.skuId === row.id);
  if (existingItem) {
    ElMessage.warning('该商品已添加，请直接修改数量');
    return;
  }
  
  // 添加商品到列表
  const newItem = {
    skuId: row.id,
    title: row.title,
    productWeight: row.productWeight,
    productVolume: row.productVolume,
    outCount: 1, // 默认出库数量为1
    pickStatus: 1
  };
  
  selectedGoodsList.value.push(newItem);
  
  // 更新出库单明细
  updateOutboundOrderDetails();
  
  // 关闭商品选择对话框
  goodsDialogVisible.value = false;
  
  ElMessage.success(`商品"${row.title}"已添加`);
};

// 更新出库单明细和总计数据
const updateOutboundOrderDetails = () => {
  // 更新出库单明细
  warehousingEntryForm.value.outboundOrderDetails = selectedGoodsList.value.map(item => ({
    skuId: item.skuId,
    outCount: item.outCount,
    pickStatus: item.pickStatus,
    volume: Math.round((parseFloat(item.productVolume) || 0) * (parseInt(item.outCount) || 0)),
    weight: Math.round((parseFloat(item.productWeight) || 0) * (parseInt(item.outCount) || 0))
  }));
  
  // 计算总计
  let totalCount = 0;
  let totalWeight = 0;
  let totalVolume = 0;
  
  selectedGoodsList.value.forEach(item => {
    const count = parseInt(item.outCount) || 0;
    totalCount += count;
    totalWeight += (parseFloat(item.productWeight) || 0) * count;
    totalVolume += (parseFloat(item.productVolume) || 0) * count;
  });
  
  warehousingEntryForm.value.totalCount = totalCount;
  warehousingEntryForm.value.totalWeight = Math.round(totalWeight);
  warehousingEntryForm.value.totalVolume = Math.round(totalVolume);
};

// 删除已选择的商品
const removeSelectedGoods = (index) => {
  selectedGoodsList.value.splice(index, 1);
  updateOutboundOrderDetails();
};

// 当出库数量变化时更新总计
const onOutCountChange = () => {
  updateOutboundOrderDetails();
};

// 定义emit事件，用于通知父组件刷新数据
const emit = defineEmits(['refresh'])

</script>

<template>
  <div>
    <el-button type="primary" @click="addWarehousingEntry">添加新的出库单</el-button>

    <el-dialog style="width: 900px" v-model="dialogFormVisible" :title="title">
      <el-form
        :model="warehousingEntryForm"
        :rules="rules"
        ref="ruleFormRef"
        label-width="auto"
      >
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="客户名称" prop="customerName">
              <el-input v-model="warehousingEntryForm.customerName" placeholder="请输入客户名称" />
            </el-form-item>
          </el-col>
          
          <el-col :span="12">
            <el-form-item label="总数量">
              <el-input v-model.number="warehousingEntryForm.totalCount" disabled />
            </el-form-item>
          </el-col>
          
          <el-col :span="12">
            <el-form-item label="总重量">
              <el-input v-model.number="warehousingEntryForm.totalWeight" disabled />
            </el-form-item>
          </el-col>
          
          <el-col :span="12">
            <el-form-item label="总体积">
              <el-input v-model.number="warehousingEntryForm.totalVolume" disabled />
            </el-form-item>
          </el-col>
        </el-row>
        
        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="商品列表">
              <el-button type="primary" @click="openGoodsDialog" size="small">
                添加商品
              </el-button>
              
              <el-table :data="selectedGoodsList" style="width: 100%; margin-top: 10px;" border>
                <el-table-column prop="title" label="商品名称" width="150" />
                <el-table-column prop="productWeight" label="单品重量" width="100" />
                <el-table-column prop="productVolume" label="单品体积" width="100" />
                <el-table-column label="出库数量" width="150">
                  <template #default="scope">
                    <el-input-number 
                      v-model="scope.row.outCount" 
                      :min="1" 
                      size="small"
                      @change="onOutCountChange"
                    />
                  </template>
                </el-table-column>
                <el-table-column label="小计重量" width="100">
                  <template #default="scope">
                    {{ Math.round((parseFloat(scope.row.productWeight) || 0) * (parseInt(scope.row.outCount) || 0)) }}
                  </template>
                </el-table-column>
                <el-table-column label="小计体积" width="100">
                  <template #default="scope">
                    {{ Math.round((parseFloat(scope.row.productVolume) || 0) * (parseInt(scope.row.outCount) || 0)) }}
                  </template>
                </el-table-column>
                <el-table-column label="操作" width="80">
                  <template #default="scope">
                    <el-button type="danger" size="small" @click="removeSelectedGoods(scope.$index)">删除</el-button>
                  </template>
                </el-table-column>
              </el-table>
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
    
    <!-- 商品选择对话框 -->
    <el-dialog v-model="goodsDialogVisible" title="选择商品" width="60%">
      <el-table :data="goodsTableData" style="width: 100%">
        <el-table-column prop="id" label="商品ID" width="80" />
        <el-table-column prop="title" label="商品名称" width="200" />
        <el-table-column prop="code" label="商品编码" width="150" />
        <el-table-column prop="category" label="商品类别" width="120" />
        <el-table-column prop="productWeight" label="商品重量" width="100" />
        <el-table-column prop="productVolume" label="商品体积" width="100" />
        <el-table-column prop="price" label="商品价格" width="100" />
        <el-table-column label="操作" width="100">
          <template #default="scope">
            <el-button size="small" type="primary" @click="addGoodsToList(scope.row)">添加</el-button>
          </template>
        </el-table-column>
      </el-table>
      
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="goodsDialogVisible = false">取消</el-button>
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