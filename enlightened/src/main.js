import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import i18n from '@/plugins/i18n'
import FlagIcon from 'vue-flag-icon'

createApp(App).use(router).use(i18n).use(FlagIcon).mount('#app')
