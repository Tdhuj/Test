import request from '@/utils/request'

// 获取搜索商品列表数据
export const getSearchProductList = (paramsObj) => {
  const { categoryId, goodsName, page, sortType, sortPrice } = paramsObj
  return request.get('/goods/list', {
    params: {
      categoryId,
      goodsName,
      page,
      sortType,
      sortPrice
    }
  })
}
// 获取商品详情数据
export const getProDetail = (goodsId) => {
  return request.get('/goods/detail', {
    params: {
      goodsId
    }
  })
}
// 获取商品评价数据
export const getProComment = (goodsId, limit) => {
  return request.get('/comment/listRows', {
    params: {
      goodsId,
      limit
    }
  })
}
