<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!--
        Charisma v1.0.0

        Copyright 2012 Muhammad Usman
        Licensed under the Apache License v2.0
        http://www.apache.org/licenses/LICENSE-2.0

        http://usman.it
        http://twitter.com/halalit_usman
    -->
    <meta charset="utf-8">
    <title>Free HTML5 Bootstrap Admin Template</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
    <meta name="author" content="Muhammad Usman">

    <!-- The styles -->
    <link id="bs-css" href="/statics/css/bootstrap-cerulean.css" rel="stylesheet">
    <style type="text/css">
        body {
            padding-bottom: 40px;
        }

        .sidebar-nav {
            padding: 9px 0;
        }
    </style>
    <link href="/statics/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="/statics/css/charisma-app.css" rel="stylesheet">
    <link href="/statics/css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
    <link href='/statics/css/fullcalendar.css' rel='stylesheet'>
    <link href='/statics/css/fullcalendar.print.css' rel='stylesheet' media='print'>
    <link href='/statics/css/chosen.css' rel='stylesheet'>
    <link href='/statics/css/uniform.default.css' rel='stylesheet'>
    <link href='/statics/css/colorbox.css' rel='stylesheet'>
    <link href='/statics/css/jquery.cleditor.css' rel='stylesheet'>
    <link href='/statics/css/jquery.noty.css' rel='stylesheet'>
    <link href='/statics/css/noty_theme_default.css' rel='stylesheet'>
    <link href='/statics/css/elfinder.min.css' rel='stylesheet'>
    <link href='/statics/css/elfinder.theme.css' rel='stylesheet'>
    <link href='/statics/css/jquery.iphone.toggle.css' rel='stylesheet'>
    <link href='/statics/css/opa-icons.css' rel='stylesheet'>
    <link href='/statics/css/uploadify.css' rel='stylesheet'>

    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- The fav icon -->
    <link rel="shortcut icon" href="/statics/img/favicon.ico">
    <script type="text/javascript">var tt = '${menuList}';</script>
</head>

<body>
<!-- topbar starts -->
<div class="navbar">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse"
               data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="/main.html"> <img alt="Charisma Logo" src="/statics/img/logo20.png"/>
                <span>SL会员商城</span></a>

            <!-- theme selector starts -->
            <div class="btn-group pull-right theme-container">
                <ul class="nav">
                    <li><a href="#">你好,${user.loginCode}</a></li>
                    <li><a href="#">角色:${user.roleName}</a></li>
                    <li><a href="/main.html">首页</a></li>
                    <li><a href="#">购物车</a></li>
                    <li><a href="#">留言板</a></li>
                    <li><a href="javascript:void();" class="btn-setting modifypwd">修改密码</a></li>
                    <li><a href="/logout.html">注销</a></li>
                </ul>
            </div>
            <!-- theme selector ends -->

            <%--start模式窗口--%>
            <div class="modal hide fade" id="myModal">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3>修改密码</h3>
                </div>
                <div class="modal-body">
                    <p>
                    <div><span class="label">原密码</span></div>
                    <div class="input-prepend" title="原密码" data-rel="tooltip">
                        <span class="add-on"><i class="icon-lock"></i></span>
                        <input autofocus class="input-large span5" name="oldPassword" id="oldPassword" type="password"
                               value=""/>
                    </div>
                    <div><span class="label">新密码</span></div>
                    <div class="input-prepend" title="新密码" data-rel="tooltip">
                        <span class="add-on"><i class="icon-lock"></i></span>
                        <input class="input-large span5" name="newPassword" id="newPassword" type="password" value=""/>
                    </div>
                    <div><span class="label">再次确认密码</span></div>
                    <div class="input-prepend" title="再次确认密码" data-rel="tooltip">
                        <span class="add-on"><i class="icon-lock"></i></span>
                        <input class="input-large span5" name="confirmPassword" id="confirmPassword" type="password"
                               value=""/>
                    </div>
                    </p>
                    <p id="modifypwdtip"></p>
                    <div id="loadingImg" style="display: none;">
                        <img src="/statics/img/ajax-loaders/ajax-loader-1.gif"
                             title="/statics/img/ajax-loaders/ajax-loader-1.gif">
                    </div>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn" data-dismiss="modal">取消</a>
                    <a href="#" id="modifySavePassword" class="btn btn-primary">修改</a>
                </div>
            </div>
            <%--end模式窗口--%>
        </div>
    </div>
