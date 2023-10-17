package br.com.fiap.epiccountingcal.countingCal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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

}
