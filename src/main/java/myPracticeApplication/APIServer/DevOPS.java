package myPracticeApplication.APIServer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DevOPS {
	
    @GetMapping("/devops")
    public String welcomeMessage() {
    	return "Hello Mulasofttechnologies";
    }
}
