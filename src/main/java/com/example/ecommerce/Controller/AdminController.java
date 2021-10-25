package com.example.ecommerce.Controller;

import com.example.ecommerce.model.Admin;
import com.example.ecommerce.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class AdminController {
    @Autowired
    private AdminService service;

    @GetMapping("/admin")
    private List<Admin> getAll(){
        return service.getAllAdmin();
    }

    @GetMapping("/admin/login")
    public Object loginAdmin(@RequestParam("adminEmail") String adminEmail,@RequestParam("adminPassword") String adminPassword){
        return service.validate(adminEmail, adminPassword);
    }

    @PostMapping("/saveAdmin")
    public ResponseEntity<Admin> postAdmin(@Valid @RequestBody Admin admin){
        return new ResponseEntity<Admin>(
                service.saveAdmin(admin), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteAdminById/{id}")
    public String deleteAdmin(@PathVariable long id){
        return service.deleteAdminById(id);
    }
}
