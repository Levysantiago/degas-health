package facade;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TesteAceitacao2 {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCadastros() {
		Facade facade = new Facade();

		String res;

		//novo médico: Nome, CRM, Sexo, Nacionalidade, Data Nasc., Data Admiss., Data Formatura

		/*res = facade.novoMedico("Nardelle Moraes","M","97719","Brasil","26/08/1977","29/06/2012","28/01/2007");
		assertEquals("Medico inserido!", res);

		res = facade.novoMedico("Rudine%  Rodrigues","M","46193","Brasil","28/05/1962","22/7/1992","18/11/1988");
		assertEquals("ERRO! Caracter '%' Invalido!", res);

		res = facade.novoMedico("Rudinei  R#drigues","M","46193","Brasil","28/05/1962","22/7/1992","18/11/1988");
		assertEquals("ERRO! Caracter '#' Invalido!", res);

		res = facade.novoMedico("Rudinei  Rodrigues","M","46193","Br@sil","28/05/1962","22/7/1992","18/11/1988");
		assertEquals("ERRO! Caracter '@' Invalido!", res);

		res = facade.novoMedico("Rudine%  Rodrigues","M","46193","Br@sil","28/05/1962","22/7/1992","18/11/1988");
		assertEquals("ERRO! Caracter '%' Invalido!", res);

		res = facade.novoMedico("Rudinei  R#drigues","M","46193","Br@sil","28/05/1962","22/7/1992","18/11/1988");
		assertEquals("ERRO! Caracter '#' Invalido!", res);

		res = facade.novoMedico("Rudinei  R@drig#es","M","46193","Brasil","28/05/1962","22/7/1992","18/11/1988");
		assertEquals("ERRO! Caracter '@' Invalido!", res);

		res = facade.novoMedico("Rudinei  Rodrigues","M","46193","Brasil","28/05/1962","22/7/1992","18/11/1988");
		assertEquals("Medico inserido!", res);

		res = facade.novoMedico("Rudinei  Rodrigues","M","46193","Brasil","28/05/1962","22/7/1992","18/11/1988");
		assertEquals("ERRO! CRM Já existente!", res);

		res = facade.novoMedico("Ailton  Carvalho","M","9614","Brasil","26/07/1970","04/10/1999","02/11/1995");
		assertEquals("Medico inserido!", res);

		res = facade.novoMedico("Tarcila do Amaral","F","9614","Brasil","26/07/1970","04/10/1999","02/11/1995");
		assertEquals("ERRO! CRM Já existente!", res);

		res = facade.novoMedico("Larissa Pereira","F","36311","Brasil","22/01/1977","13/08/2005","07/11/1999");
		assertEquals("Medico inserido!", res);

		res = facade.novoMedico("Jonelice  Pinto","F","3566","Portugal","07/02/1989","28/9/2017","22/12/2012");
		assertEquals("Medico inserido!", res);

		res = facade.novoMedico("Alvaro Degas","M","3566","Brasil","05/06/1989","28/09/2017","22/12/2012");
		assertEquals("ERRO! CRM Já existente!", res);

		res = facade.novoMedico("Jaqueline  das Neves","F","89673","Brasil","12/02/1984","31/04/2013","05/07/2011");
		assertEquals("ERRO! Data Inválida!", res);

		res = facade.novoMedico("Jaqueline  das Neves","F","89673","Brasil","12/02/1984","30/2/2013","05/07/2011");
		assertEquals("ERRO! Data Inválida!", res);

		res = facade.novoMedico("Jaqueline  das Neves","F","89673","Brasil","12/02/1984","31/3/2011","05/07/2013");
		assertEquals("ERRO! Inconsistencia de datas: Formatura posterior a admissão!", res);

		res = facade.novoMedico("Jaqueline  das Neves","F","89673","Brasil","12/02/1984","31/05/2013","05/07/2011");
		assertEquals("Medico inserido!", res);

		res = facade.encontraMedico("Nardelle Moraes");
		assertEquals("Nardelle Moraes%M%97719%Brasil%26/08/1977%29/06/2012%28/01/2007", res);

		res = facade.encontraMedico("36311");
		assertEquals("Larissa Pereira%F%36311%Brasil%22/01/1977%13/08/2005%07/11/1999", res);*/

		res = facade.alteraMedico("97719","Nome", "Jose Ferreira");
		assertEquals("Alteracao executada com sucesso!", res);
		res = facade.encontraMedico("97719");
		assertEquals("Jose Ferreira%M%97719%Brasil%26/08/1977%29/06/2012%28/01/2007", res);

		res = facade.alteraMedico("46193","Sexo", "F");
		assertEquals("Alteracao executada com sucesso!", res);
		res = facade.encontraMedico("46193");
		assertEquals("Rudinei  Rodrigues%F%46193%Brasil%28/05/1962%22/7/1992%18/11/1988", res);

		res = facade.alteraMedico("9614","Nacionalidade", "Italia");
		assertEquals("Alteracao executada com sucesso!", res);
		res = facade.encontraMedico("9614");
		assertEquals("Ailton  Carvalho%M%9614%Italia%26/07/1970%04/10/1999%02/11/1995", res);

		res = facade.alteraMedico("36311","DtNasc", "31/01/1971");
		assertEquals("Alteracao executada com sucesso!", res);
		res = facade.encontraMedico("36311");
		assertEquals("Larissa Pereira%F%36311%Brasil%31/01/1971%13/08/2005%07/11/1999", res);

		res = facade.alteraMedico("3566","DtAdmiss", "19/07/2002");
		assertEquals("ERRO! Inconsistencia de datas: Formatura posterior a admissão!", res);

		res = facade.alteraMedico("3566","DtAdmiss", "19/07/2015");
		assertEquals("Alteracao executada com sucesso!", res);
		res = facade.encontraMedico("3566");
		assertEquals("Jonelice  Pinto%F%3566%Portugal%07/02/1989%19/07/2015%22/12/2012", res);

		res = facade.alteraMedico("89673","DtFormatura", "30/03/2014");
		assertEquals("ERRO! Inconsistencia de datas: Formatura posterior a admissão!", res);

		res = facade.alteraMedico("89673","DtFormatura", "30/03/2010");
		assertEquals("Alteracao executada com sucesso!", res);
		res = facade.encontraMedico("89673");
		assertEquals("Jaqueline  das Neves%F%89673%Brasil%12/02/1984%31/05/2013%30/03/2010", res);
		
		/*---------------------------------------------------------------------------------------*/


		//COREN -> Conselho Regional de Enfermagem
		//novoEnfermeiro: Nome, Sexo, COREN, Nacionalidade, Data Nasc., Data Admiss., Data Formatura


		res = facade.novoEnfermeiro("Amadildo Maldonado","M","197719","Brasil","24/08/1969","29/06/2017","28/01/2016");
		assertEquals("Enfermeiro inserido!", res);

		res = facade.novoEnfermeiro("Bruna%  Surfer","F","151329","Brasil","18/04/1972","22/07/1999","18/11/1990");
		assertEquals("ERRO! Caracter '%' Invalido!", res);

		res = facade.novoEnfermeiro("Carine  R#drigues","F","151329","Brasil","28/05/1962","22/7/1992","18/11/1988");
		assertEquals("ERRO! Caracter '#' Invalido!", res);

		res = facade.novoEnfermeiro("Doravante  Alavante","F","151329","Br@sil","24/01/1982","22/7/2012","18/11/2000");
		assertEquals("ERRO! Caracter '@' Invalido!", res);

		res = facade.novoEnfermeiro("Edilen%e  dos Santos","F","151329","Br@sil","03/12/1991","22/1/2018","18/11/2017");
		assertEquals("ERRO! Caracter '%' Invalido!", res);

		res = facade.novoEnfermeiro("Fábio  J#nior","M","151329","Br@sil","28/05/1962","23/03/1992","28/12/1988");
		assertEquals("ERRO! Caracter '#' Invalido!", res);

		res = facade.novoEnfermeiro("Geremilda  L@jot#","F","151329","Brasil","18/05/1962","22/07/1992","18/11/1988");
		assertEquals("ERRO! Caracter '@' Invalido!", res);

		res = facade.novoEnfermeiro("Horáclides da Hora Certa","M","151329","Brasil","28/05/1962","22/07/1992","18/11/1988");
		assertEquals("Enfermeiro inserido!", res);

		res = facade.novoEnfermeiro("Ítalo  Pinto Vloger","M","151329","Brasil","28/05/1962","22/7/1992","18/11/1988");
		assertEquals("ERRO! COREN Já existente!", res);

		res = facade.novoEnfermeiro("Jalapeno Kino","M","19614","Brasil","26/07/1970","04/10/1999","02/11/1995");
		assertEquals("Enfermeiro inserido!", res);

		res = facade.novoEnfermeiro("Larissa de Las Dolores","F","19614","Brasil","26/07/1970","04/10/1999","02/11/1995");
		assertEquals("ERRO! COREN Já existente!", res);

		res = facade.novoEnfermeiro("Maria do Bairro","F","136311","Brasil","22/01/1977","13/08/2005","07/11/1999");
		assertEquals("Enfermeiro inserido!", res);


		res = facade.novoEnfermeiro("Alfarroba Cacau","F","1566","Portugal","07/02/1989","28/09/2017","22/12/2012");
		assertEquals("Enfermeiro inserido!", res);

		res = facade.novoEnfermeiro("Olivia Oliveira das Olivas","M","1566","Brasil","05/06/1989","28/09/2017","22/12/2012");
		assertEquals("ERRO! COREN Já existente!", res);

		res = facade.novoEnfermeiro("John  de Las Nieves","M","189673","Brasil","12/02/1984","31/04/2013","05/07/2011");
		assertEquals("ERRO! Data Inválida!", res);

		res = facade.novoEnfermeiro("John  Gothic Ranger","M","189673","Brasil","12/02/1984","30/2/2013","05/07/2011");
		assertEquals("ERRO! Data Inválida!", res);

		res = facade.novoEnfermeiro("Jaquelina  das Neves","F","189673","Brasil","12/02/1984","31/3/2011","05/07/2013");
		assertEquals("ERRO! Inconsistencia de datas: Formatura posterior a admissão!", res);

		res = facade.novoEnfermeiro("Bátima da Silva Sauro","F","189673","Brasil","12/02/1984","31/05/2013","05/07/2011");
		assertEquals("Enfermeiro inserido!", res);

		res = facade.encontraEnfermeiro("Amadildo Maldonado");
		assertEquals("Amadildo Maldonado%M%197719%Brasil%24/08/1969%29/06/2017%28/01/2016", res);

		res = facade.encontraEnfermeiro("151329");
		assertEquals("Horáclides da Hora Certa%M%151329%Brasil%28/05/1962%22/07/1992%18/11/1988", res);

		res = facade.alteraEnfermeiro("197719","Nome", "Arminho Amando Amador Amor Divino");
		assertEquals("Alteracao executada com sucesso!", res);
		res = facade.encontraEnfermeiro("197719");
		assertEquals("Arminho Amando Amador Amor Divino%M%197719%Brasil%24/08/1969%29/06/2017%28/01/2016", res);

		res = facade.alteraEnfermeiro("151329","Sexo", "F");
		assertEquals("Alteracao executada com sucesso!", res);
		res = facade.encontraEnfermeiro("151329");
		assertEquals("Horáclides da Hora Certa%F%151329%Brasil%28/05/1962%22/07/1992%18/11/1988", res);

		res = facade.alteraEnfermeiro("19614","Nacionalidade", "Italia");
		assertEquals("Alteracao executada com sucesso!", res);
		res = facade.encontraEnfermeiro("19614");
		assertEquals("Jalapeno Kino%M%19614%Italia%26/07/1970%04/10/1999%02/11/1995", res);

		res = facade.alteraEnfermeiro("136311","DtNasc", "31/01/1971");
		assertEquals("Alteracao executada com sucesso!", res);
		res = facade.encontraEnfermeiro("136311");
		assertEquals("Maria do Bairro%F%136311%Brasil%31/01/1971%13/08/2005%07/11/1999", res);

		res = facade.alteraEnfermeiro("1566","DtAdmiss", "19/02/2002");
		assertEquals("ERRO! Inconsistencia de datas: Formatura posterior a admissão!", res);

		res = facade.alteraEnfermeiro("1566","DtAdmiss", "19/07/2015");
		assertEquals("Alteracao executada com sucesso!", res);
		res = facade.encontraEnfermeiro("1566");
		assertEquals("Alfarroba Cacau%F%1566%Portugal%07/02/1989%19/07/2015%22/12/2012", res);

		res = facade.alteraEnfermeiro("189673","DtFormatura", "30/03/2014");
		assertEquals("ERRO! Inconsistencia de datas: Formatura posterior a admissão!", res);

		res = facade.alteraEnfermeiro("189673","DtFormatura", "30/03/2010");
		assertEquals("Alteracao executada com sucesso!", res);
		res = facade.encontraEnfermeiro("189673");
		assertEquals("Bátima da Silva Sauro%F%189673%Brasil%12/02/1984%31/05/2013%30/03/2010", res);

		//COREN -> Conselho Regional de Enfermagem. O mesmo serve para Auxiliar de Enfermagem ou Técnico de Enfermagem.
		//novoAuxiliar: Nome, Sexo, COREN, Nacionalidade, Data Nasc., Data Admiss., Data Formatura


		res = facade.novoAuxiliar("Augusta Rua","M","297719","Brasil","24/02/1969","29/12/2016","28/06/2015");
		assertEquals("Auxiliar/Técnico inserido!", res);

		res = facade.novoAuxiliar("Bárbara%  Leonel","F","251329","Brasil","18/10/1971","22/01/1999","18/05/1990");
		assertEquals("ERRO! Caracter '%' Invalido!", res);

		res = facade.novoAuxiliar("Cêcharpe L#dligues","M","251329","Brasil","28/11/1961","22/01/1992","18/05/1988");
		assertEquals("ERRO! Caracter '#' Invalido!", res);

		res = facade.novoAuxiliar("Dalva Branquinha Alves","F","251329","Br@sil","24/06/1981","22/01/2012","18/05/2000");
		assertEquals("ERRO! Caracter '@' Invalido!", res);

		res = facade.novoAuxiliar("Elie%e  dos Ponteiros","F","251329","Br@sil","03/06/1991","22/06/2017","18/05/2017");
		assertEquals("ERRO! Caracter '%' Invalido!", res);

		res = facade.novoAuxiliar("Fabíola do C#menor","M","251329","Br@sil","28/11/1961","23/09/1991","28/08/1988");
		assertEquals("ERRO! Caracter '#' Invalido!", res);

		res = facade.novoAuxiliar("Galinda C@xaqt#","F","251329","Brasil","18/11/1962","22/01/1992","18/05/1988");
		assertEquals("ERRO! Caracter '@' Invalido!", res);

		res = facade.novoAuxiliar("Hermitão da Gruta Longe","M","251329","Brasil","28/11/1962","22/01/1992","18/05/1988");
		assertEquals("Auxiliar/Técnico inserido!", res);

		res = facade.novoAuxiliar("Ivan da Van Dutch","M","251329","Brasil","28/11/1961","22/01/1992","18/105/1988");
		assertEquals("ERRO! COREN Já existente!", res);

		res = facade.novoAuxiliar("Jileno da Lamina Cega","M","29614","Brasil","26/01/1970","04/04/1999","02/05/1995");
		assertEquals("Auxiliar/Técnico inserido!", res);

		res = facade.novoAuxiliar("Lucas Bolseiro","M","29614","Brasil","26/03/1970","04/02/1999","02/10/1995");
		assertEquals("ERRO! COREN Já existente!", res);

		res = facade.novoAuxiliar("Maria do Bairro","F","236311","Brasil","22/07/1976","13/08/2005","07/05/1999");
		assertEquals("Auxiliar/Técnico inserido!", res);


		res = facade.novoAuxiliar("Naiara Cinquenta","F","21566","Tchéquia","07/10/1988","28/09/2016","22/06/2012");
		assertEquals("Auxiliar/Técnico inserido!", res);

		res = facade.novoAuxiliar("Olimpo Luz do Panteão Divino ","M","21566","Brasil","05/12/1989","28/03/2017","22/06/2012");
		assertEquals("ERRO! COREN Já existente!", res);

		res = facade.novoAuxiliar("Pablo Bardo da Sofrência Boêmio","M","289673","Brasil","12/10/1984","31/11/2013","05/01/2011");
		assertEquals("ERRO! Data Inválida!", res);

		res = facade.novoAuxiliar("Quincas Borba","M","289673","Brasil","12/10/1983","31/11/2012","05/01/2011");
		assertEquals("ERRO! Data Inválida!", res);

		res = facade.novoAuxiliar("Rubens Paiva","M","289673","Brasil","12/02/1984","31/3/2011","05/07/2013");
		assertEquals("ERRO! Inconsistencia de datas: Formatura posterior a admissão!", res);

		res = facade.novoAuxiliar("Sábado de Sol Silva","M","289673","Brasil","12/08/1984","30/11/2013","05/01/2011");
		assertEquals("Auxiliar/Técnico inserido!", res);

		res = facade.encontraAuxiliar("Augusta Rua");
		assertEquals("Augusta Rua%M%297719%Brasil%24/02/1969%29/12/2016%28/06/2015", res);

		res = facade.encontraAuxiliar("251329");
		assertEquals("Hermitão da Gruta Longe%M%251329%Brasil%28/11/1962%22/01/1992%18/05/1988", res);


		res = facade.alteraAuxiliar("297719","Nome", "Arsenio Pereira da Produção Eficaz");
		assertEquals("Alteracao executada com sucesso!", res);
		res = facade.encontraAuxiliar("297719");
		assertEquals("Arsenio Pereira da Produção Eficaz%M%297719%Brasil%24/02/1969%29/12/2016%28/06/2015", res);

		res = facade.alteraAuxiliar("251329","Sexo", "F");
		assertEquals("Alteracao executada com sucesso!", res);
		res = facade.encontraAuxiliar("251329");
		assertEquals("Hermitão da Gruta Longe%F%251329%Brasil%28/11/1962%22/01/1992%18/05/1988", res);

		res = facade.alteraAuxiliar("29614","Nacionalidade", "Argentina");
		assertEquals("Alteracao executada com sucesso!", res);
		res = facade.encontraAuxiliar("29614");
		assertEquals("Jileno da Lamina Cega%M%29614%Argentina%26/01/1970%04/04/1999%02/05/1995", res);

		res = facade.alteraAuxiliar("236311","DtNasc", "31/01/1971");
		assertEquals("Alteracao executada com sucesso!", res);
		res = facade.encontraAuxiliar("236311");
		assertEquals("Maria do Bairro%F%236311%Brasil%31/01/1971%13/08/2005%07/05/1999", res);

		res = facade.alteraAuxiliar("21566","DtAdmiss", "19/02/2002");
		assertEquals("ERRO! Inconsistencia de datas: Formatura posterior a admissão!", res);

		res = facade.alteraAuxiliar("21566","DtAdmiss", "19/07/2015");
		assertEquals("Alteracao executada com sucesso!", res);
		res = facade.encontraAuxiliar("21566");
		assertEquals("Naiara Cinquenta%F%21566%Tchéquia%07/10/1988%19/07/2015%22/06/2012", res);

		res = facade.alteraAuxiliar("289673","DtFormatura", "30/03/2014");
		assertEquals("ERRO! Inconsistencia de datas: Formatura posterior a admissão!", res);

		res = facade.alteraAuxiliar("289673","DtFormatura", "30/03/2010");
		assertEquals("Alteracao executada com sucesso!", res);
		res = facade.encontraAuxiliar("289673");
		assertEquals("Sábado de Sol Silva%M%289673%Brasil%12/08/1984%30/11/2013%30/03/2010", res);

			
	}

}
