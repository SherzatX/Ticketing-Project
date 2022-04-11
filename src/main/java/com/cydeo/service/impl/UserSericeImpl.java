package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //  diff with @Componnent : same but @service has diff impl.
public class UserSericeImpl extends AbstractMapService <UserDTO ,String> implements UserService {
    @Override
    public UserDTO save(UserDTO object) {
        return super.save(object.getUserName(),object);
    }

    @Override
    public List<UserDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(String id) {
       super.deleteById(id);
    }


    @Override
    public void delete(UserDTO object) {

    }

    @Override
    public UserDTO findById(String id) {
        return super.findById(id);
    }
}