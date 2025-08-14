<script setup>
import { getUserPageApi } from '@/api/user'
import { onMounted, ref } from 'vue'
import AddOrUpdate from './componets/addOrUpdate.vue'
import Delete from './componets/delete.vue'

const deleteRef = ref('deleteRef')
const addOrUpdateRef = ref('addOrUpdateRef')
const userList = ref([])
const page = ref(1)
const pageSize = ref(10)
const totalSize = ref(0)
const searchForm = ref({
    name: '',
    employeeId: ''
})


const handleDelete = (row)=>{
  deleteRef.value.deleteById(row.id)
}
const handleEdit = (row)=>{
    addOrUpdateRef.value.dialogFormVisible = true
    addOrUpdateRef.value.title = '修改用户'
    // addOrUpdateRef.value.reset()
    addOrUpdateRef.value.userForm = JSON.parse(JSON.stringify(row)) // 深拷贝
}

const add = ()=>{
    addOrUpdateRef.value.dialogFormVisible = true
    addOrUpdateRef.value.title = '添加用户'
    addOrUpdateRef.value.reset()
}
const reset = ()=>{
    page.value = 1
    pageSize.value = 10
    searchForm.value = {
        name: '',
        employeeId: ''
    }
    getPage()
}
const getPage = async ()=>{
    const result = await getUserPageApi(page.value,pageSize.value,searchForm.value.name,searchForm.value.employeeId)
    userList.value = result.data.rows
    totalSize.value = result.data.total
}

const handleSizeChange = (val) => {
  pageSize.value = val
  page.value = 1
  getPage()
}
const handleCurrentChange = (val) => {
  page.value = val
  getPage()
}

onMounted(()=>{
  getPage() 
})
</script>

<template>
    <el-form :inline="true" :model="searchForm" >
    <el-form-item label="姓名">
      <el-input v-model="searchForm.name" placeholder="请输入" clearable />
    </el-form-item>
    <el-form-item label="员工编号">
      <el-input v-model="searchForm.employeeId" placeholder="请输入" clearable />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="getPage">查询</el-button>
      <el-button @click="reset">重置</el-button>
    </el-form-item>
  </el-form>
  <el-button type="primary" @click="add">添加</el-button>

  <el-table :data="userList" style="width: 100%">
    <el-table-column type="index" label="序号" />
    <el-table-column prop="employeeId" label="员工编号" />
    <el-table-column prop="name" label="姓名" />
    <!-- <el-table-column prop="role" label="角色" /> -->
    <el-table-column prop="gender" label="性别" >
        <template #default="scope">
          <el-tag v-if="scope.row.gender==1">男</el-tag>
          <el-tag v-else>女</el-tag>
        </template>
    </el-table-column>
    <el-table-column prop="phone" label="手机号" />
    <el-table-column prop="status" label="状态" >
        <template #default="scope">
          <el-tag type="danger" v-if="scope.row.status==0">禁用</el-tag>
          <el-tag v-else>启用</el-tag>
        </template>
    </el-table-column>
    <el-table-column label="操作">
      <template #default="scope">
        <el-button type="primary" size="mini" @click="handleEdit(scope.row)">编辑</el-button>
        <el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>

  <el-pagination
      v-model:current-page="currentPage"
      v-model:page-size="pageSize"
      :page-sizes="[10, 20, 30, 40]"
      :small="small"
      :disabled="disabled"
      :background="background"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalSize"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
    <AddOrUpdate ref="addOrUpdateRef" @refresh="getPage" />
    <Delete ref="deleteRef" @refresh="getPage" />
</template>