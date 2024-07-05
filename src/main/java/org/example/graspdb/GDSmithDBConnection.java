package org.example.graspdb;

public interface GDSmithDBConnection extends AutoCloseable {

    String getDatabaseVersion() throws Exception;
}
