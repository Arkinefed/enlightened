<template>
	<div class="cart">
		<h1>cart</h1>

		<template v-if="Object.keys(cart).length">
			<template v-if="samplePacks.length == Object.keys(cart).length">
				<div class="cart-container">
					<div class="cart-info-container">
						<cart-item v-for="item in samplePacks" v-bind:key="item.id" :id="item.id" :name="item.name"
							:count="cart[item.id]" :price="item.price" />
					</div>
					<div class="order-container">
						<div class="order-headline">
							your order
						</div>
						<div class="flex-row">
							<div>
								price:
							</div>
							<div>
								{{ price }} zł
							</div>
						</div>
						<div class="order-part cent pointer hover" @click.prevent="placeOrder()">
							place order
						</div>
					</div>
				</div>
			</template>
			<h2 v-else>{{ message }}</h2>
		</template>
		<h2 v-else>cart is empty</h2>
	</div>
</template>

<script>
import axios from 'axios'

import CartItem from '@/components/cart/CartItem.vue'
import { cart } from '@/reactive/cart'

export default {
	components: {
		CartItem
	},
	data() {
		return {
			cart: {},
			price: 0,
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
			for (const i in this.cart) {
				this.getSamplePack(i)
			}
		}
	},
	methods: {
		getSamplePack(id) {
			axios.get('http://localhost:8080/resource/sample-pack/id/' + id)
				.then(response => {
					const pack = response.data

					this.samplePacks.push(pack)
					this.price += pack.price * this.cart[id]
				})
				.catch(error => {
					this.message = error.message
				})
		},
		placeOrder() {

		}
	}
}
</script>

<style>
.cart-container {
	display: flex;
	column-gap: 27px;
	width: 100%;
	padding: 0px 27px;
	font-size: 27px;
	box-sizing: border-box;
}

.cart-info-container {
	background-color: black;
	color: white;
	width: 75%;
	display: flex;
	padding: 27px;
	flex-direction: column;
	row-gap: 27px;
}

.order-container {
	background-color: black;
	color: white;
	width: 25%;
	display: flex;
	padding: 27px;
	flex-direction: column;
	row-gap: 27px;
}

.order-headline {
	font-size: 37px;
	text-align: center;
	padding: 27px;
}

.flex-row {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
}

.order-part {
	padding: 27px;
}

.pointer {
	cursor: pointer;
	user-select: none;
}

.hover {
	border: 1px solid white;
}

.hover:hover {
	color: black;
	background-color: white;
	border: 1px solid white;
	box-sizing: border-box;
}
</style>
