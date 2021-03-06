package me.nglow.jwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

//    @JsonIgnore
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 50, unique = true)
    private String username;

    @JsonIgnore
    @Column(length = 100)
    private String password;

    @Column(length = 50)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private UserState state;

    public static User of(String username, String password, String nickname) {
        var user = new User();
        user.username = username;
        user.password = password;
        user.nickname = nickname;
        user.activateUser();

        return user;
    }

    private void activateUser() {
        this.state = UserState.ACTIVATED;
    }

}
