import { reactive } from 'vue'

export const user = reactive({
	logged: localStorage.logged === 'true',
	token: localStorage.token || '',
	username: localStorage.username || '',
	role: localStorage.role || '',
	exp: localStorage.exp || '',

	locale: localStorage.locale || 'en'
})
