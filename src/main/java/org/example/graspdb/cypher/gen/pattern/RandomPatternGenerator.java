package org.example.graspdb.cypher.gen.pattern;

import org.example.graspdb.Randomly;
import org.example.graspdb.cypher.ast.Direction;
import org.example.graspdb.cypher.ast.ILabel;
import org.example.graspdb.cypher.ast.IPattern;
import org.example.graspdb.cypher.ast.IType;
import org.example.graspdb.cypher.ast.analyzer.ICreateAnalyzer;
import org.example.graspdb.cypher.ast.analyzer.IMatchAnalyzer;
import org.example.graspdb.cypher.ast.analyzer.IMergeAnalyzer;
import org.example.graspdb.cypher.ast.analyzer.INodeAnalyzer;
import org.example.graspdb.cypher.schema.CypherSchema;
import org.example.graspdb.cypher.standard_ast.Label;
import org.example.graspdb.cypher.standard_ast.Pattern;
import org.example.graspdb.cypher.standard_ast.RelationType;
import org.example.graspdb.cypher.dsl.BasicPatternGenerator;
import org.example.graspdb.cypher.dsl.IIdentifierBuilder;

import java.util.ArrayList;
import java.util.List;

public class RandomPatternGenerator<S extends CypherSchema<?,?>> extends BasicPatternGenerator<S> {

    private boolean overrideOld;
    public RandomPatternGenerator(S schema, IIdentifierBuilder identifierBuilder, boolean overrideOld) {
        super(schema, identifierBuilder);
        this.overrideOld = overrideOld;
    }


