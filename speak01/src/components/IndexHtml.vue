<template>
  <div style="width: 100%;height: 100vh;overflow: hidden;">
    <div style="width: 100%;height: 10vh;background-color:white;opacity: 0.9;border-bottom: 1px solid gainsboro;">
      <span style="font-weight: 200;position: relative;top: 15%;left: 2%;width:200px;" class="neon-text">//Fun&Speak<span class="keyb">@⌨\\<span v-show="rigthvalue==0" style="font-size: 27px;">交易区</span><span v-show="rigthvalue===50" style="font-size: 27px;">话题区</span></span></span>
    </div>
    <div style="border: 3px solid cornflowerblue;;top:30px;left:30%;position: absolute;border-radius:5px 0px 5px 5px;width: 600px;height: auto;margin: 0 auto;background:white;z-index: 999;">
          <input type="text"
          @keyup="longSelect()"
          @keypress.enter="getSelect(),selectUser()"
          :placeholder="selectWord"
          v-model="useSelect"
          style="width: 570px;outline: none;height: 40px;font-size: 30px;background-color:
          white;border: 0px;">
          <hr v-show="selectList.sList.length!=0 && flag3 === true" style="width: 580px;margin-left:10px ;border: 0px;height: 1px;background-color: grey;">
          <div v-for="item in selectList.sList" :key="item" class="rten" @click="getSelect2(item),selectUser()" v-show="flag3===true">
            {{ item }}
          </div>
    </div><button @click="getSelect(),selectUser()"
    style="position: absolute;left: 61%;height: 46px;top: 30px;width: 160px;border: 0px;background-color: cornflowerblue;border-radius:0px 5px 5px 0px;font-size: 27px;"
    >搜索</button>
    <div style="width: 180px;height: 300px;position: absolute;z-index: 999;right: 10px;top: 5px;" id="usert">
      <ul class="shell">
        <li class="button">
          <img :src="'http://localhost:8086/'+userdata.imgpath" class="userimg">
            <ul>
                <li @click="toTTk" style="position: relative;">
                  消息列表
                  <span v-show="total>0&&total<10" style="color: white;background-color: red;border-radius: 50%;padding: 1px;">&nbsp;{{total}}&nbsp;</span>
                  <span v-show="total>10&&total<100" style="color: white;width: 20px;height: 20px;;background-color: red;border-radius: 50%;padding: 1px;">{{ total }}</span>
                  <span v-show="total>=100" style="color: white;background-color: red;border-radius: 10px;padding: 3px;">99+</span>
                </li>
                <li @click="toPerson">我的主页</li>
                <li @click="toLogin">退出登录</li>
            </ul>
        </li>
    </ul>
    </div>
    <div style="width: 100%;height: 90vh;text-align: center;" v-show="htmlShow === false">
      <img src="../assets/ai.gif" alt="">
      <h3>加载数据ing......请稍等哈</h3>
    </div>
    <div style="width: 100%;height: 90vh;overflow: hidden;" v-show="flag2===true&&htmlShow === true">
      <div style="width: 200%;height: 90vh;z-index: 1;">
        <div style="width: 50%;background: white;display: inline-block;position: relative;vertical-align:bottom;overflow-x: auto;" id="buy">
          <button @click="toTake()"
          id="rbtn"
          style="position: fixed;right: 0px;top: 50%;background: none;border: 0px;" v-show="rigthvalue==0"
          ><img src="../assets/右箭头.png"></button>
          <BuyHtml/>
        </div>
        <div style="width: 50%;display: inline-block;position:relative;background-color: blueviolet;vertical-align:bottom;" id="take">
        <button @click="toBuy()"
        id="lbtn"
        style="position: fixed;left: 0px;top: 50%;background: none;border: 0px;" v-show="rigthvalue==50"
        ><img src="../assets/左箭头.png"></button>
        <TakeHtml/>
        </div>
      </div>
    </div>
    <div style="width: 100%;height: 90vh;background-color: rgba(241,241,241);overflow: auto;" v-show="flag2 === false">
      <br>
      <button class="rts" style="width: 160px;margin-left: 10px;"  @click="flag2=true,useSelect='',what()"
     >返回主页</button>
    <button class="rts" @click="flag=0"
     :style="{borderBottom:flag === 0?'2px solid cornflowerblue':'2px solid rgba(241,241,241)','cornflowerblue':'black'}"
     >交易 <span style="font-size: 16px;color: grey;">{{ glist.kls.length }}</span></button>
    <!-- <button class="rts" @click="flag=1" :style="{borderBottom:flag === 1?'2px solid cornflowerblue':'2px solid rgba(241,241,241)',
     color:flag === 1?'cornflowerblue':'black'}">话题</button> -->
    <button class="rts" @click="flag=2" :style="{borderBottom:flag === 2?'2px solid cornflowerblue':'2px solid rgba(241,241,241)',
      color:flag === 2?'cornflowerblue':'black'}">用户 <span style="font-size: 16px;color: grey;">{{ users.uList.length }}</span></button>
      <br>
      <br>
      <hr>
      <div class="goods" v-for="item in glist.kls" :key="item" @click="toContext(item.id)"  v-show="flag === 0">
        <div class="idiv"><img :src="'http://localhost:8086/'+item.imgpath" alt="" class="gimgs"></div>
        <div class="titles">
            {{item.title}}
            <h5 >价格:<span style="color: red;">{{item.price}}</span></h5>
          </div>
      </div>
      <div class="goods" v-show="flag === 2" v-for="item in users.uList" :key="item" @click="tops(item.name)">
        <div class="idiv"><img :src="'http://localhost:8086/'+item.imgpath" alt="" class="gimgs"></div>
        <div class="titles">
            {{item.name}}
          </div>
      </div>
      <br>
      <br>
      <br>
    </div>
  </div>
