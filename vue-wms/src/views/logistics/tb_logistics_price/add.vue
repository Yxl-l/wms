<script setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { addApi } from '@/api/logistics'

// 定义响应式数据
const dialogFormVisible = ref(false)//控制对话框的显示与隐藏
const title = ref('添加物流公司')
const ruleFormRef = ref(null)

// 物流公司表单数据
const companyForm = ref({
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

// 打开添加对话框
const addWarehouse = () => {
  dialogFormVisible.value = true
  // 重置表单数据
  Object.assign(companyForm.value, {
    companyName: '',
    minPrice: '',
    linkUser: '',
    linkePhone: '',
  })
}

// 保存信息
const save = async () => {
  if (!ruleFormRef.value) return
  
  await ruleFormRef.value.validate(async (valid) => {
    if (valid) {
      try {
        // 调用API保存数据
        console.log(companyForm.value)
        const res = await addApi(companyForm.value)
        if (res) {
          ElMessage.success('物流公司添加成功')
          dialogFormVisible.value = false
          // 添加成功后刷新列表
          emit('refresh')
        } else {
          ElMessage.error('添加物流公司失败')
        }
      } catch (error) {
        ElMessage.error('添加物流公司异常')
      }
    }
  })
}

// 定义emit事件，用于通知父组件刷新数据
const emit = defineEmits(['refresh'])

</script>

<template>
<div>
  <el-button type="primary" @click="addWarehouse">添加物流价格</el-button>
  
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
</div>
</template>

<style scoped>
.dialog-footer {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}
</style>