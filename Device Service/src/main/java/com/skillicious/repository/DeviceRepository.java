package com.skillicious.repository;

import com.skillicious.domain.Device;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeviceRepository extends MongoRepository<Device , String> {

}
