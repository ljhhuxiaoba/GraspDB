package org.example.graspdb.common.oracle;

import java.io.FileWriter;

public interface TestOracle {
    void check() throws Exception;
    FileWriter getFw();
}
