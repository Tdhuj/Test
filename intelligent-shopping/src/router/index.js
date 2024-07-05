import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from '@/views/layout'
import Search from '@/views/search'
import SearchList from '@/views/search/list'
import ProDetail from '@/views/prodetail'
import Login from '@/views/login'
import Pay from '@/views/pay'
import MyOrder from '@/views/myorder'
import Home from '@/views/layout/home'
import Category from '@/views/layout/category'
import Cart from '@/views/layout/cart'
import User from '@/views/layout/user'
import store from '@/store'

Vue.use(VueRouter)

const router = new VueRouter({
  routes: [
    {
      path: '/login',
      component: Login
    },
    {
      path: '/',
      component: Layout,
      redirect: '/home',
      children: [
        {
          path: 'home',
          component: Home
        },
        {
          path: 'category',
          component: Category
        },
        {
          path: 'cart',
          component: Cart
        },
        {
          path: 'user',
          component: User
        }
      ]
    },
    {
      path: '/search',
      component: Search
    },
    {
      path: '/searchlist',
      component: SearchList
    },
    {
      path: '/prodetail/:id',
      component: ProDetail
    },
    {
      path: '/pay',
      component: Pay
    },
    {
      path: '/myorder',
      component: MyOrder
    }
  ]
})
// 设置需要拦截的url添加到拦截名单中
const whiteList = ['/pay', 'myorder']
// 路由守卫
router.beforeEach((to, from, next) => {
  // 判断是否需要拦截
  if (!whiteList.includes(to.path)) {
    next()
    return
  }
  // 获取token
  const token = store.getters.token
  console.log('token', token)
  // 判断是否已经登录
  if (token) {
    // token存在表示已经登录 放行
    next()
  } else {
    // token不存在 未登录 拦截并跳转到登陆页面
    next('/login')
  }
})

export default router
