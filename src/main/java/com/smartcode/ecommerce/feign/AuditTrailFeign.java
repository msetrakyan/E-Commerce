package com.smartcode.ecommerce.feign;

import com.smartcode.ecommerce.model.action.ActionDto;
import com.smartcode.ecommerce.model.action.ActionFilterModel;
import com.smartcode.ecommerce.model.action.ActionRequestDto;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "auditTrailFeign", url = "http://localhost:8082/action")
@EnableFeignClients
public interface AuditTrailFeign {


    @GetMapping
     List<ActionDto> get(@RequestBody ActionFilterModel actionFilterModel);

    @GetMapping(path = "/{id}")
    ActionDto getById(@PathVariable Integer id);

    @PostMapping
    ActionDto create(@RequestBody ActionRequestDto actionRequestDto);

    @DeleteMapping
    void delete();

    @DeleteMapping(path = "/{id}")
    void deleteById(@PathVariable Integer id);

    @PutMapping
    ActionDto update(@RequestBody ActionDto actionEntity);


}