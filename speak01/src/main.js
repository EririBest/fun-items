import { createApp, defineAsyncComponent } from 'vue'
import App from './App.vue'
import router from './router/router'
import esp from 'element-plus'
import 'element-plus/theme-chalk/index.css'
import axios from 'axios'
const app = createApp(App)
app.provide('$axios', axios)
app.component('AddGoods', defineAsyncComponent(() => import('./components/AddArticleHtml.vue')))
app.component('BuyHtml', defineAsyncComponent(() => import('./components/ArticlesHtml.vue')))
app.component('TakeHtml', defineAsyncComponent(() => import('./components/TakeHtml.vue')))
app.use(esp)
app.use(router)
app.mount('#app')
