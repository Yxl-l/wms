<script setup>
import { getPageApi, deleteApi, updateStatusApi } from "@/api/warehousingEntry";
import Query from "./query.vue";
import { onMounted, ref } from "vue";
import { ElMessage, ElMessageBox } from 'element-plus'
import EditWarehousingEntry from './edit.vue'
import { getWarehouseAllApi } from "@/api/warehouse";

const queryRef = ref();
const editRef = ref() // 修改组件的引用

// 状态修改弹窗相关
const statusDialogVisible = ref(false)
const currentRow = ref({})
const selectedStatus = ref()

// 分页相关
const warehousingEntryList = ref([])
const page = ref(1)
const pageSize = ref(8)
const totalSize = ref(0)

// 仓库映射
const warehouseMap = ref(new Map())

// 状态映射
const statusMap = {
  1: '未确认',
  2: '部分已确认',
  3: '全部确认',
  4: '拣货中',
  5: '已上架'
}

// 状态类型映射（用于el-tag的type属性）
const statusTypeMap = {
  1: 'danger',
  2: 'warning',
  3: 'success',
  4: 'primary',
  5: 'success'
}

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

// 打开状态修改弹窗
const openStatusDialog = (row) => {
  currentRow.value = { ...row }
  selectedStatus.value = row.status
  statusDialogVisible.value = true
}

// 关闭状态修改弹窗
const closeStatusDialog = () => {
  statusDialogVisible.value = false
  currentRow.value = {}
  selectedStatus.value = undefined
}

// 确认修改状态
const confirmStatusUpdate = async () => {
  if (selectedStatus.value === currentRow.value.status) {
    ElMessage.info('状态未发生变化')
    closeStatusDialog()
    return
  }

  try {
    const res = await updateStatusApi({
      id: currentRow.value.id,
      status: selectedStatus.value
    })
    
    if (res.code == 1) {
      ElMessage.success('状态修改成功')
      
      // 更新表格中的数据
      const index = warehousingEntryList.value.findIndex(item => item.id === currentRow.value.id)
      if (index !== -1) {
        const updatedRow = { ...currentRow.value, status: selectedStatus.value }
        warehousingEntryList.value.splice(index, 1, updatedRow)
      }
      
      closeStatusDialog()
    } else {
      ElMessage.error('状态修改失败')
    }
  } catch (error) {
    console.error(error)
    ElMessage.error('状态修改异常')
  }
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
      <el-table-column prop="batchNumber" label="批次号" width="100"/>
      <el-table-column prop="consignorId" label="货主ID" width="100"/>
      <el-table-column prop="warehouseId" label="仓库" width="150">
        <template #default="scope">
          {{ warehouseMap.get(scope.row.warehouseId) || scope.row.warehouseId }}
        </template>
      </el-table-column>
      <el-table-column prop="totalCount" label="商品数量" width="100"/>
      <el-table-column prop="predictedArrivalTime" label="预测到达时间" width="250"/>
      <!-- <el-table-column prop="damageCount" label="破损数量" width="100"/>
      <el-table-column prop="surpassCount" label="超出数量" width="100"/>
      <el-table-column prop="lackCount" label="缺少数量" width="100"/> -->
      <el-table-column prop="status" label="状态" width="120">
        <template #default="scope">
          <el-tag :type="statusTypeMap[scope.row.status] || ''" size="large">
            {{ statusMap[scope.row.status] || scope.row.status }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="250"/>
      <el-table-column label="ᅟᅠ   ᅟᅠ   操作" width="280" fixed="right">
        <template #default="scope">
          <el-button type="warning" size="mini" @click="openStatusDialog(scope.row)">修改状态</el-button>
          <el-button type="primary" size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <!-- <el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button> -->
        </template>
      </el-table-column>
    </el-table>
    
    <!-- 状态修改弹窗 -->
    <el-dialog title="修改状态" v-model="statusDialogVisible" width="500px" :before-close="closeStatusDialog">
      <el-form label-width="100px">
        <el-form-item label="当前状态：">
          <el-tag :type="statusTypeMap[currentRow.status] || ''">
            {{ statusMap[currentRow.status] || currentRow.status }}
          </el-tag>
        </el-form-item>
        
        <el-form-item label="修改为：">
          <el-radio-group v-model="selectedStatus">
            <el-radio 
              v-for="(label, key) in statusMap" 
              :key="key" 
              :label="parseInt(key)">
              {{ label }}
            </el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="closeStatusDialog">取 消</el-button>
          <el-button type="primary" @click="confirmStatusUpdate">确 定</el-button>
        </span>
      </template>
    </el-dialog>
    
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