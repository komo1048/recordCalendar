function post(url, param, onSuccess) {
   return ajax(url, param, "post", onSuccess);
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