package br.com.fiap.epiccountingcal.countingCal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountingRepository extends JpaRepository<Counting, Long> {
    
}
