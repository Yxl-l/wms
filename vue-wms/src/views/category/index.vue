<script setup>
import { getCategoryApi } from '@/api/category';
import { onMounted, ref } from 'vue';
import {
  Delete,
  Edit,
} from '@element-plus/icons-vue'
import addOrUpdate from './componets/addOrUpdate.vue'
import deleteById from './componets/delete.vue'

const addOrUpdateRef = ref(null)
const deleteRef = ref(null)
const categoryList = ref([])


const handleDelete=(row) => {
  deleteRef.value.deleteById(row.id) 
}
const handleUpdate=(row) => {
  addOrUpdateRef.value.dialogFormVisible=true
  addOrUpdateRef.value.title = '修改分类'
  addOrUpdateRef.value.categoryForm=JSON.parse(JSON.stringify(row))
  addOrUpdateRef.value.status=true
}
const add = () => {
  addOrUpdateRef.value.dialogFormVisible=true
  addOrUpdateRef.value.title = '添加分类'
  addOrUpdateRef.value.reset()
  addOrUpdateRef.value.status=false
}
const getCategoryList =async () => {
  const res = await getCategoryApi()
  categoryList.value = res.data
}


onMounted(()=>{
  getCategoryList()
})
</script>

<template>
    <el-button type="primary" @click="add" circle>
      <el-icon><Plus /></el-icon>
    </el-button>
   <el-table
      :data="categoryList"
      style="width: 100%; margin-bottom: 20px"
      row-key="id"
      border
      default-expand-all
    >
      <el-table-column type="index" prop="序号" sortable />
      <el-table-column prop="name" label="商品类别" sortable />
      <el-table-column prop="createBy" label="创建人" sortable />
      <el-table-column prop="createTime" label="创建时间" sortable />
      <el-table-column prop="updateTime" label="修改时间" sortable />
      <el-table-column label="操作">
      <template #default="scope">
        <el-button type="primary" @click="handleUpdate(scope.row)" :icon="Edit" circle />
        <el-button type="danger" @click="handleDelete(scope.row)" :icon="Delete" circle />
      </template>
    </el-table-column>
    </el-table>
    <addOrUpdate ref="addOrUpdateRef" @refresh="getCategoryList"/>
    <deleteById ref="deleteRef" @refresh="getCategoryList"/>
</template>

<style scoped>

</style>
