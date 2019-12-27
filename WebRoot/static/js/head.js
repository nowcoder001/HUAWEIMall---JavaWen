window.onload=function(){
      document.getElementById("bb").style.display = "none";
      document.getElementById("bbb").style.display = "none";
      document.getElementById("bbbb").style.display = "none";
      document.getElementById("history").style.display = "none";
}

//鼠标移上去显示
function more(){
    var bb = document.getElementById("bb");

    bb.style.display = "block";
}
//鼠标松开
function moreOut(){
    var bb = document.getElementById("bb");

    bb.style.display = "none";
}
//鼠标移上去显示
function more2(){
    var bb = document.getElementById("bbb");

    bb.style.display = "block";
}
//鼠标松开
function moreOut2(){
    var bb = document.getElementById("bbb");

    bb.style.display = "none";
}
//鼠标移上去显示
function more3(){
    var bb = document.getElementById("bbbb");

    bb.style.display = "block";
}
//鼠标松开
function moreOut3(){
    var bb = document.getElementById("bbbb");

    bb.style.display = "none";
}
//点击搜索框弹出div
function clicks(){
    var history = document.getElementById("history");

    history.style.display = "block";
}
//失焦事件
function blurs(){
    var history = document.getElementById("history");

    history.style.display = "none";
}

