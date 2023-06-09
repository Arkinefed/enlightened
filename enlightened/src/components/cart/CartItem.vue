<template>
	<div class="cart-item">
		<div class="item-container">
			<router-link :to="'/sample-pack/' + id">
				<div class="item-container-inner">
					<div class="w-80">{{ name }}</div>
					<div class="w-20 a-r">{{ price }} zł</div>
				</div>
			</router-link>
		</div>
		<div class="remove cent pointer hover" @click.prevent="remove(id)">
			remove
		</div>
	</div>
</template>

<script>
export default {
	props: [
		'id',
		'name',
		'price'
	],
	methods: {
		remove(id) {
			this.$emit('remove', id)
		}
	}
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

.w-80 {
	width: 80%;
}

.remove {
	background-color: white;
	color: black;
	padding: 7px;
	margin-top: 17px;
}

.pointer {
	cursor: pointer;
	user-select: none;
}

.hover {
	border: 1px solid black;
}

.hover:hover {
	color: white;
	background-color: black;
	border: 1px solid white;
	box-sizing: border-box;
}
</style>
