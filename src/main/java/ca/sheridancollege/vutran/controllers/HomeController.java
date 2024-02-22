package ca.sheridancollege.vutran.controllers;

import java.util.ArrayList;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import ca.sheridancollege.vutran.bean.Account;
import ca.sheridancollege.vutran.service.AuthenticationService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	
	final private String REST_URL = "http://localhost:50000/account";
	private AuthenticationService authenticationService;
	
	@GetMapping("/") 
    public String index(Model model, RestTemplate restTemplate) {
        ResponseEntity<ArrayList<Account>> responseEntity =
                (ResponseEntity<ArrayList<Account>>) authenticationService.standardRequest(restTemplate, REST_URL, HttpMethod.GET, new ArrayList<Account>().getClass());
        model.addAttribute("accountList", responseEntity.getBody());
        return "index";
    }
	
	@GetMapping("/blankForm")
    public String blankForm(Model model) {
        model.addAttribute("account", new Account());
        return "blankForm";
    }
	
	@PostMapping("/insert")
    public String insertStudent(Model model, @ModelAttribute Account a, RestTemplate restTemplate) {
		authenticationService.postRequest(restTemplate, REST_URL, a, new Account().getClass());
        ResponseEntity<ArrayList<Account>> responseEntity =
        (ResponseEntity<ArrayList<Account>>) authenticationService.standardRequest(restTemplate, REST_URL, HttpMethod.GET, new ArrayList<Account>().getClass());
        model.addAttribute("accountList", responseEntity.getBody());
        return "index";
    }
	
	@GetMapping("/delete/{id}")
    public String deleteAccount(Model model, @PathVariable("id") int id, RestTemplate restTemplate) {
		authenticationService.standardRequest(restTemplate, REST_URL + "/" + id, HttpMethod.DELETE, new String().getClass());
        ResponseEntity<ArrayList<Account>> responseEntity =
        (ResponseEntity<ArrayList<Account>>) authenticationService.standardRequest(restTemplate, REST_URL, HttpMethod.GET, new ArrayList<Account>().getClass());
        model.addAttribute("accountList", responseEntity.getBody());
        return "index";
    }
	
	@GetMapping("/update/{id}")
    public String getAccount(Model model, @PathVariable("id") int id, RestTemplate restTemplate) {
		ResponseEntity<Account> responseEntity = 
	            (ResponseEntity<Account>) authenticationService.standardRequest(restTemplate, REST_URL +
	            "/" + id, HttpMethod.GET, Account.class);
        model.addAttribute("account", responseEntity.getBody());
        return "blankForm";
    }
	
	@PostMapping("/update")
    public String editAccount(Model model, @ModelAttribute Account a, RestTemplate restTemplate) {
		authenticationService.putRequest(restTemplate, REST_URL + "/" + a.getId(), a);
        ResponseEntity<ArrayList<Account>> responseEntity =
                    (ResponseEntity<ArrayList<Account>>) authenticationService.standardRequest(restTemplate, REST_URL, HttpMethod.GET, new ArrayList<Account>().getClass());
        model.addAttribute("accountList", responseEntity.getBody());
        return "index";
    }

}
