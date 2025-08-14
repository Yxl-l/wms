import request from "@/utils/request"

export const getCategoryApi = ()=>request.get('/category')

export const addCategoryApi = (data)=>request.post('/category',data)

export const deleteCategoryApi = (id)=>request.delete(`/category/${id}`)

export const updateCategoryApi = (data)=>request.put(`/category`,data)