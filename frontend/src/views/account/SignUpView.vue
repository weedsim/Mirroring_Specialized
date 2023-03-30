<template>
  <!-- <div class="LogoTitle">
    <div class="title">회원 가입</div>
    <v-img v-if="this.$store.state.isFan" src="@/assets/fan-club.png" class="logo"></v-img>
    <v-img v-else src="@/assets/Micro.png" class="logo"></v-img>
  </div> -->

  <div class="signup">
    <div class="info">
      <input type="text" class="nickname t" placeholder="닉네임" v-model="this.nickname"/>
      <!-- <input type="text" class="RealName" placeholder="실명" v-bind=""> -->
      <input type="text" class="email t" placeholder="이메일" v-model="this.email" />
      <input type="text" class="phone t" placeholder="전화번호" v-model="this.phone" />
      <input type="text" class="company t" v-if="!this.$store.state.isFan" placeholder="소속사" v-model="this.company" />
      <button class="connective" @click="connectWallet()">메타마스크 연결하기</button>
    </div>
    <div>
      <v-img src="@/assets/HERO.png" alt="" style="height: 50vh; width: 30vw;"></v-img>
    </div>
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
    if(this.$store.state.isFan){
      this.role = 'fan';
    }
    else{
      this.role = 'artist';
    }
    
  },
  methods: {
    async connectWallet(){ // 회원가입
      await this.$store.dispatch('changeNetWork');


      // this.address = await this.$store.dispatch('getAccount');
      // console.log(this.address);

      // if(this.company === null){
      //   this.company = 'once';
      // }
      // this.$store.state.email = this.email;
      // this.$store.state.address = this.address;
      // this.$store.state.phone = this.phone;
      // this.$store.state.nickname = this.nickname;
      // this.$store.state.name = this.name;
      // this.$store.state.role = this.role;
      // this.$store.state.company = this.company;
      // await this.$store.dispatch('signup');
      
      // await this.$store.dispatch('modiUserInfo');

      // await this.$store.dispatch('userDetail');
      // console.log(this.$store.state.nickname);
      
      // await this.$store.dispatch('LOGIN');

      // await this.$store.dispatch('modiUserInfo');
      
      console.log(this.$store.state.success);
    }
  },
  computed () {

  }
}
</script>

<style>

.LogoTitle {
  display: flex;
  align-items: center;
  position: absolute;
  top: 20vh;
  left: 5vw;
}

.title {
  display: flex;
  height: 50px;
  width: 80px;
  font-size: 40px;
  color: purple;
}

.logo {
  display: flex;
  /* vertical-align: top; */
  height: 30px;
  width: 60px;
  margin-right: 10px;
}

.signup {
  display: flex;
  justify-content: center;
  align-items: center;
}

.info {
  display: flex;
  flex-direction: column;

  background:rgba(#13232f,.9);
  padding: 40px;
  border-radius: brown;
  box-shadow:0 4px 10px 4px rgba(#13232f,.3);
}


.connective {
  border: solid 1px black;
  background-color: purple;
  color: white;
}

.t {
  margin-bottom: 10px;
  background-color: rgba(blue, 0.3);
  border: solid 1px black;
}

</style>