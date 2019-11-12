
import Vue from 'vue';
import VueRouter from 'vue-router';
import BootstrapVue from 'bootstrap-vue';
import App from './App.vue';
import Home from './Home.vue';

Vue.use(BootstrapVue)
Vue.use(VueRouter)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

const bootstrapApp = (router) => {
    new Vue({
        router: router,
        render: h => h(App),
}).$mount('#app')
}

const router = new VueRouter({
    routes: [
        { path: '/', component: Home },
        { path: '*', component: Home }
    ]
})

bootstrapApp(router);
