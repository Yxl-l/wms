<template>
  <div class="chat">
    <el-button type="primary" @click="Connection" class="bt">连接聊天室</el-button>
<el-button type="danger" @click="disconnect" class="bt">退出聊天室</el-button>
<el-button type="success" @click="reset" class="bt">清空聊天内容</el-button>

    <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-row>
      <el-col :span="10">
        <el-form-item label="登录id">
          <el-input v-model="formInline.id" placeholder="输入登录id" clearable />
        </el-form-item>
      </el-col>
      <el-col :span="10">
        <el-form-item label="信息发送目标用户id">
          <el-input v-model="formInline.goId" placeholder="输入目标用户id" clearable />
        </el-form-item>
      </el-col>
     </el-row>
        <el-form-item label="发送信息内容" >
          <el-input v-model="formInline.text" type="textarea" />
        </el-form-item>
    
        <el-form-item>
          <el-button type="primary" @click="sendMessage">发送</el-button>
        </el-form-item>
    </el-form>
    <!-- 消息接收框 -->
    <el-card style="max-width: 480px">
    <template #header>
      <div class="card-header">
        <span>消息接收</span>
      </div>
    </template>
    <div v-for="(message, index) in chatList" :key="index" class="message-item">
       {{ message }}
    </div>
    
  </el-card>
  
  </div>
</template>

<script setup>import { ref } from "vue";
import { ElMessage } from "element-plus";
import { onUnmounted } from 'vue';

const formInline = ref({
  id: "",
  goId: "",
  state: "",
  text: "",
});
const chatList = ref([]);

let socket = null;
const reset = () => {
  chatList.value = [];
};
const Connection = () => {
  // 检查用户ID是否为空
  if (!formInline.value.id) {
    ElMessage.error("请输入登录ID");
    return;
  }

  // 如果已经存在连接，先关闭它
  if (socket) {
    socket.close();
  }

  ElMessage.info("开始连接...");
  
  // 修改路径为/ws/，与后端匹配
  socket = new WebSocket(`ws://localhost:8088/ws/${formInline.value.id}`);
  
  socket.onopen = function () {
    ElMessage.success("连接成功");
  };
  
  socket.onmessage = function (e) {
    ElMessage.info("接收到消息");
    chatList.value.push(e.data);
  };
  
  socket.onclose = function (event) {
    ElMessage.warning(`连接关闭: ${event.reason || '未知原因'}`);
  };
  
  socket.onerror = function (error) {
    ElMessage.error("连接出错");
  };
};
// 断开连接功能
const disconnect = () => {
  if (socket) {
    socket.close();
    socket = null;
    ElMessage.success("已退出聊天室");
  } else {
    ElMessage.warning("当前未连接到聊天室");
  }
};
// 发送消息功能
const sendMessage = () => {
  if (!socket || socket.readyState !== WebSocket.OPEN) {
    ElMessage.error("连接未建立，请先连接聊天室");
    return;
  }
  
  if (!formInline.value.text) {
    ElMessage.error("请输入要发送的消息");
    return;
  }
  
  // 如果指定了目标用户ID，则发送点对点消息
  if (formInline.value.goId) {
    socket.send(formInline.value.goId + ":" + formInline.value.text);
  } else {
    // 否则发送广播消息
    socket.send(formInline.value.text);
  }
  
  ElMessage.success("消息已发送");
  // 清空消息输入框
  formInline.value.text = "";
};

// 组件卸载时关闭连接
onUnmounted(() => {
  if (socket) {
    socket.close();
  }
});
</script>

<style scoped>.bt {
  margin: 20px;
}
.chat {
  padding: 20px;
  background-color: rgba(255, 255, 255, 0.6);
  border-radius: 20px;
  overflow: hidden;
  margin: 20px;
}
.message-item {
  padding: 8px 0;
  border-bottom: 1px solid #eee;
}

.message-item:last-child {
  border-bottom: none;
}
</style>