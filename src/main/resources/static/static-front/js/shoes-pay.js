$(function () {
    //let date = JSON.parse(localStorage.getItem("shoes"));
    // let data = JSON.parse($.cookie("shoes"));
    //
    // console.log(data);


    console.log("开始加载订单模块的 js");

});

/**
 *  处理下单
 * @param OrderItemArr
 */
function handlePay(OrderItemArr){

}

// 订单项对象
function OrderItem(skuId, count) {
    this.skuId = skuId;
    this.count = count;
}

// 地址对象
function Addr(province, city, area,street,deatailAddr) {
    this.province = province;
    this.city = city;
    this.area = area;
    this.street = street;
    this.deatailAddr = deatailAddr;
}

/**
 *  返回地址的字符串
 */
Addr.prototype.addrString = function (){
    return this.province + this.city + this.area + this.street + this.deatailAddr;
}

/**
 *  提交订单
 */
function handOrder() {
    // 获取商品数据项
    let contactPhone = $('#contactPhone');
    let contactName = $("#contactName");
    

    // 获取收货信息项
    let addr = getAddr().addrString();

    // 获取用户id
    let userId = $.cookie("token");


    // 提交请求 POST 请求
    $.ajax({
        url:/order/add,
        type: "POST",
        data:{
            "contactPhone":contactPhone,
            "contactName":contactName,
            "receiptaddress":addr,
            "userId": userId
        },
        success: function (data) {
            // 下单成功
            if(data.code == 0){

            }else{
                // 下单失败
            }
        },
        error: function () {
            alert("客户端请求异常！");
        }
    });


}

/**
 *  根据 order item 的 class 来获取 orderItem 对象的 集合
 * @param orderCls
 */
function getOrderItems(orderCls) {
    
}

/**
 *  封装收货地址信息
 */
function getAddr() {
    let province = $("#order-province");
    let city = $("#order-city");
    let area = $("#order-area");
    let street = $("#order-street");
    let addr = $("#order-addr");

    let resAddr = new Addr(province,city,area,street,addr);

    return resAddr;
}


function getUserId() {
    let token = getCookie("token");
    if(token === ""){
        var r=confirm("您还没登录，请先登录！")
        if (r==true)
        {
           return "TO_LOGIN_PAGE";
        }
        else
        {
           return "NOT_DO_ANYTHING";
        }
    }

    return token;
}

/**
 *  根据cookie的名字获取cookie的值
 * @param name
 * @returns {string}
 */
function getCookie(name){
    let strcookie = document.cookie;//获取cookie字符串
    let arrcookie = strcookie.split("; ");//分割
    //遍历匹配
    for ( var i = 0; i < arrcookie.length; i++) {
        var arr = arrcookie[i].split("=");
        if (arr[0] == name){
            return arr[1];
        }
    }
    return "";
}

