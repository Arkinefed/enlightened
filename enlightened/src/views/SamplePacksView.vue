<template>
	<div class="sample-packs">
		<h1>{{ $t('samplePacks') }}</h1>

		<div class="search">
			<div class="w-80 search-box">
				<input type="text" placeholder="wyszukaj" v-model="part">
			</div>
			<div class="w-20 search-div pointer" @click.prevent="findSamplePacks()">
				{{ $t('search') }}
			</div>
		</div>

		<div class="search">
			<div class="w-10 search-box c">
				<input type="number" min="1" :max="pageCount" placeholder="page" v-model="page">
			</div>
			<div class="w-5 of c">
				{{ $t('of') }}
			</div>
			<div class="w-5 of c">
				{{ pageCount }}
			</div>
			<div class="w-80 search-div pointer" @click.prevent="changePage()">
				{{ $t('changePage') }}
			</div>
		</div>

		<template v-if="dataReceived">
			<sample-pack-informations v-for="(pack) in displayed" v-bind:key="pack.id" :id="pack.id" :name="pack.name"
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
			message: this.$t('loadingData'),
			samplePacks: [],
			part: '',
			displayed: [],
			pageSize: 2,
			page: 1,
			pageCount: 0
		}
	},
	mounted() {
		this.getAllSamplePacks()
	},
	methods: {
		getAllSamplePacks() {
			this.dataReceived = false
			this.message = this.$t('loadingData')

			axios.get('http://localhost:8080/resource/sample-pack/all')
				.then(response => {
					this.samplePacks = response.data
					this.dataReceived = true

					this.pageCount = Math.ceil(this.samplePacks.length / this.pageSize)

					this.changePage()
				})
				.catch(error => {
					this.message = error.message
				})
		},
		findSamplePacks() {
			this.dataReceived = false
			this.message = this.$t('loadingData')

			if (this.part === '') {
				this.getAllSamplePacks()
			} else {
				axios.get('http://localhost:8080/resource/sample-pack/find/' + this.part)
					.then(response => {
						this.samplePacks = response.data
						this.dataReceived = true
					})
					.catch(error => {
						this.message = error.message
					})
			}
		},
		changePage() {
			if (this.page > this.pageCount) {
				this.page = this.pageCount
			}

			if (this.page < 1) {
				this.page = 1
			}

			this.displayed = []

			for (let i = 0; i < this.samplePacks.length; i++) {
				if ((this.page - 1) * this.pageSize + this.pageSize > i && (this.page - 2) * this.pageSize + this.pageSize <= i) {
					this.displayed.push(this.samplePacks[i])
				}
			}
		}
	}
}
</script>

<style>
.search {
	display: flex;
	flex-direction: row;
	column-gap: 27px;
	padding: 27px;
	font-size: 27px;
	box-sizing: border-box;
	background-color: black;
	margin: 27px;
}

.search-box {
	display: flex;
}

.search-box input {
	font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
	padding: 17px;
	font-size: 27px;
	width: 100%;
}

.search-div {
	color: white;
	font-size: 27px;
	text-align: center;
	font-size: 27px;
	padding: 17px;
	border: 1px solid white;
}

.of {
	color: white;
	display: flex;
	padding: 17px;
}

.c {
	justify-content: center;
}
</style>
