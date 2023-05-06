<template>
	<div>
		<button v-for="entry in languages" :key="entry.title" @click="changeLocale(entry.language)">
			<flag :iso="entry.flag" v-bind:squared=false /> {{ entry.title }}
		</button>
	</div>
	<nav>
		<router-link to="/">Home</router-link> |
		<router-link to="/about">About</router-link> |

		<template v-if="user.logged">
			<router-link to="/">{{ user.username }}</router-link>
			<button @click="logout()">wyloguj</button>
		</template>
		<template v-else>
			<router-link to="/login">login</router-link>
		</template>

	</nav>
	<router-view />
</template>

<script>
import { user } from '@/reactive/user.js'

export default {
	data() {
		return {
			languages: [
				{ flag: 'us', language: 'en', title: 'English' },
				{ flag: 'pl', language: 'pl', title: 'Polski' }
			],
			user: user
		}
	},
	methods: {
		changeLocale(locale) {
			this.$i18n.locale = locale
		},
		logout() {
			localStorage.clear()

			user.logged = false
			user.token = ''
			user.username = ''
			user.role = ''
			user.exp = ''

			this.$router.push('/')
		}
	}
}

</script>

<style>
#app {
	text-align: center;
}
</style>
