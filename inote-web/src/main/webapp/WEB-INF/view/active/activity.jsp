<%@ page import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String basePath = request.getContextPath(); %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>New_note</title>
<link rel="stylesheet" href="<%=basePath%>/styles/edit.css"/>
<link rel="stylesheet" href="<%=basePath%>/styles/icon.css"/>
<link rel="stylesheet" href="<%=basePath%>/styles/prettify.min.css"/>
</head>

<body>
	<header class='clear_float'>
		<div class="logo"></div>
		<div class="loginbar">
			
		</div>
		<div class="activity_btn">
			<div class="activity_link">
				<h4><a class="ac_link"  onclick="addActivity();">添加活动</a><i class='icon i_global'></i></h4>
			</div>
		</div>
	</header>
	<div class="row clear_float">
<!-- alert -->
		<div class="alert_can" style='display:none;'>
			<div class="alert_background"></div>
			<div class="right_bottom">
				<div class="panel_can"><!-- panel容器 -->
					<!-- <div class="panel panel_deleteNote">
						<div class="panel_top">
							<h3 class="panel_title">收藏笔记</h3>
						</div>
						<div class="panel_middle">
							<h4>该笔记将被放至收藏笔记本,确认收藏吗?</h4>
						</div>
						<div class="panel_bottom">
							<input type="button" value="确 定" class="sure"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" value="取 消" class="cancle"/>
						</div>
					</div> -->
				</div>
			</div>
		</div>
<!-- col_5 -->
		<div class="col_5">
			<ul id="col_panel_1">
			</ul>
		</div>
<!-- col_6 -->
		<div class="col_6">
			<ul id="col_panel_2">
			</ul>
		</div>
<!-- col_7 -->
		<div class="col_7">
			<ul id="col_panel_3">
			</ul>
		</div>
<!-- col_1 -->
	</div>
	<footer>
		<div class="alert_success_s"></div>
		<div class="alert_success_b"></div>
	</footer>
	<script type="text/javascript" src="<%=basePath%>/javascripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/javascripts/jquery.nicescroll.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/javascripts/run_prettify.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/javascripts/Editor/kindeditor-min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/javascripts/Editor/lang/zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>/javascripts/index.js"></script>
	<script type="text/javascript" src="<%=basePath%>/javascripts/BaseValues.js"></script>
	<script type="text/javascript">
	function addActivity(){
		var dom=$(this).parents('li');
		$('.alert_can').data({
			addActive:dom
		});
		$('.panel_can').html(panelCan.panel_addActivity);
		show_bg();
	}
	Date.prototype.format = function(pattern) {
	    /*初始化返回值字符串*/
	    var returnValue = pattern;
	    /*正则式pattern类型对象定义*/
	    var format = {
	        "y+": this.getFullYear(),
	        "M+": this.getMonth()+1,
	        "d+": this.getDate(),
	        "H+": this.getHours(),
	        "m+": this.getMinutes(),
	        "s+": this.getSeconds(),
	        "S": this.getMilliseconds(),
	        "h+": (this.getHours()%12),
	        "a": (this.getHours()/12) <= 1? "AM":"PM"
	    };
	    /*遍历正则式pattern类型对象构建returnValue对象*/
	    for(var key in format) {
	        var regExp = new RegExp("("+key+")");
	        if(regExp.test(returnValue)) {
	            var zero = "";
	            for(var i = 0; i < RegExp.$1.length; i++) { zero += "0"; }
	            var replacement = RegExp.$1.length == 1? format[key]:(zero+format[key]).substring(((""+format[key]).length));
	            returnValue = returnValue.replace(RegExp.$1, replacement);
	        }
	    }
	    return returnValue;
	};
		String.format = function() {
	    	if (arguments.length == 0)
	        	return null;
	    	var str = arguments[0];
	    	for ( var i = 1; i < arguments.length; i++) {
	        	var re = new RegExp('\\{' + (i - 1) + '\\}', 'gm');
	        	str = str.replace(re, arguments[i]);
	    	}
	    	return str;
		};
		function appendli(elements){
			var li='<li class="ac_panel {4}">'+
			'<h4 class="ac_title"><a class="ac_link"  onclick="openDetail(\'{1}\')" id="{1}">{0}</a></h4>'+
			'<hr class="hr"/>'+
			'<p class="ac_info text_over">{2}</p>'+
			'<hr class="hr"/>'+
			'<p class="ac_time">结束时间:<strong>{3}</strong></p>'+
			'</li>';
			$.each(elements,function(index, content)
			{ 
				var rowIndex=Math.floor(index/3);
				color="";
				var changeColor=rowIndex%3;
				//alert(changeColor);
				switch(changeColor){
					case 0:color="panel_green";break;
					case 1:color="panel_red";break;
					case 2:color="panel_blue";break;
					default:color="panel_yellow";
				}
				//alert(new Date(content.deadline));
				resault=String.format(li,content.title,content.rowKey,content.detail,(new Date(content.deadline)).format("yyyy-MM-dd"),color);
				var changecol=index%3;
				switch(changecol){
					case 0:$("#col_panel_1").append(resault);break;
					case 1:$("#col_panel_2").append(resault);break;
					case 2:$("#col_panel_3").append(resault);break;
				}
				//alert( "the man's no. is: " + index + ",and " + content.name + " is learning " + content.lang );
				
			});	
		}
		function flashActives(){
			$("#col_panel_1").html("");
			$("#col_panel_2").html("");
			$("#col_panel_3").html("");
			$.ajax({
				type : "get",
				url : basePath +"note/getAllActives",
				async : false,
				dataType : "json",
				success : function(data) {
					appendli(data);
				},
				error:function(data) {
					alert("no");
				}
			});
		}
		$(function(){

			flashActives(); 
			
		});
	</script>
</body>
</html>



