<script setup>
import { ref, onMounted,computed } from "vue";
import { getCategoryApi } from "@/api/category";
import { getPageApi } from "@/api/goods";



//搜索条件
const formInline = ref({
  title: "",
  code: "",
  categoryId: "",
});
//搜索到的表单数据
const tableData=ref([])
// 分页相关
const deleteRef = ref('deleteRef')
const addOrUpdateRef = ref('addOrUpdateRef')
const userList = ref([])
const page = ref(1)
const pageSize = ref(10)
const totalSize = ref(0)
//搜索方法
const onSubmit = async() => {
  console.log(page.value,
    pageSize.value,
    formInline.value.title,
   formInline.value.code,
    formInline.value.categoryId);
    let res = await getPageApi(
    page.value,
    pageSize.value,
    formInline.value.title,
    formInline.value.categoryId,
   formInline.value.code,
    
  );
  console.log(res.data);
  tableData.value=res.data.rows
  totalSize.value=res.data.total
};



// 存储从 API 获取的原始地址数据（树形结构）
const categoryList = ref([]);
// 获取所有分类数据(树形结构)
const getCategoryList = async () => {
  const res = await getCategoryApi();
  categoryList.value = res.data;
};

// 计算属性：将原始树形数据转换为 Element Plus 级联选择器所需的格式
const cascaderOptions = computed(() => {
  // 遍历所有分类数据
  return categoryList.value.map((province) => ({
    value: province.id, // 级联选择器的值（分类ID）
    label: province.name, // 级联选择器显示的文本（分类名称）
    // 处理分类子级数据
    children: province.children.map((city) => ({
      value: city.id, // 分类ID
      label: city.name, // 分类名称
      // 处理分类子级数据
      children: city.children.map((district) => ({
        value: district.id, // 分类ID
        label: district.name, // 分类名称
      })),
    })),
  }));
});

// 重置搜索表单
const reset = () => {
    formInline.value.title = ''      
    formInline.value.code = ''
    formInline.value.categoryId = ''
    onSubmit()
}



onMounted(() => {
  // 获取所有分类数据
  getCategoryList();
  onSubmit()
});
</script>

<template>
  <div class="box">
    <div>
      <!-- 查询条件 -->
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="商品名称">
          <el-input v-model="formInline.title" placeholder="请输入商品名称" clearable />
        </el-form-item>

         <el-form-item label="商品类别">
            <el-cascader
              v-model="formInline.categoryId"
              :options="cascaderOptions"
              :props="{
                expandTrigger: 'hover', // 触发展开下一级的方式：鼠标悬停
                value: 'value', // 指定选项的值字段
                label: 'label', // 指定选项的标签字段
                children: 'children', // 指定选项的子级字段
                emitPath: false, // 只返回最后一个选中节点的值
              }"
              placeholder="请选择分类"
              clearable
            />
            {{ formInline.categoryId }}
          </el-form-item>

        <el-form-item label="商品编码">
          <el-input v-model="formInline.code" placeholder="请输入商品编码" clearable />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">查询</el-button>
          <el-button type="primary" @click="reset">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
     <!-- 列表 -->
    <div class="list">
      <el-table :data="tableData" style="width: 100%">
        <el-table-column type="index" label="序号" />
        <el-table-column prop="saasId" label="商品编码" width="120" />
        <el-table-column prop="title" label="商品名称" width="120" />
        <el-table-column prop="category" label="商品类别" width="120" />
        <el-table-column prop="description" label="商品描述" width="120" />
        <el-table-column prop="supplier" label="供应商名称" width="120" />
        <el-table-column prop="brand" label="品牌" width="120" />
        <el-table-column prop="code" label="商品条码" width="120" />
        <el-table-column prop="productWeight" label="商品重量" width="120" />
        <el-table-column prop="productLength" label="商品长度" width="120" />
        <el-table-column prop="productWidth" label="商品宽度" width="120" />
        <el-table-column prop="productHeight" label="商品高度" width="120" />
        <el-table-column prop="productVolume" label="商品体积" width="120" />
        <el-table-column prop="realPrice" label="商品成本" width="120" />
        <el-table-column prop="price" label="商品价格" width="120" />
        <el-table-column prop="spec" label="其他" width="120" />
        <el-table-column fixed="right" label="操作" min-width="120">
          <template #default>
            <el-button link type="primary" size="small" @click="updataGoods">
              修改
            </el-button>
            <el-button link type="danger" size="small" @click="deleteGoods">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <br>
<div>
    <el-pagination
      v-model:current-page="page"
      v-model:page-size="pageSize"
      :page-sizes="[10, 20, 30, 40]"
      :small="false"
      :disabled="false"
      :background="true"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalSize"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
</div>
   
  </div>
</template>

<style scoped>
.box {
  padding: 20px; 
  background-color: rgba(255, 255, 255, 0.6);
   border-radius: 20px;
  overflow: hidden;
}
.list{
  opacity: 0.8;
    border-radius: 20px;
  overflow: hidden;
}
</style>
