import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class TwitterFeed {

    private static final String ConsumerKey = "DpuAmpfFAzec1zL49n8es6OwQ";
    private static final String ConsumerSecret = "uVj12J80Pk7neTN0rKbtXKhc3qf4DwZuta5nZphO6ZWM9Esmby";
    private static final String AccessToken = "491420252-FojFm7ETNPMBt3OlcyVFA2djV3tygzCgUqPwWxt4";
    private static final String AccessTokenSecret = "FnQka81NqwHDCBpjYiEXLYb5KLKqXQJvsUQZzN2H8i6h9";

    private static Twitter twitter;

    public static void main(String[] args) {
        twitter = createTwitterConfig().getInstance();
        //showTimeLine();
        //queryFeed("@TFL");
        //getUserTweets("@TheSurreySaint");
        getUserTweetsByFilter1("@TheSurreySaint","#Thamestink");
    }

    public static TwitterFactory createTwitterConfig() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(ConsumerKey)
                .setOAuthConsumerSecret(ConsumerSecret)
                .setOAuthAccessToken(AccessToken)
                .setOAuthAccessTokenSecret(AccessTokenSecret);

        return new TwitterFactory(cb.build());
    }

    public static void showTimeLine() {
        try {

            User user = twitter.verifyCredentials();
            List<Status> statuses = twitter.getHomeTimeline();
            System.out.println("Showing @" + user.getScreenName() + "'s home timeline.");
            System.out.println("Showing @" + user.getId() + "'s home timeline.");
            for (Status status : statuses) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }
        } catch (
                TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
        }
    }

    public static void getUserTweets(String screen_name) {
        try {
            User user = twitter.verifyCredentials();
            Paging paging = new Paging(1, 30);
            List<Status> statuses = twitter.getUserTimeline(screen_name,paging);
            for (Status status : statuses) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }
        } catch (
                TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
        }
    }


    public static void queryFeed(String keyword) {
        Query query = new Query(keyword);
        QueryResult result = null;
        try {
            result = twitter.search(query);
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        for (Status status : result.getTweets()) {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        }
    }


    public static void getUserTweetsByFilter1(String screen_name, String keyword) {
        try {
            User user = twitter.verifyCredentials();
            Paging paging = new Paging(1, 30);
            List<Status> statuses = twitter.getUserTimeline(screen_name,paging);

            for (Status status : statuses) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());

                Query query = new Query(keyword);
                QueryResult result = null;
                try {
                    result = twitter.search(query);
                } catch (TwitterException e) {
                    e.printStackTrace();
                }

                for (Status Filter1 : result.getTweets()) {
                    System.out.println("@" + Filter1.getUser().getScreenName() + ":" + Filter1.getText());
                }


            }
        } catch (
                TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
        }
    }



}

