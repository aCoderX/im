/**
 * Created by xudi on 17-4-24.
 */
var user={};
var friendsInfo={};
var chatRecord={};
var nowChat;

var noticeCall = {
    "CMD_TEXT":function (sub) {
        var targetId = nowChat;
        var id = sub[2];
        var li = "";
        if(id==user.id){
            //多终端同步消息
            id=sub[3];
            li = '<li class="list-group-item my_text">'+sub[6]+'</li>';
        }else{
            li = '<li class="list-group-item">'+sub[6]+'</li>';
        }
        if(undefined==chatRecord[id+""]){
            chatRecord[id+'']=[];
        }
        chatRecord[id+''].push(li);
        if(targetId==id){
            $('#chatPanel .list-group').append(li);
        }
    }
};

//注册notice回调函数
for(var p in noticeCall){
    requestObj.registerNoticeCallback(p,noticeCall[p]);
}


function checkFriend(id){
    $('#chat_title').html(friendsInfo[id].name);
    var html="";
    if(chatRecord[id]!=undefined){
        for(var item in chatRecord[id]){
            html+=chatRecord[id][item];
        }
    }
    nowChat=id;
    $('#right_panel .list-group').html(html);
    if(!$('#right_panel').is(":visible")){
        $('#right_panel').show();
    }
}


function init() {
    var reqId = requestObj.getRequestID();
    var message = 'REQ\tCMD_INIT\t'+user.id+'\t00001\t'+reqId+"\t"+"0\t";
    requestObj.send(message,function(subs){
        var res = subs[0];
        console.log(res);
        for(var i=6;i<res.length;i++){
            if(res[i]==""){
                continue;
            }
            var s = res[i].split('|');
            friendsInfo[s[0]+'']={
                'id':s[0],
                'name':s[1]
            };
        }
        var html="";
        for(var p in friendsInfo){
            html+='<button type="button" onclick="checkFriend('+p+')" class="list-group-item">'+friendsInfo[p].name+'</button>'
        }
        $('#friends').html(html);
        $('#login').hide();
        $('#main').show();
    })
}
$('#login_btn').click(function () {
    var phone = $('#phone').val();
    var password = $('#password').val();
    var reqId = requestObj.getRequestID();
    var message = 'REQ\tCMD_LOGIN\t'+phone+'\t00001\t'+reqId+"\t"+"0\t"+phone+"\t"+password;
    requestObj.send(message,function(subs){
        var res = subs[0];
        console.log(res)

        if(res[6]=="S"){
            alert("welcome"+res[7])
            user.name = res[7];
            user.id = res[3];
            init();
        }else{
            alert(res[7]);
        }

    })
})
$('#send').click(function() {

    var targetId = nowChat;
    var content = $('#content_text').val();

    if(undefined==chatRecord[targetId+""]){
        chatRecord[targetId+'']=[];
    }
    $('#content_text').val("");
    var li = '<li class="list-group-item my_text">'+content+'</li>';
    chatRecord[targetId+''].push(li);
    $('#chatPanel .list-group').append(li);
    var reqId = requestObj.getRequestID();
    var message = 'NOTICE\tCMD_TEXT\t'+user.id+'\t'+targetId+'\t'+reqId+"\t"+"0\t"+content+"\t";
    requestObj.send(message,function(subs){
        //alert("success")
    });
});
