<template>
  <div id="body">
    <header class="header">
      <a href="#" class="logo" @click="goLogin()">Fun&Speak外设论坛.</a>
      <nav class="navbar">
        <a href="#" :class="part==0?'active item':'item'"  style="--i: 1" >请输入用户名</a>
        <a href="#" :class="part==1?'active item':'item'"  style="--i: 2">请验证第一次密码</a>
        <a href="#" :class="part==3?'active item':'item'"  style="--i: 3" >完成</a>
        <!-- <a href="#" :class="userdate.part==4?'active item':'item'" style="--i: 5">Part5</a> -->
      </nav>
    </header>
    <section class="home" >
        <br>
        <div></div>
        <div class="show" style="margin-top: 190px;" v-show="part==0">
            <div class="home-content" >
                <h1>请输入您的用户名，以便找回您的密码 </h1>
                <h3><span class="multiple-text">
                  <input @keyup="creatName()" style="border: 0px;font-size: 33px;background: none;border-bottom:1px solid gainsboro ;color: white;outline: none;" type="text" v-model="username" placeholder="用户名">
                </span></h3>
                <p style="font-size: 25px;color: red;" v-show="flag">
                没有该用户
                </p>
                <a  class="btn" style="margin-top: 20px;"  @click="next()">下一步</a>
            </div>
        </div>
        <div class="show" style="margin-top: 190px;" v-show="part==1">
            <div class="home-content" >
                <h1>请输入您第一次注册时生成的密码</h1>
                <h3><span class="multiple-text">
                  <input @keyup="ckps()" style="border: 0px;font-size: 33px;background: none;border-bottom:1px solid gainsboro ;color: white;outline: none;" type="text" v-model="password" placeholder="首次密码">
                </span></h3>
                <p style="font-size: 25px;color: red;" v-show="flag">
                密码错误
                </p>
                <a  class="btn" style="margin-top: 20px;"  @click="next()">下一步</a>
            </div>
        </div>
        <div class="show" style="margin-top: 190px;" v-show="part==2">
            <div class="home-content" >
                <h3>已经重置好您的密码，若要修改密码请在登录后到个人中心修改</h3>
                <a  class="btn" style="margin-top: 20px;"  @click="goLogin()">完成</a>
            </div>
        </div>
    </section>
  </div>
</template>
<script setup>
import router from '@/router/router'
import { inject, ref } from 'vue'
const part = ref(0)
const $axios = inject('$axios')
const uid = ref(0)
const goLogin = () => {
  console.log($axios)
  router.push('./')
}
const password = ref('')
const flag = ref(false)
const creatName = () => {
  $axios({
    method: 'post',
    url: 'http://localhost:8086/user/getname',
    params: {
      name: username.value
    }
  }).then((resp) => {
    flag.value = resp.data
  })
}
const ckps = () => {
  $axios({
    url: 'http://localhost:8086/user/ckps',
    method: 'post',
    params: {
      name: username.value,
      ps: password.value
    }
  }).then((resp) => {
    flag.value = resp.data
    if (resp.data) {
      getId()
    }
  })
}
const getId = () => {
  $axios({
    url: 'http://localhost:8086/user/byname',
    method: 'post',
    params: {
      name: username.value
    }
  }).then((resp) => {
    uid.value = resp.data.id
    $axios({
      url: 'http://localhost:8086/admin/rp',
      method: 'post',
      params: {
        id: uid.value
      }
    })
  })
}
const next = () => {
  if (username.value === '') {
    return
  }
  if (flag.value === true) {
    return 0
  }
  part.value++
}
const username = ref('')
</script>
<style  scoped>
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: "Poppins", sans-serif;
}
#image-preview {
      width: 100%;
      height: 100%;
      width: 400px;
      height: 400px;
      background-size: contain;
      background-repeat: no-repeat;
      background-position: center center;
    }
