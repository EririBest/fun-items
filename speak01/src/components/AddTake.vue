<template>
  <div >
    <img src="../assets/wallhaven-g7987q_1280x720.png" style="width: 100%;position:fixed;" id="ts" v-show="userdate.logpath===null">
    <img :src="'http://localhost:8086/'+userdate.logpath" style="width: 100%;position:fixed;" alt="" v-show="userdate.logpath!==null">
    <div style="position: absolute;width: 100%;height: 100vh;overflow: auto;" v-show="!htmlTest">
      <div style="position: relative;margin: 0 auto;width:1000px;background-color: white;height: 100vh;">
        <div style="margin: 0 auto;width: 100%;height: 100vh;">
      <div class="text">
        <br>
        <br>
        <input type="text" name="title"
        style="border: 0px;outline: none;font-size: 33px;width: 90%;position: relative;left: 5%;" v-model="title" maxlength="17"
        placeholder="标题(建议10个字以内)">
        <br>
        <br>
        <div style="position: relative;width: 90%;left: 5%;">
          <button class="addType" @click="addLabelShow=true">#添加标签#</button>
          <button class="addType" v-for="item in label.ls" :key="item">{{ item }}</button>
          <input type="text" id="epc" placeholder="#添加话题标签" v-model="labelText" @keyup.enter="addLabel()" v-show="addLabelShow===true">
        </div>
        <br>
        <textarea wrap="hard"
                    cols="10"
                    rows="3"
                    maxlength="800"
                    placeholder="说点什么吧....."
                    name="text"
                    style="resize: none;white-space: pre-wrap;width: 90%;min-height: 40px;font-size: 26px;color: black;outline: none;border: 0px;position: relative;left: 5%;"
                    v-model="text">
        </textarea>
      </div>
      <div v-for="item in lengths" :key="item" class="is" v-show="index+1>item">
        <img class="image-preview"  >
        <div class="del" @click="del(item-1)">
          <img src="../assets/删除.png" style="position: absolute;top:50px;left:140px;" alt="">
        </div>
      </div>
      <div id="fr" style="height: auto;background-color: white;"  v-show="lengths != index">
        <form action="" id="test">
          <input type="text" name="author" v-model="userdate.name" hidden/>
          <input type="text" name="authorimg" v-model="userdate.logpath" hidden/>
          <input type="text" name="authorheader" v-model="userdate.imgpath" hidden/>
          <input type="text" name="title" v-model="title" hidden/>
          <input type="text" name="text" v-model="text" hidden/>
          <input type="text" name="ctime" v-model="newTime" hidden/>
          <input type="file" name="files" style="" class="file"
            @change="upimg(item-1),showimg=true"
            v-for="item in lengths" :key="item"
            v-show="index==item-1"
            accept="image/gif, image/jpeg, image/png, image/jpg">
        <button class="addimg">
        <img src="../assets/图片添加.png" style="width: 80px;margin: 0 auto;">
        <h5>添加图片</h5>
        </button>
        </form>
      </div>
      <div style="width: 100%;height: 5px;opacity: 0;"></div>
         <button  class="btn" style="background-color: cornflowerblue;" @click="submitTake()">发布话题</button>
        <button  class="btn" @click="saveTake()">返回主页</button>
        <button @click="htmlTest=true" class="btn">网页预览</button>
      </div>
      </div>
    </div>
    <div v-show="htmlTest" style="position: absolute;width: 100%;height: 100vh;overflow: auto;" id="body">
      <div v-show="showCard" style="position: fixed;right: 0;top: 50px;width: 300px;min-height: 250px;background-color: white;height: auto;border: 2px solid white;animation:zoomIn .5s ease forwards;">
        <div style="width: 300px;height: 140px;overflow: hidden;position: absolute;overflow: hidden;">
          <img :src="'http://localhost:8086/'+userdate.logpath" style="width: 300px;">
        </div>
        <div style="border-radius: 50%;width: 140px;height: 140px;overflow: hidden;position: absolute;border: 2px solid white;top: 70px;">
          <img :src="'http://localhost:8086/'+userdate.imgpath" style="width: 140px;">
        </div>
        <div style="position: absolute;top:145px;left: 150px;"><h1>{{ userdate.name }}</h1></div>
        <div style="position: relative;top: 215px;left: 10px;width: 280px;">
          <button id="lookTa">看看Ta的主页</button>
        </div>
      </div>
      <div style="width: 1000px;margin: 0 auto;height: 300px;overflow: hidden;">
        <img :src="firstImg()" style="width: 1000px;">
      </div>
      <div style="width: 1000px;margin: 0 auto;height: auto;background-color: white;min-height: 300px;">
        <div style="border-radius: 50%;width: 200px;height: 200px;overflow: hidden;position: absolute;border: 2px solid white;top: 200px">
          <img :src="'http://localhost:8086/'+userdate.imgpath" style="width: 200px;">
        </div>
        <div style="height: 110px;width: 100%;position: relative;overflow: hidden;">
          <div style="font-size: 60px;font-weight: 900;position: relative;left: 200px;">{{ title }}</div>
          <div style="position: relative;left: 230px;color: gray;font-size: 20px;">by {{ userdate.name }}  发布于 {{ newTime }}</div>
        </div>
        <br>
        <div style="position: relative;;width: 950px;left: 25px;">
          标签:
          <button class="addType" v-for="item in label.ls" :key="item">{{ item }}</button>
          <br>
          <br>
          <div style="width: 100%;height: auto;white-space: pre-wrap;font-size: 23px;">{{ text }}</div>
          <br>
          <br>
          <img :src="item" v-for="item in imgdata.ls" :key="item" style="width: 950px;margin: 0 auto;margin-bottom: 5px;" v-show="item!==''" alt="">
          <div style="text-align: center;width: 100%;height: 30px;"> <button class="btn" @click="htmlTest=false">返回编辑界面</button></div>
          <br>
          <br>
        </div>
      </div>
    </div>
    <div style="position: absolute;width: 10%;padding: 10px;font-size: 20px;top:20%;background-color: greenyellow;left: 45%;text-align: center;" v-show="submitF">上传中</div>
  </div>
