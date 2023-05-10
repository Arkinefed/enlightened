<template>
	<div class="sample-pack-information">
		<div>{{ id }}</div>
		<div>{{ name }}</div>
		<div>{{ price }}</div>
		<div>{{ genreName }}</div>
		<div>{{ description }}</div>
		<div>{{ releaseDate }}</div>
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
