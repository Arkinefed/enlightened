<template>
	<div>
		<button v-for="entry in languages" :key="entry.title" @click="changeLocale(entry.language)">
			<flag :iso="entry.flag" v-bind:squared=false /> {{ entry.title }}
		</button>
	</div>
	<nav>
		<router-link to="/">Home</router-link> |
		<router-link to="/about">About</router-link> |

		<router-link v-if="logged" to="/">{{ username }}</router-link>
		<router-link v-else to="/login">Login</router-link>
	</nav>
	<router-view />
</template>

<script>
export default {
	data() {
		return {
			logged: false,
			username: '',
			languages: [
				{ flag: 'us', language: 'en', title: 'English' },
				{ flag: 'pl', language: 'pl', title: 'Polski' }
			]
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
#app {
	text-align: center;
}
</style>
