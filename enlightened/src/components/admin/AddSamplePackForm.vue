<template>
	<form @submit.prevent="addSamplePack">
		<label for="sp-name">{{ $t('samplePackName') }}</label>
		<input type="text" id="sp-name" v-model="samplePackData.name">

		<label for="sp-price">{{ $t('price') }}</label>
		<input type="number" min="0" id="sp-price" v-model="samplePackData.price">

		<label for="sp-genre">{{ $t('genre') }}</label>
		<select id="sp-price" v-model="samplePackData.genre">
			<option v-for="genre in genres" v-bind:key="genre.id" id="sp-genre" :value="genre.name">
				{{ genre.name }}
			</option>
		</select>

		<label for="sp-description">{{ $t('description') }}</label>
		<textarea rows="5" id="sp-description" v-model="samplePackData.description" />

		<label for="sp-image">{{ $t('image') }}</label>
		<input type="file" id="sp-image" @change="fileSelected" />

		<label for="sp-releaseDate">{{ $t('releaseDate') }}</label>
		<input type="date" id="sp-releaseDate" v-model="releaseDate" />

		<button>{{ $t('addSamplePack') }}</button>
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
				image: undefined,
				releaseDate: ''
			},
			releaseDate: new Date().toISOString().slice(0, 10),
			message: '',
			genres: [this.$t('loadingData')]
		}
	},
	mounted() {
		this.getGenres()
	},
	methods: {
		fileSelected(event) {
			this.samplePackData.image = event.target.files[0]
		},
		addSamplePack() {
			this.samplePackData.releaseDate = this.releaseDate + 'T00:00:00'

			if (this.samplePackData.name === '' ||
				this.samplePackData.genre === '' ||
				this.samplePackData.description === '') {
				this.message = this.$t('invalidInput')
			} else {
				const formData = new FormData()

				formData.append('name', this.samplePackData.name)
				formData.append('price', this.samplePackData.price)
				formData.append('genre', this.samplePackData.genre)
				formData.append('description', this.samplePackData.description)
				formData.append('image', this.samplePackData.image)
				formData.append('releaseDate', this.samplePackData.releaseDate)

				axios.post('http://localhost:8080/resource/sample-pack/add',
					formData,
					{
						headers: {
							Authorization: 'Bearer ' + user.token,
							'Content-Type': 'multipart/form-data'
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
