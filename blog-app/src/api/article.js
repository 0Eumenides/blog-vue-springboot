import request from '@/request'


export function getArticles() {
  return request({
    url: '/articles',
    method: 'get'
  })
}



export function publishArticle(article) {
  return request({
    url: '/articles/create',
    method: 'post',
    data: article
  })
}