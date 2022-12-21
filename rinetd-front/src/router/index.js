import {createRouter, createWebHashHistory} from 'vue-router'
import index from '../menu/Login/Index'
import login from '../menu/Login/Login'

const routes = [
    {
        path: "/",
        name: "Start",
        redirect: '/login',
    },
    {
        path: "/login",
        name: "login",
        component: login
    },
    {
        path: "/index",
        name: "index",
        component: index
    }
];
// 3. 创建路由实例
const router = createRouter({
    // 4. 采用hash 模式
    history: createWebHashHistory(),
    // 采用 history 模式
    // history: createWebHistory(),
    routes, // short for `routes: routes`
});

export default router
