<script setup>
import { ref, computed, onMounted } from "vue";
import { getCategoryApi } from "@/api/category";
import { getAllApi } from "@/api/supplier";
import { addGoodsApi } from "@/api/goods";
import { QuillEditor } from "@vueup/vue-quill";
import "@vueup/vue-quill/dist/vue-quill.snow.css";

const formGoods = ref({
  title: "", // 商品标题
  subTitle: "", // 商品副标题
  categoryId1: "",
  categoryId2: "", //
  categoryId3: "", //
  description: "", // 商品描述
  content: "", // 商品详情
  brandId: "", // 品牌id
  supplierId: "", //供应商ID
  skuList: [], // 商品SKU列表
});
//商品sku表单对象
const addOne = () => {
  formGoods.value.skuList.push({
    title: "", //商品标题
    price: "", //商品单价
    realPrice: "", //真实价格
    code: "", //商品编码
    unit: "", //单位
    saasId:"", //SaasId
    productWeight: "", //商品重量
    productLength: "", //商品长度
    productWidth: "", //商品宽度
    productHeight: "", //商品高度
    productVolume: "", //商品体积
    spec: "", //其他商品规格
  });
};
//sku表格删除方法
const removeSku = (index) => {
  formGoods.value.skuList.splice(index, 1);
};


// 存储从 API 获取的原始地址数据（树形结构）
const categoryList = ref([]);

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

// 获取所有分类数据(树形结构)
const getCategoryList = async () => {
  const res = await getCategoryApi();
  categoryList.value = res.data;
};
// 获取所有供应商数据
const allSupplier = ref([]);
const getAllSupplier = async () => {
  const res = await getAllApi();
  allSupplier.value = res.data;
};

// 表单提交方法
const onSubmit = async() => {
  console.log("提交表单:", formGoods.value);
  await addGoodsApi(formGoods.value);
};

onMounted(() => {
  getCategoryList();
  getAllSupplier();
});
</script>

<template>
  <div class="app-container">
    <br />
    <el-form :model="formGoods" label-width="auto" style="max-width: 1450px">
      <el-row :gutter="24">
        <el-col :span="4">
          <el-form-item label="商品标题">
            <el-input v-model="formGoods.title" />
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item label="商品副标题">
            <el-input v-model="formGoods.subTitle" />
          </el-form-item>
        </el-col>
    
   
        <el-col :span="5">
          <el-form-item label="分类ID1">
            <el-cascader
              v-model="formGoods.categoryId1"
              :options="cascaderOptions"
              :props="{
                expandTrigger: 'hover', // 触发展开下一级的方式：鼠标悬停
                value: 'value', // 指定选项的值字段
                label: 'label', // 指定选项的标签字段
                children: 'children', // 指定选项的子级字段
                emitPath: false, // 只返回最后一个选中节点的值
              }"
              placeholder="请选择分类ID1"
              clearable
            />
            {{ formGoods.categoryId1 }}
          </el-form-item>
        </el-col>
        <el-col :span="5">
          <el-form-item label="分类ID2">
            <el-cascader
              v-model="formGoods.categoryId2"
              :options="cascaderOptions"
              :props="{
                expandTrigger: 'hover', // 触发展开下一级的方式：鼠标悬停
                value: 'value', // 指定选项的值字段
                label: 'label', // 指定选项的标签字段
                children: 'children', // 指定选项的子级字段
                emitPath: false, // 只返回最后一个选中节点的值
              }"
              placeholder="请选择分类ID2"
              clearable
            />
            {{ formGoods.categoryId2 }}
          </el-form-item>
        </el-col>
        <el-col :span="5">
          <el-form-item label="分类ID3">
            <el-cascader
              v-model="formGoods.categoryId3"
              :options="cascaderOptions"
              :props="{
                expandTrigger: 'hover', // 触发展开下一级的方式：鼠标悬停
                value: 'value', // 指定选项的值字段
                label: 'label', // 指定选项的标签字段
                children: 'children', // 指定选项的子级字段
                emitPath: false, // 只返回最后一个选中节点的值
              }"
              placeholder="请选择分类ID3"
              clearable
            />
            {{ formGoods.categoryId3 }}
          </el-form-item>
        </el-col>
    <el-col :span="12">
      <el-form-item label="商品详情">
        <!-- 限制最多500字符并显示字数统计 -->
        <el-input
          v-model="formGoods.description"
          type="textarea"
          :maxlength="500"
          show-word-limit
          :rows="4"
        />
      </el-form-item>
