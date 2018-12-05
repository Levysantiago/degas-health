package cadastro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class CadastroEnfermeiroTest {
	@Test
	public void testaExisteEnfermeiro() {
		boolean res;
		CadastroEnfermeiro cadastro = new CadastroEnfermeiro();
		
		cadastro.novo("Joao Carlos","M","111111","Mexico","26/08/1977","29/06/2012","03/02/2007");
		
		res = cadastro.existe("123603");
		assertEquals(false, res);
		
		res = cadastro.existe("");
		assertEquals(false, res);
		
		res = cadastro.existe("asd");
		assertEquals(false, res);
		
		res = cadastro.existe(" ");
		assertEquals(false, res);
		
		res = cadastro.existe("1");
		assertEquals(false, res);
		
		res = cadastro.existe("111111");
		assertEquals(true, res);
		
		res = cadastro.existe("11111");
		assertEquals(false, res);
	}
	
	@Test
	public void testaNovoEnfermeiro() {
		String res;
		CadastroEnfermeiro cadastroEnfermeiro = new CadastroEnfermeiro();
		
		/*Teste de campos vazios*/
		res = cadastroEnfermeiro.novo("","M","97119","Brasil","26/08/1977","29/06/2012","28/01/2007");
		assertEquals("Campo Nome inválido", res);
		
		res = cadastroEnfermeiro.novo("Nardelle Moraes","","97119","Brasil","26/08/1977","29/06/2012","28/01/2007");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroEnfermeiro.novo("Nardelle Moraes","F","","Brasil","26/08/1977","29/06/2012","28/01/2007");
		assertEquals("Identificador inválido", res);
		
		res = cadastroEnfermeiro.novo("Nardelle Moraes","F","53432","","26/08/1977","29/06/2012","28/01/2007");
		assertEquals("Campo Nacionalidade inválido", res);
		
		res = cadastroEnfermeiro.novo("Nardelle Moraes","F","52343","Brasil","","29/06/2012","28/01/2007");
		assertEquals("ERRO! Data Inválida!", res);
		
		res = cadastroEnfermeiro.novo("Nardelle Moraes","F","52343","Brasil","26/08/1977","","28/01/2007");
		assertEquals("ERRO! Data Inválida!", res);
		
		res = cadastroEnfermeiro.novo("Nardelle Moraes","F","52343","Brasil","26/08/1977","29/06/2012","");
		assertEquals("ERRO! Data Inválida!", res);
		
		res = cadastroEnfermeiro.novo("Nardelle Moraes","","52343","","26/08/1977","29/06/2012","");
		assertNotEquals("Medico inserido!", res);
		
		/*Teste de CRM*/
		res = cadastroEnfermeiro.novo("Nardelle Moraes","F","472398472839476923864923874687642387468","Brasil","26/08/1977","29/06/2012","28/01/2007");
		assertEquals("Identificador inválido", res);
		
		res = cadastroEnfermeiro.novo("Nardelle Moraes","F","5236ds","Brasil","26/08/1977","29/06/2012","28/01/2007");
		assertEquals("Identificador inválido", res);
		
		res = cadastroEnfermeiro.novo("Nardelle Moraes","F","-12365","Brasil","26/08/1977","29/06/2012","28/01/2007");
		assertEquals("Identificador inválido", res);
		
		/*Teste com datas*/
		res = cadastroEnfermeiro.novo("Nardelle Moraes","F","123643","Brasil","26/08/1977","29/06/2012","03-02-2007");
		assertEquals("ERRO! Data Inválida!", res);
		
		res = cadastroEnfermeiro.novo("Nardelle Moraes","F","123643","Brasil","26//08/1977","29/06/2012","03/02/2007");
		assertEquals("ERRO! Data Inválida!", res);
		
		res = cadastroEnfermeiro.novo("Nardelle Moraes","F","123643","Brasil","26/0a/1977","29/06/2012","03/02/2007");
		assertEquals("ERRO! Data Inválida!", res);
		
		/*Teste com sexo*/
		res = cadastroEnfermeiro.novo("Nardelle Moraes","FD","123643","Brasil","26/08/1977","29/06/2012","03/02/2007");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroEnfermeiro.novo("Nardelle Moraes","2","123643","Brasil","26/08/1977","29/06/2012","03/02/2007");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroEnfermeiro.novo("Nardelle Moraes","f","123643","Brasil","26/08/1977","29/06/2012","03/02/2007");
		assertEquals("Campo Sexo inválido", res);
		
		/*Teste com Numeros*/
		res = cadastroEnfermeiro.novo("Narde5234lle Moraes","F","123643","Brasil","26/08/1977","29/06/2012","03/02/2007");
		assertEquals("Campo Nome inválido", res);
		
		res = cadastroEnfermeiro.novo("Nardelle Moraes","F","123643","Brasil123","26/08/1977","29/06/2012","03/02/2007");
		assertEquals("Campo Nacionalidade inválido", res);
		
		/*Teste com Espaços vazios*/
		res = cadastroEnfermeiro.novo("Nardelle Moraes","F","123643","  ","26/08/1977","29/06/2012","03/02/2007");
		assertEquals("Campo Nacionalidade inválido", res);
		
		res = cadastroEnfermeiro.novo(" ","F","123643","Brasil","26/08/1977","29/06/2012","03/02/2007");
		assertEquals("Campo Nome inválido", res);
		
		res = cadastroEnfermeiro.novo("Joao"," ","123643","Brasil","26/08/1977","29/06/2012","03/02/2007");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroEnfermeiro.novo("Joao","M","123643","Brasil"," ","29/06/2012","03/02/2007");
		assertEquals("ERRO! Data Inválida!", res);
	}
	
	@Test
	public void testaEncontraEnfermeiro() {
		String res;
		CadastroEnfermeiro cadastroEnfermeiro = new CadastroEnfermeiro();
		
		res = cadastroEnfermeiro.encontra("");
		assertEquals("Atributo inválido", res);
		
		res = cadastroEnfermeiro.encontra("  ");
		assertEquals("Atributo inválido", res);
		
		res = cadastroEnfermeiro.encontra("123asd");
		assertEquals("", res);
	}
	
	@Test
	public void testaAlteraEnfermeiro() {
		String res;
		CadastroEnfermeiro cadastroEnfermeiro = new CadastroEnfermeiro();
		
		cadastroEnfermeiro.novo("Joao Carlos","M","123543","Brasil","26/08/1977","29/06/2012","03/02/2007");
		
		res = cadastroEnfermeiro.altera("1236das","Nome", "Jose Ferreira");
		assertEquals("Enfermeiro não cadastrado", res);
		
		res = cadastroEnfermeiro.altera("123543","Nome123", "Jose Ferreira");
		assertEquals("Atributo inválido!", res);
		
		res = cadastroEnfermeiro.altera("123543","Nome", "Jose 123");
		assertEquals("Campo Nome inválido", res);
		
		res = cadastroEnfermeiro.altera("123543","Nome", " ");
		assertEquals("Campo Nome inválido", res);
		
		res = cadastroEnfermeiro.altera("123543","Nome", "");
		assertEquals("Campo Nome inválido", res);
		
		res = cadastroEnfermeiro.altera("123543","Sexo", " ");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroEnfermeiro.altera("123543","Sexo", "FO");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroEnfermeiro.altera("123543","Sexo", "S");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroEnfermeiro.altera("123543","Sexo", "");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroEnfermeiro.altera("123543","Sexo", "1");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroEnfermeiro.altera("123543","Sexo", "@");
		assertEquals("Campo Sexo inválido", res);
	}
}
