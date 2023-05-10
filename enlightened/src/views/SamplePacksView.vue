<template>
	<div class="sample-packs">
		<template v-if="dataReceived">
			<sample-pack-informations v-for="pack in samplePacks" v-bind:key="pack.id" :id="pack.id" :name="pack.name"
				:price="pack.price" :genreName="pack.genreName" :description="pack.description"
				:releaseDate="pack.releaseDate" />
		</template>
		<h2 v-else>{{ message }}</h2>
	</div>
</template>

<script>
import SamplePackInformations from '@/components/sample-packs/SamplePackInformations.vue'
import axios from 'axios'

export default {
	components: {
		SamplePackInformations
	},
	data() {
		return {
			dataReceived: false,
			message: 'loading data',
			samplePacks: []
		}
	},
	mounted() {
		this.getAllSamplePacks()
	},
	methods: {
		getAllSamplePacks() {
			axios.get('http://localhost:8080/resource/sample-pack/all')
				.then(response => {
					this.samplePacks = response.data
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
