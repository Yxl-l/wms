import request from "@/utils/request"
//获取全部物流公司
export const getAllApi = ()=>request.get('/tb_logistics_company/getAll')
//获取分页物流公司
export const getPageApi = (page,pageSize,companyName)=>request.get(`/tb_logistics_company?page=${page}&pageSize=${pageSize}&companyName=${companyName}`)
//添加物流公司
export const addApi = (data)=>request.post('/tb_logistics_company',data)
//修改物流公司
export const updateApi = (data)=>request.put('/tb_logistics_company',data)
//删除物流公司
export const deleteApi = (id)=>request.delete(`/tb_logistics_company/${id}`)
//获取物流公司详情
export const getDetailApi = (id)=>request.get(`/tb_logistics_company/${id}`)
//获取全部物流价格--------------------------------------------------------------------================================
export const getAllPriceApi = ()=>request.get('/tb_logistics_price/getAll')
//获取分页物流价格
export const getPagePriceApi = (page,pageSize,category,logisticsCompanyId)=>request.get(`/tb_logistics_price?page=${page}&pageSize=${pageSize}&category=${category}&logisticsCompanyId=${logisticsCompanyId}`)
//添加物流价格
export const addPriceApi = (data)=>request.post('/tb_logistics_price',data)
//修改物流价格
export const updatePriceApi = (data)=>request.put('/tb_logistics_price',data)
//删除物流价格
export const deletePriceApi = (id)=>request.delete(`/tb_logistics_price/${id}`)
//获取物流价格详情
export const getDetailPriceApi = (id)=>request.get(`/tb_logistics_price/${id}`)
