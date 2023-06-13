<template>
	<div class="profile">
		<template v-if="dataReceived">
			<h1>{{ profileInfo.username }}</h1>

			<div class="profile-container">
				<div class="img-container dn">

				</div>
				<div class="info-container w-100">
					<div class="info-part">
						{{ profileInfo.firstName }}
					</div>
					<div class="info-part">
						{{ profileInfo.lastName }}
					</div>
					<div class="info-part">
						{{ profileInfo.registerDate.split('T')[0] }}
					</div>
					<router-link to="/orders">
						<div class="info-part cent pointer hover">
							zamówienia [{{ profileInfo.orderCount }}]
						</div>
					</router-link>
					<router-link to="/admin">
						<div v-if="profileInfo.role == 'admin'" class="info-part cent pointer hover">
							panel administratora
						</div>
					</router-link>
				</div>
			</div>
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
			dataReceived: false,
			message: 'loading data',
			profileInfo: null
		}
	},
	mounted() {
		if (!user.logged) {
			this.$router.push('/')
		}

		this.getProfileInfo()
	},
	methods: {
		getProfileInfo() {
			axios.get('http://localhost:8080/resource/user/profile',
				{
					headers: {
						Authorization: 'Bearer ' + user.token
					}
				})
				.then(response => {
					this.profileInfo = response.data
					this.dataReceived = true
				})
				.catch(error => {
					if (error.response) {
						this.message = error.response.data
					} else {
						this.message = error.message
					}
				})
		}
	}
}
</script>

<style>
.profile-container {
	display: flex;
	column-gap: 27px;
	width: 100%;
	padding: 0px 27px;
	font-size: 27px;
	box-sizing: border-box;
}

.info-container a {
	text-decoration: none;
}

.dn {
	display: none;
}

.w-100 {
	width: 100%;
}
</style>
