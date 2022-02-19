package myApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(UserRepository repository) {

    return args -> {
    	log.info("Preloading " + repository.save(new User("Bilbo", "Baggins@gmail.com", "517-373-3776", 3 )));
    	log.info("Preloading " + repository.save(new User("Frodo", "Larry@gmail.com", "514-780-2887", 1)));
    };
  }
}