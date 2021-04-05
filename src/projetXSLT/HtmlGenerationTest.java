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
	public void convertToHtml_succes() throws TransformerConfigurationException, TransformerException, SAXException, IOException, BadlyFormedXMLException, ParserConfigurationException  {
		//GIVEN
		String source = "bibliotheque.xml";
		String feuille = ProjetConstantes.FEUILLEHTML;
		String sortie = repertoireTest.getRoot()+"/test.html";
				
		//WHEN
		HtmlGeneration.convertToHtml(source, feuille, sortie);
		Document sortieParsee = VerificationFichiers.parserXML(sortie);
		
		//THEN
		assertTrue( VerificationFichiers.fichierExiste(sortie) );
		//vérification Contenu : tableau html généré contient bien 7 lignes
		assertEquals( 7, sortieParsee.getElementsByTagName("tr").getLength() );
		//vérification Contenu : vérifier que la première cellule de la première ligne (deuxième si on compte le titre) du tableau contient bien "Harry Potter"
		assertEquals("Harry Potter", sortieParsee.getElementsByTagName("tr").item(1).getFirstChild().getTextContent());
	}
	
	@Test 
	public void convertToHtml_xmlInexistant() {
		//GIVEN
		String source = "fichierNonExistant.xml";
		String feuille = ProjetConstantes.FEUILLEHTML;
		String sortie = repertoireTest.getRoot()+"/test.html";
		Throwable exception = null;	
		
		//WHEN
		try {
			HtmlGeneration.convertToHtml(source, feuille, sortie);
		}
		catch (Exception e){
			exception = e;
		}
		
		//THEN
		assertEquals(FileNotFoundException.class, exception.getClass());
		assertFalse( VerificationFichiers.fichierExiste(sortie) );
		
	}
	
	@Test 
	public void convertToHtml_xslInexistant() {
		//GIVEN
		String source = "bibliotheque.xml";
		String feuille = "feuilleNonExistante.xsl";
		String sortie = repertoireTest.getRoot()+"/test.html";
		Throwable exception = null;
		
		//WHEN
		try {
			HtmlGeneration.convertToHtml(source, feuille, sortie);
		}
		catch (Exception e){
			exception = e;
		}
		
		//THEN
		assertEquals(FileNotFoundException.class, exception.getClass());
		assertFalse( VerificationFichiers.fichierExiste(sortie) );
		
	}
	
	@Test 
	public void convertToHtml_xmlMalForme() throws IOException{
		
		//GIVEN
		File xmlMalForme= repertoireTest.newFile("xmlMalforme.xml");
		FileUtils.writeStringToFile(xmlMalForme, "<?xml version=\"1.0\"?><bibli></bib>");
		String source = repertoireTest.getRoot()+"/xmlMalForme.xml";
		String feuille = ProjetConstantes.FEUILLEHTML;
		String sortie = repertoireTest.getRoot()+"/test.html";
		Throwable exception = null;		
		
		//WHEN
		try {
			HtmlGeneration.convertToHtml(source, feuille, sortie);
		}
		catch (Exception e){
			exception = e;
		}
		
		//THEN
		assertEquals(BadlyFormedXMLException.class, exception.getClass());
		assertFalse( VerificationFichiers.fichierExiste(sortie) );
		
	}
	
	@Test 
	public void convertToHtml_xslMalForme() throws IOException{
		
		//GIVEN
		File xslMalForme= repertoireTest.newFile("xslMalforme.xsl");
		FileUtils.writeStringToFile(xslMalForme, "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsl:stylesheet>");
		String source = "bibliotheque.xml";
		String feuille = repertoireTest.getRoot()+"/xslMalForme.xsl";
		String sortie = repertoireTest.getRoot()+"/test.html";
		Throwable exception = null;		
		
		//WHEN
		try {
			HtmlGeneration.convertToHtml(source, feuille, sortie);
		}
		catch (Exception e){
			exception = e;
		}
		
		//THEN
		assertEquals(BadlyFormedXMLException.class, exception.getClass());
		assertFalse( VerificationFichiers.fichierExiste(sortie) );
		
	}
	
	@Test 
	public void convertToHtml_xslNonFonctionnelle() throws IOException{
		
		//GIVEN
		File xslNonFonc= repertoireTest.newFile("xslNonFonc.xsl");
		FileUtils.writeStringToFile(xslNonFonc, "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsl:style xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" exclude-result-prefixes=\"xs\" version=\"2.0\"></xsl:style>\n");
		String source = "bibliotheque.xml";
		String feuille = repertoireTest.getRoot()+"/xslNonFonc.xsl";
		String sortie = repertoireTest.getRoot()+"/test.html";
		Throwable exception = null;		
		
		//WHEN
		try {
			HtmlGeneration.convertToHtml(source, feuille, sortie);
		}
		catch (Exception e){
			exception = e;
		}
		
		//THEN
		assertEquals(BadlyFormedXSLException.class, exception.getClass());
		assertFalse( VerificationFichiers.fichierExiste(sortie) );
		
	}


}


