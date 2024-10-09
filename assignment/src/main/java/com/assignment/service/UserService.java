package com.assignment.service;

import com.assignment.dto.SignUpDto;
import com.assignment.dto.UserDto;
import com.assignment.entity.UserEntity;
import com.assignment.exception.ResourceNotFoundException;
import com.assignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The type User service.
 */
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final RestClient restClient;

    private final UserRepository userRepository;

    /**
     * The Model mapper.
     */
    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;


    /**
     * Create new user user dto.
     *
     * @param user the user
     * @return the user dto
     */
    public UserDto createNewUser(UserDto user) {
        return modelMapper.map(userRepository.save(modelMapper.map(user, UserEntity.class)), UserDto.class);
    }

    /**
     * Sign up user dto.
     *
     * @param signUpDto the sign up dto
     * @return the user dto
     */
    public UserDto signUp(SignUpDto signUpDto) {
        Optional<UserEntity> user = userRepository.findByEmail(signUpDto.getEmail());
        if (user.isPresent()) {
            throw new BadCredentialsException("User with email already exits " + signUpDto.getEmail());
        }

        UserEntity toBeCreatedUser = modelMapper.map(signUpDto, UserEntity.class);
        String[] place = getCityAndCountry(signUpDto.getPinCode()).split(",");
        toBeCreatedUser.setCity(place[0]);
        toBeCreatedUser.setCountry(place[1]);
        toBeCreatedUser.setPassword(passwordEncoder.encode(toBeCreatedUser.getPassword()));
        UserEntity savedUser = userRepository.save(toBeCreatedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }

    /**
     * Gets user by id.
     *
     * @param userId the user id
     * @return the user by id
     */
    public UserEntity getUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id " + userId +
                " not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("User with email " + username + " not found"));
    }


    /**
     * Gets city and state.
     *
     * @param postalCode the postal code
     * @return the city and state
     */
    public String getCityAndCountry(String postalCode) {
        try {
            HashMap[] response = restClient.get()
                    .uri(postalCode)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            if (response != null && response.length > 0 && "Success".equals(response[0].get("Status"))) {
                List<Map<String, Object>> postOffices = (List<Map<String, Object>>) response[0].get("PostOffice");

                if (postOffices != null && !postOffices.isEmpty()) {
                    Map<String, Object> postOffice = postOffices.get(0);  // Take the first matching post office
                    String city = (String) postOffice.get("District");
                    String state = (String) postOffice.get("State");

                    return city + "," + state;
                } else {
                    throw new ResourceNotFoundException("Pin code is not valid");
                }

            } else {
                throw new ResourceNotFoundException("Pin code is not valid");
            }
        } catch (Exception e) {
            throw new ResourceNotFoundException("Pin code is not valid");
        }
    }
}
