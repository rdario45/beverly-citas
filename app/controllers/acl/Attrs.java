package controllers.acl;

import play.libs.typedmap.TypedKey;

public class Attrs {
    public static final TypedKey<User> USER = TypedKey.<User>create("user");
}