function getId(id){
    return document.getElementById(id);
}

var data = {};

var ws ;
var username = $('#username').text();
var btnSend = getId('btnSend');
var talk = getId('talk');
var msg = getId('msg');

$(document).ready(function(){
    ws = new WebSocket("ws://" + location.host + "/chat");

    ws.onmessage = function(msg){
        var data = JSON.parse(msg.data);
        var css;

        if(data.username == username){
            css = 'class=me';
        }else{
            css = 'class=other';
        }

        var item = `<div ${css} >
                    <span><b>${data.username}</b></span> [ ${data.date} ]<br/>
                     <span>${data.msg}</span>
                    </div>`;

        talk.innerHTML += item;
        talk.scrollTop = talk.scrollHeight;
    }
})

msg.onkeyup = function(ev){
    if(ev.keyCode == 13){
        send();
    }
}

btnSend.onclick = function(){
	send();
}

function send(){
	if(msg.value.trim() != ''){
		data.username = $('#username').text();
		data.msg = msg.value;
		data.date = new Date().toLocaleString();
		var temp = JSON.stringify(data);
		ws.send(temp);
	}
	msg.value ='';

}
