package resources;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import pageobjects.HomePage;

public class PropertiesFile {

	public static void getProperties() {
		try {
			Properties prop = new Properties();

			// get current project path
			String projectPath = System.getProperty("user.dir");

			// create a inputStream to read from a file
			InputStream input = new FileInputStream(projectPath + "\\src\\main\\java\\resources\\config.properties");

			// load the config file to get key value pairs
			prop.load(input);

			// read the config file and extract the browser name or to get the browser name
			// from command line, use "System.getProperty()" to read the value coming from
			// maven
			String browser = System.getProperty("browser") != null ? System.getProperty("browser")
					: prop.getProperty("browser");

			// In the Base class set the static variale "browserName" value to "browser"
			Base.browserName = browser;

			// set email and password for flipcart login
			HomePage.flipEmail = prop.getProperty("email");
			HomePage.flipPassword = prop.getProperty("password");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
	}
}
