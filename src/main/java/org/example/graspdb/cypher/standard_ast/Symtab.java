package org.example.graspdb.cypher.standard_ast;

import org.example.graspdb.cypher.ast.*;
import org.example.graspdb.cypher.ast.analyzer.*;

import java.util.ArrayList;
import java.util.List;

import static org.example.graspdb.cypher.oracle.DifferentialNonEmptyBranchOracle.queryGenerator;

public class Symtab implements ICypherSymtab {
    public boolean first;

    private IClauseAnalyzer parentClause;
    private boolean extendParent;
    //从模式中获取节点和关系变量
    private List<IPattern> patterns;
    //记录节点
    private List<INodeIdentifier> nodes;
    //记录关系
    private List<IRelationIdentifier> relations;
    //记录别名
    private List<IRet> aliasDefinitions;
    //记录路径变量
    private List<IPathAnalyzer> pathDefinitions;

    public Symtab(IClauseAnalyzer parentClause, boolean extendParent) {
        this.parentClause = parentClause;
        this.extendParent = extendParent;
        this.patterns = new ArrayList<>();
        this.aliasDefinitions = new ArrayList<>();
        this.pathDefinitions = new ArrayList<>();
        this.nodes = new ArrayList<>();
        this.relations = new ArrayList<>();
        this.first = false;
    }

    public void setParentClause(IClauseAnalyzer cypherClause) {
        this.parentClause = cypherClause;
        this.extendParent = true;
    }

    @Override
    public List<IPattern> getPatterns() {
        return patterns;
    }

    @Override
    public void addPattern(IPattern pattern) {
        patterns.add(pattern);
        addPathDef(pattern);
    }

    @Override
    public void addNode(INodeIdentifier node) {
        nodes.add(node);
    }

    @Override
    public void add_aliasDefinitions(IRet iRet) {
        aliasDefinitions.add(iRet);
    }

    @Override
    public void addRelation(IRelationIdentifier relation) {
        relations.add(relation);
    }

    @Override
    public void removePattern() {
        patterns.remove(patterns.size() - 1);
    }

    @Override
    public void addPathDef(IPattern pattern) {
        if (pattern.getPathIdentifier() != null)
            pathDefinitions.add(new PathAnalyzer(pattern.getPathIdentifier(),
                    new ContextInfo(parentClause, parentClause.getExtendableIdentifiers())));
    }

    @Override
    public void removePathDef() {
        pathDefinitions.remove(pathDefinitions.size() - 1);
    }

    //设置模式以及path变量
    @Override
    public void setPatterns(List<IPattern> patterns) {
        if (patterns != null) {
            this.patterns = new ArrayList<>();
            this.patterns.addAll(patterns);
            pathDefinitions = new ArrayList<>();
            for (IPattern p : patterns) {
                addPathDef(p);
            }
        }
    }

    @Override
    public List<IRet> getAliasDefinitions() {
        return aliasDefinitions;
    }

    @Override
    public List<IPathAnalyzer> getPathDefinitions() {
        return pathDefinitions;
    }

    @Override
    public void setAliasDefinition(List<IRet> aliasDefinitions) {
        if (aliasDefinitions != null) {
            this.aliasDefinitions = new ArrayList<>();
            this.aliasDefinitions.addAll(aliasDefinitions);
        }
    }

    @Override
    public void setpathDefinition(List<IPathAnalyzer> pathDefinitions) {
        if (pathDefinitions != null) {
            this.pathDefinitions = new ArrayList<>();
            this.pathDefinitions.addAll(pathDefinitions);
        }
    }

    @Override
    public List<IAliasAnalyzer> getLocalAliasDefs() {
        if (parentClause == null) {
            return new ArrayList<>();
        }
        List<IAliasAnalyzer> aliases = new ArrayList<>();
        if (parentClause instanceof IWith || parentClause instanceof IReturn || parentClause instanceof IUnwind || parentClause instanceof Foreach) {
            for (IRet aliasDef : aliasDefinitions) {
                if (aliasDef.isAlias()) {
                    aliases.add(new AliasAnalyzer((IAlias) aliasDef.getIdentifier(),
                            new ContextInfo(parentClause, parentClause.getExtendableIdentifiers())));
                }
            }
            linkAliasDefinitions(aliases);
        }
        List<IAliasAnalyzer> result = new ArrayList<>();
        result.addAll(aliases);
        //当前在列表理解中
        if (queryGenerator.get_list_comprehension_flag() && queryGenerator.get_list_comprehension_alias() != null) {
            result.add(new AliasAnalyzer(queryGenerator.get_list_comprehension_alias(),
                    new ContextInfo(parentClause, parentClause.getExtendableIdentifiers())));
        }
        return result;
    }

