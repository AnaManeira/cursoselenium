package br.ce.Ana.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.Ana.test.ObjectPages_Desafio_Cadastro;
import br.ce.Ana.test.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({ ObjectPages_Desafio_Cadastro.class, TesteRegrasCadastro.class })

public class SuiteTeste {
}
