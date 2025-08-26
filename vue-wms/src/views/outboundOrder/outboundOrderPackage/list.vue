<script setup>
import { getPageApi, updateApi } from "@/api/warehousingEntryUp";
import Query from "./query.vue";
import { onMounted, ref, reactive } from "vue";
import { ElMessage, ElMessageBox } from 'element-plus'
import { getAllApi as getSupplierAllApi } from "@/api/supplier";
import { getSkuAllApi } from "@/api/goods";
import { getWarehouseAllApi, getAreaByWarehouseIdApi, getPageAreaLocaApi } from "@/api/warehouse";

const queryRef = ref();

// 上架弹窗相关
const shelfDialogVisible = ref(false)
const shelfForm = reactive({
  id: null,
  skuId: null,
  warehouseId: null,
  warehouseAreaId: null,
  warehouseAreaLocaId: null,
  count: null
})

// 仓库、库区、库位相关数据
const warehouseList = ref([])
const areaList = ref([])
const locaList = ref([])

// 状态映射
const statusMap = {
  1: '未上架',
  2: '已上架'
}

// 状态类型映射（用于el-tag的type属性）
const statusTypeMap = {
  1: 'danger',
  2: 'success'
}

// 分页相关
const warehousingEntryUpList = ref([])
const page = ref(1)
const pageSize = ref(8)
const totalSize = ref(0)

// 数据映射
const supplierMap = ref(new Map())
const skuListMap = ref(new Map())
const warehouseMap = ref(new Map())
const areaMap = ref(new Map())
const locaMap = ref(new Map())

// 获取所有sku数据用于映射
const getSkuAll = async () => {
  try {
    const res = await getSkuAllApi();
    if (res.data) {
      res.data.forEach(item => {
        skuListMap.value.set(item.id, item.title);
      });
    }
  } catch (error) {
    console.error("获取sku数据失败:", error);
  }
};

// 获取所有供应商数据用于映射
const getSupplierData = async () => {
  try {
    const res = await getSupplierAllApi();
    if (res.data) {
      res.data.forEach(item => {
        supplierMap.value.set(item.id, item.supplierName);
      });
    }
  } catch (error) {
    console.error("获取供应商数据失败:", error);
  }
};

// 获取仓库数据用于映射
const getWarehouseData = async () => {
  try {
    const res = await getWarehouseAllApi();
    if (res.data) {
      res.data.forEach(item => {
        warehouseMap.value.set(item.id, item.name);
      });
    }
  } catch (error) {
    console.error("获取仓库数据失败:", error);
  }
};

// 获取库区数据用于映射
const getAreaData = async (areaId) => {
  if (!areaId || areaMap.value.has(areaId)) return;
  
  try {
    const res = await getAreaDetailApi(areaId);
    if (res.data) {
      areaMap.value.set(areaId, res.data.name);
    }
  } catch (error) {
    console.error("获取库区数据失败:", error);
  }
};

// 获取库位数据用于映射
const getLocaData = async (locaId) => {
  if (!locaId || locaMap.value.has(locaId)) return;
  
  try {
    const res = await getAreaLocaDetailApi(locaId);
    if (res.data) {
      locaMap.value.set(locaId, res.data.locaCode);
    }
  } catch (error) {
    console.error("获取库位数据失败:", error);
  }
};

// 获取仓库列表
const getWarehouseList = async () => {
  try {
    const res = await getWarehouseAllApi();
    if (res.data) {
      warehouseList.value = res.data;
    }
  } catch (err) {
    console.error("获取仓库列表失败:", err);
  }
};

// 当仓库改变时，获取对应的库区列表
const handleWarehouseChange = async (warehouseId) => {
  shelfForm.warehouseAreaId = null;
  shelfForm.warehouseAreaLocaId = null;
  areaList.value = [];
  locaList.value = [];
      const res = await getAreaByWarehouseIdApi(warehouseId);
      areaList.value = res.rows;
};

