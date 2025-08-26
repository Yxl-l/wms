<script setup>
import { onMounted, ref } from 'vue'
import { getAllApi as getSupplierAllApi } from "@/api/supplier";
import { getSkuAllApi } from "@/api/goods";
import { getWarehouseAllApi } from "@/api/warehouse";

// 获取所有sku数据
const skuList = ref([]);
const getSkuAll = async () => {
  try {
    const res = await getSkuAllApi();
    skuList.value = res.data || [];
  } catch (error) {
    console.error("获取sku失败:", error);
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

// 获取全部仓库
const warehouseList = ref([]);
const getWarehouseAll = async () => {
  try {
    const res = await getWarehouseAllApi();
    warehouseList.value = res.data || [];
  } catch (error) {
    console.error("获取仓库数据失败:", error);
  }
};

// 定义 emit
const emit = defineEmits(['search'])

// 定义搜索表单的响应式数据对象
const searchForm = ref({
    warehouseId: '',
    skuId: '',
    status: ''
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
    searchForm.value.warehouseId = ''      
    searchForm.value.skuId = ''
    searchForm.value.status = ''
  
    handleRefreshZi()
}

// 暴露 searchForm 方法给父组件使用
defineExpose({ searchForm }) 

onMounted(() => {
   getSupplierAll()
   getWarehouseAll()
   getSkuAll();
})
</script>

<template>
  <div id="box">
    <el-form :inline="true" :model="searchForm" >
      <el-form-item label="仓库"> 
        <el-select v-model="searchForm.warehouseId" placeholder="选择仓库" style="width: 180px;" clearable>
          <el-option
            v-for="item in warehouseList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      
     <el-form-item label="SKU" style="width: 170px;"> 
        <el-select 
                v-model="searchForm.skuId" 
                placeholder="选择规格"
                style="width: 100%"
                clearable
              >
                <el-option
                  v-for="item in skuList"
                  :key="item.id"
                  :label="item.title"
                  :value="item.id"
                />
              </el-select>
      </el-form-item>
      
      <el-form-item label="状态"> 
        <el-select v-model="searchForm.status" placeholder="选择状态" style="width: 120px;" clearable>
          <el-option label="未上架" value="1" />
          <el-option label="已上架" value="2" />
        </el-select>
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