// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Header from '@/components/public/Header'
import Fotter from '@/components/public/Fotter'

Vue.config.productionTip = false

Vue.component('app-Header', Header)
Vue.component('app-Fotter', Fotter)

/* eslint-disable no-new */
new Vue({
    el: '#app',
    router,
    components: { App },
    template: '<App/>'
})