</el-col>
   
        <el-col :span="6">
          <el-form-item label="商品品牌Id">
            <el-input v-model="formGoods.brandId" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="请选择供应商">
            <el-select v-model="formGoods.supplierId" placeholder="请选择供应商">
              <el-option
                v-for="(dept, index) in allSupplier"
                :key="index"
                :label="dept.supplierName"
                :value="dept.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
     

 </el-row>
      <el-form-item label="商品描述内容">
        <div class="editor">
          <QuillEditor
            v-model:content="formGoods.content"
            contentType="html"
            theme="snow"
            :toolbar="[
              ['bold', 'italic', 'underline', 'strike'],
              ['blockquote', 'code-block'],
              [{ header: 1 }, { header: 2 }],
              [{ list: 'ordered' }, { list: 'bullet' }],
              [{ script: 'sub' }, { script: 'super' }],
              [{ indent: '-1' }, { indent: '+1' }],
              [{ direction: 'rtl' }],
              [{ size: ['small', false, 'large', 'huge'] }],
              [{ header: [1, 2, 3, 4, 5, 6, false] }],
              [{ color: [] }, { background: [] }],
              [{ font: [] }],
              [{ align: [] }],
              ['clean'],
              ['link', 'image', 'video'],
            ]"
            class="rich-text-editor"
          />
        </div>
      </el-form-item>

      
        <el-button type="primary" @click="addOne">添加SKU</el-button>
        <el-row :gutter="20" v-for="(sku,index) in formGoods.skuList " :key="index" class="list"> 
          <el-col :span="4">
              <el-form-item label="商品标题">
                <el-input v-model="sku.title" placeholder="" />
              </el-form-item>
          </el-col>
           <el-col :span="4">
              <el-form-item label="商品saasId">
                <el-input v-model="sku.saasId" placeholder="" />
              </el-form-item>
          </el-col>
          <el-col :span="4">
              <el-form-item label="商品单价">
                <el-input v-model="sku.price" placeholder="" />
              </el-form-item>
          </el-col>
          <el-col :span="4">
              <el-form-item label="真实价格">
                <el-input v-model="sku.realPrice" placeholder="" />
              </el-form-item>
          </el-col>
          <el-col :span="4">
              <el-form-item label="商品编码">
                <el-input v-model="sku.code" placeholder="" />
              </el-form-item>
          </el-col>
          <el-col :span="4">
              <el-form-item label="单位">
                <el-input v-model="sku.unit" placeholder="" />
              </el-form-item>
          </el-col>
          <el-col :span="4">
              <el-form-item label="商品重量">
                <el-input v-model="sku.productWeight" placeholder="" />
              </el-form-item>
          </el-col>
          <el-col :span="4">
              <el-form-item label="商品长度">
                <el-input v-model="sku.productLength" placeholder="" />
              </el-form-item>
          </el-col>
          <el-col :span="4">
              <el-form-item label="商品宽度">
                <el-input v-model="sku.productWidth" placeholder="" />
              </el-form-item>
          </el-col>
          <el-col :span="4">
              <el-form-item label="商品高度">
                <el-input v-model="sku.productHeight" placeholder="" /> 
              </el-form-item>
          </el-col>
          <el-col :span="4">
              <el-form-item label="其他规格">
                <el-input v-model="sku.spec" placeholder="" />
              </el-form-item>
          </el-col>
          <el-col :span="2">
              <el-button type="warning" @click="removeSku(index)">删除</el-button>
          </el-col>
        </el-row>
      
          <div><br></div>
      <el-form-item>
        
        <router-link to="/goods"> <el-button type="primary" @click="onSubmit" class="bt1">保存</el-button> </router-link>
        
        <router-link to="/goods"> <el-button>取消</el-button> </router-link>
       
      </el-form-item>
    </el-form>
  </div>
</template>

<style scoped>
.bt1{
  margin-left: 500px;
  margin-right: 100px;
}
.list{
  margin-top: 10px;
   border: 3px solid #23e2ff; /* 边框颜色 */
  border-radius: 8px; /* 圆角 */
  padding: 10px; /* 可选：添加内边距以防止内容贴边 */
  margin-bottom: 10px; /* 可选：添加外边距以增加与其它元素的间距 */
}
.app-container {
  height: 1000px;
  background-color: rgba(255, 255, 255, 0.8);
}
.editor {
  /* 渐变背景 */
  background: radial-gradient(
    circle,
    rgba(40, 255, 198, 0.8) 0%,
    rgba(118, 75, 162, 0.8) 100%
  );
  padding: 20px;
  border-radius: 8px;
}
.rich-text-editor {
  height: 400px;
  width: 800px;
}

:deep(.ql-toolbar) {
  border-radius: 4px 4px 0 0;
  border-color: #dcdfe6;
}

:deep(.ql-container) {
  border-radius: 0 0 4px 4px;
  border-color: #dcdfe6;
}

:deep(.ql-editor) {
  min-height: 200px;
}
</style>
