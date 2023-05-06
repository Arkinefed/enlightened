<template>
	<template class="switch">
		<button @click="changeToRegister()">zarejestruj</button>
		<button @click="changeToLogin()">zaloguj</button>
	</template>

	<div class="login">
		<LoginForm v-if="login" />
		<RegisterForm v-else />
	</div>
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

<style>
form {
	display: flex;
	flex-direction: column;
	row-gap: 7px;
	width: 50%;
	margin: auto;
	border: 1px solid black;
	padding: 17px;
}

form * {
	padding: 7px;
}

form label {
	font-size: 17px;
}

.switch {
	display: flex;
	justify-content: center;
	column-gap: 17px;
	width: 50%;
	margin: auto;
	padding: 7px;
}
</style>
