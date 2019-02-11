import java.util.List;

public class UserService {

    public UserService(){
        super();
    }
    public List<User> findAll(){
        return UserRepo.getInstance().findAll();
    }

    public User findbyId(final Long id){
        return UserRepo.getInstance().findById(id);
    }
}
