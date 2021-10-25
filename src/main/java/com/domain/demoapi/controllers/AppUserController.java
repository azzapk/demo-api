package com.domain.demoapi.controllers;

import com.domain.demoapi.dto.AppUserData;
import com.domain.demoapi.dto.ResponseData;
import com.domain.demoapi.models.entities.AppUser;
import com.domain.demoapi.services.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<ResponseData<AppUser>> register(@RequestBody AppUserData userData){

        ResponseData<AppUser> responseData = new ResponseData<>();
        AppUser appUser = modelMapper.map(userData, AppUser.class);
        responseData.setPayload(appUserService.registerAppUser(appUser));
        responseData.setStatus(true);
        responseData.getMessages().add("AppUser Saved!");
        return ResponseEntity.ok(responseData);
    }
}
