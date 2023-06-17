<template>
  <div style="width: 100%;height: 90vh;background: white;overflow: auto;" id="body">
   <div style="width: 70%;height: auto;margin: 0 auto;">
    <el-carousel :interval="3000" arrow="always" height="500px">
      <el-carousel-item v-for="item in headerList.lss" :key="item" >
        <div style="margin: 0 auto;cursor: pointer;" @click="herfTake(item.id)">
            <div style="position: absolute;width: 100px;border-radius: 50%;border: 1px solid black;overflow: hidden;top: 40%;left: 5%;">
              <img :src="'http://localhost:8086/'+ item.authorheader" style="width: 100px;" alt="">
            </div>
            <div style="position: absolute;width: 80px;height: auto;top: 40%;left: 15%;">
            <h1><span style="background-color: black;color: white;padding: 7px;opacity: 0.7;border-radius: 5px;white-space:nowrap;"> &nbsp;{{ item.title }} &nbsp;</span></h1>
            <br>
            <br>
            <h3><span style="background-color: black;color: white;padding: 7px;opacity: 0.7;border-radius: 5px;white-space:nowrap; "> &nbsp;by {{ item.author }} &nbsp;</span></h3>
            </div>
            <img :src="'http://localhost:8086/'+firstImg(item.imgpath)" style="width: 100%;" alt="">
        </div>
      </el-carousel-item>
    </el-carousel>
   </div>
   <br>
   <br>
   <div style="width: 80%;position: relative;left: 10%;">
    <button class="choose" @click="bx(0), state=2, showLength=2, bottomGet(),showd=0">最新</button>
    <button class="choose" @click="bx(1),getHot(),showLength=2,showd=1">热度</button>
    <button class="choose" @click="bx(2), state=3, showLength=2, bottomGet(),showd=0">精华</button>
    <button class="choose" @click="bx(3),getMy(),showLength=2,showd=2">我发布的</button>
    <br>
    <br>
    <hr>
   </div>
   <button style="border: 0px;position: absolute;background: none;cursor: pointer;right: 20px;top:73%;animation: floatImage 2s ease-in-out infinite;;" id="add"
   @click="addTake()">
    <img src="../assets/发布.png" alt="发布" style="width: 50px;">
    <div>发布新话题</div>
   </button>
   <button style="border: 0px;background: none;position: absolute;cursor: pointer;right: 10px;bottom: 5%;animation:zoomIn 0.5s ease forwards;" @click="goTop" v-show="showGoTop">
    <img src="../assets/ai.gif"  style="height: 90px;">
    <div>点我上去啊</div>
  </button>
   <div class="takes"
    v-for="(item,index) in showTip.lss" :key="index" v-show="showd===0">
    <div style="width: 100px;height: 100px;border-radius: 50%;overflow: hidden;position: absolute;left: 5%;top: 5%;" @click="herfTake(item.id)">
      <img :src="'http://localhost:8086/'+item.authorheader" alt="" style="width: 100px;">
    </div>
    <div style="position: absolute;left: 15%;height: 300px;width: 85%;top: 5%;">
      <div style="width: 100%;height: 80px;">
        <h1 @click="herfTake(item.id)"><span >{{ item.title }}</span> <span style="color: gray;font-size: 19px;">by {{ item.author }} · {{ item.ctime }}</span></h1>
        <br>
        <h3>
          话题标签:<button class="cbtn" v-for="ae in getTypes(item.lable)" :key="ae" v-show="ae !== ''" @click="toLable(ae)">{{ ae }}</button>
        </h3>
      </div>
      <br>
      <br>
      <div @click="herfTake(item.id)">
        <img v-for="(inx, exs) in getImgs(item.imgpath)" :key="inx" :src="'http://localhost:8086/'+inx" style="height: 160px;margin-left: 10px;" v-show="inx !== '' && exs < 4">
      </div>
      <br>
      <h5 style="color: rgb(187, 187, 187);font-size: 18px;">浏览次数:{{ item.total }}</h5>
    </div>
  </div>
  <div class="takes"
    v-for="(item,index) in showTp.lss" :key="index" v-show="showd===1&&item.state>1">
    <div style="width: 100px;height: 100px;border-radius: 50%;overflow: hidden;position: absolute;left: 5%;top: 5%;" @click="herfTake(item.id)">
      <img :src="'http://localhost:8086/'+item.authorheader" alt="" style="width: 100px;">
    </div>
    <div style="position: absolute;left: 15%;height: 300px;width: 85%;top: 5%;">
      <div style="width: 100%;height: 80px;">
        <h1 @click="herfTake(item.id)"><span >{{ item.title }}</span> <span style="color: gray;font-size: 19px;">by {{ item.author }} · {{ item.ctime }}</span></h1>
        <br>
        <h3>
          话题标签:<button class="cbtn" v-for="ae in getTypes(item.lable)" :key="ae" v-show="ae !== ''" @click="toLable(ae)">{{ ae }}</button>
        </h3>
      </div>
      <br>
      <br>
      <div @click="herfTake(item.id)">
        <img v-for="(inx, exs) in getImgs(item.imgpath)" :key="inx" :src="'http://localhost:8086/'+inx" style="height: 160px;margin-left: 10px;" v-show="inx !== '' && exs < 4">
      </div>
      <br>
      <h5 style="color: rgb(187, 187, 187);font-size: 18px;">浏览次数:{{ item.total }}</h5>
    </div>
  </div>
  <div class="takes"
    v-for="(item,index) in showMy.lss" :key="index" v-show="showd===2">
    <div style="width: 100px;height: 100px;border-radius: 50%;overflow: hidden;position: absolute;left: 5%;top: 5%;" @click="herfTake(item.id)">
      <img :src="'http://localhost:8086/'+item.authorheader" alt="" style="width: 100px;">
    </div>
    <div style="position: absolute;left: 15%;height: 300px;width: 85%;top: 5%;">
      <div style="width: 100%;height: 80px;">
        <h1 @click="herfTake(item.id)"><span >{{ item.title }}</span> <span style="color: gray;font-size: 19px;">by {{ item.author }} · {{ item.ctime }}</span></h1>
        <br>
        <h3>
          话题标签:<button class="cbtn" v-for="ae in getTypes(item.lable)" :key="ae" v-show="ae !== ''" @click="toLable(ae)">{{ ae }}</button>
        </h3>
      </div>
      <br>
      <br>
      <div @click="herfTake(item.id)">
        <img v-for="(inx, exs) in getImgs(item.imgpath)" :key="inx" :src="'http://localhost:8086/'+inx" style="height: 160px;margin-left: 10px;" v-show="inx !== '' && exs < 4">
      </div>
      <br>
      <h5 style="color: rgb(187, 187, 187);font-size: 18px;">浏览次数:{{ item.total }} <button class="cbtn" v-show="item.state!==1" style="background-color: palevioletred;padding: 2px;color: white;" @click="delC(item.id)">删除该话题</button></h5>
    </div>
    <div style="width: 100%;height: 350px;background: #000;opacity: 0.8;text-align: center;position: absolute;color: white;font-size: 50px;" v-show="item.state===1">审核中...</div>
    <div style="width: 100%;height: 350px;background: #000;opacity: 0.8;text-align: center;position: absolute;color: white;font-size: 50px;" v-show="item.state===0">违规话题,被打回... <h3> <button class="cbtn" v-show="item.state!==1" style="background-color: palevioletred;padding: 2px;color: white;" @click="delC(item.id)">删除该话题</button></h3>
    </div>
  </div>
  <div style="padding: 8px;color: grey;text-align: center;">没有更多的话题了:(</div>
  </div>
</template>
<script setup>
import router from '@/router/router'
import { onMounted, reactive, ref, inject, watch } from 'vue'
let btns
const userdate = ref(JSON.parse(window.sessionStorage.getItem('user')))
const state = ref(2)
let Bscroll
const showLength = ref(2)
const getImgs = (imgs) => {
  return imgs.split('&')
}
const $axios = inject('$axios')
const goTop = () => {
  const top = setInterval(() => {
    Bscroll.scrollTop -= 20
    if (Bscroll.scrollTop <= 0) {
      clearInterval(top)
    }
  }, 1)
}
const delC = (cid) => {
  $axios({
    url: 'http://localhost:8086/community/del',
    method: 'post',
    params: {
      id: cid
    }
  }).then((resp) => {
    if (resp.data) {
      getMy()
    }
  })
}
const getMy = () => {
  $axios({
    url: 'http://localhost:8086/community/getMy',
    method: 'post',
    params: {
      name: userdate.value.name,
      length: showLength.value
    }
  }).then((resp) => {
    showMy.lss = resp.data
  })
}
const showd = ref(0)
const getHot = () => {
  $axios({
    url: 'http://localhost:8086/community/getHot',
    method: 'post',
    params: {
      length: showLength.value
    }
  }).then((resp) => {
    showTp.lss = resp.data
  })
}
// const show = (index) => {
//   const divs = document.getElementsByClassName('ts')
//   console.log(divs[index])
//   return false
// }
const toLable = (str) => {
  router.push('./lable/' + str)
}
const addTake = () => {
  router.push('./addTake')
}
const firstImg = (val) => {
  return val.split('&')[0]
}
const showGoTop = ref(false)
const tes = setInterval(() => {
  if (Bscroll.scrollTop > 550) {
    showGoTop.value = true
  } else {
    showGoTop.value = false
  }
  if (Bscroll.scrollTop + Bscroll.clientHeight === Bscroll.scrollHeight) {
    showLength.value += 2
  }
}, 100)
watch(showLength, (newValue, oldValue) => {
  bottomGet()
  getHot()
  getMy()
  console.log('值发生了变更', newValue, oldValue)
})
const headerList = reactive({
  lss: []
})
const showTip = reactive({
  lss: []
})
const showTp = reactive({
  lss: []
})
const showMy = reactive({
  lss: []
})
const bx = (val) => {
  for (let i = 0; i < 4; i++) {
    btns[i].style.borderBottom = ''
  }
  btns[val].style.borderBottom = '4px solid cornflowerblue'
}
const bottomGet = () => {
  $axios({
    url: 'http://localhost:8086/community/userGet',
    method: 'post',
    params: {
      state: state.value,
      length: showLength.value
    }
  }).then((resp) => {
    showTip.lss = resp.data
  })
}
const getTypes = (lb) => {
  return lb.split(',')
}
const herfTake = (id) => {
  clearInterval(tes)
  router.push('./lookTake/' + id)
}
onMounted(() => {
  btns = document.getElementsByClassName('choose')
  btns[0].style.borderBottom = '4px solid cornflowerblue'
  Bscroll = document.getElementById('body')
  $axios({
    url: 'http://localhost:8086/community/userGet',
    method: 'post',
    params: {
      state: 3,
      length: 6
    }
  }).then((resp) => {
    headerList.lss = resp.data
  })
  bottomGet()
})
</script>
<style scoped>
.choose{
  border: 0px;
  font-size: 33px;
  margin-left: 15%;
  font-weight: 900;
  background: none;
  padding: 6px;
}
.choose:active{
  color: cornflowerblue;
}
.takes{
  width: 70%;
  height: 350px;
  background-color: white;
  margin: 0 auto;
  transition: all .4s;
  border-bottom: 1px solid gainsboro;
  animation:zoomIn 1.5s ease forwards;
  position: relative;
}
.takes:hover{
  cursor: pointer;
  width: 65%;
  margin-top: 40px;
  height: 390px;
}
.cbtn{
  padding: 6px;
  background-color: rgb(50, 250, 233);
  border-radius: 3px;
  margin-left: 5px;
  border: 0px;
  transition: all .3s;
}
.cbtn:hover{
  background-color: rgb(176, 255, 229);
}
</style>
