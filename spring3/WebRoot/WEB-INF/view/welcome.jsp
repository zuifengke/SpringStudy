<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>spring3</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript">
jQuery.ajaxSetup( {
	cache : false
});//ajax不缓存

//弹出窗口
function showWindow(options) {
	jQuery("#MyPopWindow").window(options);
}
//关闭弹出窗口
function closeWindow() {
	$("#MyPopWindow").window('close');
}
var _menus = {
	"menus" : [ {
		"menuid" : "1",
		"icon" : "icon-sys",
		"menuname" : "管理员系统",
		"menus" : [ {
			"menuid" : "11",
			"menuname" : "新闻资讯",
			"icon" : "icon-nav",
			"url" : "/admin/news/NewsGridList.aspx"
		}, {
			"menuid" : "12",
			"menuname" : "组织机构管理",
			"icon" : "icon-add",
			"url" : "dept/list"
		}, {
			"menuid" : "13",
			"menuname" : "员工管理",
			"icon" : "icon-users",
			"url" : "user/list"
		}, {
			"menuid" : "14",
			"menuname" : "考点管理",
			"icon" : "icon-role",
			"url" : "/admin/examplace/ExamPlaceMap.aspx"
		}, {
			"menuid" : "15",
			"menuname" : "会员管理",
			"icon" : "icon-set",
			"url" : "/admin/users/UsersManage.aspx"
		}, {
			"menuid" : "16",
			"menuname" : "菜单管理",
			"icon" : "icon-set",
			"url" : "/admin/menu/MenuManage.aspx"
		} ]
	}, {
		"menuid" : "2",
		"icon" : "icon-sys",
		"menuname" : "管理员系统2",
		"menus" : [ {
			"menuid" : "21",
			"menuname" : "链接管理",
			"icon" : "icon-nav",
			"url" : "/SystemManage/HrefInfo.aspx"
		}, {
			"menuid" : "22",
			"menuname" : "文章管理",
			"icon" : "icon-add",
			"url" : "/SystemManage/ArticleManage.aspx"
		}, {
			"menuid" : "23",
			"menuname" : "管理员管理",
			"icon" : "icon-users",
			"url" : "/SystemManage/AdminInfo.aspx"
		}, {
			"menuid" : "24",
			"menuname" : "博客用户管理(添加修改功能有BUG)",
			"icon" : "icon-role",
			"url" : "/SystemManage/BlogUserManage.aspx"
		}, {
			"menuid" : "25",
			"menuname" : "回复管理",
			"icon" : "icon-set",
			"url" : "/SystemManage/RevertList.aspx"
		} ]
	} ]
};
//初始化左侧
function InitLeftMenu1() {
	$(".easyui-accordion1").empty();
	var menulist = "";
	//加载菜单数据
	$.post("${ctx}/user/menuList", function(data) {

		if (data == "")
			return;
		_menus = data;
		
		$.each(_menus.menus, function(i, n) {
			menulist += '<div title="' + n.menuname + '"  icon="' + n.icon
					+ '" style="overflow:auto;">';
			menulist += '<ul>';
			if (n.menus != undefined) {
				$.each(n.menus, function(j, o) {
					menulist += '<li><div><a ref="' + o.menuid
							+ '" href="#" rel="' + o.url
							+ '" ><span class="icon ' + o.icon
							+ '" >&nbsp;</span><span class="nav">' + o.menuname
							+ '</span></a></div></li> ';
				})
			}
			menulist += '</ul></div>';
		})
		
		$(".easyui-accordion1").append(menulist);

		$('.easyui-accordion1 li a').click(function() {
			var tabTitle = $(this).children('.nav').text();

			var url = $(this).attr("rel");
			var menuid = $(this).attr("ref");
			var icon = getIcon(menuid, icon);

			addTab(tabTitle, url, icon);
			$('.easyui-accordion1 li div').removeClass("selected");
			$(this).parent().addClass("selected");
		}).hover(function() {
			$(this).parent().addClass("hover");
		}, function() {
			$(this).parent().removeClass("hover");
		});
		
		//导航菜单绑定初始化
		$(".easyui-accordion1").accordion();
	});
}
function GetInputData(id, cmd) {
	var postdata = "{ \"action\":\"" + cmd + "\",";
	$("#" + id + " input[type!='checkbox']").each(
			function() {
				postdata += "\"" + $(this).attr("name") + "\":\""
						+ $(this).val() + "\",";
			});
	$("#" + id + " input[type='checkbox']").each(
			function() {
				postdata += "\"" + $(this).attr("name") + "\":\""
						+ this.checked + "\",";
			});
	postdata = postdata.substr(0, postdata.length - 1);
	postdata += "}";
	return eval("(" + postdata + ")");
}

