import request from "@/utils/request"
//添加
export const addGoodsApi = (data)=>request.put('/goods',data)
//分页查询
export const getPageApi = (page,pageSize,title,categoryId,code)=>request.get(`/goods?page=${page}&pageSize=${pageSize}&title=${title}&categoryId=${categoryId}&code=${code}`)