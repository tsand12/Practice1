package practice1;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User save(User user);
    /*User findById(Long id){
        return user;
    }*/

}
