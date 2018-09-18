function returnDetail() {

    // 跳转到详情页

    // 获取id
    let shoes_id = $($_Id("shoes-id")).html();

    //window.location.href="/shoes/"+shoes_id;


    console.log("鞋子id:"+shoes_id);

    window.location.href= "./shoes-detail.html";

}

function toCartList() {

    // 隐藏提示信息
    $($_Id("cart-tip-div")).css("display","none");
    // 显示购物车列表
    $($_Id("cart-list-div")).show();



}