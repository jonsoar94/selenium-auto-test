package support;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

public class Screenshot {

	public static void tirar(WebDriver navegador, String arquivo) {
		File screenshot = ((TakesScreenshot) navegador).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File(arquivo));
		} catch (IOException e) {
			System.out.println("Houve problemas ao copiar o arquivo para a pasta: " + e);
		}
	}
}
