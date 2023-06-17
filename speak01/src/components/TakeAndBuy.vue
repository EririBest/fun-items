<template>
  <div style="width: 100%;height: 100vh;">
    <div style="position: absolute;height: 100vh;width: 25%;overflow:auto;border-right: 1px solid gainsboro;animation: slideRight 1s ease forwards;" >
      <div style="background-color: #1f242d;height: 90px;color: aliceblue;font-size: 33px;">
        <h5 id="stitle" @click="goIndex()">⌨Fun&Speak</h5>
        <span style="font-size:24px ;vertical-align:bottom;margin-left: 10px;">消息列表  </span>
      <input @keyup="selectText()" v-model="select" type="text" style="outline: none;padding: 5px;border-radius:5px ;width: 60%;height: 17px;border: 0px;vertical-align:bottom;" placeholder="可根据 交易号\用户名\商品名 搜索">
      </div>
      <div class="apex" v-for="item in takeList.tList" :key="item" @click="flag=true,getGoods(item.gid),roomId=item.room,getBootom()">
        <img :src="'http://localhost:8086/'+item.uimg" style="width: 100px;height: 100px;border-radius: 20%;position: absolute;top: 15px;left: 5px;">
        <div style="position: absolute;left: 115px;top: 15px;font-size: 25px;">用户:{{ item.uname }} </div>
        <div style="position: absolute;left: 115px;top: 45px;width: 250px;;overflow-x: hidden;">商品交易记录凭据: <span style="font-size:5px;"> {{ item.room }}</span> </div>
        <div v-show="item.newtext!==0" style="position: absolute;width: 23px;height: 23px;text-align: center;left: 115px;top: 87px;border-radius: 50%;background-color: red;color: white;">{{ item.newtext }}</div>
        <img :src="'http://localhost:8086/'+item.gimg" class="uimg">
      </div>
    </div>
    <div style="position: absolute;height: 100vh;width: 75%;border-color: #1f242d;left: 25%;overflow: hidden;" >
      <div style="height: 75vh;width: 100%;background-color: rgb(241, 241, 241);border-bottom: 1px solid gainsboro;overflow-y:auto;overflow-x: hidden;" id="content">
        <div style="background-color: #1f242d;height: 90px;font-size: 33px;text-align: center;position: fixed;width: 100%;cursor: pointer;z-index: 9999;overflow: hidden;" v-show="flag===true&&ifhave===true">
          <img :src="'http://localhost:8086/'+imgpath" style="width: 130px;min-height: 90px;border-radius: 5%;position: absolute;left: 0px;"  @click="tobuy(dData.datas.id)">
          <div style="position: absolute;left: 133px;top: 5px;color: white;">{{ gtitle }}</div>
          <div style="position: absolute;left: 133px;top: 40px;font-size: 20px;color: white;">￥{{ gprice }}</div>
          <div style="position: absolute;left: 133px;top: 60px;font-size: 19px;color: darkgray;">买前聊一聊,更省心</div>
          <div class="buy" v-show="ugid!==userdate.id" @click="toBuyGoods()">购买</div>
        </div>
        <div style="background-color: #1f242d;text-align: center;height: 90px;font-size: 33px;text-align: center;position: fixed;width: 99%;border-left: 1% solid w;cursor: pointer;z-index: 9999;overflow: hidden;" v-show="flag===true&&ifhave===false">
          <div style="position: absolute;left: 0;top: 5px;color: white;">装备已售出~</div>
        </div>
        <div style="text-align: center;height: 90px;font-size: 50px;"></div>
        <div style="text-align: center;height: 90px;font-size: 50px;" v-show="flag===false">
          未选择聊天
        </div>
        <!-- :style="{animation:item.username===userdate.name?'slideLeft 0.5s ease forwards':'slideRight 0.9s ease forwards'}" -->
        <div v-for="(item,index) in texts.tList" :key="index" class="textList"
                                  style="animation:zoomIn 0.5s ease forwards;">
            <img :src="'http://localhost:8086/'+item.userimg"
             :class="{seflheaderimg:item.username===userdate.name,headerimg:item.username!==userdate.name}">
            <div class="ctime" v-show="showTime(item.ctime)&&index%4===0||index===0"> {{ item.ctime }}</div>
            <div :class="{selftext:item.username===userdate.name,text:item.username!==userdate.name}" v-show="item.text!==null" style="opacity: 0;position: relative;">{{ item.text }}</div>
            <div :class="{selftext:item.username===userdate.name,text:item.username!==userdate.name}" v-show="item.text!==null">{{ item.text }}</div>
            <div v-show="item.text!==null" style="width: 10px;height: 20px;"></div>
            <div :class="{selftext:item.username===userdate.name,text:item.username!==userdate.name}" style="top:40px;overflow: hidden;" v-show="item.img!==null">
              <img :src="'http://localhost:8086/'+item.img" style="height: 500px;">
            </div>
            <div style="width: 1px;height: 580px;opacity: 0;" v-show="item.img!==null"></div>
        </div>
      </div>
      <div style="position: relative;width: 100%;height: 25vh;" v-show="flag===true">
        <div style="height: 38px;"><button class="sends" @click="fshow=0">文</button> <button class="sends" @click="fshow=1">图</button></div>
        <div v-show="fshow===0">
          <textarea wrap="hard"
                  cols="10"
                  rows="3"
                  :placeholder="tip"
                  maxlength="800"
                  name="text"
                  style="resize: none;width: 98%;white-space: pre-wrap;height: auto;font-size: 30px;color: black;outline: none;border: 0px;margin-left: 2%;margin-top: 0%;"
                  v-model="text"
                  @keyup.enter="sendText()"
                 ></textarea>
          <button class="send" @click="sendText()">发送</button>
        </div>
        <div style="overflow-y: auto;" v-show="fshow===1">
          <img style="height: 200px;margin-left: 5px;" :src="imglook" >
          <button class="send" style="right:13%;">选择图片</button>
          <form id="upload">
                    <input type="file" name="img" class="send" id="file" style="right: 13%;opacity: 0;" @change="upimg()" accept="image/gif, image/jpeg, image/png, image/jpg">
          </form>
          <button class="send" v-show="sendf===true" @click="sendimg(),sendf=false">发送</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import router from '@/router/router'
