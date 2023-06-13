<template>
	<div class="genre-table">
		<table>
			<tr>
				<th>
					{{ $t('users') }}
				</th>
			</tr>
			<tr v-for="(user, index) in users" v-bind:key="user.id">
				<td class="search-box">
					<input type="text" v-model="users[index].username">
				</td>
				<td class="search-box">
					<input type="text" v-model="users[index].password">
				</td>
				<td class="search-box">
					<input type="text" v-model="users[index].role">
				</td>
				<td class="search-box">
					<input type="text" v-model="users[index].firstName">
				</td>
				<td class="search-box">
					<input type="text" v-model="users[index].lastName">
				</td>
				<td class="search-box">
					<input type="text" v-model="users[index].registerDate">
				</td>
				<td class="clickable" @click.prevent="removeUser(index)">
					{{ $t('remove') }}
				</td>
				<td class="clickable" @click.prevent="updateUser(index)">
					{{ $t('remove') }}
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
			users: [this.$t('loadingData')]
		}
	},
	mounted() {
		this.getUsers()
	},
	methods: {
		getUsers() {
			axios.get('http://localhost:8080/resource/user/all',
				{
					headers: {
						Authorization: 'Bearer ' + user.token
					}
				})
				.then(response => {
					this.users = response.data
				})
				.catch(error => {
					console.log(error)
					this.message = error.message
				})
		},
		removeUser(index) {
			axios.post('http://localhost:8080/resource/user/delete',
				this.users[index],
				{
					headers: {
						Authorization: 'Bearer ' + user.token
					}
				})
				.then(response => {
					this.message = response.data

					this.getUsers()
				})
				.catch(error => {
					this.message = error.message
				})
		},
		updateUser(index) {
			axios.post('http://localhost:8080/resource/user/update',
				this.users[index],
				{
					headers: {
						Authorization: 'Bearer ' + user.token
					}
				})
				.then(response => {
					this.message = response.data

					this.getUsers()
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
