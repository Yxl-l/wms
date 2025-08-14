<script setup>
import { loginApi } from '@/api/user';
import router from '@/router';
import { ElMessage } from 'element-plus';
import { ref } from 'vue';


const loginForm = ref({
  username: '',
  password: ''
})

const login = async()=>{
    const result = await loginApi(loginForm.value)
    if(result.code == 1){
        ElMessage.success('登录成功')
        localStorage.setItem("userInfo",JSON.stringify(result.data))
        router.push('/')
    }else{
        ElMessage.error(result.msg)
    }
}
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="1000px"></el-aside>
      <el-main>
        <div class="login-container">
          <div class="login">
            <el-form :model="loginForm" label-width="120px">
              <h1>欢迎来到ModernWMS!👋🏻</h1>
              <el-form-item style="margin-left: -65px;" label="用户名">
                <el-input v-model="loginForm.username" placeholder="请输入用户名" />
              </el-form-item>
              <el-form-item style="margin-left: -65px;" label="密码">
                <el-input type="password" v-model="loginForm.password" placeholder="请输入密码" />
              </el-form-item>
              <el-button type="primary" style="width: 100%;" @click="login">登录</el-button>
            </el-form>
          </div>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<style scoped>
.el-aside {
  min-height: 100vh;
  background-color: #d3dce6;
  background-image: url(/src/assets/PixPin_2025-07-27_13-55-26.jpg);
  background-size: 100% 100%; 
}
.el-main {
  min-height: 100vh;
  background-color: #fafafa;
  display: flex;
  justify-content: center;
  align-items: center;
}

.login-container {
  width: 100%;
  max-width: 400px;
  padding: 20px;
}
</style>
