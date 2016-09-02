/**
 *
 * @param username
 * @param tags
 * @param callback
 */
function realtimeUpdateData(pageurl,serveruri,username,tags,callback){

  var srcurl = pageurl;
  var currenturl=window.location.href;

  if(serveruri == null || serveruri==""){
    alert("请输入服务器URI地址");
    return;
  }
  if(username == null || username==""){
    alert("调用WS,需要登录用户的用户名");
    return;
  }
  if(tags == null || tags==""){
    alert("调用WS,需要消息标签");
    return;
  }
  if(callback == null || callback==""){
    alert("请加入您自己的回调函数");
    return;
  }

  var websocket;
  if ('WebSocket' in window) {
    //console.log("WebSocket");
    //websocket = new WebSocket("ws://127.0.0.1:8080/ws/echo.ws?username=zhoudi&tags=testmsgtag1");
    websocket = new WebSocket("ws://"+serveruri+"/ws/echo.ws?username="+username+"&tags="+tags);
  } else if ('MozWebSocket' in window) {
    //console.log("MozWebSocket");
    websocket = new MozWebSocket("ws://echo");
  } else {
    //console.log("SockJS");
    //websocket = new SockJS("http://127.0.0.1:8080/ws/sockjs/echo.ws?username=zhoudi&tags=testmsgtag1");
    websocket = new WebSocket("ws://"+serveruri+"/ws/sockjs/echo.ws?username="+username+"&tags="+tags);
  }
  websocket.onopen = function (evnt) {
    console.log("连接socket服务器成功!");
  };
  websocket.onerror = function (evnt) {
    console.log("连接socket服务器发生错误!");
  };
  websocket.onclose = function (evnt) {
    console.log("与socket服务器的连接断开!");
    //alert("与socket服务器的连接断开,请重连!");
  };

  //对返回数据进行的处理
  websocket.onmessage = function (evnt) {
    var currenturl=window.location.href;
    if(srcurl != currenturl){
      console.log("页面:"+srcurl+",已被关闭,其wsebsocket无需再运行,将关闭");
      websocket.close();
      return
    }


    var serverdata = evnt.data;
    //console.log(serverdata);

    //回调函数
    (callback && typeof(callback) === "function") && callback(serverdata);

  };

  //websocket.close()
  return websocket;
}
