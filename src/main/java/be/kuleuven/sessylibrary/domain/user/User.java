package be.kuleuven.sessylibrary.domain.user;

public interface User {

    public static final String USER_KEY = "user";

    /**
     * gets current user reputation
     * @return if higher, chances of lending are higher. If lower, you might have had lots of things overdue...
     */
    int getReputation();

}
