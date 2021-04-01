package projetXSLT;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TransformateurBibliTest {

	private TransformateurBibli transformateurBibli;
	
	@BeforeEach
	void setUp() throws Exception {
				
		transformateurBibli=new TransformateurBibli();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Test
	void testconvertXML() {
		//GIVEN
		String input = "bibliotheque.xml";
		String format = "pdf";
		String output = "bibliotheque";
		
		//WHEN
		transformateurBibli.convertXML(input, output, format);
		
		//THEN
		assertTrue(new File(output+"."+format).exists());
		
	}

}
