/*
* 详情页的处理
* */


/**
 *  获取商品详情，根据id
 */

$(function () {
    console.log("detail js 加载！");
});

function getShoesDetail() {
    // 获取请求的路径上的 shoesId
    let shoesId = getQueryPathStringByName("shoesId");

    console.log("鞋子id:"+shoesId);

    // 发送请求请求数据
    $.ajax({
        url: "/goodsf/todetail/"+shoesId,
        type: "GET",
        success: function(data){
            // 如果请求成功的话，开始渲染页面
            if(data.code == 0){
                // 渲染
                renderDetailPage(data.data);

            }else{
                alert(data.msg);
            }

        },
        error: function () {
            alert("页面加载失败！");
        }

    });

}

/**
 * 根据数据渲染页面
 * @param data
 */
function renderDetailPage(data) {

    console.log(data);

    data = JSON.parse(data);

    let aSizeList = data.sizelist; // 鞋子号码集合
    let aColorPicAndColor = data.colorpicandcolor; // 鞋子颜色和图片的集合
    let details = eval(data.details); // 鞋子的详情

    // setList
    setSizeList(aSizeList, "detail-sizeList");

    // setColor
    setColorList(aColorPicAndColor,"");

    // 渲染，
    $("#shoes-id").html(details.shoesid);
    $("#shoes-price").html(details.price);
    $("#ticket-price").html(details.ticketprice);
    $("#shoes-name").html(details.shoesname);
    $("#detail-shoes-total").html(details.quantity);
    let str = details.shoesdetails;

    let newStr = str.replace("\\\r\\\n","<br/>");
    $("#shoes-details").html(newStr.substr(1,newStr.length-2));


}

/**
 *
 * @param data
 * @param idString
 */
function setColorList(data, idString) {
    
}

/**
 *  遍历还原列表数据
 * @param data
 * @param parantClass
 * @param cls
 */
function setSizeList(data, idString) {
    $id="#"+idString;
    let pSizeDl = $($id);

    // 拼接字符串
    let str = "";
    $.each(data,function (i) {
         str += '<dd class="fl dd-margin number-disabled" name='+data[i].sizeId+' title='+data[i].size+''
        +' onclick=\'$_activeChange("detail-shoes-size",this,"number-active")\'>'
            +'<span class="number-of-shoe">'+data[i].size+'</span>'
            +'</dd>';
    });

    pSizeDl.html(str);
}

/**
 * 大图转换处理
 * @param imgUrl
 * @constructor
 */
function BigShoesImgChange(imgUrl) {
    let bigImg = $($_Id('detail-shoes-bimg'));

    bigImg.attr('src',imgUrl);
}

/**
 * 添加到购物车
 */
function doAddCart() {

    let data = packageShoes();

    // alert('加入购物成功！');
    console.log(data);

    // 判断是否已经登录
   // console.log("是否登录："+isLogin());

    if (isLogin()){
        // 已经登录，跳转到购物车页面
        // 封装好数据之后发送ajax请求，post
        $.post('/cart/add', data, function(msg) {
            // 将字符串转成json对象

            if(msg.code == '200'){
                //跳转到购物车页面
                window.location.href="./shoes-cart.html";
            }else{
                alert(msg.message);
            }
        });
    }else {
        console.log(typeof data.toString());

        // 加入cookie
        //localStorage.setItem('shoes',JSON.stringify(data));
         $.cookie("shoes",JSON.stringify(data));
       // window.location.href="./shoes-cart.html";
    }
}


function packageShoes() {
    //封装数据
    // 获取鞋子的id
    let shoes_id =  $($_Id("shoes-id")).html();
    // 获取当前的价格
    let now_price= $($_Id("shoes-price")).html();
    // 获取颜色
    let shoes_color = $($_Class("detail-shoes-color","choose-border")[0]).attr("title");

    // 获取鞋码
    let shoes_size = $($_Class("detail-shoes-size","number-active")[0]).attr("title");

    // 获取数量
    let shoes_count= $.trim($($_Id("detail-shoes-count")).html());

    // console.log(shoes_id+";"+now_price+";"+shoes_color+";"+shoes_size);

    let data={
        "id":shoes_id,
        "price":now_price,
        "color":shoes_color,
        "size":shoes_size,
        "count":shoes_count
    };

    return data;
}


function doPayNow() {
    //封装数据
    alert('下单成功！');

    //跳转到购物车页面
    window.location.href="./shoes-pay.html";


}