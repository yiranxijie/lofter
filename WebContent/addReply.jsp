<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	request.setCharacterEncoding("utf-8");
%>

<li class="a-slide a-slide-do" style="display:block;float:left;left:-555px;">
	<div class="cmti slide xtag" style="margin-top: 0px;">
		<div class="w-img2 w-img2-2 noshortkey">
			<a target="_blank" href="#" title="余文森✿Vincent - 1小时前"><img
				class="xtag" src="images/${replyLiModel.head }"> </a>
		</div>
		<div id="hiddeninfo">
			<input type="hidden" name="userid" value="${replyLiModel.userid }">
			<input type="hidden" name="topicid" value="${replyLiModel.topicid }">
			<input type="hidden" name="replyCount" value="${replyCount }">
		</div>
		<div class="cmtcnt">
			<div class="cmthot">
				<span class="cmtusr"> <a class="s-fc4 xtag" target="_blank"
					href="http://beeeeeeeee.lofter.com" title="">${replyLiModel.nickname
						}</a> </span> <span class="xtag">${replyLiModel.content }</span>
			</div>
			<div class="cmtopt">
				<a class="cmtj s-fc4 xtag" href="#">加黑</a> <a
					class="xtag cmtj s-fc4" href="#" style="display: none;">删除</a> <a
					class="s-fc4 xtag" href="javascript:void(0)"
					onclick="replybtn(this)">回复</a>
			</div>
		</div>
	</div>
</li>