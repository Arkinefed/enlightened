<template>
	<form @submit.prevent="login">
		<label for="username">username</label>
		<input type="text" id="username" v-model="formData.username">

		<label for="password">password</label>
		<input type="password" id="password" v-model="formData.password">

		<button>zaloguj</button>
	</form>

	<p class="message">{{ message }}</p>
</template>

<script>
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
				this.message = 'invalid input'
			} else {
				axios.post('http://localhost:8080/auth/login', this.formData)
					.then(response => {
						this.message = 'logged in'

						localStorage.setItem('logged', true)
						localStorage.setItem('token', response.data.token)

						this.extractDataFromToken()
					})
					.catch(error => {
						this.message = error.response.data.logged ? 'logged in' : 'couldn\'t log in'
					})
			}
		},
		extractDataFromToken() {
			const parts = localStorage.getItem('token').split('.')

			const payload = JSON.parse(window.atob(parts[1]))

			localStorage.setItem('name', payload.name)
			localStorage.setItem('role', payload.role)
			localStorage.setItem('exp', payload.exp)
		}
	}
}
</script>

<style></style>
