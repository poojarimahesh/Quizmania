package com.quizmania.config;

import com.quizmania.Repositories.UserRepository;
import com.quizmania.entity.User;
import com.quizmania.security.JwtAuthFilter;
import com.quizmania.service.Impl.UserInfoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

//    @Autowired
//    private UserRepository userRepository;

    @Autowired
    private JwtAuthFilter jwtAuthFilter;
    //Authentication
    @Bean
    public UserDetailsService userDetailsService(){
//        UserDetails admin = User.builder().username("Gaur").password(passwordEncoder.encode("Gaur1024"))
//                .roles("ADMIN").build();
//        UserDetails normalUser = User.withUsername("Nitai").password(passwordEncoder.encode("Nitai1024"))
//                .roles("NORMAL").build();
//        return new InMemoryUserDetailsManager(admin,normalUser);
//        List<String> un = new ArrayList<>();
//        un.add("gaurnitai");
//        un.add("gaur");
//        un.add("gauranga");
//        un.add("gaurangaaaaaa");
//        un.add("chaitanya");
//        un.add("chaitanyaaaa");
//        un.add("gujbe");
//        un.add("gaur123");
//        un.add("gaur1");
//        un.add("gaur12");
//
//
//        User gaurnitai = this.userRepository.findByUserName("gaurnitai");
//        gaurnitai.setPassword(passwordEncoder().encode(gaurnitai.getPassword()));
//        this.userRepository.save(gaurnitai);

//        User gaur = this.userRepository.findByUserName("gaur");
//        gaur.setPassword(passwordEncoder().encode(gaur.getPassword()));
//        this.userRepository.save(gaur);
//
//        User gauranga = this.userRepository.findByUserName("gauranga");
//        gauranga.setPassword(passwordEncoder().encode(gauranga.getPassword()));
//        this.userRepository.save(gauranga);
//
//        User gaurangaaaa = this.userRepository.findByUserName("gaurangaaaa");
//        gaurangaaaa.setPassword(passwordEncoder().encode(gaurangaaaa.getPassword()));
//        this.userRepository.save(gaurangaaaa);
////
////        User gaurangaaaaaa = this.userRepository.findByUserName("gaurangaaaaaa");
////        gaurangaaaaaa.setPassword(passwordEncoder().encode(gaurangaaaaaa.getPassword()));
////        this.userRepository.save(gaurangaaaaaa);
//
//        User chaitanya = this.userRepository.findByUserName("chaitanya");
//        chaitanya.setPassword(passwordEncoder().encode(chaitanya.getPassword()));
//        this.userRepository.save(chaitanya);
//
//        User chaitanyaaaa = this.userRepository.findByUserName("chaitanyaaaa");
//        chaitanyaaaa.setPassword(passwordEncoder().encode(chaitanyaaaa.getPassword()));
//        this.userRepository.save(chaitanyaaaa);
//
//        User gujbe = this.userRepository.findByUserName("gujbe");
//        gujbe.setPassword(passwordEncoder().encode(gujbe.getPassword()));
//        this.userRepository.save(gujbe);
//
//        User gaur123 = this.userRepository.findByUserName("gaur123");
//        gaur123.setPassword(passwordEncoder().encode(gaur123.getPassword()));
//        this.userRepository.save(gaur123);
//
//        User anil = this.userRepository.findByUserName("gopal");
//        anil.setPassword(passwordEncoder().encode(anil.getPassword()));
//        this.userRepository.save(anil);
//        User mahesh = this.userRepository.findByUserName("parshuram");
//        mahesh.setPassword(passwordEncoder().encode(mahesh.getPassword()));
//        this.userRepository.save(mahesh);

        return new UserInfoUserDetailsService();

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }

//    Authorization
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf-> csrf.disable())
                .authorizeHttpRequests(auth-> auth.requestMatchers("/login","/user/createUser","/auth/authenticate").permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/user/**","/auth/**","/category/**","/**").authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