    public IPattern generateSinglePattern(IMatchAnalyzer matchClause, IIdentifierBuilder identifierBuilder, S schema){

        Randomly r = new Randomly();

        int sizeOfLabels = schema.getLabels().size();
        int sizeOfTypes = schema.getRelationTypes().size();
        int lenOfPattern = Randomly.fromOptions(1, 3);

        if (lenOfPattern == 1) {
            boolean isNew = Randomly.getBoolean();
            if (isNew) {
                boolean withLabel = Randomly.getBoolean();
                //boolean isNamed = Randomly.getBoolean();
                boolean isNamed = !Randomly.getBooleanWithSmallProbability();
                if (withLabel) {
                    CypherSchema.CypherLabelInfo labelInfo = schema.getLabels().get(r.getInteger(0, sizeOfLabels));
                    ILabel label = new Label(labelInfo.getName());
                    if (isNamed) {
                        return new Pattern.PatternBuilder(identifierBuilder).newNamedNode().withLabels(label).build();
                    } else {
                        return new Pattern.PatternBuilder(identifierBuilder).newAnonymousNode().withLabels(label).build();
                    }
                } else {
                    if (isNamed) {
                        return new Pattern.PatternBuilder(identifierBuilder).newNamedNode().build();
                    } else {
                        return new Pattern.PatternBuilder(identifierBuilder).newAnonymousNode().build();
                    }
                }
            } else {
                List<INodeAnalyzer> idNode = matchClause.getExtendableNodeIdentifiers();
                if (idNode.size() == 0) {
                    return new Pattern.PatternBuilder(identifierBuilder).newNamedNode().build();
                } else {
                    INodeAnalyzer node = idNode.get(r.getInteger(0, idNode.size()));
                    return new Pattern.PatternBuilder(identifierBuilder).newRefDefinedNode(node).build();
                }
            }
        } else {
            Pattern.PatternBuilder.OngoingNode leftNode;
            boolean isNewLeft = Randomly.getBoolean();
            if (isNewLeft) {
                boolean withLabelLeft = Randomly.getBoolean();
                //boolean isNamedLeft = Randomly.getBoolean();
                boolean isNamedLeft = !Randomly.getBooleanWithSmallProbability();
                if (withLabelLeft) {
                    CypherSchema.CypherLabelInfo labelInfo = schema.getLabels().get(r.getInteger(0, sizeOfLabels));
                    ILabel label = new Label(labelInfo.getName());
                    if (isNamedLeft) {
                        leftNode = new Pattern.PatternBuilder(identifierBuilder).newNamedNode().withLabels(label);
                    } else {
                        leftNode = new Pattern.PatternBuilder(identifierBuilder).newAnonymousNode().withLabels(label);
                    }
                } else {
                    if (isNamedLeft) {
                        leftNode = new Pattern.PatternBuilder(identifierBuilder).newNamedNode();
                    } else {
                        leftNode = new Pattern.PatternBuilder(identifierBuilder).newAnonymousNode();
                    }
                }
            } else {
                List<INodeAnalyzer> idNode = matchClause.getExtendableNodeIdentifiers();
                if (idNode.size() == 0) {
                    leftNode = new Pattern.PatternBuilder(identifierBuilder).newNamedNode();
                } else {
                    INodeAnalyzer node = idNode.get(r.getInteger(0, idNode.size()));
                    leftNode = new Pattern.PatternBuilder(identifierBuilder).newRefDefinedNode(node);
                }
            }

            Pattern.PatternBuilder.OngoingRelation relation;
            boolean withType = Randomly.getBoolean();
            //boolean isNamed = Randomly.getBoolean();
            boolean isNamed = !Randomly.getBooleanWithSmallProbability();
            Direction direction = Randomly.fromOptions(Direction.LEFT, Direction.RIGHT, Direction.BOTH);
            int typeOfLength = r.getInteger(0, 4);
            if (withType) {
                CypherSchema.CypherRelationTypeInfo typeInfo = schema.getRelationTypes().get(r.getInteger(0, sizeOfTypes));
                IType type = new RelationType(typeInfo.getName());
                if (isNamed) {
                    if (typeOfLength == 0) {
                        relation = leftNode.newNamedRelation().withType(type).withDirection(direction).withLengthUnbounded();
                    } else if (typeOfLength == 1) {
                        relation = leftNode.newNamedRelation().withType(type).withDirection(direction).withOnlyLengthLowerBound(r.getInteger(1, 4));
                    } else if (typeOfLength == 2) {
                        relation = leftNode.newNamedRelation().withType(type).withDirection(direction).withOnlyLengthUpperBound(r.getInteger(1, 4));
                    } else {
                        relation = leftNode.newNamedRelation().withType(type).withDirection(direction).withLength(r.getInteger(1, 4));
                    }
                } else {
                    if (typeOfLength == 0) {
                        relation = leftNode.newAnonymousRelation().withType(type).withDirection(direction).withLengthUnbounded();
                    } else if (typeOfLength == 1) {
                        relation = leftNode.newAnonymousRelation().withType(type).withDirection(direction).withOnlyLengthLowerBound(r.getInteger(1, 4));
                    } else if (typeOfLength == 2) {
                        relation = leftNode.newAnonymousRelation().withType(type).withDirection(direction).withOnlyLengthUpperBound(r.getInteger(1, 4));
                    } else {
                        relation = leftNode.newAnonymousRelation().withType(type).withDirection(direction).withLength(r.getInteger(1, 4));
                    }
                }
            } else {
                if (isNamed) {
                    if (typeOfLength == 0) {
                        relation = leftNode.newNamedRelation().withDirection(direction).withLengthUnbounded();
                    } else if (typeOfLength == 1) {
                        relation = leftNode.newNamedRelation().withDirection(direction).withOnlyLengthLowerBound(r.getInteger(1, 4));
                    } else if (typeOfLength == 2) {
                        relation = leftNode.newNamedRelation().withDirection(direction).withOnlyLengthUpperBound(r.getInteger(1, 4));
                    } else {
                        relation = leftNode.newNamedRelation().withDirection(direction).withLength(r.getInteger(1, 4));
                    }
                } else {
                    if (typeOfLength == 0) {
                        relation = leftNode.newAnonymousRelation().withDirection(direction).withLengthUnbounded();
                    } else if (typeOfLength == 1) {
                        relation = leftNode.newAnonymousRelation().withDirection(direction).withOnlyLengthLowerBound(r.getInteger(1, 4));
                    } else if (typeOfLength == 2) {
                        relation = leftNode.newAnonymousRelation().withDirection(direction).withOnlyLengthUpperBound(r.getInteger(1, 4));
                    } else {
                        relation = leftNode.newAnonymousRelation().withDirection(direction).withLength(r.getInteger(1, 4));
                    }
                }
            }

            Pattern.PatternBuilder.OngoingNode rightNode;
            boolean isNewRight = Randomly.getBoolean();
            if (isNewRight) {
                boolean withLabelRight = Randomly.getBoolean();
                //boolean isNamedRight = Randomly.getBoolean();
                boolean isNamedRight = !Randomly.getBooleanWithSmallProbability();
                if (withLabelRight) {
                    CypherSchema.CypherLabelInfo labelInfo = schema.getLabels().get(r.getInteger(0, sizeOfLabels));
                    ILabel label = new Label(labelInfo.getName());
                    if (isNamedRight) {
                        rightNode = relation.newNamedNode().withLabels(label);
                    } else {
                        rightNode = relation.newAnonymousNode().withLabels(label);
                    }
                } else {
                    if (isNamedRight) {
                        rightNode = relation.newNamedNode();
                    } else {
                        rightNode = relation.newAnonymousNode();
                    }
                }
            } else {
                List<INodeAnalyzer> idNode = matchClause.getExtendableNodeIdentifiers();
                if (idNode.size() == 0) {
                    rightNode = new Pattern.PatternBuilder(identifierBuilder).newNamedNode();
                } else {
                    INodeAnalyzer node = idNode.get(r.getInteger(0, idNode.size()));
                    rightNode = new Pattern.PatternBuilder(identifierBuilder).newRefDefinedNode(node);
                }
            }
            return rightNode.build();
        }
    }

