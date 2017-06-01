package com.chaincode.repositories;

import org.springframework.data.repository.CrudRepository;

import com.chaincode.domain.Room;

public interface RoomRepository extends CrudRepository<Room, Long> {

}
