package by.vyun.targsoft.repository;

import by.vyun.targsoft.entity.Trn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface TrnRepo extends JpaRepository<Trn, String> {

    public List<Trn> findAll();
    public List<Trn> getAllById(String id);
    public Optional<Trn> getFirstByRelatedTransaction(String id);
    public boolean existsByRelatedTransaction(String id);
    public List<Trn> getAllByDateAfterAndDateBeforeAndMerchant(LocalDateTime after, LocalDateTime before, String merchant);


}
