package cadastro;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CadastroProcedimentoTest {
	@Test
	public void testaExisteProcedimento() {
		boolean res;
		CadastroProcedimento procedimento = new CadastroProcedimento();
		
		procedimento.novo("111111","Ponte de Saf","50000");
		
		res = procedimento.existe("123603");
		assertEquals(false, res);
		
		res = procedimento.existe("");
		assertEquals(false, res);
		
		res = procedimento.existe("asd");
		assertEquals(false, res);
		
		res = procedimento.existe(" ");
		assertEquals(false, res);
		
		res = procedimento.existe("1");
		assertEquals(false, res);
		
		res = procedimento.existe("11111");
		assertEquals(false, res);
		
		res = procedimento.existe("111111");
		assertEquals(true, res);
	}
	
	@Test
	public void testaNovoProcedimento() {
		String res;
		CadastroProcedimento procedimento = new CadastroProcedimento();
		
		res = procedimento.novo("333333","","50000");
		assertEquals("Campo Descricao inválido", res);
		
		res = procedimento.novo("333333","Proc@","50000");
		assertEquals("Campo Descricao inválido", res);
		
		res = procedimento.novo("333333","","50000");
		assertEquals("Campo Descricao inválido", res);
		
		res = procedimento.novo("333333","Procedimento","500a0");
		assertEquals("Erro: valor de custo inválido", res);
		
		res = procedimento.novo("111a11","Procedimento","500a0");
		assertEquals("Codigo inválido", res);
	}
	
	@Test
	public void testaEncontraProcedimento() {
		String res;
		CadastroProcedimento procedimento = new CadastroProcedimento();
		
		res = procedimento.encontra("   ");
		assertEquals("Atributo inválido", res);
		
		res = procedimento.encontra("");
		assertEquals("Atributo inválido", res);
		
		res = procedimento.encontra("asd");
		assertEquals("Procedimento não cadastrado", res);
	}
	
	@Test
	public void testaAlteraProcedimento() {
		String res;
		CadastroProcedimento procedimento = new CadastroProcedimento();
		
		procedimento.novo("222222","Novo Procedimento","R$50000,0");
		
		res = procedimento.altera("222222", "Descricao", "Pro$ed");
		assertEquals("Campo Descricao inválido", res);
		
		res = procedimento.altera("222222", "Va$or", "50000");
		assertEquals("Atributo inválido!", res);
		
		res = procedimento.altera("222222", "", "50000");
		assertEquals("Atributo inválido!", res);
		
		res = procedimento.altera("222222", "  ", "50000");
		assertEquals("Atributo inválido!", res);
	}
	
	@Test
	public void testaAddMaterial() {
		String res;
		CadastroProcedimento procedimento = new CadastroProcedimento();
		
		res = procedimento.addMaterial("444a444", "4444");
		assertEquals("Codigo Procedimento inválido", res);
		
		res = procedimento.addMaterial("444444", "444s4");
		assertEquals("Codigo Material inválido", res);
	}
	
	@Test
	public void testaAddEquipamento() {
		String res;
		CadastroProcedimento procedimento = new CadastroProcedimento();
		
		res = procedimento.addEquipamento("444a444", "4444");
		assertEquals("Codigo Procedimento inválido", res);
		
		res = procedimento.addEquipamento("444444", "444s4");
		assertEquals("Codigo Equipamento inválido", res);
	}
}
