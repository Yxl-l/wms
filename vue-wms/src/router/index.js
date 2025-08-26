import { createRouter, createWebHistory } from 'vue-router'
import Layout from '@/views/layout/index.vue'
import Index from '@/views/index/index.vue'
import Login from '@/views/login/index.vue'
import User from '@/views/user/index.vue'
import Category from '@/views/category/index.vue'
import Supplier from '@/views/supplier/index.vue'
import Goods from '@/views/goods/index.vue'
import AddGoods from '@/views/goods/addGoods.vue'
import Chat from '@/views/chat/index.vue'
import Warehouse from '@/views/warehouse/index.vue'
import Logistics from '@/views/logistics/index.vue'
import WarehousingEntry from '@/views/warehousingEntry/index.vue'
import OutboundOrder from '@/views/outboundOrder/index.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/' , component: Layout,children: [
      { path: '', component: Index },
      { path: '/user',name:'user', component: User },
      { path: '/category',name:'category',  component: Category },
      { path: '/supplier',name:'supplier',  component: Supplier },
      { path: '/goods', name:'goods', component: Goods },
      { path: 'addGoods', name:'addGoods', component: AddGoods },
      { path: 'chat', name:'chat', component: Chat },
      { path: 'warehouse', name:'warehouse', component: Warehouse },
      { path: 'logistics', name:'logistics', component: Logistics },
      { path: 'warehousingEntry', name:'warehousingEntry', component: WarehousingEntry },
      { path: 'outboundOrder', name:'outboundOrder', component: OutboundOrder },
     
    ]},
    { path: '/login', component: Login }
  ],
})

export default router
