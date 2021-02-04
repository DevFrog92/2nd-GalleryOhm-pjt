import Vue from 'vue'
import VueRouter from 'vue-router'

//  main page
import MainPage from '../pages/MainPage'



import ItemListPage from '../pages/ItemLisetPage'
import ItemDetailPage from '../pages/ItemDetailPage'
import MyPage from '../pages/MyPage'
import GalleryRenderPage from '../pages/GalleryRenderPage'
import LoginSignup from '../pages/User/LoginSignup'
import Settings from '../pages/Settings'
import WorkUpLoad from '../pages/WorkUpLoadPage'

import Adult from '../pages/Adult/AdultPage'
import DragAndDrop from '../pages/DragAndDrop'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'MainPage',
    component: MainPage
  },
  {
    path: '/detail',
    name: 'ItemDetailPage',
    component: ItemDetailPage
  },
  {
    path:'/listitem',
    name:'ItemListPage',
    component:ItemListPage
  },
  {
    path:'/mypage',
    name:'MyPage',
    component:MyPage,
  },
  {
    path:'/galleryrender',
    name:'GalleryRenderPage',
    component:GalleryRenderPage,
  },
  {
    path:'/loginsignup',
    name:"LoginSignup",
    component:LoginSignup,
  },
  {
    path:'/settings',
    name:"Settings",
    component:Settings,
  },
  {
    path:'/workupload',
    name:'WorkUpLoad',
    component:WorkUpLoad,
  },
  {
    path:'/adult',
    name:'Adult',
    component:Adult
  },
  {
    path:'/creategallery',
    name:"DragAndDrop",
    component:DragAndDrop
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
