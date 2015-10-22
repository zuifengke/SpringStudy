<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<html>
	<head>
		<script type="text/javascript">
//弹出窗口  
//弹出窗口
function showWindow(options) {
	$("#edit").dialog("show");
}
//关闭弹出窗口
function closeWindow() {
	$("#edit").dialog("close");
}
jQuery(function($) {
	$('#roleTable')
			.datagrid(
					{
						title : '用户列表', //标题
						method : 'post',
						iconCls : 'icon-edit', //图标
						singleSelect : true, //多选

						width : $(parent.document).find("#tabs").width() - 30 > 0 ? $(
								parent.document).find("#tabs").width() - 30
								: 360, //高度
						height : $(parent.document).find("#tabs").height() - 100 > 0 ? $(
								parent.document).find("#tabs").height() - 100
								: 360, //高度
						fitColumns : true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
						striped : true, //奇偶行颜色不同
						collapsible : true,//可折叠
						url : "${ctx}/role/queryList", //数据来源
						sortName : 'role.id', //排序的列
						sortOrder : 'desc', //倒序
						remoteSort : true, //服务器端排序
						idField : 'id', //主键字段
						queryParams : {}, //查询条件
						pagination : true, //显示分页
						rownumbers : true, //显示行号
						columns : [ [ {
							field : 'ck',
							checkbox : true,
							width : 2
						}, //显示复选框
								{
									field : 'role.rolename',
									title : '名字',
									width : 20,
									sortable : true,
									formatter : function(value, row, index) {
										return row.role.rolename;
									} //需要formatter一下才能显示正确的数据
								}, {
									field : 'role.rolecode',
									title : '代码',
									width : 20,
									sortable : true,
									formatter : function(value, row, index) {
										return row.role.rolecode;
									}
								}, {
									field : 'role.rolebak',
									title : '备注',
									width : 30,
									sortable : true,
									formatter : function(value, row, index) {
										return row.role.rolebak; //该列的值是deptId，显示的是deptName
								}
								} ] ],
						toolbar : [ {
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								addrow();
							}
						}, '-', {
							text : '更新',
							iconCls : 'icon-edit',
							handler : function() {
								updaterow();
							}
						}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								deleterow();
							}
						}, '-', {
							text : '授权',
							iconCls : 'icon-remove',
							handler : function() {
								InitRoleMenu();
							}
						} ],
						onLoadSuccess : function() {
							$('#roleTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
					}
					});
});
//新增
function addrow() {
	$("#edit").dialog("open");
	$('#roleForm').form('clear');
}
//更新
function updaterow() {
	var rows = $('#roleTable').datagrid('getSelections');
	//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选
	if (rows.length == 0) {
		$.messager.alert('提示', "请选择你要更新的用户", 'info');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', "只能选择一位用户进行更新", 'info');
		return;
	}
	$("#edit").dialog("open");
	$("#roleForm").form('load', rows[0].role);
}

