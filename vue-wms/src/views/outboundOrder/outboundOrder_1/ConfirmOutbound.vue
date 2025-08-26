<template>
  <el-dialog
    :model-value="visible"
    title="确认发货"
    width="1200px"
    :append-to-body="true"
    @update:visible="(val) => emit('update:visible', val)"
    @close="handleClose"
  >
    <el-form ref="formRef" :model="form" label-width="120px">
      <el-row :gutter="20">
        <el-col :span="5">
          <el-form-item label="客户名称" style="width: 250px;">
            <el-input v-model="form.customerName" disabled />
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item label="发货单状态" style="width: 200px;">
            <el-input :value="statusMap[form.status]" disabled />
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="发货单号" style="width: 700px;">
            <el-input v-model="form.invoiceCode" disabled />
          </el-form-item>
        </el-col>
      </el-row>

      <el-table :data="form.outboundOrderDetails" border style="width:100%">
        <el-table-column type="index" label="序号" width="60"/>
        <el-table-column prop="title" label="商品名称" width="200"/>
        <el-table-column prop="maxCount" label="总数量" width="150"/>
        <el-table-column label="发货数量" width="150">
          <template #default="scope">
            <el-input-number
              v-model="scope.row.outCount"
              :min="1"
              :max="scope.row.maxCount"
              :controls="false"
              size="small"
            />
          </template>
        </el-table-column>
        <el-table-column label="仓库" width="200">
          <template #default="scope">
            <el-select 
              v-model="scope.row.targetWarehouseId" 
              placeholder="请选择仓库"
              @change="handleWarehouseChange(scope.row)"
              size="small"
            >
              <el-option
                v-for="wh in warehouseList"
                :key="wh.id"
                :label="wh.name"
                :value="wh.id"
              />
            </el-select>
          </template>
        </el-table-column>
        <el-table-column label="库区" width="200">
          <template #default="scope">
            <el-select 
              v-model="scope.row.targetWarehouseAreaId" 
              placeholder="请选择库区"
              :disabled="!scope.row.targetWarehouseId"
              @change="handleAreaChange(scope.row)"
              size="small"
            >
              <el-option
                v-for="area in areaList"
                :key="area.id"
                :label="area.name"
                :value="area.id"
              />
            </el-select>
          </template>
        </el-table-column>
        <el-table-column label="库位" width="200">
          <template #default="scope">
            <el-select 
              v-model="scope.row.targetWarehouseAreaLocaId" 
              placeholder="请选择库位"
              :disabled="!scope.row.targetWarehouseAreaId"
              size="small"
            >
              <el-option
                v-for="loca in locaList"
                :key="loca.id"
                :label="loca.locaCode"
                :value="loca.id"
              />
            </el-select>
          </template>
        </el-table-column>
      </el-table>
    </el-form>

    <template #footer>
      <el-button @click="handleClose">关闭</el-button>
      <el-button type="primary" @click="handleConfirm">确认发货</el-button>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, watch, defineProps, defineEmits } from "vue";
import { ElMessage } from "element-plus";
import { getWarehouseAllApi, getAreaByWarehouseIdApi, getPageAreaLocaApi } from "@/api/warehouse.js";
import { updateDetailsApi, getDetailApi } from "@/api/outboundOrder.js";
import { getSkuAllApi } from "@/api/goods.js";

const props = defineProps({
  visible: Boolean,
  outboundOrder: Object
});
const emit = defineEmits(["update:visible", "refresh"]);

const formRef = ref(null);
const form = ref({
  id: "",
  customerName: "",
  invoiceCode: "",
  status: 1,
  totalCount: "",
  outboundOrderDetails: []
});

// 状态映射
const statusMap = {
  1: "预发货",
  2: "确认发货",
  3: "拣货中",
  4: "拣货完成",
  5: "待打包",
  6: "待称重",
  7: "待发货",
  8: "已发货",
  9: "已签收"
};

// 仓库/库区/库位列表
const warehouseList = ref([])
const areaList = ref([])
const locaList = ref([])
const goodsMap = ref(new Map());

