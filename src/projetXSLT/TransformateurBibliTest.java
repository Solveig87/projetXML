package projetXSLT;

import static org.junit.jupiter.api.Assertions.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
 
import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.rules.TemporaryFolder;

/**
 * Classe permettant de tester les méthodes de la classe TransformateurBibli
 * @see TransformateurBibli
 */
public class TransformateurBibliTest {

	private TransformateurBibli transformateurBibli;
	
	@Before
	public void setUp() throws Exception {
				
		transformateurBibli = new TransformateurBibli();
	}
	
	@Rule
	public TemporaryFolder repertoireTest = new TemporaryFolder();
	
	@Test
	public void testconvertXML_succes()throws SAXException, TransformerException, ParserConfigurationException, IOException, BadlyFormedXMLException, FormatException {
		//GIVEN
		String input = "bibliotheque.xml";
		String format1 = "html";
		String output1 = repertoireTest.getRoot()+"/testHtml";
		String format2 = "pdf";
		String output2 = repertoireTest.getRoot()+"/testPdf";
		
		//WHEN
		transformateurBibli.convertXML(input, output1, format1);
		transformateurBibli.convertXML(input, output2, format2);
		
		//THEN
		assertTrue(new File(output1+"."+format1).exists());
		assertTrue(new File(output2+"."+format2).exists());
		
	}
	
	@Test
	public void testconvertXML_mauvaisFormat()throws SAXException, TransformerException, ParserConfigurationException, IOException, BadlyFormedXMLException, FormatException {
		//GIVEN
		String input = "bibliotheque.xml";
		String format = "formatNonExistant";
		String output = repertoireTest.getRoot()+"/test";
		Throwable exception = null;		
		
		//WHEN
		try {
			transformateurBibli.convertXML(input, output, format);
		}
		catch (Exception e){
			exception = e;
		}
		
		//THEN
		assertEquals(FormatException.class, exception.getClass());
		assertFalse( VerificationFichiers.fichierExiste(output) );
		
	}

}
