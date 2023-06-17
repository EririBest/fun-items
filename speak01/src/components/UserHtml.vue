<template>
  <div id="body">
    <header class="header">
      <a href="#" class="logo">Fun&Speak外设论坛.</a>
      <nav class="navbar">
        <a href="#" :class="userdate.part==0?'active item':'item'"  style="--i: 1" v-show="userdate.part!=3">准备</a>
        <a href="#" :class="userdate.part==1?'active item':'item'"  style="--i: 2" v-show="userdate.part!=3">上传头像</a>
        <a href="#" :class="userdate.part==2?'active item':'item'"  style="--i: 3" v-show="userdate.part!=3">入站信息</a>
        <a href="#" :class="userdate.part==3?'active item':'item'"  style="--i: 4" v-show="userdate.part!=3">完成</a>
        <!-- <a href="#" :class="userdate.part==4?'active item':'item'" style="--i: 5">Part5</a> -->
          <div id="user" style="height: 100px;" @click="goPerson()" v-show="userdate.part==3" >
            <img :src="'http://localhost:8086/'+userdate.imgpath" id="herdimg">
          </div>
      </nav>
    </header>
    <section class="home" >
        <br>
        <div></div>
        <div class="show" style="margin-top: 190px;" v-show="userdate.part==0">
            <div class="home-content" >
                <h3>Hello,{{ userdate.name }}</h3>
                <h1>Welcome To Fun&Speak </h1>
                <h3>进入社区前请先完成以下设置<span class="multiple-text"></span></h3>
                <p>
                1.选择一个喜欢的图片作为头像  2.完善你的个人信息
                </p>
                <a  class="btn" style="margin-top: 20px;"  @click="userdate.part++,getElement()">下一条</a>
            </div>
            <div class="home-img">
                <img src="../assets/wallhaven-g7987q_1280x720.png" alt="" />
            </div>
        </div>
        <div class="show" style="margin-top: 190px;" v-show="userdate.part==1">
            <div class="home-content" >
                <h3>Hello,{{ userdate.name }}</h3>
                <h1>请上传一张图片作为你的头像 </h1>
                <h3>你还需要完成<span class="multiple-text"></span></h3>
                <p>
                完善你的个人信息
                </p>
                <a class="btn" style="margin-top: 20px;animation-delay: 0.4s;" @click="userdate.part++,upuserimg()" v-show="next>0">下一条</a>
            </div>
            <div class="home-img">
              <p><span style="font-size: 30px;font-weight: 900;">頭</span><span style="font-size: 25px;font-weight: 600;">像</span><span style="font-size: 35px;font-weight:700;">预</span>览：</p>
              <img id="image-preview" class="userimg" v-show="showimg">
            <div>
                <form action="" id="upload">
                    <input type="text" v-model="userdate.name" hidden name="name">
                    <input type="text" v-model="userdate.id" hidden name="id">
                    <input type="file" name="photo" style="opacity: 0;position: absolute;" id="file" @change="upimg(),showimg=true,next++"  accept="image/gif, image/jpeg, image/png, image/jpg">
                </form>
                <button style="border: 0px;z-index: 1; width: 120px;height: 33px;border-radius:12px;font-size: 22px;background: #b7b2a9;box-shadow: 0 0 10px #b7b2a9;">上传头像</button>
            </div>
            </div>
        </div>
        <div class="show" style="margin-top: 190px;" v-show="userdate.part==2" >
            <div class="home-content" >
                <h3>完善个人信息</h3>
                <h1>之后进入社区</h1>
                <h3>
                <form action="" id="part3">
                 <input type="text" v-model="userdate.id" hidden name="id">
                 <input type="text" name="ipone" v-model="ipone" class="userdate" placeholder="电话号码(必填)"> <br><br>
                 <textarea wrap="hard"
                  maxlength="255"
                  cols="10"
                  rows="3"
                  placeholder="个人简介(选填)"
                  name="text"
                  style="resize: none;width: 600px;height: 100px;font-size: 20px;color: white;background-color: black;"
                 ></textarea>
                </form>
                </h3>
                <h3>入站须知</h3>
                <p>
                1.交易期间请勿诱导或脱离平台交易,如脱离平台交易造成损失,请自行承担 <br>
                2.禁止在讨论区和交易区发送色情,暴力,反动的信息！！！<br>
                3.请文明发言,祖安他人可被导致禁言或封号
                </p>
                <h3><input type="checkbox" style="width: 20px;height: 20px;border-radius: 10px;" id="cxk"> &nbsp;了解以上规定</h3>
                <a href="#" class="btn" style="margin-top: 20px;" @click="adduserdata()">完成设置</a>
            </div>
            <div class="home-img">
                <img src="../assets/wallhaven-g7987q_1280x720.png"/>
            </div>
        </div>
        <div class="show" style="margin-top: 190px;" v-show="userdate.part==3" >
          <div class="home-content" style="position: absolute;top: -30px;"><h1 >请选择开始浏览的页面</h1></div>
            <div class="home-img" @click="gobuy">
                <img class="userimg" src="../assets/2.jpg" alt="交易区"/>
                <h1 class="ty" style="position: absolute;">交易区</h1>
            </div>
            <div class="home-img" @click="gotake">
                <img class="userimg" src="../assets/wallhaven-o3lx3l.png" alt="讨论区"/>
                <h1 class="ty" style="position: absolute;">讨论区</h1>
            </div>
        </div>
    </section>
  </div>
