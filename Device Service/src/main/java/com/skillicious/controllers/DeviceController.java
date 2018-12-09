package com.skillicious.controllers;

import com.skillicious.domain.Device;
import com.skillicious.repository.DeviceRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@Api( value = "Device Service REST End Points", description = "Shows Devices Information")
public class DeviceController {

    @Resource
    private DeviceRepository deviceRepository;

    @ApiOperation("List Of All Devices")
    @GetMapping("/findAll")
    private List<Device> getAll(){
        return deviceRepository.findAll();
    }

    @ApiOperation("Delete Device")
    @DeleteMapping("/delete/{id}")
    private void delete(@PathVariable("id") String id){
        deviceRepository.deleteById(id);
    }

    @ApiOperation("Adds New Device")
    @PostMapping("/add")
    private ResponseEntity<?> createDevice(@Valid @RequestBody Device request, BindingResult result){

        Device device = new Device();

        device.setId(RandomStringUtils.randomNumeric(10));
        device.setDeviceName(request.getDeviceName());
        device.setAddressLine1(request.getAddressLine1());
        device.setAddressLine2(request.getAddressLine2());
        device.setDeviceType(request.getDeviceType());
        device.setHardDisk(request.getHardDisk());
        device.setModal(request.getModal());
        device.setRam(request.getRam());

        deviceRepository.save(device);
        return new ResponseEntity<>(device, CREATED);
    }

    @ApiOperation("Edit Device Information")
    @PutMapping("/edit/{id}")
    private ResponseEntity<?> createDevice(@Valid @RequestBody Device request,@PathVariable("id") String id, BindingResult result){

        Device device = new Device();

        device.setId(id);
        device.setDeviceName(request.getDeviceName());
        device.setAddressLine1(request.getAddressLine1());
        device.setAddressLine2(request.getAddressLine2());
        device.setDeviceType(request.getDeviceType());
        device.setHardDisk(request.getHardDisk());
        device.setModal(request.getModal());
        device.setRam(request.getRam());

        deviceRepository.save(device);
        return new ResponseEntity<>(device, CREATED);
    }
}
