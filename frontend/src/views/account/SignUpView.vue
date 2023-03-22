<template>
  <div>
    <button @click="this.connectWallet()">1jkashbdfgjkhasdgjkh</button>
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
      const accounts = await window.ethereum.request({ method: 'eth_requestAccounts' });

      this.address = accounts[0];
    }
  },
  computed () {

  }
}
</script>

<style>

</style>