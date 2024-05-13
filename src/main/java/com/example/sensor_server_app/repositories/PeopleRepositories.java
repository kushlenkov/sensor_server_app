package com.example.sensor_server_app.repositories;

import com.example.sensor_server_app.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepositories extends JpaRepository<Person, Integer> {

}
