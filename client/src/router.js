import Vue from "vue";
import Router from "vue-router";
import Home from "./views/Home.vue";
import Homey from "./views/Homey.vue";
import Operations from "./views/Operations";

Vue.use(Router);

export default new Router({
    mode: "history",
    base: process.env.BASE_URL,
    routes: [
        {
            path: "/",
            name: "home",
            component: Home
        },
        {
            path: "/homey",
            name: "home",
            component: Homey
        },
        {
            path: "/operations",
            name: "operations",
            component: Operations
        },
        {
            path: "/operations/:year/:month",
            name: "operations",
            component: Operations
        },
        {
            path: "/operations/:category",
            name: "operations",
            component: Operations
        },
        {
            path: "/about",
            name: "about",
            // route level code-splitting
            // this generates a separate chunk (about.[hash].js) for this route
            // which is lazy-loaded when the route is visited.
            component: () =>
                import(/* webpackChunkName: "about" */ "./views/About.vue")
        }
    ]
});
