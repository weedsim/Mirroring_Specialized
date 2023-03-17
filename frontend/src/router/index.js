import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/mainpage/HomeView.vue'
import MyPageView from '../views/account/MyPageView.vue';
import CommunityMainView from '../views/community/CommunityMainView.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/community',
    name: 'community',
    component: CommunityMainView
  },
  {
    path: '/mypageview',
    name: 'mypageview',
    component: MyPageView
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
