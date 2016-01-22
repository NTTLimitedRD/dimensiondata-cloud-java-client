package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.http.RequestException;
import com.dimensiondata.cloud.client.http.NodeServiceImpl;
import com.dimensiondata.cloud.client.model.*;

import java.math.BigInteger;

public class NodeServiceDemo
{
    public static void main(String[] args)
    {
        UserSession.set(new User("seeded-org-id-1", "seeded-test-user-1", "Password1!"));

        try
        {
            NodeService nodeService = new NodeServiceImpl("http://localhost:8081/oec");

            CreateNodeType createNode = new CreateNodeType();
            createNode.setNetworkDomainId("1c813510-216a-434d-bd07-226ee5424ca3");
            createNode.setName("abc");
            createNode.setStatus("ENABLED");
            createNode.setIpv4Address("10.5.2.19");
            createNode.setConnectionLimit(BigInteger.valueOf(100l));
            createNode.setConnectionRateLimit(BigInteger.valueOf(100l));
            ResponseType response = nodeService.createNode(createNode);
            System.out.println(response.getMessage());

            Nodes nodes = nodeService.listNodes(100, 1, OrderBy.EMPTY, Filter.EMPTY);
            for (NodeType node : nodes.getNode())
            {
                System.out.println(node.getId() + "=" + node.getName());
            }

            EditNode editNode = new EditNode();
            editNode.setId(nodes.getNode().get(1).getId());
            editNode.setStatus("DISABLED");
            editNode.setConnectionLimit(BigInteger.valueOf(10l));
            editNode.setConnectionRateLimit(BigInteger.valueOf(10l));
            response = nodeService.editNode(editNode);
            System.out.println(response.getMessage());

            NodeType node = nodeService.getNode(nodes.getNode().get(1).getId());
            System.out.println(node.getId() + "," + node.getName() + "," + node.getStatus());

            response = nodeService.deleteNode(node.getId());
            System.out.println(response.getMessage());
        }
        catch (RequestException e)
        {
            ResponseType response = e.getResponse();
            System.out.println("bad request: " + response.getMessage());
        }
    }
}