import Vue from "vue";
import Router from "vue-router";
import Home from "./views/Home.vue";
import Operations from "./views/Operations";
import Rules from "./views/Rules";
import OperationsUpload from "./views/OperationsUpload";
import UnclassifiedOperations from "./views/UnclassifiedOperations";

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
                                      path: "/operationsupload",
                                      name: "operationsUpload",
                                      component: OperationsUpload
                                  },
                                  {
                                      path: "/operations",
                                      name: "operations",
                                      component: Operations
                                  }, {
                                      path: "/rules",
                                      name: "rules",
                                      component: Rules
                                  },
                                  {
                                      path: "/operations/:year/:month",
                                      name: "operations",
                                      component: Operations
                                  },
                                  {
                                      path: "/operations/:year",
                                      name: "operations",
                                      component: Operations
                                  },
                                  {
                                      path: "/unclassifiedoperations",
                                      name: "unclassifiedOperations",
                                      component: UnclassifiedOperations
                                  },
                                  {
                                      path: "/about",
                                      name: "about",
                                      // route level code-splitting
                                      // this generates a separate chunk (about.[hash].js) for this
                                      // route which is lazy-loaded when the route is visited.
                                      component: () =>
                                          import(/* webpackChunkName: "about" */ "./views/About.vue")
                                  }
                              ]
                          });
