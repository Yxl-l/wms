<script setup>
import { getPageApi, deleteApi } from "@/api/logistics";//分页查询c获取仓库数据
import Query from "./query.vue";
import { onMounted, ref, computed } from "vue";
import { ElMessage, ElMessageBox } from 'element-plus'
import EditCompany from '@/views/logistics/tb_logistics_company/updata.vue' 

const queryRef = ref();
const editRef = ref() // 修改组件的引用

// 分页相关
const deleteRef = ref('deleteRef')
const addOrUpdateRef = ref('addOrUpdateRef')
const companyList = ref([])
const page = ref(1)
const pageSize = ref(6)
const totalSize = ref(0)

//分页查询数据
const getPage = async () => {
  let res = await getPageApi(
    page.value,
    pageSize.value,
    queryRef.value?.searchForm?.name || '', // 添加默认值防止报错
  );
  companyList.value=res.rows
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
  editRef.value.editCompany(row)
}

// 处理删除事件
const handleDelete = (row) => {
  ElMessageBox.confirm(
    '确定要删除该物流公司吗？',
    '删除确认',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  ).then(async () => {
    try {
      // 注意：这里需要确保后端API返回的是一个对象，而不是布尔值
      const res = await deleteApi(row.id)
      // 根据你的API设计，可能需要检查res.data或者res.code来判断是否成功
      if (res) {
        ElMessage.success('删除成功')
        handleRefresh() // 删除成功后刷新列表
      } else {
        ElMessage.error('删除失败')
      }
    } catch (error) {
      console.error(error) // 添加错误日志
      ElMessage.error('删除异常')
    }
  }).catch(() => {
    // 用户取消删除
    ElMessage.info('已取消删除')
  })
}

// 处理子组件的搜索事件
const handleSearch = (searchParams) => {
  getPage(searchParams.name);
};

// 处理子组件的刷新事件
const handleRefreshZi = () => {
  handleRefresh();
};

//刷新界面
const handleRefresh = () => {
  getPage();
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
    <EditCompany ref="editRef" @refresh="handleRefreshEdit" />

    <el-table :data="companyList" style="width: 100%">
      <el-table-column type="index" label="序号" width="60px" />
      <el-table-column prop="companyName" label="物流公司名称" width="180px"/>
      <el-table-column prop="minPrice" label="物流运输最低费用" width="180px"/>
      <el-table-column prop="linkUser" label="联系人" width="180px"/>
      <el-table-column prop="linkePhone" label="联系人电话" width="180px"/>
      <el-table-column prop="saasId" label="SaaS标识" width="180px"/>
    
      <el-table-column fixed="right" label="操作" width="200px">
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