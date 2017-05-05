<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="g-mn" style="background:#fff;">
	<div class="g-box3">
		<h2 class="w-fttl">粉丝(<span class="ztag">${fanscount }</span>)</h2>
	</div>
	<div class="g-box2">
		<div class="m-glist">
			<ul class="f-cb ztag" style="background-color:transparent;">
			<c:forEach items="${fanslist }" var="fans">
				<li>
					<div class="w-img2 w-img2-1">
						<a target="_blank" href="homepage.action?op=show&userid2=${fans.id }">
							<img class="xtag" src="images/${fans.head }">
						</a>
					</div>
					
					<div class="cnt">
						<h4>
							<em>
								<a class="bname f-thide xtag" target="_blank" href="homepage.action?op=show&userid2=${fans.id }">${fans.nickname }</a>
								<a class="rem s-fc1 xtag ${fans.id }" href="javascript:void(0)" onclick="removefocus(this)">移除粉丝</a>
							</em>
						</h4>
						<p class="xtag"></p>
					</div>
					
					<c:choose>  
						<c:when test="${fans.focusrecord=='1' }"> 
							<a class="xtag w-gz w-gz-1" title="已关注" href="#" style="display:none;">已关注</a>
						</c:when>  

						<c:otherwise> 
							<a class="xtag w-gz w-gz-1 ${fans.id }" title="添加关注" href="javascript:void(0)" onclick="addfocus(this)" style="display:block;">添加关注</a>	
						</c:otherwise>  
					</c:choose>
											
				</li>
			</c:forEach>
			</ul>
		</div>
	</div>
	
	<div class="ztag" style="">
		<div class="m-page ui-8869779373 noselect" style="display:none;"></div>
	</div>
</div>














