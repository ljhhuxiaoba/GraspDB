package org.example.graspdb.cypher.ast.analyzer;

import org.example.graspdb.cypher.ICypherSchema;
import org.example.graspdb.cypher.ast.ICypherType;
import org.example.graspdb.cypher.ast.IProperty;
import org.example.graspdb.cypher.ast.IRelationIdentifier;
import org.example.graspdb.cypher.ast.IType;
import org.example.graspdb.cypher.schema.IPropertyInfo;

import java.util.List;

public interface IRelationAnalyzer extends IRelationIdentifier, IIdentifierAnalyzer {
    @Override
    IRelationIdentifier getSource();
    @Override
    IRelationAnalyzer getFormerDef();
    void setFormerDef(IRelationAnalyzer formerDef);

    /**
     * 从该处定义回溯，所有对该节点的定义中出现的RelationType
     * @return
     */
    List<IType> getAllRelationTypesInDefChain();

    /**
     * 从该处回溯，所有对该节点的定义中出现的property
     * @return
     */
    List<IProperty> getAllPropertiesInDefChain();
    List<IPropertyInfo> getAllPropertiesAvailable(ICypherSchema schema);
    List<IPropertyInfo> getAllPropertiesWithType(ICypherSchema schema, ICypherType type);

    boolean isSingleRelation();
}
