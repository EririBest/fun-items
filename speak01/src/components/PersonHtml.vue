<template>
  <div id="body">
    <img src="../assets/wallhaven-g7987q_1280x720.png" style="width: 100%;position:fixed;" id="ts" v-show="userdate.logpath===null">
    <img :src="'http://localhost:8086/'+userdate.logpath" style="width: 100%;position:fixed;opacity: 2;" alt="" v-show="userdate.logpath!==null">
    <div style="width: 80%;min-height:100vh;position:fixed;z-index: 999;animation: zoomIn 1s ease forwards;left: 10%;background: #000;border-radius: 3%;overflow: hidden;" v-show="flag3">
        <button @click="flag3=!flag3" style="position: absolute;font-size: 33px;right: 3%;background: none;border: 0px;">退出</button>
        <AddGoods/>
    </div>
    <div style="width: 30%;top:20% ;min-height:50vh;position:fixed;z-index: 999;animation: zoomIn 1s ease forwards;left: 35%;background: #ffffff;border-radius: 3%;overflow: hidden;" v-show="flag4">
      <form action="" id="user" style="margin: 0 auto;margin-top: 10%;font-size: 30px;">
        <input type="text" name="id" v-model="userdate.id" hidden>
        <input type="text" name="oldname" v-model="userdate.name" hidden>
        &#12288;用户名:<input type="text" name="name" v-model="newNmae" maxlength="10"  style="border:  0px;border-bottom: 1px solid black;outline: none;font-size: 25px;" @keyup="upname()"> <br>
        <div style="text-align: center;" v-show="!showf">已经存在该用户名</div>
        &#12288;简&#12288;介:<textarea wrap="hard"
                    cols="10"
                    rows="3"
                    maxlength="30"
                    placeholder="说点什么吧....."
                    name="text"
                    style="resize: none;vertical-align: top;left: 5%;white-space: pre-wrap;width: 90%;min-height: 40px;font-size: 26px;color: rgb(255, 255, 255);outline: none;border: 0px;position: relative;background-color: #000;"
                    v-model="userdate.text">
        </textarea>
        <br>
        &#12288;密&#12288;码: <input type="password" v-model="userdate.password" v-show="!flag5" disabled maxlength="10" style="border:  0px;border-bottom: 1px solid black;outline: none;">
        <input type="text" v-model="userdate.password" name="password" v-show="flag5" maxlength="10" style="border:  0px;border-bottom: 1px solid black;outline: none;font-size: 25px;"><br>
        <div style="text-align: center;font-size: 25px;">如果需要修改密码,请输入旧密码,按回车验证</div>
        &#12288;&#12288;&#12288;&#12288;&ensp;<input class="frt" type="text" style="border:  0px;border-bottom: 1px solid black;outline: none;font-size: 25px;" v-model="uppas" @change="ckpas()" placeholder="验证密码"><br>
        &#12288;手机号: <input class="frt" name="ipone" type="text" style="border:  0px;border-bottom: 1px solid black;outline: none;font-size: 25px;" maxlength="13" v-model="userdate.ipone">
      </form>
      <button style="top: 80%;" class="cg" @click="updateUserdata(),flag4=false">保存</button>
    </div>
    <div  id="ty" @click="golook()">
      <div  style="" id="tu">
        <img src="../assets/19-键盘.png" style="min-height: 60px;height: 80px;position: relative;left: 10px;"  alt="">
        <h5 style="position: absolute;left: 25px;top: 75px;">回到社区</h5>
      </div>
    </div>
    <div id="personbody">
      <div id="header">
        <div id="headerimg">
          <img :src="'http://localhost:8086/'+userdate.imgpath"  style="height: 250px;">
        </div>
        <h1 id="username">{{ userdate.name }}</h1>
        <h1 id="usertext" >{{ userdate.text }}</h1>
        <!-- <h1>粉丝</h1> -->
        <button class="cg" style="top: 25%;" @click="flag4=!flag4" >编辑个人资料</button>
        <form action="" id="upload">
          <input type="text"  v-model="userdate.id" name="id" hidden>
          <input type="text" v-model="userdate.name" name="name" hidden>
          <input type="file" name="photo"  @change="test()" class="cg" style="z-index: 999;opacity: 0;top: 45%;"  accept="image/gif, image/jpeg, image/png, image/jpg">
        </form>
        <button class="cg" style="top: 45%;" @click="test()">修改头像</button>
        <form id="upimg" action="">
          <input type="file" name="photo"  id="file"  @change="upimg()" class="cg" style="z-index: 999;opacity: 0;"  accept="image/gif, image/jpeg, image/png, image/jpg">
        </form>
        <button class="cg" style="z-index: 1;">修改背景图片</button>
      </div>
      <div style="width: 100%;display: flex;flex-direction: row;">
        <div class="qh" @click="lookmy=false">我的商品 {{ ccList.clist.length }}</div>
        <div class="qh" @click="lookmy=true">交易记录 {{ (outList.lss.length + buyList.lss.length) }}</div>
      </div>
      <div id="minbody" v-show="!lookmy">
        <div class="goods" v-for="item in ccList.clist" :key="item" @click="toBuy(item.id)">
          <div class="titles">
            <div style="white-space: nowrap;">{{item.title}}</div>
            <h5 >价格:<span style="color: red;">{{ item.price }}</span></h5>
          </div>
          <img :src="'http://localhost:8086/'+item.imgpath" alt="" class="gimgs">
        </div>
        <button @click="flag3=!flag3">+</button>
      </div>
      <div id="minbody2" v-show="lookmy">
       <div style="width: 50%;height: auto;min-height: 100px;margin: 0 auto;">
        <br>
        <br>
        <h2 style="text-align: center;">我卖出的 {{ outList.lss.length }}</h2>
        <div v-for="item in outList.lss" :key="item" style="width: 90%;margin-left: 5%;position: relative;margin-top: 10px;background: #000;border-radius: 5px;opacity: 0.7;height: 80px;">
          <div style="width: 80px;height: 80px;overflow: hidden;position: absolute;left: 10px;">
            <img :src="'http://localhost:8086/'+item.gimg" style="height: 80px;">
          </div>
          <div style="text-align: center;">
            <h2>交易时间：{{ item.ctime }}</h2>
            <h4>商品名称：{{ item.gname }}</h4>
          </div>
        </div>
      </div>
       <div style="width: 50%;height: auto;min-height: 100px;margin: 0 auto; ">
        <br>
        <br>
        <h2 style="text-align: center;">我买到的 {{ buyList.lss.length }}</h2>
        <div v-for="item in buyList.lss" :key="item" style="width: 90%;margin-left: 5%;position: relative;margin-top: 10px;background: #000;border-radius: 5px;opacity: 0.7;height: 80px;">
          <div style="width: 80px;height: 80px;overflow: hidden;position: absolute;left: 10px;">
            <img :src="'http://localhost:8086/'+item.gimg" style="height: 80px;">
          </div>
          <div style="text-align: center;">
            <h2>交易时间：{{ item.ctime }}</h2>
            <h4>商品名称：{{ item.gname }}</h4>
          </div>
        </div>
      </div>
      </div>
      <div id="bottomBody">
      </div>
    </div>
  </div>
