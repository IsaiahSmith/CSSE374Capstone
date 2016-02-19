package jsonloader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class LoadJson {
	public static void main(String[] args)
			throws FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException {
		JSONParser parser = new JSONParser();
		JSONArray a = (JSONArray) parser.parse(new FileReader("input\\test.json"));
		//Read in the file test.json in the input folder.
		for (Object o : a) {
			JSONObject g = (JSONObject) o;

			String IF = (String) g.get("Input-Folder");
			System.out.println("Input-Folder:");
			System.out.println(IF);
			//for single output

			System.out.println("Input-Classes:");
			JSONArray IC = (JSONArray) g.get("Input-Classes");
			for (Object c : IC) {
				System.out.println(c);
			}
			//for a list of output

			System.out.println("Output-Directory:");
			String OD = (String) g.get("Output-Directory");
			System.out.println(OD);

			System.out.println("Dot-Path:");
			String DP = (String) g.get("Dot-Path");
			System.out.println(DP);

			System.out.println("Phases:");
			JSONArray list = (JSONArray) g.get("Phases");
			for (Object c : list) {
				System.out.println(c);
			}

			System.out.println("Adapter-MethodDelegation:");
			String AM = (String) g.get("Adapter-MethodDelegation");
			System.out.println(AM);

			System.out.println("Decorator-MethodDelegation:");
			String DM = (String) g.get("Decorator-MethodDelegation");
			System.out.println(DM);

			System.out.println("Singleton-RequireGetInstance:");
			String SR = (String) g.get("Singleton-RequireGetInstance");
			System.out.println(SR);

		}
	}
}
