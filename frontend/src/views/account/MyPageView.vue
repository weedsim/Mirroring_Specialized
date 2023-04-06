<template>
  <div class="mypage-entire">
    <div>
      <!-- 프로필 이미지, 메타마스크 주소, NFT 충전 버튼 -->
      <div style="display: flex; justify-content: space-between;">
        <div>
          <!-- 프로필 이미지 -->
          <div class="circle1">
            <div class="circle2">
              <div class="circle3">
                <img :src="require('@/assets/EthereumIcon.png')" alt="로고" class="profile-logo">
              </div>
            </div>
          </div>
          
          <div style="margin: 10px; font-weight: bold; font-size: 32px;">
            {{ nickname }} 님
            <router-link to="/mypageupdate" style="text-decoration: none;">
              <v-icon icon="mdi-lead-pencil"  @click="searchArticle" class="profile-pencil-button"></v-icon>
            </router-link>
          </div>

          <!-- 메타마스크 주소 -->
          <div class="address-inline">
            <img :src="require('@/assets/metamask_logo.png')" alt="여우" style="width:35px; height: 25px; padding-left: 5px; padding-right: 5px;">
            <div id="metamask-address">{{address}}</div>
            <img :src="require('@/assets/copy.png')" alt="복사" style="width:28px; height: 20px; padding-left: 5px; padding-right: 5px; " @click="copyAddress">
          </div>
        </div>
        
        <div style="margin-top: auto">
          <button class="charge-button" @click="this.charge">
            FAN 충전
          </button>
        </div>
      </div>
  
      
      <div class="mypage-filter-tab" >
        <button @click="clickOwnedNFT" class="mypage-filter-tab-part" tabindex="-1" @keydown.prevent="handleBtnDown" >
          보유 NFT
        </button>
        |
        <button @click="clickNFTLog" class="mypage-filter-tab-part" tabindex="-1" @keydown.prevent="handleBtnDown"> 
          거래 내역 
        </button>
        |
        <button @click="clickAttractedNFT" class="mypage-filter-tab-part" tabindex="-1" @keydown.prevent="handleBtnDown">
          관심 아이템
        </button>
        <div class="indicator" :style="{transform:'translateX('+filterTapX+'%)'}"></div>
      </div>
  
      <hr style="width:100%">
  
      <!-- 메뉴 콤보박스 -->
      <div style="display: flex; justify-content: end;">
        <div style="margin-left:14px; width:200px; border-radius: 50% !important;">
          <div>
            <v-combobox :items="myNFTmenu"></v-combobox> 
          </div>
        </div>
      </div>
      
      
      
      
      <!-- <v-container>
        <v-row style="max-width:1000px">
          <v-col v-for="n in 14" :key="n" :cols="3">
            <v-card outlined tile class="holding-item" style="height:200px; width: 200px;">
              <div class="artist-circle">
                {{ n }}
              </div>
              <div>
                <v-img src="@/assets/minsu.jpg" alt="민수 ">
                  
                  <v-card-text class="nft-info">
                    <div class="font-weight-bold">
                      dfdfdffdsffs
                    </div>
                    <div>
                      전체거래량
                    </div>
                    <div>
                      최저거래가
                    </div>
                  </v-card-text>
                </v-img>
              </div>                                                                                                                                                                                                              
  
            </v-card>
          </v-col>
        </v-row>
      </v-container> -->
      
      <!-- <NFTCard v-for="(nft, index) in userNFTs" :key="index" :card="nft">
        
      </NFTCard> -->
      <!-- <div style="height:1000px; width: 1000px; padding-top: 100px;"> -->
        <div v-for="(nft, index) in userNFTs" :key="index" class="fl">
          <div> 
            <router-link :to="{name:'MarketDetailView', params:{id: nft.nftId}}">
              <div class="nft-card-title">
                <img :src="nft.nftSource.fileCID" alt="no" class="nft-img">
              </div>
              <div>
                {{ nft.nftSource.regArtist.nickname }}
              </div>
              <div>
                {{ nft.nftSource.title }}
              </div>
              <div>
                {{ nft.nftSource.originPrice }} FAN
              </div>
              <div>
                {{ nft.nftSource.remainNum }} / {{ nft.nftSource.totalNum }}
              </div>
            </router-link>
          </div>
        </div>
      <!-- </div> -->
      
      
    </div>
    

  </div>
