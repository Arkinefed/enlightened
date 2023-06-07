import { reactive } from 'vue'

export const cart = reactive({
	items: JSON.parse(localStorage.cart) || []
})
