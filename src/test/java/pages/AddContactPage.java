package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddContactPage extends BasePage {

	public AddContactPage(WebDriver navegador) {
		super(navegador);
	}
	
	public AddContactPage typeContact(String tipo) {
		WebElement tipoContato = navegador.findElement(By.id("addmoredata")).findElement(By.name("type"));
		new Select(tipoContato).selectByVisibleText((tipo));
		return this;
	}
	
	public AddContactPage typeNumber(String numero) {
		navegador.findElement(By.id("addmoredata")).findElement(By.name("contact")).sendKeys(numero);
		return this;
	}
	
	public MePage clickSave() {
		navegador.findElement(By.id("addmoredata")).findElement(By.linkText("SAVE")).click();
		return new MePage(navegador);
	}
	
	public MePage addContact(String tipo, String numero) {
		typeContact(tipo);
		typeNumber(numero);
		return clickSave();
	}

}
