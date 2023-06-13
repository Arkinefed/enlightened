<template>
	<div class="logout">
		<h2>{{ $t('loggedOut') }}</h2>
	</div>
</template>

<script>
import { cart } from '@/reactive/cart'
import { user } from '@/reactive/user'

export default {
	mounted() {
		localStorage.clear()

		user.logged = false
		user.token = ''
		user.username = ''
		user.role = ''
		user.exp = ''

		cart.items = []
	}
}
</script>

<style></style>
