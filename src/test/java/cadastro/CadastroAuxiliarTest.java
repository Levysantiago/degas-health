package cadastro;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CadastroAuxiliarTest {
	@Test
	public void testaExisteAuxiliar() {
		boolean res;
		CadastroAuxiliar cadastro = new CadastroAuxiliar();
		
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
		
		res = cadastro.existe("11111");
		assertEquals(false, res);
		
		res = cadastro.existe("111111");
		assertEquals(true, res);
	}
	
	@Test
	public void testaNovoAuxiliar() {
		String res;
		CadastroAuxiliar cadastroAuxiliar = new CadastroAuxiliar();
		
		/*Teste de campos vazios*/
		res = cadastroAuxiliar.novo("","M","91719","Brasil","26/08/1977","29/06/2012","28/01/2007");
		assertEquals("Campo Nome inválido", res);
		
		res = cadastroAuxiliar.novo("Nardelle Moraes","","91719","Brasil","26/08/1977","29/06/2012","28/01/2007");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroAuxiliar.novo("Nardelle Moraes","F","","Brasil","26/08/1977","29/06/2012","28/01/2007");
		assertEquals("Identificador inválido", res);
		
		res = cadastroAuxiliar.novo("Nardelle Moraes","F","53432","","26/08/1977","29/06/2012","28/01/2007");
		assertEquals("Campo Nacionalidade inválido", res);
		
		res = cadastroAuxiliar.novo("Nardelle Moraes","F","52343","Brasil","","29/06/2012","28/01/2007");
		assertEquals("ERRO! Data Inválida!", res);
		
		res = cadastroAuxiliar.novo("Nardelle Moraes","F","52343","Brasil","26/08/1977","","28/01/2007");
		assertEquals("ERRO! Data Inválida!", res);
		
		res = cadastroAuxiliar.novo("Nardelle Moraes","F","52343","Brasil","26/08/1977","29/06/2012","");
		assertEquals("ERRO! Data Inválida!", res);
		
		/*Teste de CRM*/
		res = cadastroAuxiliar.novo("Nardelle Moraes","F","472398472839476923864923874687642387468","Brasil","26/08/1977","29/06/2012","28/01/2007");
		assertEquals("Identificador inválido", res);
		
		res = cadastroAuxiliar.novo("Nardelle Moraes","F","5236ds","Brasil","26/08/1977","29/06/2012","28/01/2007");
		assertEquals("Identificador inválido", res);
		
		res = cadastroAuxiliar.novo("Nardelle Moraes","F","-12365","Brasil","26/08/1977","29/06/2012","28/01/2007");
		assertEquals("Identificador inválido", res);
		
		/*Teste com datas*/
		res = cadastroAuxiliar.novo("Nardelle Moraes","F","123643","Brasil","26/08/1977","29/06/2012","03-02-2007");
		assertEquals("ERRO! Data Inválida!", res);
		
		res = cadastroAuxiliar.novo("Nardelle Moraes","F","123643","Brasil","26//08/1977","29/06/2012","03/02/2007");
		assertEquals("ERRO! Data Inválida!", res);
		
		res = cadastroAuxiliar.novo("Nardelle Moraes","F","123643","Brasil","26/0a/1977","29/06/2012","03/02/2007");
		assertEquals("ERRO! Data Inválida!", res);
		
		/*Teste com sexo*/
		res = cadastroAuxiliar.novo("Nardelle Moraes","FD","123643","Brasil","26/08/1977","29/06/2012","03/02/2007");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroAuxiliar.novo("Nardelle Moraes","2","123643","Brasil","26/08/1977","29/06/2012","03/02/2007");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroAuxiliar.novo("Nardelle Moraes","f","123643","Brasil","26/08/1977","29/06/2012","03/02/2007");
		assertEquals("Campo Sexo inválido", res);
		
		/*Teste com Numeros*/
		res = cadastroAuxiliar.novo("Narde5234lle Moraes","F","123643","Brasil","26/08/1977","29/06/2012","03/02/2007");
		assertEquals("Campo Nome inválido", res);
		
		res = cadastroAuxiliar.novo("Nardelle Moraes","F","123643","Brasil123","26/08/1977","29/06/2012","03/02/2007");
		assertEquals("Campo Nacionalidade inválido", res);
		
		/*Teste com Espaços vazios*/
		res = cadastroAuxiliar.novo("Nardelle Moraes","F","123643","  ","26/08/1977","29/06/2012","03/02/2007");
		assertEquals("Campo Nacionalidade inválido", res);
		
		res = cadastroAuxiliar.novo(" ","F","123643","Brasil","26/08/1977","29/06/2012","03/02/2007");
		assertEquals("Campo Nome inválido", res);
		
		res = cadastroAuxiliar.novo("Joao"," ","123643","Brasil","26/08/1977","29/06/2012","03/02/2007");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroAuxiliar.novo("Joao","M","123643","Brasil"," ","29/06/2012","03/02/2007");
		assertEquals("ERRO! Data Inválida!", res);
	}
	
	@Test
	public void testaEncontraAuxiliar() {
		String res;
		CadastroAuxiliar cadastroAuxiliar = new CadastroAuxiliar();
		
		res = cadastroAuxiliar.encontra("");
		assertEquals("Atributo inválido", res);
		
		res = cadastroAuxiliar.encontra("  ");
		assertEquals("Atributo inválido", res);
		
		res = cadastroAuxiliar.encontra("123asd");
		assertEquals("", res);
	}
	
	@Test
	public void testaAlteraAuxiliar() {
		String res;
		CadastroAuxiliar cadastroAuxiliar = new CadastroAuxiliar();
		
		cadastroAuxiliar.novo("Joao Carlos","M","123543","Brasil","26/08/1977","29/06/2012","03/02/2007");
		
		res = cadastroAuxiliar.altera("1236das","Nome", "Jose Ferreira");
		assertEquals("Auxiliar não cadastrado", res);
		
		res = cadastroAuxiliar.altera("123543","Nome123", "Jose Ferreira");
		assertEquals("Atributo inválido!", res);
		
		res = cadastroAuxiliar.altera("123543","Nome", "Jose 123");
		assertEquals("Campo Nome inválido", res);
		
		res = cadastroAuxiliar.altera("123543","Nome", " ");
		assertEquals("Campo Nome inválido", res);
		
		res = cadastroAuxiliar.altera("123543","Nome", "");
		assertEquals("Campo Nome inválido", res);
		
		res = cadastroAuxiliar.altera("123543","Sexo", " ");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroAuxiliar.altera("123543","Sexo", "FO");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroAuxiliar.altera("123543","Sexo", "S");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroAuxiliar.altera("123543","Sexo", "");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroAuxiliar.altera("123543","Sexo", "1");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroAuxiliar.altera("123543","Sexo", "@");
		assertEquals("Campo Sexo inválido", res);
	}
}
