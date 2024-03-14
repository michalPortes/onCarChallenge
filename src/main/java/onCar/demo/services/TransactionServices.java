package onCar.demo.services;

import onCar.demo.model.UsersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TransactionServices {


    @Autowired
    private UsersServices usersServices;

    public String randomSimulator(UsersModel user) {

        Random random = new Random();
        int randomNumber = random.nextInt(999 - 1) + 1;
        String finalStatus = new String();
        if(randomNumber > 0 && randomNumber <= 299){
            finalStatus = "O cliente " + user.getName() + " foi reprovado";
        }
        if(randomNumber >= 300 && randomNumber <= 599){
            finalStatus = "O cliente " + user.getName() + " precisa de 70% de entrada, 30% do comprometimento da renda";
        }
        if(randomNumber >= 600 && randomNumber <= 799){
            finalStatus = "O cliente " + user.getName() + " precisa de 50% de entrada, 25% do comprometimento da renda";
        }
        if(randomNumber >= 800 && randomNumber <= 950){
            finalStatus = "O cliente " + user.getName() + " precisa de 30% de entrada, 20% do comprometimento da renda";
        }
        if( randomNumber >= 951 && randomNumber <= 999){
            finalStatus = "O cliente " + user.getName() + " obteve 100% de financiamento, taxa zero.";
        }

        return finalStatus;
    }
;}