</template>

<script>
// import LoginUserHeaders from "@/components/headers/LoginUserHeaders.vue"
// import NotLoginUserHeaders from "@/components/headers/NotLoginUserHeaders.vue"
import Web3 from "web3"

import BankABI from "../../../path/to/BankABI.json";
import SaleFactoryABI from "../../../path/to/SaleFactoryABI.json";
import SaleABI from "../../../path/to/SaleABI.json";
import NFTABI from "../../../path/to/NFTABI.json";

import  VueCookies  from 'vue-cookies';
// import { Buffer } from 'buffer';
// import NFTCard from "@/components/market/NFTCard.vue"

export default {
  name: 'MyPageView',
  components: {
    // NFTCard,
  },
  data(){
    return {
      nickname: VueCookies.get('nickname'),
      address: null,

      filterTapX: 0,
      btnOwnedNFT: true,
      btnNFTLog: false,
      btnAttractedNFT: false ,

      myNFTmenu : ['최신 순','거래량 많은 순','거래 횟수 많은 순', '이름 순 : A→Z','이름 순 : Z→A'],
      profileImage: VueCookies.get('profileImage'),


      userNFTs: [],

      SaleContractAddress : null,
    }
  },
  async created() {
    await this.$store.dispatch('getAccount');
    this.address = this.$store.state.address;

    this.getUserNFTs();
  },
  methods:{
    copyAddress: async function(){
      let a = this.address;
      // this.$store.dispatch('getAccount');
      // a = this.$store.state.address;
      console.log(a);

      try {
        console.log('a');
      } catch (error) {
        console.error(error);
      } 

      console.log('dd', a, '와:', a.textContent , '끝')
      window.navigator.clipboard.writeText(a).then(() => {
        // 복사가 완료되면 호출된다.
        alert("메타마스크 주소를 복사했습니다!");
      });
    },

    clickOwnedNFT (){
      this.btnOwnedNFT = true;
      this.btnNFTLog = false;
      this.btnAttractedNFT = false;
      this.filterTapX = 0;
      console.log('보유한 NFT')
    },
    clickNFTLog (){
      this.btnOwnedNFT = false;
      this.btnNFTLog = true;
      this.btnAttractedNFT = false;
      this.filterTapX = 104;
      console.log('NFT 거래 기록')
    },
    clickAttractedNFT (){
      this.btnOwnedNFT = false;
      this.btnNFTLog = false;
      this.btnAttractedNFT = true;
      this.filterTapX = 216;
      console.log('관심 있는 NFT')
    },
    handleBtnDown(e){
      if (e.keyCode===9){
        e.stopPropagation();
      }
    },

    async getUserNFTs(){
      await this.$store.dispatch('userNFTs')
      this.userNFTs = this.$store.userNFTs
    },
    async charge() {
      console.log("1111");
      // const web3 = new Web3(new Web3.providers.HttpProvider('https://fanftasy.kro.kr/network'));
      // const web3 = new Web3('https://fanftasy.kro.kr/network');
      const account = VueCookies.get('Account');
      // const accounts = await web3.eth.getAccounts();
      // console.log(accounts);
      console.log(account);
      // console.log(await web3.eth.sign('hello', account));

      // console.log(web3.providers);
      // console.log(web3.givenProvider);

      // 메타마스크가 실행 되면서 서명 요청 
      // const method = 'personal_sign';
      // const params = ['test', account];
      // // await Web3.providers.HttpProvider('https://fanftasy.kro.kr/network').send(
      // await window.ethereum.request(
      //   {
      //     method,
      //     params,
      //     account,
      //   }
      // ).then((res) => {
      //   console.log(res);
      // })
      // .catch((err) => {
      //   console.log(err);
      // })

        console.log(window.ethereum)
        
        ////////////////
        const web3 = new Web3(window.ethereum);
        const bankContractAddress = '0xbC7eeBAbbAd2E7427C7E3cF7B3B073ed51a91390';
        const bankContract = new web3.eth.Contract(BankABI, bankContractAddress);
        const  amount = web3.utils.toWei("100", "ether"); // 1 ETH를 wei 단위로 변환
        // console.log(bankContract);


        /////////////////////////////////////////////
        const saleFactoryContractAddress = '0x0509b43FF9CcAC684ef00bc82020208b6F86d156';
        const saleFactoryContract = new web3.eth.Contract(SaleFactoryABI, saleFactoryContractAddress);
        console.log(await saleFactoryContract.methods);

        // 앞이 nft_id 이고 amount가 price -> 단위는 wei 이기에 단위 변환 만들기
        // const ans = (await saleFactoryContract.methods.createSale(5, amount).send({ from: account }));
        // (await saleFactoryContract.methods.createSale(5, amount).send({ from: account })
        // .on("transactionHash", function(hash) {
        //     console.log("Transaction hash: " + hash);
        //   })
        //   .on("receipt", function(receipt) { // .then과 비슷함
        //     console.log("Create Sale Receipt : ");
        //     console.log(receipt);
        //   })
        //   .on("error", function(error) {
        //       console.error(error);
        //   }));
        //   console.log(ans);

        // 판매 컨트랙트의 주소
        // var SaleContractAddress = null;
        
        // // 판매 컨트랙트의 주소를 가져오기 위한 판매 올린 로그 불러오기
        // saleFactoryContract.getPastEvents('NewSale', {
        //   filter: { itemId : [11] },
        //   fromBlock: 0,
        //   toBlock: 'latest',
        // },async function(err, events){
        //   console.log(err);
        //   console.log(events);
        //   this.SaleContractAddress = events[events.length - 1].returnValues._saleContract;
        //   console.log(this.SaleContractAddress);
        //   
          
          
          
          
          
        //   // ///////////////////////////////////////////////////////////////////////////////////
        //   //  판매 중인 nft 구매 하려면 필요한 컨트랙트
        //   let SaleContract = new web3.eth.Contract(SaleABI, this.SaleContractAddress);
        //   console.log(SaleContract.methods);
        //   let price = null;

        //   // 판매 가격 받아오기
        //   SaleContract.methods.getFinalPrice().call((err, res) => {
        //     if(err) {
        //       console.log(err);
        //     }
        //     else {
        //       console.log(res);
        //       price = res;
        //     }
        //   })


        //   //  구매인데 판매 중인 nft의 가격을 value에 입력이 되어야한다.
        //   const ans = SaleContract.methods.purchase().send({ from : account, value : price });
        //   console.log(await ans);




        // });
        // console.log(this.SaleContractAddress);


        // // 모든 판매 정보가 배열로 나오는데, 판매 완료 되었어도 뜬다.
        // saleFactoryContract.methods.allSales().call((err, res) => {
        //   console.log(err);
        //   console.log(res);
        // });
        
        ////////////////////////////////////////////////////////////////////////////////////////////////
        let SaleContract = new web3.eth.Contract(SaleABI, this.SaleContractAddress);
        console.log(SaleContract.methods);
        // const ans = SaleContract.methods.purchase().send({ from : account, value : 1 });
        // console.log(await ans);


        ////////////////////////////////////////////////////////////////////////////////////// 
        const NFTContractAddress = '0xDcC5cdA0Ea01c12eA756601021c0029FC39efD38';
        const NFTContract = new web3.eth.Contract(NFTABI, NFTContractAddress);
        console.log(await NFTContract.methods);

        //////// NFT 생성
        // const ans = (NFTContract.methods.create(account , "1234", saleFactoryContractAddress).send({ from: account }));
        // console.log(await ans);

        /////// 생성한 NFT의 NFT_ID(token_id)를 불러오기
        // NFTContract.methods.current().call((err, res) => {
        //   if(err){
        //     console.log(err);
        //   }
        //   else {
        //     console.log(res);
        //   }
        // })

        // 전체 거래 완료 로그 확인
        // NFTContract.getPastEvents('tradeRecord', {
        //   fromBlock: 0,
        //   toBlock: 'latest',
        // }, function(err, res) {
        //   if(err){
        //     console.log(err);
        //   }
        //   else{
        //     console.log(res);
        //   }
        // })












        // 은행으로부터 인출한 로그들 
        // bankContract.getPastEvents('Withdraw', {
        //   fromBlock: 0,
        //   toBlock: 'latest',
        // }, function(err, events){
        //   if(err){ 
        //     console.log(err);
        //   }
        //   else{
        //     console.log(events);
        //   }
        // });
        
        // 은행에 돈 집어 넣은 로그들
        // bankContract.getPastEvents('Deposit', {
        //   fromBlock: 0,
        //   toBlock: 'latest',
        // }, function(err, events){
        //   if(err){ 
        //     console.log(err);
        //   }
        //   else{
        //     console.log(events);
        //     const line = events.length;
        //     if(line <= 5) {
        //       for(let i = line; i > 0; i--){
        //         let date = new Date((events[i - 1].returnValues.time) * 1000);
        //         console.log(date.toLocaleString());
        //       }
        //     }else{
        //       for(let i = line; i > (line - 5); i--){
        //         let date = new Date((events[i - 1].returnValues.time) * 1000);
        //         console.log(date.toLocaleString());
        //       }
        //     }
        //     // console.log(line);
        //     // console.log(events[line - 1]);
        //     // console.log(events[line - 1].returnValues);
        //     // console.log(events[line - 1].returnValues.time);
        //     // const date = new Date((events[line - 1].returnValues.time) * 1000);
        //     // console.log(date.toLocaleString());
        //   }
        // });
        
        
        // amount 만큼 은행에서 가져오기 amount 가 1 이더만큼인 걸 wei 단위로 변환 시켜둔 것
        bankContract.methods.withdraw(amount).send({ from: account });
        
        // // 은행에 얼마가 남아있는지 확인하는 기능
        bankContract.methods.getBalance().call((err, result) => {
          if (err) {
            console.error(err);
          } 
          else {
            console.log('Contract balance:', result);
          }
        });
        
        
          // bank 컨트랙트에 1이더만큼 넣음 -> abi 메서드 사용
          
          // bankContract.methods.deposit().send({
          //   from: account,
          //   value: amount
          // })
          // .on("transactionHash", function(hash) {
          //   console.log("Bank Deposit Transaction hash: " + hash);
          // })
          // .on("receipt", function(receipt) { // .then과 비슷함
          //   console.log("Bank Receipt");
          //   console.log(receipt);
          //   console.log(receipt.from);
          // })
          // .on("error", function(error) {
          //     console.error(error);
          // });
          
          },
        },
        watch() {
          
        },
        mounted() {
        },
      }
    </script>

