package com.maniek.software.workoutnotepad.user;

import com.maniek.software.workoutnotepad.exercise.Exercise;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Getter
@Setter
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private boolean isPrivate;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.MERGE,
                                             CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Exercise> exerciseList;

    public User(String username, String name, String email, String password, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.isPrivate = true;
        this.userRole = userRole;
        this.exerciseList = null;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());

        return Collections.singletonList(authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addExercise(Exercise tempExercise){
        if (exerciseList == null) {
            exerciseList = new ArrayList<>();
        }

        exerciseList.add(tempExercise);

        tempExercise.setUser(this);
    }
}
