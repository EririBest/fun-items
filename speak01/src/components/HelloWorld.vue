<template>
  <div id="index_body">
    <div class="container2">
        <div class="line" style="--color:#ec3e27;--x:3;--z:3;--d:1;"></div>
        <div class="line" style="--color:#fff;--x:3;--z:2;--d:2;"></div>
        <div class="line" style="--color:#fff;--x:4;--z:1;--d:3;"></div>
        <div class="line" style="--color:#fd79a8;--x:4;--z:0;--d:1;"></div>
        <div class="line" style="--color:#fff;--x:6;--z:-1;--d:2;"></div>
        <div class="line" style="--color:#0984e3;--x:6;--z:-2;--d:3;"></div>
        <div class="line" style="--color:#fff;--x:8;--z:-3;--d:1;"></div>
        <div class="line" style="--color:#fff;--x:10;--z:-4;--d:2;"></div>
        <div class="line" style="--color:#fff;--x:12;--z:-5;--d:3;"></div>
        <div class="line" style="--color:#fff;--x:14;--z:-6;--d:1;"></div>
        <div class="line" style="--color:#fff;--x:16;--z:-7;--d:2;"></div>
        <div class="line" style="--color:#fff;--x:18;--z:-8;--d:3;"></div>
        <div class="line" style="--color:#e086fd;--x:20;--z:-9;--d:1;"></div>
    </div>
    <div class="btnss" style="right: 45px;top:10px">夜</div>
    <div class="btnss" style="right: 15px;top:10px">昼</div>
    <div style="position: absolute;top: 10%;">
      <h1 class="neon-text">Fun外设社区</h1>
    </div>
    <div class="container" style="z-index: 999;">
        <div class="form-box">
            <!-- 注册 -->
            <div class="register-box hidden">
                <h1>register</h1>
                    <span id="tip" style="color: #ec3e27;"></span>
                    <input type="text" v-model="username" @keyup="creatName" placeholder="用户名">
                    <span style="font-size: 10px;color: pink;" v-show="!flag">用户名已经存在</span>
                    <!-- <input type="email" placeholder="邮箱">
                    <input type="password" placeholder="密码"> -->
                    <input type="password" v-model="pck" placeholder="确认密码">
                    <button @click="addUser">注册</button>
            </div>
            <!-- 登录 -->
            <div class="login-box">
                <h1>login</h1>
                <span id="tips" style="color: #ec3e27;"></span>
                <input type="text" v-model="username"  placeholder="用户名">
                <input type="password" v-model="pck" placeholder="密码">
                <span style="color: white;cursor: pointer;" @click="goTest()">忘记密码</span>
                <button @click="userLogin">登录</button>
            </div>
        </div>
        <div class="con-box left">
            <h2>扫描下方二维码</h2>
            <h2><span>获取密码</span></h2>
            <img src="http://localhost:8086/cod/img" alt="随机密码">
            <!-- <img src="../images/cat/1.png" alt=""> -->
            <p>已有账号</p>
            <button id="login">去登录</button>
        </div>
        <div class="con-box right">
            <h2>欢迎登录</h2>
            <h2><span style="font-weight: 900;font-family: 'Times New Roman', Times, serif;font-size: 33px;">Fun</span>外设社区</h2>
            <p>没有账号？</p>
            <button id="register" @click="getpassword()">去注册</button>
        </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { inject, ref, onMounted } from 'vue'
