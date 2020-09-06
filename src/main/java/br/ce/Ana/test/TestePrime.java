package br.ce.Ana.test;
import static br.ce.Ana.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.ce.Ana.core.DSL;

public class TestePrime {

	private DSL dsl;

	@Before
	public void Inicializa() {
		System.setProperty("webdriver.gecko.driver", "C:\\Ana\\drivers\\geckodriver.exe");
		dsl = new DSL();
	}

	@After
	public void Finaliza() {
		// killDriver();
	}

	@Test
	public void deveInteragirComRadioPrime() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		dsl.clicarRadio(By.xpath("//*[@id='j_idt726:console']/tbody/tr/td[1]/div/div[2]/span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt726:console:0"));

		dsl.clicarRadio(By.xpath("//*[@id=\'j_idt726:console\']/tbody/tr/td[2]/div/div[2]/span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt726:console:1"));
	}

	// Desafio Combo Prime // MOOOOSTRAR PRO YAGO

	@Test
	public void deveInteragirComSelectPrime() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
		dsl.clicarList(By.xpath("//*[@id=\'j_idt726:console\']/div[3]/span"));
		dsl.clicarList(By.xpath("//*[@id='j_idt726:console_items']//li[.='PS4']"));
		Assert.assertEquals("PS4", dsl.obterTexto("j_idt726:console_label"));
	}

	// DESAFIO DE UM JEITO MAIS SIMPLES

	@Test
	public void deveInteragircomSelectPrimeSoqQueMelhor() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
		dsl.selecionarComboPrime("j_idt726:console", "Xbox One");
		Assert.assertEquals("Xbox One", dsl.obterTexto("j_idt726:console_label"));
	}
}