package org.example.graspdb.cypher.standard_ast;

import org.example.graspdb.Randomly;
import org.example.graspdb.cypher.ast.*;
import org.example.graspdb.cypher.dsl.IIdentifierBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RelationIdentifier implements IRelationIdentifier {
    private Boolean LengthUnknown =false;
    public static final int NO_BOUND = -1;

    protected String name;
    protected IType relationType;
    protected List<IProperty> properties;
    protected Direction direction;
    protected int lengthLowerBound, lengthUpperBound;

    public static RelationIdentifier createRelationRefFromAlias(Alias alias) {
        return new RelationIdentifier(alias.getName(), null,
                Direction.BOTH, new ArrayList<>(), 1, 1);
    }
    public IRelationIdentifier setLengthUnknown(Boolean b){
        this.LengthUnknown=b;
        return this;
    }
    public static RelationIdentifier createRelationRef(IRelationIdentifier relationIdentifier, Direction direction,
                                                       int lengthLowerBound, int lengthUpperBound) {
        return new RelationIdentifier(relationIdentifier.getName(), null,
                direction, new ArrayList<>(), lengthLowerBound, lengthUpperBound);
    }
    RelationIdentifier(String name, IType relationType, Direction direction, List<IProperty> properties, int lengthLowerBound, int lengthUpperBound) {
        this.name = name;
        this.relationType = relationType;
        this.direction = direction;
        this.properties = properties;
        this.lengthLowerBound = lengthLowerBound;
        this.lengthUpperBound = lengthUpperBound;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ICypherType getType() {
        return CypherType.RELATION;
    }

    @Override
    public IRelationIdentifier getCopy() {
        RelationIdentifier relation = new RelationIdentifier(name, relationType, direction, new ArrayList<>(),
                lengthLowerBound, lengthUpperBound);
        if (properties != null) {
            relation.properties = properties.stream().map(p -> p.getCopy()).collect(Collectors.toList());
        }
        return relation;
    }

    @Override
    public List<IProperty> getProperties() {
        return properties;
    }

    @Override
    public List<IType> getTypes() {
        List<IType> result = new ArrayList<IType>();
        result.add(relationType);
        return result;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public IRelationIdentifier createRef() {
        return new RelationIdentifier(name, null, direction, null, 1, 1);
    }

    @Override
    public int getLengthLowerBound() {
        return lengthLowerBound;
    }

    @Override
    public int getLengthUpperBound() {
        return lengthUpperBound;
    }

    @Override
    public void setProperties(List<IProperty> properties) {
        this.properties = properties;
    }

    @Override
    public void toTextRepresentation(StringBuilder sb) {
        switch (direction) {
            case RIGHT:
            case BOTH:
                sb.append("-[");
                break;
            case LEFT:
                sb.append("<-[");
                break;
        }
        if (name != null) {
                sb.append(name);
        }
        else if(LengthUnknown)
            sb.append(Randomly.fromList(Arrays.asList("*0..0","*0..1","*0..","*..2")));
        if (relationType != null && relationType.getName() != null && relationType.getName().length() != 0) {
            sb.append(" :").append(relationType.getName());
        }
        if (properties != null && properties.size() != 0) {
            sb.append("{");
            for (int i = 0; i < properties.size(); i++) {
                properties.get(i).toTextRepresentation(sb);
                if (i != properties.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("}");
        }
        switch (direction) {
            case LEFT:
            case BOTH:
                sb.append("]-");
                break;
            case RIGHT:
                sb.append("]->");
                break;
        }

    }

    @Override
    public boolean isAnonymous() {
        return getName() == null || getName().length() == 0;
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RelationIdentifier)) {
            return false;
        }
        if(getName()==null){
            return false;
        }
        if (getName().equals(((RelationIdentifier) o).getName())) {
            return true;
        }
        return false;
    }

    public static class RelationBuilder {
        private Boolean LengthUnknown=false;
        private String curRelationName = "";
        private IType curRelationType = null;
        private Direction curDirection = Direction.BOTH;
        private int lengthLowerBound = 1, lengthUpperBound = 1;
        private List<IProperty> curRelationProperties;
        public void setLengthUnknown(Boolean b){
            this.LengthUnknown=b;
        }
        static RelationBuilder newRelationBuilder(String name) {
            return new RelationBuilder(name);
        }

        public static RelationBuilder newRelationBuilder(IIdentifierBuilder identifierBuilder) {
            return new RelationBuilder(identifierBuilder.getNewRelationName());
        }

        private RelationBuilder(String name) {
            curRelationName = name;
            curRelationProperties = new ArrayList<>();
        }

        public RelationBuilder withType(IType relationType) {
            curRelationType = relationType;
            return this;
        }

        public RelationBuilder withDirection(Direction direction) {
            curDirection = direction;
            return this;
        }

        public RelationBuilder withProperties(IProperty... properties) {
            curRelationProperties.addAll(Arrays.asList(properties));
            curRelationProperties = curRelationProperties.stream().distinct().collect(Collectors.toList());
            return this;
        }

        public RelationBuilder withOnlyLengthUpperBound(int lengthUpperBound) {
            this.lengthUpperBound = lengthUpperBound;
            this.lengthLowerBound = NO_BOUND;
            return this;
        }

        public RelationBuilder withOnlyLengthLowerBound(int lengthLowerBound) {
            this.lengthLowerBound = lengthLowerBound;
            this.lengthUpperBound = NO_BOUND;
            return this;
        }

        public RelationBuilder withLength(int length) {
            this.lengthUpperBound = length;
            this.lengthLowerBound = length;
            return this;
        }

        public RelationBuilder withLengthUnbounded() {
            this.lengthLowerBound = NO_BOUND;
            this.lengthUpperBound = NO_BOUND;
            return this;
        }

        public IRelationIdentifier build() {
            return new RelationIdentifier(curRelationName, curRelationType, curDirection,
                    curRelationProperties, lengthLowerBound, lengthUpperBound).setLengthUnknown(LengthUnknown);
        }
    }
}
