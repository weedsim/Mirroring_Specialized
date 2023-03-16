<template>
  <div>
    <LoginUserHeaders/>
    <NotLoginUserHeaders/>

    <!-- 프로필 이미지 -->
    <div class="circle1">
      <div class="circle2">
        <div class="circle3">
          <img :src="require('@/assets/EthereumIcon.png')" alt="로고" class="profileLogo">
        </div>
      </div>
    </div>

    <!-- 메타마스크 주소 -->
    <div class="addressInline">
      <img :src="require('@/assets/metamaskLogo.png')" alt="여우" style="width:35px; height: 25px; padding-left: 5px; padding-right: 5px;">
      <div id="metamaskAddress">{{address}}</div>
      <img :src="require('@/assets/copy.png')" alt="복사" style="width:28px; height: 20px; padding-left: 5px; padding-right: 5px; " @click="copyAddress">
    </div>

    <div>
      보유 NFT
    </div>
    <div>
      최신순
    </div>
    <div>
      <div>
        <div v-for="(i,n) in 10" v-bind:key="i">{{ n }} </div>
      </div>
    </div>

    <AllFooter/>
  </div>
</template>

<script>
import LoginUserHeaders from "@/components/headers/LoginUserHeaders.vue"
import NotLoginUserHeaders from "@/components/headers/NotLoginUserHeaders.vue"
import AllFooter from "@/components/headers/AllFooter.vue"
import Web3 from "web3"

export default {
  name: 'MyPageView',
  data(){
    return {
      web3: null,
      // address: '0x53fd1C95DCe6f1d00aA1Ad0296899b02efB20686',
      address: null,
    }
  },
  components:{
    LoginUserHeaders,
    NotLoginUserHeaders,
    AllFooter,
  },
  methods:{
    copyAddress: async function(){
      const a = document.getElementById('metamaskAddress')

      

      try {
        const accounts = await this.web3.eth.getAccounts();
        console.log(accounts[0]);
      } catch (error) {
        console.error(error);
      }

      // await web3.eth.getAccounts()
      // .then(function (accounts) {
      //   console.log(accounts)
      //   if (accounts.length === 0) {
      //     // 지갑이 없는 경우, Metamask에 새 지갑 생성 및 가져오기 화면을 띄웁니다.
      //     // window.open('https://metamask.io/');
      //   } else {
      //     // 지갑이 있는 경우, Metamask를 실행하여 지갑을 잠금 해제합니다.
      //     window.ethereum.enable().then(function (accounts) {
      //       console.log('Wallet address:', accounts[0]);
      //       this.address = accounts[0];
      //     }).catch(function (error) {
      //       console.log('Error:', error);
      //     });
      //   }
      // }).catch(function (error) {
      //   console.log('Error:', error);
      // });

      console.log('dd', a, '와:', a.textContent , '끝')
      window.navigator.clipboard.writeText(a.textContent).then(() => {
        // 복사가 완료되면 호출된다.
        alert("메타마스크 주소를 복사했습니다!");
      });
    },
  },
  async mounted() {
    const web = new Web3(window.ethereum);

    // check chain ID
    const chainid = await web.eth.getChainId();
    console.log(chainid);

    // check current network
    console.log(await window.ethereum.networkVersion);

    const metamaskInstallUrl = 'https://metamask.io/download.html';
    
    // Check if Web3 has already been injected by MetaMask
    if (typeof window.ethereum !== 'undefined') {
      // Use MetaMask's provider
      this.web3 = new Web3(window.ethereum);
      try {
        // Request account access if needed
        const accounts = await window.ethereum.request({ method: 'eth_requestAccounts' });
        this.address = accounts[0];
        console.log(accounts[0]);
        console.log(await window.ethereum.request({ method: 'eth_accounts'}));

      } catch (error) {
        console.error(error);
      }
    } else {
      console.log('Please install MetaMask!');
      window.open(metamaskInstallUrl, '_blank');
    }

  },
}
</script>

<style>
.circle1{
  width: 184px;
  height: 184px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 0px 5px 5px gray;
}

.circle2{
  width: 169px;
  height: 169px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgb(218, 210, 233)
}

.circle3{
  width: 154px;
  height: 154px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: white;
}

.profileLogo{
  width: 100px;
  height: 100px;
}

.addressInline{
  display: flex;
  align-items: center;
  border-color: #9B7CF8;
  border-style: solid;
  border-radius: 15px;
  width: 500px;
  height: 40px;
}


#metamaskAddress{
  width: 440px;
  /* justify-content: right; */
  /* justify-items: right; */
  /* justify-self: right; */
  text-align: left;
}

.logo {
  width: 20px;
  height: 20px;
}
</style>