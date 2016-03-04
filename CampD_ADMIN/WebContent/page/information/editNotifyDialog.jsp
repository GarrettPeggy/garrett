<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>

<div class="modal fade" id="eidtNotifyDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header table-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">修改通知</h4>
      </div>
      <div class="modal-body">
	      <form id="editNotifyForm" class="form-horizontal" role="form" >
	      	  <input type="hidden" name="type" value="${sysConfig.sysConfigList[0].sys_type}">
			  <input type="hidden" name="key" value="${sysConfig.sysConfigList[0].key_val}">
	          <div class="form-group">
		          <label for="value" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">连接:</label>
		          <div class="col-xs-12 col-sm-6">
					<span class="block input-icon input-icon-right">
						<input type="text" class="width-100" value="${sysConfig.sysConfigList[0].url_val}" notnull="true" name="url" id="url">
					</span>
				  </div>
	          </div>
	          
	          <div class="form-group">
				<label for="url" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">主题</label>
				<div class="col-xs-12 col-sm-6">
					<span class="block input-icon input-icon-right">
						<textarea class="width-100" name="value" id="value" notnull="true" placeholder="请添加通知主题">${sysConfig.sysConfigList[0].value_val}</textarea>
					</span>
				</div>
			  </div>
	          
	      </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-info" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" onclick="editNotify();">确定</button>
      </div>
    </div>
  </div>
</div>
