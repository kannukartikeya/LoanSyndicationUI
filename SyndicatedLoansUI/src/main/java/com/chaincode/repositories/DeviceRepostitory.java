package com.chaincode.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.chaincode.domain.Device;

public interface DeviceRepostitory extends CrudRepository<Device, Long> {
	public List<Device> findByLabel(String label);
}
