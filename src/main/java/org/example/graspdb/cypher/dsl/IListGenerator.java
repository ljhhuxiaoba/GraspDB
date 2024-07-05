package org.example.graspdb.cypher.dsl;

import org.example.graspdb.cypher.ast.analyzer.IUnwindAnalyzer;

public interface IListGenerator {
    void fillUnwindList(IUnwindAnalyzer unwindAnalyzer);
}
