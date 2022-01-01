package mondia.konakart;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Common {

	public WebDriver driver;
	public Properties prop;

	public WebDriver initializeDriver() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		// Read browser name from properties file
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		//driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}
	
	public ExtentReports getExtendReport() {
		String path = System.getProperty("user.dir") + "\\Report\\index.html";
		ExtentHtmlReporter sparkReporter = new ExtentHtmlReporter(path);
		ExtentReports report = new ExtentReports();
		report.attachReporter(sparkReporter);
		report.setSystemInfo("Tester", "Israa Mohamed");
		return report;
	}

	

}
