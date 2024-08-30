package fr.optimal.optimalshop.config;

import org.springframework.context.annotation.Bean;

public class BcryptPasswordEncoder {

    @Bean
    public BcryptPasswordEncoder passwordEncoder() {
        return new BcryptPasswordEncoder();
    }
}
