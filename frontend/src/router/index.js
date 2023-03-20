import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/mainpage/HomeView.vue'
import MyPageView from '../views/account/MyPageView.vue';
import CommunityMainView from '../views/community/CommunityMainView.vue';
import CommunityAddView from '../views/community/CommunityAddView.vue';
import CommunityDetailView from '../views/community/CommunityDetailView.vue';

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
    path: '/community/add',
    name: 'add',
    component: CommunityAddView
  },
  {
    path: '/community/:number',
    name: 'article',
    component: CommunityDetailView
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
