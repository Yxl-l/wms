<script setup>
import { getPageApi, deleteApi } from "@/api/warehousingEntry";
import Query from "./query.vue";
import { onMounted, ref } from "vue";
import { ElMessage, ElMessageBox } from 'element-plus'
import EditWarehousingEntry from './edit.vue'
import { getWarehouseAllApi } from "@/api/warehouse";

const queryRef = ref();
const editRef = ref() // 修改组件的引用

// 分页相关
const warehousingEntryList = ref([])
const page = ref(1)
const pageSize = ref(8)
const totalSize = ref(0)

// 仓库映射
const warehouseMap = ref(new Map())

// 获取所有仓库数据用于映射
const getWarehouseData = async () => {
  try {
    const res = await getWarehouseAllApi();
    if (res.data) {
      res.data.forEach(item => {
        warehouseMap.value.set(item.id, item.name);
      });
    }
  } catch (error) {
    console.error("获取仓库数据失败:", error);
  }
};

// 分页查询数据
const getPage = async () => {
  try {
    let res = await getPageApi(
      page.value,
      pageSize.value,
      queryRef.value?.searchForm?.batchNumber || '',
      queryRef.value?.searchForm?.status || ''
    );
    warehousingEntryList.value = res.rows || []
    totalSize.value = res.total || 0
  } catch (error) {
    console.error("获取入库单数据失败:", error);
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
    queryRef.value.searchForm.batchNumber = '';
    queryRef.value.searchForm.status = '';
  }
  page.value = 1;
  getPage();
};

// 处理编辑事件
const handleEdit = (row) => {
  editRef.value.editWarehousingEntry(row)
}

// 处理删除事件
const handleDelete = (row) => {
  ElMessageBox.confirm(
    '确定要删除该入库单吗？',
    '删除确认',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  ).then(async () => {
    try {
      const res = await deleteApi(row.id)
      if (res.code == 1) {
        ElMessage.success('删除成功')
        handleRefresh() // 删除成功后刷新列表
      } else {
        ElMessage.error('删除失败')
      }
    } catch (error) {
      console.error(error)
      ElMessage.error('删除异常')
    }
  }).catch(() => {
    ElMessage.info('已取消删除')
  })
}

// 修改完成后刷新列表
const handleRefreshEdit = () => {
  handleRefresh()
}

// 暴露方法给父组件使用
defineExpose({
  handleRefresh
})

onMounted(() => {
  getWarehouseData();
  getPage();
});
</script>

<template>
  <div id="box">
    <!-- 添加 ref 绑定   搜索事件  刷新事件  刷新界面-->
    <Query ref="queryRef" @search="handleSearch" @shuaX="handleRefreshZi"/>
    
    <!-- 修改组件 -->
    <EditWarehousingEntry ref="editRef" @refresh="handleRefreshEdit" />

    <el-table :data="warehousingEntryList" style="width: 100%">
      <el-table-column type="index" label="序号" width="80" />
      <el-table-column prop="batchNumber" label="批次号" width="120"/>
      <el-table-column prop="consignorId" label="货主ID" width="120"/>
      <el-table-column prop="warehouseId" label="仓库" width="150">
        <template #default="scope">
          {{ warehouseMap.get(scope.row.warehouseId) || scope.row.warehouseId }}
        </template>
      </el-table-column>
      <el-table-column prop="totalCount" label="商品数量" width="100"/>
      <el-table-column prop="predictedArrivalTime" label="预测到达时间" width="300"/>
      <el-table-column prop="createTime" label="创建时间" width="300"/>
      <el-table-column label="操作" width="200" fixed="right">
        <template #default="scope">
          <el-button type="primary" size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
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