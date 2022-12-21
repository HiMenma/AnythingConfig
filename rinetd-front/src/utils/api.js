import axios from 'axios'

export const  getRequest = (url,params) => {
    return axios({
        method: 'get',
        url:url,
        data: params,
        headers: {
            'Content-Type': 'application/json'
        }
    })
}
export const  postRequest = (url,params) => {
    return axios({
        method: 'post',
        url:url,
        data: params,
        headers: {
            'Content-Type': 'application/json'
        }
    })
}
