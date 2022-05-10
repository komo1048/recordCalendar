function alertModal(text, title, icon) {
	Swal.fire({
		title		: title,
		text		: text,
		icon		: getAlertModalIcon(icon),
		showConfirmButton: false,
		timer: 1500,
		allowOutsideClick: false
	}).then(function(){
		location.reload();
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