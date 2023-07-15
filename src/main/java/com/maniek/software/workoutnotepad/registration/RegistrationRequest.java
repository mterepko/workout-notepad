package com.maniek.software.workoutnotepad.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


public record RegistrationRequest(String username, String firstName, String email, String password) {

}