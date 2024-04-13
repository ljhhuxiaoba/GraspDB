package org.example.cyphertransform;

public interface GDSmithDBConnection extends AutoCloseable {

    String getDatabaseVersion() throws Exception;
}
