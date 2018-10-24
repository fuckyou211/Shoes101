
Vue.component('user-header',{
    template:'<div class="container">\n' +
        '            <div class="row">\n' +
        '                <div class="col-md-7 col-md-offset-3">\n' +
        '                    <div class="call-support">\n' +
        '                        <p id="login-tip" style="font-size: 15px;">101Shoes 欢迎您，\n' +
        '                            <span v-if="online"><b>{{user.username}}</b></span>\n' +
        '                            <span v-else> \n' +
        '                                <s style="text-decoration: none">请先\n' +
        '                                   <a id="login-link" href="http://localhost:8080/login/to_login">登陆</a>\n' +
        '                                </s>\n' +
        '                             </span>\n'+
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
        '                                    <ul v-if="online">\n' +
        '                                        <li>\n' +
        '                                            <a href="http://localhost:8080/UserInformation/UserMyAccount">我的订单</a>\n' +
        '                                        </li>\n' +
        '                                        <li>\n' +
        '                                            <a href="wishlist.html">我的信息</a>\n' +
        '                                        </li>\n' +
        '                                        <li>\n' +
        '                                            <a href="javacript:;" onclick=\"logout()\">退出</a>\n' +
        '                                        </li>\n' +
        '                                    </ul>\n' +
        '                                    <ul v-else>\n' +
        '                                        <li>\n' +
        '                                            <a href="http://localhost:8080/login/to_login">登录</a>\n' +
        '                                        </li>\n' +
        '                                        <li>\n' +
        '                                            <a href="http://localhost:8080/login/to_login">注册</a>\n' +
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
        '        </div>',
    data:function(){
        return{
            user:[],//用户信息
            online:false,//是否登录
            shopcart:[]
        }
    },

    
    created:function () {
        var self= this;
        //通过ajax获取用户，如返回空，则是未登录
        $.ajax({
            url: "http://localhost:8080/getUser",
            type: 'post',
            dataType: 'json',
            success: function (data) {
                //var result = JSON.parse(data.data);
                console.log("token:= ");
                console.log(data);
                if(data!=null&&data!=""){
                    self.online = true;
                    self.user=data;
                }

            },
            fail: function (err) {
                console.log("失败："+err)
            }
        });

    },
    methods:{
        changes:function () {

        }
    }
    
});

$(function(){
    $("#header-user").html('<user-header></user-header>');
    new Vue({el:"#header-user"});
});


function logout() {
    $.ajax({
       url:"/login/logout",
       type:"GET",
       success: function (data) {
           if(data.code == 0){
               window.location.href="/";
           }
       },
        error: function () {
            alert("请求失败！");
        }
    });
}

var cart = {"detail":[{"shoesname":"123","pic":"dfngj/dfh/sss.jgp","quantity":10,"price":25.3}],
             "sctotalprice":1230}