package by.it_academy.jd2.MJD29522.fitness.web;

import by.it_academy.jd2.MJD29522.fitness.core.dto.PageDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.user.UserDTO;
import by.it_academy.jd2.MJD29522.fitness.service.api.IUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Past;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addNewUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        userService.addNewUser(userCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageDTO<UserDTO>> getPage(
            @RequestParam(name = "page", required = false, defaultValue = "0") int numberOfPage,
            @RequestParam(name = "size", required = false, defaultValue = "20") int size){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getPage(numberOfPage, size));
    }

    //Получить информацию о пользователе
    @RequestMapping(path = "/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getCard(@PathVariable("uuid") UUID uuid){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getCard(uuid));
    }

    @RequestMapping(path = "/{uuid}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("uuid") UUID uuid,
                                    @PathVariable("dt_update") @Past LocalDateTime dtUpdate,
                                    @Valid @RequestBody UserCreateDTO userCreateDTO ) {
        userService.update(uuid, dtUpdate, userCreateDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
