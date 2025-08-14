import request from "@/utils/request"

export const loginApi = (data) => request.post('/users/login', data)

export const getUserPageApi = (page,pageSize,name,employeeId) =>request.get(`/users?page=${page}&pageSize=${pageSize}&name=${name}&employeeId=${employeeId}`)

export const addUserApi = (data) => request.post('/users', data)

export const updateUserApi = (data) => request.put(`/users`, data)

export const deleteUserApi = (id) => request.delete(`/users/${id}`)