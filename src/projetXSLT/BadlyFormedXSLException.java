package projetXSLT;

/**
 * BadlyFormedXSLException indique une erreur au niveau d'une feuille de style xsl.
 * La feuille de style est bien formée sur le plan du XML, mais pas du contenu XSL (balises ou attributs obligatoires manquants...)
 */

public class BadlyFormedXSLException extends Exception {

	public BadlyFormedXSLException() {
	}
	
	public BadlyFormedXSLException(String message){  
        super(message);  
    }  
	
	public BadlyFormedXSLException(Throwable cause) {
		super(cause);
	}

	public BadlyFormedXSLException(String message, Throwable cause) {
		super(message, cause);
	}

}