import { inject, reactive, ref } from 'vue'
const $axios = inject('$axios')
const userdate = ref(JSON.parse(window.sessionStorage.getItem('user')))
const flag = ref(false)
const tip = ref(null)
const fshow = ref(0)
const sendf = ref(false)
const ifhave = ref(true)
const takeList = reactive({
  tList: []
})
const texts = reactive({
  tList: []
})
const gData = reactive({
  datas: {}
})
const dData = reactive({
  datas: {}
})
let fileInput
const imglook = ref('')
const upimg = () => {
  fileInput = document.getElementById('file')
  if (!fileInput.value) {
    return
  }
  const file = fileInput.files[0]
  const size = file.size
  if (size >= 5 * 1024 * 1024) {
    alert('文件大小超出限制')
    return false
  }
  const reader = new FileReader()
  reader.onload = function (e) {
    const data = e.target.result
    imglook.value = data
    sendf.value = true
  }
  // 以DataURL的形式读取文件:
  reader.readAsDataURL(file)
}
const select = ref('')
const selectText = () => {
  $axios({
    url: 'http://localhost:8086/take/selectText',
    method: 'post',
    params: {
      select: select.value,
      userid: userdate.value.id
    }
  }).then((resp) => {
    takeList.tList = resp.data
  })
}
const text = ref('')
const getBootom = () => {
  readText()
  tip.value = ''
  setTimeout(() => {
    const divscll = document.getElementById('content')
    divscll.scrollTop = divscll.scrollHeight + 130
  }, 250)
}
const showTime = (ctime) => {
  const yyMMdd = ctime.split(' ')[0].split('/')
  const hhmm = ctime.split(' ')[1].split(':')
  if (yyMMdd[0] <= new Date().getFullYear() && yyMMdd[1] <= (new Date().getMonth() + 1) && yyMMdd[2] <= new Date().getDate()) {
    if (hhmm[0] < new Date().getHours()) {
      return true
    } else if (yyMMdd[2] <= (new Date().getDate() - 1) && hhmm[0] > new Date().getHours()) {
      return true
    }
  }
  return false
}
const readText = () => {
  $axios({
    url: 'http://localhost:8086/take/read',
    method: 'post',
    params: {
      uid: userdate.value.id,
      room: roomId.value
    }
  })
}
const goIndex = () => {
  router.push('./index')
}
const sendText = () => {
  if (text.value.length < 2) {
    tip.value = '不能发送空的消息'
    text.value = ''
  } else if (text.value.length >= 2) {
    $axios({
      url: 'http://localhost:8086/take/send',
      method: 'post',
      params: {
        text: text.value,
        username: userdate.value.name,
        userimg: userdate.value.imgpath,
        room: roomId.value,
        ctime: new Date().getFullYear() + '/' + (new Date().getMonth() + 1) + '/' + new Date().getDate() + ' ' + new Date().getHours() + ':' + new Date().getMinutes()
      }
    }).then(() => {
      text.value = null
      getBootom()
    })
  }
}
const sendimg = () => {
  const fd = document.getElementById('upload')
  $axios({
    url: 'http://localhost:8086/take/sendimg',
    method: 'post',
    data: fd,
    params: {
      username: userdate.value.name,
      userimg: userdate.value.imgpath,
      room: roomId.value,
      ctime: new Date().getFullYear() + '/' + (new Date().getMonth() + 1) + '/' + new Date().getDate() + ' ' + new Date().getHours() + ':' + new Date().getMinutes()
    }
  }).then(() => {
    imglook.value = ''
    getBootom()
  })
}
const roomId = ref(0)
const imgpath = ref('')
const gtitle = ref('')
const gprice = ref('')
const ugid = ref(0)
const getGoods = (gid) => {
  $axios({
    url: 'http://localhost:8086/ccs/getById',
    method: 'post',
    params: {
      id: gid
    }
  }).then((resp) => {
    gData.datas = resp.data
    dData.datas = gData.datas.goods
    console.log(gData.datas === '')
    if (gData.datas === '') {
      ifhave.value = false
    } else {
      ifhave.value = true
      imgpath.value = dData.datas.imgpath
      gtitle.value = dData.datas.title
      gprice.value = dData.datas.price
      ugid.value = dData.datas.userId
    }
  })
}
const toBuyGoods = () => {
  window.sessionStorage.setItem('price', dData.datas.price)
  window.sessionStorage.setItem('ipone', userdate.value.ipone)
  window.sessionStorage.setItem('name', userdate.value.name)
  window.sessionStorage.setItem('buy', userdate.value.id)
  window.sessionStorage.setItem('out', dData.datas.userId)
  window.sessionStorage.setItem('gid', dData.datas.id)
  window.sessionStorage.setItem('gimg', dData.datas.imgpath)
  window.sessionStorage.setItem('gname', dData.datas.title)
  window.location.href = './static/map.html'
}
setInterval(() => {
  $axios({
    url: 'http://localhost:8086/take/getTextList',
    method: 'post',
    params: {
      room: roomId.value
    }
  }).then((resp) => {
    texts.tList = resp.data
  })
}, 200)
const tobuy = (id) => {
  router.push('./buy/' + id)
}