    @Override
    public List<IPathAnalyzer> getLocalPathDefs() {
        if (parentClause == null) {
            return new ArrayList<>();
        }
        List<IPathAnalyzer> paths = new ArrayList<>();
        //todo 禁止对同一个create语句中交叉引用
        if (parentClause instanceof IMatch || parentClause instanceof IMerge) {
            for (IPathAnalyzer pathDef : pathDefinitions) {
                paths.add(new PathAnalyzer(pathDef,
                        new ContextInfo(parentClause, parentClause.getExtendableIdentifiers())));
            }
            linkPathDefinitions(paths);
        }
        //如果当前在模式理解中，需要加上局部定义的path
        if (queryGenerator.get_pattern_comprehension_flag() && queryGenerator.get_pattern_in_comprehension() != null) {
            IPattern pattern = queryGenerator.get_pattern_in_comprehension();
            if (pattern.getPathIdentifier() != null)
                paths.add(new PathAnalyzer(pattern.getPathIdentifier(),
                        new ContextInfo(parentClause, parentClause.getExtendableIdentifiers())));
        }
        List<IPathAnalyzer> result = new ArrayList<>();
        result.addAll(paths);
        return result;
    }

    @Override
    public List<INodeAnalyzer> getLocalNodePatterns() {
        if (parentClause == null)
            return null;
        List<INodeAnalyzer> Nodes = new ArrayList<>();
        //todo 禁止对同一个create语句中交叉引用
        if (parentClause instanceof IMatch || parentClause instanceof IMerge) {
            for (INodeIdentifier node : nodes) {
                if (!node.isAnonymous()) {
                    Nodes.add(new NodeAnalyzer(node,
                            new ContextInfo(parentClause, parentClause.getExtendableIdentifiers())));
                }
            }
            linkNodeDefinitions(Nodes);
            return Nodes;
        }

        //如果当前在模式理解中，需要加上局部定义的节点
        if (queryGenerator.get_pattern_comprehension_flag() && queryGenerator.get_pattern_in_comprehension() != null) {
            IPattern pattern = queryGenerator.get_pattern_in_comprehension();
            for (IPatternElement patternElement : pattern.getPatternElements()) {
                if (patternElement instanceof INodeIdentifier && !patternElement.isAnonymous()) {
                    Nodes.add(new NodeAnalyzer((INodeIdentifier) patternElement,
                            new ContextInfo(parentClause, parentClause.getExtendableIdentifiers())));
                }
            }
        }

        return Nodes;
    }

    @Override
    public List<IRelationAnalyzer> getLocalRelationPatterns() {
        if (parentClause == null)
            return null;

        List<IRelationAnalyzer> Relations = new ArrayList<>();
        //todo 禁止对同一个create语句中交叉引用
        if (parentClause instanceof IMatch || parentClause instanceof IMerge) {
            for (IRelationIdentifier relation : relations) {
                if (!relation.isAnonymous()) {
                    Relations.add(new RelationAnalyzer(relation,
                            new ContextInfo(parentClause, parentClause.getExtendableIdentifiers())));
                }
            }
            linkRelationDefinitions(Relations);
            return Relations;
        }
        //如果当前在模式理解中，需要加上局部定义的关系
        if (queryGenerator.get_pattern_comprehension_flag() && queryGenerator.get_pattern_in_comprehension() != null) {
            IPattern pattern = queryGenerator.get_pattern_in_comprehension();
            for (IPatternElement patternElement : pattern.getPatternElements()) {
                if (patternElement instanceof IRelationIdentifier && !patternElement.isAnonymous()) {
                    Relations.add(new RelationAnalyzer((IRelationIdentifier) patternElement,
                            new ContextInfo(parentClause, parentClause.getExtendableIdentifiers())));
                }
            }
        }
        return Relations;

    }

