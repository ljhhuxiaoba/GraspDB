package org.example.graspdb.cypher.standard_ast;

import org.example.graspdb.cypher.ast.*;

public class PathIdentifier implements IPathIdentifier {
    protected String name;
    protected IPattern pattern;

    public static PathIdentifier createIdentifierRef(IIdentifier path){
        return new PathIdentifier(path.getName(), null);
    }
    public PathIdentifier(String name, IPattern pattern){
        this.name = name;
        this.pattern = pattern;
    }
    public PathIdentifier(String name){
        this.name = name;
        this.pattern = null;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public IPattern getPattern() {
        return pattern;
    }

    @Override
    public void setPattern(IPattern pattern) {
        this.pattern=pattern;
    }

    @Override
    public ICypherType getType() {
        return CypherType.PATH;
    }

    @Override
    public IPathIdentifier getCopy() {
        PathIdentifier path;
        if(pattern != null){
            path = new PathIdentifier(name, pattern.getCopy());
        }
        else {
            path = new PathIdentifier(name, null);
        }
        return path;
    }


    @Override
    public void toTextRepresentation(StringBuilder sb) {
//        if(expression != null){
//            expression.toTextRepresentation(sb);
//            sb.append(" AS ");
//        }
        sb.append(name+"=");
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Alias)){
            return false;
        }
        if(getName().equals(((Alias)o).getName())){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return getName().hashCode();
    }

}
