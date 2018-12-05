package cadastro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class CadastroMedicoTest {
	@Test
	public void testaExisteMedico() {
		boolean res;
		CadastroMedico cadastro = new CadastroMedico();
		
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
	public void testaNovoMedico() {
		String res;
		CadastroMedico cadastroMedico = new CadastroMedico();
		
		/*Teste de campos vazios*/
		res = cadastroMedico.novo("","M","97119","Brasil","26/08/1977","29/06/2012","28/01/2007");
		assertEquals("Campo Nome inválido", res);
		
		res = cadastroMedico.novo("Nardelle Moraes","","97119","Brasil","26/08/1977","29/06/2012","28/01/2007");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroMedico.novo("Nardelle Moraes","F","","Brasil","26/08/1977","29/06/2012","28/01/2007");
		assertEquals("Identificador inválido", res);
		
		res = cadastroMedico.novo("Nardelle Moraes","F","53432","","26/08/1977","29/06/2012","28/01/2007");
		assertEquals("Campo Nacionalidade inválido", res);
		
		res = cadastroMedico.novo("Nardelle Moraes","F","52343","Brasil","","29/06/2012","28/01/2007");
		assertEquals("ERRO! Data Inválida!", res);
		
		res = cadastroMedico.novo("Nardelle Moraes","F","52343","Brasil","26/08/1977","","28/01/2007");
		assertEquals("ERRO! Data Inválida!", res);
		
		res = cadastroMedico.novo("Nardelle Moraes","F","52343","Brasil","26/08/1977","29/06/2012","");
		assertEquals("ERRO! Data Inválida!", res);
		
		res = cadastroMedico.novo("Nardelle Moraes","","52343","","26/08/1977","29/06/2012","");
		assertNotEquals("Medico inserido!", res);
		
		/*Teste de CRM*/
		res = cadastroMedico.novo("Nardelle Moraes","F","472398472839476923864923874687642387468","Brasil","26/08/1977","29/06/2012","28/01/2007");
		assertEquals("Identificador inválido", res);
		
		res = cadastroMedico.novo("Nardelle Moraes","F","5236ds","Brasil","26/08/1977","29/06/2012","28/01/2007");
		assertEquals("Identificador inválido", res);
		
		res = cadastroMedico.novo("Nardelle Moraes","F","-12365","Brasil","26/08/1977","29/06/2012","28/01/2007");
		assertEquals("Identificador inválido", res);
		
		/*Teste com datas*/
		res = cadastroMedico.novo("Nardelle Moraes","F","123643","Brasil","26/08/1977","29/06/2012","03-02-2007");
		assertEquals("ERRO! Data Inválida!", res);
		
		res = cadastroMedico.novo("Nardelle Moraes","F","123643","Brasil","26//08/1977","29/06/2012","03/02/2007");
		assertEquals("ERRO! Data Inválida!", res);
		
		res = cadastroMedico.novo("Nardelle Moraes","F","123643","Brasil","26/0a/1977","29/06/2012","03/02/2007");
		assertEquals("ERRO! Data Inválida!", res);
		
		/*Teste com sexo*/
		res = cadastroMedico.novo("Nardelle Moraes","FD","123643","Brasil","26/08/1977","29/06/2012","03/02/2007");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroMedico.novo("Nardelle Moraes","2","123643","Brasil","26/08/1977","29/06/2012","03/02/2007");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroMedico.novo("Nardelle Moraes","f","123643","Brasil","26/08/1977","29/06/2012","03/02/2007");
		assertEquals("Campo Sexo inválido", res);
		
		/*Teste com Numeros*/
		res = cadastroMedico.novo("Narde5234lle Moraes","F","123643","Brasil","26/08/1977","29/06/2012","03/02/2007");
		assertEquals("Campo Nome inválido", res);
		
		res = cadastroMedico.novo("Nardelle Moraes","F","123643","Brasil123","26/08/1977","29/06/2012","03/02/2007");
		assertEquals("Campo Nacionalidade inválido", res);
		
		/*Teste com Espaços vazios*/
		res = cadastroMedico.novo("Nardelle Moraes","F","123643","  ","26/08/1977","29/06/2012","03/02/2007");
		assertEquals("Campo Nacionalidade inválido", res);
		
		res = cadastroMedico.novo(" ","F","123643","Brasil","26/08/1977","29/06/2012","03/02/2007");
		assertEquals("Campo Nome inválido", res);
		
		res = cadastroMedico.novo("Joao"," ","123643","Brasil","26/08/1977","29/06/2012","03/02/2007");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroMedico.novo("Joao","M","123643","Brasil"," ","29/06/2012","03/02/2007");
		assertEquals("ERRO! Data Inválida!", res);
	}
	
	@Test
	public void testaEncontraMedico() {
		String res;
		CadastroMedico cadastroMedico = new CadastroMedico();
		
		res = cadastroMedico.encontra("");
		assertEquals("Atributo inválido", res);
		
		res = cadastroMedico.encontra("  ");
		assertEquals("Atributo inválido", res);
		
		res = cadastroMedico.encontra("123asd");
		assertEquals("Medico não cadastrado", res);
	}
	
	@Test
	public void testaAlteraMedico() {
		String res;
		CadastroMedico cadastroMedico = new CadastroMedico();
		
		cadastroMedico.novo("Joao Carlos","M","123543","Brasil","26/08/1977","29/06/2012","03/02/2007");
		
		res = cadastroMedico.altera("1236das","Nome", "Jose Ferreira");
		assertEquals("Médico não cadastrado", res);
		
		res = cadastroMedico.altera("123543","Nome123", "Jose Ferreira");
		assertEquals("Atributo inválido!", res);
		
		res = cadastroMedico.altera("123543","Nome", "Jose 123");
		assertEquals("Campo Nome inválido", res);
		
		res = cadastroMedico.altera("123543","Nome", " ");
		assertEquals("Campo Nome inválido", res);
		
		res = cadastroMedico.altera("123543","Nome", "");
		assertEquals("Campo Nome inválido", res);
		
		res = cadastroMedico.altera("123543","Sexo", " ");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroMedico.altera("123543","Sexo", "FO");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroMedico.altera("123543","Sexo", "S");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroMedico.altera("123543","Sexo", "");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroMedico.altera("123543","Sexo", "1");
		assertEquals("Campo Sexo inválido", res);
		
		res = cadastroMedico.altera("123543","Sexo", "@");
		assertEquals("Campo Sexo inválido", res);
	}
}
