import Vue from "vue";
import VueRouter from "vue-router";
import Admin from "@/pages/Admin";
import Login from "@/pages/Login";
import Employee from "@/pages/Employee";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  {
    path: "/admin",
    name: "Admin",
    component: Admin,
  },
  {
    path: "/employee",
    name: "Employee",
    component: Employee,
  },
];

const router = new VueRouter({
  routes,
  mode: 'history',
});

export default router;
