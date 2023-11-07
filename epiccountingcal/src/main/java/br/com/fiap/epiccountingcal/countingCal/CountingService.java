package br.com.fiap.epiccountingcal.countingCal;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import br.com.fiap.epiccountingcal.user.User;
import br.com.fiap.epiccountingcal.user.UserService;


@Service
public class CountingService {

    @Autowired
    CountingRepository repository;
    public List<Counting> findAll(){
        return repository.findAll();

    }
    public boolean delete(Long id) {
        var counting =  repository.findById(id);
        if (counting.isEmpty()) return false;
        repository.deleteById(id);
        return true;

    }
    public void save(Counting counting) {
        repository.save(counting);
    }

    public void decrement(Long id) {
        var optional = repository.findById(id);
        if (optional.isEmpty())
            throw new RuntimeException("alimento não encontrado");

        var counting = optional.get();
        if (counting.getCalorias() == 0)
            throw new RuntimeException("as calorias não podem ser negativas");

        counting.setCalorias(counting.getCalorias() - 1);
        repository.save(counting);
    }

    public void increment(Long id) {
        var optional = repository.findById(id);
        if (optional.isEmpty())
            throw new RuntimeException("alimento não encontrado");

        var counting = optional.get();
        if (counting.getCalorias() == 1000)
            throw new RuntimeException("as calorias não podem ser maior que 1.000");

        counting.setCalorias(counting.getCalorias() + 1);
        
         if (counting.getCalorias() == 1000){
            var user = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserService.addCalorias(User.convert( user), counting.getCalorias());
        }

        repository.save(counting);
    }
    public void catchCounting(Long id, User convert) {
    }



}
