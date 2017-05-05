<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  
		<c:forEach items="${loadmorelistTopicModel }" var="topicModel" varStatus="num">
					<div class="m-mlist" id="mlist ${topicModel.topicid }"
						style="display:block">
						<div class="mlistimg js-673810">

								<a href="homepage.action?op=show&userid2=${topicModel.userid }">
									<div class="w-img" style="z-index:1;">
										<img class="js-673810" src="images/${topicModel.head }">
									</div> </a>

						</div>
						<div class="mlistcnt">
							<div class="isay">
									<div class="isayt">
										<a class="isayc js-673810" target="_blank"
											title="查看全文 - 05/05 13:46" href="#">打开新页</a>
									</div>

								<div class="isaym">
									<div class="w-who">
										<a class="js-673810" target="_blank" href="homepage.action?op=show&userid2=${topicModel.userid }">${topicModel.nickname
											}</a> 
								<c:if test="${topicModel.userid==0 }">			
											<a
											class="js-673810 w-icn2 w-icn2-3 a-followicon icontag1889734 ${topicModel.userid}"
											href="javascript:void(0)" onclick="dofocus(this)" title="关注  ${topicModel.nickname
											} ${topicModel.userid}">关注 </a>
								</c:if>
									</div>
									<div class="js-673810">
										<div class="m-icnt">
											<div class="cnt">
											<c:if test="${topicModel.topictype!='文字'  }">
												<div class="img js-800786">
													<div class="imgc" style="height: auto;">
														<img id="img" width="164" src="${topicModel.picid }"
															onclick="imgclick(this)"> 
													</div>
												</div>
												</c:if>
												<div class="txt js-800786">${topicModel.content }</div>
											</div>
											<input type="hidden" name="collectRecord" value=""/>
											<input type="hidden" name="praiseRecord" value=""/>
										</div>
									</div>

								<!-- 每循环一个Topic都去请求一次数据  -->
									<script>
									
									</script>

									<div class="w-opt">
										<div class="opta js-673810" style="width: 176px;"></div>
										<div class="optb">
											<span class="opti"> 热度(${topicModel.praisecount }) <span
												class="opticrt"></span> </span> <span class="opti opti-crt"
												id="opti opti-crt"> <a class="js-673810"
												hidefocus="true"
												href="javascript:showReply('${topicModel.topicid }')">评论(${topicModel.replycount
													})</a> <span class="opticrt" style="display:none;" id="opticrt"></span>
											</span> 
											<c:choose>  
  												<c:when test="${topicModel.collectrecord=='1' }"> 
  													<span class="opti opti-shareTo"> 
	  													<a class="js-673810 collect" hidefocus="true" href="javascript:void(0)" onclick="collect(this)">已收藏</a> 
	  													<span class="opticrt"></span>
													</span>   
  												</c:when>  
     
   												<c:otherwise> 
   													<span class="opti opti-shareTo"> <a class="js-673810 collect"
													hidefocus="true" href="javascript:void(0)" onclick="collect(this)">收藏</a> <span class="opticrt"></span>
													</span>  
   												</c:otherwise>  
											</c:choose>
											
											<c:choose>  
  												<c:when test="${topicModel.praiserecord=='1' }"> 
  													<span class="opti"> <a
													class="w-icn w-icn-0b js-673810 w-icn-0b-do" href="javascript:void(0)"
													hidefocus="true" title="喜欢${topicModel.praiserecord }" onclick=praise(this)> 喜欢 <span></span>
													<span></span> </a> 
													</span>  
  												</c:when>  
     
   												<c:otherwise> 
   													<span class="opti"> <a
													class="w-icn w-icn-0b js-673810 " href="javascript:void(0)"
													hidefocus="true" title="取消喜欢${topicModel.praiserecord }" onclick=praise(this)> 取消喜欢 <span></span>
													<span></span> </a> 
													</span> 
   												</c:otherwise>  
											</c:choose>
											
										</div>
									</div>
								</div>
								
								<%
									session.removeAttribute("collectRecord");
									session.removeAttribute("praiseRecord");
								 %>

								<!-- 评论板块 -->
								<div class="a-isaym2" id="isaym2">
									<div class="isaym2 isaym2-open js-673810"
										style="margin-top:-81px;"></div>
								</div>


								<div class="a-isaym2 a-isaym2-do" style="display:none;"
									id="${topicModel.topicid }">

									<div id="showreplyinfo">
										<div class="isaym2 isaym2-open js-673810"
											style="margin-top: 0px;">
											<div>
												<div class="shadow"></div>

												<div class="isaymin">
													<div class="isayi add">
														<textarea name="textarea" id="textarea"
															style='overflow-y: hidden;width: 390px;padding:9px;min-height:40px;height:40px;line-height:20px;float:left;'
															onpropertychange="this.style.height = this.scrollHeight + 'px';"
															oninput="this.style.height = this.scrollHeight + 'px';"></textarea>
														<button class="w-bbtn xtag w-bbtn-0"
															onClick="publishReply()">发 布</button>
													</div>

													<!-- 回复的内容 -->
													<div class="m-cmt">
														<div class="a-show a-show-cmtul a-show-do"
															style="height:this.scrollHeight + 'px'">
															<ul class="xtag">
																<li class="a-slide a-slide-do">
																	<div class="cmti slide xtag" style="margin-top: 0px;">
																		<div class="w-img2 w-img2-2 noshortkey">
																			<a target="_blank" href="#"
																				title="余文森✿Vincent - 1小时前"><img class="xtag"
																				src="http://imgsize.ph.126.net/?imgurl=http://img0.ph.126.net/LFqLUHo7O1XBdABY5Y3LiA==/1329687789999207597.jpg_20x20x0.jpg">
																			</a>
																		</div>
																		<div class="cmtcnt">
																			<div class="cmthot">
																				<span class="cmtusr"> <a class="s-fc4 xtag"
																					target="_blank" href="http://beeeeeeeee.lofter.com"
																					title="余文森✿Vincent - 1小时前">余文森✿Vincent</a> </span> <span
																					class="xtag">维港海傍~</span>
																			</div>
																			<div class="cmtopt">
																				<a class="cmtj s-fc4 xtag" href="#">加黑</a> <a
																					class="xtag cmtj s-fc4" href="#"
																					style="display: none;">删除</a> <a class="s-fc4 xtag"
																					href="replybtn()">回复</a>
																			</div>
																		</div>
																	</div>
																</li>
															</ul>
														</div>
													</div>
													<!-- 回复的内容 -->

													<div class="isayi">
														<a class="w-more2 xtag" href="#" style="display: none;">
															<span>查看更多</span> </a>
													</div>
													<div class="more" style="">
														<div class="isayi">
															<a class="w-more w-more-close xtag"
																href="javascript:hideReply(${topicModel.topicid })">收起</a>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>


								<div class="a-isaym2 a-isaym2-shareTo">
									<div class="isaym2 isaym2-open js-673810"
										style="margin-top:-125px;"></div>
								</div>
								<div class="isayb"></div>
							</div>

						</div>

					</div>

			</c:forEach>	
		
</div>
		
  
  <body>
   
  </body>
</html>