</template>
<script setup>
// import router from '@/router/router'inject,
import router from '@/router/router'
import { onMounted, reactive, ref, inject } from 'vue'
const $axios = inject('$axios')
const userdate = ref(JSON.parse(window.sessionStorage.getItem('user')))
const htmlTest = ref(false)
const submitF = ref(false)
const newTime = new Date().getFullYear() + '/' + (new Date().getMonth() + 1) + '/' + new Date().getDay()
const label = reactive({
  ls: []
})

const firstImg = () => {
  for (let i = 0; i < imgdata.ls.length; i++) {
    if (imgdata.ls[i] !== '') {
      return imgdata.ls[i]
    }
  }
}
const saveTake = () => {
  router.push('./index')
}
const submitTake = () => {
  if (text.value === '') {
    alert('内容不能为.....')
    return
  }
  if (title.value === '') {
    alert('标题不能为.....')
    return
  }
  const fr = document.getElementById('test')
  let labels = ''
  for (let i = 0; i < label.ls.length; i++) {
    labels += label.ls[i] + ','
  }
  $axios({
    url: 'http://localhost:8086/community/submit',
    method: 'post',
    data: fr,
    params: {
      ldd: labels
    }
  }).then((resp) => {
    submitF.value = true
    setTimeout(() => {
      router.push('./index')
    }, 500)
  })
}
const labelText = ref('')
const addLabel = () => {
  if (labelText.value === '') {
    addLabelShow.value = false
    return
  }
  for (let i = 0; i < label.ls.length; i++) {
    if (label.ls[i] === labelText.value) {
      addLabelShow.value = false
      labelText.value = ''
      return
    }
  }
  label.ls.push(labelText.value)
  labelText.value = ''
  addLabelShow.value = false
}
const title = ref('')
const addLabelShow = ref(false)
const text = ref('')
let fileInput
let preview
const imgdata = reactive({
  ls: []
})
let imgview
const showCard = ref(false)
const lengths = ref(6)
const index = ref(0)
const upimg = (inx) => {
  console.log(inx)
  fileInput = document.getElementsByClassName('file')
  preview = document.getElementsByClassName('image-preview')
  imgview = document.getElementsByClassName('is')
  console.log(preview[inx].src)
  preview[inx].style.backgroundImage = ''
  if (!fileInput[inx].value) {
    return
  }
  const file = fileInput[inx].files[0]
  const size = file.size
  if (size >= 6 * 1024 * 1024) {
    alert('文件大小超出限制')
    return false
  }
  // 获取File信息:
  if (!['image/jpeg', 'image/png', 'image/gif'].includes(file.type)) {
    alert('不是有效的图片文件!')
    return
  }
  // 读取文件:
  const reader = new FileReader()
  reader.onload = function (e) {
    const data = e.target.result
    preview[inx].src = data
    imgdata.ls.push(data)
    // preview[inx].style.width = '400px'
    // preview[inx].style.height = '400px'
    index.value++
  }
  // 以DataURL的形式读取文件:
  reader.readAsDataURL(file)
}
const del = (inx) => {
  fileInput[inx].value = ''
  preview[inx].src = ''
  imgview[inx].style.display = 'none'
  imgdata.ls[inx] = ''
  lengths.value++
}
let card
onMounted(() => {
  card = document.getElementById('body')
  setInterval(() => {
    if (card.scrollTop > 400) {
      showCard.value = true
    } else {
      showCard.value = false
    }
  }, 120)
})
</script>
<style scoped>
.is:hover .del{
  display: block;
}
.image-preview{
  min-width: 300px;
  height: 300px;
  animation: zoomIn 0.8s ease forwards;
  position: absolute;
}
.is{
  position: relative;
  display: inline-block;
  width: 300px;
  height: 300px;
  margin-left: 30px;
  overflow: hidden;
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
.file{
  width: 300px;
  height: 300px;
  border-radius: 5%;
  opacity: 0;
  left: 19%;
  position: absolute;
}
.del{
  width: 400px;
  height: 400px;
  background: #000;
  right: 0;
  opacity: 0.7;
  font-size: 90px;
  position: absolute;
  display: none;
}
::-webkit-scrollbar{
      display: none;
}
.addimg{
  vertical-align:bottom;
  width: 300px;
  height: 300px;
  border: 0px;
  background-color: rgb(236, 236, 233);
  background-repeat: no-repeat;
  background-position: center;
  border-radius: 5%;
  transition: 0.5s;
}
#fr{
  margin-left: 30px;
  position: relative;
  display: inline-block;
}
#fr:hover .addimg{
  box-shadow: 0 0 20px #504e49;
  background-color: rgb(151, 150, 150);
}
.btn{
  margin-left: 3%;
  width: 200px;
  height: 40px;
  font-size: 20px;
  border-radius: 20px;
  border: 0px;
  transition: 0.6s;
}
.btn:hover{
  background-color: rgb(151, 150, 150);
}
.addType{
  border: 0px;
  outline: none;
  background-color: aquamarine;
  padding: 6px;
  margin-left: 15px;
  border-radius:5px ;
}
#epc{
  margin-left: 5px;
  border: 0px;
  width: 90px;
  max-width: 120px;
  outline: none;
  background-color: aquamarine;
  padding: 6px;
  border-radius:5px ;
}
</style>
