<template>
	<div class="logout">
		<h2>wylogowano</h2>
	</div>
</template>

<script>
import { user } from '@/reactive/user'

export default {
	mounted() {
		localStorage.clear()

		user.logged = false
		user.token = ''
		user.username = ''
		user.role = ''
		user.exp = ''
	}
}
</script>

<style></style>
