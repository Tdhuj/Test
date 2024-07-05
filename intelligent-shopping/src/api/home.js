import request from '@/utils/request'

// 获取首页数据
export const getHomeData = () => {
  return request.get('/page/detail',
    {
      params: {
        // 页面id:默认为0
        pageId: 0
      }
    })
}
