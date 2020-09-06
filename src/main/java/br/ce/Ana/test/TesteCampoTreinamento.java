package br.ce.Ana.test;
import static br.ce.Ana.core.DriverFactory.getDriver;
import static br.ce.Ana.core.DriverFactory.killDriver;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.ce.Ana.core.DSL;

public class TesteCampoTreinamento {

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
	public void testeTextField() {
		dsl.escrever("elementosForm:nome", "Teste de Escrita");
		Assert.assertEquals("Teste de Escrita", dsl.ObterValorCampo("elementosForm:nome"));
	}

	@Test
	public void DeveInteragircomTextArea() {
		getDriver().findElement(By.id("elementosForm:sugestoes")).sendKeys("teste");
		Assert.assertEquals("teste", getDriver().findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
	}

	@Test
	public void DeveInteragircomRadioButton() {
		getDriver().findElement(By.id("elementosForm:sexo:0")).click();
		boolean isSelected = getDriver().findElement(By.id("elementosForm:sexo:0")).isSelected();
		Assert.assertTrue(isSelected);
	}

	@Test
	public void DeveInteragircomCheckbox() {
		getDriver().findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(getDriver().findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
	}

	@Test
	public void DeveInteragircomCombo() {
		WebElement element = getDriver().findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
//		combo.selectByIndex(3);
//		combo.selectByValue("superior");
		combo.selectByVisibleText("Superior");

		Assert.assertEquals("Superior", combo.getFirstSelectedOption().getText());
	}

	@Test
	public void VerificarValoresCombo() {
		WebElement element = getDriver().findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());
		for (WebElement option : options) {
			if (option.getText().equals("Mestrado")) {
				Assert.assertTrue(true);
				break;
			}
		}
	}

	@Test
	public void VerificarValoresComboMultiplo() {
		WebElement element = getDriver().findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");

		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());
	}

	@Test
	public void DeveInteragirComBotoes() {
		// driver.findElement(By.id("buttonSimple")).click();
		WebElement botao = getDriver().findElement(By.id("buttonSimple"));
		botao.click();

		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
	}

	@Test
//	@Ignore
	public void DeveInteragirComLinks() {
		getDriver().findElement(By.linkText("Voltar")).click();
		Assert.assertEquals("Voltou!", getDriver().findElement(By.id("resultado")).getText());
	}

	@Test
	public void DeveBuscarTextosNaPagina() {
//		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));

		Assert.assertEquals("Campo de Treinamento", getDriver().findElement(By.tagName("h3")).getText());

		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",
				getDriver().findElement(By.className("facilAchar")).getText());
	}
}
