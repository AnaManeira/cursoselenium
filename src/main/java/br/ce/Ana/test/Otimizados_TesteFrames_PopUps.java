package br.ce.Ana.test;
import static br.ce.Ana.core.DriverFactory.getDriver;
import static br.ce.Ana.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.ce.Ana.core.DSL;

public class Otimizados_TesteFrames_PopUps {

	private DSL dsl;

	@Before
	public void Inicializa() {
		System.setProperty("webdriver.gecko.driver", "C:\\Ana\\drivers\\geckodriver.exe");
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}

	@After
	public void Finaliza() {
		killDriver();
	}

	@Test
	public void DeveInteragirComFrames() {
		dsl.entrarFrame("frame1");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoAceita();
		Assert.assertEquals("Frame OK!", msg);
		dsl.sairFrame("frame1");
		dsl.escrever("elementosForm:nome", msg);
	}

	@Test
	public void deveInteragirComFrameEscondido() {
		// UTILZIANDO O JS - dando scroll na pagina
		WebElement frame = getDriver().findElement(By.id("frame2"));
		dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		dsl.entrarFrame("frame2");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoAceita();
		Assert.assertEquals("Frame OK!", msg);
	}

	@Test
	public void DeveInteragirComJanelas() {
		dsl.clicarBotao("buttonPopUpEasy");
		dsl.trocarJanela("Popup");
		dsl.escrever(By.tagName("textarea"), "Deu Certo?");
		getDriver().close();
		dsl.trocarJanela("");
		dsl.escrever(By.tagName("textarea"), "e agora?");
	}

	@Test
	public void DeveInteragirComJanelasSemTítulo() {
		dsl.clicarBotao("buttonPopUpHard");
		System.out.println(getDriver().getWindowHandle());
		System.out.println(getDriver().getWindowHandles());
		dsl.trocarJanela((String) getDriver().getWindowHandles().toArray()[1]);
		dsl.escrever(By.tagName("textarea"), "Deu Certo?");
		dsl.trocarJanela((String) getDriver().getWindowHandles().toArray()[0]);
		dsl.escrever(By.tagName("textarea"), "e agora?");
	}
}
