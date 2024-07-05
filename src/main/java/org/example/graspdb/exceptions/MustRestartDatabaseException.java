package org.example.graspdb.exceptions;

public class MustRestartDatabaseException extends RuntimeException{

    public MustRestartDatabaseException(Exception cause){
        super(cause);
    }
}
