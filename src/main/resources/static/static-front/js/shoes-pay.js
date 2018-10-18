$(function () {
    //let date = JSON.parse(localStorage.getItem("shoes"));
    // let data = JSON.parse($.cookie("shoes"));
    //
    // console.log(data);


    console.log("开始加载订单模块的 js");

});


/**
 *  订单页面的渲染
 * @param OrderItemArr
 */
function renderPayPage(orderItemArr){
    if(!orderItemArr){
        return;
    }
    console.log(orderItemArr);
    //
    let str = "";

    $.each(orderItemArr,function (i) {
        str += doRenderPayPage(orderItemArr[i]);
    });

    $("#orderListBody").html(str);
}

function doRenderPayPage(data) {
    console.log("jaige:"+data.price);
    console.log("shuliang:"+data.count);
    let totalPrice = Number(data.price) * Number(data.count);

    let str = '<tr class="cart-list-line"></tr>'
        +'<tr class="cart-list-tr not-selected order-item">'
        +'<td>'
        +'<img src="'+data.colorPic+'" style="width: 80px;height: 80px;">'
        +'<a class="cart-shoes-name" href="javascript:;">'+ data.shoesname +'</a>'
    +'</td>'
    +'<td> 颜色分类：<span>'+ data.color +'</span> 尺码：<span>'+ data.size +'</span>'
    +'</td>'
    +'<td class="price-now">¥ <span>'+ data.price +'</span></td>'
    +'<td>'
    +'<span class="cart-shoes-count">'
    +'<b>'+ data.count +'</b>'
    +'</span>'
    +'</td>'
    +'<td class="price-total">¥ <span>'+ totalPrice +'</span> <input class="orderItem-skuid" type="hidden" value="'+ data.skuid +'"/></td>'
    +'</tr>';

    console.log(str);

    return str;

}


/**
 * 订单项
 * @param colorPic  对应颜色的图片
 * @param shoesName 鞋子的名称
 * @param color     鞋子的颜色
 * @param size      鞋子的尺码
 * @param skuid     鞋子的skuid
 * @param count     下单的数量
 * @param price     下单时候的价格
 * @constructor
 */
function OrderItem(colorPic,shoesName, color, size, skuid,count,price) {
    this.colorPic = colorPic;
    this.shoesName = shoesName;
    this.color = color;
    this.size = size;
    this.skuid = skuid;
    this.count = count;
    this.price = price;
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

/**
 * 订单对象，提交过去的就是该对象
 * @param userId                下单人
 * @param contactPhone          收货电话
 * @param contactName           签收人
 * @param addr                  签收地址
 * @param orderHandItemArr      提交的订单项数组
 * @constructor
 */
function ShoesOrder(userId,contactPhone,contactName,addr,orderHandItemArr) {
    this.userId = userId;
    this.contactPhone = contactPhone;
    this.contactName = contactName;
    this.addr = addr;
    this.orderHandItemArr = orderHandItemArr;
}

/**
 * 提交订单项
 * @param skuid
 * @param shoseCount
 */
function OrderHandItem(skuid, shoseCount) {
    this.skuid = skuid;
    this.shoseCount = shoseCount;
}

/**
 *  提交订单
 * @param ShoesOrder
 */
function handOrder(ShoesOrder) {

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

