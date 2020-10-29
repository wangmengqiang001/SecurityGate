$(document).ready(function() {
    $.ajax({
		xhrFields: { withCredentials: true },
		//dataType: 'json',
        //url: "http://localhost:8090/restgreeting/greeting"
		url: "http://guizhou:8072/restg/greeting"
		//url: "http://guizhou:8090/greeting"
		
		
    }).then(function(data, status, jqxhr) {
       $('.greeting-id').append(data.id);
       $('.greeting-content').append(data.content);
	  
       console.log(jqxhr);
    });
	
	 $.ajax({
		xhrFields: { withCredentials: true },
		//dataType: 'json',
        //url: "http://localhost:8090/restgreeting/greeting"
		//url: "http://guizhou:8072/restg/greeting"
		//url: "http://guizhou:8090/greeting"
		url: "http://guizhou:8072/newbook/available"
		
    }).then(function(data, status, jqxhr) {
       
	   $('.datavalue').append(data);
       console.log(jqxhr);
    });
	
	
});