</template>
<script setup>
import router from '@/router/router'
import { inject, onMounted, ref, reactive } from 'vue'
const $axios = inject('$axios')
const flag = ref(0)
const flag2 = ref(true)
const flag3 = ref(true)
const total = ref(0)
const htmlShow = ref(false)
const userdata = ref(JSON.parse(window.sessionStorage.getItem('user')))
const rigthvalue = ref(window.sessionStorage.getItem('look'))
const getTpyeWord = () => {
  $axios({
    url: 'http://localhost:8086/ccs/getKey',
    method: 'get'
  }).then((resp) => {
    tlist.kls = resp.data
    what()
  })
}
const tops = (str) => {
  if (str !== userdata.value.name) {
    router.push('./order/' + str)
  } else {
    router.push('./person')
  }
}
const toPerson = () => {
  router.push('./person')
}
const what = () => {
  selectWord.value = tlist.kls[parseInt(Math.random() * tlist.kls.length)].type
}
const getSelect = () => {
  flag2.value = false
  flag3.value = false
  flag.value = 0
  if (useSelect.value === '') {
    useSelect.value = selectWord.value
  }
  $axios({
    url: 'http://localhost:8086/ccs/select',
    method: 'post',
    params: {
      keyword: useSelect.value
    }
  }).then((resp) => {
    glist.kls = resp.data
    what()
  })
}
const getTotal = () => {
  setInterval(() => {
    $axios({
      url: 'http://localhost:8086/take/getTotal',
      method: 'post',
      params: {
        userid: userdata.value.id
      }
    }).then((resp) => {
      total.value = resp.data
    })
  }, 300)
}

