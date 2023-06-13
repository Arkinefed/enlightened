<template>
	<div class="admin">
		<template v-if="dataReceived">
			<h1>sample packs</h1>

			<add-sample-pack-form />
			<sample-pack-table />

			<h1>genres</h1>

			<add-genre-form />
			<genre-table />

			<h1>users</h1>

			<user-table />
		</template>
		<h2 v-else>{{ message }}</h2>
	</div>
</template>

<script>
import { user } from '@/reactive/user'
import axios from 'axios'

import AddGenreForm from '@/components/admin/AddGenreForm.vue'
import AddSamplePackForm from '@/components/admin/AddSamplePackForm.vue'
import GenreTable from '@/components/admin/GenreTable.vue'
import SamplePackTable from '@/components/admin/SamplePackTable.vue'
import UserTable from '@/components/admin/UserTable.vue'

export default {
	components: {
		AddGenreForm,
		AddSamplePackForm,
		GenreTable,
		SamplePackTable,
		UserTable
	},
	data() {
		return {
			dataReceived: false,
			message: 'loading data',
			admin: false
		}
	},
	mounted() {
		if (!user.logged) {
			this.$router.push('/')
		}

		this.verifyIfAdmin()
	},
	methods: {
		verifyIfAdmin() {
			axios.get('http://localhost:8080/resource/user/admin',
				{
					headers: {
						Authorization: 'Bearer ' + user.token
					}
				})
				.then(response => {
					this.admin = response.data
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

<style></style>
