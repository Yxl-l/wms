<script setup>
import { getPageApi,updateOutboundOrderPackageApi } from "@/api/outboundOrderPackage";
import { updateApi } from "@/api/outboundOrder";
import Query from "./query.vue";
import { onMounted, ref } from "vue";
import { ElMessage, ElMessageBox } from 'element-plus'


const dialogFormVisible = ref(false); //控制对话框的显示与隐藏
const queryRef = ref();
const editRef = ref() // 修改组件的引用
const currentRow = ref({}) // 当前行数据

// 分页相关
const outboundOrderList = ref([])
const page = ref(1)
const pageSize = ref(8)
const totalSize = ref(0)

// 打包表单数据
const packageForm = ref({
  id: '',
  packageStatus: 1,
  packageEmpId: 1,
  packageCount: '',
  packageTime: ''
})

// 状态映射和样式
const statusConfig = {

  1: { text: '未打包', type: 'danger' },
  2: { text: '已打包', type: 'primary' },
}

// 分页查询数据
const getPage = async () => {
  try {
    let res = await getPageApi(
      page.value,
      pageSize.value,
      queryRef.value?.searchForm?.title || '',
      queryRef.value?.searchForm?.packageStatus || '',
      queryRef.value?.searchForm?.invoiceCode || '',
      queryRef.value?.searchForm?.packageCode || '',


         // title参数
    );
   
      outboundOrderList.value = res.rows;
    totalSize.value = res.total || 0
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
    queryRef.value.searchForm.packageStatus = '';
 
    queryRef.value.searchForm.title = '';
    queryRef.value.searchForm.invoiceCode = ''
    queryRef.value.searchForm.packageCode = ''
  }
  page.value = 1;
  getPage();
};

// 打开打包弹窗
const retest = (row) => {
  currentRow.value = row
  packageForm.value.id = row.id
  packageForm.value.packageCount = ''
  dialogFormVisible.value = true
}

// 提交打包
const updata = async () => {
  if (!packageForm.value.packageCount) {
    ElMessage.warning("请输入打包数量")
    return
  }
  
  // 检查打包数量是否超过待打包数量
  const pendingPackageCount = currentRow.value.maxCount - currentRow.value.packageCount
  if (packageForm.value.packageCount > pendingPackageCount) {
    ElMessage.warning("打包数量不能超过待打包数量")
    return
  }
  console.log(currentRow.value.outboundOrderId)
  // 准备打包数据
  const data = {
    id: packageForm.value.id,
    packageStatus: 2, // 已打包
    packageEmpId: packageForm.value.packageEmpId,
    packageCount: packageForm.value.packageCount,
    packageTime: new Date()
  }
  
  // 准备出库单更新数据
  const data1 = {
    id: currentRow.value.outboundOrderId,
    status: 6 // 已打包待称重
  }

  try {
    let res = await updateOutboundOrderPackageApi(data)
    if(res.code==1){
      ElMessage.success("打包成功")
      dialogFormVisible.value = false
    }else{
      ElMessage.error("打包失败")
      return
    }
    //修改出库单状态为6已打包待称重
    await updateApi(data1)
    // 刷新界面
    getPage()
  } catch (error) {
    ElMessage.error("打包操作失败: " + error.message)
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
  getPage();
});
</script>

<template>
  <div id="box">
    <!-- 添加 ref 绑定   搜索事件  刷新事件  刷新界面-->
    <Query ref="queryRef" @search="handleSearch" @shuaX="handleRefreshZi"/>
    
     <el-dialog 
      style="width: 400px" 
      v-model="dialogFormVisible" 
      :append-to-body="true"
      top="5vh"
      custom-class="top-dialog"
    >
      <el-form
        :model="packageForm"
        label-width="auto"
      >
            <el-form-item label="待打包数量">
              <el-input :value="currentRow.maxCount - currentRow.packageCount" disabled />
            </el-form-item>
            <el-form-item label="打包数量" prop="packageCount">
              <el-input v-model="packageForm.packageCount" />
            </el-form-item>
      </el-form>

      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" @click="updata">提交</el-button>
        </span>
        <span class="dialog-footer">
          <el-button type="success" @click="dialogFormVisible = false">关闭</el-button>
        </span>
      </template>
    </el-dialog>


   <el-table :data="outboundOrderList" style="width: 100%">
      <el-table-column type="index" label="序号" width="80" />
       <el-table-column prop="packageStatus" label="ᅟᅠ 状态" width="120">
        <template #default="scope">
          <el-tag :type="statusConfig[scope.row.packageStatus] ? statusConfig[scope.row.packageStatus].type : 'info'">
            {{ statusConfig[scope.row.packageStatus] ? statusConfig[scope.row.packageStatus].text : scope.row.packageStatus }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="invoiceCode" label="发货单号" width="150"/>
      <el-table-column prop="customerName" label="客户名称" width="80"/>
      <el-table-column prop="skuId" label="商品编码" width="80"/>
       <el-table-column prop="packageCode" label="打包单单号" width="150"/>
      <el-table-column prop="title" label="商品名称" width="120"/>
      <el-table-column prop="spuId" label="规格编码" width="80"/>
      <el-table-column prop="code" label="条码" width="120"/>
      <el-table-column prop="maxCount" label="订单数量" width="100"/>
      <el-table-column  label="待打包数量" width="100">
        <template #default="scope">
          <el-tag >{{ scope.row.maxCount - scope.row.packageCount }}</el-tag>
        </template>
      </el-table-column>
     
      <el-table-column prop="packageCount" label="已打包数量" width="100"/>
      <el-table-column prop="weight" label="货重量/kg" width="100"/>
      <el-table-column prop="volume" label="货体积/m³" width="100"/>
     
      <el-table-column prop="createBy" label="创建人" width="120"/>
      <el-table-column prop="createTime" label="创建时间" width="200"/>
      <el-table-column label="操作" width="100" fixed="right">
        <template #default="scope">
          <el-button type="success"  @click="retest(scope.row)">打包</el-button>
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