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
	public void testConvertXML_html()throws SAXException, TransformerException, ParserConfigurationException, IOException, BadlyFormedXMLException, FormatException, BadlyFormedXSLException {
		//GIVEN
		String input =ProjetConstantes.XMLTEST;
		String format = "html";
		String output = repertoireTest.getRoot()+"/testPdf";
		
		//WHEN
		transformateurBibli.convertXML(input, format, output);
		
		//THEN
		assertTrue(VerificationFichiers.fichierExiste(output+"."+format));
		
	}

	@Test
	public void testConvertXML_pdf()throws SAXException, TransformerException, ParserConfigurationException, IOException, BadlyFormedXMLException, FormatException, BadlyFormedXSLException {
		//GIVEN
		String input =ProjetConstantes.XMLTEST;
		String format = "pdf";
		String output = repertoireTest.getRoot()+"/testPdf";
		
		//WHEN
		transformateurBibli.convertXML(input, format, output);
		
		//THEN
		assertTrue(VerificationFichiers.fichierExiste(output+"."+format));
		
	}
	
	@Test
	public void testConvertXML_surcharge()throws SAXException, TransformerException, ParserConfigurationException, IOException, BadlyFormedXMLException, FormatException, BadlyFormedXSLException {
		//GIVEN
		String input =ProjetConstantes.XMLTEST;
		String format = "html";
		
		//WHEN
		transformateurBibli.convertXML(input, format);
		
		//THEN
		String output_suppose = input.substring(0, input.lastIndexOf('.'))+"."+format;
		assertTrue(VerificationFichiers.fichierExiste(output_suppose));
		(new File(output_suppose)).delete();
		
	}
	
	@Test
	public void testConvertXML_mauvaisFormat1() {
		/* Vérifie la non-création d'un fichier de sortie */
		
		//GIVEN
		String input = ProjetConstantes.XMLTEST;
		String format = "formatNonExistant";
		String output = repertoireTest.getRoot()+"/test";
		
		//WHEN
		try {
			transformateurBibli.convertXML(input, format, output);
		}
		catch (Exception e){
		}
		
		//THEN
		assertFalse( VerificationFichiers.fichierExiste(output+"."+format) );
		
	}
	
	@Test (expected = FormatException.class)
	public void testConvertXML_mauvaisFormat2()throws SAXException, TransformerException, ParserConfigurationException, IOException, BadlyFormedXMLException, FormatException, BadlyFormedXSLException {
		/* Vérifie que l'Exception FormatException est bien levée */
		
		//GIVEN
		String input = ProjetConstantes.XMLTEST;
		String format = "formatNonExistant";
		String output = repertoireTest.getRoot()+"/test";
		
		//WHEN
		transformateurBibli.convertXML(input, format, output);
		
	}

}
