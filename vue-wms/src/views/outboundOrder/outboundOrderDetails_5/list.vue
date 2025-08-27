<script setup>
import { getPageApi,updateOutboundOrderDetailsApi } from "@/api/outboundOrderDetails";
import { updateApi } from "@/api/outboundOrder";
import Query from "./query.vue";
import { onMounted, ref } from "vue";
import { ElMessage, ElMessageBox } from 'element-plus'


const queryRef = ref();
const editRef = ref() // 修改组件的引用

// 分页相关
const outboundOrderList = ref([])
const page = ref(1)
const pageSize = ref(8)
const totalSize = ref(0)

// 状态映射和样式
const statusConfig = {

  2: { text: '已拣货待复检', type: 'primary' },
}

// 分页查询数据
const getPage = async () => {
  try {
    let res = await getPageApi(
      page.value,
      pageSize.value,
      queryRef.value?.searchForm?.customerName || '',
      queryRef.value?.searchForm?.invoiceCode || '',
      queryRef.value?.searchForm?.title || '',
      queryRef.value.searchForm.pickStatus=2,
         // title参数
    );
   
      outboundOrderList.value = res.data.rows;
    totalSize.value = res.data.total || 0
  } catch (error) {
    console.error("获取出库单数据失败:", error);
    ElMessage.error("获取出库单数据失败");
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
    queryRef.value.searchForm.invoiceCode = '';
    queryRef.value.searchForm.title = '';
  }
  page.value = 1;
  getPage();
};

// 处理查看事件
const handleView =async (row) => {
  let data = {
    id:row.odId,
    pickStatus:0,
    pickEmpId:1,
    pickTime:new Date()
  }
  let data1 = {
    id:row.outboundOrderId,
    status:5
  }

  let res = await updateOutboundOrderDetailsApi(data)
  if(res.code==1){
    ElMessage.success("拣货成功")
  }else{
    ElMessage.error("拣货失败")
  }
  let res1 = await updateApi(data1)
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
});
</script>

<template>
  <div id="box">
    <!-- 添加 ref 绑定   搜索事件  刷新事件  刷新界面-->
    <Query ref="queryRef" @search="handleSearch" @shuaX="handleRefreshZi"/>
    


    <el-table :data="outboundOrderList" style="width: 100%">
      <el-table-column type="index" label="序号" width="80" />
      <el-table-column prop="invoiceCode" label="发货单号" width="150"/>
      <el-table-column prop="customerName" label="客户名称" width="80"/>
      <el-table-column prop="id" label="商品编码" width="80"/>
       <el-table-column prop="pickStatus" label="ᅟᅠ 状态" width="120">
        <template #default="scope">
          <el-tag :type="statusConfig[scope.row.pickStatus] ? statusConfig[scope.row.pickStatus].type : 'info'">
            {{ statusConfig[scope.row.pickStatus] ? statusConfig[scope.row.pickStatus].text : scope.row.pickStatus }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="title" label="商品名称" width="120"/>
      <el-table-column prop="spuId" label="规格编码" width="80"/>
      <el-table-column prop="code" label="条码" width="120"/>
      <el-table-column prop="maxCount" label="订单数量" width="100"/>
      <el-table-column prop="pickingCount" label="待检货数量" width="100"/>
      <el-table-column prop="outCount" label="已拣货数量" width="100"/>
      <el-table-column prop="weight" label="货重量" width="100"/>
      <el-table-column prop="volume" label="货体积" width="100"/>
     
      <el-table-column prop="createBy" label="创建人" width="120">
        蔡徐坤
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="200"/>
      <el-table-column label="操作" width="100" fixed="right">
        <template #default="scope">
          <el-button type="success"  @click="handleView(scope.row)">复拣</el-button>
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