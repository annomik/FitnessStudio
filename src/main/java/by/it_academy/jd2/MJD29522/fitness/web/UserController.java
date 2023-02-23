package by.it_academy.jd2.MJD29522.fitness.web;

import by.it_academy.jd2.MJD29522.fitness.core.dto.UserCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserLoginDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserRegistrationDTO;
import by.it_academy.jd2.MJD29522.fitness.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public void register(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        service.save(userRegistrationDTO);
    }

//    @RequestMapping(path = "/verification", method = RequestMethod.GET)
//    public ResponseEntity<UserRegistrationDTO> verify(@RequestParam("code") String verificationCode,
//                                               @RequestParam("mail") String mail) {
//         service.verify(verificationCode, mail);
//         return ResponseEntity.status(HttpStatus.OK).build();
//       }
//
//    @RequestMapping(path = "/login", method = RequestMethod.POST)
//    public ResponseEntity<UserLoginDTO> login(@RequestBody UserLoginDTO userLoginDTO) {
//        return  ResponseEntity.status(HttpStatus.OK).body(service.login(userLoginDTO));
//    }
//
//    //Получить информацию о себе
//    @RequestMapping(path = "/{me}", method = RequestMethod.GET)
//    public ResponseEntity<UserCreateDTO> getCard(@PathVariable("me") UUID uuid){
//
//        return ResponseEntity.status(HttpStatus.OK).body(service.getCard(uuid));
//    }


}