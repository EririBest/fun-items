<template>
  <div style="width: 100%;">
    <div style="overflow: auto;" class="osd">
      <div style="width: auto;height: 200px;background: #000;display:inline;white-space: nowrap;overflow: scroll;" >
      <button style="width: 200px;height: 120px;background-color: aqua;" v-for="item in 18" :key="item">213</button>
    </div>
    </div>
    <div v-for="item in lt.lar" :key="item">
    {{ item.text }}
    </div>
    <input type="text" v-model="lts"/>
    <button @click="addlt()">发送</button>
    <br>
    <input type="text" v-model="lt1"/>
    <input type="text" v-model="lt2"/>
    <button @click="cuttim()">相差</button>
    <button @click="cuttim3()">相差3</button>
    <div @click="cuttim2(index)" class="sdsd" value="3333" v-for="(item,index) in 6" :key="index">相222差3{{ index }}</div>
    {{ lt4 }}
    </div>
    <div v-for="item in rse.lss" :key="item">
     {{ item.id }}
      <div v-for="items in item.arr" :key="items">
      {{ items }}
      </div>
    </div>
    <!-- <ul class="shell">
        <li class="button">
            <span>消息</span>
            <ul>
                <li>回复我的</li>
                <li>收到的赞</li>
                <li>我的消息</li>
            </ul>
        </li>
    </ul> -->
</template>
<script setup>
import { inject, onMounted, reactive, ref } from 'vue'
const $axios = inject('$axios')
const lt = reactive({
  lar: []
})
setInterval(() => {
  $axios({
    url: 'http://localhost:8086/test/get',
    method: 'get'
  }).then((resp) => {
    lt.lar = resp.data
  })
}, 300)
const rse = reactive({
  lss: []
})
// const gettest = () => {
//   $axios({
//     url: 'http://localhost:8086/take/get',
//     method: 'post',
//     params: {
//       userid: 1
//     }
//   }).then((resp) => {
//     rse.lss = resp.data
//   })
// }
const lts = ref('')
const lt1 = ref('')
const lt2 = ref([1, 2, 3, 4])
const lt3 = ref('2023/4/16 15:42')
const lt4 = ref('')
const addlt = () => {
  $axios({
    url: 'http://localhost:8086/test/tekaAdd',
    method: 'post',
    params: {
      text: lts.value
    }
  })
  lts.value = ''
}

const cuttim = () => {
  lt2.value = lt3.value.split(' ')[1].split(':')
  lt1.value = lt3.value.split(' ')[0].split('/')
  console.log(lt1.value[0])
}
const cuttim2 = (obj) => {
  console.log(document.getElementsByClassName('sdsd')[obj].offsetTop)
}
const cuttim3 = () => {
  lts.value = new Date().getFullYear() + '/' + (new Date().getMonth() + 1) + '/' + new Date().getDate() + ' ' + new Date().getHours() + ':' + new Date().getMinutes()
  console.log(lts.value)
}
onMounted(() => {
  $axios({
    url: 'http://localhost:8086/test/get',
    method: 'get'
  }).then((resp) => {
    rse.lss = resp.data
  })
})
</script>
<style scoped>
.osd::-webkit-scrollbar {
    /*滚动条整体样式*/
    width : 1px;  /*高宽分别对应横竖滚动条的尺寸*/
    height: 12px;
}
.osd::-webkit-scrollbar-thumb {
    margin-top: 900px;
    width: 3px;
    /*滚动条里面小方块*/
    border-radius   : 10px;
    background-color: rgba(31, 122, 240, 0.5);
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
    box-shadow   : inset 0 0 5px rgba(0, 0, 0, 0.2);
    background   : #ededed;
    border-radius: 10px;
}
.button{
    background-color: #50536e;
    width: 170px;
    height: 65px;
    text-align: center;
    line-height: 65px;
    transition: .3s;
    cursor: pointer;
}
.button:hover{
    background-color: #d15a39;
}
.button li:hover{
    background-color: #ffb29d;
}
.button ul li{
    height: 0;
    transition: .2s;
    opacity: 0;
    transform: translateY(-65px);
    background-color: #e07a5f;
}
.button:hover li{
    height: 65px;
    opacity: 1;
    transform: translateY(0);
}
</style>
