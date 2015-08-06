package org.darcstarsolutions.ironworker.twitter.thanker.configurations

import org.darcstarsolutions.ironworker.twitter.thanker.repositories.mongodb.TwitterFollowerRepository
import org.darcstarsolutions.ironworker.twitter.thanker.services.TwitterService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.social.twitter.api.Twitter
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import static org.junit.Assert.assertNotNull

/**
 * Created by mharris on 8/5/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = [ApplicationConfiguration.class])
class ApplicationConfigurationTest {

    @Autowired
    private Twitter twitter

    @Autowired
    private TwitterService twitterService

    @Autowired
    private TwitterFollowerRepository twitterFollowerRepository

    @Test
    public void testTwitterExistence() throws Exception {
        assertNotNull(twitter)
    }

    @Test
    public void testTwitterServiceExistence() throws Exception {
        assertNotNull(twitterService)
    }

    @Test
    public void testTwitterFollowerRepositoryExistence() throws Exception {
        assertNotNull(twitterFollowerRepository)
    }
}
