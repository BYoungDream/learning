import Vue from 'vue'
import Router from 'vue-router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import HelloWorld from '@/components/HelloWorld'
import Cpzx from '@/components/cpzx'
import Gywm from '@/components/gywm'
import Jjfz from '@/components/jjfz'
import Xxzx from '@/components/xxzx'
import Qtly from '@/components/cpzd-qtly'
import Qtlyt from '@/components/cpzd-qtlyt'

Vue.use(ElementUI)
Vue.use(Router)

export default new Router({
    routes: [{
        path: '/',
        name: 'HelloWorld',
        component: HelloWorld
    }, {
        path: '/cpzx/:username',
        name: 'Cpzx',
        component: Cpzx,
        children: [
            {
                path: 'qtly',
                component: Qtly
            },{
                path: 'qtlyt',
                component: Qtlyt
            }
        ]
    }, {
        path: '/gywm',
        name: 'Gywm',
        component: Gywm
    }, {
        path: '/jjfz',
        name: 'Jjfz',
        component: Jjfz
    }, {
        path: '/xxzx',
        name: 'Xxzx',
        component: Xxzx
    }]
})