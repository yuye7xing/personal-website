<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="/tags/loushang-web" prefix="l"%>
<!DOCTYPE html>
<html lang="zh-CN">
  	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
	    <title>数据应用服务平台</title>
	    
	    <!-- 需要引用的CSS -->
		<link rel="shortcut icon" href="<%=request.getContextPath()%>/jsp/public/images/favicon.ico" />
		<link rel="stylesheet" type="text/css" href="<l:asset path='css/bootstrap.css'/>" />
		<link rel="stylesheet" type="text/css" href="<l:asset path='css/font-awesome.css'/>" />
		<link rel="stylesheet" type="text/css" href="<l:asset path='css/ui.css'/>" />
		<link rel="stylesheet" type="text/css" href="<l:asset path='css/form.css'/>" />
		<link rel="stylesheet" type="text/css" href="<l:asset path='platform/css/home.css'/>" />
	    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	      <script src="<l:asset path='html5shiv.js'/>"></script>
	      <script src="<l:asset path='respond.js'/>"></script>
	    <![endif]-->
	</head>
	<body style="overflow:hidden">
	
		<!-- 页面结构 -->
		<header class="navbar navbar-static-top">
			<div class="navbar-header">
	   			<a href="javascript: location=location;" class="navbar-brand"></a>
	   		</div>
	   		<div class="navbar-navigation" id="topMenu"></div>
	   		<div class="navbar-personal">
				<div class="dropdown">
	    			<a class="dropdown-toggle" data-toggle="dropdown">欢迎，管理员  <span class="fa fa-angle-down"></span></a>
	    			<ul class="dropdown-menu ue-dropdown-menu">
	   					<li><a onclick="logout()"><i class="fa fa-power-off"></i>退出</a></li>
	   				</ul>
	    		</div>
	    		<ul style="display: none;">
	   				<li><a><i class="fa fa-envelope"></i></a></li>
	   				<li><a><i class="fa fa-cog"></i></a></li>
	   				<li><a><i class="fa fa-question-circle"></i></a></li>
	   			</ul>
	   		</div>
		</header>
		<div class="ue-menu-wrap" style="display: none;">
			<div class="ue-menu-left">		
				<div class="ue-left-top">
					<img class="title-icon" src="<l:asset path='platform/img/app-icon.png'/>" />
					<span></span>
				</div>
				<div class="ue-left-content">
					<div class="ue-vmenu" id="subMenu"></div>
				</div>
				<div class="slideBlock">&nbsp;<i class="fa fa-angle-left"></i></div>
			</div> 
			<div class="ue-menu-right">
				<div class="ue-right-top"><span class="mainCrumb"></span><span class="crumbs"></span></div>
				<div class="ue-right-content">
					<iframe id="mainFrame" name="mainFrame" src="" frameborder="0" allowtransparency="true" width="100%" height="100%"></iframe>
				</div>
			</div>
		</div>
		<div class="ue-menu-wrap portal-wrap">
	   		<div class="portal-content" style="height: 100%;">
				<iframe id="portalFrame" name="portalFrame" src="<%=request.getContextPath()%>/jsp/public/overview.jsp" frameborder="0" allowtransparency="true" width="100%" height="100%"></iframe>
			</div>
		</div>
		<!-- 需要引用的JS -->
		<script type="text/javascript" src="<l:asset path='jquery.js'/>" ></script>
		<script type="text/javascript" src="<l:asset path='bootstrap.js'/>" ></script>
		<script type="text/javascript" src="<l:asset path='form.js'/>" ></script>
		<script type="text/javascript" src="<l:asset path='arttemplate.js'/>" ></script>	
		<script type="text/javascript" src="<l:asset path='home.js'/>" ></script>	
	    
	    <!-- top模板 --> 
	    <script type="text/html" id="topmenu">
		<ul>    
			<li id="mainPage" class="activedTopMenu">首页</li>		
			{{each topmenus.menu.rows as topmenu i }}
    			<li id="{{topmenu.id}}">{{topmenu.text}}</li>
   			{{/each}}
    	</ul>
    	</script>
	
	    <!-- sub模板 -->
		<script type="text/html" id="submenu">
		<ul>		
			{{each subMenus as submenu i }}
				<li class="leaf{{submenu.isLeaf}}" id="{{submenu.id}}" 
				{{if submenu.isLeaf == 'true' }}
					onclick="loadUrl('{{submenu.url}}')">
					<a>{{submenu.text}}</a>
				{{else}}
					><a>{{submenu.text}}</i></a>
						{{include 'submenu' ({subMenus:submenu.children})}}
				{{/if}}
				</li>
			{{/each}}
		</ul>
		</script>		
		<script type="text/javascript">
		var context = "<%=request.getContextPath()%>";  
		var path = "";
		$(document).ready(function() {			
			// 滚动条
			$('.ue-vmenu').slimscroll({
				height: '100%'
			});
			
			// 获取顶部菜单
			getTopMenu();
			
			// 绑定菜单切换
			$("#topMenu").on("click","ul > li",function(){
				var type = $(this).html();
				var subId = $(this).attr("id");
				$(".ue-right-top span.mainCrumb").html(type);
				$(".ue-left-top span").html(type);
				
				// sub菜单
				getSubMenu(subId);
			});
			
			// 顶部菜单选中状态
			$("#topMenu").on("click","ul > li",function(){
				$(this).addClass("activedTopMenu").siblings().removeClass("activedTopMenu");
			});
			
			// 下拉菜单
			$(".dropdown").on("click","a",function(){
				$(this).toggleClass("active");
			});
			
			// 左侧菜单缩进
			$(".slideBlock").on("click",function(){
				var leftX = $(".ue-menu-left").position().left;
				var width = $(".ue-menu-left").width();
				if(!leftX)
				{
					$(".ue-menu-left").animate({
						left: -width
					}, 500, function(){
						$(this).find("i").removeClass("fa-angle-left");
						$(this).find("i").addClass("fa-angle-right");
					});
					$(".ue-menu-right").animate({
						left: 0
					}, 500);
				}
				else
				{
					$(".ue-menu-left").animate({
						left: 0
					}, 500, function(){
						$(this).find("i").removeClass("fa-angle-right");
						$(this).find("i").addClass("fa-angle-left");
					});
					$(".ue-menu-right").animate({
						left: width
					}, 500);
				}
			});
	    });
		</script>
 	</body>
</html>