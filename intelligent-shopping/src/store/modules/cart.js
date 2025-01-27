import { delSelect, changeCount, getCartList } from '@/api/cart'
import { Toast } from 'vant'

export default {
  namespaced: true,
  state () {
    return {
      cartList: []
    }
  },
  mutations: {
    // 设置购物车列表
    setCartList (state, newList) {
      console.log('debug2.购物车数据展示', newList)
      state.cartList = newList
      console.log('debug3.购物车数据展示', state.cartList)
    },
    // 点击小选修改状态
    toggleCheck (state, goodsId) {
      const goods = state.cartList.find(item => item.goods_id === goodsId)
      goods.isChecked = !goods.isChecked
    },
    // 点击全选，重置状态
    toggleAllCheck (state, flag) {
      state.cartList.forEach(item => {
        item.isChecked = flag
      })
    },
    changeCount (state, { goodsId, value }) {
      const obj = state.cartList.find(item =>
        item.goods_id === goodsId)
      obj.goods_num = value
    }
  },
  actions: {
    async getCartAction (context) {
      const { data } = await getCartList()
      console.log('debug1.购物车数据展示', data.list)
      data.list.forEach(item => {
        item.isChecked = true
      })
      context.commit('setCartList', data.list)
    },
    async changeCountAction (context, obj) {
      const { goodsId, value, skuId } = obj
      context.commit('changeCount', {
        goodsId, value
      })
      // 调用axiosApi将数据同步到后台
      await changeCount(goodsId, value, skuId)
    },
    // 删除购物车数据
    async delSelect (context) {
      const selCartList = context.getters.selCartList
      const cartIds = selCartList.map(item => item.id)
      await delSelect(cartIds)
      Toast('删除成功')
      // 重新拉取最新的购物车数据 (重新渲染)
      context.dispatch('getCartAction')
    }
  },
  getters: {
    // 商品总数
    cartTotal (state) {
      return state.cartList.reduce((sum, item) =>
        sum + item.goods_num, 0)
    },
    // 选中商品列表
    selCartList (state) {
      return state.cartList.filter(item => item.isChecked)
    },
    // 选中商品总数
    selCount (state, getters) {
      return getters.selCartList.reduce((sum, item) => sum + item.goods_num, 0)
    },
    // 选中商品总价
    selPrice (state, getters) {
      return getters.selCartList.reduce((sum, item,
        index) => {
        return sum + item.goods_num *
        item.goods.goods_price_min
      }, 0).toFixed(2)
    },
    // 全选功能
    isAllChecked (state) {
      return state.cartList.every(item => item.isChecked)
    }
  }
}
