package ca.sheridancollege.vutran.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class Account {
	private Long id;
	private String name;
	private String gender;
	private boolean subscribed;
	private String country;
	private String message; 
}


/*
[
 {
	 "name": "David",
	 "gender": "male", 
	 "subscribed": true,
	 "country": "US", 
	 "message": "abc"
 },
 {
	 "name": "Linda",
	 "gender": "female", 
	 "subscribed": false,
	 "country": "Canada", 
	 "message": "msn"
 }
]
*/
