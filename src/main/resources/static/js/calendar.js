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
			}
			$.ajax({
				type:'get',
				url:'/getTodayPlan',
				data: params,
				success: function(plan){
					$("#title").val(plan.title);
					$("#content").val(plan.content);
				}
			})
		},
		events: getData.getMonthData(date),
	});
	calendar.render();
});
let getData = {
	
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