#body {
    background: #1f242d;
    color: #fff;
}
#herdimg{
  width: 60px;
  height: 60px;
  border: 2px solid white;
   position: absolute;
   right: 14%;
   top: 14%;
   border-radius: 50%;
   z-index: 9999;
   transition: 0.5s;
   animation: zoomIn 1s ease forwards;
}
#user:hover #herdimg{
    width: 90px;
    height: 90px;
   right: 14%;
   top: 1%;
   border: 4px solid #b7b2a9;;
}
#as{
  font-size: 34px;
}
#user:hover #as{
  font-size: 40px;
  color: #b7b2a9;
}
.header {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    padding: 20px 10%;
    background: transparent;
    display: flex;
    justify-content: space-between;
    align-items: center;
    z-index: 100;
}
.userdate{
  background: none;
  border: 0px;
  border-bottom:1px solid white;
  outline: none;
  color: white;
  font-size: 33px;
}
.logo {
    font-size: 25px;
    color: #fff;
    text-decoration: none;
    font-weight: 600;
    cursor: default;
    opacity: 0;
    transition: 1s;
    animation: slideRight 1s ease forwards;
}
.logo:hover{
    color: rgb(231, 88, 112);
}
.navbar a {
    display: inline-block;
    font-size: 18px;
    color: #fff;
    text-decoration: none;
    font-weight: 500;
    margin-left: 35px;
    opacity: 0;
    transition: 0.3s;
    animation: slideTop 1s ease forwards;
    animation-delay: calc(.2s * var(--i));
}

.navbar a:hover,
.navbar a.active {
    color: #b7b2a9;
}

.home {
    width: 100%;
    height: 100vh;
    align-items: center;
}
.show{
    top: 30px;
    position: relative;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 70px 10% 0;
}
.home-content {
    max-width: 600px;
}

.home-content h3 {
    font-size: 32px;
    font-weight: 700;
    opacity: 0;
    animation: slideBottom 1s ease forwards;
    animation-delay: .7s;
}
.ty{
  animation: slideBottom 1s ease forwards;
}
.home-content h3:nth-of-type(2) {
    margin-bottom: 30px;
    opacity: 0;
    animation: slideTop 1s ease forwards;
    animation-delay: .7s;
}

.home-content h3 span {
    color: #b7b2a9;
}

.home-content h1 {
    font-size: 56px;
    font-weight: 700;
    margin: -3px 0;
    opacity: 0;
    animation: slideRight 1s ease forwards;
    animation-delay: 1s;
}

.home-content p {
    font-size: 16px;
    opacity: 0;
    animation: slideLeft .4s ease forwards;
}
p{
  animation: zoomIn 0.1s ease forwards
}
.home-img img {
    max-width: 750px;
    border-radius: 50%;
    margin-right: -20px;
    box-shadow: 0 0 20px #b7b2a9;
    opacity: 0;
    animation: zoomIn 1s ease forwards, floatImage 4s ease-in-out infinite;
    animation-delay: 2s, 3s;
}
.home-img .userimg {
    max-width: 750px;
    border-radius: 50%;
    margin-right: -20px;
    box-shadow: 0 0 20px #b7b2a9;
    opacity: 0;
    animation: zoomIn 1s ease forwards, floatImage 4s ease-in-out infinite;
    animation-delay: 0.1s, 3s;
}

.social-media a {
    display: inline-flex;
    justify-content: center;
    align-items: center;
    width: 40px;
    height: 40px;
    background: transparent;
    border: 2px solid #b7b2a9;
    border-radius: 50%;
    color: #b7b2a9;
    font-size: 20px;
    text-decoration: none;
    margin: 30px 15px 30px 0;
    transition: 0.5s ease;
    opacity: 0;
    animation: slideLeft 1s ease forwards;
    animation-delay: calc(0.2s * var(--i));
}

.social-media a:hover {
    background: #b7b2a9;
    color: #1f242d;
    box-shadow: 0 0 20px #b7b2a9;
}

.btn {
    display: inline-block;
    padding: 12px 28px;
    background: #b7b2a9;
    box-shadow: 0 0 10px #b7b2a9;
    text-decoration: none;
    border-radius: 40px;
    font-size: 16px;
    color: #1f242d;
    letter-spacing: 1px;
    font-weight: 600;
    transition: 0.5s ease;
    opacity: 0;
    animation: slideTop 1s ease forwards;
    animation-delay: 2s;
}

.btn:hover {
    box-shadow: 0 0 20px #b7b2a9;
}

.btn:active {
    background: none;
    color: #b7b2a9;
    border: 2px solid #b7b2a9;
}

</style>
