package org.example.cyphertransform.cypher.dsl;

import org.example.cyphertransform.cypher.ast.analyzer.IUnwindAnalyzer;

public interface IListGenerator {
    void fillUnwindList(IUnwindAnalyzer unwindAnalyzer);
}
