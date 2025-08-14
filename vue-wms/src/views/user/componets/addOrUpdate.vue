<script setup>
import { addUserApi, updateUserApi } from '@/api/user';
import { ElMessage } from 'element-plus';
import { getCurrentInstance, ref } from 'vue';


//获取当前Vue实例
const instance = getCurrentInstance()
const ruleFormRef = ref(null)
const dialogFormVisible = ref(false)
const title = ref('')
const userForm = ref({
    id: '',
    employeeId: '',
    name: '',   
    gender: '',
    phone: '',
    status: '',
    username: ''
})

const reset = () => {
    userForm.value = {
        id: '',
        employeeId: '',
        name: '',   
        gender: '',
        phone: '',
        status: '',
        username: ''
    }
    ruleFormRef.value.resetFields()
}

const save =async () => {
     if (!ruleFormRef) return
    await ruleFormRef.value.validate(async (valid, fields) => {
        if (valid) {
            let res = null
            if (userForm.value.id) {
                res = await updateUserApi(userForm.value)
            }else {
                res = await addUserApi(userForm.value)
            }
            if (res.code == 1) {
                ElMessage.success('操作成功')
                dialogFormVisible.value = false
                instance.emit('refresh')
            }else{
                ElMessage.error('操作失败')
            }
        } else {
        console.log('error submit!', fields)
        }
    })
}

const rules = ref({
  name: [
    { required: true, message: '姓名不能为空', trigger: 'blur' }
  ],
  employeeId: [
    { required: true, message: '员工编号不能为空', trigger: 'blur' }
  ],
  gender: [
    { required: true, message: '性别不能为空', trigger: 'blur' }
  ],
  phone: [
    { required: true, message: '手机号不能为空', trigger: 'blur' }
  ],
  status: [
    { required: true, message: '状态不能为空', trigger: 'blur' }
  ],
  username: [
    { required: true, message: '用户名不能为空', trigger: 'blur' }
  ]
})

defineExpose({
    dialogFormVisible,
    title,
    userForm,
    reset
})
</script>

<template>
    <el-dialog v-model="dialogFormVisible" :title="title">
    <el-form :model="userForm" :rules="rules" ref="ruleFormRef" label-width="auto">
      <el-form-item label="员工编号" placeholder="请输入员工编号" prop="employeeId" >
        <el-input v-model="userForm.employeeId" />
      </el-form-item>
      <el-form-item label="姓名" placeholder="请输入姓名" prop="name" >
        <el-input v-model="userForm.name" />
      </el-form-item>
      <el-form-item label="性别" prop="gender" >
        <el-select v-model="userForm.gender" placeholder="请选择" >
          <el-option label="男" value="1" />
          <el-option label="女" value="2" />
        </el-select>
      </el-form-item>
      <el-form-item label="手机号" placeholder="请输入手机号" prop="phone" >
        <el-input v-model="userForm.phone" />
      </el-form-item>
      <el-form-item label="状态" prop="status" >
        <el-select v-model="userForm.status" placeholder="请选择" prop="status">
          <el-option label="禁用" value="0" />
          <el-option label="启用" value="1" />
        </el-select>
      </el-form-item>
      <el-form-item label="用户名" placeholder="请输入用户名" prop="username" >
        <el-input v-model="userForm.username" />
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