// 当库区改变时，获取对应的库位列表
const handleAreaChange = async (areaId) => {
  shelfForm.warehouseAreaLocaId = null;
  locaList.value = [];
      const res = await getPageAreaLocaApi(1, 1000, areaId);
        locaList.value = res.rows;
    } ;

// 分页查询数据
const getPage = async () => {
  
    let res = await getPageApi(
      page.value,
      pageSize.value,
      queryRef.value?.searchForm?.warehouseId || '',
      queryRef.value?.searchForm?.skuId || '',
      queryRef.value?.searchForm?.status || ''
    );
    
   
      warehousingEntryUpList.value = res.rows || [];
      totalSize.value = res.total || 0;
      
      // 获取关联数据用于显示
      for (const item of warehousingEntryUpList.value) {
        await getAreaData(item.warehouseAreaId);
        await getLocaData(item.warehouseAreaLocaId);
      }
    };

const handleSizeChange = (val) => {
  pageSize.value = val
  page.value = 1
  getPage()
}

const handleCurrentChange = (val) => {
  page.value = val
  getPage()
}

// 处理子组件的搜索事件
const handleSearch = () => {
  page.value = 1; // 搜索时重置到第一页
  getPage();
};

// 处理子组件的刷新事件
const handleRefreshZi = () => {
  handleRefresh();
};

// 刷新界面
const handleRefresh = () => {
  if (queryRef.value) {
    queryRef.value.searchForm.warehouseId = '';
    queryRef.value.searchForm.skuId = '';
    queryRef.value.searchForm.status = '';
  }
  page.value = 1;
  getPage();
};

// 处理上架事件
const handleEdit = (row) => {
  // 初始化表单数据
  shelfForm.id = row.id;
  shelfForm.skuId = row.skuId;
  shelfForm.count = row.count || 0;
  
  // 设置默认值
  shelfForm.warehouseId = row.warehouseId || null;
  shelfForm.warehouseAreaId = row.warehouseAreaId || null;
  shelfForm.warehouseAreaLocaId = row.warehouseAreaLocaId || null;
  
  // 获取仓库列表
  getWarehouseList();
  
  // 如果已有仓库ID，获取对应的库区列表
  if (row.warehouseId) {
    handleWarehouseChange(row.warehouseId);
  }
  
  // 如果已有库区ID，获取对应的库位列表
  if (row.warehouseAreaId) {
    handleAreaChange(row.warehouseAreaId);
  }
  
  // 显示上架弹窗
  shelfDialogVisible.value = true;
};

// 提交上架信息
const submitShelfForm = async () => {
  try {
    // 更新入库上架记录
    const data = {
      id: shelfForm.id,
      warehouseId: shelfForm.warehouseId,
      warehouseAreaId: shelfForm.warehouseAreaId,
      warehouseAreaLocaId: shelfForm.warehouseAreaLocaId,
      count: shelfForm.count,
      status: 2 // 已上架
    };
    
    const res = await updateApi(data);
    
    if (res.code === 1) {
      ElMessage.success("上架成功");
      shelfDialogVisible.value = false;
      getPage(); // 刷新列表
    } else {
      ElMessage.error("上架失败: " + res.msg);
    }
  } catch (err) {
    console.error("上架失败:", err);
    ElMessage.error("上架失败");
  }
};

// 处理下架事件
const handleDelete = (row) => {
  ElMessageBox.confirm('确定要下架该记录吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    try {
      // 更新状态为未上架
      const data = {
        id: row.id,
        status: 1 // 未上架
      };
      
      const res = await updateApi(data);
      
      if (res.code === 1) {
        ElMessage.success("下架成功");
        getPage(); // 刷新列表
      } else {
        ElMessage.error("下架失败: " + res.msg);
      }
    } catch (err) {
      console.error("下架失败:", err);
      ElMessage.error("下架失败");
    }
  }).catch(() => {
    // 用户取消操作
  });
};

// 暴露方法给父组件使用
defineExpose({
  handleRefresh
})

