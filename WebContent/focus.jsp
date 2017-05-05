<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="g-mn" style="background:#fff;">
	<div class="g-box3">
		<h2 class="w-fttl">我关注的人(<span class="ztag">${focuscount }</span>)</h2>
	</div>
	<div class="g-box2">
		<div class="m-glist">
			<ul class="f-cb ztag" style="background-color:transparent;">
			<c:forEach items="${focuslist }" var="focus">
				<li>
					<div class="w-img2 w-img2-1">
						<a target="_blank" href="homepage.action?op=show&userid2=${focus.id }">
							<img class="xtag" src="images/${focus.head }">
						</a>
					</div>
					
					<div class="cnt">
						<h4>
							<em>
								<a class="bname f-thide xtag" target="_blank" href="homepage.action?op=show&userid2=${focus.id }">${focus.nickname }</a>
								<a class="rem s-fc1 xtag ${focus.id }" href="javascript:void(0)" onclick="cancelfocus(this)">取消关注</a>
							</em>
						</h4>
						<p class="xtag"></p>
					</div>
					<a class="xtag w-gz w-gz-1" title="添加关注" href="#" style="display:none;">添加关注</a>
				</li>
			</c:forEach>
			</ul>
		</div>
	</div>
	
	<div class="ztag" style="">
		<div class="m-page ui-8869779373 noselect" style="display:none;"></div>
	</div>
</div>














