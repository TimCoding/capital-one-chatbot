import java.io.*;
import java.util.*;
import java.net.*;
import org.json.*;


public class tester {
	
	public static void main(String[] args) throws Exception {
		
		String name = "John Doe";
		Integer savings = 400;
		HashMap<Integer, String> curMap = new HashMap<Integer, String>();

		curMap.put(1, "fast food");
		curMap.put(2, "games");
		curMap.put(3, "home improvement");
		curMap.put(4, "electroncis");


		operations curOP = new operations(name, savings, curMap);

		boolean b = curOP.takeAway();

		if(b) {

			System.out.println();
			System.out.println("Congragulations you can save enough money by cutting down on following purchases");
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.println();

			HashMap<String, JSONObject> vals = curOP.returnFrivilous();
			Set<String> keys = vals.keySet();

			for(String s: keys) {

				JSONObject curObj = vals.get(s);

				String curName = curObj.getString("description");
				double curPrice = curObj.getDouble("amount");
				String curDate = curObj.getString("purchase_date");

				System.out.println(curName + " made on " + curDate + " for price of: $" + curPrice);
			}

			System.out.println();
			System.out.println("You can save a total of: $" + curOP.totalSavings);
			System.out.println();
		}

	}
}