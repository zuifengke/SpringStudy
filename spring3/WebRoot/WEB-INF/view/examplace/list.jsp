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
	$("#edit").dialog({
            onOpen: function () {
                $('#parentID').combotree('reload');
            }
        });
	$("#parentID").combotree({
            url: "${ctx}/examplace/getComboTree",
        });
	$('#userTable')
			.treegrid(
					{
						title : '考点列表', //标题
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
						  animate: true,
						collapsible : true,//可折叠
						url : "${ctx}/examplace/queryList", //数据来源
						sortName : 'examplace.id', //排序的列
					    collapsible: false, //可折叠
						sortOrder : 'desc', //倒序
						remoteSort : true, //服务器端排序
						 nowrap: false,
						idField : 'id', //主键字段
						treeField: 'placename',
						queryParams : {}, //查询条件
						rownumbers : true, //显示行号
						columns : [ [{
							field : 'ck',
							checkbox : true,
							width : 2
						},
							    {
									field : 'placename',
									title : '考点名称',
									width : 180
								}, {
									field : "placetype",
									title : '级别',
									width : 100
								},

								{
									field : 'description',
									title : '描述',
									width : 220
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
							text : '刷新',
							iconCls : 'icon-reload',
							handler : function() {
								$('#userTable').treegrid('reload');
							}
						}, '-' ],
						onLoadSuccess : function() {
							$('#userTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
					}
					});

	//下拉表格初始化，这个东西在ajax下要尽量少用，太变态了，每加载一次就会重新创建一次，隐藏在页面上，
	//时间一长效率很低，用firebug一看页面上有几十个同样的层保存着下拉框中的内容，只有整个页面全部刷新才清除。
	//不知道新版本修正了没有，我目前的做法是点击菜单的时候手动清除一下。
	
});
//新增
function addrow() {
	$("#edit").dialog("open");
	$('#userForm').form('clear');
}
//更新
function updaterow() {
	var rows = $('#userTable').datagrid('getSelections');
	//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选
	if (rows.length == 0) {
		$.messager.alert('提示', "请选择你要更新的项", 'info');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', "只能选择一行进行更新", 'info');
		return;
	}
	$("#edit").dialog("open");
	
	$("#userForm").form('load', rows[0]);

}

//删除
function deleterow() {
	$.messager.confirm('提示', '确定要删除吗?', function(result) {
		if (result) {
			var rows = $('#userTable').datagrid('getSelections');
			var ps = "";
			$.each(rows, function(i, n) {
				if (i == 0)
					ps += "?id=" + n.id;
				else
					ps += "&id=" + n.id;
			});
			
			$.post('${ctx}/examplace/delete' + ps, function(data) {
				$('#userTable').treegrid('reload');
				$.messager.alert('提示', data.mes, 'info');
			});
		}
	});
}
//表格查询
function searchUser() {
	var params = $('#userTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
	var fields = $('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
	$.each(fields, function(i, field) {
		params[field.name] = field.value; //设置查询参数
		});
	$('#userTable').treegrid('reload'); //设置好查询参数 reload 一下就可以了
}
//清空查询条件
function clearForm() {
	$('#queryForm').form('clear');
	searchUser();
}
function addOrUpdateUser() {
	var r = $('#userForm').form('validate');
	if (!r) {
		return false;
	}
	$.post("${ctx}/examplace/addOrUpdate", $("#userForm").serializeArray(),
			function(data) {
				$("#edit").dialog("close");
				$('#userTable').treegrid('reload');
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
			<table id="userTable"></table>
		</div>

		<div id="edit" class="easyui-dialog" title="编辑考点"
			style="width: 300px; height: 250px;" modal="true" closed="true"
			buttons="#edit-buttons">
			<form id="userForm" method="post"
				style="margin: 10; text-align: center;">
				<input type="hidden" name="id" id="id">
				
				  名称：
				<input id="placename" name="placename" style="width: 200" 
					class="easyui-validatebox" required="true">
				<br>
				
				上级：
				  <input id="parentID" style="width:200px"  style="width: 200" required="true"  name="parentID" value="0" />
				<br>
				
				级别：
				<select class="easyui-combobox" name="placetype" id="placetype" style="width: 200"  required="true">
                            <option value="省份" selected="selected">省份</option>
                            <option value="城市">城市</option>
                            <option value="考点">考点</option>
                </select>
				<br/>
				  描述：
				<input id="description" name="description" style="width: 200" 
					>
				<br>
				<a href="#" id="btn-back" onclick="closeWindow();"
					class="easyui-linkbutton" iconCls="icon-back">返回</a>
				<a href="#" id="btn-add" onclick="addOrUpdateUser();"
					class="easyui-linkbutton" iconCls="icon-save">保存</a>
			</form>
		</div>
	</body>
</html>
