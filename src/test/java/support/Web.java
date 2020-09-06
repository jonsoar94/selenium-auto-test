package support;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Web {
	
	public static WebDriver createWebDriver() {
		System.setProperty("webdriver.chrome.driver", "/Users/jonatanjosesoares/drivers/chromedriver");
		WebDriver navegador = new ChromeDriver();
		navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		navegador.get("http://www.juliodelima.com.br/taskit");
		
		return navegador;
	}

}
