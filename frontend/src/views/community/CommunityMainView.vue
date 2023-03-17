<template>
  <LoginUserHeaders/>
  <NotLoginUserHeaders  v-if="i == 1"/>
  <br>
  <div class="communityMenu">
    <input type="text" placeholder="글 검색">
    <div class="communityMenu">
      <div>전체보기</div>
      <div>일반글</div>
      <div>팝니다</div>
      <div>삽니다</div>
    </div>
  </div>
  <hr>
  <!-- 게시글 목록 -->
  
  <div v-for="i in articles" :key="i">
    <img :src="require(`@/assets/${i.thumbnail}`)" alt="image">
    {{ i.title }}
    {{ i.writer }}
    {{ i.createdAt }}
    {{ i.viewed }}
  </div>

  <!-- 페이지네이션 -->
  

  <button class="writeButton">
    글쓰기
  </button>
 
</template>

<script>
import LoginUserHeaders from "@/components/headers/LoginUserHeaders.vue"
import NotLoginUserHeaders from "@/components/headers/NotLoginUserHeaders.vue"

export default {
  name: "communityMain",
  data() {
    return {

      articles : [
        { thumbnail: 'ImageGallery.png',
        title:'제가 산 NFT 봐주세요',
        writer: '조민수',
        createdAt: '2023-03-02',
        viewed: 12,
      },
      { thumbnail: 'ImageGallery.png',
        title:'[팝니다] 현제 NFT 팝니다',
        writer: '조민수',
        createdAt: '2023-03-02',
        viewed: 62,
      },
      { thumbnail: 'Document.png',
        title:'[삽니다] 호연 NFT 갖고 계신분 계신가요...',
        writer: '조민수',
        createdAt: '2023-03-02',
        viewed: 62,
      },
    ],

    }
  },
  components: {
    LoginUserHeaders,
    NotLoginUserHeaders,
  }
}
</script>

<style>
  .communityMenu{
    display: flex;
  }

  .writeButton{
    display: flex;
    right: 10px;
    width: 211px;
    height: 55px;
    font-size: 24px;
    font-weight: bold;
    justify-content: center;
    align-items: center;
    border-radius: 15px;
    color: white;
    background-color: RGB(106, 63, 193);
  }

</style>