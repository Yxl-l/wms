<script setup>
import { onMounted, ref, watch } from 'vue'
import { getWarehouseAllApi, getAreaByWarehouseIdApi } from "@/api/warehouse";

//获取全部仓库
const warehouseList = ref([]);
const areaList = ref([]);

const getWarehouseAll = async () => {
  try {
    const res = await getWarehouseAllApi();
    warehouseList.value = res.data || [];
  } catch (error) {
    console.error("获取仓库数据失败:", error);
  }
};

// 根据仓库ID获取库区
const getAreaByWarehouseId = async (warehouseId) => {
  try {
    if (!warehouseId) {
      areaList.value = [];
      return;
    }
    const res = await getAreaByWarehouseIdApi(warehouseId);
    areaList.value = res.rows || [];
  } catch (error) {
    console.error("获取库区数据失败:", error);
  }
};

// 定义 emit
const emit = defineEmits(['search'])

// 定义搜索表单的响应式数据对象
const searchForm = ref({
    locaCode: '',           
    areaId: '',
    warehouseId: ''
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
    searchForm.value.locaCode = ''      
    searchForm.value.areaId = ''
    searchForm.value.warehouseId = ''
    handleRefreshZi()
}

// 监听仓库选择变化
watch(() => searchForm.value.warehouseId, (newVal) => {
  getAreaByWarehouseId(newVal);
  searchForm.value.areaId = ''; // 重置库区选择
})

// 暴露 searchForm 方法给父组件使用
defineExpose({ searchForm }) 

onMounted(() => {
   getWarehouseAll()
})
</script>

<template>
  <div id="box">
    <el-form :inline="true" :model="searchForm" >
      <el-form-item label="库位编号"> 
        <el-input v-model="searchForm.locaCode" placeholder="请输入库位编号" clearable />
      </el-form-item>
      
      <el-form-item label="仓库"> 
        <el-select v-model="searchForm.warehouseId" placeholder="请选择仓库" style="width: 200px;" clearable>
          <el-option
            v-for="item in warehouseList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      
      <el-form-item label="库区"> 
        <el-select v-model="searchForm.areaId" placeholder="请选择库区" style="width: 200px;" clearable>
          <el-option
            v-for="item in areaList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      
      <!-- 按钮区域 -->
      <el-form-item>
        <!-- 查询按钮，点击时调用 getPage 函数 -->
        <el-button type="primary" @click="handleSearch">查询</el-button>
        <!-- 重置按钮，点击时调用 reset 函数 -->
        <el-button @click="reset">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>