<script setup>
import { getPageApi, deleteApi } from "@/api/warehousingEntryDetailsSort";
import Query from "./query.vue";
import { onMounted, ref } from "vue";
import { ElMessage, ElMessageBox } from 'element-plus'
import EditWarehousingEntryDetailsSort from './edit.vue'
import { getAllApi as getWarehousingEntryDetailsAllApi } from "@/api/warehousingEntryDetails";
import { getSkuAllApi } from "@/api/goods";

const queryRef = ref();
const editRef = ref() // 修改组件的引用

// 状态映射
const statusMap = {
  1: '分拣中',
  2: '已完成分拣',
  3: '已上架'
}

// 状态类型映射（用于el-tag的type属性）
const statusTypeMap = {
  1: 'primary',
  2: 'success',
  3: 'success'
}

// 分页相关
const warehousingEntryDetailsSortList = ref([])
const page = ref(1)
const pageSize = ref(8)
const totalSize = ref(0)

// 数据映射
const warehousingEntryDetailsMap = ref(new Map())
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

// 获取所有入库单明细数据用于映射
const getWarehousingEntryDetailsData = async () => {
  try {
    const res = await getWarehousingEntryDetailsAllApi();
    if (res.data) {
      res.data.forEach(item => {
        warehousingEntryDetailsMap.value.set(item.id, item.id);
      });
    }
  } catch (error) {
    console.error("获取入库单明细数据失败:", error);
  }
};

// 分页查询数据
const getPage = async () => {
  try {
    let res = await getPageApi(
      page.value,
      pageSize.value,
      queryRef.value?.searchForm?.warehousingEntryDetailsId || '',
      queryRef.value?.searchForm?.skuId || '',
      queryRef.value?.searchForm?.status || '',
    );
    warehousingEntryDetailsSortList.value = res.rows || []
    totalSize.value = res.total || 0
  } catch (error) {
    console.error("获取分拣明细数据失败:", error);
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
    queryRef.value.searchForm.warehousingEntryDetailsId = '';
    queryRef.value.searchForm.skuId = '';
    queryRef.value.searchForm.status = '';
  }
  page.value = 1;
  getPage();
};

// 处理编辑事件
const handleEdit = (row) => {
  editRef.value.editWarehousingEntryDetailsSort(row)
}

// 处理删除事件
const handleDelete = (row) => {
  ElMessageBox.confirm(
    '确定要删除该分拣明细吗？',
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
  getWarehousingEntryDetailsData();
  getPage();
  getSkuAll();
});
</script>

<template>
  <div id="box">
    <!-- 添加 ref 绑定   搜索事件  刷新事件  刷新界面-->
    <Query ref="queryRef" @search="handleSearch" @shuaX="handleRefreshZi"/>
    
    <!-- 修改组件 -->
    <EditWarehousingEntryDetailsSort ref="editRef" @refresh="handleRefreshEdit" />

    <el-table :data="warehousingEntryDetailsSortList" style="width: 100%">
      <el-table-column type="index" label="序号" width="80" />
      <el-table-column prop="warehousingEntryDetailsId" label="入库单明细ID" width="120">
        <template #default="scope">
          {{ warehousingEntryDetailsMap.get(scope.row.warehousingEntryDetailsId) || scope.row.warehousingEntryDetailsId }}
        </template>
      </el-table-column>
      <el-table-column prop="skuId" label="规格" width="150">
        <template #default="scope">
          {{ skuListMap.get(scope.row.skuId) || scope.row.skuId }}
        </template>
      </el-table-column>
      <el-table-column prop="count" label="分拣数量" width="100"/>
      <el-table-column prop="damageCount" label="破损个数" width="100"/>
      <el-table-column prop="surpassCount" label="超出数量" width="100"/>
      <el-table-column prop="lackCount" label="缺少数量" width="100"/>
      <el-table-column prop="status" label="状态" width="120">
        <template #default="scope">
          <el-tag :type="statusTypeMap[scope.row.status] || ''" size="large">
            {{ statusMap[scope.row.status] || scope.row.status }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="180"/>
      
      <el-table-column label="操作" width="150" fixed="right">
        <template #default="scope">
          <el-button type="primary" size="small" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button type="danger" size="small" @click="handleDelete(scope.row)">删除</el-button>
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