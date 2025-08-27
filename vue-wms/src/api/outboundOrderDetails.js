import request from "@/utils/request"


// 分页查询待检货-已拣货组件的数据
export const getPageApi = (page, pageSize, title, customerName,invoiceCode,pickStatus) => 
  request.get(`/outbound_order_details?page=${page}&pageSize=${pageSize}&customerName=${customerName}&invoiceCode=${invoiceCode}&title=${title}&pickStatus=${pickStatus}`)

// 根据ID查询出库单明细
export const getOutboundOrderDetailsByIdApi = (id) => request.get(`/outbound_order_details/${id}`)

// 添加出库单明细
export const addOutboundOrderDetailsApi = (data) => request.post('/outbound_order_details', data)

// 修改出库单明细
export const updateOutboundOrderDetailsApi = (data) => request.put('/outbound_order_details', data)

// 删除出库单明细
export const deleteOutboundOrderDetailsApi = (id) => request.delete(`/outbound_order_details/${id}`)


// 查询全部出库单明细
export const getAllOutboundOrderDetailsApi = () => request.get('/outbound_order_details/all')