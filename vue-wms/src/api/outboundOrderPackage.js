import request from "@/utils/request"

// 分页查询出库单打包记录
export const getPageApi = (page, pageSize,  title, packageStatus,invoiceCode,packageCode) => 
  request.get(`/outbound_order_package?page=${page}&pageSize=${pageSize}&title=${title}&packageStatus=${packageStatus}&&invoiceCode=${invoiceCode}&packageCode=${packageCode}`)

// 根据ID查询出库单打包记录
export const getOutboundOrderPackageByIdApi = (id) => request.get(`/outbound_order_package/${id}`)

// 添加出库单打包记录
export const addOutboundOrderPackageApi = (data) => request.post('/outbound_order_package', data)

// 修改出库单打包记录
export const updateOutboundOrderPackageApi = (data) => request.put('/outbound_order_package', data)

// 删除出库单打包记录
export const deleteOutboundOrderPackageApi = (id) => request.delete(`/outbound_order_package/${id}`)

// 查询全部出库单打包记录
export const getAllOutboundOrderPackageApi = () => request.get('/outbound_order_package/all')