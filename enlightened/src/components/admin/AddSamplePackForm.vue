<template>
	<form @submit.prevent="addSamplePack">
		<label for="sp-name">sample pack name</label>
		<input type="text" id="sp-name" v-model="samplePackData.name">

		<label for="sp-price">price</label>
		<input type="number" min="0" id="sp-price" v-model="samplePackData.price">

		<label for="sp-genre">genre</label>
		<select id="sp-price" v-model="samplePackData.genre">
			<option v-for="genre in genres" v-bind:key="genre" id="sp-genre" :value="genre">{{ genre }}</option>
		</select>

		<label for="sp-description">description</label>
		<textarea rows="5" id="sp-description" v-model="samplePackData.description" />

		<label for="sp-releaseDate">release date</label>
		<input type="date" id="sp-releaseDate" v-model="releaseDate" />

		<button>add sample pack</button>
	</form>

	<p class="message">{{ message }}</p>
</template>

<script>
import { user } from '@/reactive/user'
import axios from 'axios'

export default {
	data() {
		return {
			samplePackData: {
				name: '',
				price: 0.0,
				genre: '',
				description: '',
				releaseDate: ''
			},
			releaseDate: new Date().toISOString().slice(0, 10),
			message: '',
			genres: ['loading data']
		}
	},
	mounted() {
		this.getGenres()
	},
	methods: {
		addSamplePack() {
			this.samplePackData.releaseDate = this.releaseDate + 'T00:00:00'

			if (this.samplePackData.name === '' ||
				this.samplePackData.genre === '' ||
				this.samplePackData.description === '') {
				this.message = 'invalid input'
			} else {
				axios.post('http://localhost:8080/resource/sample-pack/add',
					this.samplePackData,
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
		},
		getGenres() {
			axios.get('http://localhost:8080/resource/genre/all')
				.then(response => {
					this.genres = response.data
				})
				.catch(error => {
					this.message = error.message
				})
		}
	}
}
</script>

<style></style>
