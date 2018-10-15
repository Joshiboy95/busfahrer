package de.joshuarosenberger.suffkischte.framework.exceptions;


public class NotEnoughPlayersException extends Exception {

    public NotEnoughPlayersException(){
    }

    public NotEnoughPlayersException(String message) {
        super(message);
    }
}
