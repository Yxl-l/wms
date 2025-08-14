<script setup>
import { addCategoryApi, getCategoryApi, updateCategoryApi } from '@/api/category';
import { ElMessage } from 'element-plus';
import { getCurrentInstance, onMounted, ref, watch } from 'vue';


//获取当前Vue实例
const instance = getCurrentInstance()
const selectedValue = ref(null) // 选中的值
const dialogFormVisible = ref(false)
const title = ref('添加分类')
const categoryData = ref([])
const status = ref(true)
const categoryForm = ref({
    id:'',
    name:'',
    parentId:'',
    createBy:'1',
})

const reset = () => {
     categoryForm.value = {
        id:'',
        name:'',
        parentId:'',
        createBy:'1',
    }
}
const save =async () => {
    let res = null
    if(categoryForm.value.id){
        res = await updateCategoryApi(categoryForm.value)
    }else{
        res = await addCategoryApi(categoryForm.value)
    }
    if(res.code == 1){
        dialogFormVisible.value = false
        ElMessage.success('添加成功')
        reset()
        instance.emit('refresh')
    }
}
const getData = async () => {
  let result = await getCategoryApi()
  const backData = result.data // 原始数据
  // 转换数据结构的函数
  const transformData = (data) => {
    return data.map(item => ({
      value: item.id,          // value 对应 id
      label: item.name,        // label 对应 name
      children: transformData(item.children) // 递归转换 children
    }))
  }
  // 转换后的数据
  const transformedData = transformData(backData)
  // 更新 goodData
  categoryData.value = transformedData
}

// 监听 selectedValue 的变化，将值赋给 categoryForm.parentId
watch(selectedValue, (newValue) => {
  categoryForm.value.parentId = newValue
  console.log(categoryForm.value.parentId);
})


defineExpose({
    dialogFormVisible,
    title,
    categoryForm,
    status,
    reset
})

onMounted(() => {
  getData()
})
</script>

<template>
    <el-dialog style="width: 350px;" v-model="dialogFormVisible" :title="title">
        <el-form :model="categoryForm">
            <el-form-item label="商品名称" >
                <el-input v-model="categoryForm.name" autocomplete="off" />
            </el-form-item>
            <el-form-item label="所属分类" :label-width="formLabelWidth">
                <el-tree-select 
                    v-model="selectedValue" 
                    :data="categoryData" check-strictly 
                    :render-after-expand="false" 
                    :disabled="status"
                    show-checkbox
                    style="width: 240px" />
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="save">
                提交
                </el-button>
            </span>
        </template>
    </el-dialog>
</template>