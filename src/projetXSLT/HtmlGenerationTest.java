package projetXSLT;

import static org.junit.jupiter.api.Assertions.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
 
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import org.w3c.dom.Document;

/**
 * Classe permettant de tester les méthodes de la classe HtmlGeneration
 * @see HtmlGeneration
 */
public class HtmlGenerationTest {
	
	@Rule
	public TemporaryFolder repertoireTest = new TemporaryFolder();
	
	@Test
	public void testConvertToHtml_succes() throws TransformerConfigurationException, TransformerException, SAXException, IOException, BadlyFormedXMLException, ParserConfigurationException, BadlyFormedXSLException  {
		//GIVEN
		String source = ProjetConstantes.XMLTEST;
		String feuille = ProjetConstantes.FEUILLEHTML;
		String sortie = repertoireTest.getRoot()+"/test.html";
				
		//WHEN
		HtmlGeneration.convertToHtml(source, feuille, sortie);
		
		//THEN
		assertTrue( VerificationFichiers.fichierExiste(sortie) );
		Document sortieParsee = VerificationFichiers.parserXML(sortie);
		//vérification Contenu : tableau html généré contient bien 7 lignes
		assertEquals( 7, sortieParsee.getElementsByTagName("tr").getLength() );
		//vérification Contenu : vérifier que la première cellule de la première ligne (deuxième si on compte le titre) du tableau contient bien "Harry Potter"
		assertEquals("Harry Potter", sortieParsee.getElementsByTagName("tr").item(1).getFirstChild().getTextContent());
	}
	
	@Test 
	public void testConvertToHtml_xmlInexistant1() {
		/* Vérifie la non-création d'un fichier de sortie */
		
		//GIVEN
		String source = "fichierNonExistant.xml";
		String feuille = ProjetConstantes.FEUILLEHTML;
		String sortie = repertoireTest.getRoot()+"/test.html";
		
		//WHEN
		try {
			HtmlGeneration.convertToHtml(source, feuille, sortie);
		}
		catch (Exception e){
		}
		
		//THEN
		assertFalse( VerificationFichiers.fichierExiste(sortie) );
		
	}
	
	@Test (expected = FileNotFoundException.class)
	public void testConvertToHtml_xmlInexistant2() throws TransformerConfigurationException, TransformerException, SAXException, IOException, BadlyFormedXMLException, ParserConfigurationException, BadlyFormedXSLException {
		/* Vérifie que l'Exception FileNotFoundException est bien levée */
		
		//GIVEN
		String source = "fichierNonExistant.xml";
		String feuille = ProjetConstantes.FEUILLEHTML;
		String sortie = repertoireTest.getRoot()+"/test.html";
		
		//WHEN
		HtmlGeneration.convertToHtml(source, feuille, sortie);
		
	}
	
	@Test 
	public void testConvertToHtml_xslInexistant1() {
		/* Vérifie la non-création d'un fichier de sortie */
		
		//GIVEN
		String source = ProjetConstantes.XMLTEST;
		String feuille = "feuilleNonExistante.xsl";
		String sortie = repertoireTest.getRoot()+"/test.html";
		
		//WHEN
		try {
			HtmlGeneration.convertToHtml(source, feuille, sortie);
		}
		catch (Exception e){
		}
		
		//THEN
		assertFalse( VerificationFichiers.fichierExiste(sortie) );
		
	}
	
	@Test (expected = FileNotFoundException.class)
	public void testConvertToHtml_xslInexistant2() throws TransformerConfigurationException, TransformerException, SAXException, IOException, BadlyFormedXMLException, ParserConfigurationException, BadlyFormedXSLException {
		/* Vérifie que l'Exception FileNotFoundException est bien levée */
		
		//GIVEN
		String source = ProjetConstantes.XMLTEST;
		String feuille = "feuilleNonExistante.xsl";
		String sortie = repertoireTest.getRoot()+"/test.html";
		
		//WHEN
		HtmlGeneration.convertToHtml(source, feuille, sortie);
		
	}
	
	@Test 
	public void testConvertToHtml_xmlMalForme1() throws IOException{
		/* Vérifie la non-création d'un fichier de sortie */
		
		//GIVEN
		File xmlMalForme= repertoireTest.newFile("xmlMalforme.xml");
		FileUtils.writeStringToFile(xmlMalForme, ProjetConstantes.XMLBADFORM);
		String source = repertoireTest.getRoot()+"/xmlMalForme.xml";
		String feuille = ProjetConstantes.FEUILLEHTML;
		String sortie = repertoireTest.getRoot()+"/test.html";	
		
		//WHEN
		try {
			HtmlGeneration.convertToHtml(source, feuille, sortie);
		}
		catch (Exception e){
		}
		
		//THEN
		assertFalse( VerificationFichiers.fichierExiste(sortie) );
		
	}
	
