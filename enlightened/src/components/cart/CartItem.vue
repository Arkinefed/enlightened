<template>
	<div class="cart-item">
		<div class="item-container">
			<router-link :to="'/sample-pack/' + id">
				<div class="item-container-inner">
					<div class="w-60">{{ name }}</div>
					<div class="w-10">{{ count }}</div>
					<div class="w-10 a-r">{{ price }} zł</div>
					<div class="w-20"></div>
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
		'count',
		'price'
	]
}
</script>

<style>
.item-container {
	padding: 27px;
	font-size: 27px;
	box-sizing: border-box;
	color: black;
	background-color: white;
}

.item-container a {
	color: black;
	text-decoration: none;
}

.item-container-inner {
	display: flex;
}
</style>
