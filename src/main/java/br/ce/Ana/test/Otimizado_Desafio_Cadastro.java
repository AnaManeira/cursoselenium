package br.ce.Ana.test;
import static br.ce.Ana.core.DriverFactory.getDriver;
import static br.ce.Ana.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.Ana.core.DSL;

public class Otimizado_Desafio_Cadastro {

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
		dsl.escrever("elementosForm:nome", "Ana Paula");
		dsl.escrever("elementosForm:sobrenome", "Sanches");
		dsl.clicarRadio("elementosForm:sexo:1");
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
		dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.clicarBotao("elementosForm:cadastrar");

		Assert.assertTrue(dsl.obterTexto("Resultado").startsWith("Cadastrado!"));
		Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Ana Paula"));
		Assert.assertEquals("Sobrenome: Sanches", dsl.obterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Feminino", dsl.obterTexto("descSexo"));
		Assert.assertEquals("Comida: Carne", dsl.obterTexto("descComida"));
		Assert.assertEquals("Escolaridade: superior", dsl.obterTexto("descEscolaridade"));
		Assert.assertEquals("Esportes: Natacao Corrida Karate", dsl.obterTexto("descEsportes"));

		// driver.findElement(By.id("confirm")).click();
		// Assert.assertEquals("Confirm Simples", );
	}
}
