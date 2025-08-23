import request from "@/utils/request"

// 分页查询入库上架
export const getPageApi = (page, pageSize, warehouseId, skuId) => 
  request.get(`/warehousing_entry_up?page=${page}&pageSize=${pageSize}&warehouseId=${warehouseId}&skuId=${skuId}`)

// 添加入库上架
export const addApi = (data) => request.post('/warehousing_entry_up', data)

// 修改入库上架
export const updateApi = (data) => request.put('/warehousing_entry_up', data)

// 删除入库上架
export const deleteApi = (id) => request.delete(`/warehousing_entry_up/${id}`)

// 根据ID查询入库上架
export const getDetailApi = (id) => request.get(`/warehousing_entry_up/${id}`)