$(function () {
    //let date = JSON.parse(localStorage.getItem("shoes"));
    // let data = JSON.parse($.cookie("shoes"));
    //
    // console.log(data);

    console.log("开始加载订单模块的 js");

    // window.onunload=function () {
    //     //alert("请勿重复提交订单！");
    //     alert("刷新了页面！");
    //     $("#order-caculate-do").addClass("reapClick");
    // }
});

let orderTotal = 0;

let orderItemList = null;

function doOrderPageRenderWhenLoad() {

    let token = getToken();

    // 发送ajax获取数据
    $.ajax({
        url:"/order/getOrderPageData",
        type: "GET",
        data: {
            "key":token
        },
        success: function (data) {
            //alert(data.data);
            // 如果成功的话
            // 开始渲染页面
            if(data.data != null){
                renderPayPage(eval(data.data));
            }else {
                //window.location.href="/";
            }
        }
    });
}

/**
 *  跳转购物车页面
 */
function returnCart() {

    window.location.href="/static-front/html/shoes-cart.htm";

}

/**
 *  订单页面的渲染
 * @param OrderItemArr
 */
function renderPayPage(orderItemArr){

    if(!orderItemArr){
        $("#order-container").html("");
        return;
    }
    orderItemList = orderItemArr;
    //console.log(orderItemArr);
    //
    let totalPrice = 0;
    let str = "";
    $.each(orderItemArr,function (i) {
        str += doRenderPayPage(orderItemArr[i]);
        totalPrice += (Number(orderItemArr[i].price) * Number(orderItemArr[i].count));
        if(orderItemArr[i].rushbuyid){
            $("#rushbuyid-order").val(orderItemArr[i].rushbuyid);
        }
    });
    orderItemArr = null;
    $("#orderListBody").html(str);
    // 订单总额的渲染
    $("#orderTotal").html(totalPrice);
    renderOrderCart(); //  渲染订单卡片

}

/**
 *  订单卡片的数据渲染
 */
function renderOrderCart() {
    // 订单地址的渲染
    // 选中的地址
    let selectAddr = $($_Class("order-addr-li","addr-seleted")[0]);

    // 获取省份
    let province = selectAddr.children(".li-order-province").html();
    let city = selectAddr.children(".li-order-city").html();
    let addrDetail = selectAddr.children(".li-order-detail-addr").html();
    let contactName = selectAddr.children(".li-order-contactName").html();
    let contactPhone = selectAddr.children(".li-order-contactPhone").html();

    $("#order-province").html(province);
    $("#order-city").html(city);
    $("#order-addr").html(addrDetail);

    $("#contactName").html(contactName);
    $("#contactPhone").html(contactPhone);

    if($("#rushbuyid-order").val() == -1){
        $("#pay-hand-order").html("<a href=\"javascript:;\" id=\"order-caculate-do\" onclick=\"handOrder()\">提交订单</a>");
    }else {
        $("#pay-hand-order-rush").html("<a href=\"javascript:;\" id=\"order-caculate-do-rush\" onclick=\"handRushOrder()\">开始抢购</a>");
    }
}

