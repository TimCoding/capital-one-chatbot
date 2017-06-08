import java.util.*;
import java.io.*;
import java.net.*;
import org.json.*;

public class operations {
	
	private ArrayList<String> sorted_priorities;
	private JSONObject cur_obj;
	private String customer_ID;
	private String account_ID;
	public String name;
	public String goal_type;
	public String goal_name;
	public Integer savings;
	public HashMap<Integer, String> priorities;

	//Testing constructor
	public operations() {

	}

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

	public void getAccountID() {

		try {

			//Temp, may change if added users
			customer_ID = "59387df1ceb8abe24251781e";

			String url = "http://api.reimaginebanking.com/customers/" + customer_ID + "/accounts?key=3f6ee99b28ba6db316ff1dfec4ec1ce2";
			URL cur_url = new URL(url);

			HttpURLConnection connect = (HttpURLConnection) cur_url.openConnection();
			connect.setRequestMethod("GET");
			connect.setRequestProperty("Content-Type", "application/json");
			InputStream in_stream = connect.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in_stream));
			StringBuilder build = new StringBuilder();
			String curLine;
			while((curLine = read.readLine()) != null) {
				build.append(curLine);
			}
			read.close();

			String final_values = build.toString();

			System.out.println(final_values);

			JSONObject customer_obj = new JSONObject(final_values);

			account_ID = customer_obj.getString("_id");
		}
		catch(Exception e) {

			System.out.println(e);
		}

	}

	//Helper for testing
	public String returnAccountID() {
		return account_ID;
	}
}