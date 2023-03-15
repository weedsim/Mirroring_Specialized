<template>
  <div class="div-ranking-card">
    <v-card class="ranking-card">      
      <v-img src="@/assets/뉴진스.png" alt="" height="180px"></v-img>
      <v-card-title style="padding: 0;">
        <hr>
      </v-card-title>
      <v-card-item style="font-size:larger; vertical-align: center; text-align: center;">
        New Jeans
      </v-card-item>
    </v-card>
    <v-card class="ranking-card">      
      <v-img src="@/assets/bell.png" alt="" height="100px"></v-img>
      <v-card-title>
      </v-card-title>
    </v-card>
    <v-card class="ranking-card">      
      <v-img src="@/assets/bell.png" alt="" height="100px"></v-img>
      <v-card-title>
      </v-card-title>
    </v-card>
    <v-card class="ranking-card">      
      <v-img src="@/assets/bell.png" alt="" height="100px"></v-img>
      <v-card-title>
      </v-card-title>
    </v-card>
    <v-card class="ranking-card">      
      <v-img src="@/assets/bell.png" alt="" height="100px"></v-img>
      <v-card-title>
      </v-card-title>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "RankingCard",

}
</script>

<style>
.div-ranking-card{
  display: flex;
  margin-left: 12vw;
}

.ranking-card{
  width: 208px;
  height: 284px;
  margin-right: 30px;
  border: solid 1px #9B7CF8;
}
</style>