</template>
<script setup>
import router from '@/router/router'
import { ref, inject } from 'vue'
const userdate = ref(JSON.parse(window.sessionStorage.getItem('user')))
const $axios = inject('$axios')
const next = ref(0)
const ipone = ref('')
const showimg = ref(false)
let fileInput
let preview
const upimg = () => {
  preview.style.backgroundImage = ''
  if (!fileInput.value) {
    return
  }
  const file = fileInput.files[0]
  const size = file.size
  if (size >= 1 * 1024 * 1024) {
    alert('文件大小超出限制')
    return false
  }
  // 获取File信息:
  if (!['image/jpeg', 'image/png', 'image/gif'].includes(file.type)) {
    alert('不是有效的图片文件!')
    return
  }
  // 读取文件:
  const reader = new FileReader()
  reader.onload = function (e) {
    const data = e.target.result
    console.log(preview, 'a标签')
    preview.src = data
  }
  // 以DataURL的形式读取文件:
  reader.readAsDataURL(file)
}
const getElement = () => {
  fileInput = document.getElementById('file')
  preview = document.getElementById('image-preview')
  console.log(fileInput)
  console.log(preview)
}
const upuserimg = () => {
  const fd = document.getElementById('upload')
  console.log(fd)
  $axios({
    method: 'post',
    url: 'http://localhost:8086/user/up',
    data: fd
  }).then((resp) => {
    userdate.value.imgpath = resp.data
    window.sessionStorage.setItem('user', JSON.stringify(userdate.value))
    uppart()
  })
}
const uppart = () => {
  $axios({
    method: 'post',
    url: 'http://localhost:8086/user/uppart',
    params: {
      id: userdate.value.id,
      part: userdate.value.part
    }
  })
}
const gotake = () => {
  window.sessionStorage.setItem('look', 50)
  router.push('./index')
}
const gobuy = () => {
  window.sessionStorage.setItem('look', 0)
  router.push('./index')
}
const goPerson = () => {
  router.push('./person')
}
const adduserdata = () => {
  const boxd = document.getElementById('cxk')
  const nd = document.getElementById('part3')
  if (ipone.value !== '' && boxd.checked) {
    userdate.value.part++
    window.sessionStorage.setItem('user', JSON.stringify(userdate.value))
    $axios({
      method: 'post',
      url: 'http://localhost:8086/user/adddata',
      data: nd
    }).then(() => {
      uppart()
      window.sessionStorage.setItem('ipone', ipone.value)
    })
  } else {
    alert('你似乎漏了些东西没看')
  }
}
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
    animation: slideLeft 1s ease forwards;
    animation-delay: 0.7s;
}
p{
  animation: zoomIn 1s ease forwards
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
