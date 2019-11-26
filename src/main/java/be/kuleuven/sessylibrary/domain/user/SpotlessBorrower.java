package be.kuleuven.sessylibrary.domain.user;

public class SpotlessBorrower implements User {

    @Override
    public int getReputation() {
        return 10;
    }
}
