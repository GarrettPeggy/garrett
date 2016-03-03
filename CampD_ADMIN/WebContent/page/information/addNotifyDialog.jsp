<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>

<div class="modal fade" id="addNotifyDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header table-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">添加通知</h4>
      </div>
      <div class="modal-body">
	      <form id="addTagForm" class="form-horizontal" role="form" >
			  <input type="hidden" name="type" value="${sysConfig.sys_conf_notify}"><!-- 系统配置类型，1表示首页通知配置 -->
			  <input type="hidden" name="key" id="max_key" value="0"><!-- 轮播图顺序，0表示首页通知第一个 -->
		      <div class="form-group">
		          <label for="value" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">链接:</label>
		          <div class="col-xs-12 col-sm-6">
					<span class="block input-icon input-icon-right">
						<input type="text" class="width-100" notnull="true" name="value" id="value">
					</span>
				  </div>
	          </div>
	          
	          <div class="form-group">
				<label for="remark" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">主题</label>
				<div class="col-xs-12 col-sm-6">
					<span class="block input-icon input-icon-right">
						<textarea class="width-100" name="url" id="url" notnull="true" placeholder="请添加通知主题"></textarea>
					</span>
				</div>
			  </div>
	      </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-info" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" onclick="addNotify();">确定</button>
      </div>
    </div>
  </div>
</div>
