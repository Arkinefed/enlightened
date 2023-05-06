<template>
	<form @submit.prevent="register">
		<label for="username">username</label>
		<input type="text" id="login" v-model="formData.username">

		<label for="password">password</label>
		<input type="password" id="password" v-model="formData.password">

		<label for="firstName">first name</label>
		<input type="text" id="firstName" v-model="formData.firstName">

		<label for="lastName">last name</label>
		<input type="text" id="lastName" v-model="formData.lastName">

		<button>zarejestruj</button>
	</form>

	<p class="message">{{ message }}</p>
</template>

<script>
import axios from 'axios'

export default {
	name: 'RegisterForm',
	data() {
		return {
			formData: {
				username: '',
				password: '',
				firstName: '',
				lastName: ''
			},
			message: ''
		}
	},
	methods: {
		register() {
			this.formData.username = this.formData.username.replace(/\s/g, '')
			this.formData.password = this.formData.password.replace(/\s/g, '')
			this.formData.firstName = this.formData.firstName.replace(/\s/g, '')
			this.formData.lastName = this.formData.lastName.replace(/\s/g, '')

			if (this.formData.username === '' || this.formData.password === '' || this.formData.firstName === '' || this.formData.lastName === '') {
				this.message = 'invalid input'
			} else {
				axios.post('http://localhost:8080/auth/register', this.formData)
					.then(response => {
						this.message = response.data.message
					})
					.catch(error => {
						this.message = error.response.data.message
					})
			}
		}
	}
}
</script>

<style></style>