</div>
<!-- topbar ends -->
<div class="container-fluid">
    <div class="row-fluid">

        <!-- left menu starts -->
        <div class="span2 main-menu-span">
            <div class="well nav-collapse sidebar-nav">
                <ul class="nav nav-tabs nav-stacked main-menu" id="menus">
                    <!--
                    <label id="for-is-ajax" class="hidden-tablet" for="is-ajax"><input id="is-ajax" type="checkbox"> Ajax on menu</label>
                     -->

                    <%--TODO--%>
                </ul>
            </div><!--/.well -->
        </div><!--/span-->
        <!-- left menu ends -->

        <%--<!-- left menu starts -->
        <div class="span2 main-menu-span">
            <div class="well nav-collapse sidebar-nav">
                <ul class="nav nav-tabs nav-stacked main-menu">
                    <li class="nav-header hidden-tablet">Main</li>
                    <li><a class="ajax-link" href="index.html"><i class="icon-home"></i><span class="hidden-tablet"> Dashboard</span></a></li>
                    <li><a class="ajax-link" href="ui.html"><i class="icon-eye-open"></i><span class="hidden-tablet"> UI Features</span></a></li>
                    <li><a class="ajax-link" href="form.html"><i class="icon-edit"></i><span class="hidden-tablet"> Forms</span></a></li>
                    <li><a class="ajax-link" href="chart.html"><i class="icon-list-alt"></i><span class="hidden-tablet"> Charts</span></a></li>
                    <li><a class="ajax-link" href="typography.html"><i class="icon-font"></i><span class="hidden-tablet"> Typography</span></a></li>
                    <li><a class="ajax-link" href="gallery.html"><i class="icon-picture"></i><span class="hidden-tablet"> Gallery</span></a></li>
                    <li class="nav-header hidden-tablet">Sample Section</li>
                    <li><a class="ajax-link" href="table.html"><i class="icon-align-justify"></i><span class="hidden-tablet"> Tables</span></a></li>
                    <li><a class="ajax-link" href="calendar.html"><i class="icon-calendar"></i><span class="hidden-tablet"> Calendar</span></a></li>
                    <li><a class="ajax-link" href="grid.html"><i class="icon-th"></i><span class="hidden-tablet"> Grid</span></a></li>
                    <li><a class="ajax-link" href="file-manager.html"><i class="icon-folder-open"></i><span class="hidden-tablet"> File Manager</span></a></li>
                    <li><a href="tour.html"><i class="icon-globe"></i><span class="hidden-tablet"> Tour</span></a></li>
                    <li><a class="ajax-link" href="icon.html"><i class="icon-star"></i><span class="hidden-tablet"> Icons</span></a></li>
                    <li><a href="error.html"><i class="icon-ban-circle"></i><span class="hidden-tablet"> Error Page</span></a></li>
                    <li><a href="login.html"><i class="icon-lock"></i><span class="hidden-tablet"> Login Page</span></a></li>
                </ul>
                <label id="for-is-ajax" class="hidden-tablet" for="is-ajax"><input id="is-ajax" type="checkbox"> Ajax on menu</label>
            </div><!--/.well -->
        </div><!--/span-->
        <!-- left menu ends -->--%>

        <noscript>
            <div class="alert alert-block span10">
                <h4 class="alert-heading">Warning!</h4>
                <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a>
                    enabled to use this site.</p>
            </div>
        </noscript>

        <div id="content" class="span10">
            <!-- content starts -->
