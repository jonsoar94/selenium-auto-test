import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.*;

import pages.LoginPage;
import support.Web;

public class InformacoesUsuarioPageObjectTest {
	
	private WebDriver navegador = null;
	
	@Before
	public void setUp() {
		navegador = Web.createWebDriver();
	}
	
	@Test
	public void testAdicionarUmaInformacaoAdicionalDoUsuario() {
		String textoToast = new LoginPage(navegador)
			.clickSignIn()
			.doLogin("julio0001", "123456")
			.clicarMe()
			.clickTabMoreDataAboutYou()
			.clickButtonAddMoreDataAboutYou()
			.addContact("Phone", "+551133334444")
			.capturarTextoToast();
		
		assertEquals("Your contact has been added!", textoToast);
	}
	
	@Test
	public void testRemoverUmaInformacaoAdicionarDoUsuario() {
		new LoginPage(navegador)
			.clickSignIn()
			.doLogin("julio0001", "1234556")
			.clicarMe();
	}

	@After
	public void tearDown() {
//		navegador.quit();
	}
}
