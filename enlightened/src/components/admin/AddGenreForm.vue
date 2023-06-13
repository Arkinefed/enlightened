<template>
	<form @submit.prevent="addGenre">
		<label for="g-name">{{ $t('genreName') }}</label>
		<input type="text" id="g-name" v-model="genreData.name">

		<button>{{ $t('addGenre') }}</button>
	</form>

	<p class="message">{{ message }}</p>
</template>

<script>
import { user } from '@/reactive/user'
import axios from 'axios'

export default {
	data() {
		return {
			genreData: {
				name: ''
			},
			message: ''
		}
	},
	mounted() {

	},
	methods: {
		addGenre() {
			if (this.genreData.name === '') {
				this.message = this.$t('invalidInput')
			} else {
				axios.post('http://localhost:8080/resource/genre/add',
					this.genreData,
					{
						headers: {
							Authorization: 'Bearer ' + user.token
						}
					})
					.then(response => {
						this.message = response.data
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
}
</script>

<style></style>
