<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglib.jsp" %>

<div class="modal fade" id="toUpdateUserInfo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header table-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">查看个人信息</h4>
      </div>
      <div class="modal-body">
	      <form id="updateUserInfoForm" class="form-horizontal" role="form" >
	          <input type="hidden" name="userId" value="${USER_INFO.id}">
	          <div class="form-group">
		          <label for="tagName" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">账户名称:</label>
		          <div class="col-xs-12 col-sm-4">
					<span class="block input-icon input-icon-right">
						<input type="text" class="width-100" notnull="true" value="${USER_INFO.userName}" name="userName" id="userName">
					</span>
				  </div>
	          </div>
			  
		      <div class="form-group">
		          <label for="tagName" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">手机号:</label>
		          <div class="col-xs-12 col-sm-4">
					<span class="block input-icon input-icon-right">
						<input type="text" class="width-100" dataType="phone" notnull="true" value="${USER_INFO.mdn}" name="mdn" id="mdn">
					</span>
				  </div>
	          </div>
	          
	          <div class="form-group">
		          <label for="tagName" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">邮箱:</label>
		          <div class="col-xs-12 col-sm-4">
					<span class="block input-icon input-icon-right">
						<input type="text" class="width-100" dataType="email" value="${USER_INFO.email}" name="email" id="email">
					</span>
				  </div>
	          </div>
	      </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-info" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" onclick="updateUserInfo();">确定</button>
      </div>
    </div>
  </div>
</div>
