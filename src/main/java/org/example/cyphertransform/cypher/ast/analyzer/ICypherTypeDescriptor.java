package org.example.cyphertransform.cypher.ast.analyzer;

import org.example.cyphertransform.cypher.ast.ICypherType;

public interface ICypherTypeDescriptor {
    ICypherType getType();
    boolean isBasicType();
    boolean isNodeOrRelation();
    boolean isNode();
    boolean isRelation();
    boolean isList();
    boolean isMap();

    boolean isPath();

    IListDescriptor getListDescriptor();
    IMapDescriptor getMapDescriptor();
    INodeAnalyzer getNodeAnalyzer();
    IRelationAnalyzer getRelationAnalyzer();
    IPathAnalyzer getPathAnalyzer();
}
