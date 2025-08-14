import request from "@/utils/request"
//数据字典
export const getDictApi = ()=>request.get('/tb_warehouse_area/tb_dictionary')
//分页查询仓库

export const getPageApi = (page,pageSize,name,cityCode)=>request.get(`/warehouse?page=${page}&pageSize=${pageSize}&name=${name}&cityId=${cityCode}`)
//添加仓库
export const addWarehouseApi = (data)=>request.post(`/warehouse`,data)
//查询全部仓库
export const getWarehouseAllApi = ()=>request.get(`/warehouse/all`)
//分页查询库区
export const getPageAreaApi = (page,pageSize,warehouseId,name)=>request.get(`/tb_warehouse_area?page=${page}&pageSize=${pageSize}&warehouseId=${warehouseId}&name=${name}`)
//添加库区
export const addAreaApi = (data)=>request.post(`/tb_warehouse_area`,data)
