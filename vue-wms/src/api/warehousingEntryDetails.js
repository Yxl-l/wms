import request from "@/utils/request"

// 分页查询入库明细
export const getPageApi = (page, pageSize, warehousingEntryId, supplierId, unloadEmpId,skuId,status) => 
  request.get(`/warehousing_entry_details?page=${page}&pageSize=${pageSize}&warehousingEntryId=${warehousingEntryId}&supplierId=${supplierId}&unloadEmpId=${unloadEmpId}&skuId=${skuId}&status=${status}`)

// 添加入库明细
export const addApi = (data) => request.post('/warehousing_entry_details', data)

// 修改入库明细
export const updateApi = (data) => request.put('/warehousing_entry_details', data)

// 删除入库明细
export const deleteApi = (id) => request.delete(`/warehousing_entry_details/${id}`)

// 根据ID查询入库明细
export const getDetailApi = (id) => request.get(`/warehousing_entry_details/${id}`)

// 查询全部入库明细
export const getAllApi = () => request.get('/warehousing_entry_details/all')
//修改入库明细状态
export const updateStatusApi = (data) => request.put('/warehousing_entry_details/status', data)