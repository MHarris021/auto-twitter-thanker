package org.darcstarsolutions.ironworker.twitter.thanker.configurations

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.social.twitter.api.Twitter
import org.springframework.social.twitter.api.impl.TwitterTemplate

/**
 * Created by mharris on 8/5/15.
 */

@Configuration
@EnableConfigurationProperties(value = [TwitterConfiguration.class])
@EnableAutoConfiguration()
@EnableMongoRepositories(basePackages = "org.darcstarsolutions.ironworker.twitter.thanker.repositories.mongodb")
@ComponentScan(basePackages = "org.darcstarsolutions.ironworker.twitter.thanker.services")
class ApplicationConfiguration {

    @Autowired
    private TwitterConfiguration configuration;

    @Bean
    public Twitter twitter() {
        Twitter twitter = new TwitterTemplate(configuration.getConsumerKey(), configuration.getConsumerSecret(), configuration.getAccessToken(), configuration.getAccessTokenSecret());
        return twitter;
    }

}
