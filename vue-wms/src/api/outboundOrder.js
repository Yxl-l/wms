import request from "@/utils/request"

// 分页查询库单
export const getPageApi = (page, pageSize, customerName, invoiceCode,status) => 
  request.get(`/outbound_order?page=${page}&pageSize=${pageSize}&customerName=${customerName}&invoiceCode=${invoiceCode}&status=${status}`)

// 添加出库单
export const addApi = (data) => request.post('/outbound_order', data)

// 修改出库单
export const updateApi = (data) => request.put('/outbound_order', data)

// 删除出库单
export const deleteApi = (id) => request.delete(`/outbound_order/${id}`)

// 根据ID查询出库单
export const getDetailApi = (id) => request.get(`/outbound_order/${id}`)

// 查询全部出库单
export const getAllApi = () => request.get('/outbound_order/all')

// 修改出库单和明细...确认订单
export const updateDetailsApi = (data) => request.put('/outbound_order/details', data)