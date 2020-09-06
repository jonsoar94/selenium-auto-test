import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import support.Generator;
import support.Screenshot;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioTeste.csv")
public class InserirNovasInformacoesTest {

	private WebDriver navegador = null;
	
	@Rule
	public TestName test = new TestName();

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "/Users/jonatanjosesoares/drivers/chromedriver");
		navegador = new ChromeDriver();
		navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		navegador.get("http://www.juliodelima.com.br/taskit");
		System.out.println("Chamando o setUp() ");
		// Clicar no link que possui o texto "Sign in"
		navegador.findElement(By.linkText("Sign in")).click();

		// Indentificando o formulário de login:
		WebElement formSigninBox = navegador.findElement(By.id("signinbox"));

		// Digitar no campo com name "login" que está dentro do formulário de id
		// "siginbox" o texto "julio0001"
		formSigninBox.findElement(By.name("login")).sendKeys("julio0001");

		// Digitar no campo com name "password" que está dentro do formulário de id
		// "siginbox" o texto "123456"
		formSigninBox.findElement(By.name("password")).sendKeys("123456");

		// Clicar no link com o texto "SIGN IN"
		navegador.findElement(By.linkText("SIGN IN")).click();
		
		// Clicar no link que possui a class "me";
		navegador.findElement(By.className("me")).click();

		// Clicar em um link que possui o text "MORE DATA ABOUT YOU";
		navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
	}

	@Test
	public void inserirNovasInformacoesTest(@Param(name = "tipo") String tipo, @Param(name = "contato") String contato, @Param(name = "mensagem") String mensagem) {
		// Clicar no botão através do seu xPath = //button[@data-target="addmoredata"]
		navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();
		;

		// identificar o pop-up onde está o formulário de id "addmoredata"
		WebElement formAddMoreData = navegador.findElement(By.id("addmoredata"));

		// na combo de name "type", escolher a opção "Phone";
		WebElement typeField = formAddMoreData.findElement(By.name("type"));
		new Select(typeField).selectByVisibleText((tipo));

		// No campo de name "contact" digitar "+49 99999999";
		formAddMoreData.findElement(By.name("contact")).sendKeys(contato);

		// Clicar no link de text "SAVE" que está no popup
		formAddMoreData.findElement(By.linkText("SAVE")).click();

		// Na mensagem de id "toast-container" validar que o texto e´ "Your contact has
		// been added!";
		WebElement msgContainer = navegador.findElement(By.id("toast-container"));
		String msg = msgContainer.getText();
		assertEquals(mensagem, msg);

	}

//	@Test
	public void removerUmContatoDeUmUsuario() {
		// Clicar no elemento pelo xpath //span[text()="+551133334444"]/following-sibling::a
		navegador.findElement(By.xpath("//span[text()=\"+551133334444\"]/following-sibling::a")).click();
		// Confirmar a janela javascript
		navegador.switchTo().alert().accept();
		
		Screenshot.tirar(navegador, "/Users/jonatanjosesoares/test-report/" + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png");
		
		// validar que a mensagem apresentada foi Rest in peace, dear phone!;
		WebElement msgContainer = navegador.findElement(By.id("toast-container"));
		String msg = msgContainer.getText();
		assertEquals("Rest in peace, dear phone!", msg);
		
		// Aguardar até 1 segundos para que a janela desapareca;
		WebDriverWait aguardar = new WebDriverWait(navegador, 10);
		aguardar.until(ExpectedConditions.stalenessOf(msgContainer));
		
		// Clicar no link com o texto logout;
		navegador.findElement(By.linkText("Logout")).click();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Chamando o tearDown()");
		navegador.quit();
	}
}
