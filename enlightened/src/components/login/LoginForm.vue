<template>
	<form @submit.prevent="login">
		<label for="username">{{ $t('username') }}</label>
		<input type="text" id="username" v-model="formData.username">

		<label for="password">{{ $t('password') }}</label>
		<input type="password" id="password" v-model="formData.password">

		<button>{{ $t('login') }}</button>
	</form>

	<p class="message">{{ message }}</p>
</template>

<script>
import { user } from '@/reactive/user'
import axios from 'axios'

export default {
	name: 'LoginForm',
	data() {
		return {
			formData: {
				username: '',
				password: ''
			},
			message: ''
		}
	},
	methods: {
		login() {
			if (this.formData.name === '' || this.formData.password === '') {
				this.message = this.$t('invalidInput')
			} else {
				axios.post('http://localhost:8080/auth/login', this.formData)
					.then(response => {
						this.message = this.$t('loggedIn')

						localStorage.logged = user.logged = true
						localStorage.token = user.token = response.data.token

						this.extractDataFromToken()
					})
					.catch(error => {
						if (error.response) {
							this.message = error.response.data.logged ? this.$t('loggedIn') : this.$t('couldntLogIn')
						} else {
							this.message = this.$t('connectionError')
						}
					})
			}
		},
		extractDataFromToken() {
			const parts = localStorage.getItem('token').split('.')
			const payload = JSON.parse(window.atob(parts[1]))

			localStorage.username = user.username = payload.username
			localStorage.role = user.role = payload.role
			localStorage.exp = user.exp = payload.exp

			this.$router.push('/')
		}
	}
}
</script>

<style></style>
