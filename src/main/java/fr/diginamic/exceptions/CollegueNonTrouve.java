package fr.diginamic.exceptions;

public class CollegueNonTrouve extends RuntimeException {

	/** serialVersionUID : long */
	private static final long serialVersionUID = 1L;

	public CollegueNonTrouve(String messageErreur) {
		super(messageErreur);
	}

}
