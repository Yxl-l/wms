import request from "@/utils/request"

// 分页查询入库明细分拣
export const getPageApi = (page, pageSize, warehousingEntryDetailsId, skuId, status) => 
  request.get(`/warehousing_entry_details_sort?page=${page}&pageSize=${pageSize}&warehousingEntryDetailsId=${warehousingEntryDetailsId}&skuId=${skuId}&status=${status}`)

// 添加入库明细分拣
export const addApi = (data) => request.post('/warehousing_entry_details_sort', data)

// 修改入库明细分拣
export const updateApi = (data) => request.put('/warehousing_entry_details_sort', data)

// 删除入库明细分拣
export const deleteApi = (id) => request.delete(`/warehousing_entry_details_sort/${id}`)

// 根据ID查询入库明细分拣
export const getDetailApi = (id) => request.get(`/warehousing_entry_details_sort/${id}`)

// 查询全部入库明细分拣
export const getAllApi = () => request.get('/warehousing_entry_details_sort/all')
