package Repository;

import Entity.User;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserRepo {

    private static final UserRepo INSTANCE = new UserRepo();
    private final Map<Long, User> usersById;

    public static UserRepo getInstance(){
        return INSTANCE;
    }

    private UserRepo(){
        super();

        this.usersById = new LinkedHashMap<Long, User>();

        final User prod1 = new User("Smob", "Floss", 88);
        final User prod2 = new User("Iris", "West", 28);
        final User prod3 = new User("Titan", "Sanders",3);

        this.usersById.put(prod1.getId(), prod1);
        this.usersById.put(prod2.getId(), prod2);
        this.usersById.put(prod3.getId(), prod3);
    }

    public List<User> findAll(){
        return new ArrayList<User>(this.usersById.values());
    }

    public User findById(final Long id){
        return this.usersById.get(id);
    }
}
