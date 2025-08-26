import request from "@/utils/request"

// 添加出库单明细
export const addOutboundOrderDetailsApi = (data) => request.post('/outbound_order_details', data)

// 修改出库单明细
export const updateOutboundOrderDetailsApi = (data) => request.put('/outbound_order_details', data)

// 删除出库单明细
export const deleteOutboundOrderDetailsApi = (id) => request.delete(`/outbound_order_details/${id}`)

// 根据ID查询出库单明细
export const getOutboundOrderDetailsByIdApi = (id) => request.get(`/outbound_order_details/${id}`)

// 查询全部出库单明细
export const getAllOutboundOrderDetailsApi = () => request.get('/outbound_order_details/all')