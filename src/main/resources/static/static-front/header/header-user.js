var tmp = "<div class=\"container\">\n" +
    "            <div class=\"row\">\n" +
    "                <div class=\"col-md-7 col-md-offset-3\">\n" +
    "                    <div class=\"call-support\">\n" +
    "                        <p id=\"login-tip\" style=\"font-size: 15px;\">101Shoes 欢迎您，\n" +
    "                            <!-- 如果没有登录显示这个信息 -->\n" +
    "                            <!--<s style=\"text-decoration: none\">请先\n" +
    "                                <a id=\"login-link\" href=\"#\">登陆</a>\n" +
    "                            </s>-->\n" +
    "                            <!-- 登录成功的话显示这个 -->\n" +
    "                            <b>隔壁老王</b>\n" +
    "                        </p>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "                <div class=\"col-md-2 col-sm-3\">\n" +
    "                    <div class=\"dashboard\">\n" +
    "                        <div class=\"account-menu\">\n" +
    "                            <ul>\n" +
    "                                <li>\n" +
    "                                    <a href=\"#\">\n" +
    "                                        <i class=\"fa fa-user\" style=\"font-size: 20px;\"></i>\n" +
    "                                    </a>\n" +
    "                                    <ul>\n" +
    "                                        <li>\n" +
    "                                            <a href=\"my-account.html\">我的订单</a>\n" +
    "                                        </li>\n" +
    "                                        <li>\n" +
    "                                            <a href=\"wishlist.html\">我的信息</a>\n" +
    "                                        </li>\n" +
    "                                        <li>\n" +
    "                                            <a href=\"checkout.html\">退出</a>\n" +
    "                                        </li>\n" +
    "                                    </ul>\n" +
    "                                </li>\n" +
    "                            </ul>\n" +
    "                        </div>\n" +
    "                        <div class=\"cart-menu\">\n" +
    "                            <ul>\n" +
    "                                <li>\n" +
    "                                    <a href=\"#\"><i class=\"fa fa-shopping-cart\" style=\"font-size: 20px;\"></i> <span>2</span> </a>\n" +
    "                                    <div class=\"cart-info\">\n" +
    "                                        <ul>\n" +
    "                                            <li>\n" +
    "                                                <div class=\"cart-img\">\n" +
    "                                                    <img src=\"../static-front/img/1.png\">\n" +
    "                                                </div>\n" +
    "                                                <div class=\"cart-details\">\n" +
    "                                                    <a href=\"shoes-cart.html\">商品1</a>\n" +
    "                                                    <p>1 x $174.00</p>\n" +
    "                                                </div>\n" +
    "                                                <div class=\"btn-edit\"></div>\n" +
    "                                                <div class=\"btn-remove\"></div>\n" +
    "                                            </li>\n" +
    "                                            <li>\n" +
    "                                                <div class=\"cart-img\">\n" +
    "                                                    <img src=\"../static-front/img/2.png\" alt=\"\">\n" +
    "                                                </div>\n" +
    "                                                <div class=\"cart-details\">\n" +
    "                                                    <a href=\"shoes-cart.html\">商品2</a>\n" +
    "                                                    <p>1 x $777.00</p>\n" +
    "                                                </div>\n" +
    "                                                <div class=\"btn-edit\"></div>\n" +
    "                                                <div class=\"btn-remove\"></div>\n" +
    "                                            </li>\n" +
    "                                        </ul>\n" +
    "                                        <h3>小计: <span> $951.00</span></h3>\n" +
    "                                        <a href=\"checkout.html\" class=\"checkout\">查看</a>\n" +
    "                                    </div>\n" +
    "                                </li>\n" +
    "                            </ul>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "        </div>"
$(function(){
        loadHeaderUser();
})
function loadHeaderUser() {
    $("#header-user").html(tmp);
}
