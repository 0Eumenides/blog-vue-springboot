import axios from 'axios'
import { Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/request/token'

const service = axios.create({
  baseURL: process.env.BASE_API, 
  timeout: 10000 
})

//request拦截器
service.interceptors.request.use(config => {
  
  if (store.state.token) {
  	console.info("拦截器-" + getToken())
    config.headers['Oauth-Token'] = getToken() 
  }
  return config
}, error => {
	
  Promise.reject(error)
})

// respone拦截器
service.interceptors.response.use(
  response => {
  	console.info("拦截器response")
  	console.info(response.data)
    const res = response.data;
    //0 为成功状态
   	if (res.code !== 0) {
   		
     //90001 Session超时
     if (res.code === 90001) {
     		console.info("Session超时")
        store.dispatch('fedLogOut')
        return Promise.reject('error');
     }
     
     //20001 用户未登录
     if (res.code === 20001) {
     		console.info("用户未登录")
     		
     		Message({
	    		type: 'warning',
	    		showClose: true,
	      	message: '未登录或登录超时，请重新登录哦'
	    	})
     		
	    	store.dispatch('fedLogOut')
	    	
     		return Promise.reject('error');
     }
     
     //70001 权限认证错误
     if(res.code === 70001){
     	console.info("权限认证错误")
     	Message({
    		type: 'warning',
    		showClose: true,
      	message: '你没有权限访问哦'
    	})
     	return Promise.reject('error');
     }
     
     return Promise.reject(res.msg);
   } else {
     return response.data;
   }
  },
  error => {
    Message({
			type: 'warning',
			showClose: true,
	  	message: '连接超时'
		})
    return Promise.reject('error')
  })

export default service
