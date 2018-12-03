package com.tecgurus.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;

import lombok.Setter;

@RestController
@RequestMapping("/users")
public class UserController {

    @Setter(onMethod = @__(@Autowired))
    private DiscoveryClient discoveryClient;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/{userName}")
    public String findBlogsByUserName(@PathVariable String userName) {

        List<ServiceInstance> instances = discoveryClient.getInstances("blog-service");

        //ServiceInstance instance = instances.get(0);
        //ServiceInstance instance = instances.iterator().next();
        ServiceInstance instance = instances.stream().findFirst().orElse(null);

        String url = String.format("%s/blogs/byOwner/%s", 
                instance.getUri().toString(), userName);

        return instance.getHost();
    }

}