package by.it_academy.jd2.MJD29522.fitness.web;

import by.it_academy.jd2.MJD29522.fitness.core.dto.UserCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserLoginDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserRegistrationDTO;
import by.it_academy.jd2.MJD29522.fitness.service.api.IPersonalAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/users")
public class PersonalAccountController {

    private final IPersonalAccountService service;

    public PersonalAccountController(IPersonalAccountService service) {
        this.service = service;
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public void register(@RequestBody UserRegistrationDTO userRegistrationDTO) {

        service.save(userRegistrationDTO);
    }

    @RequestMapping(path = "/verification", method = RequestMethod.GET)
    public ResponseEntity<UserRegistrationDTO> verify(@RequestParam("code") int verificationCode,
                                                      @RequestParam("mail") String mail) {
         service.verify(verificationCode, mail);
         return ResponseEntity.status(HttpStatus.OK).build();
       }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {
        service.login(userLoginDTO);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    //Получить информацию о себе
    @RequestMapping(path = "/me", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getCard(@PathVariable("me") UUID uuid){

        return ResponseEntity.status(HttpStatus.OK).body(service.getCard(uuid));
    }


}