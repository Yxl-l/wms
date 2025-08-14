<script setup>
import { getPageApi,getPcdAllApi } from "@/api/supplier";
import Query from "./query.vue";
import { onMounted, ref, computed } from "vue";

const queryRef = ref();

// 分页相关
const deleteRef = ref('deleteRef')
const addOrUpdateRef = ref('addOrUpdateRef')
const userList = ref([])
const page = ref(1)
const pageSize = ref(10)
const totalSize = ref(0)
//分页查询数据
const getPage = async () => {
  let res = await getPageApi(
    page.value,
    pageSize.value,
    queryRef.value.searchForm.name,
    queryRef.value.searchForm.employeeId
  );
  userList.value=res.data.rows
  totalSize.value=res.data.total
};
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

onMounted(() => {
  handleRefresh();
});
</script>

<template>
  <div id="box">
    <H2>供应商列表</H2>
    <!-- 添加 ref 绑定 -->
    <Query ref="queryRef" @search="handleSearch" @shuaX="handleRefreshZi"/>



    <el-table :data="userList" style="width: 100%">
      <el-table-column type="index" label="序号" />
      <el-table-column prop="supplierName" label="供应商" />
      <!-- 使用映射表显示省份名称 -->
      <el-table-column label="所在省份">
        <template #default="scope">
          <!-- 
            通过Map查找对应名称，如果找不到则显示原始ID
            provinceMap.get(scope.row.provinceId): 查找省份名称
            || scope.row.provinceId: 查找失败时显示原始ID作为降级方案
          -->
          {{ provinceMap.get(scope.row.provinceId) || scope.row.provinceId }}
        </template>
      </el-table-column>
      <!-- 使用映射表显示城市名称 -->
      <el-table-column label="所在市区">
        <template #default="scope">
          <!-- 
            通过Map查找对应名称，如果找不到则显示原始ID
            cityMap.get(scope.row.cityCode): 查找城市名称
            || scope.row.cityCode: 查找失败时显示原始ID作为降级方案
          -->   
           {{ provinceMap.get(scope.row.cityCode) || scope.row.cityCode }}
        </template>
      </el-table-column>
      <el-table-column prop="address" label="具体地址" />
      <el-table-column prop="linkName" label="联系人" />
      <el-table-column prop="linkPhone" label="联系电话" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button type="primary" size="mini" @click="handleEdit(scope.row)"
            >编辑</el-button
          >
          <el-button type="danger" size="mini" @click="handleDelete(scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
      <br>
    <el-pagination
      v-model:current-page="page"
      v-model:page-size="pageSize"
      :page-sizes="[10, 20, 30, 40]"
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