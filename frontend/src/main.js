
import Vue from 'vue';
import VueRouter from 'vue-router';
import BootstrapVue from 'bootstrap-vue';

import App from './App.vue';
import Home from './Home.vue';
import BoekDetail from './BoekDetail.vue'

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

const truncateFilter = (text, length, clamp) => {
    clamp = clamp || '...';
    let node = document.createElement('div');
    node.innerHTML = text;
    const content = node.textContent;
    return content.length > length ? content.slice(0, length) + clamp : content;
}
Vue.filter('truncate', truncateFilter)

const router = new VueRouter({
    routes: [
        { path: '/', component: Home },
        { path: '/detail/:isbn', component: BoekDetail },
        { path: '*', component: Home }
    ]
})

bootstrapApp(router);
