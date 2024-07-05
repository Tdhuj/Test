import request from '@/utils/request'
// 获取购物车列表数据
export const getCartList = () => {
  return request.get('/cart/list')
}
// 加入购物车
export const addCart = (goodsId, goodsNum, goodsSkuId) => {
  return request.post('/cart/add', {
    goodsId,
    goodsNum,
    goodsSkuId
  })
}
// 修改商品数量
export const changeCount = (goodsId, goodsNum, goodsSkuId) => {
  return request.post('/cart/update', {
    goodsId,
    goodsNum,
    goodsSkuId
  })
}
// 购物车删除所选功能
export const delSelect = (cartIds) => {
  return request.post('/cart/clear', {
    cartIds
  })
}
