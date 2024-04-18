package org.example.graspdb.cypher;

import org.example.graspdb.cypher.ast.ILabel;
import org.example.graspdb.cypher.ast.IType;
import org.example.graspdb.cypher.schema.IFunctionInfo;
import org.example.graspdb.cypher.schema.ILabelInfo;
import org.example.graspdb.cypher.schema.IRelationTypeInfo;

import java.util.List;

public interface ICypherSchema {
    boolean containsLabel(ILabel label);
    ILabelInfo getLabelInfo(ILabel label);
    boolean containsRelationType(IType relation);
    IRelationTypeInfo getRelationInfo(IType relation);
    List<IFunctionInfo> getFunctions();

    List<ILabelInfo> getLabelInfos();
    List<IRelationTypeInfo> getRelationshipTypeInfos();
}
