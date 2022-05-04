$('DOMcontentLoaded', function(){
	let date = getData.dateFormatChange(new Date());
	var calendarEl = document.getElementById('calendar');
	var calendar = new FullCalendar.Calendar(calendarEl, {
	    dateClick: function(info){
			$("#exampleModal").modal('show');
			$("#exampleModalLabel").html(info.dateStr+' 한 일');
			$("#start").val(info.dateStr);
			
			let params = {
				'start' : info.dateStr
			};
			getData.init();
			post('/getTodayPlan', params, function(plan){
				$("#title").val(plan.title);
				$("#content").val(plan.content);
			});
		},
		events: getData.getMonthData(date),
	});
	getData.initFn();
	calendar.render();
});
let getData = {
	
	init: function(){
		$("#title").val('');
		$("#content").val('');
	},
	
	initFn: function(){
		$('#deletePlan').click(function(){
			let params = {
				'start' : $('#start').val()
			}
			post('/deletePlan', params, function(result){
				if(result > 0){
					alertModal("삭제에 성공했습니다.", "success", 's');
				}else{
					alertModal("삭제에 실패했습니다.", "error", 'e');
				}
			});
		});
		
		$('#workSave').click(function(){
			let params = {
				'start' : $('#start').val(),
				'title' : $('#title').val(),
				'content' : $('#content').val()
			}
			post('/workSave', params, function(result){
				if(result > 0){
					alertModal("등록에 성공했습니다.", "success", "s");
				}else{
					alertModal("등록에 실패했습니다.", "error", "e");
				}
			});
		});
	},
	
	getMonthData : function(date){
		let getMonthData_return;
		let params = {
				'start' : date
			}
			$.ajax({
				type:'get',
				url:'/getMyPlan',
				data: params,
				dataType:'json',
				async: false,
				success: function(plan){
					getMonthData_return = plan;
				},
				error: function(request, status, error){
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
		return getMonthData_return;
	},
	
	dateFormatChange : function(change){
		return change.getFullYear() + "-" + ((change.getMonth() + 1) > 9 ? (change.getMonth() + 1).toString() : "0" + (change.getMonth() + 1)) + "-" + (change.getDate() > 9 ? change.getDate().toString() : "0" + change.getDate().toString());
	}
}

function post(url, param, onSuccess) {
   return ajax(url, param, "get", onSuccess);
}

function ajax(url, param, type, onSuccess) {
	var settings = {
		dataType	: "json",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
	};
	
	return $.ajax({
		url    		: url,
		data   		: param,
		dataType	: settings.dataType,
		contentType : settings.contentType,
		success   	: onSuccess,
		cache 		: false,
		type   		: type,
		async		: false
	});
}

function alertModal(text, title, icon) {
	Swal.fire({
		title		: title,
		text		: text,
		icon		: getAlertModalIcon(icon),
		showConfirmButton: false,
		timer: 1500
	}).then(function(){
		window.location = window.location.pathname;
 	});

}
function getAlertModalIcon(text){
	switch (text) {
	case "w" :
		return "warning";
	case "e" :
		return "error";
	case "s" :
		return "success";

	default :
		return "info";
	}
}