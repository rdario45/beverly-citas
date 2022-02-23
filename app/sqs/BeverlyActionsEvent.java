package sqs;

import acl.types.BeverlyAction;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class BeverlyActionsEvent {

    private Map<String, List<BeverlyAction>> actions = new HashMap<>();

    @Inject
    public BeverlyActionsEvent() {
    }

    public void update(String eventName, Object... args) {
        actions.get(eventName).stream().forEach(beverlyAction -> {
            beverlyAction.execute(args);
        });
    }
}
