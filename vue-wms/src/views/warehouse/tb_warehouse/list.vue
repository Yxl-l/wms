<script setup>
import { getPcdAllApi } from "@/api/supplier";//获取地区
import { getPageApi, deleteWarehouseApi } from "@/api/warehouse";//分页查询获取仓库数据
import Query from "./query.vue";
import { onMounted, ref, computed } from "vue";
import { ElMessage, ElMessageBox } from 'element-plus'
import EditWarehouse from './edit.vue'

const queryRef = ref();
const editRef = ref() // 修改组件的引用

// 分页相关
const deleteRef = ref('deleteRef')
const addOrUpdateRef = ref('addOrUpdateRef')
const warehouseList = ref([])
const page = ref(1)
const pageSize = ref(6)
const totalSize = ref(0)

//分页查询数据
const getPage = async () => {
  let res = await getPageApi(
    page.value,
    pageSize.value,
    queryRef.value.searchForm.name,
    queryRef.value.searchForm.employeeId
  );
  warehouseList.value=res.rows
  totalSize.value=res.total
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

// 处理编辑事件
const handleEdit = (row) => {
  editRef.value.editWarehouse(row)
}

// 处理删除事件
const handleDelete = (row) => {
  ElMessageBox.confirm(
    '确定要删除该仓库吗？',
    '删除确认',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  ).then(async () => {
    try {
      const res = await deleteWarehouseApi(row.id)
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

// 城市列表普通结构
const PcdAllList = ref([])
// 获取城市列表普通结构
const getPcdAllList = async () => {
  let res = await getPcdAllApi();
  PcdAllList.value = res.data;
 
};

/**
 * 创建省份ID到名称的映射表
 * 使用computed确保响应式更新和性能优化
 * 当PcdAllList变化时自动重新计算
 */
const provinceMap = computed(() => {
  // 创建Map数据结构，提供O(1)时间复杂度的查找性能
  const map = new Map();
  // 确保PcdAllList已经加载且不为空
  if (PcdAllList.value && PcdAllList.value.length > 0) {
    // 遍历所有省份数据，建立ID到名称的映射关系
    PcdAllList.value.forEach(province => {
      map.set(province.id, province.name);
    });
  }
  return map;
});

// 处理子组件的搜索事件
const handleSearch = (searchParams) => {
  getPage(searchParams.name, searchParams.employeeId);
};

// 处理子组件的刷新事件
const handleRefreshZi = () => {
  handleRefresh();
};

//刷新界面
const handleRefresh = () => {
  getPage();
  getPcdAllList();
};

// 修改完成后刷新列表
const handleRefreshEdit = () => {
  handleRefresh()
}

// 暴露方法给父组件使用
defineExpose({
  handleRefresh
})

onMounted(() => {
  handleRefresh();
});
</script>

<template>
  <div id="box">
    <!-- 添加 ref 绑定   搜索事件  刷新事件  刷新界面-->
    <Query ref="queryRef" @search="handleSearch" @shuaX="handleRefreshZi"/>
    
    <!-- 修改组件 -->
    <EditWarehouse ref="editRef" @refresh="handleRefreshEdit" />

    <el-table :data="warehouseList" style="width: 100%">
      <el-table-column type="index" label="序号" width="60px" />
      <el-table-column prop="name" label="仓库名称" width="180px"/>
      <!-- 使用映射表显示省份名称 -->
      <el-table-column label="所在省份" width="80px">
        <template #default="scope">
          {{ provinceMap.get(scope.row.provinceId) || scope.row.provinceId }}
        </template>
      </el-table-column>
      <!-- 使用映射表显示城市名称 -->
      <el-table-column label="所在市区" width="80px">
        <template #default="scope">
          {{ provinceMap.get(scope.row.cityId) || scope.row.cityId }}
        </template>
      </el-table-column>
      <el-table-column prop="address" label="具体地址" width="200px" />
      <el-table-column prop="location" label="经纬度" />
      <el-table-column prop="personChargeName" label="负责人名字" width="100px"/>
      <el-table-column prop="saasId" label="SaaS标识" />
      <el-table-column prop="personChargeId" label="负责人ID" />
      <el-table-column label="操作" width="200px">
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
      :page-sizes="[6, 16, 24, 32]"
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
div{
  opacity: 0.9;
}
</style>