package org.example.graspdb.cypher.schema;

import org.example.graspdb.cypher.ast.ICypherType;

import java.util.List;

public interface ILabelInfo extends IPatternElementInfo{
    String getName();
    List<IPropertyInfo> getProperties();
    boolean hasPropertyWithType(ICypherType type);
    List<IPropertyInfo> getPropertiesWithType(ICypherType type);
}
