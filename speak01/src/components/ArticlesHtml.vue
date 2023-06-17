<template>
  <div style="width: 100%;height:90vh;overflow: auto;" id="bbody">
    <br>
    <div style="overflow: auto;" class="osd">
      <div style="width: auto;height: 200px;display:inline;white-space: nowrap;overflow: visible;" >
        <span style="margin-left: 10px;font-size: 30px;">分类:</span>
        <div style="  margin-left: 10px;display: inline-block;" v-for="item in tlist.kls" :key="item">
          <button class="types" style="background-color: rgb(71, 102, 243);color: white;" v-show="keyType == item.type" @click="getGoods(),keyType = ''">{{ keyType }}</button>
          <button class="types"  @click="getType(item.type)" v-show="item.type != keyType">{{ item.type }}</button>
        </div>
        <div>
          <br>
        </div>
      </div>
    </div>
    <div>
      <div class="goods" v-for="item in glist.kls" :key="item" @click="toContext(item.id)">
        <div class="idiv"><img :src="'http://localhost:8086/'+item.imgpath" alt="" class="gimgs"></div>
        <div class="titles">
            <div style="white-space: nowrap;">{{item.title}}</div>
            <div style="position: absolute;right: 0px;padding: 6px;background-color: aquamarine;font-size: 25px;border-radius: 10px;">{{ item.type }}</div>
            <h5 style="color: turquoise;">￥{{item.price}}</h5>
          </div>
      </div>
    </div>
    <br>
    <br>
    <br>
  </div>
</template>
<script setup>
import router from '@/router/router'
import { inject, reactive, ref } from 'vue'
const $axios = inject('$axios')
const glist = reactive({
  kls: []
})
const tlist = reactive({
  kls: []
})
const getTpyeWord = () => {
  $axios({
    url: 'http://localhost:8086/ccs/getKey',
    method: 'get'
  }).then((resp) => {
    tlist.kls = resp.data
  })
}
const getGoods = () => {
  $axios({
    url: 'http://localhost:8086/ccs/get',
    method: 'get'
  }).then((resp) => {
    glist.kls = resp.data
  })
}
getGoods()
getTpyeWord()
const toContext = (id) => {
  router.push('./buy/' + id)
}
const keyType = ref('')
const getType = (typeKey) => {
  keyType.value = typeKey
  $axios({
    url: 'http://localhost:8086/ccs/getByType',
    method: 'post',
    params: {
      keyword: typeKey
    }
  }).then((resp) => {
    glist.kls = resp.data
  })
}
</script>
<style  scoped>
#bbody{
  background-color: rgb(241, 241, 241);
}
.osd::-webkit-scrollbar {
    /*滚动条整体样式*/
    width : 1%;  /*高宽分别对应横竖滚动条的尺寸*/
    height: 6px;
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
  font-size: 41px;
  transition: all 1s;
  position: relative;
}
.osd::-webkit-scrollbar-thumb {
    /*滚动条里面小方块*/
    border-radius   : 10px;
    background-color: rgba(0, 0, 0, 0.5);
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
.osd::-webkit-scrollbar-track {
    /*滚动条里面轨道*/
    background   : none;
    border-radius: 10px;
}

.types{
  width: 90px;
  height: 40px;
  color: black;
  background-color:white;
  border: 1px solid rgb(71, 102, 243);
}
.types:hover{
color: white;
background-color: rgb(71, 102, 243);
}

</style>
