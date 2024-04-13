package org.example.cyphertransform.cypher.ast.analyzer;

import org.example.cyphertransform.cypher.ast.IIdentifier;
import org.example.cyphertransform.cypher.ast.IRemove;
import org.example.cyphertransform.cypher.ast.ISet;

public interface IRemoveAnalyzer extends IRemove, IClauseAnalyzer{
    @Override
    IRemove getSource();
}