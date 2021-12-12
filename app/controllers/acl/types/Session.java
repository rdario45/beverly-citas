package controllers.acl.types;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public class Session {
    public String accessToken;
    public String lastRefresh;
    public User user;

    public Session(Map<String, AttributeValue> map) {
        this.accessToken = map.get("access_token").s();
        this.lastRefresh = map.get("last_refresh").n();
        this.user = new User(
                map.get("name").s(),
                map.get("telephone").s()
        );
    }

    public User getUser() {
        return this.user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getLastRefresh() {
        return lastRefresh;
    }

    public void setLastRefresh(String lastRefresh) {
        this.lastRefresh = lastRefresh;
    }
}