//删除
function deleterow() {
	$.messager.confirm('提示', '确定要删除吗?', function(result) {
		if (result) {
			var rows = $('#roleTable').datagrid('getSelections');
			var ps = "";
			$.each(rows, function(i, n) {
				if (i == 0)
					ps += "?uid=" + n.uid;
				else
					ps += "&uid=" + n.uid;
			});
			$.post('${ctx}/role/delete' + ps, function(data) {
				$('#roleTable').datagrid('reload');
				$.messager.alert('提示', data.mes, 'info');
			});
		}
	});
}
//表格查询
function searchUser() {
	var params = $('#roleTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
	var fields = $('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
	$.each(fields, function(i, field) {
		params[field.name] = field.value; //设置查询参数
		});
	$('#roleTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
}
//清空查询条件
function clearForm() {
	$('#queryForm').form('clear');
	searchUser();
}
function addOrUpdateUser() {
	var r = $('#roleForm').form('validate');
	if (!r) {
		return false;
	}
	$.post("${ctx}/role/addOrUpdate", $("#roleForm").serializeArray(),
			function(data) {
				$("#edit").dialog("close");
				$('#roleTable').datagrid('reload');
				$.messager.alert('提示', data.mes, 'info');
			});

}


    function InitRoleMenu() {
        $("#edit_tree").dialog("open");
        
        var rows = $('#roleTable').datagrid('getSelections');
        if (rows.length == 0) {
			$.messager.alert('提示', "请选择你要授权的角色", 'info');
			return;
		}
        var roleid=rows[0].role.id;
     
        $("#edit-buttons_tree a:first").attr("onclick", "submitTree(" + roleid + "); return false;");
        $('#tree').tree({
            url: '${ctx}/role/getRoleMenus?roleid='+roleid,
            checkbox: true,
            onClick: function (node) {
				
            }
        });
    }
    function submitTree(roleid) {
        var nodes = $('#tree').tree('getChecked', ['checked', 'indeterminate']);
        var s = '';
        for (var i = 0; i < nodes.length; i++) {
            if (s == '') 
				s += "?menuids=" + nodes[i].id;
			else
				s += "&menuids=" + nodes[i].id;
        }
        if(s=='')
        	s="?roleid="+roleid;
        else
        	s+="&roleid="+roleid;
    
        //提交机构树选择结果
        
		$.post('${ctx}/role/saveByRoleID' + s, function(data) {
			
				$.messager.alert('提示', data.mes, 'info');
			});
    }
</script>
	</head>

	<body>
		<form id="queryForm" style="text-align: center;">
			<table width="100%">
				<tr>
					<td>
						名字：
						<input name="name" style="width: 200">
					</td>
					<td align="center"></td>

					<td>
						生日：
						<input name="birthday" style="width: 200" class="Wdate"
							onClick="WdatePicker()">
					</td>
					<td>
						部门：
						<input id="deptCombo" name="deptId" style="width: 200">
					</td>
					<td align="center">
						<a href="#" onclick="searchUser();" class="easyui-linkbutton"
							iconCls="icon-search">查询</a>
					</td>
				</tr>
			</table>
		</form>
		<div style="" id="tabdiv">
			<table id="roleTable"></table>
		</div>


		<div id="edit" class="easyui-dialog" title="编辑角色信息"
			style="width: 300px; height: 250px;" modal="true" closed="true"
			buttons="#edit-buttons">
			<form id="roleForm" method="post"
				style="margin: 10; text-align: center;">
				<input type="hidden" name="id" id="id">
				名字：
				<input name="rolename" style="width: 200" class="easyui-validatebox"
					required="true">
				<br>
				代码：
				<input name="rolecode" style="width: 200" class="easyui-validatebox"
					required="true">
				<br>
				备注：
				<input name="rolebak" min="1" max="200" style="width: 200px;"></input>
				<br>

				<br>
				<a href="#" id="btn-back" onclick="closeWindow();"
					class="easyui-linkbutton" iconCls="icon-back">返回</a>
				<a href="#" id="btn-add" onclick="addOrUpdateUser();"
					class="easyui-linkbutton" iconCls="icon-save">保存</a>
			</form>
		</div>
		<div id="edit_tree" class="easyui-dialog" title="设置角色功能权限"
			closed="true" style="width: 600px; height: 450px;">
			<div class="easyui-panel" style="width: 580px; height: 360px;">
				<table>
					<tr>
						<td>
							<table id="emp_list"></table>
						</td>
						<td style="vertical-align: top;">
							<ul id="tree"></ul>
						</td>
					</tr>
				</table>


			</div>
			<div id="edit-buttons_tree" style="float: right;">
				<a id="btn_add_tree" href="javascript:;" class="easyui-linkbutton">提交</a>
				<a href="javascript:;" class="easyui-linkbutton"
					onclick="$('#edit_tree').dialog('close');return false;">取消</a>
			</div>
		</div>
	</body>
</html>
