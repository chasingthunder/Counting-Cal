package br.com.fiap.epiccountingcal.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public void addScore(User user, Integer calorias) {
        var optional = repository.findById(user.getId());
        if (optional.isEmpty())
            throw new RuntimeException("usuário não encontrado");

        var userDb = optional.get();
        userDb.setCalorias(userDb.getCalorias() + calorias);
        repository.save(userDb);
    }

    public static void addCalorias(User convert, Integer calorias) {
    }
    
}