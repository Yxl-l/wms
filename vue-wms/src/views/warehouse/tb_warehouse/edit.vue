<script setup>
import { ref, onMounted, computed } from 'vue'
import { ElMessage } from 'element-plus'
import { getPcdApi } from '@/api/supplier'
import { updateWarehouseApi, getWarehouseDetailApi } from '@/api/warehouse'

// 定义响应式数据
const dialogFormVisible = ref(false)//控制对话框的显示与隐藏
const title = ref('修改仓库')
const ruleFormRef = ref(null)

// 仓库表单数据
const warehouseForm = ref({
  id: '',
  name: '',
  cityId: '',
  cityName: '',
  provinceId: '',
  provinceName: '',
  address: '',
  location: ''
})

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

// 表单验证规则
const rules = ref({
  name: [
    { required: true, message: '请输入仓库名称', trigger: 'blur' },
    { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
  ],
  cityId: [
    { required: true, message: '请选择所在城市', trigger: 'change' }
  ],
  address: [
    { required: true, message: '请输入详细地址', trigger: 'blur' }
  ]
})

// 获取地址数据
const getPcd = async() => {
  try {
    const res = await getPcdApi()
    pcdData.value = res.data
  } catch (error) {
    ElMessage.error('获取地址数据失败')
  }
}

// 打开修改仓库对话框并回显数据
const editWarehouse = async (row) => {
  dialogFormVisible.value = true
  title.value = '修改仓库'
  
  // 获取详细信息并回显
  try {
    const res = await getWarehouseDetailApi(row.id)
    if (res.data) {
      warehouseForm.value = { ...res.data }
    }
  } catch (error) {
    ElMessage.error('获取仓库信息失败')
  }
}

// 处理城市选择变化的函数，当用户在级联选择器中选择地址时会触发此函数
const handleCityChange = (val) => {
  // 检查是否选择了完整的三级地址（省-市-区）
  if (val && val.length === 3) {
    // 获取省级信息（第一级）
    // 在cascaderOptions中查找与选择的第一个值（省份ID）匹配的省份对象
    const province = cascaderOptions.value.find(p => p.value === val[0])
    if (province) {
      // 将省份ID和名称保存到表单数据中
      warehouseForm.value.provinceId = province.value     // 省份ID
      warehouseForm.value.provinceName = province.label   // 省份名称
    }
    
    // 获取区级信息（第三级）
    // 通过三级嵌套查找找到用户选择的区（最后一级）
    const district = cascaderOptions.value
      .find(p => p.value === val[0])                    // 找到对应的省份
      ?.children.find(c => c.value === val[1])          // 在该省份下找到对应的市
      ?.children.find(d => d.value === val[2])          // 在该市下找到对应的区
      
    if (district) {
      // 将区ID和名称保存到表单数据中（cityId和cityName字段存储的是区的信息）
      warehouseForm.value.cityId = district.value       // 区ID
      warehouseForm.value.cityName = district.label     // 区名称
    }
  } else {
    // 如果没有选择完整路径（三级），清空相关字段
    warehouseForm.value.provinceId = ''      // 清空省份ID
    warehouseForm.value.provinceName = ''    // 清空省份名称
    warehouseForm.value.cityId = ''          // 清空区ID
    warehouseForm.value.cityName = ''        // 清空区名称
  }
}

// 保存仓库信息
const save = async () => {
  if (!ruleFormRef.value) return
  
  await ruleFormRef.value.validate(async (valid) => {
    if (valid) {
      try {
        const res = await updateWarehouseApi(warehouseForm.value)
        if (res.code == 1) {
          ElMessage.success('仓库修改成功')
          dialogFormVisible.value = false
          // 通知父组件刷新列表
          emit('refresh')
        } else {
          ElMessage.error('修改仓库失败')
        }
      } catch (error) {
        ElMessage.error('修改仓库异常')
      }
    }
  })
}

// 定义emit事件，用于通知父组件刷新数据
const emit = defineEmits(['refresh'])

// 暴露方法给父组件使用
defineExpose({
  editWarehouse
})

// 组件挂载时获取地址数据
onMounted(() => {
  getPcd()
})
</script>

<template>
  <el-dialog 
    style="width: 400px;" 
    v-model="dialogFormVisible" 
    :title="title"
  >
    <el-form 
      :model="warehouseForm" 
      :rules="rules" 
      ref="ruleFormRef" 
      label-width="auto"
    >
      <el-form-item label="仓库名字" prop="name">
        <el-input 
          v-model="warehouseForm.name" 
          placeholder="请输入仓库名称"
        />
      </el-form-item>
      
      <el-form-item label="所在城市" prop="cityId">
        <!-- 级联选择器：用于选择省市区 -->
        <el-cascader
          v-model="warehouseForm.cityId"
          :options="cascaderOptions"
          :props="{ 
            expandTrigger: 'hover',    // 触发展开下一级的方式：鼠标悬停
            value: 'value',            // 指定选项的值字段
            label: 'label',            // 指定选项的标签字段
            children: 'children',      // 指定选项的子级字段
            emitPath: true             // 返回完整路径
          }"
          placeholder="请选择仓库地址"
          clearable
          style="width: 100%"
          @change="handleCityChange"
        />
      </el-form-item>
      
      <el-form-item label="详细地址" prop="address">
        <el-input 
          v-model="warehouseForm.address" 
          placeholder="请输入详细地址"
        />
      </el-form-item>
      
      <el-form-item label="经纬度">
        <el-input 
          v-model="warehouseForm.location" 
          placeholder="请输入经纬度"
        />
      </el-form-item>
    </el-form>
    
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="save">提交</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<style scoped>
.dialog-footer {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}
</style>