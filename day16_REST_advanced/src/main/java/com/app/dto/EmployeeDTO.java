package com.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.app.entities.EmploymentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

// DTO pattern helps separation of POJO(persistent entities) and DTO (for data transfer)
public class EmployeeDTO {
	@JsonProperty(access = Access.READ_ONLY) // used during serialization(skip it during de-seriz)
	private Long id;	
	@NotBlank(message=" Please provide first name")
	private String firstName;
	@NotBlank(message=" Please provide last name")
	private String lastName;	
	@Email(message = "invalid email")
	@Size(min = 5, max = 20)
	private String email;
	@JsonProperty(access =Access.WRITE_ONLY )//skip this prop during serilization (use during de-serz)
	@Pattern(regexp = "((?=.*\\\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "please folllow pattern")
	private String password;	
		private LocalDate joinDate;
	private EmploymentType empType;
	@Range(min =50000, max = 100000 )
	private double salary;
	@JsonProperty(access = Access.WRITE_ONLY)//used during de-ser
	private Long deptId;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 	@JsonProperty(access = Access.READ_ONLY) // used during serialization(skip it duirng de-ser)
	private Long id;
	private String firstName;
	private String lastName;
	@Email(message = "Invalid email !")
	@Size(min = 5, max = 20, message = "Invalid email length!")
	private String email;
	@JsonProperty(access = Access.WRITE_ONLY) // skip this prop during ser. n in use it only during de-ser
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Blank or invalid password!")
	private String password;
	@Future(message="Join date must be in future")
	private LocalDate joinDate;
	private EmploymentType empType;
	@Min(value = 20000,message = "sal must be > 20000")
	@Max(value = 200000,message = "sal must be < 200000")
	private double salary;
	 */
}





