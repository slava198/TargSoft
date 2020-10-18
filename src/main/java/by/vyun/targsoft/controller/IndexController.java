package by.vyun.targsoft.controller;


import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

@Controller
public class IndexController {

    private ResourceLoader resourceLoader;


    public String start() throws IOException {
        Resource resource  = resourceLoader.getResource("classpath:\\input.txt");
        File file = resource.getFile();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()){
                System.out.println(scanner.nextLine());
            }
        }








        return "Welcome";



    }

}
