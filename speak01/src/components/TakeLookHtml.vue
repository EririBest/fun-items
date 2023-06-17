<template>
  <div>
    <img src="../assets/wallhaven-g7987q_1280x720.png" style="width: 100%;position:fixed;" id="ts" v-show="userdate.logpath===null">
    <img :src="'http://localhost:8086/'+userdate.logpath" style="width: 100%;position:fixed;" alt="" v-show="userdate.logpath!==null">
    <div style="position: absolute;width: 100%;height: 100vh;overflow-y: auto;overflow-x: hidden;" id="body">
      <div v-show="showCard" style="position: fixed;right: 16px;top: 50px;width: 300px;min-height: 250px;background-color: white;height: auto;border: 2px solid white;animation:zoomIn .9s ease forwards;">
        <div style="width: 300px;height: 140px;overflow: hidden;position: absolute;overflow: hidden;">
          <img :src="'http://localhost:8086/'+rdata.authorimg" v-show="rdata.authorimg!==''" style="width: 300px;">
          <img src="../assets/wallhaven-g7987q_1280x720.png"  v-show="rdata.authorimg===''" style="width: 300px;">
        </div>
        <div style="border-radius: 50%;width: 140px;height: 140px;overflow: hidden;position: absolute;border: 2px solid white;top: 70px;">
          <img :src="'http://localhost:8086/'+rdata.authorheader" style="width: 140px;" >
        </div>
        <div style="position: absolute;top:145px;left: 150px;"><h1>{{ rdata.author }}</h1></div>
        <div style="position: relative;top: 215px;left: 10px;width: 280px;">
          <button id="lookTa"  v-show="userdate.name!==rdata.author" @click="toOther(rdata.author)">看看Ta的主页</button>
          <button id="lookTa" v-show="userdate.name===rdata.author" @click="toPerson()">看看wo的主页</button>
        </div>
      </div>
      <div  id="ty" @click="golook()">
      <div  style="" id="tu">
        <img src="../assets/19-键盘.png" style="min-height: 60px;height: 80px;position: relative;left: 10px;"  alt="">
        <h5 style="position: absolute;left: 25px;top: 75px;">回到社区</h5>
      </div>
      </div>
      <div style="width: 1000px;margin: 0 auto;height: 300px;overflow: hidden;">
        <img :src="'http://localhost:8086/'+firstImg(rdata.imgpath)" style="width: 1000px;">
      </div>
      <div style="width: 1000px;margin: 0 auto;height: auto;background-color: white;min-height: 300px;">
        <div style="border-radius: 50%;width: 200px;height: 200px;overflow: hidden;position: absolute;border: 2px solid white;top: 200px;z-index:9999 ;">
          <img :src="'http://localhost:8086/'+rdata.authorheader" style="width: 200px;cursor: pointer;" @click="toOther(rdata.author)" v-show="userdate.name!==rdata.author">
          <img :src="'http://localhost:8086/'+rdata.authorheader" style="width: 200px;cursor: pointer;" @click="toPerson()" v-show="userdate.name===rdata.author">
        </div>
        <div style="height: 110px;width: 100%;position: relative;overflow: hidden;">
          <div style="font-size: 60px;font-weight: 900;position: relative;left: 200px;">{{ rdata.title }}</div>
          <div style="position: relative;left: 230px;color: gray;font-size: 20px;">by {{ rdata.author }}  发布于 {{ rdata.ctime }}</div>
        </div>
        <br>
        <div style="position: relative;;width: 950px;left: 25px;">
          标签:
          <button class="addType"  v-for="item in getLable(rdata.lable)" :key="item" v-show="item !== ''" @click="toLable(item)">{{ item }}</button>
          <br>
          <br>
          <div style="width: 100%;height: auto;font-size: 23px;word-wrap:break-word;white-space: pre-wrap;">{{ rdata.text }}</div>
          <br>
          <br>
          <img :src="'http://localhost:8086/'+item" v-for="item in getImgs(rdata.imgpath)" :key="item" style="width: 950px;margin: 0 auto;margin-bottom: 5px;animation: zoomIn .5s ease forwards;" v-show="item!==''" alt="">
        </div>
        <br>
        <div style="margin: 0 auto;width: 900px;border-bottom:1px solid gray;position: relative;">
          <img :src="'http://localhost:8086/'+userdate.imgpath" style="width: 70px;height: 70px;border-radius: 50%;vertical-align:bottom"><textarea wrap="hard"
                    cols="10"
                    rows="1"
                    maxlength="210"
                    placeholder="说点什么吧....."
                    name="text"
                    id="text"
                    v-model="text">
        </textarea><button id="send" @click="sendComment()">评论</button>
        <br>
        <br>
        </div>
        <!-- v-for="item in 3" :key="item" -->
        <div style="margin: 0 auto;width: 900px;border-bottom:1px solid gray;position: relative;animation: zoomIn 1s ease forwards;"  v-for="item in comments.lss" :key="item" v-show="showcomment">
          <br>
          <img :src="'http://localhost:8086/'+item.uimg" style="width: 70px;height: 70px;border-radius: 50%;position: absolute;">
          <div style="position: absolute;left: 80px;top: 20px;color: gray;font-size: 14px;">{{ item.uname }} <span v-show="item.uname === rdata.author" style="font-size: 5px;background-color: red;color: white;padding: 2px;border-radius: 4px;">UP主</span>
             <button class="rbtn" @click="delComment(item.id)" v-show="userdate.name===item.uname" >删除</button>
          </div>
          <div style="margin-left: 80px;width: 800px;height:auto;margin-top: 25px;font-size: 30px;word-wrap: break-word;">{{ item.text }}</div>
          <div style="margin-left: 80px;margin-top: 5px;"><button class="rbtn" @click="cid = item.id,rname = item.uname,replyShow = true">回复</button></div>
          <div style="margin-top: 25px;animation: zoomIn 1.5s ease forwards;" v-for="inx in item.sonCommentObjectList" :key="inx">
            <img :src="'http://localhost:8086/'+inx.uimg" style="width: 50px;height: 50px;border-radius: 50%;position: absolute;left: 60px;">
            <div style="margin-left: 130px;margin-top: 20px;color: gray;font-size: 14px;" v-show="inx.uname !== inx.rname">{{ inx.uname }} <span v-show="inx.uname === rdata.author" style="font-size: 5px;background-color: red;color: white;padding: 2px;border-radius: 4px;">UP主</span>
              @{{ inx.rname }} <span v-show="inx.rname === rdata.author" style="font-size: 5px;background-color: red;color: white;padding: 2px;border-radius: 4px;">UP主</span>
               <button class="rbtn" v-show="userdate.name===inx.uname" @click="delSonComment(inx.id)">删除</button> </div>
            <div style="margin-left: 130px;margin-top: 20px;color: gray;font-size: 14px;" v-show="inx.uname === inx.rname">{{ inx.uname }} <span v-show="inx.uname === rdata.author" style="font-size: 5px;background-color: red;color: white;padding: 2px;border-radius: 4px;">UP主</span> 在自言自语 <button class="rbtn" v-show="userdate.name===inx.uname" @click="delSonComment(inx.id)">删除</button> </div>
            <div style="margin-left: 130px;width: 800px;height:auto;margin-top: 5px;font-size: 30px;word-wrap: break-word;">{{inx.text}}</div>
            <div style="margin-left: 130px;margin-top: 5px;"><button class="rbtn" @click="cid = item.id,rname = inx.uname,replyShow = true">回复</button></div>
          </div>
          <br>
          <br>
        </div>
        <div style="width: 900px;position: fixed;bottom: 0px;left: 26%;background-color: white;border-radius: 50px;" v-show="replyShow">
           <div style="position: absolute;color: rgb(141, 141, 141);left: 80px;">@{{ rname }}  <button class="rbtn" @click="replyShow=false">取消回复</button></div>
              <img :src="'http://localhost:8086/'+userdate.imgpath" style="width: 70px;height: 70px;border-radius: 50%;vertical-align:bottom"><textarea wrap="hard"
                        cols="10"
                        rows="1"
                        maxlength="210"
                        placeholder="说点什么吧....."
                        name="text"
                        id="text"
                        v-model="replytext">
            </textarea><button id="send" @click="sendReply(), replyShow=false">回复</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import { onMounted, ref, inject, reactive } from 'vue'
