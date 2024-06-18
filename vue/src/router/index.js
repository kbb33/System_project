import Vue from 'vue'
import VueRouter from 'vue-router'
import Commentpage from '@/views/Commentpage.vue'
import Managepage from "@/components/Menu/Managepage.vue"
import Homepage from "@/components/Menu/Homepage.vue"
import Statisticalpage from "@/components/Menu/Statisticalpage.vue"
import Addpage from "@/views/Addpage.vue";
import Teachermenu from "@/views/Teachermenu.vue";
import Editpage from "@/components/Addpage/Editpage.vue";
/*下面是测试路由*/
import Test from "@/views/Test.vue"
import Testwork from "@/components/Test/Testwork.vue";
import Testmenu from "@/components/Test/Testmenu.vue";




Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'testmenu',
    component: Testmenu,
    redirect: '/home',
    children:[
      {
        path: '/test',
        name:'test',
        component: Test
      }
    ]
  },
  {
    path: '/cv',
    name: 'ctrlvtest',
    component: Testwork
  },
  /*以上为测试路由*/
    /*引入路径的另一种格式*/
  // {
  //   path: '/about',
  //   name: 'about',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  // },
  {
    path: '/',
    name: 'teachermenu',
    redirect: '/home',//重定向路由
    component: Teachermenu,
    children:[
      {
        path: 'home',
        name:'homepage',
        component: Homepage
      },
      {
        path: 'manage',
        name: 'managepage',
        component: Managepage
      },
      {
        path: 'stat',
        name: 'statisticalpage',
        component: Statisticalpage
      },
    ]
  },//主页菜单路由，有父子路由
  {
    path: '/comment',
    name: 'commentpage',
    component: Commentpage
  },//批改页面路由
  {
    path: '/edit',
    name: 'Editpage',
    component: Editpage
  },//批改页面路由
  {
    path: '/add',
    name: 'addpage',
    component: Addpage
  }//添加题目页面路由
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
