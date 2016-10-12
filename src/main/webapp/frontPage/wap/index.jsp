<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/10
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0" name="viewport" />
    <title>首页</title>
    <link rel="stylesheet" href="../../code/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../code/css/bootstrap-theme.min.css">
    <link href="../../code/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="../../code/css/wapstyle.css" rel="stylesheet" type="text/css"/>
    <script src="../../code/js/wap/common.js"></script>
    <script>
        var u = navigator.userAgent;
        var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1;//android终端
        var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/);// ios终端
        $(document).ready(function () {
            $(".owner img").click(function(){
                location.href="mycenter.html";
            })
            var index=0;//index只是定义的一个变量，可以是任意名称。
            var liwidth=$(document.body).width();
            var leftwidth=liwidth*2.5%
                    console.log(leftwidth);
            $(".homebanner li").css("width",liwidth);
            var liwi=$(".homebanner li").width();
            var length=$(".homebanner li").length;
            $(".homebanner ul").css("width",liwi*length);
            var ulwi=$(".homebanner ul").width();
            function show() {
                if (index == length - 1) {
                    $(".homebanner ul").css("right",0);
                    index = 1;
                } else {
                    index++;
                }
                $(".homebanner ul").animate({right: -index * liwi},400);
            }
            setInterval(show,2000);
            getLocation();
            updateMyscore();
            $(".money").click(function (){
                updateMyscore()
            })
//            document.addEventListener('touchstart', touchStart);
        })
    </script>
</head>
<body>
<header>
    <div class="adress">
        <span class="glyphicon glyphicon-map-marker"></span>
        <p id="addressname">俄罗斯</p>
    </div>
    <div class="owner">
        <img class="photo" src="../../code/img/MainViewImgae/women.jpg"/>
        <div class="ownerdetail">
            <h3 class="ownername"><span>黎沙</span>一款玩着就会赚钱的APP一款玩着就会赚钱的APP</h3>
        </div>
        <div class="money">
            <p><span>38.0</span>米<br/>我的钱包</p>
        </div>
    </div>
</header>
<div class="homebanner swiper-container">
    <ul class="swiper-wrapper">
        <li class="swiper-slide">
            <a href="rewards.html"> <img src="../../code/img/fahongbao.png"/></a>
        </li>
        <li class="swiper-slide">
            <a href="myinvite.html"><img src="../../code/img/yaohaoyou.png"/></a>
        </li>
        <li class="swiper-slide">
            <a href="rewards.html"><img src="../../code/img/fahongbao.png"/></a>
        </li>
    </ul>
</div>
<ul class="row indexlist">
    <li class="col-xs-4">
        <a class="thumbnail" href="readlist.html">
            <img src="../../code/img/MainViewImgae/read.png" >
            <div class="caption">
                <h3>文章列表</h3>
            </div>
        </a>
    </li>
    <li class="col-xs-4">
        <a class="thumbnail" href="gongzhonghao.html">
            <img src="../../code/img/MainViewImgae/attention.png" >
            <div class="caption">
                <h3>关注公众号</h3>
            </div>
        </a>
    </li>
    <li class="col-xs-4">
        <a class="thumbnail" href="pursewithdraw.html">
            <img src="../../code/img/MainViewImgae/mymoney.png" >
            <div class="caption">
                <h3>钱包提现</h3>
            </div>
        </a>
    </li>
    <li class="col-xs-4">
        <a class="thumbnail" href="rewards.html">
            <img src="../../code/img/MainViewImgae/new.png" >
            <div class="caption">
                <h3>任务奖励</h3>
            </div>
        </a>
    </li>
    <li class="col-xs-4">
        <a class="thumbnail" href="mycenter.html">
            <img src="../../code/img/MainViewImgae/person.png" >
            <div class="caption">
                <h3>个人中心</h3>
            </div>
        </a>
    </li>
    <li class="col-xs-4">
        <a class="thumbnail" href="invitefriend.html">
            <img src="../../code/img/MainViewImgae/friend.png" >
            <div class="caption">
                <h3>邀请好友</h3>
            </div>
        </a>
    </li>
    <li class="col-xs-4">
        <a class="thumbnail" href="question.html">
            <img src="../../code/img/MainViewImgae/tasknotes.png" >
            <div class="caption">
                <h3>常见问题</h3>
            </div>
        </a>
    </li>
    <li class="col-xs-4">
        <a class="thumbnail" href="rewardrules.html">
            <img src="../../code/img/MainViewImgae/help.png" >
            <div class="caption">
                <h3>奖励规则</h3>
            </div>
        </a>
    </li>
    <li class="col-xs-4">
        <a class="thumbnail" href="testrecord.html">
            <img src="../../code/img/MainViewImgae/about.png" >
            <div class="caption">
                <h3>任务记录</h3>
            </div>
        </a>
    </li>
</ul>
</body>
</html>