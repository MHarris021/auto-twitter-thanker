package org.darcstarsolutions.ironworker.twitter.thanker

import org.darcstarsolutions.ironworker.twitter.thanker.services.TwitterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.social.twitter.api.Twitter

/**
 * Created by mharris on 8/5/15.
 */

@SpringBootApplication
class Application implements CommandLineRunner {

    @Autowired
    private Twitter twitter

    @Autowired
    TwitterService twitterService

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args)
    }

    @Override
    void run(String... args) throws Exception {
        twitterService.updateFollowers()
        twitterService.thankNewFollowers()
    }
}
