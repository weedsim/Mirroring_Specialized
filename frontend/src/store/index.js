import { createStore } from 'vuex'
import VueCookies from "vue-cookies"
import axios from "axios"
import createPersistedState from "vuex-persistedstate"

// const API_URL = "http://70.12.246.214/api"

const store = createStore({
  plugins: [createPersistedState()],
  state: {
    // API_URL : "https://fanftasy.kro.kr/api",
    API_URL : "http://70.12.247.124:8080/api",
    // API_URL : "http://localhost:8080/api",
    CurrentAccount: null,
    // RefreshToken: null,
    AccessToken: null,
    isFan: true,
    name: null,
    nickname: null,
    phone: null,
    role: null,
    email: null,
    company: null,
    totalPrice: null,
    totalSales: null,
    profileImg: null,
    success: false,
  },
  getters: {
    
  },
  mutations: {
    installedMetamask() { // metamask 확장자가 설치 되어있는지 확인하는 method
      // Check if Web3 has already been injected by MetaMask
      if(typeof window.ethereum !== 'undefined'){
        console.log("설치되있음");
        return true;
      }
      else {
        alert("저희 사이트는 METAMASK가 필수입니다.");
        //METAMASK 설치 페이지가 새 창에 뜸
        window.open('https://chrome.google.com/webstore/detail/metamask/nkbihfbeogaeaoehlefnkodbefgpgknn', "_blank", "width=500, height=500");
        return false;
      }
    },
    setCookies(){ // 메타마스크의 현재 지갑 주소들 return -> 현재 연결된 거만 뜨는 상황
      VueCookies.set("CurrentAccount", this.state.currentAccount, "10h");
      return this.state.currentAccount;
    },
    LogOut() {
      this.state.CurrentAccount = null;
      this.state.AccessToken = null;
      // this.state.RefreshToken = null;
      VueCookies.remove("CurrentAccount");
      VueCookies.remove("AccessToken");
      // VueCookies.remove("RefreshToken");
      return true;
    },
    LogInStatus() {
      if(VueCookies.isKey('RefreshToken')){
        return true;
      }
      else {
        return false;
      }
    }
  },
  actions: {
    async getAccount() { // 계정 불러오기
      const account = (await window.ethereum.request({ method: 'eth_requestAccounts' }))[0];
      console.log(account);
      this.state.currentAccount = account;
      return account;
    },
    async LOGIN() { // 우리 회원인지 확인하고, 회원이면 토큰을 받고, 비회원이면 404 에러
      const account = (await window.ethereum.request({ method: 'eth_requestAccounts' }))[0];
      await axios({
        method: "post",
        url: `${this.state.API_URL}/user/login`,
        params: {
          address: account, //지갑 주소
        },
      })
      .then((res) => {
        console.log(res);
        console.log(res.headers.accesstoken);
        VueCookies.set('AccessToken', res.headers.accesstoken, '3h');
        this.state.success = true;
      }) 
      .catch((error) => {
        console.log(error);
        console.log(error.response.data.success);
        if(!error.response.data.success){
        this.state.success =false;
        }
        this.state.success =false;
      })
      
    },
    async sameAccount(){ // 쿠키와 메타마스크의 현재 지갑 주소를 비교해서 같으면 true, 다르면 false
      const account = (await window.ethereum.request({ method: 'eth_requestAccounts' }))[0];
      console.log(account);
      if(VueCookies.isKey("CurrentAccount") === true) { // 이미 로그인을 했을 경우
        if(VueCookies.get("CurrentAccount") === account) { // 마지막으로 로그인 했을 때의 쿠키와 현재의 메타마스크에 연결되있는 계정 주소가 같을 경우
          this.state.success = true;

        }
        else { // 다르면 다시 로그인 절차를 밟아야 한다.
          this.commit('LogOut');
          this.state.success = false;

        }
      }
      else { // 로그인이 안되어 있을 경우
        this.state.success = false;

      }
    },
    async signup(payload) {
      console.log(payload);
      await axios({
        method: "post",
        url: `${this.state.API_URL}/user/join`,
        data: {
          address: this.state.address, //지갑 주소
          email : this.state.email, //이메일
          nickname: this.state.nickname, //닉네임
          phone: payload.phone, //전화번호
          role: this.state.role, //아티스트 or 팬
          company: this.state.company, //소속사
        },
      })
      .then((res) => {
        console.log(res);
        this.state.success = true;
      }) 
      .catch((error) => {
        console.log(error);
        this.state.success = false;
      })
    },
    async userDetail() {
      const address = (await window.ethereum.request({ method: 'eth_requestAccounts' }))[0];
      await axios({
        method: "get",
        url: `${this.state.API_URL}/user/detail`,
        params: {
          address: address,
        },
      }) .then((res) => {
        console.log(res);
        console.log(res.data);
        console.log(res.data.data);
        this.state.name = res.data.data.name;
        console.log(this.state.name);
      }) .catch((err) => {
        console.log(err);
      })
    },
    async modiUserInfo(payload) {
      const address = (await window.ethereum.request({ method: 'eth_requestAccounts' }))[0];
      console.log(payload);
      await axios({
        method: "put",
        url: `${this.state.API_URL}/user/modi`,
        data: {
          address: address,
          email: this.state.email,
          nickname: this.state.nickname,
          profileImg: null,
        }
      }) .then((res) => {
        console.log(res);
        this.state.success = true;
      }) .catch((err) => {
        console.log(err);
        this.state.success = false;
      })
    },
  },
  modules: {
  }
})

export default store