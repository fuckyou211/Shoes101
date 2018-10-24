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

    //console.log(data);

    data = JSON.parse(data);

    let aSizeList = data.sizelist; // 鞋子号码集合
    let aColorPicAndColor = data.colorpicandcolor; // 鞋子颜色和图片的集合
    let details = eval(data.details); // 鞋子的详情

    let bigPic = eval(data.bigpics);      // 大图
    let properties = eval(data.propertys);        // 属性参数渲染

    //console.log("大图："+bigPic);
    //console.log("shds图："+properties);

    // 品牌
    ShoesPropertiesHandle(properties,"arg-shoes");

    ShoesImgShowHandle(bigPic,"big-img-show","detail-shoes-simg");   // 图片渲染

    // setList
    setSizeList(aSizeList, "detail-sizeList");

    // setColor
    setColorList(aColorPicAndColor,"detail-color-dl");

    // 渲染，
    $("#shoes-id").html(details.shoesid);   //  鞋子id
    $("#shoes-price").html(details.price);  // 鞋子价格
    $("#ticket-price").html(details.ticketprice);   // 折扣价
    $("#shoes-name").html(details.shoesname);   // 名字
    $("#shoes-label").html(details.label);
    $("#detail-shoes-total").html(details.quantity);
    let str = details.shoesdetails;

    let newStr = str.replace("\\\r\\\n","<br/>");
    $("#shoes-details").html(newStr.substr(1,newStr.length-2));


}

/**
 *  商品参数配置
 * @param data
 * @param idString
 * @constructor
 */
function ShoesPropertiesHandle(data, idString) {

    let idStr="#"+idString;
    let str = "";
    let prefix = "<tr>";
    let suffix = "</tr>";
    $.each(data,function (i) {
        // 如果是每行3个

        str += "<dd style='float: left'><span>"+ data[i].property +"</span>:&nbsp;<span>"+ data[i].propertyvalue +"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</dd>";
    });

    $(idStr).html(str);
}

/**
 *  鞋子图片处理
 * @param data
 * @param bigImgId 大图ID
 * @param miniImgId 缩略图ID
 * @constructor
 */
function ShoesImgShowHandle(data, bigImgId,miniImgId) {
    let bigId = "#"+bigImgId;
    let miniId = "#"+miniImgId;

    let bigStr = '<img id="detail-shoes-bimg" src="'+ data[0]+'"'
        +' class="img-responsive img-thumbnail detail-big-img"/>';

    //console.log(bigStr);

    $(bigId).html(bigStr);

    let miniStr = "";

    $.each(data,function (i) {

        if(i == 0){
            miniStr += '<li><img style="width: 80px;height: 80px;" src="'+ data[i] +'" class="img-responsive img-thumbnail img-disabled img-active"'
                +' onclick=\'$_activeChange("detail-shoes-simg",this,"img-active")\'/>'
                +'</li>';
        }else{
            miniStr += '<li><img style="width: 80px;height: 80px;" src="'+ data[i] +'" class="img-responsive img-thumbnail img-disabled"'
                +' onclick=\'$_activeChange("detail-shoes-simg",this,"img-active")\'/>'
                +'</li>';
        }




    });
    // http://123.207.109.158:9999/images/1539254939277.png
    //console.log("ddd"+miniStr);

    $(miniId).html(miniStr);

}

/**
 *  详情颜色处理
 * @param data
 * @param idString
 */
function setColorList(data, idString) {
    $id="#"+idString;
    let pColorDl = $($id);
    let str = "";
    $.each(data,function (i) {

        str += '<dd class="fl dd-margin no-choose-border" name='+data[i].colorid+' title='+ data[i].color+''
            +' onclick=\'$_activeChange("detail-shoes-color",this,"choose-border")\'>'
            +'<img class="small-show-img" src='+ data[i].colorpic +'>'
            +'</dd>';

    });

    pColorDl.html(str);

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
        str += '<dd class="fl dd-margin number-disabled" name='+data[i].sizeid+' title='+data[i].size+''
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


function packageShoes(type) {
    //封装数据
    // 获取鞋子的id
    let shoes_id =  $($_Id("shoes-id")).html();
    let shoesName = $($_Id("shoes-name")).html();
    let price = $($_Id("ticket-price")).html();
    // 获取当前的价格
    //let now_price= $($_Id("shoes-price")).html();
    // 获取颜色
    let colorId = $($_Class("detail-shoes-color","choose-border")[0]).attr("name");
    let colorPic = $($_Class("detail-shoes-color","choose-border")[0]).children(".small-show-img").attr("src");
    let color = $($_Class("detail-shoes-color","choose-border")[0]).attr("title");
    let skuId = $("#shoes-skuid").val();        // skuid

    if((!colorId) && (type == 1)){
        alert("请选择鞋子颜色！");
        return;
    }


    // 获取鞋码
    let sizeId = $($_Class("detail-shoes-size","number-active")[0]).attr("name");
    let size = $($_Class("detail-shoes-size","number-active")[0]).attr("title");

    if(!sizeId){
        alert("请选择鞋子尺码！");
        return;
    }

    // 获取数量
    let shoes_count= $.trim($($_Id("detail-shoes-count")).html());

    // console.log(shoes_id+";"+now_price+";"+shoes_color+";"+shoes_size);

    // let data={
    //     "id":shoes_id,
    //     "color":shoes_color,
    //     "size":shoes_size,
    //     "count":shoes_count,
    //     toString : function () {
    //         return "id:"+this.id +"color:"+this.color+"size:"+this.size+"count:"+this.count;
    //     }
    // };

    let orderItem = new OrderItem(colorPic,shoesName,color,size,skuId,shoes_count,price);

    return orderItem;
}


function doPayNow(type) {
    let token = getToken();
    //封装数据
    //alert('下单成功！');
    let orderItem = packageShoes(type);
    let rushbuyid = 0;

    if(!orderItem){
        return;
    }

    if($("#detail-shoes-count").html() == 0){
        alert("请选择数量购买的数量");
        return;
    }

    console.log("下单的数据："+orderItem);

    let orderItemArr = new Array(orderItem);

    //orderItemArr[orderItemArr.length] = orderItem;

    if(type == 2){
        rushbuyid = getQueryPathStringByName(rushbuyid);
        dumpToPayPage(orderItemArr,token,type,rushbuyid);
    }else {
        dumpToPayPage(orderItemArr,token,type);
    }



    //跳转到购物车页面
    //window.location.href="./shoes-pay.html";

}