const getList = () => {
  // selectText()
  setInterval(() => {
    selectText()
  }, 330)
}
getList()
</script>
<style  scoped>
.textList{
  width: 100%;
  min-height: 120px;
  height: auto;
  position: relative;
}
.apex{
  position: relative;
  z-index: 999;
  width:100%;
  cursor: pointer;
  height:130px;
  transition: all .3s;
}
.apex:active{
  background-color: #1f242d;
}

.uimg{
  width: 80px;height: 80px;border-radius: 5%;position: absolute;top: 15px;right: 5px;
}
.sends{
  border: 0px;outline: none;
  margin-left: 10px;
  font-size: 30px;

  color: gainsboro;
  background: none;
  transition: all .3s;
}
.sends:hover{
  font-size: 33px;
  color: black;
}
.headerimg{
  width: 100px;
  height: 100px;
  border-radius: 50%;
  position: absolute;
  top: 10px;
  left: 20px;
}
#stitle{
  margin-left: 10px;
  color: rgb(237, 248, 237);
  cursor: pointer;
  text-shadow: -3px 0px 5px #20e9bd, 0px 0px 2px #20e9bd;
}
.buy{
  position: relative;top: 20px;height: 45px;width: 200px;left: 60%;border: 0px solid black;border-radius: 30px;background-color: white;
}
.buy:hover{
  background-color: cornflowerblue;
}
.send{
  border: 0px;
  width: 160px;
  height: 50px;
  font-size: 33px;
  position: absolute;
  cursor: pointer;
  right: 1%;
  bottom: 10px;
  transition: all .3s;
  border-radius: 2px;
}
.send:hover{
  background-color: cornflowerblue;
}
.seflheaderimg{
  right: 20px;
  width: 100px;
  height: 100px;
  border-radius: 50%;
  position: absolute;
  top: 10px;
}
.text{
  position: absolute;
  left: 125px;
  word-wrap:break-word;
  max-width: 80%;
  top: 35px;
  width: auto;
  height: auto;
  font-size: 33px;
  white-space: pre-wrap;
  padding: 10px;
  border-radius: 10px;
  background-color: white;
}
.selftext{
  position: absolute;
  right: 125px;
  top: 35px;
  white-space: pre-wrap;
  word-wrap:break-word;
  max-width: 80%;
  width: auto;
  height: auto;
  font-size: 33px;
  padding: 10px;
  border-radius: 10px;
  background-color: rgb(60, 252, 124);
}

.ctime{
  padding: 3px;
  position: absolute;
  left: 40%;
  color: white;
  background-color: rgb(159, 160, 160);
  border-radius: 5px;
  font-size: 13px;
}
</style>
