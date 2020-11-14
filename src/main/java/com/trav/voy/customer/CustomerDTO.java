/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.customer;

/**
 *
 * @author Latitude
 */
import java.time.LocalDate;

public class CustomerDTO implements Comparable<CustomerDTO>{
	
//	@ApiModelProperty(value = "Customer id")
	private Integer id;
	
//        @ApiModelProperty(value = "Customer first name")
	private String cni;
        
//	@ApiModelProperty(value = "Customer first name")
	private String firstName;
	
//	@ApiModelProperty(value = "Customer last name")
	private String lastName;
	
//	@ApiModelProperty(value = "Customer job")
	private String job;
	
//	@ApiModelProperty(value = "Customer address")
	private String address;
	
//	@ApiModelProperty(value = "Customer email")
	private String email;
	
//	@ApiModelProperty(value = "Customer creation date in the system")
	private LocalDate creationDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

    public String getCni() {
        return cni;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }

	@Override
	public int compareTo(CustomerDTO o) {
		return this.lastName.compareToIgnoreCase(o.getLastName());
	}
		
}

