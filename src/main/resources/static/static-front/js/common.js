/*
 *  对一些公共工具类的封装
 *
 *  在这里封装的方法全部以 $_ 开头
 *
 * */
$(function () {
    console.log(" commom.js 加载成功！")
});

/**
 *  跳转至下单页面
 * @param data
 */
function dumpToPayPage(data) {



    if(!data){
        return;
    }
    //$.cookie("data",data);
    let token = getCookie("token");

    if(!token){
        //TODO alert("你还未登录,请先登录！");
        token = "guoguanzhi-909683502";
        // 登录成功之后跳转到该页面
        // TODO 保存数据到时候直接跳转到该页面
        //return;
    }

    $.ajax({
        url: "/order/pageData",
        type: "POST",
        data: {
            "key":token,
            "data":JSON.stringify(data),
        },
        success: function (data) {

        }

    });

    //renderPayPage(data);
    window.location.href ="/static-front/html/shoes-pay.htm";
}


/**
 * 获取库存
 * @param shoesId
 * @param colorId
 * @param sizeId
 */
function getShoesStock(shoesId, colorId, sizeId) {

    let flag = true;
    let stock;

    if(!shoesId){
        return;
    }
    if(!colorId){
        colorId = "";
    }
    if(!sizeId){
        sizeId ="";
    }

    $.ajax({
        type: "GET",
        url: "/goodsf/getQtyAjax",
        async: false,
        data: {
            "shoesid":shoesId,
            "colorid":colorId,
            "sizeid":sizeId
        },
        success:function (data) {
            console.log("ajax ----"+data.skuid);
            // 处理 下单
            if(!data.skuid){
                return;
            }
            $("#shoes-skuid").val(data.skuid);
            stock = data.quantity;
        },
        error:function () {
            alert("请求失败！");
        }

    });

    return stock;
}

/**
 *  根据类名获取对象
 *
 * @param oParent 父元素的对象
 * @param clazz 要获取元素的类名
 * @returns
 */
function $_Class(oParent, clazz) {

    let parent = $_Id(oParent) || document;

    let boxArr = new Array();

    if(document.getElementsByClassName){//如果条件为真,就代表浏览器为火狐;
        return parent.getElementsByClassName(clazz)//火狐下面直接返回结果;

    }else{//不是火狐
        let oElements =parent.getElementsByTagName("*");//首先找到页面所有的标签;

        for (let i=0; i<oElements.length; i++) {
            if(checkClass(oElements[i].className,clazz)){//1.1.回调函数判断类名,因为同一标签可能有多个类名;
                boxArr.push(oElements[i])//1.3.如果是真的,就把这个元素推进数组里面;
            }
        }
        return boxArr;
    }

}

/**
 * 检查类
 * @param startClass
 * @param endClass
 * @returns {boolean}
 */
function checkClass (startClass,endClass) {//判断类名
    let arr=startClass.split(" ");//多个类名用空格分隔成不同元素的数组;

    for (let i=0; i<arr.length; i++) {

        if(arr[i]==endClass){//1.2.被分割的数组元素里面如果有一个等于classname就返回真;
            return true;
        }
    }

    return false;
}


/**
 * 根据id获取元素的简写
 * @param oId
 * @returns {HTMLElement}
 */
function $_Id(oId) {
    return document.getElementById(oId);
}

/**
 * 活跃对象转换
 *
 * @param oParent 父元素
 * @param oTarget 活跃的目标对象
 *
 * @param className 类名
 */
