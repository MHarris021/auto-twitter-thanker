package org.darcstarsolutions.ironworker.twitter.thanker.services

import org.darcstarsolutions.ironworker.twitter.thanker.domain.TwitterFollower
import org.darcstarsolutions.ironworker.twitter.thanker.repositories.mongodb.TwitterFollowerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.social.twitter.api.Tweet
import org.springframework.social.twitter.api.TweetData
import org.springframework.social.twitter.api.Twitter
import org.springframework.social.twitter.api.TwitterProfile
import org.springframework.stereotype.Service

/**
 * Created by mharris on 8/5/15.
 */

@Service
class TwitterService {

    @Autowired
    private Twitter twitter

    @Autowired
    private TwitterFollowerRepository twitterFollowerRepository

    List<TwitterProfile> getFollowers(String screenName) {
        return twitter.friendOperations().getFollowers(screenName)
    }

    List<TwitterProfile> getFollowers() {
        return twitter.friendOperations().getFollowers()
    }

    TwitterProfile getProfile(String screenName) {
        return twitter.userOperations().getUserProfile(screenName)
    }

    TwitterProfile getProfile() {
        return twitter.userOperations().getUserProfile()
    }

    boolean inRepository(TwitterProfile profile) {
        TwitterFollower follower = twitterFollowerRepository.findByTwitterId(profile.getId())
        return (follower != null)
    }

    boolean notInRepository(TwitterProfile profile) {
        return !inRepository(profile)
    }

    TwitterFollower createAndSaveFollower(TwitterProfile twitterProfile) {
        TwitterFollower follower = new TwitterFollower()
        follower.screenName = twitterProfile.getScreenName()
        follower.fullName = twitterProfile.getName()
        follower.thanked = false
        follower.followDate = new Date()
        follower.followers = twitterProfile.getFollowersCount()
        follower.friends = twitterProfile.getFriendsCount()
        follower.twitterId = twitterProfile.getId()
        follower = twitterFollowerRepository.save(follower)
        return follower
    }

    TwitterFollower thankFollower(TwitterFollower twitterFollower) {
        StringBuilder builder = new StringBuilder("@" + twitterFollower.screenName)
        builder.append(" thanks for the follow!")
        String message = builder.toString()
        TweetData tweetData = new TweetData(message)
        Tweet tweet = twitter.timelineOperations().updateStatus(tweetData)
        if (tweet != null) {
            twitterFollower.thanked = true
            twitterFollowerRepository.save(twitterFollower)
        }
    }

    List<TwitterFollower> thankNewFollowers() {
        List<TwitterFollower> followers = twitterFollowerRepository.findAllByThanked(false)
        followers.replaceAll({ follower ->
            follower = thankFollower(follower)
            return follower
        })
    }


    List<TwitterFollower> updateFollowers() {
        List<TwitterProfile> profiles = getFollowers()
        profiles.forEach({ profile ->
            createAndSaveFollower(profile)
        })
    }
}
