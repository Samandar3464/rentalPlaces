package uz.project.rentalplaces.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.project.rentalplaces.exception.UserNotFoundException;
import uz.project.rentalplaces.repository.UserRepository;

import static uz.project.rentalplaces.constants.Constants.USER_NOT_FOUND;


@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return userRepository.findByPhone(phoneNumber).orElseThrow(()->new UserNotFoundException(USER_NOT_FOUND));
    }
}