const getSelect2 = (keyword) => {
  useSelect.value = keyword
  getSelect()
}
const selectWord = ref('')
const useSelect = ref('')
const glist = reactive({
  kls: []
})
const tlist = reactive({
  kls: []
})
// const toPerson = (id) => {
// }
const toContext = (id) => {
  router.push('./buy/' + id)
}
const toTTk = () => {
  router.push('./ttk')
}
const toTake = () => {
  rigthvalue.value = 0
  window.sessionStorage.setItem('look', 50)
  const buyt = document.getElementById('buy').style
  const take = document.getElementById('take').style
  const sett = setInterval(() => {
    rigthvalue.value += 0.5
    buyt.right = rigthvalue.value + '%'
    take.right = rigthvalue.value + '%'
    if (rigthvalue.value === 50) {
      clearInterval(sett)
    }
  }, 10)
}
const rtn = ref(0)
const ltn = ref(0)
getTotal()
onMounted(() => {
  if (rigthvalue.value !== 0) {
    const buyt = document.getElementById('buy').style
    const take = document.getElementById('take').style
    buyt.right = window.sessionStorage.getItem('look') + '%'
    take.right = window.sessionStorage.getItem('look') + '%'
  }
  const flag = ref(true)
  const left = document.getElementById('lbtn').style
  const rigt = document.getElementById('rbtn').style
  setInterval(() => {
    if (ltn.value <= 30 && flag.value) {
      ltn.value += 1
      rtn.value += 1
    } else {
      ltn.value -= 1
      rtn.value -= 1
      flag.value = false
      if (ltn.value === 0) {
        flag.value = true
      }
    }
    left.left = ltn.value + 'px'
    rigt.right = rtn.value + 'px'
  }, 30)
})
const toBuy = () => {
  rigthvalue.value = 50
  window.sessionStorage.setItem('look', 0)
  const buyt = document.getElementById('buy').style
  const take = document.getElementById('take').style
  const sett = setInterval(() => {
    rigthvalue.value -= 0.5
    buyt.right = rigthvalue.value + '%'
    take.right = rigthvalue.value + '%'
    if (rigthvalue.value === 0) {
      clearInterval(sett)
    }
  }, 10)
}
// const toPerson = () => {
//   router.push('./person')
// }
const toLogin = () => {
  window.sessionStorage.clear()
  router.push('./')
}
getTpyeWord()
const users = reactive({
  uList: []
})
const selectList = reactive({
  sList: []
})
const longSelect = () => {
  flag3.value = true
  $axios({
    url: 'http://localhost:8086/ccs/types',
    method: 'post',
    params: {
      name: useSelect.value
    }
  }).then((resp) => {
    selectList.sList = resp.data
  })
}
const selectUser = () => {
  $axios({
    url: 'http://localhost:8086/user/select',
    method: 'post',
    params: {
      name: useSelect.value
    }
  }).then((resp) => {
    users.uList = resp.data
  })
}
onMounted(() => {
  setInterval(() => {
    htmlShow.value = true
  }, 100)
})
</script>
<style  scoped>
.rts{
  width: 100px;
  height: 60px;
  font-size: 30px;
  border: 0px;
  border-bottom:2px solid rgba(241,241,241);;
  margin-left: 10%;
}
.rts:hover{
  color: cornflowerblue;
}
.rts:active{
  border-bottom: 2px solid cornflowerblue;
}
.neon-text {
      font-family: 'Monoton', cursive;
      font-size: 50px;
      color: #e91e63;
      /* animation: neon 3s infinite; */
      text-shadow: -3px 0px 5px #e91e63, 0px 0px 2px #e91e63;
}
.keyb{
  font-family: 'Monoton', cursive;
      font-size: 50px;
      color: #7395f1;
      /* animation: neon 3s infinite; */
      text-shadow: -3px 0px 5px #7395f1, 0px 0px 2px #7395f1;
}
#buy::-webkit-scrollbar {
    /*滚动条整体样式*/
    width : 10px;  /*高宽分别对应横竖滚动条的尺寸*/
    height: 10px;
}
#buy::-webkit-scrollbar-thumb {
    /*滚动条里面小方块*/
    background-color:  palegoldenrod;
    /* background-image: -webkit-linear-gradient(
            45deg,
            rgba(255, 255, 255, 0.2) 25%,
            transparent 25%,
            transparent 50%,
            rgba(255, 255, 255, 0.2) 50%,
            rgba(255, 255, 255, 0.2) 75%,
            transparent 75%,
            transparent
    ); */
}
.button{

    width: 170px;
    height: 65px;
    text-align: center;
    line-height: 65px;
    transition: .3s;
    cursor: pointer;
}
.button:hover .userimg{
    width: 90px;
    height: 90px;
    border-radius: 10%;
}
.userimg{
  width: 80px;height: 80px;position: relative;border-radius: 50%;transition: .3s;
}
.button li:hover{
    border-radius: 10px;
    background-color: rgb(162, 190, 243);
}
.button ul{
  background-color: cornflowerblue;
  border-radius: 10px;
}
.button ul li{
    height: 0;
    transition: .2s;
    opacity: 0;
    transform: translateY(-65px);
}
.button:hover li{
    height: 65px;
    opacity: 1;
    transform: translateY(0);
}
#buy::-webkit-scrollbar-track {
    /*滚动条里面轨道*/
    background-color: cornflowerblue;
}
.rten{
  font-size: 30px;
}
.rten:hover{
  background-color: cornflowerblue;
}
.idiv{
  width: 400px;
  height: 400px;
  transition: all .4s;
  border-radius: 50%;
  overflow: hidden;
}
.gimgs{
  min-width: 400px;
  height: 400px;
  transition: all .4s;
}
.goods{
  position: relative;
  width: 400px;
  height: auto;
  display: inline-block;
  background-color: white;
  margin-top: 5%;
  margin-left: 2%;
  transition: all 1s;
  overflow: hidden;
  vertical-align:bottom;
}
.goods:hover .gimgs{
  min-width: 420px;
  height: 420px;
}
.goods:hover .idiv{
  border-radius: 0%;
}
.titles{
  width: 398px;
  height: 100px;
  background-color: white;
  /* border-bottom: 2px solid black; */
  text-align: center;
  font-size: 41px;
  transition: all 1s;
  position: relative;
}
</style>
