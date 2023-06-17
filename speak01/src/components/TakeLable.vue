<template>
  <div>
    <img src="../assets/wallhaven-g7987q_1280x720.png" style="width: 100%;position:fixed;" id="ts" v-show="userdate.logpath===null">
    <img :src="'http://localhost:8086/'+userdate.logpath" style="width: 100%;position:fixed;" alt="" v-show="userdate.logpath!==null">
    <div style="position: absolute;width: 100%;height: 100vh;overflow-y: auto;overflow-x: hidden;" id="body">
      <div  id="ty" @click="golook()">
      <div  style="" id="tu">
        <img src="../assets/19-键盘.png" style="min-height: 60px;height: 80px;position: relative;left: 10px;"  alt="">
        <h5 style="position: absolute;left: 25px;top: 75px;">回到社区</h5>
      </div>
    </div>
      <div style="width: 1200px;margin: 0 auto;min-height: 100vh;overflow: hidden;background-color: white;">
        <div style="width: 1200px;height: 300px;overflow: hidden;">
          <img :src="'http://localhost:8086/'+imgpath" alt="" style="width: 1200px;">
        </div>
        <br>
        <div style="margin-left: 20px;font-size: 20px;border-bottom: 1px solid gainsboro;width: 1160px;"><span class="cbtn">·{{ keyword }}</span>
          <br>
          <br>
          <div>共计 {{ tass.lss.length }} 条相关话题</div>
          <br>
        </div>
        <!-- <div v-for="item in tass.lss" :key="item">
        {{ item }}</div> -->
            <div class="takes"
              v-for="(item,index) in tass.lss" :key="index"  @click="herfTake(item.id)">
                <div style="width: 100px;height: 100px;border-radius: 50%;overflow: hidden;position: absolute;left: 5%;top: 5%;">
                  <img :src="'http://localhost:8086/'+item.authorheader" alt="" style="width: 100px;">
                </div>
                <div style="position: absolute;left: 15%;height: 300px;width: 85%;top: 5%;">
                  <div style="width: 100%;height: 80px;">
                    <h1><span >{{ item.title }}</span> <span style="color: gray;font-size: 19px;">by {{ item.author }} · {{ item.ctime }}</span></h1>
                    <br>
                    <h3>
                      话题标签:<button class="cbtn" v-for="ae in getTypes(item.lable)" :key="ae" v-show="ae !== ''">{{ ae }}</button>
                    </h3>
                  </div>
                  <br>
                  <br>
                  <div>
                    <img v-for="(inx, exs) in getImgs(item.imgpath)" :key="inx" :src="'http://localhost:8086/'+inx" style="height: 160px;margin-left: 10px;" v-show="inx !== '' && exs < 4">
                  </div>
                  <br>
                  <h5 style="color: rgb(187, 187, 187);font-size: 18px;">浏览次数:{{ item.total }}</h5>
              </div>
            </div>
      </div>
    </div>
  </div>
</template>
<script setup>
// import router from '@/router/router'
import router from '@/router/router'
import { inject, reactive, ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
const userdate = ref(JSON.parse(window.sessionStorage.getItem('user')))
const $axios = inject('$axios')
const getTypes = (lb) => {
  return lb.split(',')
}
const getImgs = (imgs) => {
  return imgs.split('&')
}
const tass = reactive({
  lss: []
})
const route = useRoute()
const herfTake = (id) => {
  router.push('../lookTake/' + id)
}
const golook = () => {
  router.push('../index')
}
let imgpath
const keyword = route.params.word
onMounted(() => {
  console.log(route.params.word)
  $axios({
    url: 'http://localhost:8086/community/lable',
    method: 'post',
    params: {
      keyword: route.params.word + ','
    }
  }).then((resp) => {
    tass.lss = resp.data
    imgpath = getImgs(tass.lss[0].imgpath)[0]
  })
})
</script>
<style scoped>
#break{
  border: 0px;
  border-radius: 10px;
  padding: 7px;
  background-color: rgb(192, 192, 192);
  transition: all .3s;
  font-size: 30px;
  margin-left: 10px;
}
#break:hover{
  background-color: cornflowerblue;
}
.takes{
  width: 98%;
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
