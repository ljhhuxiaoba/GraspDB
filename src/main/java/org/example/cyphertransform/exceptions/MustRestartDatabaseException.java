package org.example.cyphertransform.exceptions;

public class MustRestartDatabaseException extends RuntimeException{

    public MustRestartDatabaseException(Exception cause){
        super(cause);
    }
}
