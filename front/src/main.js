import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import "bootstrap";
import moment from 'moment';
// import axios from 'axios';

Vue.prototype.moment = moment;
// Vue.prototype.axios = axios;
Vue.config.productionTip = false;

new Vue({
            router,
            render: h => h(App)
        }).$mount("#app");
