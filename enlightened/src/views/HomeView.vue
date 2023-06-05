<template>
	<div class="home">
		<img alt="Vue logo" src="../assets/logo.png">
		<HomeInformations />
	</div>
</template>

<script>
import HomeInformations from '@/components/home/HomeInformations.vue'

export default {
	name: 'HomeView',
	components: {
		HomeInformations
	}
}
</script>

<style>
.home {
	display: flex;
	flex-direction: column;
	width: 100%;
	height: 90vh;
	justify-content: center;
	align-items: center;
}

img {
	height: 50vh;
	margin: 47px;
}
</style>
