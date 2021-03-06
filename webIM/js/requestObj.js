//实现socket 一个请求一个回调
var requestObj= new function(){
	var SYNC=0;
	var _self=this;

	var requestID=100;
	var requestMap={};
	var packageNum={};

	var noticeCallBack={
	};

	/*
	 创建webSocket
	 */
	var Chat = new function(){
		var that=this;
		this.socket=null;
		this.connect=function(host){
			if ('WebSocket' in window) {
				that.socket = new WebSocket(host);
			} else if ('MozWebSocket' in window) {
				that.socket = new MozWebSocket(host);
			} else {
				return;
			}
			that.socket.onopen = function () {
				console.log('Info: WebSocket onopen.');
			};

			that.socket.onclose = function () {
				console.log('Info: WebSocket closed.');
				that.initialize();
			};

			that.socket.onmessage = function (message) {
				console.log('Info: WebSocket onmessage.');
				exchangeData(message.data);
			};
		};
		this.initialize=function(){
			if(window.location.protocol=="https:"){
				this.connect('wss://172.16.192.1:9999');
			}else{
				this.connect('ws://172.16.192.1:9999');
			}
			//nginx地址
			//this.connect('ws://172.16.192.1/im');
		}
	};

	//判断包是否是错误的返回包
	function filterResult(Sub){

	};

	//路由ACK消息还是通知消息
	function exchangeData(data){
		var Sub = _self.fomatResult(data);
		if(Sub[0]=="ACK"&&_self.contains(Sub[4])){
			requestObj.receive(Sub);
		}else{
			//通知消息
			console.log(Sub)

			requestObj.receiveNotice(Sub);

		}
	}

	//关闭socket
	this.close=function(){
		Chat.socket.close();
	};


	this.send=function(content,callback){

		console.log("发送请求："+content);
		Chat.socket.send(content);
		requestMap[requestID+""]=callback;
	};
	this.contains=function(id){
		if(id in requestMap){
			return true;
		}else{
			return false;
		}
	};
	this.getRequestID=function(){
		return ++requestID;
	};
	this.fomatResult=function(result){
		var reg=new RegExp("\t","g"); //创建正则RegExp对象
		var r=result.replace(reg,"$");
		var Sub=r.split("$",-1);
		filterResult(Sub);
		return Sub;
	};

//接受ack消息
	this.receive=function(Sub){
		console.log("接受数据："+Sub);
		var random=Sub[4];
		if(packageNum[random+""]==undefined){
			packageNum[random+""]={};
			packageNum[random+""]['Subs']=[];
		}
		packageNum[random].Subs.push(Sub);
		var callback = requestMap[random+""];
		callback(packageNum[random].Subs);
		delete packageNum[random];
		return;
	};

    //接受notice消息
    this.receiveNotice=function(Sub){
        console.log("notice接受数据："+Sub);
        var cmd=Sub[1];
        var syncNO = Sub[4];
        if(syncNO!==-1){
            var reqID= ++requestID;
            if(syncNO-SYNC>1){
                //发送sync包
                var message = 'REQ\tCMD_SYNC\t'+user.id+'\t00001\t'+reqID+"\t"+"0\t"+SYNC;
                requestObj.send(message,function () {});
                //return;
            }else{
                //发送FIN包
                var message = 'REQ\tCMD_FIN\t'+user.id+'\t00001\t'+reqID+"\t"+"0\t"+SYNC;
                requestObj.send(message,function () {})
            }
            SYNC=syncNO;
		}
        noticeCallBack[cmd](Sub);
        return;
    };

    this.registerNoticeCallback = function(cmd,callback){
        noticeCallBack[cmd]=callback;
	}

	Chat.initialize();

    this.setSync = function(sync){
        SYNC=sync;
    }
};