function doRenderPayPage(data) {
    console.log("jaige:"+data.price);
    console.log("shuliang:"+data.count);

    let totalPrice = Number(data.price) * Number(data.count);
    orderTotal += totalPrice;
    let str = '<tr class="cart-list-line"></tr>'
        +'<tr class="cart-list-tr not-selected order-item">'
        +'<td>'
        +'<img src="'+data.colorPic+'" style="width: 80px;height: 80px;">'
        +'<a class="cart-shoes-name" href="javascript:;">'+ data.shoesName +'</a>'
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

    //console.log(str);

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
function OrderItem(colorPic,shoesName, color, size, skuid,count,price,rushbuyid) {

    this.colorPic = colorPic;
    this.shoesName = shoesName;
    this.color = color;
    this.size = size;
    this.skuid = skuid;
    this.count = count;
    this.price = price;
    this.rushbuyid = rushbuyid;
}
// 地址对象
function Addr(province, city,deatailAddr) {
    this.province = province;
    this.city = city;
    this.deatailAddr = deatailAddr;
}
/**
 *  返回地址的字符串
 */
Addr.prototype.addrString = function (){
    return this.province + this.city  + this.deatailAddr;
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
function OrderHandItem(skuid, quantity) {
    this.skuid = skuid;
    this.quantity = quantity;
}

/**
 *  显示加载中的
 */
function showLoading(){
    layer.msg('订单创建中。。。', {
        icon: 16
        ,shade: 0.01
    });
}
function closeAll() {
    layer.closeAll('loading');
}


/**
 *  提交订单
 * @param ShoesOrder
 */
function handOrder() {

    if($("#order-caculate-do").hasClass("reapClick")){
        alert("请勿重复提交订单！");
        closeAll();
        return;
    }
    // 组装数据  地址数据
    let addr = $("#order-province").html() + $("#order-city").html()+$("#order-addr").html();
    let contactName =  $("#contactName").html();
    let contactPhone = $("#contactPhone").html();

    let remark = $("#remarkTextArea").val();

    //orderListBody,cart-list-tr

    if(!orderItemList){
        alert("非法请求！");
        return;
    }

    showLoading();

    let orderItemArr = new Array();
    $.each(orderItemList, function (i) {
        let orderHandItem = new OrderHandItem();
        orderHandItem.skuid = orderItemList[i].skuid;
        orderHandItem.quantity = orderItemList[i].count;
        orderItemArr[i] = orderHandItem;
    });

    let token = getToken();

    console.log(orderItemArr);
    console.log("---------------");
    // 开始提交订单
    $.ajax({
        url: "/order/add-test",
        type:"POST",
        data: {
            "skuidandqty": JSON.stringify(orderItemArr),
            "contactPhone":contactPhone,
            "contactName":contactName,
            "remark":remark,
            "receiptaddress":addr,
            "token":token
        },
        beforeSend: function () {
            //$("loading").show();
            $("#order-caculate-do").addClass("reapClick");
        },
        success:function (data) {
            if(data.code  == 0){
                //alert("下单成功");
                $("#order-container").html("");
                closeAll();
                layer.msg('订单创建成功！立即支付？', {
                    time: 0 //不自动关闭
                    ,btn: ['确定', '稍后支付']
                    ,yes: function(index){
                        layer.close(index);
                        //alert("跳转成功！");
                        orderItemList = null;
                        window.location.href="/static-front/html/shoes-trace.htm?orderId="+data.data;
                    }
                    ,btn2: function(index){
                        alert("跳转至我的订单！");
                        //return false 开启该代码可禁止点击该按钮关闭
                    }
                });

            }
            else {
                $("#order-caculate-do").removeClass("reapClick");
            }

        },
        error: function () {
            $("#order-caculate-do").removeClass("reapClick");
        }
    });

}

/**
 *  开始抢购
 * @param handRushOrder()
 */
function handRushOrder(){


    if($("#order-caculate-do-rush").hasClass("reapClick")){
        alert("请勿重复提交订单！");
        closeAll();
        return;
    }
    // 组装数据  地址数据
    let addr = $("#order-province").html() + $("#order-city").html()+$("#order-addr").html();
    let contactName =  $("#contactName").html();
    let contactPhone = $("#contactPhone").html();

    let remark = $("#remarkTextArea").val();

    //orderListBody,cart-list-tr

    if(!orderItemList){
        alert("非法请求！");
        return;
    }

    showLoading();

    let orderItemArr = new Array();
    $.each(orderItemList, function (i) {
        let orderHandItem = new OrderHandItem();
        orderHandItem.skuid = orderItemList[i].skuid;
        orderHandItem.quantity = orderItemList[i].count;
        orderItemArr[i] = orderHandItem;
    });

    let token = getToken();

    console.log(orderItemArr);
    console.log("---------------");
    // 开始提交订单
    $.ajax({
        url: "/RushOrder/CreateRushOrder",
        type:"POST",
        data: {
            "skuidandqty": JSON.stringify(orderItemArr),
            "contactPhone":contactPhone,
            "contactName":contactName,
            "remark":remark,
            "receiptaddress":addr,
            "rushbuyid":rushbuyid,
            "token":token
        },
        beforeSend: function () {
            //$("loading").show();
            $("#order-caculate-do-rush").addClass("reapClick");
        },
        success:function (data) {
            if(data.code  == 0){
                //alert("下单成功");
                $("#order-container").html("");
                closeAll();
                layer.msg('订单创建成功！立即支付？', {
                    time: 0 //不自动关闭
                    ,btn: ['确定', '稍后支付']
                    ,yes: function(index){
                        layer.close(index);
                        //alert("跳转成功！");
                        orderItemList = null;
                        window.location.href="/static-front/html/shoes-trace.htm?orderId="+data.data;
                    }
                    ,btn2: function(index){
                        alert("跳转至我的订单！");
                        //return false 开启该代码可禁止点击该按钮关闭
                    }
                });

            }
            else {
                $("#order-caculate-do-rush").removeClass("reapClick");
            }

        },
        error: function () {
            $("#order-caculate-do-rush").removeClass("reapClick");
        }
    });

}

function getUserId() {
    let token = getCookie("token");
    if(token === ""){
        var r=confirm("您还没登录，请先登录！");
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



