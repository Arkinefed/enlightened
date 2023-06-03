<template>
	<div id="sample-pack">
		<template v-if="dataReceived">
			<h1>{{ samplePack.name }}</h1>
		</template>
		<h2 v-else>{{ message }}</h2>
	</div>
</template>

<script>
import axios from 'axios'

export default {
	data() {
		return {
			dataReceived: false,
			message: 'loading data',
			samplePack: null
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
					this.dataReceived = true
				})
				.catch(error => {
					this.message = error.message
				})
		}
	}
}
</script>

<style></style>