</template>
<script setup>
import router from '@/router/router'
import { inject, onMounted, reactive, ref } from 'vue'
const userdate = ref(JSON.parse(window.sessionStorage.getItem('user')))
const flag3 = ref(false)
const $axios = inject('$axios')
const flag4 = ref(false)
const buyList = reactive({
  lss: []
})
const lookmy = ref(false)
const outList = reactive({
  lss: []
})
const flag5 = ref(false)
const newNmae = ref(userdate.value.name)
const ckpas = () => {
  console.log(uppas.value)
  if (uppas.value === userdate.value.password) {
    console.log(22222)
    flag5.value = true
  }
}
const ccList = reactive({
  clist: []
})
const uppas = ref('')
const toBuy = (str) => {
  router.push('./buy/' + str)
}
const getMyOut = () => {
  $axios({
    url: 'http://localhost:8086/user/out',
    method: 'post',
    params: {
      id: userdate.value.id
    }
  }).then((resp) => {
    outList.lss = resp.data
  })
}
const getMybuy = () => {
  $axios({
    url: 'http://localhost:8086/user/buy',
    method: 'post',
    params: {
      id: userdate.value.id
    }
  }).then((resp) => {
    buyList.lss = resp.data
  })
}
const upimg = () => {
  const ts = document.getElementById('ts')
  const fileInput = document.getElementById('file')
  ts.style.backgroundImage = ''
  if (!fileInput.value) {
    return
  }
  const file = fileInput.files[0]
  const size = file.size
  if (size >= 6 * 1024 * 1024) {
    alert('文件大小超出限制')
    return false
  }
  const fd = document.getElementById('upimg')
  $axios({
    url: 'http://localhost:8086/user/updateBgImg',
    method: 'post',
    data: fd,
    params: {
      id: userdate.value.id,
      name: userdate.value.name
    }
  }).then((resp) => {
    window.sessionStorage.setItem('user', JSON.stringify(resp.data))
    window.location.reload()
  })
}
const showf = ref(true)
const upname = () => {
  $axios({
    url: 'http://localhost:8086/user/getname',
    method: 'post',
    params: {
      name: newNmae.value.name
    }
  }).then((resp) => {
    console.log(resp.data)
    showf.value = resp.data
  })
}
const updateUserdata = () => {
  if (!showf.value) {
    return
  }
  const fd = document.getElementById('user')
  $axios({
    url: 'http://localhost:8086/user/update',
    method: 'post',
    data: fd
  }).then((resp) => {
    if (resp.data === false) {
      alert('更新失败')
    } else {
      alert('更新成功')
      $axios({
        url: 'http://localhost:8086/user/byname',
        method: 'post',
        params: {
          name: newNmae.value
        }
      }).then((resp) => {
        const udata = resp.data
        window.sessionStorage.setItem('user', JSON.stringify(udata))
        window.location.reload()
      })
    }
  })
}
const getGoodsList = () => {
  $axios({
    method: 'post',
    url: 'http://localhost:8086/ccs/getUserCommoditys',
    params: {
      userId: userdate.value.id
    }
  }).then((resp) => {
    ccList.clist = resp.data
  })
}
const test = () => {
  console.log(12312312)
  const fd = document.getElementById('upload')
  $axios({
    url: 'http://localhost:8086/user/up',
    method: 'post',
    data: fd
  }).then((resp) => {
    $axios({
      url: 'http://localhost:8086/user/login',
      method: 'post',
      params: {
        name: userdate.value.name,
        password: userdate.value.password
      }
    }).then((resp) => {
      window.sessionStorage.setItem('user', JSON.stringify(resp.data))
      window.location.reload()
    })
  })
}
const golook = () => {
  router.push('./index')
}
onMounted(() => {
  getGoodsList()
  getMyOut()
  getMybuy()
})
</script>
<style scoped>
#body{
  width: 100%;
  height: auto;
  min-height: 100vh;
}
#personbody{
  position: relative;
  color: aliceblue;
  width: 80%;
  left: 10%;
  min-height: 100vh;
  backdrop-filter: blur(6px);
  height: auto;
}
.qh{
  margin: 0 auto;
  height: 40px;
  width: 190px;
  font-size: 25px;
  text-align: center;
  border-radius: 10px;
}
.qh:hover{
  box-shadow: 0 0 20px #ebe6de;
  cursor: pointer;
}
#bottomBody{
  width: 100%;
  height: 300px;
}
#header{
  width: 100%;
  height: 300px;
  position: relative;
}
#minbody{
  width: 100%;
  height: auto;
}
#minbody2{
  width: 100%;
  height: auto;
  display: flex;
  flex-direction: row;
}
#minbody button{
  width: 400px;
  height: 400px;
  border-radius: 30px;
  margin-left: 5%;
  margin-top: 5%;
  border: 0px;
  font-size: 100px;
  transition: all 1s;
  opacity: 0.5;
}
.titles{
  width: 100%;
  min-height: 100px;
  background-color: black;
  opacity: 0;
  top:75%;
  color: white;
  text-align: center;
  font-size: 41px;
  transition: all 1s;
  position: absolute;
}
.gimgs{
  min-width: 400px;
  height: 400px;
}
#minbody button:hover{
  opacity: 0.9;
}
#headerimg{
  width: 250px;
  height: 250px;
  overflow: hidden;
  position: absolute;
  border-radius: 5%;
  box-shadow: 0 0 20px #ebe6de;
  top: 6%;
  left: 2%;
}
.goods{
  position: relative;
  width: 400px;
  height: 400px;
  border-radius: 30px;
  display: inline-block;
  margin-top: 5%;
  margin-left: 5%;
  transition: all 1s;
  overflow: hidden;
  vertical-align:bottom;
}
.goods:hover .titles{
  opacity: 0.8;
}
#username{
  font-size: 100px;
  position: absolute;
  top: 5%;
  left: 21%;
}
#usertext{
  position: absolute;
  top: 60%;
  left: 21%;
}
.cg{
  width: 140px;
  height: 50px;
  position: absolute;
  top: 200px;
  right: 1%;
  transition: 0.2s;
  font-size: 20px;
  opacity: 0.8;
  border-radius: 10px;
  border: 0px;
}
.cg:hover{
  opacity: 1;
  color: cornflowerblue;
  box-shadow: 0 0 20px #ebe6de;
}
#ty{
  width: 11px;
  height: 100px;
  background-color: white;
  position: fixed;left: 0%;
  top: 10%;
  overflow: hidden;
  border-radius: 0% 95% 95% 0%;
  transition:all .5s;
}
#tu{
  width: 100px;height: 100px;position: relative;top: 0%;
}
#ty:hover{
  width: 100px;
  background-color: #ebe6de;
  border-radius: 0% 10% 10% 0%;
  box-shadow: 0 0 20px #ebe6de;
}
.frt{
  border: 0 px;
  border-bottom: 1 px solid black;
  font-size: 20px;
}
</style>
