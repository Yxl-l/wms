<!-- 引入所需的功能模块 -->
<script setup>
// 从 vue 中引入需要的响应式和生命周期函数
import { onMounted, ref, computed } from 'vue'
// 引入获取地址数据的 API 函数
import { getPcdApi } from '@/api/supplier'

// 定义 emit
const emit = defineEmits(['search'])

// 定义搜索表单的响应式数据对象
const searchForm = ref({
    name: '',           
    employeeId: []  
})
const handleSearch = () => {
    // 触发父组件的 search 事件，并传递 searchForm 数据
    emit('search', searchForm.value)
}
const handleRefreshZi = () => {
    // 触发父组件的 search 事件
    emit('shuaX')
}


// 存储从 API 获取的原始地址数据（树形结构）
const pcdData = ref([])

// 计算属性：将原始树形数据转换为 Element Plus 级联选择器所需的格式
const cascaderOptions = computed(() => {
    // 遍历所有省份数据
    return pcdData.value.map(province => ({
        value: province.id,      // 级联选择器的值（省份ID）
        label: province.name,    // 级联选择器显示的文本（省份名称）
        // 处理城市子级数据
        children: province.children.map(city => ({
            value: city.id,      // 城市ID
            label: city.name,    // 城市名称
            // 处理区域子级数据
            children: city.children.map(district => ({
                value: district.id,    // 区域ID
                label: district.name   // 区域名称
            }))
        }))
    }))
})

// 获取地址数据
const getPcd = async() => {
    const res = await getPcdApi()
    pcdData.value = res.data
}
// 重置表单函数
const reset = () => {
    searchForm.value.name = ''      
    searchForm.value.employeeId = ''
    handleRefreshZi()
}




// 暴露 searchForm 方法给父组件使用
defineExpose({ searchForm }) 



onMounted(() => {
    getPcd() 
})

</script>

<template>
  <div id="box">
    <el-form :inline="true" :model="searchForm" >
      <el-form-item label="仓库名"> 
        <el-input v-model="searchForm.name" placeholder="请输入仓库名" clearable />
      </el-form-item>
      <el-form-item label="选择仓库地址">
        <!-- 级联选择器：用于选择省市区 -->
        <el-cascader
          v-model="searchForm.employeeId"
          :options="cascaderOptions"
          :props="{ 
            expandTrigger: 'hover',    // 触发展开下一级的方式：鼠标悬停
            value: 'value',            // 指定选项的值字段
            label: 'label',            // 指定选项的标签字段
            children: 'children',       // 指定选项的子级字段
             emitPath: false  // 只返回最后一个选中节点的值
          }"
          placeholder="请选择仓库地址"
          clearable
        />
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