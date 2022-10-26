import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home'
import Login from '../views/account/Login'
import Course from '../views/Course'
import CourseDetails from '../views/CourseDetails'
import Search from '../views/Search'
import ForgotPassword from '../views/account/ForgotPassword'
import Register from '../views/account/Register'
import Profile from '../views/account/Profile'
import ChangePassword from '../views/account/ChangePassword'
import AccountVerification from '../views/account/AccountVerification'
import ResetPassword from '../views/account/ResetPassword'
import University from '../views/University'
import Discussion from '../views/discussion/Discussion'
import DiscussionList from '../views/discussion/DiscussionList'
import NewDiscussion from '../views/discussion/NewDiscussion'
import ReplyDiscussion from '../views/discussion/ReplyDiscussion'
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
    path: '/course',
    name: 'Course',
    component: Course,
  },
  {
    path: '/search/:query',
    name: 'Search',
    component: Search,
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
    path: '/university/:uniName',
    name: 'University',
    component: University,
  },
  {
    path: '/register/verify/:email/:verification_code',
    name: 'AccountVerification',
    component: AccountVerification,
  },
  {
    path: '/password/recovery/:verification_code',
    name: 'ResetPassword',
    component: ResetPassword,
  },
  {
    path: '/discussion/:id',
    name: 'Discussion',
    component: Discussion
  },
  {
    path: '/discussion/new',
    name: 'NewDiscussion',
    component: NewDiscussion
  },
  {
    path: '/discussion/:id/reply',
    name: 'ReplyDiscussion',
    component: ReplyDiscussion
  },
  {
    path: '/discussion',
    name: 'DiscussionList',
    component: DiscussionList
  },
  { 
    path: '/university/:uniName/:courseName', 
    name: 'CourseDetails', 
    component: CourseDetails, 
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