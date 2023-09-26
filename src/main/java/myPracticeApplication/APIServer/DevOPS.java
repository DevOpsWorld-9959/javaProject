package myPracticeApplication.APIServer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DevOPS {
	
    @GetMapping("/devops")
    public String welcomeMessage() {
    	return "<h1>Hello Mulasofttechnologies  this is tesing getting tools  this is new tesgin code get code</h1>" ;
    }
    @GetMapping("/devops/1")
    public String welcomeMessages() {
    	return "Hello Mulasofttechnologies  this is tesing getting tools tis is tesicjselkrae skdjflksdjflksdflksdf";
    }	
}
