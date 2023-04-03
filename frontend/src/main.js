import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'
import InfiniteScroll from 'infinite-loading-vue3'
import VueCookies from "vue-cookies"
// import cors from "cors"

loadFonts()

createApp(App)
.use(router)
.use(store)
.use(vuetify)
.use(VueCookies)
.use(InfiniteScroll)
.mount('#app')
// .use(cors({ origin: '*', }))
