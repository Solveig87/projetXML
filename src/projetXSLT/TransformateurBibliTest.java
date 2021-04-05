package projetXSLT;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;


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
	void testconvertXML()throws SAXException, TransformerException, ParserConfigurationException, IOException, BadlyFormedXMLException, FormatException {
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
