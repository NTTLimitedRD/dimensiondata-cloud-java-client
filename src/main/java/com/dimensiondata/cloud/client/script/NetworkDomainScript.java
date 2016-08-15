package com.dimensiondata.cloud.client.script;

import com.dimensiondata.cloud.client.Cloud;

public interface NetworkDomainScript
{
    void execute(Cloud cloud, String networkDomainId);
}