    public IPattern generateSinglePattern(ICreateAnalyzer createClause, IIdentifierBuilder identifierBuilder, S schema){

        Randomly r = new Randomly();

        int sizeOfLabels = schema.getLabels().size();
        int sizeOfTypes = schema.getRelationTypes().size();
        int lenOfPattern = Randomly.fromOptions(1, 3);

        if (lenOfPattern == 1) {
            boolean isNew = Randomly.getBoolean();
            if (isNew) {
                boolean withLabel = Randomly.getBoolean();
                //boolean isNamed = Randomly.getBoolean();
                boolean isNamed = !Randomly.getBooleanWithSmallProbability();
                if (withLabel) {
                    CypherSchema.CypherLabelInfo labelInfo = schema.getLabels().get(r.getInteger(0, sizeOfLabels));
                    ILabel label = new Label(labelInfo.getName());
                    if (isNamed) {
                        return new Pattern.PatternBuilder(identifierBuilder).newNamedNode().withLabels(label).build();
                    } else {
                        return new Pattern.PatternBuilder(identifierBuilder).newAnonymousNode().withLabels(label).build();
                    }
                } else {
                    if (isNamed) {
                        return new Pattern.PatternBuilder(identifierBuilder).newNamedNode().build();
                    } else {
                        return new Pattern.PatternBuilder(identifierBuilder).newAnonymousNode().build();
                    }
                }
            } else {
                List<INodeAnalyzer> idNode = createClause.getExtendableNodeIdentifiers();
                if (idNode.size() == 0) {
                    return new Pattern.PatternBuilder(identifierBuilder).newNamedNode().build();
                } else {
                    INodeAnalyzer node = idNode.get(r.getInteger(0, idNode.size()));
                    return new Pattern.PatternBuilder(identifierBuilder).newRefDefinedNode(node).build();
                }
            }
        } else {
            Pattern.PatternBuilder.OngoingNode leftNode;
            boolean isNewLeft = Randomly.getBoolean();
            if (isNewLeft) {
                boolean withLabelLeft = Randomly.getBoolean();
                //boolean isNamedLeft = Randomly.getBoolean();
                boolean isNamedLeft = !Randomly.getBooleanWithSmallProbability();
                if (withLabelLeft) {
                    CypherSchema.CypherLabelInfo labelInfo = schema.getLabels().get(r.getInteger(0, sizeOfLabels));
                    ILabel label = new Label(labelInfo.getName());
                    if (isNamedLeft) {
                        leftNode = new Pattern.PatternBuilder(identifierBuilder).newNamedNode().withLabels(label);
                    } else {
                        leftNode = new Pattern.PatternBuilder(identifierBuilder).newAnonymousNode().withLabels(label);
                    }
                } else {
                    if (isNamedLeft) {
                        leftNode = new Pattern.PatternBuilder(identifierBuilder).newNamedNode();
                    } else {
                        leftNode = new Pattern.PatternBuilder(identifierBuilder).newAnonymousNode();
                    }
                }
            } else {
                List<INodeAnalyzer> idNode = createClause.getExtendableNodeIdentifiers();
                if (idNode.size() == 0) {
                    leftNode = new Pattern.PatternBuilder(identifierBuilder).newNamedNode();
                } else {
                    INodeAnalyzer node = idNode.get(r.getInteger(0, idNode.size()));
                    leftNode = new Pattern.PatternBuilder(identifierBuilder).newRefDefinedNode(node);
                }
            }

            Pattern.PatternBuilder.OngoingRelation relation;
            boolean withType = Randomly.getBoolean();
            //boolean isNamed = Randomly.getBoolean();
            boolean isNamed = !Randomly.getBooleanWithSmallProbability();
            Direction direction = Randomly.fromOptions(Direction.LEFT, Direction.RIGHT, Direction.BOTH);
            int typeOfLength = r.getInteger(0, 4);
            if (withType) {
                CypherSchema.CypherRelationTypeInfo typeInfo = schema.getRelationTypes().get(r.getInteger(0, sizeOfTypes));
                IType type = new RelationType(typeInfo.getName());
                if (isNamed) {
                    if (typeOfLength == 0) {
                        relation = leftNode.newNamedRelation().withType(type).withDirection(direction).withLengthUnbounded();
                    } else if (typeOfLength == 1) {
                        relation = leftNode.newNamedRelation().withType(type).withDirection(direction).withOnlyLengthLowerBound(r.getInteger(1, 4));
                    } else if (typeOfLength == 2) {
                        relation = leftNode.newNamedRelation().withType(type).withDirection(direction).withOnlyLengthUpperBound(r.getInteger(1, 4));
                    } else {
                        relation = leftNode.newNamedRelation().withType(type).withDirection(direction).withLength(r.getInteger(1, 4));
                    }
                } else {
                    if (typeOfLength == 0) {
                        relation = leftNode.newAnonymousRelation().withType(type).withDirection(direction).withLengthUnbounded();
                    } else if (typeOfLength == 1) {
                        relation = leftNode.newAnonymousRelation().withType(type).withDirection(direction).withOnlyLengthLowerBound(r.getInteger(1, 4));
                    } else if (typeOfLength == 2) {
                        relation = leftNode.newAnonymousRelation().withType(type).withDirection(direction).withOnlyLengthUpperBound(r.getInteger(1, 4));
                    } else {
                        relation = leftNode.newAnonymousRelation().withType(type).withDirection(direction).withLength(r.getInteger(1, 4));
                    }
                }
            } else {
                if (isNamed) {
                    if (typeOfLength == 0) {
                        relation = leftNode.newNamedRelation().withDirection(direction).withLengthUnbounded();
                    } else if (typeOfLength == 1) {
                        relation = leftNode.newNamedRelation().withDirection(direction).withOnlyLengthLowerBound(r.getInteger(1, 4));
                    } else if (typeOfLength == 2) {
                        relation = leftNode.newNamedRelation().withDirection(direction).withOnlyLengthUpperBound(r.getInteger(1, 4));
                    } else {
                        relation = leftNode.newNamedRelation().withDirection(direction).withLength(r.getInteger(1, 4));
                    }
                } else {
                    if (typeOfLength == 0) {
                        relation = leftNode.newAnonymousRelation().withDirection(direction).withLengthUnbounded();
                    } else if (typeOfLength == 1) {
                        relation = leftNode.newAnonymousRelation().withDirection(direction).withOnlyLengthLowerBound(r.getInteger(1, 4));
                    } else if (typeOfLength == 2) {
                        relation = leftNode.newAnonymousRelation().withDirection(direction).withOnlyLengthUpperBound(r.getInteger(1, 4));
                    } else {
                        relation = leftNode.newAnonymousRelation().withDirection(direction).withLength(r.getInteger(1, 4));
                    }
                }
            }

            Pattern.PatternBuilder.OngoingNode rightNode;
            boolean isNewRight = Randomly.getBoolean();
            if (isNewRight) {
                boolean withLabelRight = Randomly.getBoolean();
                //boolean isNamedRight = Randomly.getBoolean();
                boolean isNamedRight = !Randomly.getBooleanWithSmallProbability();
                if (withLabelRight) {
                    CypherSchema.CypherLabelInfo labelInfo = schema.getLabels().get(r.getInteger(0, sizeOfLabels));
                    ILabel label = new Label(labelInfo.getName());
                    if (isNamedRight) {
                        rightNode = relation.newNamedNode().withLabels(label);
                    } else {
                        rightNode = relation.newAnonymousNode().withLabels(label);
                    }
                } else {
                    if (isNamedRight) {
                        rightNode = relation.newNamedNode();
                    } else {
                        rightNode = relation.newAnonymousNode();
                    }
                }
            } else {
                List<INodeAnalyzer> idNode = createClause.getExtendableNodeIdentifiers();
                if (idNode.size() == 0) {
                    rightNode = new Pattern.PatternBuilder(identifierBuilder).newNamedNode();
                } else {
                    INodeAnalyzer node = idNode.get(r.getInteger(0, idNode.size()));
                    rightNode = new Pattern.PatternBuilder(identifierBuilder).newRefDefinedNode(node);
                }
            }
            return rightNode.build();
        }
    }

