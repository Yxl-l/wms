<script setup>
import { ref } from "vue";
import { ElMessage } from "element-plus";
import { getDetailApi } from "@/api/outboundOrder.js";
import { getSkuAllApi } from "@/api/goods.js";

// 定义响应式数据
const dialogFormVisible = ref(false); //控制对话框的显示与隐藏
const title = ref("查看出库单详情");
const ruleFormRef = ref(null);

// 出库单表单数据（与添加界面保持一致的结构）
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

// 状态映射
const statusMap = {
  1: '预发货',

};

// 商品列表数据
const goodsList = ref([]);

// 商品映射（用于显示商品名称）
const goodsMap = ref(new Map());

// 查看出库单详情
const editOutboundOrder = async (row) => {
  dialogFormVisible.value = true;
  
  try {
    // 获取出库单详情数据
    const res = await getDetailApi(row.id);
    if (res.code === 1) {
      // 填充表单数据
      Object.assign(warehousingEntryForm.value, res.data);
      
      // 获取所有商品信息用于映射
      await getGoodsMap();
      
      // 处理商品明细数据
      processGoodsDetails(res.data.outboundOrderDetails || []);
    } else {
      ElMessage.error('获取出库单详情失败');
    }
  } catch (error) {
    ElMessage.error('获取出库单详情异常');
  }
};

// 获取所有商品信息用于映射
const getGoodsMap = async () => {
  try {
    const res = await getSkuAllApi();
    if (res.code === 1) {
      goodsMap.value.clear();
      res.data.forEach(item => {
        goodsMap.value.set(item.id, item);
      });
    }
  } catch (error) {
    ElMessage.error('获取商品信息失败');
  }
};

// 处理商品明细数据
const processGoodsDetails = (details) => {
  goodsList.value = details.map(item => {
    const goodsInfo = goodsMap.value.get(item.skuId) || {};
    return {
      ...item,
      title: goodsInfo.title || '未知商品',
      productWeight: goodsInfo.productWeight || 0,
      productVolume: goodsInfo.productVolume || 0,
      subtotalWeight: (parseFloat(goodsInfo.productWeight) || 0) * (parseInt(item.outCount) || 0),
      subtotalVolume: (parseFloat(goodsInfo.productVolume) || 0) * (parseInt(item.outCount) || 0)
    };
  });
};

// 暴露方法给父组件使用
defineExpose({
  editOutboundOrder
});

</script>

<template>
  <div>
    <el-dialog 
      style="width: 900px" 
      v-model="dialogFormVisible" 
      :title="title"
      :append-to-body="true"
      top="5vh"
      custom-class="top-dialog"
    >
      <el-form
        :model="warehousingEntryForm"
        ref="ruleFormRef"
        label-width="auto"
      >
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="客户名称">
              <el-input v-model="warehousingEntryForm.customerName" disabled />
            </el-form-item>
          </el-col>
          
          <el-col :span="12">
            <el-form-item label="状态">
              <el-input :value="statusMap[warehousingEntryForm.status]" disabled />
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
          
          <el-col :span="12">
            <el-form-item label="操作员工ID">
              <el-input v-model.number="warehousingEntryForm.empId" disabled />
            </el-form-item>
          </el-col>
          
          <el-col :span="12">
            <el-form-item label="创建人账户">
              <el-input v-model="warehousingEntryForm.createBy" disabled />
            </el-form-item>
          </el-col>
        </el-row>
        
        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="商品明细">
              <el-table :data="goodsList" style="width: 100%;" border>
                <el-table-column prop="title" label="商品名称" width="150" />
                <el-table-column prop="productWeight" label="单品重量" width="100" />
                <el-table-column prop="productVolume" label="单品体积" width="100" />
                <el-table-column prop="outCount" label="出库数量" width="100" />
                <el-table-column prop="subtotalWeight" label="小计重量" width="100" />
                <el-table-column prop="subtotalVolume" label="小计体积" width="100" />
                <el-table-column prop="pickStatus" label="拣货状态" width="100">
                  <template #default="scope">
                    <el-tag v-if="scope.row.pickStatus === 1">待拣货</el-tag>
                    <el-tag v-else-if="scope.row.pickStatus === 2">拣货中</el-tag>
                    <el-tag v-else-if="scope.row.pickStatus === 3">已拣货</el-tag>
                    <span v-else>{{ scope.row.pickStatus }}</span>
                  </template>
                </el-table-column>
              </el-table>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" @click="dialogFormVisible = false">关闭</el-button>
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

<style>
.top-dialog {
  z-index: 3000 !important;
}
</style>