package uz.project.rentalplaces.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import uz.project.rentalplaces.dto.base.ApiResponse;
import uz.project.rentalplaces.dto.user.UserRoleDto;
import uz.project.rentalplaces.entity.UserRole;
import uz.project.rentalplaces.exception.RecordAlreadyExistException;
import uz.project.rentalplaces.exception.RecordNotFoundException;
import uz.project.rentalplaces.repository.RoleRepository;

import static uz.project.rentalplaces.constants.Constants.*;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final RoleRepository roleRepository;


    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(UserRoleDto dto) {
        if (roleRepository.existsByName(dto.getName())) {
            throw new RecordAlreadyExistException(ROLE_ALREADY_EXIST);
        }
        UserRole entity = UserRole.toEntity(dto);
        roleRepository.save(entity);
        return new ApiResponse(SUCCESSFULLY, true);
    }

    @ResponseStatus(HttpStatus.OK)
    public ApiResponse getAll() {
        return new ApiResponse(roleRepository.findAll(), true);
    }

    @ResponseStatus(HttpStatus.OK)
    public UserRole findByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new RecordNotFoundException(ROLE_NOT_FOUND));
    }

    @ResponseStatus(HttpStatus.OK)
    public ApiResponse getById(Integer id) {
        return new ApiResponse(roleRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(ROLE_NOT_FOUND)), true);
    }


    @ResponseStatus(HttpStatus.OK)
    public ApiResponse delete(Integer id) {
        UserRole userRole = roleRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(ROLE_NOT_FOUND));
        userRole.setDeleted(true);
        roleRepository.save(userRole);
        return new ApiResponse(DELETED, true);
    }
}
