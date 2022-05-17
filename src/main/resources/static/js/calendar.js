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
			getData.textSpeak(getData.getDayOfWeek(info.dateStr), {
			    rate : 1,
			    pitch: 1.2
			});
			post('/getTodayPlan', params, function(plan){
				$("#title").val(plan.title);
				$("#content").val(plan.content);
			});
		},
		eventSources: [
		    {
                events: getData.getMonthData(date),
                backgroundColor: '#1d9895',
                borderColor: '#1d9895'
		    }
		]
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
			Swal.fire({
              title: '정말로 삭제 하시겠습니까?',
              text: "삭제 시 복구가 불가능 합니다.",
              icon: 'warning',
              showCancelButton: true,
              confirmButtonColor: '#3085d6',
              cancelButtonColor: '#d33',
              cancelButtonText: '취소',
              confirmButtonText: '삭제'
            }).then((result) => {
              if (result.isConfirmed) {
                 post('/deletePlan', params, function(result){
                     if(result > 0){
                         alertModal("삭제에 성공했습니다.", "success", 's');
                     }else{
                         alertModal("삭제에 실패했습니다.", "error", 'e');
                     }
                 });
              }
            })

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
				type:'post',
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
	},

	textSpeak : function(dayOfWeek, opt_prop){
	    if (typeof SpeechSynthesisUtterance === "undefined" || typeof window.speechSynthesis === "undefined") {
            alert("이 브라우저는 음성 합성을 지원하지 않습니다.")
            return
        }

        window.speechSynthesis.cancel();

        const prop = opt_prop || {};

        const speechMsg = new SpeechSynthesisUtterance();
        speechMsg.rate = prop.rate || 1;
        speechMsg.pitch = prop.pitch || 1;
        speechMsg.text = dayOfWeek;

        window.speechSynthesis.speak(speechMsg);
	},

	getDayOfWeek : function(date){
	    let that = this;

	    let week = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
	    let dayOfWeek = week[new Date(date).getDay()];

	    return dayOfWeek;
	},

	selectPage : function(page){
	    let params = {
	        page : page
	    }

	    $.ajax({
            type:'post',
            url:'/select/pageNumber',
            data: params,
            dataType:'text',
            async: false,
            success: function(data){
                $(".profile__comments").empty();
                $(".profile__comments").append(data);
            },
            error: function(request, status, error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
	},

	modifyPlan : function(date){
	    let params = {
	        start : date
	    }
	    post('/getTodayPlan', params, function(data){
	        $("#exampleModal").modal('show');
            $("#exampleModalLabel").html(data.start+' 한 일');
            $("#start").val(data.start);
            $("#title").val(data.title);
            $("#content").val(data.content);

	    })
	},

	searchTitle : function(){
	    let params = {
	        search : $('#searchTitle').val()
	    }

        $.ajax({
            type:'post',
            url:'/searchPlan',
            data: params,
            dataType:'text',
            async: false,
            success: function(data){
                $(".profile__comments").empty();
                $(".profile__comments").append(data);
            },
            error: function(request, status, error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
	}
}