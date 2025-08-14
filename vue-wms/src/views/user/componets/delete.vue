<script setup>
import { deleteUserApi } from '@/api/user';
import { ElMessage, ElMessageBox } from 'element-plus';
import { getCurrentInstance } from 'vue';

//获取当前Vue实例
const instance = getCurrentInstance()
const deleteById =(id)=>{
    ElMessageBox.alert('确定要删除吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
    }).then(async () => {
        const res = await deleteUserApi(id)
        if(res.code === 1){
            ElMessage.success('删除成功')
            instance.emit('refresh')
        }else{
            ElMessage.error('删除失败')
        }
    }).catch(() => {
})
}
defineExpose({
    deleteById
})
</script>