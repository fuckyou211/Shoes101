Vue.component('navigate',{
    template:'<nav class="navbar-default navbar-static-side" role="navigation">\n' +
        '            <div class="sidebar-collapse">\n' +
        '                <ul class="nav metismenu" id="side-menu">\n' +
        '                    <li class="nav-header">\n' +
        '                        <div class="dropdown profile-element"> <span>\n' +
        '                            <img alt="image" class="img-circle" src="../img/profile_small.jpg" />\n' +
        '                             </span>\n' +
        '                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">\n' +
        '                            <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">欢迎你！</strong>\n' +
        '                             </span> <span class="text-muted text-xs block">靓仔！<b class="caret"></b></span> </span> </a>\n' +
        '                            <ul class="dropdown-menu animated fadeInRight m-t-xs">\n' +
        '                                <li><a href="profile.html">修改密码</a></li>\n' +
        '                                <li class="divider"></li>\n' +
        '                                <li><a href="login.html">退出</a></li>\n' +
        '                            </ul>\n' +
        '                        </div>\n' +
        '                        <div class="logo-element">\n' +
        '                            IN+\n' +
        '                        </div>\n' +
        '                    </li>\n' +
        '                    <li>\n' +
        '                        <a href="Admin.html"><i class="fa fa-user-circle-o"></i> <span class="nav-label">销售统计</span></a>\n' +
        '                    </li>\n' +
        '                    <li>\n' +
        '                        <a href="../muser/tomuser"><i class="fa fa-user-circle-o"></i> <span class="nav-label">会员管理</span></a>\n' +
        '                    </li>\n' +
        '                    <li >\n' +
        '                        <a href="../shoes/toshoes"><i class="fa fa-bandcamp"></i> <span class="nav-label">商品管理</span></a>\n' +
        '                       \n' +
        '                    </li>\n' +
        '                    <li>\n' +
        '                        <a href="layouts.html"><i class="fa fa-pencil-square-o"></i> <span class="nav-label">订单管理</span><span class="label label-warning pull-right">10</span></a>\n' +
        '                    </li>\n' +
        '                    <li>\n' +
        '                        <a href="mailbox.html"><i class="fa fa-rocket"></i> <span class="nav-label">抢购管理 </span></a>\n' +
        '                    </li>\n' +
        '                   \n' +
        '                    <li>\n' +
        '                        <a href="../../Catalog/tomanagerClassify"><i class="fa fa-rocket"></i> <span class="nav-label">分类管理 </span></a>\n' +
        '                    </li>\n' +
        '                    <li>\n' +
        '                        <a href="../../property/toproperty"><i class="fa fa-rocket"></i> <span class="nav-label">属性管理 </span></a>\n' +
        '                    </li>\n' +
        '                    <li class="active">\n' +
        '                        <a href="metrics.html"><i class="fa fa-telegram"></i> <span class="nav-label">首页内容管理</span><span class="fa arrow"></span>  </a>\n' +
        '                        <ul class="nav nav-second-level">\n' +
        '                            <li class="active"><a href="index.html">导航栏管理</a></li>\n' +
        '                            <li><a href="dashboard_2.html">轮播图管理</a></li>\n' +
        '                            <li><a href="dashboard_3.html">推荐管理</a></li>\n' +
        '                        </ul>\n' +
        '                    </li>\n' +
        '                    <li>\n' +
        '                        <a href="metrics.html"><i class="fa fa-comments-o"></i> <span class="nav-label">聊天室</span><span class="label label-danger pull-right">16</span></a>\n' +
        '                    </li>\n' +
        '                    <li>\n' +
        '                        <a href="metrics.html"><i class="fa fa-tags"></i> <span class="nav-label">系统说明</span>  </a>\n' +
        '                    </li>\n' +
        '                </ul>\n' +
        '\n' +
        '            </div>\n' +
        '        </nav>'
});

Vue.component('navigate-assist',{
    template:'<div class="row border-bottom">\n' +
        '                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">\n' +
        '                    <div class="navbar-header">\n' +
        '                        <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>\n' +
        '                    </div>\n' +
        '                    <ul class="nav navbar-top-links navbar-right">\n' +
        '                        <li>\n' +
        '                            <span class="m-r-sm text-muted welcome-message">欢迎使用101Shoes后台管理系统</span>\n' +
        '                        </li>\n' +
        '        \n' +
        '                        <li>\n' +
        '                            <a href="login.html">\n' +
        '                                <i class="fa fa-sign-out"></i> 退出\n' +
        '                            </a>\n' +
        '                        </li>\n' +
        '                        <li>\n' +
        '                            <a class="right-sidebar-toggle">\n' +
        '                                <i class="fa fa-tasks"></i>\n' +
        '                            </a>\n' +
        '                        </li>\n' +
        '                    </ul>\n' +
        '        \n' +
        '                </nav>\n' +
        '            </div>'
})