<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="main" class="g-mn">
			<div id="publishBarArea" class="publishBarArea" style="display:block">
				<ul id="publishPostBar" class="m-nav2" style="">
					<li class="user"><a href="homepage.action?op=show&userid2=${user.id }"><img
							src="images/${user.head }"> </a>
					</li>
					<li><a class="publishlink n21"
						href="javascript:showPublishtext()"
						bakhref="/blog/balagengaobaxiong/new/text/">文字</a></li>
					<li><a class="publishlink n22"
						href="javascript:showPublishpic()"
						bakhref="/blog/balagengaobaxiong/new/photo/">图片</a></li>
					<li><a class="publishlink n23" href="#publish=music"
						bakhref="/blog/balagengaobaxiong/new/music/">音乐</a></li>
					<li><a class="publishlink publishlink-video n24"
						href="#publish=video" bakhref="/blog/balagengaobaxiong/new/video/">视频</a>
					</li>
				</ul>

				<!-- 发布纯文字 -->
				<div id="publishtext" style="display:none">

					<div class="blogTittle">陶钧</div>
					<form id="formtext">
						<input type="hidden" name="userid" value=${user.getId() } id="userid">
						<input type="hidden" name="topictype" value="文字" id="topictype">

					</form>
						<div>


							<div class="blogTittle">
								<input type="text" name="title" id="title" class="title">
								<label>标题（可不填）</label>
							</div>
							<textarea rows="10" cols="50" name="content" id="content"></textarea>
							<script type="text/javascript">
								CKEDITOR.replace('content');
							</script>
							
							<div class="blogBtn">
								<ul class="blogul">
									<li class="blogli"><button class="bloginp"
											onclick="hidePublishtext()">取消</button>
									</li>
									<li class="blogli previewBtn"><button class="bloginp">预览</button>
									</li>
									<li class="blogli previewBtn"><button class="bloginp"
											onclick="sendMessage()" id="btntext">发布</button>
									</li>

								</ul>
							</div>

						</div>
					

				</div>
				<!-- 发布图文 -->
				<div id="publishpic" class="isaym" style="display:none">
					<div class="publishpic_nickname">
						<label>昵称</label>
					</div>

						<input type="hidden" name="userid" value=${user.id } id="userid">
						<input type="hidden" name="topictype" value="图文" id="topictype">
					<div class="imgupload">
						<div id="demo" class="demo" ></div>
						<div
							style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">

						</div>

					</div>

					<div>
						<div class="editor" id="editorpic">
							<textarea rows="10" cols="50" name="content" id="contentpic"></textarea>
							<script type="text/javascript">
								CKEDITOR.replace('contentpic');
							</script>
						</div>

						<div class="blogBtn">
							<ul class="blogul">
								<li class="blogli"><button class="bloginp"
										onclick="hidePublishpic()">取消</button>
								</li>
								<li class="blogli previewBtn"><button class="bloginp">预览</button>
								</li>
								<li class="blogli previewBtn"><button class="bloginp"
										onclick="sendMessage()" id="btnpic">发布</button>
								</li>

							</ul>
						</div>

					</div>
				</div>
			</div>
<!--                               -->




			<!--隐藏域，假如有没发布的文章，则会自动显示出来-->
			<div style="display:none" id="tempSaveTipArea"
				class="tempSaveTipArea">
				<div class="tempsavetipwrap">
					你还有一篇未发布的文章。<a class="gotemppost" href="#">查看</a><a
						class="closetip icon2-8" href="#" hidefocus="true">&nbsp;</a>
				</div>
			</div>

			<!--通知提示-->
			<div id="noticetip" class="a-slide a-slide-do"></div>

			<!--首次加载的动态效果-->
			<!-- <div class="w-load2" id="loadfirst" >
                <div class="load">加载中</div> 
            </div> -->

			<!-- 消息发布的隐藏域 -->
			<div id="publish" style="dispaly:none;">
				<div class="mlistimg js-673810"></div>

			</div>
			<!-- 图片显示路径掩藏域 -->
		
				<input type="hidden" id="pathpic" >
				<input type="hidden" id="size" value="${size }">
				<input type="hidden" id="page" value="${page }">
				<input type="hidden" id="count" value="${count }">
			<!--显示发布的版块 -->
			<div id="list"></div>
			<!--                               -->
			<!--图文版块-->

<c:forEach items="${personalTopic }" var="tm" varStatus="num">
					<div class="m-mlist m-mlist-self" id="mlist ${tm.topicid }"
						style="display:block">
						<div class="mlistimg js-673810">

								<a href="homepage.action?op=show&userid2=${tm.userid }">
									<div class="w-img" style="z-index:1;">
										<img class="js-673810" src="images/${tm.head }">
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
										<a class="js-673810" target="_blank" href="#">${tm.nickname
											}</a> <a
											class="js-673810 w-icn2 w-icn2-3 a-followicon icontag1889734"
											href="#" title="关注  ${tm.nickname
											}">关注 </a>
									</div>
									<div class="js-673810">
										<div class="m-icnt">
											<div class="cnt">
												<div class="img js-800786">
													<div class="imgc" style="height: auto;">
													
													<c:if test="${tm.picid != ' '}">
														<img id="img" width="164" src="${tm.picid }"
														onclick="imgclick(this)"> <span class="total">
														<span class="totalbg"></span> <span class="totalnum"> </span> </span>
													</c:if>
													</div>
												</div>
												<div class="txt js-800786">${tm.content }</div>
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
											<span class="opti"> 热度(${tm.praisecount }) <span
												class="opticrt"></span> </span> <span class="opti opti-crt"
												id="opti opti-crt"> <a class="js-673810"
												hidefocus="true"
												href="javascript:showReply('${tm.topicid }')">评论(${tm.replycount
													})</a> <span class="opticrt" style="display:none;" id="opticrt"></span>
											</span> 
											<!-- 
  													<span class="opti opti-shareTo"> <a class="js-673810"
													hidefocus="true" href="#">编辑</a> <span class="opticrt"></span>
													</span>   
											 -->
  											<span class="opti"> 
  												<a class="js-295778" href="javascript:void(0)" hidefocus="true" onclick="deletetopic(this)">删除 </a> 
											</span>  
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
									id="${tm.topicid }">

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
																href="javascript:hideReply(${tm.topicid })">收起</a>
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