<template>
  <div >
    <div style="display: inline-block;">
      <v-card class="nft-card">      
        <v-card-title style="padding: 0; display: flex; justify-content: center; position: relative;" >
          <v-img src="@/assets/뉴진스.png" alt="" class="card-img"></v-img>
        </v-card-title>
        <v-card-item style="font-size:larger; vertical-align: center; text-align: center; ">
          <p class="item-name">
            아티스트
          </p>
          <p class="item-content">
            심호연
          </p>
          <p class="item-name">
            제목
          </p>
          <p class="item-content">
            코인 손해본거 놀릴 때 호연
          </p>
          <p class="item-name">
            가격
          </p>
          <p class="item-content">
            1.03FAN
          </p>
          <p class="item-name">
            잔여수량
          </p>
          <p class="item-content">
          19/50
          </p>
        </v-card-item>
      </v-card>
      <v-card class="nft-card">      
        <v-card-title style="padding: 0; display: flex; justify-content: center; position: relative;" >
          <v-img src="@/assets/뉴진스.png" alt="" class="card-img"></v-img>
        </v-card-title>
        <v-card-item style="font-size:larger; vertical-align: center; text-align: center; ">
          <br>
          New Jeans
        </v-card-item>
      </v-card>
      <v-card class="nft-card">      
        <v-card-title style="padding: 0; display: flex; justify-content: center; position: relative;" >
          <v-img src="@/assets/뉴진스.png" alt="" class="card-img"></v-img>
        </v-card-title>
        <v-card-item style="font-size:larger; vertical-align: center; text-align: center; ">
          <br>
          New Jeans
        </v-card-item>
      </v-card>
    </div>
    <br>
    <div style="display: inline-block;">
      <v-card class="nft-card">      
        <v-card-title style="padding: 0; display: flex; justify-content: center; position: relative;" >
          <v-img src="@/assets/뉴진스.png" alt="" class="card-img"></v-img>
        </v-card-title>
        <v-card-item style="font-size:larger; vertical-align: center; text-align: center; ">
          <br>
          New Jeans
        </v-card-item>
      </v-card>
      <v-card class="nft-card">      
        <v-card-title style="padding: 0; display: flex; justify-content: center; position: relative;" >
          <v-img src="@/assets/뉴진스.png" alt="" class="card-img"></v-img>
        </v-card-title>
        <v-card-item style="font-size:larger; vertical-align: center; text-align: center; ">
          <br>
          New Jeans
        </v-card-item>
      </v-card>
      <v-card class="nft-card">      
        <v-card-title style="padding: 0; display: flex; justify-content: center; position: relative;" >
          <v-img src="@/assets/뉴진스.png" alt="" class="card-img"></v-img>
        </v-card-title>
        <v-card-item style="font-size:larger; vertical-align: center; text-align: center; ">
          <br>
          New Jeans
        </v-card-item>
      </v-card>
    </div>
  </div>
</template>

<script>
export default {
  name: "NFTCard",

}
</script>

<style>
.nft-card {
  display: flex;
  float: left;
  width: 413px;
  height: 245px;
  border: solid 1px #6a3fc1;
}

.item-name{
  color: #6A3FC1;
  text-align: left;
  font-size: 15px;
  font-weight: bolder;
}

.item-content{
  text-align: left;
  margin-left: 1vw;
  font-size: 15px;
}
</style>