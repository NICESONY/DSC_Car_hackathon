package com.mysite.extraclass.map;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.extraclass.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MapService {

    private final MapRepository mapRepository;

    public Map getMap(Integer id) {
        Optional<Map> map = this.mapRepository.findById(id);
        if (map.isPresent()) {
            return map.get();
        }
        throw new DataNotFoundException("map not found");
    }
}
