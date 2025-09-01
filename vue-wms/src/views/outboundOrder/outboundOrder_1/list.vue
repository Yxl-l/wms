<template>
  <div id="box">
    <!-- 查询组件 -->
    <Query ref="queryRef" @search="handleSearch" @shuaX="handleRefreshZi"/>

    <!-- 查看出库单组件 -->
    <EditOutboundOrder ref="editRef" @refresh="handleRefreshEdit" />

    <!-- 确认发货组件 -->
    <ConfirmOutbound 
      :visible="confirmVisible" 
      :outboundOrder="currentOrder" 
      @update:visible="confirmVisible = $event"
      @refresh="handleRefresh"
    />

    <el-table :data="outboundOrderList" style="width: 100%">
      <el-table-column type="index" label="序号" width="80" />
      <el-table-column prop="invoiceCode" label="发货单号" width="150"/>
    <el-table-column prop="status" label="发货单状态" width="120">
        <template #default="scope">
          <el-tag :type="statusConfig[scope.row.status] ? statusConfig[scope.row.status].type : 'info'">
            {{ statusConfig[scope.row.status] ? statusConfig[scope.row.status].text : scope.row.status }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="customerName" label="客户名称" width="120"/>
      <el-table-column prop="totalCount" label="总数量" width="100"/>
      <el-table-column prop="totalWeight" label="总重量/kg" width="100"/>
      <el-table-column prop="totalVolume" label="总体积/m³" width="100"/>
      <el-table-column prop="empId" label="操作员工ID" width="120"/>
      <el-table-column prop="createBy" label="创建人账户" width="120"/>
      <el-table-column prop="createTime" label="创建时间" width="200"/>
      <el-table-column label="ᅟᅠ       ᅟᅠᅟᅠ 操   ᅟᅠ   ᅟᅠ作" width="280" fixed="right">
        <template #default="scope">
          <el-button type="primary" size="small" @click="handleView(scope.row)">查看</el-button>
          <el-button type="warning" size="small" @click="handleConfirmOutbound(scope.row)">确认发货单</el-button>
          <el-button type="danger" size="small" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <br>

    <el-pagination
      v-model:current-page="page"
      v-model:page-size="pageSize"
      :page-sizes="[8,16,24,32]"
      :background="true"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalSize"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import { getPageApi, deleteApi,updateApi } from "@/api/outboundOrder.js";
import Query from "./query.vue";
import EditOutboundOrder from "./edit.vue";
import ConfirmOutbound from "./ConfirmOutbound.vue";

const queryRef = ref();
const editRef = ref();
const outboundOrderList = ref([]);
const page = ref(1);
const pageSize = ref(8);
const totalSize = ref(0);

// 确认发货弹窗
const confirmVisible = ref(false);
const currentOrder = ref(null);

// 出库单状态映射
const statusConfig = {
  1: { text: '预发货', type: 'info' },
  2: { text: '确认发货', type: 'primary' },
  3: { text: '拣货中', type: 'success' },
  4: { text: '拣货完成', type: 'success' },
  5: { text: '拣货完成待打包', type: 'warning' },
  6: { text: '待称重', type: 'primary' },
  7: { text: '待发货', type: 'danger' },
  8: { text: '已发货', type: 'success' },
  9: { text: '已签收', type: 'success' }
};

// 获取分页数据
const getPage = async () => {
  try {
    const res = await getPageApi(
      page.value,
      pageSize.value,
      queryRef.value?.searchForm?.customerName || '',
      queryRef.value?.searchForm?.invoiceCode || '',
      queryRef.value?.searchForm?.status || ''
    );
    outboundOrderList.value = res.data.rows || [];
    totalSize.value = res.data.total || 0;
  } catch (err) {
    console.error(err);
  }
};

// 分页处理
const handleSizeChange = val => { pageSize.value = val; page.value = 1; getPage(); };
const handleCurrentChange = val => { page.value = val; getPage(); };

// 查询刷新
const handleSearch = () => { page.value = 1; getPage(); };
const handleRefreshZi = () => handleRefresh();
const handleRefreshEdit = () => handleRefresh();
const handleRefresh = () => {
  if (queryRef.value) {
    queryRef.value.searchForm.customerName = '';
    queryRef.value.searchForm.invoiceCode = '';
    queryRef.value.searchForm.status = '';
  }
  page.value = 1;
  getPage();
};

// 查看出库单
const handleView = (row) => {
  editRef.value.editOutboundOrder(row);
};

// 确认发货
const handleConfirmOutbound = (row) => {
  currentOrder.value = row;
  confirmVisible.value = true;
};

// 删除出库单
const handleDelete = (row) => {
  ElMessageBox.confirm('确定要删除该出库单吗？','删除确认',{
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    try {
      const res = await deleteApi(row.id);
      if(res.code == 1) { ElMessage.success('删除成功'); handleRefresh(); }
      else { ElMessage.error('删除失败'); }
    } catch(err) {
      console.error(err); ElMessage.error('删除异常');
    }
  }).catch(() => { ElMessage.info('已取消删除'); });
};



onMounted(() => { getPage(); });
</script>

<style scoped>
div { opacity: 0.9; }
</style>
