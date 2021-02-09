import Vue from 'vue'
import VueRouter from 'vue-router'

//  main page
import MainPage from '../pages/Main/MainPage'



import ItemListPage from '../pages/ItemLisetPage'
import ItemDetailPage from '../pages/ItemDetailPage'
import MyPage from '../pages/MyPage'
import UserProfile from '../pages/UserProfile'
import GalleryRenderPage from '../pages/GalleryRenderPage'
import LoginSignup from '../pages/User/LoginSignup'
import Settings from '../pages/Settings'
import WorkUpLoad from '../pages/WorkUpLoadPage'


import DragAndDrop from '../pages/DragAndDrop'
import RedGallery from "../pages/Adult/RedGallery";
import AdultPage from "../pages/Adult/AdultPage";

import SubGallery from "../pages/Sub/SubGalleryPage";

import GalleryCategoryPage from '../pages/GalleryCategoryPage'


import AdminPage1 from '../pages/Admin/AdminPage1'
import AdminPage2 from '../pages/Admin/AdminPage2'

// Gallery BJO
import GalleryTest from '../pages/Gallery/GalleryTest'
import GalleryB from '../pages/Gallery/GalleryB'
import GalleryJ from '../pages/Gallery/GalleryJ'
import GalleryO from '../pages/Gallery/GalleryO'
import DetailPage from '../pages/Gallery/DetailPage'
import uploadImageResize from '../pages/Gallery/uploadImageResize'

import GalleryOWorks from '../pages/Gallery/GalleryO_works'


import testmainpage from '../pages/testmainpage'

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
    component: ItemDetailPage,
    props:true
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
    props:true,
  },
  {
    path:'/userprofile',
    name:'UserProfile',
    component:UserProfile,
    props:true,
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
    props:true,
  },
  {
    path:'/RedGallery/AdultPage',
    name:'AdultPage',
    component:AdultPage
  },
  {
    path:'/creategallery',
    name:"DragAndDrop",
    component:DragAndDrop
  },
  {
    path: "/RedGallery",
    name: "RedGallery",
    component: RedGallery,
  },
  {
    path: "/SubGallery",
    name: "SubGallery",
    component: SubGallery,
  },
  {
    path:'/gallerycategory',
    name:"GalleryCategoryPage",
    component:GalleryCategoryPage
  },
  {
    path:'/AdminPage1',
    name:"AdminPage1",
    component:AdminPage1
  },
  {
    path:'/AdminPage2',
    name:"AdminPage2",
    component:AdminPage2
  },
  {
    path:'/test/GalleryB',
    name:"GalleryB",
    component:GalleryB
  },
  {
    path:'/test/GalleryJ',
    name:"GalleryJ",
    component:GalleryJ
  },
  {
    path:'/test/GalleryO',
    name:"GalleryO",
    component:GalleryO
  },
  {
    path:'/test',
    name:"GalleryTest",
    component:GalleryTest
  },
  {
    path:'/test/GalleryJ/DetailPage',
    name:"DetailPage",
    component:DetailPage
  },
  {
    path:'/test/GalleryO/works',
    name:"GalleryO_works",
    component:GalleryOWorks
  },
  {
    path:'/test/uploadImageResize',
    name:"uploadImageResize",
    component:uploadImageResize
  },
  {
    path:'/testmainpage',
    name:'testmainpage',
    component:testmainpage
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