const router = useRouter()
const pasword = ref('')
const flag = ref()
const username = ref()
const pck = ref()
const $axios = inject('$axios')
const getpassword = () => {
  if (pasword.value === null || pasword.value === '') {
    flag.value = true
    $axios({
      method: 'post',
      url: 'http://localhost:8086/cod/password'
    }).then((resp) => {
      pasword.value = resp.data
    })
  }
}
const goTest = () => {
  router.push('./test')
}
// 注册用户函数
const addUser = () => {
  if (pasword.value === pck.value && username.value !== '' && flag.value === true) {
    console.log('注册成功')
    $axios({
      method: 'post',
      url: 'http://localhost:8086/user/register',
      params: {
        name: username.value,
        password: pck.value
      }
    }).then((resp) => {
      userLogin()
    })
  } else {
    document.getElementById('tip').innerHTML = '注册失败,确认密码错误'
  }
}
// 检测用户是否重名
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
// 登录验证
const userLogin = () => {
  $axios({
    method: 'post',
    url: 'http://localhost:8086/user/login',
    params: {
      name: username.value,
      password: pck.value
    }
  }).then((resp) => {
    if (resp.data) {
      const userdate = resp.data
      if (userdate.flag === 0) {
        document.getElementById('tips').innerHTML = '该账户已被封禁,请联系管理员进行解封'
        return
      }
      window.sessionStorage.setItem('user', JSON.stringify(userdate))
      window.sessionStorage.setItem('look', 50)
      router.push('./user')
    } else {
      document.getElementById('tips').innerHTML = '登录失败,账号或密码错误'
    }
  })
}
const use = () => {
  const login = document.getElementById('login')
  const register = document.getElementById('register')
  const formbox = document.getElementsByClassName('form-box')[0]
  const registerbox = document.getElementsByClassName('register-box')[0]
  const loginbox = document.getElementsByClassName('login-box')[0]
  // 去注册按钮点击事件
  register.addEventListener('click', () => {
    formbox.style.transform = 'translateX(80%)'
    loginbox.classList.add('hidden')
    registerbox.classList.remove('hidden')
  })
  // 去登录按钮点击事件
  login.addEventListener('click', () => {
    formbox.style.transform = 'translateX(0%)'
    registerbox.classList.add('hidden')
    loginbox.classList.remove('hidden')
  })
}
onMounted(() => {
  use()
})
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.neon-text {
      font-family: 'Monoton', cursive;
      font-size: 100px;
      animation: neon 3s infinite;
    }
