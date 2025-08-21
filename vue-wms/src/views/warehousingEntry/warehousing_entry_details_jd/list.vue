<script setup>
import { getPageApi, deleteApi } from "@/api/warehousingEntryDetails";
import Query from "./query.vue";
import { onMounted, ref } from "vue";
import { ElMessage, ElMessageBox } from 'element-plus'
import { getAllApi as getSupplierAllApi } from "@/api/supplier";
import { getAllApi as getWarehousingEntryAllApi } from "@/api/warehousingEntry";
import { getSkuAllApi } from "@/api/goods";
import { updateStatusApi } from "@/api/warehousingEntryDetails";

const queryRef = ref();
const editRef = ref() // 修改组件的引用
// 状态修改弹窗相关
const statusDialogVisible = ref(false)
const currentRow = ref({})
const selectedStatus = ref()
// 状态映射
const statusMap = {
  1: '未到货',
  2: '已到货',
  3: '已卸货可分拣',
  4: '分拣中',
  5: '分拣完成可上架'
}

// 状态类型映射（用于el-tag的type属性）
const statusTypeMap = {
  1: 'danger',
  2: 'warning',
  3: 'success',
  4: 'primary',
  5: 'success'
}

// 分页相关
const warehousingEntryDetailsList = ref([])
const page = ref(1)
const pageSize = ref(8)
const totalSize = ref(0)

// 数据映射
const supplierMap = ref(new Map())
const warehousingEntryMap = ref(new Map())
const skuListMap = ref(new Map())

// 获取所有sku数据用于映射
const getSkuAll = async () => {
  try {
    const res = await getSkuAllApi();
    if (res.data) {
      res.data.forEach(item => {
        skuListMap.value.set(item.id, item.title);
      });
    }
  } catch (error) {
    console.error("获取sku数据失败:", error);
  }
};

// 获取所有供应商数据用于映射
const getSupplierData = async () => {
  try {
    const res = await getSupplierAllApi();
    if (res.data) {
      res.data.forEach(item => {
        supplierMap.value.set(item.id, item.supplierName);
      });
    }
  } catch (error) {
    console.error("获取供应商数据失败:", error);
  }
};

// 获取所有入库单数据用于映射
const getWarehousingEntryData = async () => {
  try {
    const res = await getWarehousingEntryAllApi();
    if (res.data) {
      res.data.forEach(item => {
        warehousingEntryMap.value.set(item.id, item.batchNumber);
      });
    }
  } catch (error) {
    console.error("获取入库单数据失败:", error);
  }
};

// 分页查询数据
const getPage = async () => {
  try {
    let res = await getPageApi(
      page.value,
      pageSize.value,
      queryRef.value?.searchForm?.warehousingEntryId || '',
      queryRef.value?.searchForm?.supplierId || '',
      queryRef.value?.searchForm?.unloadEmpId || '',
      queryRef.value?.searchForm?.skuId || '',
      queryRef.value?.searchForm?.status || '',
    );
    warehousingEntryDetailsList.value = res.rows || []
    totalSize.value = res.total || 0
  } catch (error) {
    console.error("获取入库明细数据失败:", error);
  }
};

const handleSizeChange = (val) => {
  pageSize.value = val
  page.value = 1
  getPage()
}

const handleCurrentChange = (val) => {
  page.value = val
  getPage()
}

// 处理子组件的搜索事件
const handleSearch = () => {
  page.value = 1; // 搜索时重置到第一页
  getPage();
};

// 处理子组件的刷新事件
const handleRefreshZi = () => {
  handleRefresh();
};

// 刷新界面
const handleRefresh = () => {
  if (queryRef.value) {
    queryRef.value.searchForm.warehousingEntryId = '';
    queryRef.value.searchForm.supplierId = '';
    queryRef.value.searchForm.unloadEmpId = '';
    queryRef.value.searchForm.skuId = '';
  }
  page.value = 1;
  getPage();
};

// 处理编辑事件
const handleEdit = (row) => {
  editRef.value.editWarehousingEntryDetails(row)
}



// 处理状态修改事件(固定修改为分拣完成 5)
const handleStatusEdit =async (row) => {
   let res = await updateStatusApi({
      id: row.id,
      status: 5
    })
    if (res.code === 1) {
      ElMessage.success('分拣任务下发成功')
      handleRefresh() // 修改成功后刷新列表
    } else {
      ElMessage.error('操作失败')
    }
}



// 暴露方法给父组件使用
defineExpose({
  handleRefresh
})

onMounted(() => {
  getSupplierData();
  getWarehousingEntryData();
  getPage();
  getSkuAll();
});
</script>

<template>
  <div id="box">
    <!-- 添加 ref 绑定   搜索事件  刷新事件  刷新界面-->
    <Query ref="queryRef" @search="handleSearch" @shuaX="handleRefreshZi"/>
    
    <!-- 修改组件 -->
    <EditWarehousingEntryDetails ref="editRef" @refresh="handleRefreshEdit" />

    <el-table :data="warehousingEntryDetailsList" style="width: 100%">
      <el-table-column type="index" label="序号" width="90" />
      <el-table-column prop="skuId" label="规格sku" width="150">
        <template #default="scope">
          {{ skuListMap.get(scope.row.skuId) || scope.row.skuId }}
        </template>
      </el-table-column>
      <el-table-column prop="count" label="数量" width="140"/>
      <el-table-column prop="supplierId" label="供应商" width="140">
        <template #default="scope">
          {{ supplierMap.get(scope.row.supplierId) || scope.row.supplierId }}
        </template>
      </el-table-column>
      <el-table-column prop="price" label="单价" width="120"/>
      <el-table-column prop="warehousingEntryId" label="入库单" width="120">
        <template #default="scope">
          {{ warehousingEntryMap.get(scope.row.warehousingEntryId) || scope.row.warehousingEntryId }}
        </template>
      </el-table-column>
      <el-table-column prop="status" label="ᅟᅠ   状态" width="150">
        <template #default="scope">
          <el-tag :type="statusTypeMap[scope.row.status] || ''" size="large">
            {{ statusMap[scope.row.status] || scope.row.status }}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column prop="unloadEmpId" label="卸货员工ID" width="100"/>
      <el-table-column prop="unloadTime" label="卸货时间" width="180"/>
      
      <el-table-column label="操作" width="200" fixed="right">
        <template #default="scope">
           <el-button type="warning" size="mini" @click="handleStatusEdit(scope.row)">分拣完成</el-button>
           <el-button type="primary" size="mini" @click="handleStatusEdit(scope.row)">上架</el-button>
      
        </template>
      </el-table-column>
    </el-table>
   
    <br>
    
    <el-pagination
      v-model:current-page="page"
      v-model:page-size="pageSize"
      :page-sizes="[8, 16, 24, 32]"
      :small="false"
      :disabled="false"
      :background="true"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalSize"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
</template>

<style scoped>
div {
  opacity: 0.9;
}
</style>