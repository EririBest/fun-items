import { createRouter, createWebHistory } from 'vue-router'
// import store from '../store/store'

const routes = [
  {
    path: '/',
    name: 'HelloWorld',
    component: () => import('../components/HelloWorld.vue'),
    meta: {
      title: 'hello'
    }
  }, {
    path: '/test',
    name: 'TestOne',
    component: () => import('../components/TestOne.vue'),
    meta: {
      title: '找回密码'
    }
  }, {
    path: '/user',
    name: 'UserHtml',
    component: () => import('../components/UserHtml.vue'),
    meta: {
      title: '用户界面'
    }
  }, {
    path: '/take',
    name: 'TakeHtml',
    component: () => import('../components/TakeHtml.vue'),
    meta: {
      title: '讨论区'
    }
  }, {
    path: '/person',
    name: 'PersonHtml',
    component: () => import('../components/PersonHtml.vue'),
    meta: {
      title: '个人中心'
    }
  }, {
    path: '/add',
    name: 'AddHtml',
    component: () => import('../components/AddArticleHtml.vue'),
    meta: {
      title: '发布交易'
    }
  }, {
    path: '/ttk3',
    name: 'ttkHtml',
    component: () => import('../components/TestTakeHtml.vue'),
    meta: {
      title: '聊天功能测试'
    }
  }, {
    path: '/lgo',
    name: '发布中',
    component: () => import('../components/LovHtml.vue'),
    meta: {
      title: '聊天功能测试'
    }
  }, {
    path: '/buy/:id',
    name: '商品详情',
    component: () => import('../components/BuyTextHtml.vue'),
    meta: {
      title: '详情浏览'
    }
  }, {
    path: '/aricles',
    name: '商品浏览',
    component: () => import('../components/ArticlesHtml.vue'),
    meta: {
      title: '商品浏览'
    }
  }, {
    path: '/index',
    name: '主页',
    component: () => import('../components/IndexHtml.vue'),
    meta: {
      title: 'Speak'
    }
  }, {
    path: '/ttk',
    name: '消息列表',
    component: () => import('../components/TakeAndBuy.vue'),
    meta: {
      title: '消息列表'
    }
  }, {
    path: '/addTake',
    name: '发布您的话题',
    component: () => import('../components/AddTake.vue'),
    meta: {
      title: '发布您的话题'
    }
  }, {
    path: '/looKTake/:id',
    name: '话题详情页',
    component: () => import('../components/TakeLookHtml.vue'),
    meta: {
      title: '话题'
    }
  }, {
    path: '/lable/:word',
    name: '标签话题页面',
    component: () => import('../components/TakeLable.vue'),
    meta: {
      title: '话题'
    }
  }, {
    path: '/order/:name',
    name: '233',
    component: () => import('../components/OhterPerson.vue'),
    meta: {
      title: '个人主页'
    }
  }
]
const routerHistory = createWebHistory()
const router = createRouter({
  history: routerHistory,
  routes
})
router.beforeEach((to, from, next) => {
  document.getElementById('title').innerHTML = to.meta.title
  next()
})
export default router
