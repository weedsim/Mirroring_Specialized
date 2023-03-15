<template>
  <hr />
  <br />
  <div class="footer">
    <img :src="require('@/assets/logo.png')" alt="" class="header-logo" />
    <img :src="require('@/assets/logo.png')" alt="" class="header-logo" />
    <img :src="require('@/assets/logo.png')" alt="" class="header-logo" />
  </div>
  <br />
</template>

<script>
export default {
  name: "AllFooter",
}
</script>

<style>
.footer {
  display: flex;
  justify-content: space-evenly;
}
</style>
