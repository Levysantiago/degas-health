package cadastro;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CadastroEquipamentoTest {
	@Test
	public void testaExisteEquipamento() {
		boolean res;
		CadastroEquipamento equipamento = new CadastroEquipamento();
		
		equipamento.novo("111","EquipamentoExiste","50000");
		
		res = equipamento.existe("000");
		assertEquals(false, res);
		
		res = equipamento.existe("");
		assertEquals(false, res);
		
		res = equipamento.existe("asd");
		assertEquals(false, res);
		
		res = equipamento.existe(" ");
		assertEquals(false, res);
		
		res = equipamento.existe("1");
		assertEquals(false, res);
		
		res = equipamento.existe("232");
		assertEquals(false, res);
		
		res = equipamento.existe("111");
		assertEquals(true, res);
	}
	
	@Test
	public void testaNovoEquipamento() {
		String res;
		CadastroEquipamento equipamento = new CadastroEquipamento();
		
		res = equipamento.novo("333","","50000");
		assertEquals("Campo Descricao inválido", res);
		
		res = equipamento.novo("333","Asdsds@","50000");
		assertEquals("Campo Descricao inválido", res);
		
		res = equipamento.novo("333","","50000");
		assertEquals("Campo Descricao inválido", res);
		
		res = equipamento.novo("333","Bisturi","500a0");
		assertEquals("Erro: valor de custo inválido", res);
		
		res = equipamento.novo("33a","Bisturi","500a0");
		assertEquals("Codigo inválido", res);
	}
	
	@Test
	public void testaEncontraEquipamento() {
		String res;
		CadastroEquipamento equipamento = new CadastroEquipamento();
		
		res = equipamento.encontra("   ");
		assertEquals("Atributo inválido", res);
		
		res = equipamento.encontra("");
		assertEquals("Atributo inválido", res);
		
		res = equipamento.encontra("asd");
		assertEquals("Equipamento não cadastrado", res);
	}
	
	@Test
	public void testaAlteraEquipamento() {
		String res;
		CadastroEquipamento equipamento = new CadastroEquipamento();
		
		equipamento.novo("222","Equip","R$50000,0");
		
		res = equipamento.altera("222", "Descricao", "Bi$turi");
		assertEquals("Campo Descricao inválido", res);
		
		res = equipamento.altera("222", "Va$or", "50000");
		assertEquals("Atributo inválido!", res);
		
		res = equipamento.altera("222", "", "50000");
		assertEquals("Atributo inválido!", res);
		
		res = equipamento.altera("222", "  ", "50000");
		assertEquals("Atributo inválido!", res);
	}
	
	@Test
	public void testaTombaEquipamento() {
		String res;
		CadastroEquipamento equipamento = new CadastroEquipamento();
		
		res = equipamento.tombaEquipamento("444", "M91j123k1h2j3122");
		assertEquals("Codigo de tombo inválido", res);
		
		res = equipamento.tombaEquipamento("44#4", "M912");
		assertEquals("Codigo de equipamento inválido", res);
	}
}
