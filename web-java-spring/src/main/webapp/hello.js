$(document).ready(function() {
    $.ajax({
		xhrFields: { withCredentials: true },
		url: "http://guizhou:8072/restg/greeting"
		
		
    }).then(function(data, status, jqxhr) {
       $('.greeting-id').append(data.id);
       $('.greeting-content').append(data.content);
	  
       console.log(jqxhr);
    });
	
	 $.ajax({
		xhrFields: { withCredentials: true },
		url: "http://guizhou:8072/newbook/available"
		
    }).then(function(data, status, jqxhr) {
       
	   $('.datavalue').append(data);
       console.log(jqxhr);
    });
	
	
});
