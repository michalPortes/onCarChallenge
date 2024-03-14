package onCar.demo.services;

import onCar.demo.dto.UsersDTO;
import onCar.demo.model.UsersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServices {

    @Autowired
    private UsersDTO usersDTO;

    public UsersModel searchUserById(Long id){
        Optional<UsersModel> user = usersDTO.findById(id);
        if(user.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return user.get();
    }
}
