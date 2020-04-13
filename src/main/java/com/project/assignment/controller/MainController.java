package com.project.assignment.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.project.assignment.model.UserModel;
import com.project.assignment.service.UserService;

@RestController
@RequestMapping
public class MainController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private ObjectMapper mapper;
	
	@Value("${message.user.created}")
	private String usercreatedMessage;
	

	@GetMapping("/user/{id}")
	public ResponseEntity<UserModel> getUser(@PathVariable Long id) {
		return ResponseEntity.ok().body(userService.getUser(id));
	}
	
	@PostMapping("/user")
	public ResponseEntity<String> addUser(@RequestBody UserModel userModel) throws JsonProcessingException, URISyntaxException {
		ResponseEntity<String> response = null;
        JsonNode responseJson = mapper.createObjectNode();
        
        UserModel createdUser = userService.addUser(userModel);
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
    			.buildAndExpand(createdUser.getId()).toUri();
        
        ((ObjectNode) responseJson).put("status", HttpStatus.CREATED.value());
        ((ObjectNode) responseJson).put("message", usercreatedMessage);
        response = ResponseEntity.created(location)
                    .body(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseJson));
        
        

        return response;
	}

}
