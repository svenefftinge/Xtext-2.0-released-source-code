package xtend.tutorial.basics;

import java.util.ArrayList;
import java.util.HashMap;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import xtend.tutorial.util.NetNode;

@SuppressWarnings("all")
public class Xtend10_CreateFunctions extends TestCase {
  
  public void testCreateFunctions() {
    {
      NetNode _netNode = new NetNode();
      final NetNode nodeA = _netNode;
      nodeA.setName("A");
      NetNode _netNode_1 = new NetNode();
      final NetNode nodeB = _netNode_1;
      nodeB.setName("B");
      NetNode _netNode_2 = new NetNode();
      final NetNode nodeC = _netNode_2;
      nodeC.setName("C");
      NetNode _netNode_3 = new NetNode();
      final NetNode nodeD = _netNode_3;
      nodeD.setName("D");
      ArrayList<NetNode> _newArrayList = CollectionLiterals.<NetNode>newArrayList(nodeD, nodeC);
      nodeA.setReferences(_newArrayList);
      ArrayList<NetNode> _newArrayList_1 = CollectionLiterals.<NetNode>newArrayList(nodeA, nodeB);
      nodeB.setReferences(_newArrayList_1);
      ArrayList<NetNode> _newArrayList_2 = CollectionLiterals.<NetNode>newArrayList(nodeA, nodeB, nodeD);
      nodeC.setReferences(_newArrayList_2);
      ArrayList<NetNode> _newArrayList_3 = CollectionLiterals.<NetNode>newArrayList(nodeB, nodeC);
      nodeD.setReferences(_newArrayList_3);
      NetNode _copyNet = this.copyNet(nodeA);
      final NetNode copyOfNodeA = _copyNet;
      NetNode _copyNet_1 = this.copyNet(nodeB);
      final NetNode copyOfNodeB = _copyNet_1;
      Assert.assertNotSame(nodeA, copyOfNodeA);
      Iterable<NetNode> _references = copyOfNodeB.getReferences();
      NetNode _head = IterableExtensions.<NetNode>head(_references);
      Assert.assertSame(_head, copyOfNodeA);
      Iterable<NetNode> _references_1 = copyOfNodeB.getReferences();
      Iterable<NetNode> _tail = IterableExtensions.<NetNode>tail(_references_1);
      NetNode _head_1 = IterableExtensions.<NetNode>head(_tail);
      Assert.assertSame(_head_1, copyOfNodeB);
    }
  }
  
  private final HashMap<ArrayList<?>,NetNode> _createCache_copyNet = new HashMap<ArrayList<?>,NetNode>();
  
  public NetNode copyNet(final NetNode toCopy) {
    final ArrayList<?>_cacheKey = CollectionLiterals.newArrayList(toCopy);
    NetNode result;
    synchronized (_createCache_copyNet) {
      if (_createCache_copyNet.containsKey(_cacheKey)) {
        return _createCache_copyNet.get(_cacheKey);
      }
      NetNode _netNode = new NetNode();
      result = _netNode;
      _createCache_copyNet.put(_cacheKey, result);
    }
    {
      String _name = toCopy.getName();
      result.setName(_name);
      Iterable<NetNode> _references = toCopy.getReferences();
      final Function1<NetNode,NetNode> _function = new Function1<NetNode,NetNode>() {
          public NetNode apply(final NetNode node) {
            NetNode _copyNet = Xtend10_CreateFunctions.this.copyNet(node);
            return _copyNet;
          }
        };
      Iterable<NetNode> _map = IterableExtensions.<NetNode, NetNode>map(_references, _function);
      result.setReferences(_map);
    }
    return result;
  }
}