    public IPattern generateSinglePattern(IMergeAnalyzer mergeClause, IIdentifierBuilder identifierBuilder, S schema){

        Randomly r = new Randomly();

        int sizeOfLabels = schema.getLabels().size();
        int sizeOfTypes = schema.getRelationTypes().size();
        int lenOfPattern = Randomly.fromOptions(1, 3);

        if (lenOfPattern == 1) {
            boolean isNew = Randomly.getBoolean();
            if (isNew) {
                boolean withLabel = Randomly.getBoolean();
                //boolean isNamed = Randomly.getBoolean();
                boolean isNamed = !Randomly.getBooleanWithSmallProbability();
                if (withLabel) {
                    CypherSchema.CypherLabelInfo labelInfo = schema.getLabels().get(r.getInteger(0, sizeOfLabels));
                    ILabel label = new Label(labelInfo.getName());
                    if (isNamed) {
                        return new Pattern.PatternBuilder(identifierBuilder).newNamedNode().withLabels(label).build();
                    } else {
                        return new Pattern.PatternBuilder(identifierBuilder).newAnonymousNode().withLabels(label).build();
                    }
                } else {
                    if (isNamed) {
                        return new Pattern.PatternBuilder(identifierBuilder).newNamedNode().build();
                    } else {
                        return new Pattern.PatternBuilder(identifierBuilder).newAnonymousNode().build();
                    }
                }
            } else {
                List<INodeAnalyzer> idNode = mergeClause.getExtendableNodeIdentifiers();
                if (idNode.size() == 0) {
                    return new Pattern.PatternBuilder(identifierBuilder).newNamedNode().build();
                } else {
                    INodeAnalyzer node = idNode.get(r.getInteger(0, idNode.size()));
                    return new Pattern.PatternBuilder(identifierBuilder).newRefDefinedNode(node).build();
                }
            }
        } else {
            Pattern.PatternBuilder.OngoingNode leftNode;
            boolean isNewLeft = Randomly.getBoolean();
            if (isNewLeft) {
                boolean withLabelLeft = Randomly.getBoolean();
                //boolean isNamedLeft = Randomly.getBoolean();
                boolean isNamedLeft = !Randomly.getBooleanWithSmallProbability();
                if (withLabelLeft) {
                    CypherSchema.CypherLabelInfo labelInfo = schema.getLabels().get(r.getInteger(0, sizeOfLabels));
                    ILabel label = new Label(labelInfo.getName());
                    if (isNamedLeft) {
                        leftNode = new Pattern.PatternBuilder(identifierBuilder).newNamedNode().withLabels(label);
                    } else {
                        leftNode = new Pattern.PatternBuilder(identifierBuilder).newAnonymousNode().withLabels(label);
                    }
                } else {
                    if (isNamedLeft) {
                        leftNode = new Pattern.PatternBuilder(identifierBuilder).newNamedNode();
                    } else {
                        leftNode = new Pattern.PatternBuilder(identifierBuilder).newAnonymousNode();
                    }
                }
            } else {
                List<INodeAnalyzer> idNode = mergeClause.getExtendableNodeIdentifiers();
                if (idNode.size() == 0) {
                    leftNode = new Pattern.PatternBuilder(identifierBuilder).newNamedNode();
                } else {
                    INodeAnalyzer node = idNode.get(r.getInteger(0, idNode.size()));
                    leftNode = new Pattern.PatternBuilder(identifierBuilder).newRefDefinedNode(node);
                }
            }

            Pattern.PatternBuilder.OngoingRelation relation;
            boolean withType = Randomly.getBoolean();
            //boolean isNamed = Randomly.getBoolean();
            boolean isNamed = !Randomly.getBooleanWithSmallProbability();
            Direction direction = Randomly.fromOptions(Direction.LEFT, Direction.RIGHT, Direction.BOTH);
            int typeOfLength = r.getInteger(0, 4);
            if (withType) {
                CypherSchema.CypherRelationTypeInfo typeInfo = schema.getRelationTypes().get(r.getInteger(0, sizeOfTypes));
                IType type = new RelationType(typeInfo.getName());
                if (isNamed) {
                    if (typeOfLength == 0) {
                        relation = leftNode.newNamedRelation().withType(type).withDirection(direction).withLengthUnbounded();
                    } else if (typeOfLength == 1) {
                        relation = leftNode.newNamedRelation().withType(type).withDirection(direction).withOnlyLengthLowerBound(r.getInteger(1, 4));
                    } else if (typeOfLength == 2) {
                        relation = leftNode.newNamedRelation().withType(type).withDirection(direction).withOnlyLengthUpperBound(r.getInteger(1, 4));
                    } else {
                        relation = leftNode.newNamedRelation().withType(type).withDirection(direction).withLength(r.getInteger(1, 4));
                    }
                } else {
                    if (typeOfLength == 0) {
                        relation = leftNode.newAnonymousRelation().withType(type).withDirection(direction).withLengthUnbounded();
                    } else if (typeOfLength == 1) {
                        relation = leftNode.newAnonymousRelation().withType(type).withDirection(direction).withOnlyLengthLowerBound(r.getInteger(1, 4));
                    } else if (typeOfLength == 2) {
                        relation = leftNode.newAnonymousRelation().withType(type).withDirection(direction).withOnlyLengthUpperBound(r.getInteger(1, 4));
                    } else {
                        relation = leftNode.newAnonymousRelation().withType(type).withDirection(direction).withLength(r.getInteger(1, 4));
                    }
                }
            } else {
                if (isNamed) {
                    if (typeOfLength == 0) {
                        relation = leftNode.newNamedRelation().withDirection(direction).withLengthUnbounded();
                    } else if (typeOfLength == 1) {
                        relation = leftNode.newNamedRelation().withDirection(direction).withOnlyLengthLowerBound(r.getInteger(1, 4));
                    } else if (typeOfLength == 2) {
                        relation = leftNode.newNamedRelation().withDirection(direction).withOnlyLengthUpperBound(r.getInteger(1, 4));
                    } else {
                        relation = leftNode.newNamedRelation().withDirection(direction).withLength(r.getInteger(1, 4));
                    }
                } else {
                    if (typeOfLength == 0) {
                        relation = leftNode.newAnonymousRelation().withDirection(direction).withLengthUnbounded();
                    } else if (typeOfLength == 1) {
                        relation = leftNode.newAnonymousRelation().withDirection(direction).withOnlyLengthLowerBound(r.getInteger(1, 4));
                    } else if (typeOfLength == 2) {
                        relation = leftNode.newAnonymousRelation().withDirection(direction).withOnlyLengthUpperBound(r.getInteger(1, 4));
                    } else {
                        relation = leftNode.newAnonymousRelation().withDirection(direction).withLength(r.getInteger(1, 4));
                    }
                }
            }

            Pattern.PatternBuilder.OngoingNode rightNode;
            boolean isNewRight = Randomly.getBoolean();
            if (isNewRight) {
                boolean withLabelRight = Randomly.getBoolean();
                //boolean isNamedRight = Randomly.getBoolean();
                boolean isNamedRight = !Randomly.getBooleanWithSmallProbability();
                if (withLabelRight) {
                    CypherSchema.CypherLabelInfo labelInfo = schema.getLabels().get(r.getInteger(0, sizeOfLabels));
                    ILabel label = new Label(labelInfo.getName());
                    if (isNamedRight) {
                        rightNode = relation.newNamedNode().withLabels(label);
                    } else {
                        rightNode = relation.newAnonymousNode().withLabels(label);
                    }
                } else {
                    if (isNamedRight) {
                        rightNode = relation.newNamedNode();
                    } else {
                        rightNode = relation.newAnonymousNode();
                    }
                }
            } else {
                List<INodeAnalyzer> idNode = mergeClause.getExtendableNodeIdentifiers();
                if (idNode.size() == 0) {
                    rightNode = new Pattern.PatternBuilder(identifierBuilder).newNamedNode();
                } else {
                    INodeAnalyzer node = idNode.get(r.getInteger(0, idNode.size()));
                    rightNode = new Pattern.PatternBuilder(identifierBuilder).newRefDefinedNode(node);
                }
            }
            return rightNode.build();
        }
    }