onMounted(() => {
  getSupplierData();
  getWarehouseData();
  getPage();
  getSkuAll();
});
</script>

<template>
  <div id="box">
    <!-- 添加 ref 绑定   搜索事件  刷新事件  刷新界面-->
    <Query ref="queryRef" @search="handleSearch" @shuaX="handleRefreshZi"/>
    
    <!-- 上架弹窗 -->
    <el-dialog v-model="shelfDialogVisible" title="上架信息" width="50%">
      <el-form :model="shelfForm" label-width="120px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="仓库">
              <el-select 
                v-model="shelfForm.warehouseId" 
                placeholder="请选择仓库" 
                @change="handleWarehouseChange"
                style="width: 100%"
              >
                <el-option
                  v-for="item in warehouseList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
          </el-col>
          
          <el-col :span="12">
            <el-form-item label="库区">
              <el-select 
                v-model="shelfForm.warehouseAreaId" 
                placeholder="请选择库区" 
                @change="handleAreaChange"
                :disabled="!shelfForm.warehouseId"
                style="width: 100%"
              >
                <el-option
                  v-for="item in areaList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
          </el-col>
          
          <el-col :span="12">
            <el-form-item label="库位">
              <el-select 
                v-model="shelfForm.warehouseAreaLocaId" 
                placeholder="请选择库位" 
                :disabled="!shelfForm.warehouseAreaId"
                style="width: 100%"
              >
                <el-option
                  v-for="item in locaList"
                  :key="item.id"
                  :label="item.locaCode"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
          </el-col>
          
          <el-col :span="12">
            <el-form-item label="上架数量">
              <el-input-number 
                v-model="shelfForm.count" 
                :min="1" 
                controls-position="right" 
                style="width: 100%"
              />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="shelfDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="submitShelfForm">确认保存</el-button>
        </span>
      </template>
    </el-dialog>
    
    <el-table :data="warehousingEntryUpList" style="width: 100%">
      <el-table-column type="index" label="序号" width="60" />
      <el-table-column prop="skuId" label="规格SKU" width="120">
        <template #default="scope">
          {{ skuListMap.get(scope.row.skuId) || scope.row.skuId }}
        </template>
      </el-table-column>
      <el-table-column prop="warehouseId" label="仓库" width="120">
        <template #default="scope">
          {{ warehouseMap.get(scope.row.warehouseId) || scope.row.warehouseId }}
        </template>
      </el-table-column>
      <el-table-column prop="warehouseAreaId" label="库区" width="120">
        <template #default="scope">
          {{ areaMap.get(scope.row.warehouseAreaId) || scope.row.warehouseAreaId }}
        </template>
      </el-table-column>
      <el-table-column prop="warehouseAreaLocaId" label="库位" width="120">
        <template #default="scope">
          {{ locaMap.get(scope.row.warehouseAreaLocaId) || scope.row.warehouseAreaLocaId }}
        </template>
      </el-table-column>
      <el-table-column prop="count" label="上架数量" width="100"/>
      <el-table-column prop="status" label="状态" width="100">
        <template #default="scope">
          <el-tag :type="statusTypeMap[scope.row.status] || ''" size="small">
            {{ statusMap[scope.row.status] || scope.row.status }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="180"/>
      <el-table-column label="操作" width="150" fixed="right">
        <template #default="scope">
          <el-button type="primary" size="small" @click="handleEdit(scope.row)">上架</el-button>
          <el-button type="danger" size="small" @click="handleDelete(scope.row)">下架</el-button>
        </template>
      </el-table-column>
    </el-table>
   
    <br>
    
    <el-pagination
      v-model:current-page="page"
      v-model:page-size="pageSize"
      :page-sizes="[8, 16, 24, 32]"
      :small="false"
      :disabled="false"
      :background="true"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalSize"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
</template>
<style scoped>
div {
  opacity: 0.9;
}
.el-tag {
  font-size: 12px !important;
  height: auto !important;
  padding: 4px 8px !important;
}
</style>