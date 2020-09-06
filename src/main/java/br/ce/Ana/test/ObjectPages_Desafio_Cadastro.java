package br.ce.Ana.test;

import static br.ce.Ana.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.Ana.core.BaseTest;
import br.ce.Ana.page.ObjectPages_CampoTreinamentoPage;

public class ObjectPages_Desafio_Cadastro extends BaseTest {

	private ObjectPages_CampoTreinamentoPage page;

	@Before
	public void Inicializa() {
		System.setProperty("webdriver.gecko.driver", "C:\\Ana\\drivers\\geckodriver.exe");
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new ObjectPages_CampoTreinamentoPage();
	}

	@Test
	public void testeTextField() {
		page.setNome("Ana Paula");
		page.setSobrenome("Sanches");
		page.setSexoFeminino();
		page.setComidaFavoritaCarne();
		page.setEscolaridade("Superior");
		page.setEsportes("Natacao");
		page.cadastrar();

		Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
		Assert.assertEquals("Ana Paula", page.obterNomeCadastro());
		Assert.assertEquals("Sanches", page.obterSobrenomeCadastro());
		Assert.assertEquals("Feminino", page.obterSexoCadastro());
		Assert.assertEquals("Carne", page.obterComidaCadastro());
		Assert.assertEquals("superior", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Natacao", page.obterEsportesCadastro());
		// driver.findElement(By.id("confirm")).click();
		// Assert.assertEquals("Confirm Simples", );
	}
}
