package com.example.resource;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;
import com.example.Main;
import com.example.entity.Account;
import com.example.entity.Transfer;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.*;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static javax.ws.rs.client.Entity.entity;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.junit.Assert.assertEquals;

@RunWith(ConcurrentTestRunner.class)
public class TransferResourceTest {
    private static final int NUM_ACCOUNTS = 10;
    private static final int NUM_THREADS = 4;
    private static final int NUM_TRANSFERS = 1000;
    private static final int START_BALANCE = 50000;

    private HttpServer server;
    private WebTarget target;

    private List<Long> accountIds = new ArrayList<>();
    private Long initialOverallBalance = 0L;
    private Random random = new Random(System.currentTimeMillis());

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        target = c.target(Main.BASE_URI);

        IntStream.range(0, NUM_ACCOUNTS).forEach((n) -> {
            Account acc = new Account().setName("User " + n).setBalance((long) START_BALANCE);
            acc = target.path("/account").request().post(entity(acc, APPLICATION_JSON), Account.class);
            accountIds.add(acc.getId());
        });

        initialOverallBalance = target.path("/stats/overallbalance").request().get(Long.class);
    }

    /**
     * Test whether the transfer service works properly
     * when receiving loads of concurrent requests
     */
    @Test
    @ThreadCount(NUM_THREADS)
    public void testTransfer() throws Exception {
        IntStream.range(0, NUM_TRANSFERS).forEach(value -> {
            Transfer transfer = new Transfer();

            int randomAccountIdx = random.nextInt(accountIds.size());
            long fromAccountId = accountIds.get(randomAccountIdx);
            long toAccountId = accountIds.get((randomAccountIdx + 1) % accountIds.size());

            transfer.setFromAccountId(fromAccountId);
            transfer.setToAccountId(toAccountId);
            transfer.setAmount((long) random.nextInt(START_BALANCE));
            try {
                target.path("/transfer").request().post(entity(transfer, APPLICATION_JSON), Transfer.class);
            } catch (Exception ignored) {
            }
        });
    }

    @After
    public void after() throws Exception {
        Long overallBalance = target.path("/stats/overallbalance").request().get(Long.class);
        assertEquals(initialOverallBalance, overallBalance);

        server.shutdownNow();
    }
}
