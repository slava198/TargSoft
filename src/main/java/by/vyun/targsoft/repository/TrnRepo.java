package by.vyun.targsoft.repository;

import by.vyun.targsoft.entity.Trn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface TrnRepo extends JpaRepository<Trn, String> {
    List<Trn> findAll();

    boolean existsByRelatedTransaction(String id);

    List<Trn> getAllByDateAfterAndDateBeforeAndMerchant(LocalDateTime after, LocalDateTime before, String merchant);

}
