/*
 *  对一些公共工具类的封装
 *
 *  在这里封装的方法全部以 $_ 开头
 *
 * */
$(function () {
    console.log(" commom.js 加载成功！")
});

function dumpToPayPage(data) {

    if(!data){
        return;
    }

    // 将 data 封装成 OrderItem 对象 id:4color:2size:7count:1

    let skuId = $_getSkuId(data.id, data.color,data.size);

    console.log("skuId ===="+skuId);

    handlePay(data);

   // window.location.href ="/static-front/html/shoes-pay.htm";
}

/**
 * 获取skuId
 * @param shoesid
 * @param colorId
 * @param sizeId
 */
function $_getSkuId(shoesid,colorId, sizeId){

    let result;

    // 请求
    $.ajax({
            type:"GET",
            url: "/goodsf/getQtyAjax",
            data: {
                "shoesid":shoesid,
                "colorid":colorId,
                "sizeid":sizeId
            },
            success:function (data) {
               console.log("ajax ----"+data.skuid);
                // 处理 下单

                //result = data.skuid;
            },
            error:function () {
                alert("请求失败！");
            }
        });
    if(!skuId){
        alert("请求失败！");
    }

    return result;

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



    // init status first
    $_initActive(oParent,className);

    let target = $(oTarget);

    target.addClass(className);


    // 大图的处理表示要处理大图
    if(oParent === 'detail-shoes-simg'){
        let imgUrl = target.attr('src');
        imgUrl = imgUrl.replace('/sma-img','/big-img');

        BigShoesImgChange(imgUrl);
    }
}

/**
 * 初始化活跃状态 基于jquery处理
 *
 * @param oParent 要初始化的父类元素
 * @param className 要初始化的对象类
 */
function $_initActive(oParent, className) {

    let $parent = $($_Id(oParent));

    // The first must get object with the class name is oClass but inner of the oParent
    // is return a array
    let aClassObj = $_Class($parent,className);

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
};