package net.javaguides.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "fisrt name not null.please enter in your form")
	@Size(min = 3, max = 50, message = "length min >=3 and lenth max <= 50")
	private String firstName;

	@NotBlank(message = "last name not null.please enter in your form")
	@Size(min = 3, max = 50, message = "length min >=3 and length max <=50 ")
	private String lastName;

	@NotBlank(message = "email not null.please enter in your form")
	@Size(min = 3, max = 50, message = "length min >=3 and length max <=50")
	@Email(message = "not email")
	private String emailId;

	@NotBlank(message = "password not null.please enter in your form")
	@Size(min = 4, message = "length min >=4")
	@Column(length = 255)
	private String passWord;

	@Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\b", message = "phoneNumber contains only Numbers")
	@Size(min = 10,max = 10, message = "phoneNumber have length 10 charater")
	@Column(length = 20)
	private String phoneNumber;

	public Employee() {

	}
	

	public Employee(
			@NotBlank(message = "fisrt name not null.please enter in your form") @Size(min = 3, max = 50, message = "length min >=3 and lenth max <= 50") String firstName,
			@NotBlank(message = "last name not null.please enter in your form") @Size(min = 3, max = 50, message = "length min >=3 and length max <=50 ") String lastName,
			@NotBlank(message = "email not null.please enter in your form") @Size(min = 3, max = 50, message = "length min >=3 and length max <=50") @Email(message = "not email") String emailId,
			@NotBlank(message = "password not null.please enter in your form") @Size(min = 4, message = "length min >=4") String passWord,
			@Pattern(regexp = "[0-9]+", message = "phoneNumber contains only Numbers") String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.passWord = passWord;
		this.phoneNumber = phoneNumber;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
