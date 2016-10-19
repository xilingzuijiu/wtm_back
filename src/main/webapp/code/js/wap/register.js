/**
 * Created by Administrator on 2016/10/18 0018.
 */
$(function(){
    $("#sendVerifyCode").click( function (){
        var telephone = $("#telephone").val().trim();
        if (telephone==null || !telephone.match(/^1\d{10}$/)){
            alert("手机号码不正确")
        }else {
            $.ajax({
                type: 'get',
                url: '/pc/admin/member/sendIndentifyCode',
                data: {telephone:telephone},
                success: function (params) {
                    var data=eval(params)
                    var errorCode=data.errorCode
                    if (errorCode==0){

                    } else if (errorCode==4){
                        alert(data.message)
                    }
                }

            })
            $("#sendVerifyCode").css("display","none");
            $("#downtime").css("display","block");
            countDown();
            setTimeout(function () {
                $("#sendVerifyCode").css("display","block");
                $("#downtime").css("display","none");
            }, SS*1000);
        }

    })
    $("#register").click( function (){
        $("#form").attr("enctype","multipart/form-data");
        var requestObj = eval($("#registerForm").serializeObject())
        var request =getRegeisterParams(requestObj);
        if(!$("#memberName").val()){
            alert("用户名不能为空");
        }else if($("#password").val().length<6){
            alert("密码长度不能小于6位");
        }else{
            $.ajax({
                type: 'post',
                url: '/pc/admin/member/register',
                dataType:"json",
                data: request,
                contentType: "application/json",
                beforeSend: function (XMLHttpRequest) {
                    XMLHttpRequest.setRequestHeader("from","2")
                },
                success: function (params) {
                    var data=eval(params)
                    var errorCode=data.errorCode
                    if (errorCode==0){
                        if (data.data){
                            location.href="/frontPage/wap/login.html";
                        }
                    } else if (errorCode==4){
                        alert(data.message)
                    }
                }
            })
        }
    })
})