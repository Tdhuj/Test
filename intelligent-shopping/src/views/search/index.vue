<template>
  <div class="search">
    <van-nav-bar title="商品搜索" left-arrow @click-left="$router.go(-1)" />

    <van-search v-model="search" show-action placeholder="请输入搜索关键词" clearable>
      <template #action>
        <div @click="goSearch(search)">搜索</div>
      </template>
    </van-search>

    <!-- 搜索历史 -->
    <div class="search-history">
      <div class="title">
        <span>最近搜索</span>
        <van-icon @click="clear()" name="delete-o" size="16" />
      </div>
      <div class="list">
        <div
        class="list-item"
        v-for="item in history"
        :key="item"
        @click="goSearch(item)">
        {{ item }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getHistory, setHistoryList } from '@/utils/storage'
export default {
  name: 'SearchIndex',
  data () {
    return {
      search: '',
      history: getHistory() // 获取历史记录
    }
  },
  methods: {
    goSearch (key) {
    /* 1.查找输入的内容是否在历史纪录中
       2.如果存在，则删除该内容
       3.添加该内容到历史记录列表第一个
       4.跳转到搜索列表页面
     */
      // 判断输入是否为空
      if (!key.trim()) return this.$toast('请输入搜索内容')
      // 判断输入是否在历史记录中
      const index = this.history.indexOf(key)
      if (index !== -1) {
        this.history.splice(index, 1)
      }
      this.history.unshift(key)
      setHistoryList(this.history)
      this.$router.push(`/searchlist?search=${key}`)
      this.search = ''
    },
    clear () {
      // 清空历史记录
      this.history = []
      setHistoryList([])
      this.$toast('清除成功')
    }
  }
}
</script>

<style lang="less" scoped>
.search {
  .searchBtn {
    background-color: #fa2209;
    color: #fff;
  }
  ::v-deep .van-search__action {
    background-color: #c21401;
    color: #fff;
    padding: 0 20px;
    border-radius: 0 5px 5px 0;
    margin-right: 10px;
  }
  ::v-deep .van-icon-arrow-left {
    color: #333;
  }
  .title {
    height: 40px;
    line-height: 40px;
    font-size: 14px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 15px;
  }
  .list {
    display: flex;
    justify-content: flex-start;
    flex-wrap: wrap;
    padding: 0 10px;
    gap: 5%;
  }
  .list-item {
    width: 30%;
    text-align: center;
    padding: 7px;
    line-height: 15px;
    border-radius: 50px;
    background: #fff;
    font-size: 13px;
    border: 1px solid #efefef;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    margin-bottom: 10px;
  }
}
</style>
