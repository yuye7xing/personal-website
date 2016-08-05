<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" language="java"%>
<%@ taglib uri="/tags/loushang-web" prefix="l"%>
<%@ page import="java.net.URLDecoder" %>
<%
String msg=(String)request.getSession().getAttribute("AuthenticationException");
if(msg==null){
	msg="";
}else if(msg.indexOf("，")>0){
	String[] info = msg.split("，");
	if(info.length > 2)
		msg = info[info.length-1];
	else
		msg = info[0];
}
request.getSession().setAttribute("AuthenticationException",null);
//获取用户名
String userName = null;
Cookie[] cookies = request.getCookies();
if(cookies!=null){
	for(int i = 0 ; i < cookies.length; i++){
		if(cookies[i].getName().equals("userName")) {
			userName=URLDecoder.decode(cookies[i].getValue(), "UTF-8");
			  break;
		}
	}
}
%>

<html lang="en">
  	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
	    <title>IDAP浪潮数据应用服务平台-登录页</title>
	
	    <!-- 需要引用的CSS -->
	    <link rel="shortcut icon" href="<%=request.getContextPath()%>/jsp/public/images/favicon.ico" />
	    <link rel="stylesheet" type="text/css" href="<l:asset path='css/bootstrap.css'/>" />
   	    <link rel="stylesheet" type="text/css" href="<l:asset path='css/font-awesome.css'/>" />
	    <link rel="stylesheet" type="text/css" href="<l:asset path='css/ui.css'/>" />
	    <link rel="stylesheet" type="text/css" href="<l:asset path='platform/css/login.css'/>" />
	
	    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	      <script src="<l:asset path='html5shiv.js'/>"></script>
	      <script src="<l:asset path='respond.js'/>"></script>
	    <![endif]-->
	</head>
  	<body>
		<!-- 页面结构 -->
		<div class="login">
			<img src="<l:asset path='platform/img/bg.jpg'/>" />
			<div class="login-body">
				<div class="login-left pull-left"><img src="<l:asset path='platform/img/login-bg.png'/>" /></div>
				<div class="login-right pull-right">
					<div class="logo">
						<img src="<l:asset path='platform/img/login-logo.png'/>" />
					</div>
					<div class="avatar">登录</div>
	                <div id="hints" class="error-hints" >
	                	<img src="<l:asset path='platform/img/error.png'/>"/>
	                	<p><%=msg%></p>
	                </div>
                	
					<form method="post" onsubmit="return false;">
					  	<div class="input-wrap">
					  		<div class="form-group" >
					  			<label><i class="fa fa-user"></i></label>
						    	<span class="quickdelete-wrap">
						    		<input type="text" class="form-control ue-form qd_ipt" id="userName" name="j_username" tabindex="1" placeholder="请输入用户名">
						    		<a id="deleteName" class="quickdelete" href="javascript:void(0)" onclick="clearName()" title="清空" ></a>
						    	</span>
							</div>
							<div class="form-group">
								<label><i class="fa fa-lock"></i></label>
								<span class="quickdelete-wrap">
									<input type="password" class="form-control ue-form qd_ipt" id="password" name="j_password" tabindex="2" placeholder="请输入密码">
									<a id="deletePassword" class="quickdelete" href="javascript:void(0)" onclick="clearPassword()" title="清空" ></a>
								</span>
								<input type="hidden" name="rdmCode" id="rdmCode"/>
							</div>
						</div>
				  		<div class="checkbox">
							<label class="form-checkbox">
								<input type="checkbox" id="remember" tabindex="3">&nbsp;记住用户名
							</label>
						</div>
			  	  		<button class="btn btn-login" tabindex="4" onclick="doLogin()">登录</button>
					</form>
				</div>
				
			</div>
			<div class="login-bottom">
				<a href="">常用下载</a>
				<a href="">版本信息</a>
				<a href="">使用帮助</a>
			</div>
		</div>	
	    <!-- 需要引用的JS -->
	    <script type="text/javascript" src="<l:asset path='jquery.js'/>"></script>
	    <script type="text/javascript" src="<l:asset path='ui.js'/>"></script>
	    <script type="text/javascript" src="<l:asset path='form.js'/>"></script>
	    <script type="text/javascript" src="<l:asset path='md5.js'/>" ></script> 
	    <script language="javascript">
	    $(function(){
			if ($.cookie("rmbUser") == "true") {
				$("#remember").attr("checked", "checked");
				$("#userName").val($.cookie("userName"));
			}	
	   		$("#deleteName").hide();
	   		$("#deletePassword").hide();
	   		$("#hints").hide();
	   		if('<%=msg%>' != "") {
	   			$("#hints").show();
	   		}
	   		if('<%=userName%>' != "null") {
	   			$("#userName")[0].value = '<%=userName%>';
	   			$("#deleteName").show();
	   		}
	   		
			//用户姓名输入框清空按钮
			$("#userName").bind("input propertychange",function(){
				if($("#userName")[0].value!="")
					$("#deleteName").show();
				else
					$("#deleteName").hide();
			});
			
			//用户密码输入框清空按钮
			$("#password").bind("input propertychange",function(){
				if($("#password")[0].value!="")
					$("#deletePassword").show();
				else
					$("#deletePassword").hide();
			});
			doSession();		
		});
	    
		function saveUserInfo() {
			if ($("#remember").is(':checked')) {
				var userName = $("#userName").val();
				$.cookie("rmbUser", "true", { expires: 7 }); 
				$.cookie("userName", userName, { expires: 7 }); 
			}
			else {
				$.cookie("rmbUser", null, { expires: -1 });  
				$.cookie("userName", null, { expires: -1 });
			}
		};
		function doSession() {
			if(window.dialogArguments!=null) {
				 window.dialogArguments.location=self.location;
				 window.returnValue="aa";
				 parent.close();
				 return;
				}
				if(self!=top) {
					top.location=self.location;
				}
		};
		function doLogin(){
			var salt = "1#2$3%4(5)6@7!poeeww$3%4(5)djjkkldss";
			if(!check()) return;
			var password = document.getElementById("password");
			password.value = hex_md5(password.value + "{" + salt + "}");
			saveUserInfo();
			window.location.href = "<%=request.getContextPath()%>/jsp/public/home.jsp";
		};
		
		//如果名称或者密码 为空要返回提示 
		function check(){
			var username = document.forms[0].j_username;
			var password = document.forms[0].j_password;
			if(username.value=="" && password.value==""){
				$("#hints").show();
				$("#hints").children("p").text("请输入用户名和密码");
				username.focus();
				return false;
			}
			if(username.value==""){
				$("#hints").show();
				$("#hints").children("p").text("请输入用户名");
				username.focus();
				return false;
			}
			if(password.value==""){
				$("#hints").show();
				$("#hints").children("p").text("请输入密码");
				password.focus();
				return false;
			}
			return true;
		
		};
		
		//'enter' key,/=47,*=42,+=43
		function keypress(e)
		{
		
			if(!window.event){
				e = e.which;
			}else{
				e = window.event.keyCode;
			}
			if(e==13||e==42)	//'enter' key,*
			{
				doLogin();
			}
		
		};
		function clearName(){
			$("#userName")[0].value="";
			$("#deleteName").hide();
		};
		function clearPassword(){
			$("#password")[0].value="";
			$("#deletePassword").hide();
		}
		</script>
	</body>
</html>