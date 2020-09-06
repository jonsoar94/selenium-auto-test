package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecretPage {
	
	private WebDriver navegador;
	
	public SecretPage(WebDriver navegador) {
		this.navegador = navegador;
	}
	
	public MePage clicarMe() {
		navegador.findElement(By.className("me")).click();
		return new MePage(navegador);
	}

}
