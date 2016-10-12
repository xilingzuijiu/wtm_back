/**
 * Created by Administrator on 2016/10/12 0012.
 */
var hashMap = {
    Set : function(key,value){this[key] = value},
    Get : function(key){return this[key]},
    Contains : function(key){return this.Get(key) == null?false:true},
    Remove : function(key){delete this[key]}
}
var total=0;
var count=1;
var pageSize=8;
$(function(){
    loadReadlist();
//            setInterval(function () {
//                location.reload()
//            },30000)
})
$(window).scroll(function(){
    var scrollTop = $(this).scrollTop();               //滚动条距离顶部的高度
    var scrollHeight = $(document).height();                   //当前页面的总高度
    var windowHeight = $(this).height();                   //当前可视的页面高度
    if(scrollTop + windowHeight >= scrollHeight){        //距离顶部+当前高度 >=文档总高度 即代表滑动到底部
        console.log(Math.ceil(total/pageSize)+"shi");
        count++;
        if(count<=Math.ceil(total/pageSize)){
            loadReadlist();
        }
        if(count==Math.ceil(total/pageSize)){
            var p = document.createElement('p');
            p.className="loadmore"
            p.innerHTML = "没有更多";
            $("body").append(p);
        }
    }else if(scrollTop<=0){         //滚动条距离顶部的高度小于等于0
//                    location.reload();
    }
});
//function getMemberRequestHeaderMsg(XMLHttpRequest){
//    var memberId= $.cookie("memberId");
//    if (memberId==null||memberId == undefined){
//        Showbo.Msg.confirm("登录已过期请重新登录", function () {
//            location.href="login.html";
//        })
//    }
//    var password= $.cookie("password");
//    XMLHttpRequest.setRequestHeader("memberId",memberId);
//    XMLHttpRequest.setRequestHeader("from",2);
//}
function loadReadlist(){
    var request=JSON.stringify(new ArticleSerach(0,count,pageSize));
    console.log("count"+count);
    $.ajax({
        type: 'post',
        contentType: "application/json",
        dataType: 'json',
        url: '/pc/admin/article/getAllArticle',
        data:request,
        beforeSend: function (XMLHttpRequest) {
            getMemberRequestHeaderMsg(XMLHttpRequest)
        },
        header:{
            "contentType":"application/json"
        },
        success: function (params) {
            var json = eval(params); //数组
            console.log("json数据为：" + params);
            console.log($.cookie("memberId"));
            if (json!=null&&json.errorCode==0){
                total=json.data.total
                json.data.list.forEach(function (article) {
                    var li = document.createElement("li");
                    li.setAttribute("onClick", "articleSubmit(this)");
                    li.id = article.id;
                    var div1 = document.createElement('div');
                    hashMap.Set(li.id, article.url);
                    div1.className = "col-xs-3 listimg";
                    var img = document.createElement('img');
                    img.setAttribute("src", 'http://weitaomi.b0.upaiyun.com' + article.imageUrl);
                    div1.appendChild(img);
                    var div2 = document.createElement('div');
                    div2.className = "readdetails col-xs-6";
                    var h5 = document.createElement('h5');
                    h5.className="articltitle";
                    h5.innerHTML = article.title;
                    var h6 = document.createElement('h6');
                    h6.innerHTML = article.articleAbstract;
                    var p = document.createElement('p');
                    p.innerHTML = "剩余阅读数："
                    var span = document.createElement('span');
                    span.innerHTML = article.readIncreaseNumber;//阅读数名称修改
                    p.appendChild(span);
                    div2.appendChild(h5);
                    div2.appendChild(h6);
                    div2.appendChild(p);
                    var div3 = document.createElement('div');
                    div3.className = "checkanniu col-xs-3";
                    var a = document.createElement('a');
                    a.innerHTML = "阅读";
                    div3.appendChild(a);
                    li.appendChild(div1);
                    li.appendChild(div2);
                    li.appendChild(div3);
                    document.body.appendChild(li);
                    document.getElementsByTagName('ul')[0].appendChild(li);//动态添加文章（li标签）
                })
            }
            else
            {
                alert("获取数据失败")
            }
        },
        error:function (data) {
            console.log(data)
            alert("页面加载错误，请重试");
        }

    })
}
var articleSubmit = function(obj){
    var articleId=obj.getAttribute("id");
    var articleUrl=hashMap.Get(articleId);
    console.log(articleId);
    $.ajax({
        type: 'post',
//                dataType: 'json',
        url: '/pc/admin/article/pcreadArticleRequest',
        data: {articleId:articleId},
        beforeSend: function (XMLHttpRequest) {
            getMemberRequestHeaderMsg(XMLHttpRequest)
        },
        success: function (params) {
            var json = eval(params); //数组
            console.log("json数据为："+params)
            if (json!=null&&json.errorCode==0) {
                obj.style.display='none';
                location.href=articleUrl;
            }else if (json!=null&&json.errorCode==4){
                Showbo.Msg.alert(json.message, function () {
                    location.reload();
                });
            }else{ alert("获取数据失败")}
        },
        error:function (data){
            console.log(data)
            alert("页面加载错误，请重试");
        }
    })
}
function ArticleSerach(searchWay,pageIndex,pageSize){
    this.searchWay=searchWay
    this.pageIndex=pageIndex
    this.pageSize=pageSize
}