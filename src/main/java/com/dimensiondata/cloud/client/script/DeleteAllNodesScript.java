package com.dimensiondata.cloud.client.script;

import com.dimensiondata.cloud.client.*;
import com.dimensiondata.cloud.client.http.CallableDeletedState;
import com.dimensiondata.cloud.client.model.NodeType;
import com.dimensiondata.cloud.client.model.Nodes;

import java.util.List;

import static com.dimensiondata.cloud.client.script.Script.*;

public class DeleteAllNodesScript implements NetworkDomainScript
{
    @Override
    public void execute(Cloud cloud, String networkDomainId)
    {
        Filter filter = new Filter(new Param(NodeService.PARAMETER_NETWORKDOMAIN_ID, networkDomainId));
        Nodes nodes = cloud.node().listNodes(PAGE_SIZE, 1, OrderBy.EMPTY, filter);
        println("Nodes to delete: " + nodes.getTotalCount());
        while (nodes.getTotalCount() > 0)
        {
            deleteNodes(cloud, nodes.getNode());
            nodes = cloud.node().listNodes(PAGE_SIZE, 1, OrderBy.EMPTY, filter);
        }
    }

    private static void deleteNodes(Cloud cloud, List<NodeType> nodes)
    {
        // check all items are in a NORMAL state
        for (NodeType node : nodes)
        {
            if (!node.getState().equals(NORMAL_STATE))
            {
                throw new RuntimeException("Node not in NORMAL state: " + node.getId());
            }
        }

        for (NodeType node : nodes)
        {
            cloud.node().deleteNode(node.getId());
            awaitUntil("Deleting Node " + node.getId(), new CallableDeletedState(cloud.node(), "node", node.getId()));
        }
    }
}
