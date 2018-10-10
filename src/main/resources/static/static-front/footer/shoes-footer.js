$(function () {
    loadShoesFooter();
})
function loadShoesFooter() {
    var tmp = "<!--尾部信息-->\n" +
        "<div class=\"footer-top-area\" style=\"margin-top: 60px;\">\n" +
        "    <div class=\"container\">\n" +
        "        <div class=\"row\">\n" +
        "            <div class=\"col-md-3 col-sm-4\">\n" +
        "                <div class=\"footer-contact\">\n" +
        "                    <img src=\"../static-front/img/logo_4.png\" alt=\"\">\n" +
        "                    <p align=\"center\">101Shoes，买到就是赚到</p>\n" +
        "                    <ul class=\"address\">\n" +
        "\n" +
        "                        <li>\n" +
        "                            <span class=\"fa fa-phone\"></span> (000) 12580\n" +
        "                        </li>\n" +
        "                        <li>\n" +
        "                            <span class=\"fa fa-envelope-o\"></span> 1048900106@qq.com\n" +
        "                        </li>\n" +
        "                    </ul>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "            <div class=\"col-md-3 hidden-sm\">\n" +
        "                <div class=\"footer-tweets\">\n" +
        "                    <div class=\"footer-title\">\n" +
        "                        <h3>我们的话</h3>\n" +
        "                    </div>\n" +
        "                    <div class=\"twitter-feed\">\n" +
        "                        <div class=\"twitter-article\">\n" +
        "                            <div class=\"twitter-img\">\n" +
        "                                <a href=\"#\">\n" +
        "                                    <img src=\"../static-front/img/twitter-1.png\" alt=\"\">\n" +
        "                                </a>\n" +
        "                            </div>\n" +
        "                            <div class=\"twitter-text\">\n" +
        "                                <p>101shoes</p>\n" +
        "                                <a href=\"#\">http://www.101shoes.com</a>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                        <div class=\"twitter-article\">\n" +
        "                            <div class=\"twitter-img\">\n" +
        "                                <a href=\"#\">\n" +
        "                                    <img src=\"../static-front/img/twitter-1.png\" alt=\"\">\n" +
        "                                </a>\n" +
        "                            </div>\n" +
        "                            <div class=\"twitter-text\">\n" +
        "                                <p>101shoes</p>\n" +
        "                                <a href=\"#\">http://www.101shoes.com</a>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "            <div class=\"col-md-3 col-sm-4\">\n" +
        "                <div class=\"footer-support\">\n" +
        "                    <div class=\"footer-title\">\n" +
        "                        <h3>我们的支持</h3>\n" +
        "                    </div>\n" +
        "                    <div class=\"footer-menu\">\n" +
        "                        <ul>\n" +
        "                            <li>\n" +
        "                                <a href=\"#\">您的账户</a>\n" +
        "                            </li>\n" +
        "                            <li>\n" +
        "                                <a href=\"#\">隐私政策</a>\n" +
        "                            </li>\n" +
        "                            <li>\n" +
        "                                <a href=\"#\">联系我们</a>\n" +
        "                            </li>\n" +
        "                        </ul>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "            <div class=\"col-md-3 col-sm-4\">\n" +
        "                <div class=\"footer-info\">\n" +
        "                    <div class=\"footer-title\">\n" +
        "                        <h3>我们的信息</h3>\n" +
        "                    </div>\n" +
        "                    <div class=\"footer-menu\">\n" +
        "                        <ul>\n" +
        "                            <li>\n" +
        "                                <a href=\"\">关于我们</a>\n" +
        "                            </li>\n" +
        "                            <li>\n" +
        "                                <a href=\"#\">客户服务</a>\n" +
        "                            </li>\n" +
        "                            <li>\n" +
        "                                <a href=\"#\">隐私政策</a>\n" +
        "                            </li>\n" +
        "                        </ul>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "        </div>\n" +
        "    </div>\n" +
        "</div>\n" +
        "\n" +
        "<!--版权信息-->\n" +
        "    <div class=\"container\">\n" +
        "        <div class=\"row\">\n" +
        "            <div class=\"footer-copyright\" align=\"center\">\n" +
        "                <p>Copyright &copy; 2018\n" +
        "                    <a href=\"#\"> 山寨平台</a>\n" +
        "                </p>\n" +
        "            </div>\n" +
        "        </div>\n" +
        "    </div>\n" +
        "    <!--返回顶层-->\n" +
        "    <a href=\"#\" id=\"scrollUp\"><i class=\"fa fa fa-arrow-up\"></i></a>";
    $("#the_footer").html(tmp);
}