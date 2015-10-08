<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<script type="text/javascript" src="${locResPath}/static/js/user/user.js?_v=${vs}"></script>
</head>
<body class="login-layout blur-login">
	<div class="main-container">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">
						<div class="center">
							<h1>
								<i class="ace-icon fa fa-leaf green"></i>
								<span class="red">CD</span>
								<span class="white grey" id="id-text2">运营后台</span>
							</h1>
							<h4 class="light-blue" id="id-company-text">&copy; 上海徊燕信息技术有限公司</h4>
						</div>

						<div class="space-6"></div>

						<div class="position-relative" style="width: 340px;">
							<div id="login-box" class="login-box visible widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header blue lighter bigger">
											<i class="ace-icon fa fa-coffee green"></i>
											运营人员登陆
										</h4>

										<div class="space-6"></div>

										<form>
											<fieldset>
												<label class="block clearfix">
													<span class="block input-icon input-icon-right">
														<input type="text" class="form-control" id="userName" placeholder="用户名" />
														<i class="ace-icon fa fa-user"></i>
													</span>
												</label>

												<label class="block clearfix">
													<span class="block input-icon input-icon-right">
														<input type="text" class="form-control" id=mdn placeholder="手机号" />
														<i class="ace-icon fa fa-lock"></i>
													</span>
												</label>

												<div class="space"></div>

												<div class="clearfix">
													<button type="button" onclick="User.login();" class="width-35 pull-right btn btn-sm btn-primary">
														<i class="ace-icon fa fa-key"></i>
														<span class="bigger-110">Login</span>
													</button>
												</div>

												<div class="space-4"></div>
											</fieldset>
										</form>
									</div><!-- /.widget-main -->
								</div><!-- /.widget-body -->
							</div><!-- /.login-box -->
						</div><!-- /.position-relative -->
					</div>
				</div><!-- /.col -->
			</div><!-- /.row -->
		</div><!-- /.main-content -->
	</div><!-- /.main-container -->
</body>
</html>