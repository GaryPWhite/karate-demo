package mock.contract;

import com.intuit.karate.FileUtils;
import com.intuit.karate.junit4.Karate;
import com.intuit.karate.netty.FeatureServer;
import com.intuit.karate.KarateOptions;
import java.io.File;
import java.util.Collections;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

/**
 *
 * @author pthomas3
 */
@RunWith(Karate.class)
@KarateOptions(features = "classpath:mock/contract/payment-service.feature")
public class PaymentServiceContractUsingMockTest {
    
    private static FeatureServer server;
    
    @BeforeClass
    public static void beforeClass() {
        String queueName = "DEMO.CONTRACT.MOCK";
        System.setProperty("karate.env", "contract");        
        File file = FileUtils.getFileRelativeTo(PaymentServiceContractUsingMockTest.class, "payment-service-mock.feature");
        server = FeatureServer.start(file, 0, false, Collections.singletonMap("queueName", queueName));
        String paymentServiceUrl = "http://localhost:" + server.getPort();
        System.setProperty("payment.service.url", paymentServiceUrl);
        System.setProperty("shipping.queue.name", queueName);
    }
        
    @AfterClass
    public static void afterClass() {
        server.stop();        
    }     
    
}
