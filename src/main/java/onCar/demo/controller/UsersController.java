package onCar.demo.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import onCar.demo.dto.UsersDTO;
import onCar.demo.event.UriServices;
import onCar.demo.model.UsersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersDTO usersDTO;

    @Autowired
    private ApplicationEventPublisher publisher;

//    Find All Users
    @GetMapping
    public ResponseEntity<?> findAll(){
        List<UsersModel> listUsers = usersDTO.findAll();
        return !listUsers.isEmpty() ? ResponseEntity.ok(listUsers) : ResponseEntity.ok("Empty list");
    }
//    Find User By ID
    @GetMapping("/{id}")
    public ResponseEntity<UsersModel> findUserById(@PathVariable Long id){
        Optional<UsersModel> user = usersDTO.findById(id);
        return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.notFound().build();
    }
//    Create
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UsersModel user, HttpServletResponse response){
        UsersModel newUser = usersDTO.save(user);
        publisher.publishEvent(new UriServices(this, response, newUser.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
//    Delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id){usersDTO.deleteById(id);};
}
