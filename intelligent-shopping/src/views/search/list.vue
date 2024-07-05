<template>
  <div class="search">
    <van-nav-bar fixed title="商品列表" left-arrow @click-left="$router.go(-1)" />

    <van-search
      readonly
      shape="round"
      background="#ffffff"
      :value="querySearch || '搜索商品'"
      show-action
      @click="goSearch"
    >
      <template #action>
        <van-icon class="tool" name="apps-o" />
      </template>
    </van-search>

    <!-- 排序选项按钮 -->
    <div class="sort-btns">
      <button @click="goSearchAll" class="sort-item">综合</button>
      <button @click="goSearchSales" class="sort-item">销量</button>
      <button @click="goSearchPrice" class="sort-item">价格 </button>
    </div>

    <div class="goods-list">
      <GoodsItem v-for="item in proList" :key="item.goods_id" :goods=item></GoodsItem>
    </div>
  </div>
</template>

<script>
import GoodsItem from '@/components/GoodsItems'
import { getSearchProductList } from '@/api/product'
export default {
  name: 'SearchIndex',
  components: {
    GoodsItem
  },
  data () {
    return {
      proList: [],
      page: 1,
      sortType: 'all', // all-按综合搜索(默认)，sales-按销量搜索，price-按价格搜索
      sortPrice: '0', // 0-价格从低到高， 1-价格从高到低
      categoryId: '0'
    }
  },
  computed: {
    // 接收路由参数
    querySearch () {
      return this.$route.query.search
    }
  },
  async created () {
    this.proList = await this.getSearchProductList()
    console.log(this.proList)
  },
  methods: {
    // 跳转搜索页面
    goSearch () {
      this.$router.push('/search')
    },
    // 综合排序
    async goSearchAll () {
      this.sortType = 'all'
      this.proList = await this.getSearchProductList()
    },
    // 销量排序
    async goSearchSales () {
      this.sortType = 'sales'
      this.proList = await this.getSearchProductList()
    },
    async goSearchPrice () {
      // 0-价格从低到高， 1-价格从高到低
      this.sortType = 'price'
      this.sortPrice = this.sortPrice === '0' ? '1' : '0'
      this.proList = await this.getSearchProductList()
    },
    // 获取商品列表
    async getSearchProductList () {
      const { data: { list } } = await getSearchProductList({
        goodsName: this.querySearch,
        page: this.page,
        categoryId: this.categoryId,
        sortType: this.sortType,
        sortPrice: this.sortPrice
      })
      console.log(list.data)
      return list.data
    }
  }
}
</script>

<style lang="less" scoped>
.search {
  padding-top: 46px;
  ::v-deep .van-icon-arrow-left {
    color: #333;
  }
  .tool {
    font-size: 24px;
    height: 40px;
    line-height: 40px;
  }

  .sort-btns {
    display: flex;
    height: 36px;
    line-height: 36px;
    .sort-item {
      background:#ffffff;
      text-align: center;
      flex: 1;
      font-size: 16px;
      border:none
    }
  }
}

// 商品样式
.goods-list {
  background-color: #f6f6f6;
}
</style>
