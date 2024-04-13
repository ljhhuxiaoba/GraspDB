package org.example.cyphertransform.cypher.schema;

import org.example.cyphertransform.cypher.ast.ICypherType;

import java.util.List;

public interface ILabelInfo extends IPatternElementInfo{
    String getName();
    List<IPropertyInfo> getProperties();
    boolean hasPropertyWithType(ICypherType type);
    List<IPropertyInfo> getPropertiesWithType(ICypherType type);
}
