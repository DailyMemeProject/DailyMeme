package com.dailymeme.dailymeme.domain.user.repository;

import com.dailymeme.dailymeme.domain.user.dto.info.UserInfoListResponseDto;
import com.dailymeme.dailymeme.domain.user.entity.User;
import com.dailymeme.dailymeme.global.exception.ExceptionType;
import com.dailymeme.dailymeme.global.exception.NotFoundException;
import org.hibernate.annotations.NotFound;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    default User findByEmailOrElseThrow(String email) {
        return findByEmail(email).orElseThrow(()-> new NotFoundException(ExceptionType.USER_NOT_FOUND));
    }

    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(()-> new NotFoundException(ExceptionType.USER_NOT_FOUND));
    }

    @Query("select new com.dailymeme.dailymeme.domain.user.dto.info.UserInfoListResponseDto(u.userName, u.email, u.mailSendAgree) " +
            "from User u " +
            "where u.userAuth = 'USER' ")
    List<UserInfoListResponseDto> findUserByUserAuth();


}
