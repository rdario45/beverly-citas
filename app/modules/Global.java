package modules;

import acl.BeverlyDB;
import acl.BeverlySNS;
import com.google.inject.AbstractModule;
import sqs.ActionsEvent;

public class Global extends AbstractModule {

    public Global() { }

    @Override
    public void configure() {
        bind(BeverlyDB.class).asEagerSingleton();
        bind(BeverlySNS.class).asEagerSingleton();
        bind(ActionsEvent.class).asEagerSingleton();
    }
}