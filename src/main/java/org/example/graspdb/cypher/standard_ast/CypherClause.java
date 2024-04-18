package org.example.graspdb.cypher.standard_ast;

import org.example.graspdb.cypher.ast.ICypherClause;
import org.example.graspdb.cypher.ast.IDele;
import org.example.graspdb.cypher.ast.IIdentifier;
import org.example.graspdb.cypher.ast.IPatternElement;
import org.example.graspdb.cypher.ast.analyzer.*;
import org.example.graspdb.cypher.gen.query.ModelBasedQueryGenerator;
import org.example.graspdb.cypher.standard_ast.expr.CaseWhenExpression;
import org.example.graspdb.cypher.standard_ast.expr.IdentifierExpression;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public abstract class CypherClause implements IClauseAnalyzer {
    public Symtab symtab;
    protected ICypherClause nextClause = null, prevClause = null;
    protected Boolean inCall = false;
    protected Boolean inSubQuery = false;
    protected Boolean inForeach = false;
    protected Boolean inQuery = false;

    public CypherClause(boolean extendParent) {
        symtab = new Symtab(this, extendParent);
    }

    @Override
    public void setParentClause(CypherClause cypherClause) {
        symtab.setParentClause(cypherClause);
    }

    @Override
    public void setNextClause(ICypherClause next) {
        this.nextClause = next;
        if (next != null) {
            next.setPrevClause(this);
        }
    }

    @Override
    public void setInCall(Boolean b) {
        inCall = b;
    }

    @Override
    public void setFirstClause(Boolean b) {
        symtab.first = b;
    }

    @Override
    public void setInForeach(Boolean b) {
        inForeach = b;
    }

    @Override
    public void setInSubQuery(Boolean b) {
        inSubQuery = b;
    }

    @Override
    public void setend_return(Boolean b) {
        inQuery = b;
    }

    @Override
    public Boolean getInCall() {
        return inCall;
    }

    @Override
    public Boolean getFirstClause() {
        return symtab.first;
    }

    @Override
    public Boolean getInSubQuery() {
        return inSubQuery;
    }

    @Override
    public Boolean getInQuery() {
        return inQuery;
    }

    @Override
    public Boolean getInForeach() {
        return inForeach;
    }

    @Override
    public ICypherClause getNextClause() {
        return nextClause;
    }

    @Override
    public void setPrevClause(ICypherClause prev) {
        this.prevClause = prev;
    }

    @Override
    public ICypherClause getPrevClause() {
        return this.prevClause;
    }

    @Override
    public List<IAliasAnalyzer> getLocalAliases() {
        return symtab.getLocalAliasDefs();
    }

    @Override
    public List<INodeAnalyzer> getLocalNodeIdentifiers() {
        return symtab.getLocalNodePatterns();
    }

    @Override
    public List<IPathAnalyzer> getLocalPathIdentifiers() {
        return symtab.getLocalPathDefs();
    }

    @Override
    public List<IRelationAnalyzer> getLocalRelationIdentifiers() {
        return symtab.getLocalRelationPatterns();
    }

    @Override
    public List<IAliasAnalyzer> getAvailableAliases() {
        return symtab.getAvailableAliasDefs();
    }

    @Override
    public List<INodeAnalyzer> getAvailableNodeIdentifiers() {
        return symtab.getAvailableNodePatterns();
    }

    @Override
    public List<IPathAnalyzer> getAvailablePathIdentifiers() {
        return symtab.getAvailablePathDefs();
    }

    @Override
    public List<IRelationAnalyzer> getAvailableRelationIdentifiers() {
        return symtab.getAvailableRelationPatterns();
    }

    @Override
    public List<IAliasAnalyzer> getExtendableAliases() {
        if (prevClause == null)
            return new ArrayList<>();
        //前一子句是with的情况
        if (prevClause instanceof With) {
            if(((With)(prevClause)).getReturnList().get(0).isAll())
                return prevClause.toAnalyzer().getAvailableAliases();
            else
                return prevClause.toAnalyzer().getLocalAliases();
        }
        //前一子句是call的情况
        if (prevClause instanceof Call) {
            int size_call = ((Call) prevClause).getSequence().getClauseList().size();
            List<IAliasAnalyzer> extendedAliases = ((Call) prevClause).getSequence().getClauseList().get(size_call - 1).toAnalyzer().getLocalAliases();
            if (prevClause.getPrevClause() != null)
                extendedAliases.addAll(prevClause.getPrevClause().toAnalyzer().getAvailableAliases());
            return extendedAliases;
        }
        //前一子句是foreach的情况
        if (prevClause instanceof Foreach) {
            if (prevClause.getPrevClause() == null)
                return new ArrayList<>();
            else {
                while(prevClause instanceof Foreach)
                    prevClause=prevClause.getPrevClause();
                if(prevClause == null)
                    return new ArrayList<>();
            }
        }
        return prevClause.toAnalyzer().getAvailableAliases();
    }

    @Override
    public List<INodeAnalyzer> getExtendableNodeIdentifiers() {
        if (prevClause == null)
            return new ArrayList<>();
        //当前子句是delete的情况
        if (this instanceof Delete) {
            List<INodeAnalyzer> nodeAnalyzers = prevClause.toAnalyzer().getAvailableNodeIdentifiers();
            List<IPathAnalyzer> pathAnalyzers = prevClause.toAnalyzer().getAvailablePathIdentifiers();
            //前一子句是with的情况
            if (prevClause instanceof With) {
                if(!((With)(prevClause)).getReturnList().get(0).isAll())
                {nodeAnalyzers  =  prevClause.toAnalyzer().getLocalNodeIdentifiers();
                    pathAnalyzers = prevClause.toAnalyzer().getLocalPathIdentifiers();
                }
            }
            for (IDele dele : ((Delete) this).getDeleteList()) {
                if (dele.getExpression() instanceof IdentifierExpression) {
                    //删除点的情况
                    Iterator<INodeAnalyzer> iterator_n = nodeAnalyzers.iterator();
                    while (iterator_n.hasNext()) {
                        INodeAnalyzer nodeAnalyzer = iterator_n.next();
                        if (nodeAnalyzer.getName().equals(((IdentifierExpression) dele.getExpression()).getIdentifier().getName())) {
                            iterator_n.remove(); // 使用迭代器安全地删除元素
                        }
                    }
                    //删除路径的情况
                    HashSet<String> namesToRemove = new HashSet<>();

                    for (IPathAnalyzer pathAnalyzer : pathAnalyzers) {
                        String targetName = ((IdentifierExpression) dele.getExpression()).getIdentifier().getName();
                        if (pathAnalyzer.getName().equals(targetName)) {
                            for (IPatternElement patternElement : pathAnalyzer.getPattern().getPatternElements()) {
                                if (patternElement.getType() == CypherType.NODE) {
                                    namesToRemove.add(patternElement.getName());
                                }
                            }
                        }
                    }

// 移除 nodeAnalyzers 中包含在 namesToRemove 集合中的元素
                    Iterator<INodeAnalyzer> iterator_r = nodeAnalyzers.iterator();
                    while (iterator_r.hasNext()) {
                        INodeAnalyzer nodeAnalyzer = iterator_r.next();
                        if (namesToRemove.contains(nodeAnalyzer.getName())) {
                            iterator_r.remove();
                        }
                    }
                }
                if (dele.getExpression() instanceof CaseWhenExpression && (dele.getExpression()).getValue(null) instanceof IdentifierExpression) {
                    Iterator<INodeAnalyzer> iterator = nodeAnalyzers.iterator();
                    String targetName = ((IdentifierExpression) dele.getExpression().getValue(null)).getIdentifier().getName();

                    while (iterator.hasNext()) {
                        INodeAnalyzer nodeAnalyzer = iterator.next();
                        if (targetName.equals(nodeAnalyzer.getName())) {
                            iterator.remove(); // 使用迭代器的remove方法删除元素
                            break; // 找到匹配项后立即退出循环
                        }
                    }

                }
            }
            return nodeAnalyzers;
        }
        //前一子句是with的情况
        if (prevClause instanceof With) {
            if(((With)(prevClause)).getReturnList().get(0).isAll())
                return prevClause.toAnalyzer().getAvailableNodeIdentifiers();
            else
                return prevClause.toAnalyzer().getLocalNodeIdentifiers();
        }
        //前一子句是call的情况
        if (prevClause instanceof Call) {
            int size_call = ((Call) prevClause).getSequence().getClauseList().size();
            List<INodeAnalyzer> extendedNodes = ((Call) prevClause).getSequence().getClauseList().get(size_call - 1).toAnalyzer().getLocalNodeIdentifiers();
            if (prevClause.getPrevClause() != null)
                extendedNodes.addAll(prevClause.getPrevClause().toAnalyzer().getAvailableNodeIdentifiers());
            return extendedNodes;
        }
        //前一子句是foreach的情况
        if (prevClause instanceof Foreach) {
            if (prevClause.getPrevClause() != null)
                return prevClause.getPrevClause().toAnalyzer().getAvailableNodeIdentifiers();
            else
                return new ArrayList<>();
        }
        //前一子句是delete的情况
        if (prevClause instanceof Delete) {
            List<INodeAnalyzer> nodeAnalyzers = prevClause.toAnalyzer().getAvailableNodeIdentifiers();
            List<IPathAnalyzer> pathAnalyzers = prevClause.toAnalyzer().getAvailablePathIdentifiers();
            for (IDele dele : ((Delete) prevClause).getDeleteList()) {
                if (dele.getExpression() instanceof IdentifierExpression) {
                    //删除点的情况
                    Iterator<INodeAnalyzer> iterator_n = nodeAnalyzers.iterator();
                    while (iterator_n.hasNext()) {
                        INodeAnalyzer nodeAnalyzer = iterator_n.next();
                        if (nodeAnalyzer.getName().equals(((IdentifierExpression) dele.getExpression()).getIdentifier().getName())) {
                            iterator_n.remove(); // 使用迭代器安全地删除元素
                        }
                    }
                    //删除路径的情况
                    HashSet<String> namesToRemove = new HashSet<>();

                    for (IPathAnalyzer pathAnalyzer : pathAnalyzers) {
                        String targetName = ((IdentifierExpression) dele.getExpression()).getIdentifier().getName();
                        if (pathAnalyzer.getName().equals(targetName)) {
                            for (IPatternElement patternElement : pathAnalyzer.getPattern().getPatternElements()) {
                                if (patternElement.getType() == CypherType.NODE) {
                                    namesToRemove.add(patternElement.getName());
                                }
                            }
                        }
                    }

// 移除 nodeAnalyzers 中包含在 namesToRemove 集合中的元素
                    Iterator<INodeAnalyzer> iterator_r = nodeAnalyzers.iterator();
                    while (iterator_r.hasNext()) {
                        INodeAnalyzer nodeAnalyzer = iterator_r.next();
                        if (namesToRemove.contains(nodeAnalyzer.getName())) {
                            iterator_r.remove();
                        }
                    }
                }
                if (dele.getExpression() instanceof CaseWhenExpression && (dele.getExpression()).getValue(null) instanceof IdentifierExpression) {
                    Iterator<INodeAnalyzer> iterator = nodeAnalyzers.iterator();
                    String targetName = ((IdentifierExpression) dele.getExpression().getValue(null)).getIdentifier().getName();

                    while (iterator.hasNext()) {
                        INodeAnalyzer nodeAnalyzer = iterator.next();
                        if (targetName.equals(nodeAnalyzer.getName())) {
                            iterator.remove(); // 使用迭代器的remove方法删除元素
                            break; // 找到匹配项后立即退出循环
                        }
                    }

                }
            }
            return nodeAnalyzers;
        }
        return prevClause.toAnalyzer().getAvailableNodeIdentifiers();
    }

    @Override
    public List<IPathAnalyzer> getExtendablePathIdentifiers() {
        if (prevClause == null)
            return new ArrayList<>();
        //当前子句是delete的情况
        if (this instanceof Delete) {
            List<IPathAnalyzer> pathAnalyzers = prevClause.toAnalyzer().getAvailablePathIdentifiers();
            //前一子句是with的情况
            if (prevClause instanceof With) {
                if(!((With)(prevClause)).getReturnList().get(0).isAll())
                {
                    pathAnalyzers = prevClause.toAnalyzer().getLocalPathIdentifiers();
                }
            }
            for (IDele dele : ((Delete) this).getDeleteList()) {
                if (dele.getExpression() instanceof IdentifierExpression) {
                    Iterator<IPathAnalyzer> iterator = pathAnalyzers.iterator();
                    String targetName = ((IdentifierExpression) dele.getExpression()).getIdentifier().getName();

                    while (iterator.hasNext()) {
                        IPathAnalyzer pathAnalyzer = iterator.next();
                        if (targetName.equals(pathAnalyzer.getName())) {
                            iterator.remove(); // 使用迭代器的remove方法删除元素
                            break; // 找到匹配项后立即退出循环
                        }
                    }

                }
                if (dele.getExpression() instanceof CaseWhenExpression && (dele.getExpression()).getValue(null) instanceof IdentifierExpression) {

                    Iterator<IPathAnalyzer> iterator = pathAnalyzers.iterator();
                    String targetName = ((IdentifierExpression) dele.getExpression().getValue(null)).getIdentifier().getName();

                    while (iterator.hasNext()) {
                        IPathAnalyzer pathAnalyzer = iterator.next();
                        if (targetName.equals(pathAnalyzer.getName())) {
                            iterator.remove(); // 使用迭代器的remove方法删除元素
                            break; // 找到匹配项后立即退出循环
                        }
                    }

                }
            }
            return pathAnalyzers;
        }
        //前一子句是with的情况
        if (prevClause instanceof With) {
            if(((With)(prevClause)).getReturnList().get(0).isAll())
                return prevClause.toAnalyzer().getAvailablePathIdentifiers();
            else
                return prevClause.toAnalyzer().getLocalPathIdentifiers();
        }
        //前一子句是call的情况
        if (prevClause instanceof Call) {
            int size_call = ((Call) prevClause).getSequence().getClauseList().size();
            List<IPathAnalyzer> extendedPaths = ((Call) prevClause).getSequence().getClauseList().get(size_call - 1).toAnalyzer().getLocalPathIdentifiers();
            if (prevClause.getPrevClause() != null)
                extendedPaths.addAll(prevClause.getPrevClause().toAnalyzer().getAvailablePathIdentifiers());
            return extendedPaths;
        }
        //前一子句是foreach的情况
        if (prevClause instanceof Foreach) {
            if (prevClause.getPrevClause() != null)
                return prevClause.getPrevClause().toAnalyzer().getAvailablePathIdentifiers();
            else
                return new ArrayList<>();
        }
        //前一子句是delete的情况
        if (prevClause instanceof Delete) {
            List<IPathAnalyzer> pathAnalyzers = prevClause.toAnalyzer().getAvailablePathIdentifiers();
            for (IDele dele : ((Delete) prevClause).getDeleteList()) {
                if (dele.getExpression() instanceof IdentifierExpression) {
                    Iterator<IPathAnalyzer> iterator = pathAnalyzers.iterator();
                    String targetName = ((IdentifierExpression) dele.getExpression()).getIdentifier().getName();

                    while (iterator.hasNext()) {
                        IPathAnalyzer pathAnalyzer = iterator.next();
                        if (targetName.equals(pathAnalyzer.getName())) {
                            iterator.remove(); // 使用迭代器的remove方法删除元素
                            break; // 找到匹配项后立即退出循环
                        }
                    }

                }
                if (dele.getExpression() instanceof CaseWhenExpression && (dele.getExpression()).getValue(null) instanceof IdentifierExpression) {

                    Iterator<IPathAnalyzer> iterator = pathAnalyzers.iterator();
                    String targetName = ((IdentifierExpression) dele.getExpression().getValue(null)).getIdentifier().getName();

                    while (iterator.hasNext()) {
                        IPathAnalyzer pathAnalyzer = iterator.next();
                        if (targetName.equals(pathAnalyzer.getName())) {
                            iterator.remove(); // 使用迭代器的remove方法删除元素
                            break; // 找到匹配项后立即退出循环
                        }
                    }

                }
            }
            return pathAnalyzers;
        }
        return prevClause.toAnalyzer().getAvailablePathIdentifiers();
    }

    @Override
    public List<IRelationAnalyzer> getExtendableRelationIdentifiers() {
        if (prevClause == null)
            return new ArrayList<>();
        //当前是delete的话
        if (this instanceof Delete)
        {
            List<IRelationAnalyzer> relationAnalyzers = prevClause.toAnalyzer().getAvailableRelationIdentifiers();
            List<IPathAnalyzer> pathAnalyzers = prevClause.toAnalyzer().getAvailablePathIdentifiers();
            //前一子句是with的情况
            if (prevClause instanceof With) {
                if(!((With)(prevClause)).getReturnList().get(0).isAll())
                {relationAnalyzers  =  prevClause.toAnalyzer().getLocalRelationIdentifiers();
                    pathAnalyzers = prevClause.toAnalyzer().getLocalPathIdentifiers();
                }
            }
            for (IDele dele : ((Delete) this).getDeleteList()) {
                Iterator<IRelationAnalyzer> iterator_r = relationAnalyzers.iterator();
                String targetName = null;
                if (dele.getExpression() instanceof IdentifierExpression)
                    targetName = ((IdentifierExpression) dele.getExpression()).getIdentifier().getName();
                else if (dele.getExpression() instanceof CaseWhenExpression && (dele.getExpression()).getValue(null) instanceof IdentifierExpression)
                    targetName = ((IdentifierExpression) dele.getExpression().getValue(null)).getIdentifier().getName();
                //todo 删除的是点
                if(ModelBasedQueryGenerator.node_to_relationship.containsKey(targetName)){
                    HashSet<String> relation_deleted = ModelBasedQueryGenerator.node_to_relationship.get(targetName);
                    while (iterator_r.hasNext()) {
                        IRelationAnalyzer relationAnalyzer = iterator_r.next();
                        if (relation_deleted.contains(relationAnalyzer.getName())) {
                            iterator_r.remove(); // 使用迭代器的remove方法删除元素
                        }
                    }
                }
                //todo 删除的是关系
                else {
                    while (iterator_r.hasNext()) {
                        IRelationAnalyzer relationAnalyzer = iterator_r.next();
                        if (relationAnalyzer.getName().equals(targetName)) {
                            iterator_r.remove(); // 使用迭代器的remove方法删除元素
                            break; // 找到匹配项后立即退出循环
                        }
                    }
                }
                //todo 删除的是路径
                for (IPathAnalyzer pathAnalyzer : pathAnalyzers) {
                    if (dele.getExpression() instanceof IdentifierExpression && pathAnalyzer.getName().equals(((IdentifierExpression) dele.getExpression()).getIdentifier().getName())) {
                        for (IPatternElement patternElement : pathAnalyzer.getPattern().getPatternElements()) {
                            if (patternElement.getType() == CypherType.RELATION) {
                                Iterator<IRelationAnalyzer> iterator = relationAnalyzers.iterator();
                                while (iterator.hasNext()) {
                                    IRelationAnalyzer relationAnalyzer = iterator.next();
                                    if (relationAnalyzer.getName().equals(patternElement.getName())) {
                                        iterator.remove(); // 使用迭代器的remove方法删除元素
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return relationAnalyzers;
        }
        //前一子句是with的情况
        if (prevClause instanceof With) {
            if(((With)(prevClause)).getReturnList().get(0).isAll())
                return prevClause.toAnalyzer().getAvailableRelationIdentifiers();
            else
                return prevClause.toAnalyzer().getLocalRelationIdentifiers();
        }
        //前一子句是call的情况
        if (prevClause instanceof Call) {
            int size_call = ((Call) prevClause).getSequence().getClauseList().size();
            List<IRelationAnalyzer> extendedRelations = ((Call) prevClause).getSequence().getClauseList().get(size_call - 1).toAnalyzer().getLocalRelationIdentifiers();
            if (prevClause.getPrevClause() != null)
                extendedRelations.addAll(prevClause.getPrevClause().toAnalyzer().getAvailableRelationIdentifiers());
            return extendedRelations;
        }
        //前一子句是foreach的情况
        if (prevClause instanceof Foreach) {
            if (prevClause.getPrevClause() != null)
                return prevClause.getPrevClause().toAnalyzer().getAvailableRelationIdentifiers();
            else
                return new ArrayList<>();
        }
        //todo 前一子句是delete的情况,被删除的点的相邻边也要删除
        if (prevClause instanceof Delete)
        {
            List<IRelationAnalyzer> relationAnalyzers = prevClause.toAnalyzer().getAvailableRelationIdentifiers();
            List<IPathAnalyzer> pathAnalyzers = prevClause.toAnalyzer().getAvailablePathIdentifiers();
            for (IDele dele : ((Delete) prevClause).getDeleteList()) {
                    Iterator<IRelationAnalyzer> iterator_r = relationAnalyzers.iterator();
                    String targetName = null;
                    if (dele.getExpression() instanceof IdentifierExpression)
                        targetName = ((IdentifierExpression) dele.getExpression()).getIdentifier().getName();
                    else if (dele.getExpression() instanceof CaseWhenExpression && (dele.getExpression()).getValue(null) instanceof IdentifierExpression)
                        targetName = ((IdentifierExpression) dele.getExpression().getValue(null)).getIdentifier().getName();
                    //todo 删除的是点
                    if(ModelBasedQueryGenerator.node_to_relationship.containsKey(targetName)){
                        HashSet<String> relation_deleted = ModelBasedQueryGenerator.node_to_relationship.get(targetName);
                        while (iterator_r.hasNext()) {
                            IRelationAnalyzer relationAnalyzer = iterator_r.next();
                            if (relation_deleted.contains(relationAnalyzer.getName())) {
                                iterator_r.remove(); // 使用迭代器的remove方法删除元素
                            }
                        }
                    }
                    //todo 删除的是关系
                    else {
                        while (iterator_r.hasNext()) {
                            IRelationAnalyzer relationAnalyzer = iterator_r.next();
                            if (relationAnalyzer.getName().equals(targetName)) {
                                iterator_r.remove(); // 使用迭代器的remove方法删除元素
                                break; // 找到匹配项后立即退出循环
                            }
                        }
                    }
                    //todo 删除的是路径
                    for (IPathAnalyzer pathAnalyzer : pathAnalyzers) {
                        if (dele.getExpression() instanceof IdentifierExpression && pathAnalyzer.getName().equals(((IdentifierExpression) dele.getExpression()).getIdentifier().getName())) {
                            for (IPatternElement patternElement : pathAnalyzer.getPattern().getPatternElements()) {
                                if (patternElement.getType() == CypherType.RELATION) {
                                    Iterator<IRelationAnalyzer> iterator = relationAnalyzers.iterator();
                                    while (iterator.hasNext()) {
                                        IRelationAnalyzer relationAnalyzer = iterator.next();
                                        if (relationAnalyzer.getName().equals(patternElement.getName())) {
                                            iterator.remove(); // 使用迭代器的remove方法删除元素
                                        }
                                    }
                                }
                            }
                        }
                    }
            }
            return relationAnalyzers;
        }
        return prevClause.toAnalyzer().getAvailableRelationIdentifiers();
    }

    @Override
    public List<IIdentifierAnalyzer> getAvailableIdentifiers() {
        List<IIdentifierAnalyzer> identifierAnalyzers = new ArrayList<>();
        identifierAnalyzers.addAll(getAvailableNodeIdentifiers());
        identifierAnalyzers.addAll(getAvailableRelationIdentifiers());
        identifierAnalyzers.addAll(getAvailablePathIdentifiers());
        identifierAnalyzers.addAll(getAvailableAliases());
        return identifierAnalyzers;
    }

    @Override
    public List<IIdentifierAnalyzer> getLocalIdentifiers() {
        List<IIdentifierAnalyzer> identifierAnalyzers = new ArrayList<>();
        identifierAnalyzers.addAll(getLocalNodeIdentifiers());
        identifierAnalyzers.addAll(getLocalRelationIdentifiers());
        identifierAnalyzers.addAll(getLocalPathIdentifiers());
        identifierAnalyzers.addAll(getLocalAliases());
        return identifierAnalyzers;
    }

    @Override
    public List<IIdentifierAnalyzer> getExtendableIdentifiers() {
        List<IIdentifierAnalyzer> identifierAnalyzers = new ArrayList<>();
        identifierAnalyzers.addAll(getExtendableNodeIdentifiers());
        identifierAnalyzers.addAll(getExtendableRelationIdentifiers());
        identifierAnalyzers.addAll(getExtendablePathIdentifiers());
        identifierAnalyzers.addAll(getExtendableAliases());
        return identifierAnalyzers;
    }

    @Override
    public IIdentifierAnalyzer getIdentifierAnalyzer(String name) {
        List<IIdentifierAnalyzer> identifierAnalyzers = getAvailableIdentifiers();
        for (IIdentifierAnalyzer identifierAnalyzer : identifierAnalyzers) {
            if (identifierAnalyzer.getName().equals(name)) {
                return identifierAnalyzer;
            }
        }
        return null;
    }

    @Override
    public IIdentifierAnalyzer getIdentifierAnalyzer(IIdentifier identifier) {
        return getIdentifierAnalyzer(identifier.getName());
    }

}
