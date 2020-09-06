package br.ce.Ana.test;
import static br.ce.Ana.core.DriverFactory.getDriver;
import static br.ce.Ana.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.Ana.core.DSL;

public class Otimizado_TesteAlert {

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
	public void DeveInteragirComAlertSimples() {
		dsl.clicarBotao("alert");
		String texto = dsl.alertaObterTextoAceita();
		Assert.assertEquals("Alert Simples", texto);
		dsl.escrever("elementosForm:nome", texto);
	}

	@Test
	public void DeveInteragirComAlertConfirm() {
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoAceita());
		Assert.assertEquals("Confirmado", dsl.alertaObterTextoAceita());
		dsl.clicarBotao("confirm");

		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoNega());
		Assert.assertEquals("Negado", dsl.alertaObterTextoNega());
	}

	@Test
	public void DeveInteragirComAPrompt() {
		dsl.clicarBotao("prompt");
		Assert.assertEquals("Digite um numero", dsl.alertaObterTexto());
		dsl.alertaEscrever("12");
		Assert.assertEquals("Era 12?", dsl.alertaObterTextoAceita());
		Assert.assertEquals(":D", dsl.alertaObterTextoAceita());
	}

}
