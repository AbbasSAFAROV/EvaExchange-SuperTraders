package com.eva.tradingApp.service;

import com.eva.tradingApp.domain.converter.Converter;
import com.eva.tradingApp.domain.dto.StockDto;
import com.eva.tradingApp.domain.dto.UserDto;
import com.eva.tradingApp.domain.entity.Portfolio;
import com.eva.tradingApp.domain.entity.Role;
import com.eva.tradingApp.domain.entity.StockItem;
import com.eva.tradingApp.domain.entity.User;
import com.eva.tradingApp.domain.request.UserCreateRequest;
import com.eva.tradingApp.repository.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final Converter converter;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder, Converter converter) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.converter = converter;
    }

    public List<UserDto> getAllUsers(){
        return null;
    }

    public UserDto getUserById(Long id){
        return  null;
    }

    public UserDto createUser(UserCreateRequest request){
        User user = converter.toUserEntity(request);
        user.setRoles(Arrays.asList(new Role("TRADER")));
        user.setPassword(encoder.encode(request.getPassword()));
        User addedUser = userRepository.save(user);
        return converter.toUserDto(addedUser);
    }

    public List<StockItem> getMyStocks(){
        User user = getCurrentUser();
        return user.getPortfolio().getStockItems().stream().collect(Collectors.toList());
    }

    public List<StockItem> getAllMyStock(Long id){
        User user = findUserById(id);
        return user.getPortfolio().getStockItems().stream().collect(Collectors.toList());
    }

    public String deleteUserById(Long id){
        return null;
    }

    public User getUserByUsername(String userName){
        return userRepository.findUserByUsername(userName);
    }

    public User findUserById(Long id){
        return userRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("user not availabe with id: "+id));
    }

    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return getUserByUsername(authentication.getName());
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("Username or Password is wrong repeat again ...");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRoles(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRoles(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
