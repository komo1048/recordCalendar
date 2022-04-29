$('DOMcontentLoaded', function(){
	var calendarEl = document.getElementById('calendar');
	var calendar = new FullCalendar.Calendar(calendarEl, {
	    dateClick: function(info){
			$("#exampleModal").modal('show');
			$("#exampleModalLabel").html(info.dateStr+' 한 일');
			$("#insertDate").val(info.dateStr);
		},
	});
	calendar.render();
})
