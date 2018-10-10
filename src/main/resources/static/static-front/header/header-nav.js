$(document).ready(function () {
    getNav();
    getBrands();
});
/!*获取品牌数据*!/
function getBrands() {
    $.ajax({
        url: "../header/getBrandInfo",
        type: 'post',
        success: function (result) {
            console.log("品牌获取");
            console.log(result.data);
            loadBrands(result.data);
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
    console.log( "进入获取分类数据函数");
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

var sta_1="<div class=\"mainmenu-area product-items\">\n" +
    "            <div class=\"container\">\n" +
    "                <div class=\"row\">\n" +
    "                    <div class=\"col-md-3\">\n" +
    "                        <div class=\"logo\">\n" +
    "                            <a href=\"index.html\">\n" +
    "                                <img src=\"../static-front/images/newlogo.png\" alt=\"\">\n" +
    "                            </a>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                    <div class=\"col-md-9\">\n" +
    "                        <div class=\"mainmenu\">\n" +
    "                            <nav>\n" +
    "                                <ul>\n" +
    "                                    <li>\n" +
    "                                        <a href=\"index.html\">101Shoes</a>\n" +
    "                                    </li>\n" +
    "                                    <li>\n" +
    "                                        <a href=\"shop.html\">男鞋</a>\n" +
    "                                        <div class=\"mega-menu\" id=\"men_sort\">" +
    "                                           <ul>";
var sta_2 = "</ul>\n" +
    "                                        </div>\n" +
    "                                    </li>\n" +
    "                                    <li>\n" +
    "                                        <a href=\"shop.html\">女鞋</a>\n" +
    "                                        <div class=\"mega-menu\" id=\"women_sort\">\n" +
    "                                            <ul>";
var sta_3 = "</ul>\n" +
    "                                        </div>\n" +
    "                                    </li>\n" +
    "                                    <li>\n" +
    "                                        <a href=\"shop.html\">品牌</a>\n" +
    "                                        <div class=\"mega-menu\">\n" +
    "                                            <ul id=\"theBrand\">\n" +
    "                                                <li class=\"col-md-12\" >\n" +
    "                                                    <div class=\"row\">\n" +
    "                                                        <div>";
var sta_4 = "</div>\n" +
    "                                                    </div>\n" +
    "                                                </li>\n" +
    "                                            </ul>\n" +
    "                                        </div>\n" +
    "                                    </li>\n" +
    "                                    <li class=\"mega-jewellery\">\n" +
    "                                        <a href=\"shoes-sale.html\">疯狂抢购</a>\n" +
    "                                    </li>\n" +
    "                                    <li class=\"search\">\n" +
    "                                        <input type=\"text\" class=\"myinput\" placeholder=\"搜索  运动鞋/休闲鞋\">\n" +
    "                                        <button class=\"mybutton\" title=\"搜索\" ><i class=\"fa fa-search myicon\"></i></button>\n" +
    "                                    </li>\n" +
    "                                </ul>\n" +
    "                            </nav>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "        </div>";
var sta_6 = "</ul>\n" +
    "                                        </div>\n" +
    "                                    </li>\n" +
    "                                    <li>\n" +
    "                                        <a href=\"shop.html\">品牌</a>\n" +
    "                                        <div class=\"mega-menu\">\n" +
    "                                            <ul id=\"theBrand\">\n" +
    "                                                <li class=\"col-md-12\" >\n" +
    "                                                    <div class=\"row\">\n" +
    "                                                        <div>\n" +
    "                                                            <div class=\"col-md-2\">\n" +
    "                                                                <span><a href=\"#\" style=\"padding-top: 10px;\">xxx</a></span>\n" +
    "                                                            </div>\n" +
    "                                                        </div>\n" +
    "                                                    </div>\n" +
    "                                                </li>\n" +
    "                                            </ul>\n" +
    "                                        </div>\n" +
    "                                    </li>\n" +
    "                                    <li class=\"mega-jewellery\">\n" +
    "                                        <a href=\"shoes-sale.html\">疯狂抢购</a>\n" +
    "                                    </li>\n" +
    "                                    <li class=\"search\">\n" +
    "                                        <input type=\"text\" class=\"myinput\" placeholder=\"搜索  运动鞋/休闲鞋\">\n" +
    "                                        <button class=\"mybutton\" title=\"搜索\" ><i class=\"fa fa-search myicon\"></i></button>\n" +
    "                                    </li>\n" +
    "                                </ul>\n" +
    "                            </nav>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "        </div>";
var inner_1 = "";
var inner_2 = "";
var inner_3 = "";
var inner_all = "";
var tmp_1 = "";
var tmp_2 = "";
var tmp_3 = "";
var tmp_4 = "";
var tmp_5 = "";
var tmp_6 = "";
var tmp_7 = "";

function loadBrands(data) {
    $.each(data,function (index,item) {
        tmp_7 = "<div class=\"col-md-2\">\n" +
            "       <span><a href=\"#\" style=\"padding-top: 10px;\">"+item.propertyvalue+"</a></span>\n" +
            "    </div>"
        inner_3 += tmp_7;
    })
}
function loadNavData(data) {
    /*let menShoesData = data.男鞋;
    let womenShoesData = data.女鞋;*/
    console.log("男女鞋数据区分");
    console.log(data.男鞋);
    console.log(data.女鞋);

    $.each(data.男鞋,function (index,item) {
        tmp_1 = "<li class=\"col-md-12\">\n" +
            "      <span><a class=\"subtitle\" style=\"padding-top: 18px;font-size: 16px;\" href=\"javascript:void(0);\" @click=\"\">"+item.shoescatalog.catalogname+"</a></span>\n" +
            "      <div class=\"row\">\n"
        tmp_2 = "";
        $.each(item.childMapData.childList,function (index,items) {
            tmp_2 += "<div class=\"col-md-2\">\n"+
                "       <span><a style=\"padding-top: 5px;\" href=\"javascript:void(0);\" @click=\"\">"+items.shoescatalog.catalogname+"</a></span>\n"+
                "     </div>"
        });

        tmp_3 = "       </div>\n" +
            "   </li>"

        inner_1 += tmp_1+tmp_2+tmp_3;
    });
    $.each(data.女鞋,function (index,item) {
        tmp_4 = "<li class=\"col-md-12\">\n" +
            "      <span><a class=\"subtitle\" style=\"padding-top: 18px;font-size: 16px;\" href=\"javascript:void(0);\" @click=\"\">"+item.shoescatalog.catalogname+"</a></span>\n" +
            "      <div class=\"row\">\n"
        tmp_5 = "";
        $.each(item.childMapData.childList,function (index,items) {
            tmp_5 += "<div class=\"col-md-2\">\n"+
                "       <span><a style=\"padding-top: 5px;\" href=\"javascript:void(0);\" @click=\"\">"+items.shoescatalog.catalogname+"</a></span>\n"+
                "     </div>"
        });

        tmp_6 = "       </div>\n" +
            "   </li>"

        inner_2 += tmp_4+tmp_5+tmp_6;
    });
    inner_all = sta_1+inner_1+sta_2+inner_2+sta_3+inner_3+sta_4;
    $("#header-nav").html(inner_all);
}
