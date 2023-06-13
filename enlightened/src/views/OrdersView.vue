<template>
	<div class="orders">
		<h1>{{ $t('orders') }}</h1>

		<template v-if="dataReceived">
			<table>
				<tr>
					<th>{{ $t('price') }}</th>
					<th>{{ $t('count') }}</th>
					<th>{{ $t('orderDate') }}</th>
				</tr>
				<tr v-for="order in orders" :key="order.date">
					<td>
						{{ order.price }} zł
					</td>
					<td>
						{{ order.count }}
					</td>
					<td>
						{{ order.date }}
					</td>
				</tr>
			</table>
		</template>
		<h2 v-else>{{ message }}</h2>
	</div>
</template>

<script>
import { user } from '@/reactive/user'
import axios from 'axios'

export default {
	data() {
		return {
			orders: [],
			dataReceived: false,
			message: this.$t('loadingData')
		}
	},
	mounted() {
		this.getOrders()
	},
	methods: {
		getOrders() {
			axios.get('http://localhost:8080/resource/order/get',
				{
					headers: {
						Authorization: 'Bearer ' + user.token
					}
				})
				.then(response => {
					this.orders = response.data
					this.dataReceived = true
				})
				.catch(error => {
					this.message = error.message
				})
		}
	}
}
</script>

<style>
.orders {
	margin: 27px;
}

.orders table {
	text-align: center;
}
</style>
