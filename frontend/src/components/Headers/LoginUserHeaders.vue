<template>
  <div class="navbar">
    <span>
      <a href="/">
        <img src="@\assets\logo.png" alt="로고" class="logo" />FANFTASY
      </a>
    </span>
    <span class="headerlist">
      |
      <router-link to="/" style="text-decoration: none">NFT 마켓</router-link> |
      <router-link to="/about" style="text-decoration: none"
        >커뮤니티</router-link
      >
      |
    </span>
    <label for="" class="box" style="margin-left: 300px">
      <input
        type="text"
        class="notting"
        style="
          width: 350px;
          height: 40px;
          display: flex;
          justify-content: left;
          float: left;
        "
      />
      <button
        href=""
        class="notting"
        style="display: flex; justify-content: right; align-self: center"
      >
        <img src="@/assets/Search.png" alt="" style="margin-top: 7px" />
      </button>
    </label>
    <span>
      <a href="">
        <img
          src="@\assets\chatting.png"
          alt="채팅"
          style="margin-right: 20px"
        />
      </a>
      <a href="">
        <img src="@\assets\bell.png" alt="알림" style="margin-right: 20px" />
      </a>
      <a href="">
        <img src="@\assets\wallet.png" alt="지갑" style="margin-right: 20px" />
      </a>
      <a href="">
        <img
          src="@\assets\base_profile.png"
          alt="기본 프로필 사진"
          style="margin-right: 20px"
        />
      </a>
    </span>
  </div>
</template>

<script>
export default {
  name: "NotLoginUserHeaders",
}
</script>

<style>
.navbar {
  height: 50px;
  display: flex;
  align-items: center;
}

.logo {
  width: 35px;
  height: 35px;
}

.headerlist {
  font-size: medium;
  text-decoration: none;
}

.box {
  width: 400px;
  height: 40px;
  border-radius: 15px;
  border: 1px solid;
}

.notting {
  background: none;
  border: none;
}
</style>