$(function() {

	InitLeftMenu1();

	$('#editpass').click(function() {
		$("#edit").dialog("open");
	});

	$('#btnsubmit').click(function() {
		if (!$("#form_edit").form("validate")) {
			return;
		}
		var pwdnew = $('#pwdnew').val();
		var pwdconfirm = $('#pwdconfirm').val();
		if (pwdnew != pwdconfirm) {
			alert("确认密码和新密码不一致");
			return;
		}
		var json = GetInputData('edit', 'submit');

		$.post("admin/home/ChangePwd", json, function(data) {
			$.messager.alert('提示', data, 'info', function() {
				if (data.indexOf("成功") > 0) {
					console.info(data);
					$("#edit").dialog("close");
				}
			});
		});
	});

	$('#btnEp').click(function() {
		serverLogin();
	});

	$('#btnCancel').click(function() {
		closePwd();
	})

	$('#loginOut').click(function() {
		$.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {

			if (r) {
				location.href = '/Admin/Home/Login';
			}
		});
	})
});
</script>
	</head>

	<!-- easyui-layout 可分上下左右中五部分，中间的是必须的，支持href，这样就可以不用iframe了 -->
	<body class="easyui-layout">
		<!-- 头部样式 start -->
		<div class="header">
			<div class="header-nav">
				<div class="logo"></div>

				<ul class="toolbar"></ul>
			</div>
		</div>
		<!-- 上 -->
		<div region="north" split="true" border="false"
			style="overflow: hidden; height: 36px; background: repeat-x center 100%; line-height: 36px; color: #fff; font-family: Verdana, 微软雅黑, 黑体">
			<span style="float: right; padding-right: 20px;" class="head">
				欢迎， ${userSessionInfo.name} <a href="http://www.zyldingfang.com"
				target="_blank" style="cursor: pointer; text-decoration: none;">
					状元乐主页 </a> <a href="javascript:void()"
				style="cursor: pointer; text-decoration: none;" id="editpass">
					修改密码 </a> <a href="javascript:void()" id="loginOut"
				style="cursor: pointer; text-decoration: none;"> 安全退出 </a> </span>
			<span style="padding-left: 10px; font-size: 16px;"> 状元乐后台管理 </span>
		</div>
		<div data-options="region:'west',split:true,title:'导航菜单'"
			style="width: 170px;">
			<div class="easyui-accordion1" fit="true" border="false">
				<!--  导航内容 -->
			</div>
		</div>
		<div data-options="region:'south',border:false" style="height: 23px;">
			<div class="footer">
				@copyright windy.studio
				<script type="text/javascript">
var cnzz_protocol = (("https:" == document.location.protocol) ? " https://"
		: " http://");
document
		.write(unescape("%3Cspan id='cnzz_stat_icon_1253892058'%3E%3C/span%3E%3Cscript src='"
				+ cnzz_protocol
				+ "s11.cnzz.com/z_stat.php%3Fid%3D1253892058%26online%3D2' type='text/javascript'%3E%3C/script%3E"));</script>
			</div>

		</div>
		<div data-options="region:'center'">
			<div id="tabs" class="easyui-tabs"
				data-options="tools:'#tab-tools',fit:true,border:false">

				<div title="技术支持" data-options="iconCls:'icon-house'"
					style="padding: 10px;">

				</div>
			</div>

		</div>
		<div id="edit" class="easyui-dialog" title="修改密码"
			style="width: 300px; height: 200px;" modal="true" closed="true"
			buttons="#edit-buttons">
			<form id="form_edit" name="form_edit" method="post"
				url="EmployeeManage.aspx">
				<table class="table_edit">
					<tr>
						<td class="tdal">
							原密码：
						</td>
						<td class="tdar">
							<input id="pwdold" name="pwdold" type="password"
								class="easyui-validatebox" required="true" />

						</td>
					</tr>
					<tr>
						<td class="tdal">
							新密码：
						</td>
						<td class="tdar">
							<input id="pwdnew" name="pwdnew" type="password"
								class="easyui-validatebox" required="true" />

						</td>
					</tr>
					<tr>
						<td class="tdal">
							确认密码：
						</td>
						<td class="tdar">
							<input id="pwdconfirm" name="pwdconfirm" type="password"
								class="easyui-validatebox" required="true" />

						</td>
					</tr>

				</table>
			</form>
			<div id="edit-buttons" style="text-align: center;">
				<a id="btnsubmit" href="javascript:;" class="easyui-linkbutton">提交</a>
				<a href="javascript:;" class="easyui-linkbutton"
					onclick="$('#edit').dialog('close');return false;">取消</a>
			</div>
		</div>
	</body>
</html>
