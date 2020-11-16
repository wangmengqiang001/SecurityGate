package org.eclipse.che.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.MediaType;

@Controller
@RequestMapping("/rest")  
public class RestGreeting {

	private static final String template = "Hi, %s! How are you ";
	private final AtomicLong counter = new AtomicLong();
    	
	//@RequestMapping(value="greeting",produces = { MediaType.APPLICATION_JSON_VALUE })
        @RequestMapping(value="/greeting",produces="application/json;charset=UTF-8")
    	public @ResponseBody Greeting  greeting(@RequestParam(required = false, defaultValue = "Guys") String name) {
		System.out.println("==== get greeting ====");
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
    


}
