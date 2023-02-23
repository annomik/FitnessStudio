package by.it_academy.jd2.MJD29522.fitness.web;

import by.it_academy.jd2.MJD29522.fitness.core.dto.Page;
import by.it_academy.jd2.MJD29522.fitness.core.dto.UserCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.service.api.IAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/users/")
public class AdminController {

    private final IAdminService adminService;

    public AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addNewUser(@RequestBody UserCreateDTO userCreateDTO) {
        adminService.addNewUser(userCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //Получить страницу пользователей
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page> getPage(@RequestParam("page") int numberOfPage,
                                        @RequestParam("size") int size){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getPage(numberOfPage, size));
    }

    //Получить информацию о пользователе
    @RequestMapping(path = "/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<UserCreateDTO> getCard(@PathVariable("uuid") UUID uuid){

        return ResponseEntity.status(HttpStatus.OK).body(adminService.getCard(uuid));
    }

    @RequestMapping(path = "/{uuid}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("uuid") UUID uuid,
                                    @PathVariable("dt_update") long dtUpdate,
                                    @RequestBody UserCreateDTO userCreateDTO ) {
        adminService.update(uuid, dtUpdate, userCreateDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
