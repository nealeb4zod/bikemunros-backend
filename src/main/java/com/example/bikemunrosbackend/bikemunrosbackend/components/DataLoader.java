package com.example.bikemunrosbackend.bikemunrosbackend.components;



import com.example.bikemunrosbackend.bikemunrosbackend.models.Munro;
import com.example.bikemunrosbackend.bikemunrosbackend.models.User;
import com.example.bikemunrosbackend.bikemunrosbackend.repositories.MunroRepository;
import com.example.bikemunrosbackend.bikemunrosbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;




@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    MunroRepository munroRepository;

    public DataLoader(){

    }

    public void run(ApplicationArguments args){


        User neale = userRepository.getOne(1L);
        Munro benchonzie = munroRepository.getOne(1L);

//        neale.addBikedMunro(benchonzie);
//        userRepository.save(neale);



    }
}
