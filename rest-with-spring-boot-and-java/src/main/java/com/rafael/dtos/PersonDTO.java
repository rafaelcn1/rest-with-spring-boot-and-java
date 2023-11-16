package com.rafael.dtos;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rafael.model.Person;

@JsonPropertyOrder({"id", "firstName", "lastName", "address", "gender"}) // Selecionar a ordem da resposta do json
public class PersonDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@JsonProperty("fist-name")
	private String firstName;

	private String lastName;

	private String address;

	private String gender;

	public PersonDTO() {
	}

	public PersonDTO(Person person) {
		this.id = person.getId();
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.address = person.getAddress();
		this.gender = person.getGender();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, firstName, gender, id, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonDTO other = (PersonDTO) obj;
		return Objects.equals(address, other.address) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(gender, other.gender) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName);
	}

}
