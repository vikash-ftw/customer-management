package skeleton;

import java.time.LocalDate;

public class Customer implements Comparable<Customer> {
	private String email;
	private String name;
	private String password;
	private double regAmount;
	private LocalDate birthDate;
	// HAS A - Customer HAS A customerType
	private CustomerType custType;
	private String phoneNo;
	
	// add a constr to init state of the customer
	public Customer(String email,String name,String pwd,double amount, LocalDate dob, CustomerType custType, String phone) {
		super();
		// LocalDate birth = LocalDate.parse(dob);
		this.email = email;
		this.name = name;
		this.password = pwd;
		this.regAmount = amount;
		this.birthDate = dob;  // this.birthdate = birth
		this.custType = custType;
		this.phoneNo = phone;
	}
	
	//adding a customer to accept PK(unique ID) details from user.
	public Customer(String email, LocalDate birthDate) {
		super();
		this.email = email;
		this.birthDate = birthDate;
	}
	
	// getters and setters
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	
	
	@Override
	public String toString() {
		return "Customer [email=" + email + ", name=" + name + ", regAmount=" + regAmount + ", birthDate=" + birthDate
				+ ", custType=" + custType + ", phoneNo=" + phoneNo + "]";
	}

	@Override 
	public boolean equals(Object o) {
		if(o instanceof Customer) { // checking before downcasting
			Customer c = (Customer) o; // o is downcasted
			//using LocalDate method isEqual to compare birthDate;
			return email.equals(c.email) && birthDate.isEqual(c.birthDate);
		}
		return false;
	}
	
	@Override
	public int compareTo(Customer c) {
		return email.compareToIgnoreCase(c.email) < 0 ? -1 : email.compareToIgnoreCase(c.email) > 0 ? 1 : 0; 
	}

	
}
