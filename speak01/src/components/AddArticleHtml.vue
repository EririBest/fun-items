<template>
  <div style="width: 100%;height:auto;background:white;overflow: auto;">
     <div style="margin: 0 auto;width: 80%;height: 100vh;">
      <br>
      <br>
      <div class="text">
        <input type="text" style="font-size: 30px;width: 100%;border: 0px;outline: none;border-bottom: 1px solid gray;" placeholder="标题" name="title" v-model="title">
        <input type="text" style="font-size: 30px;color: red;width: 33%;border: 0px;outline: none;border-bottom: 1px solid gray;" placeholder="价格" name="title" v-model="price">
        &nbsp;
        <input type="text" style="font-size: 30px;width: 33%;border: 0px;outline: none;border-bottom: 1px solid gray;" placeholder="分类/型号" name="title" v-model="type">
        <br>
      <textarea wrap="hard"
                  cols="10"
                  rows="3"
                  placeholder="商品描述"
                  name="text"
                  style="resize: none;width: 100%;min-height: 40px;font-size: 30px;color: black;outline: none;border: 0px;"
                  v-model="text"
                 ></textarea>
                 <hr>
    </div>
    <div v-for="item in lengths" :key="item" class="is" v-show="index+1>item">
      <img class="image-preview"  >
      <div class="del" @click="del(item-1)">
        <img src="../assets/删除.png" style="position: absolute;top:110px;left:90px;" alt="">
      </div>
    </div>
    <div action="" id="fr">
      <form action="" id="test">
        <input type="text" name="name" v-model="userdate.name" hidden/>
        <input type="text" name="id" v-model="userdate.id" hidden/>
        <input type="file" name="files" style="" class="file"
          @change="upimg(item-1),showimg=true"
          v-for="item in lengths" :key="item"
          v-show="index==item-1"
          accept="image/gif, image/jpeg, image/png, image/jpg">
      <button class="addimg" v-show="lengths != index"></button>
      </form>
    </div>
    <br>
    <button @click="addgoods()" class="btn">发布交易</button>
    </div>
  </div>
</template>
<script  setup>
import router from '@/router/router'
import { inject, ref } from 'vue'
const $axios = inject('$axios')
const userdate = ref(JSON.parse(window.sessionStorage.getItem('user')))
const price = ref()
const type = ref()
const addgoods = () => {
  const fd = document.getElementById('test')
  $axios({
    url: 'http://localhost:8086/ccs/addUserCommodity',
    method: 'post',
    params: {
      text: text.value,
      title: title.value,
      price: price.value,
      type: type.value
    },
    data: fd
  }).then(() => {
    router.push('./lgo')
  })
}
const title = ref('')
const text = ref('')
let fileInput
let preview
let imgview
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
    console.log(preview, 'a标签')
    preview[inx].src = data
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
  lengths.value++
}
</script>
<style scoped>
.is:hover .del{
  display: block;
}
.image-preview{
  min-width: 400px;
  height: 400px;
  animation: zoomIn 0.8s ease forwards;
  position: absolute;
}
.is{
  position: relative;
  display: inline-block;
  width: 400px;
  height: 400px;
  margin-left: 10%;
  overflow: hidden;
}
.file{
  width: 400px;
  height: 400px;
  border-radius: 50%;
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
  width: 400px;
  height: 400px;
  border: 0px;
  margin-left: 19%;
  background-color: rgb(236, 236, 233);
  background-image: url('../assets/图片添加.png');
  background-repeat: no-repeat;
  background-position: center;
  border-radius: 50%;
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
  width: 50%;
  left: 25%;
  height: 90px;
  font-size: 40px;
  border-radius: 20px;
  border: 0px;
  transition: 0.6s;
}
.btn:hover{
  background-color: rgb(151, 150, 150);
}
</style>
