<template>
	<div id="sample-pack">
		<template v-if="dataReceived">
			<h1>{{ samplePack.name }}</h1>

			<div class="sample-pack-container">
				<div class="img-container">
					<img :src="image" alt="sample pack image">
				</div>
				<div class="info-container">
					<div class="info-part">
						{{ samplePack.description }}
					</div>
					<div class="info-part">
						{{ samplePack.genreName }}
					</div>
					<div class="info-part">
						{{ samplePack.releaseDate.split('T')[0] }}
					</div>
					<div class="info-part">
						{{ samplePack.price }} zł
					</div>
					<div class="info-part cent pointer hover" @click.prevent="addToCart()">
						{{ $t('addToCart') }}
					</div>
				</div>
			</div>
		</template>
		<h2 v-else>{{ message }}</h2>
	</div>
</template>

<script>
import { cart } from '@/reactive/cart'
import axios from 'axios'

export default {
	data() {
		return {
			dataReceived: false,
			message: this.$t('loadingData'),
			samplePack: null,
			image: '',
			defaultIcon: require('@/assets/img/default.png')
		}
	},
	mounted() {
		this.getSamplePack(this.$route.params.id)
	},
	methods: {
		getSamplePack(id) {
			axios.get('http://localhost:8080/resource/sample-pack/id/' + id)
				.then(response => {
					this.samplePack = response.data

					if (this.samplePack.image != null) {
						this.image = 'data:image/jpeg;base64,' + this.samplePack.image
					} else {
						this.image = this.defaultIcon
					}

					this.dataReceived = true
				})
				.catch(error => {
					this.message = error.message
				})
		},
		addToCart() {
			if (!cart.items.includes(this.samplePack.id)) {
				cart.items.push(this.samplePack.id)
				localStorage.cart = JSON.stringify(cart.items)
			}
		}
	}
}
</script>

<style>
.sample-pack-container {
	display: flex;
	column-gap: 27px;
	width: 100%;
	padding: 0px 27px;
	font-size: 27px;
	box-sizing: border-box;
}

.img-container {
	background-color: black;
	color: white;
	width: 25%;
	padding: 27px;
}

.img-container img {
	max-width: 100%;
	height: auto;
	margin: 0px;
}

.info-container {
	background-color: black;
	color: white;
	width: 75%;
	display: flex;
	padding: 27px;
	flex-direction: column;
	row-gap: 27px;
}

.info-part {
	background-color: white;
	color: black;
	padding: 27px;
}

.cent {
	text-align: center;
}

.pointer {
	cursor: pointer;
	user-select: none;
}

.hover {
	border: 1px solid white;
}

.hover:hover {
	color: white;
	background-color: black;
	border: 1px solid white;
	box-sizing: border-box;
}
</style>
