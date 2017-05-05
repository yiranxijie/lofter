<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="g-mn" style="background:#fff;">
	<div class="g-box3">
		<h2 class="w-fttl">其他用户(<span class="ztag">${alluserscount }</span>)</h2>
	</div>
	<div class="g-box2">
		<div class="m-glist">
			<ul class="f-cb ztag" style="background-color:transparent;">
			<c:forEach items="${allusers }" var="user">
				<li>
					<div class="w-img2 w-img2-1">
						<a target="_blank" href="homepage.action?op=show&userid2=${user.id }">
							<img class="xtag" src="images/${user.head }">
						</a>
					</div>
					
					<div class="cnt">
						<h4>
							<em>
								<a class="bname f-thide xtag" target="_blank" href="homepage.action?op=show&userid2=${user.id }">${user.nickname }</a>
							</em>
						</h4>
						<p class="xtag"></p>
					</div>
					
					<c:choose>  
						<c:when test="${user.focusrecord=='1' }"> 
							<a class="xtag w-gz w-gz-1" title="已关注" href="#" style="display:none;">已关注</a>
						</c:when>  

						<c:otherwise> 
							<a class="xtag w-gz w-gz-1 ${user.id }" title="添加关注" href="javascript:void(0)" onclick="addfocus(this)" style="display:block;">添加关注</a>	
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














