<template>
	<div class="login">
		<LoginForm v-if="login" />
		<RegisterForm v-else />
	</div>

	<button @click="changeToRegister()">zarejestruj</button>
	<button @click="changeToLogin()">zaloguj</button>
</template>

<script>
import LoginForm from '@/components/login/LoginForm.vue'
import RegisterForm from '@/components/login/RegisterForm.vue'

export default {
	name: 'LoginView',
	components: {
		LoginForm,
		RegisterForm
	},
	data() {
		return {
			login: true
		}
	},
	methods: {
		changeToRegister() {
			this.login = false
		},
		changeToLogin() {
			this.login = true
		}
	}
}
</script>

<style></style>
