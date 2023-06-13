<template>
	<div class="genre-table">
		<table>
			<tr>
				<th>{{ $t('genres') }}</th>
			</tr>
			<tr v-for="(genre, index) in genres" v-bind:key="genre.id">
				<td class="search-box">
					<input type="text" v-model="genres[index].name">
				</td>
				<td class="clickable" @click.prevent="removeGenre(genres[index].name)">
					{{ $t('remove') }}
				</td>
				<td class="clickable" @click.prevent="updateGenre(genres[index].id, genres[index].name)">
					{{ $t('update') }}
				</td>
			</tr>
		</table>

		<p class="message">{{ message }}</p>
	</div>
</template>

<script>
import { user } from '@/reactive/user'
import axios from 'axios'

export default {
	data() {
		return {
			message: '',
			genres: [this.$t('loadingData')]
		}
	},
	mounted() {
		this.getGenres()
	},
	methods: {
		getGenres() {
			axios.get('http://localhost:8080/resource/genre/all')
				.then(response => {
					this.genres = response.data
				})
				.catch(error => {
					this.message = error.message
				})
		},
		removeGenre(name) {
			if (name === '') {
				this.message = this.$t('invalidInput')
			} else {
				axios.post('http://localhost:8080/resource/genre/delete',
					{ name },
					{
						headers: {
							Authorization: 'Bearer ' + user.token
						}
					})
					.then(response => {
						this.message = response.data

						this.getGenres()
					})
					.catch(error => {
						this.message = error.message
					})
			}
		},
		updateGenre(id, name) {
			if (name === '') {
				this.message = this.$t('invalidInput')
			} else {
				axios.post('http://localhost:8080/resource/genre/update',
					{ id, name },
					{
						headers: {
							Authorization: 'Bearer ' + user.token
						}
					})
					.then(response => {
						this.message = response.data

						this.getGenres()
					})
					.catch(error => {
						this.message = error.message
					})
			}
		}
	}
}
</script>

<style>
.genre-table {
	margin: 27px;
	display: flex;
	flex-direction: column;
}

table {
	border: 1px solid black;
	width: 100%;
}

table,
th,
td {
	border: 1px solid black;
	border-collapse: collapse;
}

th,
td {
	padding: 17px;
}

td.clickable {
	text-align: center;
	cursor: pointer;
	user-select: none;
}

td.search-box {
	padding: 0px;
	border-width: 0px;
}
</style>
