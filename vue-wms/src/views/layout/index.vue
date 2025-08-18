<script setup>
import router from '@/router';
import { ElMessage } from 'element-plus';
import logoImage from '@/assets/PixPin_2025-07-27_20-08-22.jpg';
import { ref, onMounted } from 'vue';

// 添加当前用户信息
const currentUser = ref({
  id: '',
  name: ''
});

onMounted(() => {
  // 从 localStorage 获取用户信息
  const userInfo = localStorage.getItem('userInfo');
  if (userInfo) {
    try {
      const user = JSON.parse(userInfo);
      currentUser.value.id = user.id || '';
      currentUser.value.name = user.name || '管理员';
    } catch (e) {
      console.error('解析用户信息失败', e);
      currentUser.value.name = '管理员';
    }
  } else {
    currentUser.value.name = '管理员';
  }
});

const exit = () => {
  localStorage.removeItem('userInfo')
  router.push('/login')
  ElMessage.success('退出成功')
}
</script>

<template>
  <div class="common-layout">
    <el-container class="layout-container">
      <div class="ax">
      <el-aside width="220px" class="sidebar">
        <div class="logo">
          <img :src="logoImage" alt="ModernWMS Logo" class="logo-image" />
        </div>
        <div >
        <el-menu 
          default-active="2" 
          class="el-menu-vertical-demo" 
          router
          background-color="#f4f5fa"
          text-color="#333"
          active-text-color="#409eff"
          unique-opened
        >
          <el-menu-item index="/">
            <el-icon><House /></el-icon>
            <span>首页</span>
          </el-menu-item>
          <div class="tm">
          <el-sub-menu index="1">
            <template #title>
              <el-icon><Setting /></el-icon>
              <span>基础设置</span>
            </template>
            <el-menu-item index="/user">
              <el-icon><User /></el-icon>
              <span>用户列表</span>
            </el-menu-item>
            <el-menu-item index="/category">
              <el-icon><Collection /></el-icon>
              <span>商品分类列表</span>
            </el-menu-item>
            <el-menu-item index="/supplier">
              <el-icon><Collection /></el-icon>
              <span>渠道商列表</span>
            </el-menu-item>
            <el-menu-item index="/goods">
              <el-icon><Collection /></el-icon>
              <span>商品管理</span>
            </el-menu-item>
            <el-menu-item index="/warehouse">
              <el-icon><Collection /></el-icon>
              <span>仓库管理</span>
            </el-menu-item>
             <el-menu-item index="/Logistics">
              <el-icon><Collection /></el-icon>
              <span>物流公司及价格</span>
            </el-menu-item>
             <el-menu-item index="/warehousingEntry">
              <el-icon><Collection /></el-icon>
              <span>收货管理</span>
            </el-menu-item>
            <el-menu-item index="/chat">
              <el-icon><Collection /></el-icon>
              <span>聊天室</span>
            </el-menu-item>
          </el-sub-menu>
          </div>
        </el-menu>
        </div>
      </el-aside>
      </div>
      <el-container class="main-container">
        <el-header class="header">
          <div class="header-content">
            <div class="header-left">
              <h3>仓储管理系统</h3>
            </div>
            <div class="header-right">
              <el-dropdown>
                <span class="user-info">
                  <el-avatar size="small" icon="User" />
                  <span class="username">{{ currentUser.name }}</span>
                  <!-- 添加显示用户ID -->
                  <span class="user-id">ID: {{ currentUser.id }}</span>
                </span>
                <template #dropdown>
                  <el-dropdown-menu>
                    <el-dropdown-item>个人中心</el-dropdown-item>
                    <el-dropdown-item @click="exit">退出登录</el-dropdown-item>
                  </el-dropdown-menu>
                </template>
              </el-dropdown>
            </div>
          </div>
        </el-header>
        <div class="xr">
        <el-main class="main-content">
          <div class="acc"> <router-view /></div>
        </el-main>
        </div>
      </el-container>
    </el-container>
  </div>
</template>

<style scoped>
.acc{
  height: 1000px;
   background: url("./岸宝.png") no-repeat center center;
  background-size: cover;
}
.ax{
   background: url("/public/芭芭拉.png") no-repeat center center;
   background-size: cover;
}
.tm{
  opacity: 0.5;
}

.sidebar {
  box-shadow: 2px 0 8px rgba(0, 0, 0, 0.1);
  z-index: 10;
}

:deep(.el-menu) {
  background-color: rgba(244, 245, 250, 0.8) !important;
  border-right: none;
  margin-top: 10px;
}
.layout-container {
  min-height: 100vh;
}


.logo {
  height: 60px;
  background-color: #f4f5fa;
  display: flex;
  align-items: center;
  justify-content: center;
  border-bottom: 1px solid #e0e2f0;
}

.logo-image {
  max-width: 100%;
  max-height: 100%;
  object-fit: contain;
}

:deep(.el-menu) {
  border-right: none;
  margin-top: 10px;
}

:deep(.el-menu-item), 
:deep(.el-sub-menu__title) {
  height: 50px;
  line-height: 50px;
}

:deep(.el-menu-item:hover),
:deep(.el-sub-menu__title:hover) {
  background-color: #e0e2f0 !important;
}

:deep(.el-menu-item.is-active) {
  background-color: #e0e2f0 !important;
  border-right: 3px solid #409eff;
}

.main-container {
  flex-direction: column;
}

.header {
  background-color: #ffffff;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
  padding: 0;
  height: 60px;
  z-index: 9;
}

.header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 100%;
  padding: 0 20px;
}

.header-left h3 {
  margin: 0;
  color: #333;
  font-weight: 500;
}

.header-right {
  display: flex;
  align-items: center;
}

.user-info {
  display: flex;
  align-items: center;
  cursor: pointer;
}

.username {
  margin-left: 10px;
  color: #333;
  font-size: 14px;
  margin-right: 10px;
}

/* 添加用户ID样式 */
.user-id {
  color: #666;
  font-size: 12px;
  background-color: #f0f2f5;
  padding: 2px 6px;
  border-radius: 4px;
}

.main-content {
  background-color: #f0f2f5;
  padding: 20px;
  min-height: calc(100vh - 60px);
}

:deep(.el-main) {
  padding: 20px;
}
</style>