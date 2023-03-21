<template>
  <div class="df cen">
    <div class="cen">
      <v-img src="@/assets/coin_hoyoen.png" alt="" class="nft-detail-img"></v-img>
    </div>
    <div >
      <div class="nft-detail-box1">
        <br>
        <h1>코인 손해본거 놀릴 때 호연</h1>
        <br>
        <br>
        <br>
        <br>
        <div class="fl">
          <p>
            Artist
          </p>
          <p>
            H.Y Shim
          </p>
        </div>
        <div class="fl">
          <p>
            Type
          </p>
          <p>
            Photo
          </p>
        </div>
        <div class="fl">
          <v-img src="@/assets/Heart_Icon.png" alt="" class="heart-detail"></v-img>
          <p>
            132
          </p>
          <br>
        </div>
        <br>
        <br>
      </div>
  
      <div class="nft-detail-box">
        <span style="float: left;">
          <p class="nft-item-name">
            판매가격
          </p>
          <p class="nft-item-content">
            1.03NFN
          </p>
        </span>  
        <span>
          <p class="nft-item-name">
            잔여수량
          </p>
          <p class="nft-item-content">
            19/50
          </p>
        </span>
        <button class="purchase-btn">구매하기</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>
.nft-detail-img {
  width: 372px;
  height: 504px;
  margin-right: 166px;
  float: left;
}

.nft-detail-box{
  width: 495px;
  height: 235px;
  background: #F2F3F6;
  border-radius: 20px;
  text-align: center;
}

.nft-detail-box1{
  width: 495px;
  height: 235px;
}

.purchase-btn {
  display: flex;
  /* right: 10px; */
  width: 415px;
  height: 70px;
  font-size: 36px;
  font-weight: bold;
  justify-content: center;
  align-items: center;
  border-radius: 15px;
  color: white;
  margin: auto;
  float: bottom;
  background-color: RGB(106, 63, 193);
}

.nft-item-name{
  color: #7780A1;
  text-align: center;
  font-size: 14px;
}

.nft-item-content{
  text-align: center;
  margin-left: 1vw;
  font-size: 24px;
  font-weight: bold;
}

.heart-detail{
  width: 38px;
  height: 38px;
  float: left;
}

.df {
  display: flex;
}

.fl {
  float: left;
}

.cen {
  justify-content: center;
}
</style>