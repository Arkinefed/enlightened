<template>
	<div>
		<button v-for="entry in languages" :key="entry.title" @click="changeLocale(entry.language)">
			<flag :iso="entry.flag" v-bind:squared=false /> {{ entry.title }}
		</button>
	</div>
	<nav>
		<router-link to="/">Home</router-link> |
		<router-link to="/about">About</router-link>
	</nav>
	<router-view />
</template>

<script>
export default {
	data() {
		return {
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
	font-family: Avenir, Helvetica, Arial, sans-serif;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
	text-align: center;
	color: #2c3e50;
}

nav {
	padding: 30px;
}

nav a {
	font-weight: bold;
	color: #2c3e50;
}

nav a.router-link-exact-active {
	color: #42b983;
}

button {
	padding: 15px;
	border: 1px solid green;
	font-size: 18px;
	margin: 15px;
}
</style>
