package com.energygrid.user_service;

import com.energygrid.common.models.Status;
import com.energygrid.common.models.User;
import com.energygrid.common.utils.AuthenticationUtils;
import com.energygrid.common.utils.CsvValues;
import com.energygrid.common.utils.RandomString;
import com.energygrid.user_service.repositories.StatusRepository;
import com.energygrid.user_service.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}, scanBasePackages = {"com.energygrid"})
@ComponentScan({"com.EnergyGrid.common","com.EnergyGrid.user_service"})
public class UserServiceApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, StatusRepository statusRepository){
        return args -> {

            AuthenticationUtils auth = new AuthenticationUtils();
            RandomString rdm = new RandomString();
            BufferedReader reader = new BufferedReader(new FileReader("enexis_electricity_01012010_mod.csv"));
            // Do one readLine to skip the first line of column headers
            reader.readLine();
            String value1 = reader.readLine();
            String value2 = reader.readLine();
            String[] data1 = value1.split(",");
            String[] data2 = value2.split(",");

            User user1 = new User("victor","victory",auth.encode("test2"),"test@test.com", "0773077070", "0612345678", data1[CsvValues.ZIPCODE.getValue()], data1[CsvValues.STREET.getValue()], data1[CsvValues.CITY.getValue()], data1[CsvValues.HOUSE_NUMBER.getValue()],"123456");

            User user2 = new User("Piet","Pieters",auth.encode("test1"),"test@test.nl", "0773086060", "0687654321",data2[CsvValues.ZIPCODE.getValue()],data2[CsvValues.STREET.getValue()], data2[CsvValues.CITY.getValue()], data2[CsvValues.HOUSE_NUMBER.getValue()], rdm.getAlphaNumericString(8));

            User adminUser = new User("ad","min",auth.encode("yeet"),"admin@email.com","0773086090","0623456789","DQ9001","Adminstreet","Admincity","1","007");



            Status status1 = new Status();
            Status status2 = new Status();
            Status status3 = new Status();


            status1.setDate(new Date());
            status1.setConsumption(Double.parseDouble(data1[CsvValues.CONSUME.getValue()]));
            status1.setProduction(Double.parseDouble(data1[CsvValues.PRODUCE.getValue()]));

            status2.setDate(new Date());
            status2.setConsumption(Double.parseDouble(data2[CsvValues.CONSUME.getValue()]));
            status2.setProduction(Double.parseDouble(data2[CsvValues.PRODUCE.getValue()]));


            status3.setDate(new Date());
            status3.setConsumption(111);
            status3.setProduction(133);

            Set<Status> status_dashboard1 = new HashSet<>();
            Set<Status> status_dashboard2 = new HashSet<>();

            status_dashboard1.add(status1);
            status_dashboard2.add(status2);
            status_dashboard2.add(status3);


            user1.setStatus(status_dashboard1);
            user2.setStatus(status_dashboard2);

            statusRepository.save(status1);
            statusRepository.save(status2);
            statusRepository.save(status3);

            user1 = userRepository.save(user1);
            user2 = userRepository.save(user2);
            adminUser = userRepository.save(adminUser);
            System.out.println("Id of user1 is: " + user1.getId() + "CustomerCode:" + user1.getCustomerCode());
            System.out.println("Id of user2 is: " + user2.getId()+ "CustomerCode:" + user2.getCustomerCode());
            System.out.println("Id of adminUser is: " + adminUser.getId() + "CustomerCode:" + adminUser.getCustomerCode());

        };
    }

}
