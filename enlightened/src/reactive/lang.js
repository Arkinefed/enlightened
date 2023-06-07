import { reactive } from 'vue'

export const lang = reactive({
	locale: localStorage.locale || 'en'
})
