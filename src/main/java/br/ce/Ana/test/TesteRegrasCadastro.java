package br.ce.Ana.test;

import static br.ce.Ana.core.DriverFactory.getDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.ce.Ana.core.BaseTest;
import br.ce.Ana.core.DSL;
import br.ce.Ana.page.ObjectPages_CampoTreinamentoPage;

@RunWith(Parameterized.class)
public class TesteRegrasCadastro extends BaseTest {

	private DSL dsl;
	private ObjectPages_CampoTreinamentoPage page;

	@Parameter
	public String nome;
	@Parameter(value = 1)
	public String sobrenome;
	@Parameter(value = 2)
	public String sexo;
	@Parameter(value = 3)
	public List<String> comidas;
	@Parameter(value = 4)
	public String[] esportes;
	@Parameter(value = 5)
	public String msg;

	@Before
	public void Inicializa() {
		System.setProperty("webdriver.gecko.driver", "C:\\Ana\\drivers\\geckodriver.exe");
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new ObjectPages_CampoTreinamentoPage();
	}

	@Parameters
	public static Collection<Object[]> getCollection() {
		return Arrays.asList(new Object[][] { { "", "", "", Arrays.asList(), new String[] {}, "Nome eh obrigatorio" },
				{ "Ana", "", "", Arrays.asList(), new String[] {}, "Sobrenome eh obrigatorio" },
				{ "Ana", "Sanches", "", Arrays.asList(), new String[] {}, "Sexo eh obrigatorio" },
				{ "Ana", "Sanches", "Feminino", Arrays.asList("Carne", "Vegetariano"), new String[] {},
						"Tem certeza que voce eh vegetariano?" },
				{ "Ana", "Sanches", "Feminino", Arrays.asList("Carne"), new String[] { "Karate", "O que eh esporte?" },
						"Voce faz esporte ou nao?" } });

	}

	@Test
	public void devevalidarRegras() {

		// data driven test
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		if (sexo.equals("Masculino")) {
			page.setSexoMasculino();
		}
		if (sexo.equals("Feminino")) {
			page.setSexoFeminino();
		}
		if (comidas.contains("Carne"))
			page.setComidaFavoritaCarne();
		if (comidas.contains("Vegetariano"))
			page.setComidaVegetariano();
		page.setEsportes(esportes);
		page.setEsportes("Natacao", "O que eh esporte?");
		page.cadastrar();
		System.out.println(msg);
		Assert.assertEquals(msg, dsl.alertaObterTextoAceita());
	}
}
