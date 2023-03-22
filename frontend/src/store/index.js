import { createStore } from 'vuex'

export default createStore({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
    installedMetamask() { // metamask 확장자가 설치 되어있는지 확인하는 method
      // Check if Web3 has already been injected by MetaMask
      if(typeof window.ethereum !== 'undefined'){
        console.log("설치되있음");
        return true;
      }
      else {
        alert("저희 사이트는 METAMASK가 필수입니다.");
        //METAMASK 설치 페이지가 새 창에 뜸
        window.open('https://chrome.google.com/webstore/detail/metamask/nkbihfbeogaeaoehlefnkodbefgpgknn');
        return false;
      }
    },
    login(){
      return window.ethereum.request({ method: 'eth_requestAccounts' });
    },
  },
  modules: {
  }
})
