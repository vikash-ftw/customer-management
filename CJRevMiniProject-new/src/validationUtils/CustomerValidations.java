package validationUtils;

import java.time.LocalDate;
import java.time.Period;

import customExcep.CustomerHandlingException;
import skeleton.Customer;
import skeleton.CustomerType;

import static java.time.LocalDate.now;
import static java.time.LocalDate.parse;
public class CustomerValidations {
	
	private static final int EMAIL_MIN_LENGTH, EMAIL_MAX_LENGTH;
	private static final double AMOUNT_MULTIPLE;
	private static LocalDate currentDate;
	
	static {
		currentDate = now();
		EMAIL_MIN_LENGTH = 8;
		EMAIL_MAX_LENGTH = 15;
		AMOUNT_MULTIPLE = 500;
	}
	
	//add a static method to validate all i/ps In case of any err : throw an exc
	//In case of success , return validated customer details , encapsulated in Customer obj form
	public static Customer validateAllInputs(Customer[] allcust, String email, String name, String password,
			double regAmount, String dob, String custType, String phone) throws CustomerHandlingException 
	{  
		validateEmail(email);
		LocalDate birthDate = validateAge(dob);
		checkForDup(allcust, email, birthDate);
		validatePassword(password);
		validateAmount(regAmount);
		CustomerType type = validateCustType(custType);
		validatePhoneNo(phone);
		// all validation successfull, wrap all validated details into Customer obj n return it to the caller
		return new Customer(email, name, password, regAmount, birthDate, type, phone);
	}
	
	//email validation
	public static void validateEmail(String email) throws CustomerHandlingException {
		if(email.length() < EMAIL_MIN_LENGTH || email.length() > EMAIL_MAX_LENGTH
				|| !email.contains("@") || !email.endsWith(".com"))
			throw new CustomerHandlingException("Invalid  email !!");
	}
	
	// password validation
	public static void validatePassword(String pwd) throws CustomerHandlingException{
		String regExp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{6,20})";
		if(!pwd.matches(regExp))
			throw new CustomerHandlingException("Invalid password !!");
	}
	
	//Age validation --- customer's age must be > 21
	public static LocalDate validateAge(String birthDate)throws CustomerHandlingException { 
		LocalDate dob = parse(birthDate);
		if(Period.between(dob, currentDate).getYears() <= 21)
			throw new CustomerHandlingException("Invalid Age !!");
		return dob;
	}
	
	//validate regAmount
	public static void validateAmount(double amount) throws CustomerHandlingException{
		if(amount % AMOUNT_MULTIPLE != 0)
			throw new CustomerHandlingException("Invalid registration amount !!");
	}
	
	//validate customer Type
	public static CustomerType validateCustType(String type) { 
		return CustomerType.valueOf(type.toUpperCase());
	}
	
	//validate phone no
	public static void validatePhoneNo(String phone) throws CustomerHandlingException {
		if(phone.length() < 10 || phone.length() > 10)
			throw new CustomerHandlingException("Invalid phone no.");
	}
	
	//check customer duplication
	public static void checkForDup(Customer[] allcust, String email, LocalDate dob) throws CustomerHandlingException {
		Customer newCust = new Customer(email, dob);
		for(Customer c : allcust) {
			if(newCust.equals(c))
				throw new CustomerHandlingException("Duplicate Customer");
		}	
	}
	
//	public static Customer validateLogin(Customer[] allcust, String email, String password) {
//		for(Customer c : allcust) {
//			if(c != null && c.getEmail().equals(email) && c.getPassword().equals(password))
//				return c;
//		}
//		return null;
//	}
	
	public static void changePhoneNo(Customer[] allcust, String email, 
			String birthDate, String password, String newPhone) throws CustomerHandlingException 
	{
		boolean found = false;
		for(Customer c : allcust) {
			if(c != null && c.getEmail().equals(email) && c.getPassword().equals(password)
					&& c.getBirthDate().isEqual(parse(birthDate))) {
				validatePhoneNo(newPhone);
				c.setPhoneNo(newPhone);
				found = true;
				System.out.println("Successfully update phone no for "+ email);
			}
		}
		if(!found) {
			throw new CustomerHandlingException("Customer does not exist !!");
		}
	}
	
	public static void changePassword(Customer[] allcust, String email, 
			String birthDate, String password, String newPass) throws CustomerHandlingException 
	{
		boolean found = false;
		for(Customer c : allcust) {
			if(c != null && c.getEmail().equals(email) && c.getPassword().equals(password)
					&& c.getBirthDate().isEqual(parse(birthDate))) {
				validatePassword(newPass);
				c.setPassword(newPass);
				found = true;
				System.out.println("Successfully update password for "+ email);
			}
		}
		if(!found) {
			throw new CustomerHandlingException("Customer does not exist !!");
		}
	}
	
	public static void performUnsub(Customer[] allcust, String email, String dob) throws CustomerHandlingException {
		boolean found = false;
		for(int i = 0; i < allcust.length; i++) {
			if(allcust[i] != null && allcust[i].getEmail().equals(email) && allcust[i].getBirthDate().isEqual(parse(dob))) {
				allcust[i] = null;
				found = true;
				System.out.println("Successfully unsubscribed "+ email);
			}
		}
		if(!found) {
			throw new CustomerHandlingException("Customer does not exist !!");
		}
	}
	
	// parsing date
	public static LocalDate  parseDate(String date) {
		return parse(date);
	}
	
}
