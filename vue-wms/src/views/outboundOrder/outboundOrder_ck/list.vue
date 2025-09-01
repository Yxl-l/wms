<script setup>
import { getPageApi,updateApi } from "@/api/outboundOrder";
import Query from "./query.vue";
import { onMounted, ref } from "vue";
import { ElMessage, ElMessageBox } from 'element-plus'
//获取全部物流公司
import{getAllApi} from "@/api/logistics"
//全部物流公司用于映射
const LogisticsMap = ref(new Map())
const getLogistics = async () => {
  try {
    const res = await getAllApi();
    if (res.data) {
      res.data.forEach(item => {
        LogisticsMap.value.set(item.id, item.companyName);
      });
    }
  } catch (error) {
    console.error("获取供应商数据失败:", error);
  }
};

const dialogFormVisible = ref(false); //控制对话框的显示与隐藏
const carrierUnitDialogVisible = ref(false); // 承运单位设置对话框
const queryRef = ref();
const editRef = ref() // 修改组件的引用
const currentRow = ref({}) // 当前行数据

// 分页相关
const outboundOrderList = ref([])
const page = ref(1)
const pageSize = ref(99999)
const totalSize = ref(0)

// 承运单位表单数据
const carrierUnitForm = ref({
  id: '',
  status: 666,
  logisticsCompanyId: '',
  logisticsType: '' ,// 新增物流类型字段
  totalWeight: '',
})

// 物流类型映射
const logisticsTypeMap = {
  1: '省内',
  2: '普通国内',
  3: '边远地区'
}

// 状态映射和样式
const statusConfig = {
  7: { text: '未出库', type: 'danger' },
  8: { text: '已出库', type: 'primary' },
  9: { text: '已签收', type: 'success' }
}

const getPage = async () => {
  try {
    const res = await getPageApi(
      page.value,
      pageSize.value,
      queryRef.value?.searchForm?.customerName || '',
      queryRef.value?.searchForm?.invoiceCode || '',
      queryRef.value?.searchForm?.status || ''
    );
    // 只显示 status 为 7 或 8 的数据
    const filteredData = res.data.rows.filter(item => item.status === 7 || item.status === 8);
    outboundOrderList.value = filteredData || [];
    totalSize.value = filteredData.length || 0;
  } catch (err) {
    console.error(err);
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
    queryRef.value.searchForm.customerName = '';
    queryRef.value.searchForm.invoiceCode = ''
    queryRef.value.searchForm.status = ''
  }
  page.value = 1;
  getPage();
};

// 设置承运单位
const setCarrierUnit = (row) => {
  currentRow.value = row
  carrierUnitForm.value.id = row.id
  carrierUnitForm.value.logisticsCompanyId = row.logisticsCompanyId || ''
  carrierUnitForm.value.logisticsType = row.logisticsType || '' // 设置物流类型
  carrierUnitForm.value.totalWeight = row.totalWeight || ''
  carrierUnitDialogVisible.value = true
}

// 提交承运单位设置
const submitCarrierUnit = async () => {
  if (!carrierUnitForm.value.logisticsCompanyId) {
    ElMessage.warning("请选择承运单位")
    return
  }
  
  if (!carrierUnitForm.value.logisticsType) {
    ElMessage.warning("请选择物流类型")
    return
  }
  
  try {
    const res = await updateApi(carrierUnitForm.value)
    if (res.code === 1) {
      ElMessage.success("承运单位设置成功")
      carrierUnitDialogVisible.value = false
    
    } else {
      ElMessage.error("承运单位设置失败: " + res.msg)
    }
  } catch (error) {
    console.error(error)
    ElMessage.error("承运单位设置异常")
  }
  handleRefresh();
}

// 出库操作
const deliveryFromWarehouse = (row) => {
  ElMessageBox.confirm('确认要将该订单出库吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    try {
      const res = await updateApi({
        id: row.id,
        status: 8
      })
      if (res.code === 1) {
        ElMessage.success("出库成功")
       handleRefresh();
      } else {
        ElMessage.error("出库失败: " + res.msg)
      }
    } catch (error) {
      console.error(error)
      ElMessage.error("出库操作异常")
    }
  }).catch(() => {
    // 用户取消操作
  })

}

