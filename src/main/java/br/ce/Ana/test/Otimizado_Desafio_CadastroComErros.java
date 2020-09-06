package br.ce.Ana.test;
import static br.ce.Ana.core.DriverFactory.getDriver;
import static br.ce.Ana.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.Ana.core.DSL;

public class Otimizado_Desafio_CadastroComErros {

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
	public void DesafioSemNome() {
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoAceita());
	}

	@Test
	public void DesafioComNomeSemSobrenome() {
		dsl.escrever("elementosForm:nome", "Ana Paula");
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoAceita());
	}

	@Test
	public void DesafioSexoNaoSelecionado() {
		dsl.escrever("elementosForm:nome", "Ana Paula");
		dsl.escrever("elementosForm:sobrenome", "Sanches");
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoAceita());
	}

	@Test
	public void DesafioConflitoEntreComidas() {
		dsl.escrever("elementosForm:nome", "Ana Paula");
		dsl.escrever("elementosForm:sobrenome", "Sanches");
		dsl.clicarRadio("elementosForm:sexo:1");
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:3");
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoAceita());
	}

	@Test
	public void DesafioConflitoEntreEsportes() {

		dsl.escrever("elementosForm:nome", "Ana Paula");
		dsl.escrever("elementosForm:sobrenome", "Sanches");
		dsl.clicarRadio("elementosForm:sexo:1");
		dsl.clicarRadio("elementosForm:comidaFavorita:3");
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoAceita());
	}
}
