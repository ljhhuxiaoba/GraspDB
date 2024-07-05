package org.example.graspdb.cypher.ast.analyzer;

import java.util.List;

public interface IListDescriptor {
    boolean isListLengthUnknown();
    List<ICypherTypeDescriptor> getListMemberTypes();
    boolean isMembersWithSameType();
    ICypherTypeDescriptor getSameMemberType();
}
