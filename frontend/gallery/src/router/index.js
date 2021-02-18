import Vue from "vue";
import VueRouter from "vue-router";

//  main page
import MainPage from "../pages/Main/MainPage";

// User
import LoginSignupPage from "../pages/User/LoginSignupPage";
import SettingsPage from "../pages/User/SettingsPage";
import MyPage from "../pages/User/MyPage";
import ArtistMyPage from "../pages/User/ArtistMyPage";
import GuestMyPage from "../pages/User/GuestMyPage";

// Gallery
import CreateGalleryPage from "../pages/Gallery/CreateGalleryPage";
import RedGalleryPage from "../pages/Gallery/RedGalleryPage";
import GalleryPage from "../pages/Gallery/GalleryPage";
import ExhibitionPage from "../pages/Gallery/ExhibitionPage";

// Work
import WorkPage from "../pages/Work/WorkPage";
import WorkDetailFirstPage from "../pages/Work/WorkDetailFirstPage";
import DetailPage2 from "../pages/Gallery/DetailPage2";
import WorkUpLoad from "../pages/WorkUpLoadPage";

import GalleryCategoryPage from "../pages/GalleryCategoryPage";

import AdminPage1 from "../pages/Admin/AdminPage1";
import AdminPage2 from "../pages/Admin/AdminPage2";

// Gallery BJO
import uploadImageResize from "../pages/Gallery/uploadImageResize";

import gridTestPage from "../pages/gridTestPage";
import About from "../pages/About";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "MainPage",
    component: MainPage,
  },
  {
    path: "/firstdetail",
    name: "WorkDetailFirstPage",
    component: WorkDetailFirstPage,
    props: true,
  },
  {
    path: "/work",
    name: "WorkPage",
    component: WorkPage,
  },
  {
    path: "/mypage",
    name: "MyPage",
    component: MyPage,
    props: true,
  },
  {
    path: "/artistmypage",
    name: "ArtistMyPage",
    component: ArtistMyPage,
    props: true,
  },
  {
    path: "/gallery",
    name: "GalleryPage",
    component: GalleryPage,
    props: true,
  },
  {
    path: "/loginsignup",
    name: "LoginSignupPage",
    component: LoginSignupPage,
  },
  {
    path: "/settings",
    name: "SettingsPage",
    component: SettingsPage,
  },
  {
    path: "/workupload",
    name: "WorkUpLoad",
    component: WorkUpLoad,
    props: true,
  },
  {
    path: "/creategallery",
    name: "CreateGalleryPage",
    component: CreateGalleryPage,
    props: true,
  },
  {
    path: "/gallerycategory",
    name: "GalleryCategoryPage",
    component: GalleryCategoryPage,
  },
  {
    path: "/AdminPage1",
    name: "AdminPage1",
    component: AdminPage1,
  },
  {
    path: "/AdminPage2",
    name: "AdminPage2",
    component: AdminPage2,
  },
  {
    path: "/redgallery",
    name: "RedGalleryPage",
    component: RedGalleryPage,
  },
  {
    path: "/test/GalleryJ/DetailPage2",
    name: "DetailPage2",
    component: DetailPage2,
  },
  {
    path: "/test/uploadImageResize",
    name: "uploadImageResize",
    component: uploadImageResize,
    props: true,
  },
  {
    path: "/exhibition",
    name: "ExhibitionPage",
    component: ExhibitionPage,
  },
  {
    path: "/gridtestpage",
    name: "gridTestPage",
    component: gridTestPage,
  },
  {
    path: "/about",
    name: "About",
    component: About,
  },
  {
    path: "/guestmypage",
    name: "GuestMyPage",
    component: GuestMyPage,
    props: true,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
