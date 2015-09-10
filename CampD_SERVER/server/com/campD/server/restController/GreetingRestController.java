/**
 * 
 */
package com.campD.server.restController;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.campD.server.data.DemoJsonServer;
import com.campD.server.model.Greeting;

/**
 * @author Administrator
 *
 */

@RestController
@RequestMapping("/demo")
public class GreetingRestController extends BaseRestController{
	
	protected Logger logger = Logger.getLogger(getClass());

	@Autowired
	private DemoJsonServer demoJsonServer;
	
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    @ResponseBody
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
    
    @SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/register", method=RequestMethod.POST)
    @ResponseBody
    public Map register(HttpServletRequest request) {
    	
    	Map reqMap = bindParamToMap(request);
    	Map returnMap = demoJsonServer.register(reqMap);
    	
        return returnMap;
    }
}