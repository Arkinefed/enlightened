<template>
	<div class="sample-pack-information">
		<router-link :to="'/sample-pack/' + id">
			<div>{{ id }}</div>
			<div>{{ name }}</div>
			<div>{{ price }}</div>
			<div>{{ genreName }}</div>
			<div>{{ description }}</div>
			<div>{{ releaseDate }}</div>
		</router-link>
	</div>
</template>

<script>
export default {
	props: [
		'id',
		'name',
		'price',
		'genreName',
		'description',
		'releaseDate'
	]
}
</script>

<style></style>
