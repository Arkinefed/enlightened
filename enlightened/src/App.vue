<template>
	<nav>
		<router-link to="/">{{ $t('home') }}</router-link>
		<router-link to="/sample-packs">sample packs</router-link>
		<router-link to="/about">{{ $t('about') }}</router-link>

		<a v-bind:class="{ 'active-language': entry.language === this.$i18n.locale }" v-for="entry in languages"
			:key="entry.title" @click="changeLocale(entry.language)">
			<flag :iso="entry.flag" v-bind:squared=false /> {{ entry.title }}
		</a>

		<template v-if="user.logged">
			<router-link to="/profile" class="account-link">{{ user.username }}</router-link>
			<router-link to="/logout">{{ $t('logout') }}</router-link>
		</template>
		<template v-else>
			<router-link to="/login" class="account-link">{{ $t('login') }}</router-link>
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
		}
	}
}
</script>

<style>
* {
	margin: 0px;
	padding: 0px;
}

#app {
	font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
}

button {
	padding: 13px;
	border: 1px solid black;
	background-color: black;
	color: white;
	font-size: 13px;
}

.active-button,
.active-language {
	background-color: white;
	color: black;
}

nav {
	font-size: 21px;
	box-sizing: border-box;
	display: flex;
	flex-direction: row;
	background-color: black;
	position: relative;
}

nav a {
	padding: 17px;
	text-decoration: none;
	background-color: black;
	color: white;
	width: 175px;
	text-align: center;
}

nav a:hover {
	background-color: white;
	color: black;
}

.account-link {
	margin-left: auto;
}

.router-link-active,
.router-link-exact-active {
	background-color: white;
	color: black;
}
</style>
