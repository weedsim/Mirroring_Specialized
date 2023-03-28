<template>
  <div class="entire-select">
    <div style="flex-direction: column;">

    
      
      <div class="title-container">
        <div class="title-select">
          회원 가입
        </div>
        <div>
          고객님께서 해당되는 회원 종류를 선택해주세요
        </div>
      </div>
      <div class="button-container">
        <div class="button-group ">
          <div class="button-hover">
            <button class="artist-button" @click="isArtist">
              <v-img class="img-artist" src="@/assets/Micro.png" alt="artistLogo" style="height: 7vh ; width: 7vw;"></v-img>
              <span style="margin-bottom:3vh">
                ARTIST
              </span>
            </button>
          </div>
          <div class="button-gap"></div>
          <div class="button-hover">
            <button class="fan-button" @click="isFan">
              <v-img class="img-fan" src="@/assets/fan-club.png" alt="fanLogo" style="height: 7vh ; width: 7vw;"></v-img>
              <span style="margin-bottom:3vh">
                FAN
              </span>
            </button>
          </div>
        </div>
        <div class="button-gap2"></div>
        <div style="display: flex; justify-content: center; margin-top: 30px;">
          이미 계정이 있다면&nbsp; 
          <button class="login">로그인</button>

        </div>
      </div>

    </div>
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
    isArtist () {
      this.$store.state.isFan = false;
      this.$router.push('/signup');
    },
    isFan() {
      this.$store.state.isFan = true;
      this.$router.push('/signup')
    },
  },
  computed () {
      
  }
  
}
</script>

<style>
.entire-select{
  display: flex;
  justify-content: center;
}


.title-container {
  text-align: center;
  margin-top: 10px;
  margin-bottom: 30px;
}

.title-select {
  /* display: flex; */
  font-family:'KCC-Ganpan';
  font-size: 40px;
  font-weight: 200;
  background: linear-gradient(to right, #6A3FC1, #5B9BD5); 
  background-clip: text;
  -webkit-background-clip: text;
  color: transparent;
}

.button-container {
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

.artist-button {
  display: flex;
  float: left;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  
  width: 20vw;
  height: 40vh;
  left: 1vw;
  top: 1vh;
  
  border: none;
  background: #FFFFFF;
  /* box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.5); */
  
  border-radius: 25px;
  /* padding: 12px 24px;
  margin: 0 8px; */
  font-size: 16px;
  cursor: pointer;
}

.button-hover:not(:hover) .img-artist{
  transition: .3s;
}
.button-hover:hover .img-artist{
  transition: .3s;
  animation: vibration1 .3s infinite;
  /* box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.5); */
  /* transform: scale(1.2); */
}
.button-hover2:hover .img-artist{
  transition: .3s;
  /* animation: vibration1 .3s infinite; */
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.5);
  /* transform: scale(1.2); */
}
@keyframes vibration1 {
  0% {
    transform-origin: 50% 50%;
    transform: rotate(18deg);
  }
  50% {
    transform-origin: 50% 50%;
    transform: rotate(-18deg);
  }
  100%{
    transform-origin: 50% 50%;
    transform: rotate(18deg);
  }
}

.button-hover:not(:hover) .img-fan{
  transition: .3s;
}
.button-hover:hover .img-fan{
  transition: .3s;
  animation: vibration .3s infinite;
  /* transform: scale(1.2); */
}
@keyframes vibration {
  0% {
    transform-origin: 50% 100%;
    transform: rotate(4deg);
  }
  50% {
    transform-origin: 50% 100%;
    transform: rotate(-4deg);
  }
  100%{
    transform-origin: 50% 100%;
    transform: rotate(4deg);
  }
}

.button-gap {
  display: flex;
  width: 3vw;
}

.fan-button {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  
  width: 20vw;
  height: 40vh;
  left: 4vw;
  top: 4vh;
  
  background: #FFFFFF;
  box-shadow: 0px 10px 10px rgba(0, 0, 0, 0.25);
  border-radius: 25px;
  /* padding: 12px 24px;
  margin: 0 8px; */
  font-size: 16px;
  cursor: pointer;
}

.login {
  display: flex;
  font-weight: bold;
  color: #6A3FC1;
}

</style>