    @Override
    public List<IAliasAnalyzer> getAvailableAliasDefs() {
        //对于内部嵌套的首个语句
        if (extendParent && first) {
            // todo 获取本地定义的,对于WITH,RETURN而言本地定义的变量不可用
            List<IAliasAnalyzer> aliases = null;
            if (parentClause instanceof With || parentClause instanceof Return)
                aliases = new ArrayList<>();
            else
                aliases = getLocalAliasDefs();
            for (IAliasAnalyzer alias : aliases) {
                alias.setFormerDef(null);
            }
            List<IAliasAnalyzer> extendedAliases = parentClause.toAnalyzer().getAvailableAliases();
            for (IAliasAnalyzer extendedAlias : extendedAliases) {
                if (!aliases.contains(extendedAlias)) {
                    aliases.add(extendedAlias);
                } else {
                    IAliasAnalyzer alias = aliases.get(aliases.indexOf(extendedAlias));
                    if (alias.getExpression() == null) {
                        alias.setFormerDef(extendedAlias);
                    }
                }
            }
            return aliases;
        }
        //非嵌套内部的首个语句
        else {
            //本语句定义的
            // todo 获取本地定义的,对于WITH,RETURN而言本地定义的变量不可用
            List<IAliasAnalyzer> availableAliases = null;
            if (parentClause instanceof With || parentClause instanceof Return)
                availableAliases = new ArrayList<>();
            else
                availableAliases = getLocalAliasDefs();
            //从前一个语句继承的
            availableAliases.addAll(parentClause.getExtendableAliases());
            return availableAliases;
        }
    }

    @Override
    public List<IPathAnalyzer> getAvailablePathDefs() {
        //对于内部嵌套的首个语句
        if (extendParent && first) {
            //获取本地定义的
            List<IPathAnalyzer> paths = getLocalPathDefs();
            for (IPathAnalyzer path : paths) {
                path.setFormerDef(null);
            }
            List<IPathAnalyzer> extendedPaths = parentClause.toAnalyzer().getAvailablePathIdentifiers();
            for (IPathAnalyzer extendedPath : extendedPaths) {
                if (!paths.contains(extendedPath)) {
                    paths.add(extendedPath);
                } else {
                    IPathAnalyzer path = paths.get(paths.indexOf(extendedPath));
                    if (path.getPattern() == null) {
                        path.setFormerDef(extendedPath);
                    }
                }
            }
            return paths;
        }
        //没有在其他语句内部嵌套或者不是嵌套中的首个子语句
        else {
            //本语句定义的
            List<IPathAnalyzer> availablePaths = getLocalPathDefs();
            //从前一个语句继承的
            availablePaths.addAll(parentClause.getExtendablePathIdentifiers());
            return availablePaths;
        }
    }

    @Override
    public List<INodeAnalyzer> getAvailableNodePatterns() {
        if (extendParent && first) {
            List<INodeAnalyzer> nodes = getLocalNodePatterns();
            for (INodeAnalyzer node : nodes) {
                node.setFormerDef(null);
            }
            List<INodeAnalyzer> extendedNodes = parentClause.toAnalyzer().getAvailableNodeIdentifiers();
            for (INodeAnalyzer extendedNode : extendedNodes) {
                boolean containing = nodes.contains(extendedNode);
                boolean containing2 = nodes.contains(extendedNode);
                if (!containing) {
                    nodes.add(extendedNode);
                } else {
                    boolean containing3 = nodes.contains(extendedNode);
                    INodeAnalyzer node = nodes.get(nodes.indexOf(extendedNode));
                    node.setFormerDef(extendedNode);
                }
            }
            return nodes;
        } else {
            //本语句定义的
            List<INodeAnalyzer> availableNodes = getLocalNodePatterns();
            //从前一个语句继承的
            availableNodes.addAll(parentClause.getExtendableNodeIdentifiers());
            return availableNodes;
        }
    }

    @Override
    public List<IRelationAnalyzer> getAvailableRelationPatterns() {
        if (extendParent && first) {
            List<IRelationAnalyzer> relations = getLocalRelationPatterns();
            for (IRelationAnalyzer relation : relations) {
                relation.setFormerDef(null);
            }
            List<IRelationAnalyzer> extendedRelations = parentClause.toAnalyzer().getAvailableRelationIdentifiers();
            for (IRelationAnalyzer extendedRelation : extendedRelations) {
                if (!relations.contains(extendedRelation)) {
                    relations.add(extendedRelation);
                } else {
                    IRelationAnalyzer relationPattern = relations.get(relations.indexOf(extendedRelation));
                    relationPattern.setFormerDef(extendedRelation);
                }
            }
            return relations;
        } else {
            //本语句定义的
            List<IRelationAnalyzer> availableRelations = getLocalRelationPatterns();
            //从前一个语句继承的
            availableRelations.addAll(parentClause.getExtendableRelationIdentifiers());
            return availableRelations;
        }

    }

