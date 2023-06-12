<template>
	<div class="genre-table">
		<table>
			<tr>
				<th>
					sample packs
				</th>
			</tr>
			<tr v-for="(pack, index) in packs" v-bind:key="pack.id">
				<td class="search-box">
					<input type="text" v-model="packs[index].name">
				</td>
				<td class="search-box">
					<input type="text" v-model="packs[index].price">
				</td>
				<td class="search-box">
					<input type="text" v-model="packs[index].genreName">
				</td>
				<td class="search-box">
					<input type="text" v-model="packs[index].description">
				</td>
				<td class="search-box">
					<input type="text" v-model="packs[index].releaseDate">
				</td>
				<td class="clickable" @click.prevent="removeSamplePack(index)">
					remove
				</td>
				<td class="clickable" @click.prevent="updateSamplePack(index)">
					update
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
			packs: ['loading data']
		}
	},
	mounted() {
		this.getSamplePacks()
	},
	methods: {
		getSamplePacks() {
			axios.get('http://localhost:8080/resource/sample-pack/all')
				.then(response => {
					this.packs = response.data
				})
				.catch(error => {
					this.packs = error.message
				})
		},
		removeSamplePack(index) {
			axios.post('http://localhost:8080/resource/sample-pack/delete',
				this.packs[index],
				{
					headers: {
						Authorization: 'Bearer ' + user.token
					}
				})
				.then(response => {
					this.message = response.data

					this.getSamplePacks()
				})
				.catch(error => {
					this.message = error.message
				})
		},
		updateSamplePack(index) {
			axios.post('http://localhost:8080/resource/sample-pack/update',
				this.packs[index],
				{
					headers: {
						Authorization: 'Bearer ' + user.token
					}
				})
				.then(response => {
					this.message = response.data

					this.getSamplePacks()
				})
				.catch(error => {
					this.message = error.message
				})
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
