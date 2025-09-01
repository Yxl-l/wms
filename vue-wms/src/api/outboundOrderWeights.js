import request from "@/utils/request"

// 分页查询出库单称重记录
export const getPageApi = (page, pageSize,  title, weightsStatus,invoiceCode,weightsCode) => 
 request.get(`/outbound_order_weights?page=${page}&pageSize=${pageSize}&title=${title}&weightsStatus=${weightsStatus}&invoiceCode=${invoiceCode}&weightsCode=${weightsCode}`)

// 根据ID查询出库单称重记录
export const getOutboundOrderWeightsByIdApi = (id) => request.get(`/outbound_order_weights/${id}`)

// 添加出库单称重记录
export const addOutboundOrderWeightsApi = (data) => request.post('/outbound_order_weights', data)

// 修改出库单称重记录
export const updateOutboundOrderWeightsApi = (data) => request.put('/outbound_order_weights', data)

// 删除出库单称重记录
export const deleteOutboundOrderWeightsApi = (id) => request.delete(`/outbound_order_weights/${id}`)

// 查询全部出库单称重记录
export const getAllOutboundOrderWeightsApi = () => request.get('/outbound_order_weights/all')