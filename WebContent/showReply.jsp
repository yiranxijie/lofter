<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="isaym2 isaym2-open js-673810" style="margin-top: 0px;" id="${topicid }">
		<div>
			<div class="shadow"></div>

			<div class="isaymin">
				<div class="isayi add">
					<span id="replyspan" style="display:none;float:left;height:30px;left:30px;line-height:30px;padding:5px;position:absolute;">我是超人我会飞</span>
					<textarea name="textarea" id="textarea"
						style='overflow-y: hidden;width: 390px;padding:9px;min-height:40px;height:40px;line-height:20px;float:left;'
						onpropertychange="this.style.height = this.scrollHeight + 'px';"
						oninput="this.style.height = this.scrollHeight + 'px';" onfocus="textareafocus(this)" onkeydown="userkeydown(event,this)" onkeyup="userkeyup(event,this)">
					</textarea>
					<button class="w-bbtn xtag w-bbtn-0" onClick="publishReply(this)">发
						布</button>
						<input type="hidden" name="parentid" value="">
				</div>

				<!-- 回复的内容 -->
				<div class="m-cmt">
					<div class="a-show a-show-do" style="">
						<ul class="xtag">

							<c:forEach items="${replylist }" var="reply">
								<li class="a-slide a-slide-do">
									<div class="cmti slide xtag" style="margin-top: 0px;">
										<div class="w-img2 w-img2-2 noshortkey">
											<a target="_blank" href="#" title="余文森✿Vincent - 1小时前"><img
												class="xtag" src="images/${reply.head }"> </a>
										</div>
										<div id="hiddeninfo">
											<input type="hidden" name="userid" value="${reply.userid }">
										</div>
										<div class="cmtcnt">
											<div class="cmthot">
												<span class="cmtusr"> <a class="s-fc4 xtag"
													target="_blank" href="http://beeeeeeeee.lofter.com"
													title="余文森✿Vincent - 1小时前">${reply.nickname }</a> </span> <span
													class="xtag">${reply.content }</span>
											</div>
											<div class="cmtopt">
												<a class="cmtj s-fc4 xtag" href="#">加黑</a> <a
													class="xtag cmtj s-fc4" href="#" style="display: none;">删除</a>
												<a class="s-fc4 xtag" href="javascript:void(0)" onclick="replybtn(this)">回复</a>
											</div>
										</div>
									</div></li>
							</c:forEach>

						</ul>
					</div>
				</div>
				<!-- 回复的内容 -->

				<div class="isayi">
					<a class="w-more2 xtag" href="#" style="display: none;"> <span>查看更多</span>
					</a>
				</div>
				<div class="more" style="">
					<div class="isayi">
						<a class="w-more w-more-close xtag"
							href="javascript:void(0)" onclick="hidereply(this)">收起</a>
					</div>
				</div>
			</div>
		</div>
	</div>