    private void linkNodeDefinitions(List<INodeAnalyzer> curNodes) {
        if (parentClause == null) {
            return;
        }

        List<INodeAnalyzer> prevDefs = new ArrayList<>();
        if (parentClause.getPrevClause() != null) {
            prevDefs = parentClause.getPrevClause().toAnalyzer().getAvailableNodeIdentifiers();
        }

        for (INodeAnalyzer node : curNodes) {
            node.setFormerDef(null);
        }
        for (INodeAnalyzer prevDef : prevDefs) {
            if (curNodes.contains(prevDef)) {
                INodeAnalyzer node = curNodes.get(curNodes.indexOf(prevDef));
                node.setFormerDef(prevDef);
            }
        }
    }

    private void linkRelationDefinitions(List<IRelationAnalyzer> curRelations) {
        if (parentClause == null) {
            return;
        }

        List<IRelationAnalyzer> prevDefs = new ArrayList<>();
        if (parentClause.getPrevClause() != null) {
            prevDefs = parentClause.getPrevClause().toAnalyzer().getAvailableRelationIdentifiers();
        }

        for (IRelationAnalyzer relation : curRelations) {
            relation.setFormerDef(null);
        }
        for (IRelationAnalyzer prevDef : prevDefs) {
            if (curRelations.contains(prevDef)) {
                IRelationAnalyzer relation = curRelations.get(curRelations.indexOf(prevDef));
                relation.setFormerDef(prevDef);
            }
        }
    }

    private void linkAliasDefinitions(List<IAliasAnalyzer> curAlias) {
        if (parentClause == null) {
            return;
        }

        List<IAliasAnalyzer> prevDefs = new ArrayList<>();
        if (parentClause.getPrevClause() != null) {
            prevDefs = parentClause.getPrevClause().toAnalyzer().getAvailableAliases();
        }

        for (IAliasAnalyzer alias : curAlias) {
            alias.setFormerDef(null);
        }
        for (IAliasAnalyzer prevDef : prevDefs) {
            if (curAlias.contains(prevDef)) {
                IAliasAnalyzer alias = curAlias.get(curAlias.indexOf(prevDef));
                if (alias.getExpression() == null) {
                    alias.setFormerDef(prevDef);
                }
            }
        }
    }

    private void linkPathDefinitions(List<IPathAnalyzer> curPath) {
        if (parentClause == null) {
            return;
        }
        List<IPathAnalyzer> prevDefs = new ArrayList<>();
        if (parentClause.getPrevClause() != null) {
            prevDefs = parentClause.getPrevClause().toAnalyzer().getAvailablePathIdentifiers();
        }

        for (IPathAnalyzer path : curPath) {
            path.setFormerDef(null);
        }
        for (IPathAnalyzer prevDef : prevDefs) {
            if (curPath.contains(prevDef)) {
                IPathAnalyzer path = curPath.get(curPath.indexOf(prevDef));
                if (path.getPattern() == null) {
                    path.setFormerDef(prevDef);
                }
            }
        }
    }

    @Override
    public List<IIdentifierAnalyzer> getLocalIdentifiers() {
        List<IIdentifierAnalyzer> identifierAnalyzers = new ArrayList<>();
        identifierAnalyzers.addAll(getLocalNodePatterns());
        identifierAnalyzers.addAll(getLocalRelationPatterns());
        identifierAnalyzers.addAll(getLocalAliasDefs());
        identifierAnalyzers.addAll(getLocalPathDefs());
        return identifierAnalyzers;
    }

    @Override
    public List<IIdentifierAnalyzer> getAvailableIdentifiers() {
        List<IIdentifierAnalyzer> identifierAnalyzers = new ArrayList<>();
        identifierAnalyzers.addAll(getAvailableNodePatterns());
        identifierAnalyzers.addAll(getAvailableRelationPatterns());
        identifierAnalyzers.addAll(getAvailableAliasDefs());
        identifierAnalyzers.addAll(getAvailablePathDefs());
        return identifierAnalyzers;
    }

}
