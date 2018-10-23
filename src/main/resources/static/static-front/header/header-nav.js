$(document).ready(function () {
    getNav();
    getBrands();
});

var sta_1="<div class=\"mainmenu-area product-items\">\n" +
    "            <div class=\"container\">\n" +
    "                <div class=\"row\">\n" +
    "                    <div class=\"col-md-3\">\n" +
    "                        <div class=\"logo\">\n" +
    "                            <a href=\"http://localhost:8080\">\n" +
    "                                <img src=\"../static-front/images/newlogo.png\" alt=\"\">\n" +
    "                            </a>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                    <div class=\"col-md-9\">\n" +
    "                        <div class=\"mainmenu\">\n" +
    "                            <nav>\n" +
    "                                <ul>\n" +
    "                                    <li>\n" +
    "                                        <a href=\"http://localhost:8080\">101Shoes</a>\n" +
    "                                    </li>\n" +
    "                                    <li>\n" +
    "                                        <a href=\"javascript:;\" value=\"1\" onclick=\"toShoesList(this)\">男鞋</a>\n" +
    "                                        <div class=\"mega-menu\" id=\"men_sort\">" +
    "                                           <ul>";
var sta_2 = "</ul>\n" +
    "                                        </div>\n" +
    "                                    </li>\n" +
    "                                    <li>\n" +
    "                                        <a href=\"javascript:;\" value=\"1\" onclick=\"toShoesList(this)\">女鞋</a>\n" +
    "                                        <div class=\"mega-menu\" id=\"women_sort\">\n" +
    "                                            <ul>";
var sta_3 = "</ul>\n" +
    "                                        </div>\n" +
    "                                    </li>\n" +
    "                                    <li>\n" +
    "                                        <a style=\"cursor:pointer\">品牌</a>\n" +
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
    "                                        <input name=\"searchContent\" type=\"text\" class=\"myinput\" placeholder=\"搜索  运动鞋/休闲鞋\">\n" +
    "                                        <button type=\"button\" class=\"mybutton\" title=\"搜索\" ><i class=\"fa fa-search\" onclick=\"theSearch();\"></i></button>\n" +
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
/*加载品牌导航*/
function loadBrands(data) {
    $.each(data,function (index,item) {
        tmp_7 = "<div class=\"col-md-2\">\n" +
            "       <span><a style=\"padding-top: 10px;\" href=\"javascript:;\" value=\""+item.propertyvalueid+"\" onclick=\"brandsToNext(this);\" >"+item.propertyvalue+"</a></span>\n" +
            "    </div>"
        inner_3 += tmp_7;
    })
}
/*加载男女鞋分类导航*/
function loadNavData(data) {
    /*let menShoesData = data.男鞋;
    let womenShoesData = data.女鞋;*/
    console.log("男女鞋数据区分");
    console.log(data.男鞋);
    console.log(data.女鞋);

    $.each(data.男鞋,function (index,item) {
        tmp_1 = "<li class=\"col-md-12\">\n" +
            "      <span><a class=\"subtitle\" style=\"padding-top: 18px;font-size: 16px;\" href=\"javascript:;\" value=\""+item.shoescatalog.catalogid+"\" onclick=\"toShoesList(this)\">"+item.shoescatalog.catalogname+"</a></span>\n" +
            "      <div class=\"row\">\n"
        tmp_2 = "";
        $.each(item.childMapData.childList,function (index,items) {
            tmp_2 += "<div class=\"col-md-2\">\n"+
                "       <span><a style=\"padding-top: 5px;\" href=\"javascript:;\" value=\""+items.shoescatalog.catalogid+"\" onclick=\"toShoesList(this)\">"+items.shoescatalog.catalogname+"</a></span>\n"+
                "     </div>"
        });

        tmp_3 = "       </div>\n" +
            "   </li>"

        inner_1 += tmp_1+tmp_2+tmp_3;
    });
    $.each(data.女鞋,function (index,item) {
        tmp_4 = "<li class=\"col-md-12\">\n" +
            "      <span><a class=\"subtitle\" style=\"padding-top: 18px;font-size: 16px;\" href=\"javascript:;\" value=\""+item.shoescatalog.catalogid+"\" onclick=\"toShoesList(this)\">"+item.shoescatalog.catalogname+"</a></span>\n" +
            "      <div class=\"row\">\n"
        tmp_5 = "";
        $.each(item.childMapData.childList,function (index,items) {
            tmp_5 += "<div class=\"col-md-2\">\n"+
                "       <span><a style=\"padding-top: 5px;\" href=\"javascript:;\" value=\""+items.shoescatalog.catalogid+"\" onclick=\"toShoesList(this)\">"+items.shoescatalog.catalogname+"</a></span>\n"+
                "     </div>"
        });

        tmp_6 = "       </div>\n" +
            "   </li>"

        inner_2 += tmp_4+tmp_5+tmp_6;
    });
    inner_all = sta_1+inner_1+sta_2+inner_2+sta_3+inner_3+sta_4;
    $("#header-nav").html(inner_all);

}
/*品牌跳转*/
function brandsToNext(obj) {
    let theBrandsId = $(obj).attr("value");
    console.log(theBrandsId);
    if(theBrandsId){
        window.location.href="../../ShoesShop/shoes-list?propertyValueId="+theBrandsId;
    }
}
/*分类跳转*/
function toShoesList(obj) {
    let theClassifyId = $(obj).attr("value");
    console.log("导航男女鞋分类：");
    console.log(theClassifyId);
    if(theClassifyId){
        window.location.href="../../ShoesShop/shoes-list?catalogId="+theClassifyId;
    }
}
/*  跳转至抢购页面 */
function carryRush() {
    window.location.href="/rush/getAllRush";
}
/*搜索*/
function theSearch() {
    let theSearchContent = $("input[name='searchContent']").val();
    console.log("搜索");
    console.log(theSearchContent);
    if(theSearchContent){
        window.location.href="../../ShoesShop/Search?value="+theSearchContent;
    }
}
