import router from '@/router'
import axios from 'axios'
import { ElMessage } from 'element-plus'



//创建axios实例对象
const request = axios.create({
  baseURL: '/api',
  timeout: 600000
})

// 添加请求拦截器
request.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    const usreInfo = localStorage.getItem("userInfo")
    if(usreInfo){
      config.headers.token = JSON.parse(usreInfo).token
    }
    return config;
  }, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
  });

//axios的响应 response 拦截器
request.interceptors.response.use(
  (response) => { //成功回调
    return response.data
  },
  (error) => { //失败回调
    ElMessage.error(error.message)
    if(error.response.status === 401){
      ElMessage.error('登录失效,请重新登录')
      router.push('/login')
    }
    return Promise.reject(error)
  }
)

export default request