	@Test (expected = BadlyFormedXMLException.class)
	public void testConvertToHtml_xmlMalForme2() throws IOException, TransformerConfigurationException, TransformerException, SAXException, BadlyFormedXMLException, ParserConfigurationException, BadlyFormedXSLException{
		/* Vérifie que l'Exception BadlyFormedXMLException est bien levée */
		
		//GIVEN
		File xmlMalForme= repertoireTest.newFile("xmlMalforme.xml");
		FileUtils.writeStringToFile(xmlMalForme, ProjetConstantes.XMLBADFORM);
		String source = repertoireTest.getRoot()+"/xmlMalForme.xml";
		String feuille = ProjetConstantes.FEUILLEHTML;
		String sortie = repertoireTest.getRoot()+"/test.html";
		
		//WHEN
		HtmlGeneration.convertToHtml(source, feuille, sortie);
		
	}
	
	@Test 
	public void convertToHtml_xslMalForme1() throws IOException{
		/* Vérifie la non-création d'un fichier de sortie */
		
		//GIVEN
		File xslMalForme= repertoireTest.newFile("xslMalforme.xsl");
		FileUtils.writeStringToFile(xslMalForme,  ProjetConstantes.XSLBADFORM);
		String source = ProjetConstantes.XMLTEST;
		String feuille = repertoireTest.getRoot()+"/xslMalForme.xsl";
		String sortie = repertoireTest.getRoot()+"/test.html";
		
		//WHEN
		try {
			HtmlGeneration.convertToHtml(source, feuille, sortie);
		}
		catch (Exception e){
		}
		
		//THEN
		assertFalse( VerificationFichiers.fichierExiste(sortie) );
		
	}
	
	@Test (expected = BadlyFormedXMLException.class)
	public void convertToHtml_xslMalForme2() throws IOException, TransformerConfigurationException, TransformerException, SAXException, BadlyFormedXMLException, ParserConfigurationException, BadlyFormedXSLException{
		/* Vérifie que l'Exception BadlyFormedXMLException est bien levée */
		
		//GIVEN
		File xslMalForme= repertoireTest.newFile("xslMalforme.xsl");
		FileUtils.writeStringToFile(xslMalForme, ProjetConstantes.XSLBADFORM);
		String source = ProjetConstantes.XMLTEST;
		String feuille = repertoireTest.getRoot()+"/xslMalForme.xsl";
		String sortie = repertoireTest.getRoot()+"/test.html";
		
		//WHEN
		HtmlGeneration.convertToHtml(source, feuille, sortie);
	}
	
	@Test 
	public void testConvertToHtml_xslNonFonctionnelle1() throws IOException{
		/* Vérifie la non-création d'un fichier de sortie */
		
		//GIVEN
		File xslNonFonc= repertoireTest.newFile("xslNonFonc.xsl");
		FileUtils.writeStringToFile(xslNonFonc, ProjetConstantes.XSLNONFONC);
		String source = ProjetConstantes.XMLTEST;
		String feuille = repertoireTest.getRoot()+"/xslNonFonc.xsl";
		String sortie = repertoireTest.getRoot()+"/test.html";	
		
		//WHEN
		try {
			HtmlGeneration.convertToHtml(source, feuille, sortie);
		}
		catch (Exception e){
		}
		
		//THEN
		assertFalse( VerificationFichiers.fichierExiste(sortie) );
		
	}
	
	@Test (expected = BadlyFormedXSLException.class)
	public void testConvertToHtml_xslNonFonctionnelle2() throws IOException, TransformerConfigurationException, TransformerException, SAXException, BadlyFormedXMLException, ParserConfigurationException, BadlyFormedXSLException{
		/* Vérifie que l'Exception BadlyFormedXSLException est bien levée */
		
		//GIVEN
		File xslNonFonc= repertoireTest.newFile("xslNonFonc.xsl");
		FileUtils.writeStringToFile(xslNonFonc, ProjetConstantes.XSLNONFONC);
		String source = ProjetConstantes.XMLTEST;
		String feuille = repertoireTest.getRoot()+"/xslNonFonc.xsl";
		String sortie = repertoireTest.getRoot()+"/test.html";
		
		//WHEN
		HtmlGeneration.convertToHtml(source, feuille, sortie);
		
	}


}