.container2 {
    position: absolute;
    width: 100vw;
    height: 100vh;
    background: linear-gradient(200deg,#291f21,#0e50b3);
    overflow: hidden;
    display: flex;
    justify-content: center;
    align-items: center;
    perspective: 800px;
    perspective-origin: left;
    transform-style: preserve-3d;
}

:root {
    --background-color: #2c3e50;
    --border-color: #7591AD;
    --text-color: #34495e;
    --color1: #ec3e27;
    --color2: #fd79a8;
    --color3: #0984e3;
    --color4: #00b894;
    --color5: #fdcb6e;
    --color6: #e056fd;
    --color7: #F97F51;
    --color8: #BDC581;
}
.line {
    position: absolute;
    width: 200px;
    height: 3px;
    border-radius: 3px;
    /* background-color: #fff; */
    background-image: linear-gradient(to right, var(--color), #ffffff50, transparent);
    animation: down 1s linear infinite both;
    animation-delay: calc(var(--d) * 0.3s);
}

.line::before,
.line::after {
    position: absolute;
    content: "";
    width: inherit;
    height: inherit;
    background-image: inherit;
}

.line::before {
    filter: blur(5px);
}

.line::after {
    filter: blur(10px);
}

@keyframes down {
    0% {
        transform:
        translateY(calc(var(--z) * 60px))
        translateZ(calc(var(--z) * 100px))
        rotate(-45deg)
        translateX(calc(var(--x) * 100px))
        ;
    }
    100% {
        transform:
        translateY(calc(var(--z) * 60px))
        translateZ(calc(var(--z) * 100px))
        rotate(-45deg)
        translateX(calc(var(--x) * -100px))
        ;
    }
}
@keyframes neon {
      0%, 30%, 32%, 34% {
        color: #222;
        text-shadow: -3px 0px 5px #222, 0px 0px 2px #222;
      }
      31%, 33%, 35%, 100% {
        color: #e91e63;
        text-shadow: -3px 0px 5px #e91e63, 0px 0px 2px #e91e63;
      }
    }
#index_body{
    height: 100vh;
    /* 弹性布局 水平+垂直居中 */
    display: flex;
    justify-content: center;
    align-items: center;
}
.container{
    background-color: #000000;
    width: 650px;
    height: 415px;
    animation: zoomIn 1s ease forwards;
    border-radius: 5px;
    /* 阴影 */
    box-shadow: 5px 5px 5px rgba(0,0,0,0.1);
    /* 相对定位 */
    position: relative;
}
.form-box{
    /* 绝对定位 */
    position: absolute;
    top: -10%;
    left: 5%;
    background-color: #0e203b;
    width: 320px;
    height: 500px;
    border-radius: 5px;
    box-shadow: 2px 0 10px rgba(0,0,0,0.1);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 2;
    /* 动画过渡 加速后减速 */
    transition: 0.5s ease-in-out;
}
.register-box,.login-box{
    /* 弹性布局 垂直排列 */
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
}
.hidden{
    display: none;
    transition: 0.5s;
}
h1{
    text-align: center;
    margin-bottom: 25px;
    /* 大写 */
    text-transform: uppercase;
    color: #fff;
    /* 字间距 */
    letter-spacing: 5px;
}
#roles{
  transition-duration: 1.1s;
  width: 170px;
  height: 30px;
  font-size: 20px;
  border-radius: 10px;
  outline: none;
  color: #a262ad;
  background-color: #f6f6f6;
  border: 0px;
}
#roles:hover{
  color: #f6f6f6;
  background-color: #a262ad;
}
input{
    background-color: transparent;
    width: 70%;
    color: #fff;
    border: none;
    /* 下边框样式 */
    border-bottom: 1px solid rgba(255,255,255,0.4);
    padding: 10px 0;
    text-indent: 10px;
    margin: 8px 0;
    font-size: 14px;
    letter-spacing: 2px;
}
input::placeholder{
    color: #fff;
}
input:focus{
    color: #a262ad;
    outline: none;
    border-bottom: 1px solid #a262ad80;
    transition: 0.5s;
}
input:focus::placeholder{
    opacity: 0;
}
.form-box button{
    width: 70%;
    margin-top: 35px;
    background-color: #f6f6f6;
    outline: none;
    border-radius: 8px;
    padding: 13px;
    color: #a262ad;
    letter-spacing: 2px;
    border: none;
    cursor: pointer;
}
.form-box button:hover{
    background-color: #a262ad;
    color: #f6f6f6;
    transition: background-color 0.5s ease;
}
.con-box{
    width: 50%;
    /* 弹性布局 垂直排列 居中 */
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    /* 绝对定位 居中 */
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
}
.con-box.left{
    left: -2%;
}
.con-box.right{
    right: -2%;
}
.con-box h2{
    color: #8e9aaf;
    font-size: 25px;
    font-weight: bold;
    letter-spacing: 3px;
    text-align: center;
    margin-bottom: 4px;
}
.con-box p{
    font-size: 12px;
    letter-spacing: 2px;
    color: #8e9aaf;
    text-align: center;
}
.con-box span{
  text-shadow: -3px 0px 5px #e91e63, 0px 0px 2px #e91e63;
    color: #e91e63;
}
.con-box img{
    width: 150px;
    height: 150px;
    opacity: 0.9;
    margin: 40px 0;
}
.con-box button{
    margin-top: 3%;
    background-color: #fff;
    color: #a262ad;
    border: 1px solid #d3b7d8;
    padding: 6px 10px;
    border-radius: 5px;
    letter-spacing: 1px;
    outline: none;
    cursor: pointer;
}
.con-box button:hover{
    background-color: #d3b7d8;
    color: #fff;
}
.btnss{
  position: absolute;
  height: 30px;
  width: 30px;
  color: #fff;
  transition:0.6S;
}
.btnss:hover{
  font-size: 23px;
}
</style>
