package by.vyun.targsoft.service;


import by.vyun.targsoft.entity.Trn;
import by.vyun.targsoft.repository.TrnRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

//import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class DatabaseService {
    @Value("${fromDate}")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime fromDate;

    @Value("${toDate}")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime toDate;

    @Value("${merchant}")
    private String merchant;

//    @Value("classpath:input.txt")
//    Resource resourceFile;

    @Autowired
    TrnRepo trnRepo;


    public List<Trn> trnStatistic (LocalDateTime from, LocalDateTime to, String merchant) {

        System.out.println(trnRepo.findAll());

        List<Trn> resultList = trnRepo.getAllByDateAfterAndDateBeforeAndMerchant(from, to, merchant);
        resultList = resultList
                .stream()
                .filter(trn -> !trnRepo.existsByRelatedTransaction((trn.getId())))
                .collect(Collectors.toList());
        double summaryAmount = resultList.stream().reduce(0d, (totalAmount, t) -> totalAmount + t.getAmount(), Double::sum);
        int count = resultList.size();
        System.out.println("Number of transactions = " + count);
        System.out.printf("Average Transaction Value = %1.2f\n", summaryAmount / count);

        return resultList;
    }

    public void dbLoad() throws FileNotFoundException {

        try (Scanner reader = new Scanner(new FileReader(getClass().getClassLoader().getResource("input.txt").getFile())) ) {
            while (reader.hasNextLine()) {
                Trn trn = parse(reader.nextLine());
                System.out.println(trn);
                trnRepo.save(trn);
            }
            //
            // System.out.println(trnRepo.getAllById("AKNBVHMN"));
            System.out.println(trnStatistic(fromDate, toDate, merchant));
        }
    }

    private Trn parse(String line) {
        String[] trnFields = line.split(",");
        Trn transaction = Trn.builder()
                .id(trnFields[0].trim())
                .date(LocalDateTime.parse(trnFields[1].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
                .amount(Float.parseFloat(trnFields[2].trim()))
                .merchant(trnFields[3].trim())
                .type(trnFields[4].trim())
                .build();
        if (transaction.getType().equals("REVERSAL")) {
            transaction.setRelatedTransaction(trnFields[5].trim());
        }
        return transaction;
    }





}
