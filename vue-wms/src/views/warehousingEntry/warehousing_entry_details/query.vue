<script setup>
import { onMounted, ref } from 'vue'
import { getAllApi as getSupplierAllApi } from "@/api/supplier";
import { getAllApi as getWarehousingEntryAllApi } from "@/api/warehousingEntry";
import { getSkuAllApi } from "@/api/goods";


// 获取所有sku数据
const skuList = ref([]);
const getSkuAll = async () => {
  try {
    const res = await getSkuAllApi();
    skuList.value = res.data || [];
  } catch (error) {
    ElMessage.error("获取sku失败");
  }
};
// 获取全部供应商
const supplierList = ref([]);
const getSupplierAll = async () => {
  try {
    const res = await getSupplierAllApi();
    supplierList.value = res.data || [];
  } catch (error) {
    console.error("获取供应商数据失败:", error);
  }
};

// 获取全部入库单
const warehousingEntryList = ref([]);
const getWarehousingEntryAll = async () => {
  try {
    const res = await getWarehousingEntryAllApi();
    warehousingEntryList.value = res.data || [];
  } catch (error) {
    console.error("获取入库单数据失败:", error);
  }
};

// 定义 emit
const emit = defineEmits(['search'])

// 定义搜索表单的响应式数据对象
const searchForm = ref({
    warehousingEntryId: '',
    supplierId: '',
    unloadEmpId: '',
    skuId: '',
    status: '',
})

const handleSearch = () => {
    // 触发父组件的 search 事件，并传递 searchForm 数据
    emit('search', searchForm.value)
}

const handleRefreshZi = () => {
    // 触发父组件的 search 事件
    emit('shuaX')
}

// 重置表单函数
const reset = () => {
    searchForm.value.warehousingEntryId = ''      
    searchForm.value.supplierId = ''
    searchForm.value.unloadEmpId = ''
    searchForm.value. skuId= ''
    searchForm.value. status= ''
    handleRefreshZi()
}

// 暴露 searchForm 方法给父组件使用
defineExpose({ searchForm }) 

onMounted(() => {
   getSupplierAll()
   getWarehousingEntryAll()
    getSkuAll();
})
</script>

<template>
  <div id="box">
    <el-form :inline="true" :model="searchForm" >
     <el-form-item label="入库单" style="width: 180px;"> 
        <el-select 
                v-model="searchForm.warehousingEntryId" 
                placeholder="选择入库单"
                style="width: 100%"
              >
                <el-option
                  v-for="item in warehousingEntryList"
                  :key="item.id"
                  :label="item.batchNumber"
                  :value="item.id"
                />
              </el-select>
      </el-form-item>
      <el-form-item label="sku" style="width: 170px;"> 
        <el-select 
                v-model="searchForm.skuId" 
                placeholder="选择规格"
                style="width: 100%"
              >
                <el-option
                  v-for="item in skuList"
                  :key="item.id"
                  :label="item.title"
                  :value="item.id"
                />
              </el-select>
      </el-form-item>
      
      <el-form-item label="供应商"> 
        <el-select v-model="searchForm.supplierId" placeholder="选择供应商" style="width: 180px;" clearable>
          <el-option
            v-for="item in supplierList"
            :key="item.id"
            :label="item.supplierName"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="状态"> 
        <el-select v-model="searchForm.status" placeholder="选择状态" style="width: 120px;" clearable>
          <el-option label="未到货" :value="1" />
          <el-option label="已到货" :value="2" />
          <el-option label="已卸货" :value="3" />
          <el-option label="拣货中" :value="4" />
          <el-option label="已上架" :value="5" />
        </el-select>
      </el-form-item>
      <el-form-item label="卸货员工ID" style="width: 200px;"> 
        <el-input v-model="searchForm.unloadEmpId" placeholder="卸货员工ID" clearable />
      </el-form-item>
      
      <!-- 按钮区域 -->
      <el-form-item>
        <!-- 查询按钮，点击时调用 getPage 函数 -->
        <el-button type="primary" @click="handleSearch" class="bt1">查询</el-button>
        <!-- 重置按钮，点击时调用 reset 函数 -->
        <el-button @click="reset">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<style scoped>
  
</style>