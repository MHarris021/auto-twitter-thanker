package org.darcstarsolutions.ironworker.twitter.thanker.repositories.mongodb

import org.darcstarsolutions.ironworker.twitter.thanker.domain.TwitterFollower
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

/**
 * Created by mharris on 8/5/15.
 */
interface TwitterFollowerRepository extends MongoRepository<TwitterFollower, BigInteger> {

    @Query
    List<TwitterFollower> findAllByThanked(boolean thanked)

    @Query
    TwitterFollower findByTwitterId(long twitterId)


}