// 当弹窗显示时初始化表单数据
watch(() => props.visible, async (val) => {
  if (val && props.outboundOrder) {
    // 获取出库单详情数据
    const detailRes = await getDetailApi(props.outboundOrder.id);
    if (detailRes.code === 1) {
      const detailData = detailRes.data;
      form.value.customerName = detailData.customerName;
      form.value.invoiceCode = detailData.invoiceCode;
      form.value.status = detailData.status || 1;
      form.value.totalCount = detailData.totalCount;
      form.value.id = detailData.id;
  
      // 获取商品信息
      await getGoodsMap();
      
      // 处理出库单明细
      form.value.outboundOrderDetails = (detailData.outboundOrderDetails || []).map(item => {
        const goodsInfo = goodsMap.value.get(item.skuId) || {};
        return {
          ...item,
          title: goodsInfo.title || '未知商品',
          outCount: item.outCount || item.totalCount, // 默认发总数量
          targetWarehouseId: null,
          targetWarehouseAreaId: null,
          targetWarehouseAreaLocaId: null
        };
      });
    }
    
    // 获取仓库列表
    const warehouseRes = await getWarehouseAllApi();
    warehouseList.value = warehouseRes.data || [];
  }
});

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

// 处理仓库变化
const handleWarehouseChange = async (row) => {
  // 清空库区和库位
  row.targetWarehouseAreaId = null;
  row.targetWarehouseAreaLocaId = null;
  areaList.value = [];
  locaList.value = [];
  
  if (row.targetWarehouseId) {
    // 获取对应仓库的库区列表
    try {
      const res = await getAreaByWarehouseIdApi(row.targetWarehouseId);
     
        areaList.value = res.rows || [];
     
    } catch (error) {
      ElMessage.error('获取库区信息失败');
    }
  }
};

// 处理库区变化
const handleAreaChange = async (row) => {
  // 清空库位
  row.targetWarehouseAreaLocaId = null;
  locaList.value = [];
  
  if (row.targetWarehouseAreaId) {
    // 获取对应库区的库位列表
    try {
      const res = await getPageAreaLocaApi(1, 1000, row.targetWarehouseAreaId);
    
        locaList.value = res.rows || [];
  
    } catch (error) {
      ElMessage.error('获取库位信息失败');
    }
  }
};

// 关闭弹窗
const handleClose = () => {
  // 清空数据
  areaList.value = [];
  locaList.value = [];
  emit("update:visible", false);
};

// 确认发货
const handleConfirm = async () => {
  try {
    // 验证每个明细是否选择了仓库、库区、库位
    for (const detail of form.value.outboundOrderDetails) {
      if (!detail.targetWarehouseId) {
        ElMessage.warning(`商品"${detail.title}"未选择仓库`);
        return;
      }
      if (!detail.targetWarehouseAreaId) {
        ElMessage.warning(`商品"${detail.title}"未选择库区`);
        return;
      }
      if (!detail.targetWarehouseAreaLocaId) {
        ElMessage.warning(`商品"${detail.title}"未选择库位`);
        return;
      }
      if (detail.outCount > detail.totalCount) {
        ElMessage.warning(`商品"${detail.title}"发货数量不能超过总数量`);
        return;
      }
    }
    
    // 构造要发送的数据，状态改为2
    const submitData = {
      id: form.value.id,
      customerName: form.value.customerName,
      invoiceCode: form.value.invoiceCode,
      status: 2, // 状态改为2
      totalCount: form.value.totalCount,
      outboundOrderDetails: form.value.outboundOrderDetails.map(detail => ({
        ...detail,
        // 可以在这里添加其他需要的字段处理
      }))
    };
    
    // 调用API更新出库单和明细
    const res = await updateDetailsApi(submitData);
    if (res.code === 1) {
      ElMessage.success('确认发货成功');
      // 清空数据
      areaList.value = [];
      locaList.value = [];
      emit("update:visible", false);
      emit("refresh");
    } else {
      ElMessage.error('确认发货失败: ' + res.msg);
    }
  } catch (error) {
    console.error(error);
    ElMessage.error('确认发货异常');
  }
};
</script>

<style scoped>
.el-table th, .el-table td {
  text-align: center;
}
</style>