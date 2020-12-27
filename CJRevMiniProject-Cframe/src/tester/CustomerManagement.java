package tester;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import skeleton.Customer;

import static validationUtils.CustomerValidations.*;
public class CustomerManagement {
	 
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)){
			
			String menu = "\n1. Register Customer"
					+ "\n2. Display all customers (born at spc Date)"
					+ "\n3. Change Password"
					+ "\n4. Display all customers details"
					+ "\n5. Unsubscribe customer"
					+ "\n6. Sort based on email"
					+ "\n7. Change PhoneNo"
					+ "\n10. Exit";
			
			ArrayList<Customer> allCust = new ArrayList<>();
			boolean exit = false;
			while(!exit) {
				try {
					System.out.println(menu);
					System.out.println("Enter the choice: ");
					switch(sc.nextInt()) {
					case 1:
						System.out.println("Registration page ---------");
						System.out.println("Enter customer details : email, name, password, regAmount, "
								+ "dob(yyyy-MM-dd), custType, phone");

						 allCust.add(validateAllInputs(allCust, sc.next(), sc.next(), sc.next(), 
								sc.nextDouble(), sc.next(), sc.next(), sc.next()));

						System.out.println("Registered Successfully");
						break;
						
					case 2: 
						System.out.println("Display by born in specific date range ---------");
						System.out.println("Enter beginDate and endDate (yyyy-MM-dd) : ");
						 // parsing string to Date
						LocalDate beginDate = parseDate(sc.next());
						LocalDate endDate = parseDate(sc.next());
						for(Customer c : allCust) {
							if(c.getBirthDate().isAfter(beginDate) && c.getBirthDate().isBefore(endDate))
								System.out.println(c);
						}
						
						break;
					
					case 3:
						System.out.println("Change password page ---------------");
						System.out.println("Enter email, birthDate(dd-mm-yyyy), password, new password :");
						changePassword(allCust, sc.next(), sc.next(), sc.next(), sc.next());
						break;
						
					case 4:
						System.out.println("Display all customer details ---------");
						for(Customer c : allCust) {
							System.out.println(c);
						}
						break;
					
					case 5:
						System.out.println("Unsubscribe page --------");
						System.out.println("Enter email, dob(yyyy-MM-dd): ");
						performUnsub(allCust, sc.next(), sc.next());
						break;
						
					case 6:
						System.out.println("Sorting based on email");
						Collections.sort(allCust);
						for(Customer c : allCust) {
							System.out.println(c);
						}
						break; 
					
					case 7:
						System.out.println("Change phone no page ---------------");
						System.out.println("Enter email, birthDate(dd-mm-yyyy), password, new phoneNo :");
						changePhoneNo(allCust, sc.next(), sc.next(), sc.next(), sc.next());
						break;
						
					case 10:
						System.out.println("Good Bye...");
						exit = true;
						break;
						
					default:
						System.out.println("Invalid choice !");
					} // switch ends here
				} // inner try block ends here
				catch (Exception e) {
					e.printStackTrace();
				}
				sc.nextLine();
			}// loop ends here
		} // outer try ends here
	}
}
