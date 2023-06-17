<template>
      <div  id="ty" @click="golook()">
      <div  style="" id="tu">
        <img src="../assets/19-键盘.png" style="min-height: 60px;height: 80px;position: relative;left: 10px;"  alt="">
        <h5 style="position: absolute;left: 25px;top: 75px;">回到社区</h5>
      </div>
    </div>
  <div style="width: 100%;min-height: 100vh;height: auto;">

    <img src="../assets/wallhaven-g7987q_1280x720.png" style="width: 100%;position:fixed;" id="ts" v-show="udate.logpath===null">
    <img :src="'http://localhost:8086/'+udate.logpath" style="width: 100%;position:fixed;" alt="" v-show="udate.logpath!==null">
    <div style="position: relative;width: 80%;min-height: 100vh;left:10%;opacity: 0.98;background:white;">
      <h1 style="margin: 0 auto;text-align: center;font-size: 43px;">{{ dData.datas.title }}</h1>
      <div style="width: 100%;height: 200px;position: relative;">
        <span style="position: absolute;left: 180px;font-size: 43px;top: 10%;">{{ userdata.data.name }}</span>
        <span style="position: absolute;left: 180px;font-size: 23px;top: 40%;">{{ userdata.data.text }}</span>
        <button class="bs" style="width: 270px;top: 40%;right: 10px;position: absolute;" @click="toOrder(userdata.data.name)" v-show="userdata.data.name!==udate.name">进入ta的主页</button>
        <button class="bs" style="width: 270px;top: 40%;right: 10px;position: absolute;" @click="toPerson()" v-show="userdata.data.name===udate.name">进入wo的主页</button>
        <img :src="'http://localhost:8086/'+userdata.data.imgpath"
         style="width: 150px;height: 150px;border-radius: 50%;border: 2px solid black;position: relative; top: 10px;left: 10px;">
      </div>
      <hr>
      <br>
      <div style="width: 80%;font-size: 40px;margin: 0 auto;">类型:{{ dData.datas.type }}</div>
      <br>
      <div style="width: 80%;font-size: 30px;margin: 0 auto;">
        {{ gData.datas.text }}
      </div>
      <br>
      <img v-for="item in gData.datas.imgs" :key="item" :src="'http://localhost:8086/'+item" style="width: 100%;min-height: 100%;" >
      <div>
        <hr>
      </div>
      <br>
      <button class="bs"
        @click="toTake()"
         style="width: 160PX;border-radius:30px;margin-left: 40px;" v-show="udate.id != userdata.data.id">
          我想要
        </button>
        <button class="bs" style="width: 160px;margin-left: 100px;" v-show="udate.id === userdata.data.id" @click="deleteG()">
          删除
        </button>
      <div>
        <!-- 操作按钮 我想要 管理 删除 -->
      </div>
      <br>
    </div>
  </div>
</template>
<script setup>
import { inject, onMounted, reactive, ref } from 'vue'
import { useRoute } from 'vue-router'
import router from '@/router/router'
const golook = () => {
  router.push('../index')
}
const $axios = inject('$axios')
const route = useRoute()
const udate = ref(JSON.parse(window.sessionStorage.getItem('user')))
const gData = reactive({
  datas: {}
})
const dData = reactive({
  datas: {}
})
const toOrder = (str) => {
  router.push('../order/' + str)
}
const toPerson = () => {
  router.push('../person')
}
const gid = route.params.id
const userdata = reactive({
  data: {}
})
const deleteG = () => {
  $axios({
    url: 'http://localhost:8086/ccs/del',
    method: 'post',
    params: {
      id: route.params.id
    }
  }).then(() => {
    golook()
  })
}
const toTake = () => {
  $axios({
    url: 'http://localhost:8086/take/inspect',
    method: 'post',
    params: {
      userid: udate.value.id,
      gid: gid
    }
  }).then((resp) => {
    if (resp.data === true) {
      $axios({
        url: 'http://localhost:8086/take/creat',
        method: 'post',
        params: {
          userid: udate.value.id,
          uid: userdata.data.id,
          username: udate.value.name,
          uname: userdata.data.name,
          userimg: udate.value.imgpath,
          uimg: userdata.data.imgpath,
          gid: gid,
          gimg: gData.datas.imgs[0]
        }
      }).then(() => {
        console.log('OK')
        router.push('../ttk')
      })
    } else {
      router.push('../ttk')
      console.log('no')
    }
  })
}
onMounted(() => {
  $axios({
    url: 'http://localhost:8086/ccs/getById',
    method: 'post',
    params: {
      id: gid
    }
  }).then((resp) => {
    gData.datas = resp.data
    dData.datas = gData.datas.goods
    $axios({
      url: 'http://localhost:8086/user/getById',
      method: 'post',
      params: {
        id: dData.datas.userId
      }
    }).then((resp) => {
      userdata.data = resp.data
    })
  })
})
</script>

<style scoped>
.bs{
  border: 0px;font-size: 30px;width: auto;height: 60px;border-radius: 5px;background-color: cornflowerblue;
}
.bs:hover{
  background-color: rgb(144, 179, 245);
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
  z-index: 999;
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
