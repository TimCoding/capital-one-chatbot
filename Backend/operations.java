import java.util.*;
import java.io.*;
import java.net.*;
import org.json.*;

public class operations {
	
	private ArrayList<String> sorted_priorities;
	private JSONObject cur_obj;
	private String customerID;
	public String name;
	public String goal_type;
	public String goal_name;
	public Integer savings;
	public HashMap<Integer, String> priorities;


	public operations(String n, String gt, String gn, Integer s, HashMap<Integer, String> p) {

		name = n;
		goal_type = gt;
		goal_name = gn;
		savings = s;
		priorities = p;
	}

	public void sort() {

		for(int i = 0; i < priorities.size(); i++) {

			sorted_priorities.add(priorities.get(i));
		}
	}

	public void getCustomerID() {

		String url = "http://api.reimaginebanking.com/customers?key=3f6ee99b28ba6db316ff1dfec4ec1ce2";
		URL cur_url = new URL(url);

		
	}
}