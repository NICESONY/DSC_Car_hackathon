package com.mysite.extraclass.map;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MapRepository extends JpaRepository<Map, Integer> {
	Map findByTitle(String title);

}
