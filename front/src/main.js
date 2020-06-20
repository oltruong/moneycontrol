import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import "bootstrap";
import moment from 'moment';
import HighchartsVue from 'highcharts-vue'
import Highcharts from 'highcharts'
import stockInit from 'highcharts/modules/stock'

// import axios from 'axios';
stockInit(Highcharts)
// mapInit(Highcharts)
// addWorldMap(Highcharts)

Vue.use(HighchartsVue)


Vue.prototype.moment = moment;
// Vue.prototype.axios = axios;
Vue.config.productionTip = false;

new Vue({
            router,
            render: h => h(App)
        }).$mount("#app");
