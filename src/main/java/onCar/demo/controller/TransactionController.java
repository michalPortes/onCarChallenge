package onCar.demo.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import onCar.demo.dto.TransactionDTO;
import onCar.demo.dto.UsersDTO;
import onCar.demo.event.UriServices;
import onCar.demo.model.TransactionModel;
import onCar.demo.model.UsersModel;
import onCar.demo.services.TransactionServices;
import onCar.demo.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionDTO transactionDTO;

    @Autowired
    private UsersServices usersServices;

    @Autowired
    private TransactionServices transactionServices;

    private ApplicationEventPublisher publisher;

    @GetMapping
    public ResponseEntity<?> getAllTransaction(){
        List<TransactionModel> transactions = transactionDTO.findAll();

        return !transactions.isEmpty() ? ResponseEntity.ok(transactions) : ResponseEntity.ok("Empty list");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> postTransaction(@PathVariable Long id) {
//        veriica se o usuario existe
        UsersModel user =  usersServices.searchUserById(id);
        String validTransaction = transactionServices.randomSimulator(user);

        return ResponseEntity.ok().body(validTransaction);
    };
}
