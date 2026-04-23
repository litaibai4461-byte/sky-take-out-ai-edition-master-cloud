package com.sky.api.client;

import com.sky.dto.EmployeeDTO;
import com.sky.entity.AddressBook;
import com.sky.entity.Employee;
import com.sky.entity.User;
import com.sky.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient("user-service")
public interface UserClient {
    @GetMapping("/user/addressBook/{id}")
    Result<AddressBook> queryAddressBookById(@PathVariable Long id);

    @GetMapping("/user/user/{id}")
    Result<User> getById(@PathVariable Long id);

    @GetMapping("/admin/employee/{id}")
    Result<Employee> getEmpById(@PathVariable Long id);

    @PostMapping("/admin/employee")
    Result save(@RequestBody EmployeeDTO employeeDTO);

    @PostMapping("/user/user/countByMap")
    Integer countByMap(@RequestBody Map map);

    @GetMapping("/admin/employee/getAll")
    List<Employee> getAll();
}
