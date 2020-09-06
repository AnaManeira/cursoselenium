package br.ce.Ana.test;
import static br.ce.Ana.core.DriverFactory.getDriver;
import static br.ce.Ana.core.DriverFactory.killDriver;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import br.ce.Ana.core.DSL;

public class Otimizado_TesteCampoDeTreinamento {

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
		dsl.escrever("elementosForm:sugestoes", "teste");
		Assert.assertEquals("teste", dsl.ObterValorCampo("elementosForm:sugestoes"));
	}

	@Test
	public void DeveInteragircomRadioButton() {
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
	}

	@Test
	public void DeveInteragircomCheckbox() {
		dsl.clicarCheck("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:2"));
	}

	@Test
	public void DeveInteragircomCombo() {
		dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
		Assert.assertEquals("Superior", dsl.obterValorCombo("elementosForm:escolaridade"));
	}

	@Test
	public void VerificarValoresCombo() {
		Assert.assertEquals(8, dsl.obterQuantidadedeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
	}

	@Test
	public void VerificarValoresComboMultiplo() {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());

		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
	}

	@Test
	public void DeveInteragirComBotoes() {
		dsl.clicarBotao("buttonSimple");
		Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));
	}

	@Test
	public void DeveInteragirComLinks() {
		dsl.clicarLink("Voltar");
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}

	@Test
	public void DeveBuscarTextosNaPagina() {
//		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));

		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));

		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
	}

	@Test
	public void testJavascript() {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
// a linha abaixo é apenas para testar se funcionou abrir a web com o Java
		// js.executeScript("alert('Testando js via selenium')");
// na linha abaixo, conseguimos escrever o valor (nome), no campo.
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
// na linha abaixo, conseguimos trocar o tipo do elemento, ao invés de um campo pra escrever, foi colocado um campo de selecionar.
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");

// na linha abaixo, encontramos o elemento que queremos e pedimos para o java formar uma borda vermelha.
		WebElement element = getDriver().findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].styleborder = arguments[1]", element, "solid 4px red");
	}

	@Test
	public void deveClicarBotaoTabela() {
		dsl.clicarBotaoTabela("Nome", "Maria", "Botao", "elementosForm:tableUsuarios");
	}
}
