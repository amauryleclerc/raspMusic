package fr.aleclerc.rasp.music.storage.exception;

import java.io.IOException;

public class StorageException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8092918573392295954L;
	
	public StorageException(String msg) {
		super(msg);
	}

	public StorageException(IOException e) {
		super(e);
	}

	public StorageException(Exception e) {
		super(e);
	}
}
