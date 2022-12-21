import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import { getRequest } from './utils/api'
import { postRequest } from './utils/api'

// createApp(App).mount('#app')

const app = createApp(App)
app.use(ElementPlus)
app.config.productionTip = false
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.config.globalProperties.getRequest = getRequest
app.config.globalProperties.postRequest = postRequest
//挂载路由
app.use(router)

app.mount('#app')
