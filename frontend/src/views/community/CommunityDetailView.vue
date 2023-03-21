<template>
  <div>
    <LoginUserHeaders/>
    <NotLoginUserHeaders v-if="i == 1"/>
    
    <div>
      제목
    </div>
    <div style="display: inline-flex;">
      작성자
      <div style="border-radius: 50%; background-color: aqua; width: 20px; height: 20px;"></div>
      조민수
      <div style="display: inline-flex;">
        <div>
          작성일자
          2023.03.21
        </div> 
        <div>
          조회수
          12
        </div>
      </div>
    </div>
    <hr>
    
    <div>
      내용
    </div>

    <hr>

    <div>
      댓글 [{{ commentCnt }}]
    </div>
    <div>
      <!-- 달린 댓글 -->
      <div style="display: inline-flex;">
        <v-img src="@/assets/base_profile.png" alt="?" style="width:20px; height:20px; border-radius: 50%;  object-fit: contain; object-position: 20% 50%;">
        </v-img>
        <div>
          그누그누
          얼마주고샀어요?
          1분전
          ⁝
        </div>
      </div>
    </div>

    <div>
      <!-- 댓글 작성창 -->
      <div style="display: inline-flex; align-items: center;">
        <v-img src="@/assets/base_profile.png" alt="?" style="width:20px; height:20px; border-radius: 50%;  object-fit: contain; object-position: 20% 50%;">
        </v-img>
        조민수
        <input type="text" class="comment-write">
        <button class="comment-confirm">작성</button>
      </div>
    </div>

  </div>
</template>

<script>
import LoginUserHeaders from '@/components/headers/LoginUserHeaders.vue';
import NotLoginUserHeaders from '@/components/headers/NotLoginUserHeaders.vue';
export default {
  components:{
    LoginUserHeaders,
    NotLoginUserHeaders,
  },
  data(){
    return {
      commentCnt: 0,
      
    }
  },
  created(){
    //내용, 댓글 가져오는 함수 실행
  },
  methods:{
    //내용 가져오는 axios get 함수
    //댓글 가져오는 axio get 함수
    //댓글 작성해서 axios post하는 함수
  },
}
</script>

<style>
  .comment-write{
    width: 887px;
    height: 34px;
    border-radius: 10px;
    border-style: solid;
  }

  .comment-confirm{
    margin-left: 10px;
    width: 98px;
    height: 50px;
    font-size: 24px;
    font-weight: bold;
    justify-content: center;
    align-items: center;
    border-radius: 10px;
    color: white;
    background-color: RGB(106, 63, 193)
  }
</style>