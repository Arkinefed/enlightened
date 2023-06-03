<template>
	<div class="sample-pack-information">
		<div class="spi-container">
			<router-link :to="'/sample-pack/' + id">
				<div class="spi-container-inner">
					<div class="w-60">{{ name }}</div>
					<div class="w-20">{{ genreName }}</div>
					<div class="w-20 a-r">{{ price }} zł</div>
				</div>
			</router-link>
		</div>
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

<style>
.spi-container {
	margin: 27px;
	padding: 27px;
	font-size: 27px;
	box-sizing: border-box;
	background-color: black;
}

.spi-container a {
	color: white;
	text-decoration: none;
}

.spi-container-inner {
	display: flex;
}

.w-60 {
	width: 60%;
}

.w-20 {
	width: 20%;
}

.w-10 {
	width: 10%;
}

.a-r {
	text-align: right;
}
</style>
