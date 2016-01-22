package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.http.PoolServiceImpl;
import com.dimensiondata.cloud.client.model.CreatePool;
import com.dimensiondata.cloud.client.model.ResponseType;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

public class PoolServiceDemo extends AbstractServiceTest
{

    public static final String SEEDED_ORG_ID_2 = "seeded-org-id-1";
    public static final String SEEDED_TEST_USER_OF_SEEDED_ORG_USERNAME = "seeded-test-user-1";
    public static final String SEEDED_TEST_USER_OF_SEEDED_ORG_PASSWORD = "Password1!";
    private PoolService poolService;

    @Before
    public void setup()
    {
        UserSession.set(new User(SEEDED_ORG_ID_2, SEEDED_TEST_USER_OF_SEEDED_ORG_USERNAME, SEEDED_TEST_USER_OF_SEEDED_ORG_PASSWORD));
        poolService = new PoolServiceImpl("http://localhost:8081/oec");
    }

    @Test
    public void testCreatePool()
    {
        CreatePool pool = new CreatePool();
        pool.setNetworkDomainId("3ca806bb-924b-4964-bb6c-cd36dcbb0857"/*"8c6b8002-1e1d-4901-b001-c618436f9703"*/);
        pool.setName("Test1");
        pool.setLoadBalanceMethod("ROUND_ROBIN");
        pool.setServiceDownAction("NONE");
        pool.setSlowRampTime(BigInteger.valueOf(10));
        ResponseType responseType = poolService.createPool(pool);
        responseType.getResponseCode();
    }

//    @Test
//    public void testListPool(){
//        Pools pools = poolService.listPools(1, 1, OrderBy.EMPTY, Filter.EMPTY);
//        List<PoolType> poolTypes = pools.getPool();
//    }


}