import { useRoute } from 'vue-router'
import router from '@/router/router'
const $axios = inject('$axios')
const userdate = ref(JSON.parse(window.sessionStorage.getItem('user')))
const rdata = ref({})
const route = useRoute()
const text = ref('')
const replytext = ref('')
const delComment = (cid) => {
  $axios({
    url: 'http://localhost:8086/com/del',
    method: 'post',
    params: {
      id: cid
    }
  }).then((resp) => {
    if (resp.data === true) {
      getComments()
    }
  })
}
const delSonComment = (tid) => {
  $axios({
    url: 'http://localhost:8086/com/delSon',
    method: 'post',
    params: {
      id: tid
    }
  }).then((resp) => {
    if (resp.data === true) {
      getComments()
    }
  })
}
const replyShow = ref(false)
const firstImg = (img) => {
  const is = img + ''
  return is.split('&')[0]
}
const toLable = (str) => {
  router.push('../lable/' + str)
}
const getLable = (lb) => {
  const ty = lb + ''
  return ty.split(',')
}
const getImgs = (img) => {
  const is = img + ''
  return is.split('&')
}
const golook = () => {
  router.push('../index')
}
const showCrd = () => {
  setInterval(() => {
    if (card.scrollTop > 200) {
      showCard.value = true
    } else {
      showCard.value = false
    }
    if (card.scrollTop + card.clientHeight === card.scrollHeight) {
      showcomment.value = true
    }
  }, 220)
}