function $_activeChange(oParent,oTarget, className) {
    let target = $(oTarget);

    if(target.hasClass(className)){
        return;
    }
    // init status first
    $_initActive(oParent,className,target);

    target.addClass(className);


    // 大图的处理表示要处理大图
    if(oParent === 'detail-shoes-simg'){
        let imgUrl = target.attr('src');
        imgUrl = imgUrl.replace('/sma-img','/big-img');

        BigShoesImgChange(imgUrl);
    }

    // 地址选中
    if(className == "addr-seleted"){
        target.children("span").children(".img-radio").attr("src","../img/radio-checked.png");
        renderOrderCart();
    }


    // 是颜色或者尺码才获取库存
    if(className == "number-active" || className == "choose-border"){

        //  alert("点击了尺码或者颜色！");
        // 获取库存
        let colorId = $($_Class("detail-shoes-color","choose-border")[0]).attr("name");
        let sizeId = $($_Class("detail-shoes-size","number-active")[0]).attr("name");
        let shoesId =  $($_Id("shoes-id")).html();

        let stock = getShoesStock(shoesId,colorId,sizeId);
        let shoesCount= $.trim($($_Id("detail-shoes-count")).html());

        if(stock <= 0){
            $("#detail-shoes-total").html("<font style='color: red;'>已售罄！</font>");
            $($_Id("detail-shoes-count")).html(0);
            return;
        }

        // 设置库存
        $("#detail-shoes-total").html(stock);

        // 如果现在选择数量大于库存，直接为库存数量
        if(stock < shoesCount){
            if(stock < 0){
                $($_Id("detail-shoes-count")).html(0);
            }else{
                $($_Id("detail-shoes-count")).html(stock);
            }
        }
    }
}

/**
 *  获取token
 */
function getToken() {
    let token = getCookie("token");

    if(!token){
        // alert("你还未登录,请先登录！");
        token = "guoguanzhi-909683502";
        // 登录成功之后跳转到该页面
        // TODO 保存数据到时候直接跳转到该页面
        //return;
    }

    return token;
}

/**
 * 初始化活跃状态 基于jquery处理
 *
 * @param oParent 要初始化的父类元素
 * @param className 要初始化的对象类
 */
function $_initActive(oParent, className,target) {

    let $parent = $($_Id(oParent));

    // The first must get object with the class name is oClass but inner of the oParent
    // is return a array
    let aClassObj = $_Class($parent,className);

    if(oParent == "order-addr-li"){
        $parent.children(".addr-seleted").children("span").children(".img-radio").attr("src","../img/redio-no-checked.png");
        console.log("--->"+$parent.children(".addr-seleted").children("span").children(".img-radio").attr("src"));

    }

    // do init
    for (let i = 0; i<aClassObj.length; i++){

        // be a jquery obj
        if($(aClassObj[i]).hasClass(className)){
            $(aClassObj[i]).removeClass(className);
        }
    }






}


/**
 * 处理鞋子数目变化
 * @param isDecrease 是一个标记，如果是减少操作的话，该值为-1
 * @param target 目标对象
 * @param stock 库存
 */
function doShoesCount(isDecrease, target, stock) {

    let $target = $($_Id(target));
    let str = $("#detail-shoes-total").html();
    //库存不足
    if((str.indexOf("<font")) != -1){
        console.log("库存不足！" + str);
        $target.html(0);
        return;
    }



    let count = Number($target.html()); //当前选择数量

    const MAX_STOCK = getShoesMaxStock(stock);


    // is 0 can't decrease
    if(count === 1 && isDecrease === -1){

        return;
    }

    if(count === MAX_STOCK && isDecrease === 1){

        return;
    }
    count += isDecrease;

    $target.html(count);
}

/**
 * 获取最大库存
 *
 * @param id
 * @returns {number}
 */
function getShoesMaxStock(id) {

    return Number($($_Id(id)).html());
}

/**
 * 判断是否登录
 */
function isLogin() {
    let login_tip = $($_Id("login-tip"));

    // 如果有<b> 标签就表示已经登录
    return login_tip.children("b:first-child").length === 1 ? true:false;
}
// 获取url参数
function getQueryPathStringByName(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r != null) return unescape(r[2]);
    return null;
}
function $_chooseChange(oParent,oTarget, className) {

    $_initActive(oParent,className);

    let target = $(oTarget);

    target.addClass(className);

}

/**
 *  处理添加地址
 */
function doAddrClick() {
    
}

function $_initChoose(oParent, className) {

    let aClassObj = $_Class(oParent,className);

    // do init
    for (let i = 0; i<aClassObj.length; i++){

        // be a jquery obj
        if($(aClassObj[i]).hasClass(className)){
            $(aClassObj[i]).removeClass(className);
        }
    }

}