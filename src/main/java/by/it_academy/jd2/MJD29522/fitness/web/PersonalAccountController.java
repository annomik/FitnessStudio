package by.it_academy.jd2.MJD29522.fitness.web;

import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserLoginDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserRegistrationDTO;
import by.it_academy.jd2.MJD29522.fitness.service.UserHolder;
import by.it_academy.jd2.MJD29522.fitness.service.api.IPersonalAccountService;
import by.it_academy.jd2.MJD29522.fitness.web.utils.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class PersonalAccountController {

    private final IPersonalAccountService service;

    public PersonalAccountController(IPersonalAccountService service) {
        this.service = service;
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody UserRegistrationDTO userRegistrationDTO) {
         service.save(userRegistrationDTO);
         return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(path = "/verification", method = RequestMethod.GET)
    public ResponseEntity<UserRegistrationDTO> verify(@RequestParam("code") String verificationCode,
                                                      @RequestParam("mail") String mail) {
         service.verify(verificationCode, mail);
         return ResponseEntity.status(HttpStatus.OK).build();
       }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public  ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {
        UserDTO userDTO = service.login(userLoginDTO);
        return ResponseEntity.status(HttpStatus.OK).body(JwtTokenUtil.generateAccessToken(userDTO));
    }

    //Получить информацию о себе
    @RequestMapping(path = "/me", method = RequestMethod.GET)
    public ResponseEntity<?> getCard(){
        UserHolder userHolder = new UserHolder();
        return ResponseEntity.status(HttpStatus.OK).body(userHolder.getUser());
    }
}