<style>
.mypage-entire{
  padding-top: 90px;
  display: flex;
  justify-content: center;
}

.circle1{
  width: 184px;

  height: 184px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 0px 5px 5px gray; 
  margin-left: 20px;
  margin-bottom: 20px;
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

.profile-logo{
  width: 100px;
  height: 100px;
}


.profile-pencil-button{
  font-size: 30px;
  color: black;
}
.profile-pencil-button:hover{
  color: #9B7CF8;
}

.address-inline{
  display: flex;
  align-items: center;
  border-color: #9B7CF8;
  border-style: solid;
  border-radius: 15px;
  width: 500px;
  height: 40px;
  margin-bottom: 10px;
}

.charge-button{
  display: flex;
  right: 10px;
  width: 204px;
  height: 70px;
  font-size: 36px;
  font-weight: bold;
  justify-content: center;
  align-items: center;
  border-radius: 15px;
  color: white;
  background-color: RGB(106, 63, 193);
  margin-bottom: 10px;
}

#metamask-address{
  width: 440px;
  text-align: left;
}

/* 마이페이지 보유NFT, 거래내역, 관심아이템 탭 버튼 */
.mypage-filter-tab{
  display: flex;
  align-items: center;
  margin: 10px;
  font-size: 1em;
  position: relative;
}
.mypage-filter-tab-part{
  padding: 10px;
  border-radius: 15px;
}
.indicator {
  padding: 10px;
  border-radius: 15px;
  position: absolute;
  bottom: 0%;
  width: 84px;
  height: 40px;
  background-color: #9B7CF8;
  opacity: 0.3;
  transition: transform 0.3s ease-in;
  pointer-events: none;
}


.holding-item{
  border-radius: 15px;
  z-index: 0;
  border-color: #9B7CF8;
}


.artist-circle{
  position: absolute;
  margin-left: 5px;
  margin-top: 5px;
  z-index: 10;
  background-color: #9B7CF8;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 35px;
  height: 35px;
  border-radius: 50%;
}

.nft-info{
  justify-content: center;
  background-color: white;
  font-size: 10px;
  border-bottom-left-radius: 15px;
  margin-top: 118px;
}

.changeImageBtn{
  border: solid 1px black;
  border-radius: 5%;
}

</style>
