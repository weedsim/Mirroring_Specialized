<template>
  <br>
  <br>
  <div>
    <div class="nftbar">
      <span style="display: inline-block">
        <h1 class="market-space">판매중인 NFT</h1>
        
        <button v-on:click="selectNewest" v-if="sortnum === 0" class="selected-ranking-button">최신순</button>
        <button v-on:click="selectNewest" v-else class="unselected-ranking-button">최신순</button>
        <button v-on:click="selectSalesVolume" v-if="sortnum === 1" class="selected-ranking-button">
          판매량순
        </button>
        <button v-on:click="selectSalesVolume" v-else class="unselected-ranking-button">
          판매량순
        </button>
        <button v-on:click="selectSalesAmmount" v-if="sortnum === 2" class="selected-ranking-button">
          판매금액순
        </button>
        <button v-on:click="selectSalesAmmount" v-else class="unselected-ranking-button">
          판매금액순
        </button>
      </span>
      <label for="" class="box" style="display: inline-block">
          <input type="text" class="notting search-input" />
          <button
            href=""
            class="notting"
            style="display: flex; justify-content: right; align-self: center"
          >
            <img
              :src="require('@/assets/Search.png')"
              alt=""
              style="margin-top: 7px"
            />
          </button>
        </label>
    </div>
    <NFTCard />
  </div>
</template>

<script>
import NFTCard from "@/components/market/NFTCard.vue"

export default {
  name: "MarketListView",
  components: {
    NFTCard,
  },
  data() {
    return {
      sortnum: 0,
    }
  },
  methods: {
    selectNewest() {
      this.sortnum = 0
    },
    selectSalesAmmount() {
      this.sortnum = 2
    },
    selectSalesVolume() {
      this.sortnum = 1
    },
  }
}
</script>

<style>
.nftbar {
  display: flex;
  justify-content: space-around;
  /* white-space: nowrap; */
  /* width: 100%; */
  overflow: hidden;
}

.market-space {
  display: flex;
  justify-content: left;
  /* margin-left: 7vw; */
  margin-right: 3vw;
  float: left;
}
</style>