package br.ce.Ana.test;
import static br.ce.Ana.core.DriverFactory.getDriver;
import static br.ce.Ana.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.Ana.core.DSL;

public class TesteAjax {

	private DSL dsl;

	@Before
	public void Inicializa() {
		System.setProperty("webdriver.gecko.driver", "C:\\Ana\\drivers\\geckodriver.exe");
		dsl = new DSL();
	}

	@After
	public void Finaliza() {
		killDriver();
	}

	@Test
	public void testeAjax() {
		getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		dsl.escrever("j_idt725:name", "Teste");
		dsl.clicarBotao("j_idt725:j_idt728");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.textToBe(By.id("j_idt725:display"), "Teste"));
		Assert.assertEquals("Teste", dsl.obterTexto("j_idt725:display"));
	}

}
