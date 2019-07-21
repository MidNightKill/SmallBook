function getNowFormatDate() {
    if($("#date").val()!="")
    {
        $("#date").val("");
        return;
    }

    var date = new Date();
    var seperator1 = "";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    // return currentdate;

    $("#date").val(currentdate);


}
function getProfit()
{
    if(!trueInput())
    {
        $("#profit").html("");
        return;
    }
    var result=parseFloat($("#ordering").val())+parseFloat($("#menber").val())+parseFloat($("#dine").val())
        +parseFloat($("#takeaway").val())-parseFloat($("#pay").val());


    $("#profit").html(""+result);
    // return result;
}
function trueInput()
{
    if(isNaN($("#date").val())||$("#date").val().length!=8)
    {
        $("#tips").html("日期格式输入异常");
        return false;
    }
    if(isNaN($("#ordering").val()))
    {
        $("#tips").html("预定金额异常");
        return false;
    }
    if(isNaN($("#menber").val()))
    {
        $("#tips").html("会员金额异常");
        return false;
    }
    if(isNaN($("#dine").val()))
    {
        $("#tips").html("堂食金额异常");
        return false;
    }
    if(isNaN($("#takeaway").val()))
    {
        $("#tips").html("外卖金额异常");
        return false;
    }
    if(isNaN($("#pay").val()))
    {
        $("#tips").html("支出金额异常");
        return false;
    }
    $("#tips").html(" ");
    return true;
}


function getrecordingbyday(){

    $.ajax({
        type: "post",
        url: "./getrecordingbyday",
        data: {
            date:$("#date").val()
        },
        dataType: 'text',
        success: function (data) {
            if(data!="false")
            {
                result=JSON.parse(data);
                $("#ordering").val(result.ordering);
                $("#menber").val(result.menber);
                $("#takeaway").val(result.takeaway);
                $("#dine").val(result.dine);
                $("#pay").val(result.pay);
                getProfit();
                $("#modifyrecording").show();
            }
            else
            {
                alert("获取数据失败，当日是否没有入账");
            }
        },
        error: function () {
            // alert("登录失败"+"aaaa");
        }
    })}

function addrecording(){
    if(!trueInput())
    {
        return;
    }
    var recording={};
    recording.date=$("#date").val();
    recording.ordering=$("#ordering").val();
    recording.menber=$("#menber").val();
    recording.takeaway=$("#takeaway").val();
    recording.dine=$("#dine").val();
    recording.pay=$("#pay").val();
    $.ajax({
        type: "post",
        url: "./addrecording",
        data: recording,
        dataType: 'text',
        success: function (data) {
            if(data=="true")
            {
                alert("写入成功");
            }
            else if(data=="exist")
            {
                alert("当日账目已经存在");
            }
            else
            {
                alert("写入失败");
            }
        },
        error: function () {
            // alert("登录失败"+"aaaa");
        }
    })}

function modifyrecording(){
    if(!trueInput())
    {
        return;
    }
    var recording={};
    recording.date=$("#date").val();
    recording.ordering=$("#ordering").val();
    recording.menber=$("#menber").val();
    recording.takeaway=$("#takeaway").val();
    recording.dine=$("#dine").val();
    recording.pay=$("#pay").val();
    $.ajax({
        type: "post",
        url: "./modifyrecording",
        data: recording,
        dataType: 'text',
        success: function (data) {
            if(data=="true")
            {
                alert("写入成功");
            }
            else
            {
                alert("写入失败");
            }
        },
        error: function () {
            // alert("登录失败"+"aaaa");
        }
    })}

function searchrecording(){
    $.ajax({
        type: "post",
        url: "./searchrecording",
        data: {
            date:$("#date").val()
        },
        dataType: 'text',
        success: function (data) {
            if(data=="true")
            {
                window.location.href="./searchrecordingresult.html";

            }
            else
            {
                alert("查询失败");
            }
        },
        error: function () {
            alert("查询失败");
        }
    })}