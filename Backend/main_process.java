import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;
import java.util.*;

public class main_process extends HttpServlet {

	private HashMap<Integer, String> priorities;
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
		} catch (Exception error) {

			System.out.println("Buffer Reader fail");
		}

		try {

			JSONObject obj = HTTP.toJSONObject(read.toString());
		} catch (JSONException error) {

			System.out.println("Error parsing JSON");
		}

	}

	public void processData(JSONObject obj) throws JSONException {

		String name = obj.getString("name");
		String goal_type = obj.getString("goal type");
		String goal_name = "";

		if(!(goal_type.equals("save"))) {

			goal_name = obj.getString("goal name");
		}

		Integer savings_amount = obj.getInt("savings amount");

		JSONArray arr = obj.getJSONArray("priorities");
		priorities = new HashMap<Integer, String>();

		for(int i = 0; i < arr.length(); i++) {

			String cur_priority = arr.getString(i);
			Integer cur_num = arr.getInt(i);

			priorities.put(cur_num, cur_priority);
		}

	}
}