package org.darcstarsolutions.ironworker.twitter.thanker.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Created by mharris on 8/5/15.
 */

@Document
class TwitterFollower {

    @Id
    BigInteger id

    String fullName

    String screenName

    int followers

    int friends

    @Indexed(unique = true)
    long twitterId

    Date followDate

    boolean thanked
}
