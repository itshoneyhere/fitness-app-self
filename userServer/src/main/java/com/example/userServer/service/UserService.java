package com.example.userServer.service;

import com.example.userServer.dtos.RequestUser;
import com.example.userServer.dtos.ResponseUsers;
import com.example.userServer.mapper.Mapper;
import com.example.userServer.model.Users;
import com.example.userServer.repo.UsersRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UsersRepo usersRepo;



    public Page<Users> getAllUsers(int page,
                                   int size,
                                   String sortBy)
    {
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));

        return  usersRepo.findAll(pageable);
    }

    public ResponseUsers createOneUser(String userId,RequestUser requestUser){
        Users users = Mapper.requestToEntity(requestUser);
        users.setUserId(userId);
        return Mapper.entityToResponse(usersRepo.save(users));
    }


    public ResponseUsers getOneUser(String id) {
        return Mapper.entityToResponse(usersRepo.getReferenceById(Long.valueOf(id)));
    }

    public List<ResponseUsers> createUsersBulk(List<RequestUser> requestUsers) {

        List<Users> usersList = requestUsers.stream().map(Mapper::requestToEntity).toList();

        return usersRepo.saveAll(usersList).stream().map(Mapper::entityToResponse).toList();
    }
}
