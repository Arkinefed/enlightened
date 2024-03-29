import { createI18n } from 'vue-i18n'

const i18n = createI18n({
	locale: 'en',
	fallbackLocale: 'en',
	messages: {
		en: require('@/locales/en.json'),
		pl: require('@/locales/pl.json')
	}
})

export default i18n
