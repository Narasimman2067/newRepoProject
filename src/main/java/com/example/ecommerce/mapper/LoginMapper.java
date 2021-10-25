package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.LoginDto;
import com.example.ecommerce.model.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoginMapper {
    LoginMapper INSTANCE = Mappers.getMapper(LoginMapper.class);

    List<LoginDto> modelsToDto(List<Admin> admins);
}
