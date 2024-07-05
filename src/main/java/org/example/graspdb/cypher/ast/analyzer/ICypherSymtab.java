package org.example.graspdb.cypher.ast.analyzer;

import org.example.graspdb.cypher.ast.INodeIdentifier;
import org.example.graspdb.cypher.ast.IPattern;
import org.example.graspdb.cypher.ast.IRelationIdentifier;
import org.example.graspdb.cypher.ast.IRet;

import java.util.List;

public interface ICypherSymtab {
    List<IPattern> getPatterns();

    void addPattern(IPattern pattern);

    void addNode(INodeIdentifier node);

    void add_aliasDefinitions(IRet iRet);

    void addRelation(IRelationIdentifier relation);

    void removePattern();

    void addPathDef(IPattern pattern);

    void removePathDef();

    void setPatterns(List<IPattern> patterns);
    List<IRet> getAliasDefinitions();
    List<IPathAnalyzer> getPathDefinitions();
    void setAliasDefinition(List<IRet> aliasDefinitions);
    void setpathDefinition(List<IPathAnalyzer> pathDefinitions);
    List<IAliasAnalyzer> getLocalAliasDefs();

    List<IPathAnalyzer> getLocalPathDefs();

    List<IAliasAnalyzer> getAvailableAliasDefs();

    List<IPathAnalyzer> getAvailablePathDefs();

    List<INodeAnalyzer> getLocalNodePatterns();
    List<IRelationAnalyzer> getLocalRelationPatterns();
    List<INodeAnalyzer> getAvailableNodePatterns();
    List<IRelationAnalyzer> getAvailableRelationPatterns();
    List<IIdentifierAnalyzer> getLocalIdentifiers();
    List<IIdentifierAnalyzer> getAvailableIdentifiers();

}
