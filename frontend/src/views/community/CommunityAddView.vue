<template>
  <LoginUserHeaders/>
  <NotLoginUserHeaders  v-if="i == 1"/>
  <br />
  <div>
    <div>글쓰기</div>
    <div>
      제목
      <input type="text" style="border-style: solid; border-color: rgb(188, 188, 188); border-radius: 15px;">
    </div>

    <div>
      게시판
      <v-combobox>

      </v-combobox>
    </div>

    <div>
      <textarea name="" id="" cols="30" rows="10"></textarea>
    </div>
  </div>
  <AllFooter/>
</template>

<script>
import LoginUserHeaders from '@/components/headers/LoginUserHeaders.vue';
import NotLoginUserHeaders from '@/components/headers/NotLoginUserHeaders.vue';
import AllFooter from '@/components/headers/AllFooter.vue';

export default {
  components:{
    LoginUserHeaders,
    NotLoginUserHeaders,
    AllFooter,
  },
}
</script>

<style>

</style>