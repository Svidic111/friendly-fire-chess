package com.ffirechess.io.repositories;

import com.ffirechess.io.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    UserEntity findByUserId(String userId);

    UserEntity findById(long id);

    UserEntity findByNick(String nick);

    UserEntity findUserByEmailVerificationToken(String token);
}
