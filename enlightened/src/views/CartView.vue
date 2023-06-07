<template>
	<div class="cart">
		{{ JSON.stringify(cart.items) }}
	</div>
</template>

<script>
import { cart } from '@/reactive/cart'

export default {
	data() {
		return {
			cart: cart
		}
	}
}
</script>

<style></style>
