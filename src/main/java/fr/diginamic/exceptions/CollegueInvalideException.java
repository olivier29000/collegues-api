package fr.diginamic.exceptions;

public class CollegueInvalideException extends RuntimeException {

	/** serialVersionUID : long */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur
	 * 
	 * @param messageErreur
	 */
	public CollegueInvalideException(String messageErreur) {
		super(messageErreur);
	}

}
