package com.commerce.jpa;

import com.commerce.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false, unique = true)
    private String encryptedPwd;

    public UserEntity(final UserDto userDto) {
        this.email = userDto.getEmail();
        this.name = userDto.getName();
        this.userId = userDto.getUserId();
        this.encryptedPwd = userDto.getEncryptedPwd();
    }
}
