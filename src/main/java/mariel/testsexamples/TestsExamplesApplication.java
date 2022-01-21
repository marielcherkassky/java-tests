package mariel.testsexamples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
// scan for configuration properties instead of putting component annotation of them(Spring 2.2+)
@ConfigurationPropertiesScan
public class TestsExamplesApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestsExamplesApplication.class, args);
    }

}
