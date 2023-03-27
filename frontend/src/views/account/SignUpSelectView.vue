<template>
  <div class="LogoTitle">
    <v-img src="@/assets/logo.png" class="logo"></v-img>
    <div class="title">회원 가입</div>
  </div>
  <div class="button-container">
    <div class="button-group">
      <button class="artist" @click="isartist()">
        <v-img src="@/assets/Micro.png" alt="artistLogo" style="height: 15vh ; width: 15vw;"></v-img>
        ARTIST
      </button>
      <div class="button-gap"></div>
      <button class="fan" @click="isfan()">
        <v-img src="@/assets/fan-club.png" alt="fanLogo" style="height: 15vh ; width: 15vw;"></v-img>
        FAN
      </button>
    </div>
    <div class="button-gap2"></div>
    <button class="login">이미 계정이 있다면 로그인</button>
  </div>
  
</template>

<script>

export default {
  name: "SignUpSelectView",
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
    isartist () {
      this.$store.state.isFan = false;
      this.$router.push('/signup');
    },
    isfan() {
      this.$store.state.isFan = true;
      this.$router.push('/signup')
    },
  },
  computed () {
      
  }
  
}
</script>

<style>

.LogoTitle {
  display: flex;
  align-items: center;
  /* flex-direction: column; */
  position: absolute;
  top: 20vh;
  left: 5vw;
}

.logo {
  display: flex;
  /* vertical-align: top; */
  height: 30px;
  width: 60px;
  margin-right: 10px;
}

.title {
  display: flex;
  height: 50px;
  width: 80px;
  font-size: 40px;
  color: purple;
}

.button-container {
  display: flex;
  flex-direction: column ;
  justify-content: center;
  align-items: center;
  height: 74vh;
}

.button-group {
  display: flex;
  justify-content: center;
}

.button-gap2 {
  display: flex;
  width: 1vw;
  height: 3vh;
}

.artist {
  display: flex;
  float: left;
  /* flex-direction: center; */
  flex-direction: column;
  align-items: center;
  justify-content: center;
  
  width: 30vw;
  height: 30vh;
  left: 1vw;
  top: 1vh;
  
  border: none;
  background: #FFFFFF;
  box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.25);
  border-radius: 25px;
  padding: 12px 24px;
  margin: 0 8px;
  font-size: 16px;
  cursor: pointer;
}

.button-gap {
  display: flex;
  width: 1vw;

}

.fan {
  display: flex;
  /* flex-direction: center; */
  flex-direction: column;
  align-items: center;
  justify-content: center;
  
  width: 30vw;
  height: 30vh;
  left: 4vw;
  top: 4vh;
  /* border: none; */
  background: #FFFFFF;
  box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.25);
  border-radius: 25px;
  padding: 12px 24px;
  margin: 0 8px;
  font-size: 16px;
  cursor: pointer;
}

.login {
  display: flex;
  justify-content: center;
  flex: center;
  color: blue;
}

</style>