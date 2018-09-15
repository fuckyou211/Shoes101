/*
* 详情页的处理
* */


/**
 * 大图转换处理
 * @param imgUrl
 * @constructor
 */
function BigShoesImgChange(imgUrl) {
    var bigImg = $($_Id('detail-shoes-bimg'));

    bigImg.attr('src',imgUrl);
}

function doAddCart() {
    //封装数据
    alert('加入购物成功！');

    //跳转到购物车页面
    window.location.href="./shoes-cart.html";



}

function doPayNow() {
    //封装数据
    alert('下单成功！');

    //跳转到购物车页面
    window.location.href="./shoes-pay.html";


}