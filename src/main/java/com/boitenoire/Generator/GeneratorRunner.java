package com.boitenoire.Generator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("generator")
public class GeneratorRunner implements CommandLineRunner {

        private final EventGenerator eventGenerator;

        public GeneratorRunner(EventGenerator eventGenerator) {
            this.eventGenerator = eventGenerator;
        }

        @Override
        public void run(String... args) {
            eventGenerator.generateEvents();
        }

}
