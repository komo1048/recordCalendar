let date = new Date(+new Date() + 3240 * 10000).toISOString().split("T")[0];
let that = this;
$('DOMcontentLoaded', function(){
	let getList = getData.getMonthData(date);
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
		events: getList,
	});
	getData.getPrevNextMonth();
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
	getPrevNextMonth: function(){
		$(document).on("click", "#calendar .fc-next-button", function(){
			let nextMonth = new Date();
			that.date = new Date(new Date().setMonth(nextMonth.getMonth()+1)).toISOString().split("T")[0];
		});
	
		$(document).on("click", "#calendar .fc-prev-button", function(){
			let prevForDate = new Date();
			that.date = new Date(new Date().setMonth(prevForDate.getMonth()-1)).toISOString().split("T")[0];
		});
	}
}