const showcomment = ref(false)
const cid = ref(0)
const rname = ref('')
const sendReply = () => {
  $axios({
    url: 'http://localhost:8086/com/sendReply',
    method: 'post',
    params: {
      cid: cid.value,
      uimg: userdate.value.imgpath,
      uname: userdate.value.name,
      rname: rname.value,
      text: replytext.value
    }
  }).then((resp) => {
    replytext.value = ''
    getComments()
  })
}
const sendComment = () => {
  $axios({
    url: 'http://localhost:8086/com/sendComment',
    method: 'post',
    params: {
      tid: route.params.id,
      uimg: userdate.value.imgpath,
      uname: userdate.value.name,
      text: text.value
    }
  }).then(() => {
    text.value = ''
    getComments()
  })
}
const toOther = (str) => {
  router.push('../order/' + str)
}
const toPerson = () => {
  router.push('../person')
}
let card
const comments = reactive({
  lss: []
})
const getComments = () => {
  $axios({
    url: 'http://localhost:8086/com/getComment',
    method: 'post',
    params: {
      tid: route.params.id
    }
  }).then((resp) => {
    comments.lss = resp.data
  })
}
const showCard = ref(false)
onMounted(() => {
  card = document.getElementById('body')
  showCrd()
  $axios({
    url: 'http://localhost:8086/community/getById',
    method: 'post',
    params: {
      id: route.params.id
    }
  }).then((resp) => {
    rdata.value = resp.data
    console.log(rdata.value)
  })
  $axios({
    url: 'http://localhost:8086/community/addTotal',
    method: 'post',
    params: {
      id: route.params.id
    }
  })
  getComments()
})
</script>
<style scoped>
.addType{
  border: 0px;
  outline: none;
  background-color: aquamarine;
  padding: 6px;
  cursor: pointer;
  margin-left: 15px;
  border-radius:5px ;
}
.addType:hover{
  background-color: rgb(219, 248, 238);
}
#text{
  margin-left: 5px;resize: none;padding: 10px;white-space: pre-wrap;width: 80%;min-height: 20px;font-size: 16px;color: black;outline: none;border: 1px solid cornflowerblue;border-radius:10px ;vertical-align:bottom;
}
#send{
  vertical-align:bottom;padding: 10px;border-radius: 7px;font-size: 16px;border: 0px;margin-left: 10px;background-color: cornflowerblue;transition: all .4s;
}
#send:hover{
  background-color: rgb(173, 201, 253);
}
#lookTa{
  position: relative;
  width: 80%;
  left: 10%;
  border-radius: 5px;
  height: 30px;
  transition: all .3s;
}
#lookTa:hover{
  background-color: cornflowerblue;
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
.rbtn{
  cursor: pointer;border: 0px;background: none;outline: none;color: rgb(194, 194, 194);
}
.rbtn:hover{
  color: black;
}
</style>
