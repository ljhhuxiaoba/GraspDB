package org.example.graspdb.cypher.ast.analyzer;

import java.util.Map;

public interface IMapDescriptor {
    boolean isMapSizeUnknown();
    Map<String, ICypherTypeDescriptor> getMapMemberTypes();
    boolean isMembersWithSameType();
    ICypherTypeDescriptor getSameMemberType();
}
