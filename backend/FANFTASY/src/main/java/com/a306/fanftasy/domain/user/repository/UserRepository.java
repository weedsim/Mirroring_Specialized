package com.a306.fanftasy.domain.user.repository;

import com.a306.fanftasy.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByAddress(String address);


    List<User> findByRegArtistOrderByRegDateDesc(User regArtist);

    List<User> findByOwnerOrderByTransactionTimeDesc(User owenr);
}
