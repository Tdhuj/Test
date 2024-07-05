<template>
  <div>
    <keep-alive include="Home">
    <router-view></router-view>
    </keep-alive>
    <van-tabbar route active-color="#42b884" inactive-color="#000">
      <van-tabbar-item to="/home" icon="home-o">首页</van-tabbar-item>
      <van-tabbar-item to="/category" icon="search">分类页</van-tabbar-item>
      <van-tabbar-item to="/cart" icon="friends-o">购物车</van-tabbar-item>
      <van-tabbar-item to="/user" icon="setting-o">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>
export default {
  name: 'LayoutIndex'
}
</script>

<style >

</style>
