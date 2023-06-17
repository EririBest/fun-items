<template>
  <router-view></router-view>
</template>

<script>

</script>

<style>
*{
  margin: 0;
  padding: 0;
  font-family: "微软雅黑";
  list-style: none;
}
.container{
    background-color: #fff;
    width: 650px;
    height: 415px;
    animation: zoomIn 1s ease forwards;
    border-radius: 5px;
    /* 阴影 */
    box-shadow: 5px 5px 5px rgba(0,0,0,0.1);
    /* 相对定位 */
    position: relative;
    /* 透明度 */
    opacity: 0.92;
}
span.pixal{
    position:absolute;
    left:-5px;
    top:-5px;
    width:4px;
    height:4px;
    background:white;
    border-radius: 50%;
    z-index: 9999;
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

/* 动画 */

@keyframes slideRight {
    0% {
        transform: translateX(-100px);
    }

    100% {
        transform: translateX(0);
        opacity: 1;
    }
}

@keyframes slideLeft {
    0% {
        transform: translateX(100px);
    }
    100% {
        transform: translateX(0);
        opacity: 1;
    }
}

@keyframes slideTop {
    0% {
        transform: translateY(100px);
    }
    100% {
        transform: translateY(0);
        opacity: 1;
    }
}

@keyframes slideBottom {
    0% {
        transform: translateY(-100px);
    }
    100% {
        transform: translateY(0);
        opacity: 1;
    }
}

@keyframes zoomIn {
    0% {
        transform: scale(0);
    }
    100% {
        transform: scale(1);
        opacity: 1;
    }
}
@keyframes down {
    0% {
        transform:
        translateY(calc(var(--z) * 60px))
        translateZ(calc(var(--z) * 100px))
        rotate(-45deg)
        translateX(calc(var(--x) * 100px))
        ;
    }
    100% {
        transform:
        translateY(calc(var(--z) * 60px))
        translateZ(calc(var(--z) * 100px))
        rotate(-45deg)
        translateX(calc(var(--x) * -100px))
        ;
    }
}
@keyframes neon {
      0%, 30%, 32%, 34% {
        color: #222;
        text-shadow: -3px 0px 5px #222, 0px 0px 2px #222;
      }
      31%, 33%, 35%, 100% {
        color: #e91e63;
        text-shadow: -3px 0px 5px #e91e63, 0px 0px 2px #e91e63;
      }
    }
@keyframes floatImage {
    0% {
        transform: translateY(0);
    }

    50% {
        transform: translateY(-24px);
    }

    100% {
        transform: translateY(0);
    }
}

</style>
