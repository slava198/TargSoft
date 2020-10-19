package by.vyun.targsoft.service;


import by.vyun.targsoft.entity.Trn;
import by.vyun.targsoft.repository.TrnRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class DatabaseService {
    @Autowired
    TrnRepo trnRepo;

    @Value("${input}")
    private String resourceFile;
    @Value("${fromDate}")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime fromDate;
    @Value("${toDate}")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime toDate;
    @Value("${merchant}")
    private String merchant;


    public void showTrnStatistic() {
        List<Trn> resultList = trnRepo.getAllByDateAfterAndDateBeforeAndMerchant(fromDate, toDate, merchant);
        resultList = resultList
                .stream()
                .filter(trn -> !trnRepo.existsByRelatedTransaction((trn.getId())))
                .collect(Collectors.toList());
        double summaryAmount = resultList.stream().reduce(0d, (totalAmount, t) -> totalAmount + t.getAmount(), Double::sum);
        int count = resultList.size();
        System.out.println("Number of transactions = " + count);
        System.out.printf("Average Transaction Value = %1.2f\n", summaryAmount / count);
    }


    public void dbLoad() throws FileNotFoundException {
        try (Scanner reader = new Scanner(new FileReader(getClass().getClassLoader().getResource(resourceFile).getFile()))) {
            while (reader.hasNextLine()) {
                Trn trn = parse(reader.nextLine());
                trnRepo.save(trn);
            }
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
