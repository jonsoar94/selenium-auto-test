package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginFormPage extends BasePage {

	public LoginFormPage(WebDriver navegador) {
		super(navegador);
	}

	public LoginFormPage typeUsername(String username) {
		navegador.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys(username);
		return this;
	}
	
	public LoginFormPage typePassword(String password) {
		navegador.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(password);
		return this;
	}
	
	public SecretPage clickOnSignIn() {
		navegador.findElement(By.linkText("SIGN IN")).click();
		return new SecretPage(navegador);
	}
	
	public SecretPage doLogin(String username, String password) {
		typeUsername(username);
		typePassword(password);
		return clickOnSignIn();
	}
}
