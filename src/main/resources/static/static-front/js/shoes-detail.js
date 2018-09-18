/*
* 详情页的处理
* */


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