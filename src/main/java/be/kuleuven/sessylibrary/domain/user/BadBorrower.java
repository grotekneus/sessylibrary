package be.kuleuven.sessylibrary.domain.user;

public class BadBorrower implements User {
    @Override
    public int getReputation() {
        return -5;
    }
}
