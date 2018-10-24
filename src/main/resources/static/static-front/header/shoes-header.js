Vue.component('header-user',{
    template:'<!--用户栏目-->\n' +
        '        <div class="container">\n' +
        '            <div class="row">\n' +
        '                <div class="col-md-7 col-md-offset-3">\n' +
        '                    <div class="call-support">\n' +
        '                        <p id="login-tip" style="font-size: 15px;">101Shoes 欢迎您，\n' +
        '                            <!-- 如果没有登录显示这个信息 -->\n' +
        '                            <!--<s style="text-decoration: none">请先\n' +
        '                                <a id="login-link" href="#">登陆</a>\n' +
        '                            </s>-->\n' +
        '                            <!-- 登录成功的话显示这个 -->\n' +
        '                            <b>隔壁老王</b>\n' +
        '                        </p>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '                <div class="col-md-2 col-sm-3">\n' +
        '                    <div class="dashboard">\n' +
        '                        <div class="account-menu">\n' +
        '                            <ul>\n' +
        '                                <li>\n' +
        '                                    <a href="#">\n' +
        '                                        <i class="fa fa-user" style="font-size: 20px;"></i>\n' +
        '                                    </a>\n' +
        '                                    <ul>\n' +
        '                                        <li>\n' +
        '                                            <a href="my-account.html">我的订单</a>\n' +
        '                                        </li>\n' +
        '                                        <li>\n' +
        '                                            <a href="wishlist.html">我的信息</a>\n' +
        '                                        </li>\n' +
        '                                        <li>\n' +
        '                                            <a href="checkout.html">退出</a>\n' +
        '                                        </li>\n' +
        '                                    </ul>\n' +
        '                                </li>\n' +
        '                            </ul>\n' +
        '                        </div>\n' +
        '                        <div class="cart-menu">\n' +
        '                            <ul>\n' +
        '                                <li>\n' +
        '                                    <a href="#"><i class="fa fa-shopping-cart" style="font-size: 20px;"></i> <span>2</span> </a>\n' +
        '                                    <div class="cart-info">\n' +
        '                                        <ul>\n' +
        '                                            <li>\n' +
        '                                                <div class="cart-img">\n' +
        '                                                    <img src="../static-front/img/1.png">\n' +
        '                                                </div>\n' +
        '                                                <div class="cart-details">\n' +
        '                                                    <a href="shoes-cart.html">商品1</a>\n' +
        '                                                    <p>1 x $174.00</p>\n' +
        '                                                </div>\n' +
        '                                                <div class="btn-edit"></div>\n' +
        '                                                <div class="btn-remove"></div>\n' +
        '                                            </li>\n' +
        '                                            <li>\n' +
        '                                                <div class="cart-img">\n' +
        '                                                    <img src="../static-front/img/2.png" alt="">\n' +
        '                                                </div>\n' +
        '                                                <div class="cart-details">\n' +
        '                                                    <a href="shoes-cart.html">商品2</a>\n' +
        '                                                    <p>1 x $777.00</p>\n' +
        '                                                </div>\n' +
        '                                                <div class="btn-edit"></div>\n' +
        '                                                <div class="btn-remove"></div>\n' +
        '                                            </li>\n' +
        '                                        </ul>\n' +
        '                                        <h3>小计: <span> $951.00</span></h3>\n' +
        '                                        <a href="checkout.html" class="checkout">查看</a>\n' +
        '                                    </div>\n' +
        '                                </li>\n' +
        '                            </ul>\n' +
        '                        </div>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>'
});
Vue.component('header-nav',{
    template: '<!--商城导航-->\n' +
        '        <div class="mainmenu-area product-items">\n' +
        '            <div class="container">\n' +
        '                <div class="row">\n' +
        '                    <div class="col-md-3">\n' +
        '                        <div class="logo">\n' +
        '                            <a href="index.html">\n' +
        '                                <img src="../static-front/images/newlogo.png" alt="">\n' +
        '                            </a>\n' +
        '                        </div>\n' +
        '                    </div>\n' +
        '                    <div class="col-md-9">\n' +
        '                        <div class="mainmenu">\n' +
        '                            <nav>\n' +
        '                                <ul>\n' +
        '                                    <li>\n' +
        '                                        <a href="index.html">101Shoes</a>\n' +
        '                                    </li>\n' +
        '                                    <li>\n' +
        '                                        <a href="shop.html">男鞋</a>\n' +
        '                                        <div class="mega-menu" id="men_sort">\n' +
        '                                            <ul>\n' +
        '                                                <li class="col-md-12">\n' +
        '                                                    <span><a class="subtitle" style="padding-top: 18px;font-size: 15px;" href="javascript:void(0);" >x</a></span>\n' +
        '                                                        <div class="row">\n' +
        '                                                            <div class="col-md-2">\n' +
        '                                                                <span><a style="padding-top: 5px;" href="javascript:void(0);" >x</a></span>\n' +
        '                                                            </div>\n' +
        '                                                        </div>\n' +
        '                                                </li>\n' +
        '                                            </ul>\n' +
        '                                        </div>\n' +
        '                                    </li>\n' +
        '                                    <li>\n' +
        '                                        <a href="shop.html">女鞋</a>\n' +
        '                                        <div class="mega-menu" id="women_sort">\n' +
        '                                            <ul >\n' +
        '                                                <li class="col-md-12"><span><a class="subtitle" style="padding-top: 18px;font-size: 15px;" href="javascript:void(0);" >x</a></span>\n' +
        '                                                    <div class="row">\n' +
        '                                                        <div class="col-md-2" >\n' +
        '                                                            <span><a  style="padding-top: 5px;" href="javascript:void(0);" >x</a></span>\n' +
        '                                                        </div>\n' +
        '                                                    </div>\n' +
        '                                                </li>\n' +
        '                                            </ul>\n' +
        '                                        </div>\n' +
        '                                    </li>\n' +
        '                                    <li>\n' +
        '                                        <a href="shop.html">品牌</a>\n' +
        '                                        <div class="mega-menu">\n' +
        '                                            <ul id="theBrand">\n' +
        '                                                <li class="col-md-12" >\n' +
        '                                                    <div class="row">\n' +
        '                                                        <div >\n' +
        '                                                            <div class="col-md-2">\n' +
        '                                                                <span><a href="#" style="padding-top: 10px;">x</a></span>\n' +
        '                                                            </div>\n' +
        '                                                        </div>\n' +
        '                                                    </div>\n' +
        '                                                </li>\n' +
        '                                            </ul>\n' +
        '                                        </div>\n' +
        '                                    </li>\n' +
        '                                    <li class="mega-jewellery">\n' +
        '                                        <a href="javascript:;" onclick="carryRush()">疯狂抢购</a>\n' +
        '                                    </li>\n' +
        '                                    <li class="search">\n' +
        '                                        <input type="text" class="myinput" placeholder="搜索  运动鞋/休闲鞋">\n' +
        '                                        <button class="mybutton" title="搜索" ><i class="fa fa-search myicon"></i></button>\n' +
        '                                    </li>\n' +
        '                                </ul>\n' +
        '                            </nav>\n' +
        '                        </div>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>',

       /*data: function(){
            return{
                lists:[]
            }
        },*/
    ready: function(){
        /*this.getNav();*/
        /!*this.getBrands();*!/
    },
    /*methods:{
        getNav: function () {

            let catalogName = [];
            catalogName[0] = "男鞋";
            catalogName[1] = "女鞋";
            console.log( "hji");
            $.ajax({
                url: "../header/getCatalogInfo/2",
                type: 'post',
                data: {
                    catalogNameInfo: JSON.stringify(catalogName),
                    parentId: 0,
                },
                dataType: 'json',
                success: function (result){
                    console.log(result.data);
        },
                error: function (err) {
                    console.log(err);
                }
            })
        },*/
        /*getBrands: function () {

        }*/
});

