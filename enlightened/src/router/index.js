import { createRouter, createWebHistory } from 'vue-router'

import HomeView from '../views/HomeView.vue'
import SamplePacksView from '../views/SamplePacksView.vue'
import SamplePackView from '../views/SamplePackView.vue'
import AboutView from '../views/AboutView.vue'
import LoginView from '../views/LoginView.vue'
import LogoutView from '../views/LogoutView.vue'
import ProfileView from '../views/ProfileView.vue'

const routes = [
	{
		path: '/',
		name: 'home',
		component: HomeView
	},
	{
		path: '/sample-packs',
		name: 'sample-packs',
		component: SamplePacksView
	},
	{
		path: '/sample-pack/:id',
		name: 'sample-pack',
		component: SamplePackView
	},
	{
		path: '/about',
		name: 'about',
		component: AboutView
	},
	{
		path: '/login',
		name: 'login',
		component: LoginView
	},
	{
		path: '/logout',
		name: 'logout',
		component: LogoutView
	},
	{
		path: '/profile',
		name: 'profile',
		component: ProfileView
	}
]

const router = createRouter({
	history: createWebHistory(process.env.BASE_URL),
	routes
})

export default router
