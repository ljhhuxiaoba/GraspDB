package org.example.cyphertransform.cypher;

import org.example.cyphertransform.cypher.ast.ILabel;
import org.example.cyphertransform.cypher.ast.IType;
import org.example.cyphertransform.cypher.schema.IFunctionInfo;
import org.example.cyphertransform.cypher.schema.ILabelInfo;
import org.example.cyphertransform.cypher.schema.IRelationTypeInfo;

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