$(document).ready(function () {
    getNav();
    getBrands();
});
let sorts_1, sorts_2, brands;
sorts_1 = new Vue({
    el: '#men_sort',
    data: {
        lists:[],
    },
    method:{
    }
});
sorts_2 = new Vue({
    el: '#women_sort',
    data: {
        lists:[],
    },
    method:{
        toShoesList: function(catalogid){
            showShoesLists(catalogid);
        }
    }
});
brands = new Vue({
    el: '#theBrand',
    data: {
        lists:[]
    },
    method:{
    }
});

/!*获取品牌数据*!/
function getBrands() {
    $.ajax({
        url: "../header/getBrandInfo",
        type: 'post',
        success: function (result) {
            brands.lists = result.data;
            console.log(result.data);
        },
        error: function (err) {
            console.log(err);
        }
    })
}

/!*获取导航分类数据*!/
function getNav() {
    let catalogName = [];
    catalogName[0] = "男鞋";
    catalogName[1] = "女鞋";
    /!*console.log( "hji");*!/
    $.ajax({
        url: "../header/getCatalogInfo/2",
        type: 'post',
        data: {
            catalogNameInfo: JSON.stringify(catalogName),
            parentId: 0,
        },
        dataType: 'json',
        success: function (result){
            loadNavData(result.data);
        },
        error: function (err) {
            console.log(err);
        }
    })
}

/!*区分开男女鞋的数据*!/
function loadNavData(data) {
    sorts_1.lists = data.男鞋;
    sorts_2.lists = data.女鞋;
    /!*console.log(sorts_2.lists);*!/
}

/!*传类别id跳转至商品页*!/
function showShoesLists(catalogId) {
    let theId = catalogId;
    $.ajax({
        url: "",
        type: 'post',
        data: { catalogId:theId },
        dataType: 'json',
        success: function () {
            console.log("ok");
        },
        error: function (err) {
            console.log(err);
        }
    })
}
