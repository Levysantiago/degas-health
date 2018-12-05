package cadastro;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CadastroMaterialTest {
	@Test
	public void testaExisteMaterial() {
		boolean res;
		CadastroMaterial material = new CadastroMaterial();
		
		material.novo("1111","Material","50000");
		
		res = material.existe("1236");
		assertEquals(false, res);
		
		res = material.existe("");
		assertEquals(false, res);
		
		res = material.existe("asd");
		assertEquals(false, res);
		
		res = material.existe(" ");
		assertEquals(false, res);
		
		res = material.existe("1");
		assertEquals(false, res);
		
		res = material.existe("11111");
		assertEquals(false, res);
		
		res = material.existe("1111");
		assertEquals(true, res);
	}
	
	@Test
	public void testaNovoMaterial() {
		String res;
		CadastroMaterial material = new CadastroMaterial();
		
		res = material.novo("3333","","50000");
		assertEquals("Campo Descricao inválido", res);
		
		res = material.novo("3333","Asdsds@","50000");
		assertEquals("Campo Descricao inválido", res);
		
		res = material.novo("3333","","50000");
		assertEquals("Campo Descricao inválido", res);
		
		res = material.novo("3333","Materiais","500a0");
		assertEquals("Erro: valor de custo inválido", res);
		
		res = material.novo("11a1","Materiais","500a0");
		assertEquals("Codigo inválido", res);
	}
	
	@Test
	public void testaEncontraMaterial() {
		String res;
		CadastroMaterial material = new CadastroMaterial();
		
		res = material.encontra("   ");
		assertEquals("Atributo inválido", res);
		
		res = material.encontra("");
		assertEquals("Atributo inválido", res);
		
		res = material.encontra("asd");
		assertEquals("Material não cadastrado", res);
	}
	
	@Test
	public void testaAlteraMaterial() {
		String res;
		CadastroMaterial material = new CadastroMaterial();
		
		material.novo("2222","Novo Material","R$50000,0");
		
		res = material.altera("2222", "Descricao", "Mate$rial");
		assertEquals("Campo Descricao inválido", res);
		
		res = material.altera("2222", "Va$or", "50000");
		assertEquals("Atributo inválido!", res);
		
		res = material.altera("2222", "", "50000");
		assertEquals("Atributo inválido!", res);
		
		res = material.altera("2222", "  ", "50000");
		assertEquals("Atributo inválido!", res);
		
	}
}
