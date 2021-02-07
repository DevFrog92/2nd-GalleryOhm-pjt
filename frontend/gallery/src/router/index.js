import Vue from "vue";
import VueRouter from "vue-router";

//  main page
import MainPage from "../pages/MainPage";

import ItemListPage from "../pages/ItemLisetPage";
import ItemDetailPage from "../pages/ItemDetailPage";
import MyPage from "../pages/MyPage";
import GalleryRenderPage from "../pages/GalleryRenderPage";
import LoginSignup from "../pages/User/LoginSignup";
import Settings from "../pages/Settings";
import WorkUpLoad from "../pages/WorkUpLoadPage";

import DragAndDrop from "../pages/DragAndDrop";
import RedGallery from "../pages/Adult/RedGallery";
import AdultPage from "../pages/Adult/AdultPage";

import SubGallery from "../pages/Sub/SubGalleryPage";
import MainPageTest1 from '../pages/Main/MainPageTest1';
import MainPageTest2 from '../pages/Main/MainPageTest2';
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "MainPage",
    component: MainPage,
  },
  {
    path: "/detail",
    name: "ItemDetailPage",
    component: ItemDetailPage,
  },
  {
    path: "/listitem",
    name: "ItemListPage",
    component: ItemListPage,
  },
  {
    path: "/mypage",
    name: "MyPage",
    component: MyPage,
  },
  {
    path: "/galleryrender",
    name: "GalleryRenderPage",
    component: GalleryRenderPage,
  },
  {
    path: "/loginsignup",
    name: "LoginSignup",
    component: LoginSignup,
  },
  {
    path: "/settings",
    name: "Settings",
    component: Settings,
  },
  {
    path: "/workupload",
    name: "WorkUpLoad",
    component: WorkUpLoad,
  },
  {
    path: "/creategallery",
    name: "DragAndDrop",
    component: DragAndDrop,
  },
  {
    path: "/RedGallery",
    name: "RedGallery",
    component: RedGallery,
  },
  {
    path: "/RedGallery/AdultPage",
    name: "AdultPage",
    component: AdultPage,
  },
  {
    path: "/SubGallery",
    name: "SubGallery",
    component: SubGallery,
  },
  {
    path:'/mainpagetest1',
    name:'MainPageTest1',
    component:MainPageTest1,
  },
  {
    path:'/mainpagetest2',
    name:'MainPageTest2',
    component:MainPageTest2,
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
