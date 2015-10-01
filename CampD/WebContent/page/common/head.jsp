<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><!-- 我的 -->
   	<!-- 毛玻璃效果蒙层 -->
    <div class="mao hide" id="activity_mao"></div>
    <!-- end -->
    <div class="person-right hide" id="activity_person">
     	<ul>
         	<li class="line clearfix" onclick="Activity.signUp()">
             	<img src="${rmtResPath}/static/images/p1.png" width="41" height="41" class="fl left-img"/>
              <div class="fl right-text">已报名的活动</div>
          </li>
          <li class="line clearfix" onclick="Activity.sponsored()">
             	<img src="${rmtResPath}/static/images/p2.png" width="41" height="41" class="fl left-img"/>
              <div class="fl right-text">要举办的活动</div>
          </li>
          <li class="line clearfix" onclick="Header.toLogin()">
             	<img src="${rmtResPath}/static/images/p3.png" width="41" height="41" class="fl left-img"/>
              <div class="fl right-text">登录</div>
          </li>
          <!-- 以下两个通过增加hide样式显示隐藏 -->
          <li class="line clearfix" onclick="Header.toQuit()">
             	<img src="${rmtResPath}/static/images/p4.png" width="41" height="41" class="fl left-img"/>
              <div class="fl right-text">退出</div>
          </li>
          <li class="line clearfix" onclick="Header.toRegister()">
             	<img src="${rmtResPath}/static/images/p5.png" width="41" height="41" class="fl left-img"/>
              <div class="fl right-text">注册</div>
          </li>
      </ul>
    </div>
    <!-- end -->
    <!-- 头部 -->
	<div class="header clearfix classify-head retina-1px-border-bottom positionR" id="activity_header">
    	<div class="head-left-icon">
        	<img src="${rmtResPath}/static/images/nav_icon.png" width="27" height="14"/>
        </div>
        <div class="head-content">
        	<img src="${rmtResPath}/static/images/cd_logo.png" width="56" height="20" />
        </div>
        <div class="head-right-icon" >
        	<img src="${rmtResPath}/static/images/user_icon_grey.png" onclick="Activity.person()" width="17" height="17"/>
        </div>
    </div>
    <!-- end -->
    
     <!-- 导航 -->
    <div class="index-nav hide" id="avtivity_nav" style="z-index: 99;">
    	<ul>
        	<li class="retina-1px-border-bottom1">
            	<img src="${rmtResPath}/static/images/clear_input.png" onclick="Activity.classify()" width="22" height="22"/>
                                   活动
            </li>
            <li class="retina-1px-border-bottom1">
            	<img src="${rmtResPath}/static/images/clear_input.png" onclick="Space.classify()" width="22" height="22"/>
               	 场地
            </li>
            <li class="retina-1px-border-bottom1">
            	<img src="${rmtResPath}/static/images/clear_input.png" onclick="Activity.hold()" width="22" height="22"/>
                                   举办活动
            </li>
        </ul>
    </div>
<!-- end -->

<script type="text/javascript" src="${locResPath}/static/common/header.js?_v=${vs}"></script>