<template>
  <div>

  </div>
</template>
<script setup>
import router from '@/router/router'
import { onMounted } from 'vue'
onMounted(() => {
  router.push('./person')
})
</script>
