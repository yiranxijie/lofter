var xmlHttp;
	//创建一个xmlHttprequest的对象
	function createXMLHttpRequest() {
	    if (window.XMLHttpRequest) { // 如果可以取得XMLHttpRequest
	        xmlHttp = new XMLHttpRequest();  // Mozilla、Firefox、Safari 
	    }
	    else if (window.ActiveXObject) { // 如果可以取得ActiveXObject
	        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); // Internet Explorer
	    }
	}
	
	function startRequest(url,handleStateChange ) {
	    createXMLHttpRequest(); // 建立非同步請求物件
	    xmlHttp.onreadystatechange = handleStateChange;  // 設定callback函式
	    xmlHttp.open("GET", url);  // 開啟連結
	    xmlHttp.send(null);  // 傳送請求
	}
	
	function startRequestByPost(url,params,handleStateChange) {
	    createXMLHttpRequest(); // 建立非同步請求物件
	    xmlHttp.onreadystatechange = handleStateChange;  // 設定callback函式
	    xmlHttp.open("POST", url);  // 開啟連結
	    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	    xmlHttp.send(params);  // 傳送請求
	}
	
	function $(elementId){
		return document.getElementById(elementId);
	}