// 签收操作
const signFor = (row) => {
  ElMessageBox.confirm('确认该订单已签收吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    try {
      const res = await updateApi({
        id: row.id,
        status: 9
      })
      if (res.code === 1) {
        ElMessage.success("签收成功")
         handleRefresh();
      } else {
        ElMessage.error("签收失败: " + res.msg)
      }
    } catch (error) {
      console.error(error)
      ElMessage.error("签收操作异常")
    }
  }).catch(() => {
    // 用户取消操作
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
  getPage();
  getLogistics();
});
</script>

<template>
  <div id="box">
    <!-- 添加 ref 绑定   搜索事件  刷新事件  刷新界面-->
    <Query ref="queryRef" @search="handleSearch" @shuaX="handleRefreshZi"/>
    
    <!-- 承运单位设置对话框 -->
    <el-dialog 
      title="设置承运单位"
      style="width: 400px" 
      v-model="carrierUnitDialogVisible" 
      :append-to-body="true"
      top="5vh"
    >
      <el-form
        :model="carrierUnitForm"
        label-width="auto"
      >
        <el-form-item label="承运单位" prop="logisticsCompanyId">
          <el-select v-model="carrierUnitForm.logisticsCompanyId" placeholder="请选择承运单位" style="width: 100%">
            <el-option
              v-for="[id, name] in LogisticsMap"
              :key="id"
              :label="name"
              :value="id"
            />
          </el-select>
        </el-form-item>
        
        <el-form-item label="物流类型" prop="logisticsType">
          <el-select v-model="carrierUnitForm.logisticsType" placeholder="请选择物流类型" style="width: 100%">
            <el-option
              v-for="(label, value) in logisticsTypeMap"
              :key="value"
              :label="label"
              :value="Number(value)"
            />
          </el-select>
        </el-form-item>
      </el-form>

      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" @click="submitCarrierUnit">提交</el-button>
        </span>
        <span class="dialog-footer">
          <el-button @click="carrierUnitDialogVisible = false">取消</el-button>
        </span>
      </template>
    </el-dialog>
    


    <el-table :data="outboundOrderList" style="width: 100%">
      <el-table-column type="index" label="序号" width="80" />
      <el-table-column prop="invoiceCode" label="发货单号" width="150"/>
      <el-table-column prop="status" label="发货单状态" width="100">
        <template #default="scope">
          <el-tag :type="statusConfig[scope.row.status] ? statusConfig[scope.row.status].type : 'info'">
            {{ statusConfig[scope.row.status] ? statusConfig[scope.row.status].text : scope.row.status }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="customerName" label="客户名称" width="100"/>
            <el-table-column prop="price" label="承运费用" width="100">
        <template #default="scope">
          <span>{{ scope.row.price || '（暂无）' }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="logisticsCompanyId" label="物流公司" width="120">
        <template #default="scope">
          <span>{{ LogisticsMap.get(scope.row.logisticsCompanyId) || '（暂无）' }}</span>
        </template>
      </el-table-column>

      <el-table-column prop="logisticsNumber" label="物流单号" width="150">
        <template #default="scope">
          <span>{{ scope.row.logisticsNumber || '（暂无）' }}</span>
        </template>
      </el-table-column>
     <el-table-column prop="logisticsNumber" label="物流单号" width="150">
        <template #default="scope">
          <span>{{ scope.row.logisticsNumber || '（暂无）' }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="logisticsType" label="物流类型" width="100">
        <template #default="scope">
          <span>{{ logisticsTypeMap[scope.row.logisticsType] || '（暂无）' }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="totalWeight" label="总重量/kg" width="100"/>
      <el-table-column prop="totalVolume" label="总体积/m³" width="100"/>
      <el-table-column prop="totalCount" label="总数量" width="100"/>
      <el-table-column prop="empId" label="操作员工ID" width="120"/>
      <el-table-column prop="createBy" label="创建人账户" width="120"/>
      <el-table-column prop="createTime" label="创建时间" width="200"/>
      <el-table-column label="操作" width="280" fixed="right">
        <template #default="scope">
          <el-button type="primary" size="small" @click="setCarrierUnit(scope.row)">设置承运单位</el-button>
          <el-button type="warning" size="small" @click="deliveryFromWarehouse(scope.row)">出库</el-button>
          <el-button type="danger" size="small" @click="signFor(scope.row)">签收</el-button>
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