import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home'
import Login from '../views/account/Login'
import ForgotPassword from '../views/account/ForgotPassword'
import Register from '../views/account/Register'
import Profile from '../views/account/Profile'
import ChangePassword from '../views/account/ChangePassword'

import Error from '../views/404'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/forgot_password',
    name: 'ForgotPassword',
    component: ForgotPassword,
  },
  {
    path: '/register',
    name: 'Register',
    component: Register,
  },
  {
    path: '/profile',
    name: 'Profile',
    component: Profile,
  },
  {
    path: '/profile/password',
    name: 'ChangePassword',
    component: ChangePassword,
  },
  {
    path: '/:pathMatch(.*)*',
    name: '404',
    component: Error,
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router