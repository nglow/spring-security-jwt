package me.nglow.jwt.repository;

import me.nglow.jwt.entity.User;
import me.nglow.jwt.entity.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {
    @Query(value = "select u from UserAuthority u join fetch u.authority where u.user = :user")
    List<UserAuthority> findByUser(@Param("user") User user);

}
