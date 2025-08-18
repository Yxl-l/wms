<script setup>
import { getPageAreaApi, getDictApi, getWarehouseAllApi, deleteAreaApi, getAreaDetailApi } from "@/api/warehouse";
import Query from "../tb_warehouse_area/query.vue";
import { onMounted, ref } from "vue";
import { ElMessage, ElMessageBox } from 'element-plus'
import EditArea from './edit.vue'

const queryRef = ref();
const editRef = ref() // 修改组件的引用

// 分页相关
const deleteRef = ref('deleteRef')
const addOrUpdateRef = ref('addOrUpdateRef')
const warehouseAreaList = ref([])
const page = ref(1)
const pageSize = ref(8)
const totalSize = ref(0)

// 数据字典映射
const categoryMap = ref(new Map())
const warehouseMap = ref(new Map())

// 获取库区类型字典数据
const getDictData = async () => {
  try {
    const res = await getDictApi();
    if (res.data) {
      // 根据返回的数据结构，假设数据在res.data中
      res.data.forEach(item => {
        // 假设字典项有code和name字段
        categoryMap.value.set(item.dictCode, item.dictValue);
      });
    }
  } catch (error) {
    console.error("获取字典数据失败:", error);
  }
};

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
    let res = await getPageAreaApi(
      page.value,
      pageSize.value,
      queryRef.value.searchForm.warehouseId,
      queryRef.value.searchForm.name
    );
    warehouseAreaList.value = res.rows || []
    totalSize.value = res.total || 0
  } catch (error) {
    console.error("获取库区数据失败:", error);
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
    queryRef.value.searchForm.name = '';
    queryRef.value.searchForm.warehouseId = '';
  }
  page.value = 1;
  getPage();
};

// 处理编辑事件
const handleEdit = (row) => {
  editRef.value.editArea(row)
}

// 处理删除事件
const handleDelete = (row) => {
  ElMessageBox.confirm(
    '确定要删除该库区吗？',
    '删除确认',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  ).then(async () => {
    try {
      const res = await deleteAreaApi(row.id)
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
  getDictData();
  getWarehouseData();
  getPage();
});
</script>

<template>
  <div id="box">
    <!-- 添加 ref 绑定   搜索事件  刷新事件  刷新界面-->
    <Query ref="queryRef" @search="handleSearch" @shuaX="handleRefreshZi"/>
    
    <!-- 修改组件 -->
    <EditArea ref="editRef" @refresh="handleRefreshEdit" />

    <el-table :data="warehouseAreaList" style="width: 100%">
      <el-table-column type="index" label="序号" width="80" />
      <el-table-column prop="name" label="库区名称" />
      <el-table-column prop="warehouseId" label="仓库名称">
        <template #default="scope">
          {{ warehouseMap.get(scope.row.warehouseId) || scope.row.warehouseId }}
        </template>
      </el-table-column>
      <el-table-column prop="category" label="库区类型">
        <template #default="scope">
          {{ categoryMap.get(scope.row.category) || scope.row.category }}
        </template>
      </el-table-column>
      <el-table-column prop="saasId" label="SaaS标识" />
      <el-table-column label="操作" width="200">
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