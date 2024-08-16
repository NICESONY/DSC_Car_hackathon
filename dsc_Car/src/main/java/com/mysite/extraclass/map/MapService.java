package com.mysite.extraclass.map;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.extraclass.DataNotFoundException;





@Service
public class MapService {
	
	@Autowired
	private MapRepository mapRepository;
	
	
	public Map getMap(Integer id) {  
        Optional<Map> map = this.mapRepository.findById(id);
        if (map.isPresent()) {
            return map.get();
        } else { 
            throw new DataNotFoundException("map not found");
        }
        
    }
	
}
