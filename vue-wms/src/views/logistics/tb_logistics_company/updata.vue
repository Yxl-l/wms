<script setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { updateApi, getDetailApi } from '@/api/logistics'

// 定义响应式数据
const dialogFormVisible = ref(false) // 控制对话框的显示与隐藏
const title = ref('修改物流公司')
const ruleFormRef = ref(null)

// 物流公司表单数据
const companyForm = ref({
  id: '',
  companyName: '',
  minPrice: '',
  linkUser: '',
  linkePhone: '',
})

// 表单验证规则
const rules = ref({
  companyName: [
    { required: true, message: '请输入物流公司名称', trigger: 'blur' },
    { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
  ],
})

// 打开修改对话框并回显数据
const editCompany = async (row) => {
  dialogFormVisible.value = true
  title.value = '修改物流公司'
  
  // 获取详细信息并回显
  try {
    const res = await getDetailApi(row.id)
    if (res.data) {
      companyForm.value = { ...res.data }
    }
  } catch (error) {
    ElMessage.error('获取物流公司信息失败')
  }
}

// 保存修改信息
const save = async () => {
  if (!ruleFormRef.value) return
  
  await ruleFormRef.value.validate(async (valid) => {
    if (valid) {
      try {
        const res = await updateApi(companyForm.value)
        if (res) {
          ElMessage.success('物流公司修改成功')
          dialogFormVisible.value = false
          // 通知父组件刷新列表
          emit('refresh')
        } else {
          ElMessage.error('修改物流公司失败')
        }
      } catch (error) {
        ElMessage.error('修改物流公司异常')
      }
    }
  })
}

// 定义emit事件，用于通知父组件刷新数据
const emit = defineEmits(['refresh'])

// 暴露方法给父组件使用
defineExpose({
  editCompany
})
</script>

<template>
  <el-dialog 
    style="width: 400px;" 
    v-model="dialogFormVisible" 
    :title="title"
  >
    <el-form 
      :model="companyForm" 
      :rules="rules" 
      ref="ruleFormRef" 
      label-width="auto"
    >
      <el-form-item label="物流公司名字" prop="companyName">
        <el-input 
          v-model="companyForm.companyName" 
          placeholder="请输入物流公司名字"
        />
      </el-form-item>
      <el-form-item label="物流运输最低费用" prop="minPrice">
        <el-input 
          v-model="companyForm.minPrice" 
          placeholder="请输入物流运输最低费用"
        />
      </el-form-item>
      <el-form-item label="物流联系人" prop="linkUser">
        <el-input 
          v-model="companyForm.linkUser" 
          placeholder="请输入物流联系人"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="linkePhone">
        <el-input 
          v-model="companyForm.linkePhone" 
          placeholder="请输入联系电话"
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