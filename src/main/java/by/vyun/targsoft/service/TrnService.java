//package by.vyun.targsoft.service;
//
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//@Service
//public class TrnService {
//    @Value("${fromDate}")
//    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
//    private LocalDateTime fromDate;
//
//    @Value("${toDate}")
//    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
//    private LocalDateTime toDate;
//
//    @Value("${merchant}")
//    private String merchant;
//
//
//
//
//
//    public String showData() {
//        return fromDate.toString();
//    }
//
//    public String showData(String arg) {
//        return merchant + arg;
//    }
//
//
//
//}
