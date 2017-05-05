<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${topiclist}" var="topic" varStatus="num">
			<div class="block photo" id="topic${topic.id}">
				<div class="side">
					<div class="day">
						<a href="#">${topic.day }</a>
					</div>
					<div class="month">
						<a href="#">${topic.month }</a>
					</div>
				</div>
				<div class="main">
					<div class="content">
						<div class="img">
							<a href="#"> <img src="${topic.picid }">
							</a>
						</div>
						<div class="text">
							<p>${topic.content }</p>
						</div>
					</div>

					<div class="link">
						<a href="#">热度(${topic.like })</a> <a href="#">全文链接</a>

						<c:if test="${set==true }">
							<a onclick="doDelete('${topic.id }')">删除</a>
						</c:if>
					</div>
				</div>
			</div>
</c:forEach>