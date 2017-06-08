import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;
import java.util.*;

public class main_process extends HttpServlet {

	private HashMap<Integer, String> priorities;
	private HashMap<String, JSONObject> data;
	private String[] frivilous = {"fast food", "movies", "games", "luxury brands", "home improvement", "digital subscriptions",
		"physical subscriptions", "electronics", "gifts", "cable"};

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		StringBuffer read = new StringBuffer();

		String curLine = null;

		try {

			BufferedReader reader = request.getReader();

			while ((curLine = reader.readLine()) != null) {

				read.append(curLine);
			}

			JSONObject obj = HTTP.toJSONObject(read.toString());
			boolean complete = processData(obj);

			if(complete) {

				JSONArray finish_data = new JSONArray();
				Set<String> keys = data.keySet();

				for(String s : keys) {

					finish_data.put(data.get(s));
				}

				PrintWriter output = response.getWriter();
				output.println(finish_data);

			}

		} catch (Exception error) {

			System.out.println(error);
		}

	}

	public boolean processData(JSONObject obj) throws Exception {

		String name = obj.getString("name");
		String goal_type = obj.getString("goal type");
		String goal_name = "";

		if(!(goal_type.equals("save"))) {

			goal_name = obj.getString("goal name");
		}

		double savings_amount = obj.getDouble("savings amount");

		JSONArray arr = obj.getJSONArray("priorities");
		priorities = new HashMap<Integer, String>();

		for(int i = 0; i < arr.length(); i++) {

			String cur_priority = arr.getString(i);
			Integer cur_num = arr.getInt(i);

			priorities.put(cur_num, cur_priority);
		}

		operations curOP = new operations(name, goal_type, goal_name, savings_amount, priorities);
		boolean isSuccessfull = curOP.takeAway();

		if(isSuccessfull) {

			data = curOP.returnFrivilous();
			return true;
		}
		return false;

	}
}