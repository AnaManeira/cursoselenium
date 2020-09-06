package br.ce.Ana.test;
import static br.ce.Ana.core.DriverFactory.getDriver;
import static br.ce.Ana.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.Ana.core.DSL;
import br.ce.Ana.page.ObjectPages_CampoTreinamentoPage;

public class ObjectPages_Desafio_CadastroComErros {

	private DSL dsl;
	private ObjectPages_CampoTreinamentoPage page;

	@Before
	public void Inicializa() {
		System.setProperty("webdriver.gecko.driver", "C:\\Ana\\drivers\\geckodriver.exe");
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new ObjectPages_CampoTreinamentoPage();
	}

	@After
	public void Finaliza() {
		killDriver();
	}

	@Test
	public void DesafioSemNome() {
		page.cadastrar();
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoAceita());
	}

	@Test
	public void DesafioComNomeSemSobrenome() {
		page.setNome("Ana Paula");
		page.cadastrar();
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoAceita());
	}

	@Test
	public void DesafioSexoNaoSelecionado() {
		page.setNome("Ana Paula");
		page.setSobrenome("Sanches");
		page.cadastrar();
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoAceita());
	}

	@Test
	public void DesafioConflitoEntreComidas() {
		page.setNome("Ana Paula");
		page.setSobrenome("Sanches");
		page.setSexoFeminino();
		page.setComidaFavoritaCarne();
		page.setComidaVegetariano();
		page.cadastrar();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoAceita());
	}

	@Test
	public void DesafioConflitoEntreEsportes() {

		page.setNome("Ana Paula");
		page.setSobrenome("Sanches");
		page.setSexoFeminino();
		page.setComidaFavoritaCarne();
		page.setEsportes("Natacao", "O que eh esporte?");
		page.cadastrar();
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoAceita());
	}
}
