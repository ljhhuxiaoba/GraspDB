package org.example.graspdb.cypher.ast;

import java.util.List;

public interface IPattern extends ITextRepresentation, ICopyable{
    List<IPatternElement> getPatternElements();

    void setPatternElements(List<IPatternElement> elements);
    void setPathIdentifier(IPathIdentifier pathIdentifier);
    IPathIdentifier getPathIdentifier();
    @Override
    IPattern getCopy();
}
