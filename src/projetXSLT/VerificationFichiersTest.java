package projetXSLT;

import static org.junit.jupiter.api.Assertions.*;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
 
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import org.w3c.dom.Document;

/**
 * Classe permettant de tester les méthodes de la classe VerificationFichiers
 * @see VerificationFichiers
 */

public class VerificationFichiersTest {
	
	@Rule
	public TemporaryFolder repertoireTest = new TemporaryFolder();
	
	@Test
	public void testFichierExiste() throws IOException {
		//GIVEN
		File fichierTest= repertoireTest.newFile("fichierTest.txt");
		
		//THEN
		assertTrue(VerificationFichiers.fichierExiste(repertoireTest.getRoot()+"/fichierTest.txt"));
		assertFalse(VerificationFichiers.fichierExiste(repertoireTest.getRoot()+"+fauxfichier.txt"));
		
	}
	
	@Test
	public void testParserXML_bienForme() throws IOException, ParserConfigurationException, SAXException {
		//GIVEN
		File fichierTest= repertoireTest.newFile("fichierTest.xml");
		FileUtils.writeStringToFile(fichierTest, "<racine><element></element><element></element></racine>");
		
		//WHEN
		Document doc = VerificationFichiers.parserXML(repertoireTest.getRoot()+"/fichierTest.xml");
				
		//THEN
		assertEquals( 2, doc.getElementsByTagName("element").getLength() );
		
	}
	
	@Test (expected = SAXException.class)
	public void testParserXML_malForme() throws IOException, ParserConfigurationException, SAXException {
		//GIVEN
		File fichierTest= repertoireTest.newFile("fichierTest.xml");
		FileUtils.writeStringToFile(fichierTest, "<racine><element></element></heheJeNeTeFermePasBien>");
		
		//WHEN
		Document doc = VerificationFichiers.parserXML(repertoireTest.getRoot()+"/fichierTest.xml");			
		
	}

}
