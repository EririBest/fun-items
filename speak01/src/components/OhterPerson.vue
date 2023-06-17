<template>
  <div id="body">
    <img src="../assets/wallhaven-g7987q_1280x720.png" style="width: 100%;position:fixed;" id="ts" v-show="userdate.logpath===null">
    <img :src="'http://localhost:8086/'+userdate.logpath" style="width: 100%;position:fixed;" alt="" v-show="userdate.logpath!==null">
    <div style="width: 80%;min-height:100vh;position:fixed;z-index: 999;animation: zoomIn 1s ease forwards;left: 10%;background: #000;border-radius: 3%;overflow: hidden;" v-show="flag3">
        <button @click="flag3=!flag3" style="position: absolute;font-size: 33px;right: 3%;background: none;border: 0px;">退出</button>
        <AddGoods/>
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
      </div>
      <div style="display:flex;flex-direction: row;">
        <div style="margin: 0 auto;" class="cg" @click="sw=false">Ta的话题</div><div style="margin: 0 auto;" class="cg" @click="sw=true">Ta的商品</div>
      </div>
      <div class="minbody" v-show="sw">
        <div class="goods" v-for="item in ccList.clist" :key="item" @click="toBuy(item.id)">
          <div class="titles">
            <div style="white-space: nowrap;">{{item.title}}</div>
            <h5 >价格:<span style="color: red;">{{ item.price }}</span></h5>
          </div>
          <img :src="'http://localhost:8086/'+item.imgpath" alt="" class="gimgs">
        </div>
      </div>
      <div class="minbody" v-show="!sw">
        <div class="goods" v-for="item in ttList.clist" :key="item" v-show="item.state!==0" @click="toTake(item.id)">
          <div class="titles">
            <div style="white-space: nowrap;">{{item.title}}</div>
            <h5 >浏览次数:<span>{{ item.total }}</span></h5>
          </div>
          <img :src="'http://localhost:8086/'+getFirst(item.imgpath)" alt="" class="gimgs">
        </div>
      </div>
      <div id="bottomBody">
      </div>
    </div>
  </div>
</template>
<script setup>
import router from '@/router/router'
import { inject, onBeforeMount, reactive, ref } from 'vue'
import { useRoute } from 'vue-router'
const userdate = ref()
const flag3 = ref(false)
const $axios = inject('$axios')
const ccList = reactive({
  clist: []
})
const ttList = reactive({
  clist: []
})
const sw = ref(false)
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
const getFirst = (str) => {
  return str.split('&')[0]
}
const getTakeList = () => {
  $axios({
    method: 'post',
    url: 'http://localhost:8086/community/getMy',
    params: {
      name: userdate.value.name,
      length: 9999
    }
  }).then((resp) => {
    ttList.clist = resp.data
  })
}
const golook = () => {
  router.push('../index')
}
const toTake = (str) => {
  router.push('../looKTake/' + str)
}
const toBuy = (str) => {
  router.push('../buy/' + str)
}
const route = useRoute()
onBeforeMount(() => {
  $axios({
    url: 'http://localhost:8086/user/byname',
    method: 'post',
    params: {
      name: route.params.name
    }
  }).then((resp) => {
    userdate.value = resp.data
    console.log(userdate.value)
    getGoodsList()
    getTakeList()
  })
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
#bottomBody{
  width: 100%;
  height: 300px;
}
#header{
  width: 100%;
  height: 300px;
  position: relative;
}
.minbody{
  width: 100%;
  height: auto;
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
  padding: 10px;
  text-align: center;
  transition: 0.2s;
  font-size: 20px;
  opacity: 0.8;
  border-radius: 10px;
  border: 1px solid black;
}
.cg:hover{
  border: 0px;
  opacity: 1;
  color: cornflowerblue;
  box-shadow: 0 0 20px #ebe6de;
  cursor: pointer;
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
</style>
