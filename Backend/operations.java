import java.util.*;
import java.io.*;
import java.net.*;
import org.json.*;

public class operations {

	private ArrayList<String> sorted_priorities;
	private JSONObject cur_obj;
	private String account_ID;
	public String name;
	public String goal_type;
	public String goal_name;
	public double savings;
	public double totalSavings;
	public HashMap<Integer, String> priorities;
	public HashMap<String, JSONObject> purchases_taken_away;

	//Temp, may change if added users
	public String customer_ID = "59387df1ceb8abe24251781e";

	//Testing constructor
	public operations(String n, Integer s, HashMap<Integer, String> p) {

		name = n;
		savings = s;
		priorities = p;
	}

	public operations(String n, String gt, String gn, double s, HashMap<Integer, String> p) {

		name = n;
		goal_type = gt;
		goal_name = gn;
		savings = s;
		priorities = p;
	}

	public void sort() {

		for (int i = 1; i <= priorities.size(); i++) {

			sorted_priorities.add(priorities.get(i));
		}
	}

	public void getAccountID() throws JSONException {

		String url = "http://api.reimaginebanking.com/customers/" + customer_ID + "/accounts?key=3f6ee99b28ba6db316ff1dfec4ec1ce2";
		JSONArray customer_obj = makeRequest(url);
		account_ID = customer_obj.getJSONObject(0).getString("_id");
	}

	public boolean takeAway() throws Exception {

		//Get all purchases
		getAccountID();
		String url = "http://api.reimaginebanking.com/accounts/" + account_ID + "/purchases?key=3f6ee99b28ba6db316ff1dfec4ec1ce2";
		JSONArray customer_obj = makeRequest(url);

		//Find frivilous purchases that can be removed
		purchases_taken_away = new HashMap<String, JSONObject>();

		for(int i = 0; i <= priorities.size(); i++) {

			String current_priority = priorities.get(i);

			for(int j = 0; j < customer_obj.length(); j++) {

				JSONObject cur = customer_obj.getJSONObject(j);
				if(cur.getString("description").equals(current_priority)) {

					purchases_taken_away.put(current_priority, cur);
					double cur_amount = cur.getDouble("amount");
					savings -= cur_amount;
					totalSavings += cur_amount;

					if(savings <= 0.0) {

						return true;
					}
				}
			}
		}

		if(savings <= 0.0) {

			return true;
		}

		return false;
	}

	//Helper to make connections to Capital One API
	public JSONArray makeRequest(String url) {

		try {

			URL cur_url = new URL(url);
			HttpURLConnection connect = (HttpURLConnection) cur_url.openConnection();
			connect.setRequestMethod("GET");
			connect.setRequestProperty("Content-Type", "application/json");
			InputStream in_stream = connect.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in_stream));
			StringBuilder build = new StringBuilder();
			String curLine;
			while ((curLine = read.readLine()) != null) {
				build.append(curLine);
			}
			read.close();

			String final_values = build.toString();

			JSONArray customer_obj = new JSONArray(final_values);

			return customer_obj;
		} catch (Exception e) {

			System.out.println(e);
			return null;
		}
	}

	//Helpers for testing
	public String returnAccountID() {
		return account_ID;
	}

	public HashMap<String, JSONObject> returnFrivilous() {
		return purchases_taken_away;
	}
}