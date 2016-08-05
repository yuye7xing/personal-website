// 获取顶部菜单
function getTopMenu() {
	$.ajax({
		url: context+"/service/home/topmenu",
 	    type: "GET",
 	    success: function(dataobj) {
			try {
				dataobj = eval("(" + dataobj + ")");
				var temps = template("topmenu", {
					topmenus: dataobj
				});
				$("#topMenu").prepend(temps);
				
				// 初始化vmenu
				$('.ue-vmenu').vmenu({
					autostart: 1
				});			
			} catch(err) {
				$.dialog({
					type: 'alert',
					content: '加载菜单异常！',
					autofocus: true,
					ok: function () {
					}
				});
			}
 	    	
 	    },
 	    error: function() {
 	    	$.dialog({
				type: 'alert',
				content: '加载菜单出错！',
				autofocus: true,
				ok: function () {
				}
			});
 	    }
   });
};	

// 获取侧边栏
function getSubMenu(id) {
	var menuId = id;
	$(".ue-menu-wrap").css("display","block");
	$(".portal-wrap").css("display","none");
	$.ajax({
		url: context+"/service/home/menu/submenu/" + menuId,
 	    type: "GET",
 	    success: function(dataobj) {
			try {
				dataobj = eval("(" + dataobj + ")");
				var subMenu = dataobj.menu.rows;
				var temps = template("submenu", {
					subMenus: subMenu
				});
				$("#subMenu").empty().prepend(temps);
				
				// vmenu
				$('.ue-vmenu').vmenu({
					autostart: 1
				});
				
				// 获取第一个叶子节点
				var firstLeaf = $('.ue-vmenu').find(".leaftrue").eq(0);
				firstLeaf.addClass("curr");
				$('.ue-right-top').find("span.crumbs").html("");
				if(firstLeaf)
				{
					$('.ue-vmenu').find(".leaftrue").eq(0).click();
					findBreadCrumb(firstLeaf);
				}
				
				// 获取面包屑
				$('.ue-vmenu').on("click","ul li",function(e) {
					var leaf = $(this);
					if(leaf.is(".leaftrue"))
					{
						$('.ue-right-top').find("span.crumbs").empty();
						findBreadCrumb(leaf);
					}
					e.stopPropagation();
				});

			} catch(err) {
				if (err.message.indexOf("Unexpected token") > -1) {
					$.dialog({
						type: 'alert',
						content: '用户未登录或超时！',
						autofocus: true,
						ok: function () {
							document.location.href= context + "/logout";
						}
					});
				} else {
					
				}
			}
			
 	    },
 	    error: function() {
        }
	});
	if (id == "mainPage") 
	{
		$(".ue-menu-wrap").css("display", "none");
		$(".portal-wrap").css("display", "block");
	}
};

// 面包屑
function findBreadCrumb(e) {
	path = "";
	var firstCrumb = e.text();
	var crumbs = e.parents("ul").prev();
	var count = e.parents("ul").length;
	for (var i = 0; i < count - 1 ; i++)
	{
		var text = crumbs.eq(i).text();
		path = text +"&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;" + path;
	}
	$('.ue-right-top').find("span.crumbs").html("&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;" + path + firstCrumb);
};

// 绑定iframe的url
function loadUrl(data) {
	var url = null;
	if(data.substr(0,7) == "http://") {
		url = data;
	}
	else {
		url = context + data;
	}
	$('#mainFrame').attr('src', url);
};

// 退出
function logout(){
	$.dialog({
		type: 'confirm',
	    content: '您确定要退出系统吗？',
	    ok: function () {
	    	document.location.href = context + "/jsp/public/signin.jsp";
	    },
	    cancel: function () {},
	    autofocus: true
    }).showModal();
};