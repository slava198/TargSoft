package by.vyun.targsoft.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trn {
    @Id
    private String id;
    private LocalDateTime date;
    private double amount;
    private String merchant;
    private String type;
    private String relatedTransaction;

}
