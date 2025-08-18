import request from "@/utils/request"
//数据字典
export const getDictApi = ()=>request.get('/tb_warehouse_area/tb_dictionary')
//分页查询仓库
export const getPageApi = (page,pageSize,name,cityCode)=>request.get(`/warehouse?page=${page}&pageSize=${pageSize}&name=${name}&cityId=${cityCode}`)
//添加仓库
export const addWarehouseApi = (data)=>request.post(`/warehouse`,data)
//修改仓库
export const updateWarehouseApi = (data)=>request.put(`/warehouse`,data)
//删除仓库
export const deleteWarehouseApi = (id)=>request.delete(`/warehouse/${id}`)
//查询全部仓库
export const getWarehouseAllApi = ()=>request.get(`/warehouse/all`)
//根据ID查询仓库详情
export const getWarehouseDetailApi = (id)=>request.get(`/warehouse/${id}`)
//分页查询库区
export const getPageAreaApi = (page,pageSize,warehouseId,name)=>request.get(`/tb_warehouse_area?page=${page}&pageSize=${pageSize}&warehouseId=${warehouseId}&name=${name}`)
//添加库区
export const addAreaApi = (data)=>request.post(`/tb_warehouse_area`,data)
//修改库区
export const updateAreaApi = (data)=>request.put(`/tb_warehouse_area`,data)
//删除库区
export const deleteAreaApi = (id)=>request.delete(`/tb_warehouse_area/${id}`)
//根据ID查询库区详情
export const getAreaDetailApi = (id)=>request.get(`/tb_warehouse_area/${id}`)
//分页查询库位
export const getPageAreaLocaApi = (page,pageSize,areaId,locaCode)=>request.get(`/tb_warehouse_area_loca?page=${page}&pageSize=${pageSize}&areaId=${areaId}&locaCode=${locaCode}`)
//添加库位
export const addAreaLocaApi = (data)=>request.post(`/tb_warehouse_area_loca`,data)
//修改库位
export const updateAreaLocaApi = (data)=>request.put(`/tb_warehouse_area_loca`,data)
//删除库位
export const deleteAreaLocaApi = (id)=>request.delete(`/tb_warehouse_area_loca/${id}`)
//根据ID查询库位详情
export const getAreaLocaDetailApi = (id)=>request.get(`/tb_warehouse_area_loca/${id}`)
//获取库区列表（根据仓库ID）
export const getAreaByWarehouseIdApi = (warehouseId)=>request.get(`/tb_warehouse_area?warehouseId=${warehouseId}`)