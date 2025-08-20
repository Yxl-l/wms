import request from "@/utils/request"

// 分页查询入库单
export const getPageApi = (page, pageSize, batchNumber,status) => 
  request.get(`/warehousing_entry?page=${page}&pageSize=${pageSize}&batchNumber=${batchNumber}&status=${status}`)

// 添加入库单
export const addApi = (data) => request.post('/warehousing_entry', data)

// 修改入库单
export const updateApi = (data) => request.put('/warehousing_entry', data)

// 删除入库单
export const deleteApi = (id) => request.delete(`/warehousing_entry/${id}`)

// 根据ID查询入库单
export const getDetailApi = (id) => request.get(`/warehousing_entry/${id}`)

// 查询全部入库单
export const getAllApi = () => request.get('/warehousing_entry/all')
//修改入库单状态
export const updateStatusApi = (data) => request.put('/warehousing_entry/status', data)