    @Override
    public List<IPattern> generatePattern(IMatchAnalyzer matchClause, IIdentifierBuilder identifierBuilder, S schema) {
        List<IPattern> matchPattern = matchClause.getPatternTuple();
        if (matchPattern.size() > 0 && !overrideOld) {
            return matchPattern;
        }

        List<IPattern> patternTuple = new ArrayList<>();
        Randomly r = new Randomly();
        int numOfPatterns = r.getInteger(1, 4);
        for (int i = 0; i < numOfPatterns; i++) {
            patternTuple.add(generateSinglePattern(matchClause, identifierBuilder, schema));
        }
        return patternTuple;
    }

    @Override
    public IPattern generatePattern(IMergeAnalyzer mergeClause, IIdentifierBuilder identifierBuilder, S schema) {
        IPattern mergePattern = mergeClause.getPattern();
        if (mergePattern!=null && !overrideOld) {
            return mergePattern;
        }
        return generateSinglePattern(mergeClause, identifierBuilder, schema);
    }

    @Override
    public List<IPattern> generatePattern(ICreateAnalyzer createClause, IIdentifierBuilder identifierBuilder, S schema) {
        List<IPattern> createPattern = createClause.getPatternTuple();
        if (createPattern.size() > 0 && !overrideOld) {
            return createPattern;
        }

        List<IPattern> patternTuple = new ArrayList<>();
        Randomly r = new Randomly();
        int numOfPatterns = r.getInteger(1, 4);

        for (int i = 0; i < numOfPatterns; i++) {
            patternTuple.add(generateSinglePattern(createClause, identifierBuilder, schema));
        }
        return patternTuple;
    }
}
