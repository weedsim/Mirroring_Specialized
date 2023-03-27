<template>
  <div>
    <button @click="connectWallet()">1jkashbdfgjkhasdgjkh</button>
  </div>
</template>

<script>
export default {
  name: "SignUpView",
  components: {

  },
  data () {

    return {
      address: null,
      email: null,
      nickname: null,
      phone: null,
      role: null,
      company: null,
    }
  },
  created () {
    
  },
  methods: {
    async connectWallet(){ //메타마스크 연결하기 눌렀을 때 지갑 주소 받기 이후에 백에 보낼 address를 위해
      this.address = await this.$store.dispatch('getAccount');
      console.log(this.address);
      const payload = {
        address: this.address,
        email : this.email,
        nickname: this.nickname,
        phone: this.phone,
        role: this.role,
        company: this.company,
      }
      this.$store.dispatch('signup', payload)
    }
  },
  computed () {

  }
}
</script>

<style>

</style>