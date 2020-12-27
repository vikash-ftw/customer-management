package custom_comparator;

import java.util.Comparator;

import skeleton.Customer;

public class MyComparison implements Comparator<Customer> {
	
	@Override
	public int compare(Customer c1, Customer c2) {
		if(c1 == null)
			return 1;  // shuffle 
		else if (c2 == null)
			return -1; // no shuffle
		return c1.compareTo(c2);
		// all null values will be at last
	}
}
