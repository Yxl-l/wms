import request from "@/utils/request"
// 获取省市区信息树形结构
export const getPcdApi = ()=>request.get('/pcd')
//获取所有省市区普通结构
export const getPcdAllApi = ()=>request.get('/pcd/all')
//添加省市区
export const addPcdApi = (data)=>request.post('/pcd',data)
//分页查询
export const getPageApi = (page,pageSize,supplierName,cityCode)=>request.get(`/supplier?page=${page}&pageSize=${pageSize}&supplierName=${supplierName}&cityCode=${cityCode}`)
//获取所有供应商
export const getAllApi = ()=>request.get('/supplier/getAll')