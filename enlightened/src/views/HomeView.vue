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

<style></style>
