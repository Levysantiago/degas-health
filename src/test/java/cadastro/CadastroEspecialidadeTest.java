package cadastro;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CadastroEspecialidadeTest {
	@Test
	public void testaExisteEspecialidade() {
		boolean res;
		CadastroEspecialidade especialidade = new CadastroEspecialidade();
		
		especialidade.novo("111","EspecialidadeExiste");
		
		res = especialidade.existe("000");
		assertEquals(false, res);
		
		res = especialidade.existe("");
		assertEquals(false, res);
		
		res = especialidade.existe("asd");
		assertEquals(false, res);
		
		res = especialidade.existe(" ");
		assertEquals(false, res);
		
		res = especialidade.existe("1");
		assertEquals(false, res);
		
		res = especialidade.existe("11");
		assertEquals(false, res);
		
		res = especialidade.existe("111");
		assertEquals(true, res);
	}
	
	@Test
	public void testaNovoEspecialidade() {
		String res;
		CadastroEspecialidade especialidade = new CadastroEspecialidade();
		
		res = especialidade.novo("333","");
		assertEquals("Campo Descricao inválido", res);
		
		res = especialidade.novo("333","Esp@");
		assertEquals("Campo Descricao inválido", res);
		
		res = especialidade.novo("333","");
		assertEquals("Campo Descricao inválido", res);
			
		res = especialidade.novo("1a1","Especialidade");
		assertEquals("Codigo inválido", res);
	}
	
	@Test
	public void testaEncontraEspecialidade() {
		String res;
		CadastroEspecialidade especialidade = new CadastroEspecialidade();
		
		res = especialidade.encontra("   ");
		assertEquals("Atributo inválido", res);
		
		res = especialidade.encontra("");
		assertEquals("Atributo inválido", res);
		
		res = especialidade.encontra("asd");
		assertEquals("Especialidade não cadastrada", res);
	}
	
	@Test
	public void testaAlteraEspecialidade() {
		String res;
		CadastroEspecialidade especialidade = new CadastroEspecialidade();
		
		especialidade.novo("222","Novo Especialidade");
		
		res = especialidade.altera("222", "Descricao", "Esp$e");
		assertEquals("Campo Descricao inválido", res);
		
		res = especialidade.altera("222", "Descr$icao", "Espe");
		assertEquals("Atributo inválido!", res);
	}
}
