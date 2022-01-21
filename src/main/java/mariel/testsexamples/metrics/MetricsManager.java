package mariel.testsexamples.metrics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
// This bean doesn't really do anything useful, was created for testing
public class MetricsManager {

    public int increment(String metric){
        log.info("Incrementing metric {}",metric);
        return 1;
    }
}
