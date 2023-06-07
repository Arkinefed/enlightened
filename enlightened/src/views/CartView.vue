<template>
	<div class="cart">
		<h1>cart</h1>

		<template v-if="Object.keys(cart).length">
			<template v-if="dataReceived">
				<cart-item />
			</template>
			<h2 v-else>{{ message }}</h2>
		</template>
		<h2 v-else>cart is empty</h2>
	</div>
</template>

<script>
import CartItem from '@/components/cart/CartItem.vue'
import { cart } from '@/reactive/cart'

export default {
	components: {
		CartItem
	},
	data() {
		return {
			cart: {},
			dataReceived: false,
			message: 'loading data',
			samplePacks: []
		}
	},
	mounted() {
		for (const i of cart.items) {
			if (!this.cart[i]) {
				this.cart[i] = 1
			} else {
				this.cart[i]++
			}
		}

		if (Object.keys(cart).length) {
			;
		}
	}
}
</script>

<style></style>
