package com.workshop.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.Entity.City;
import com.workshop.Repo.CityRepo;

@Service
public class SitemapService {

    @Autowired
    CityRepo cityRepo;

    public List<String> getAllUrls() {
        List<City> cities = cityRepo.findAll();
        List<String> urls = new ArrayList<>();
        urls.add("http://localhost:8085/");
        for (City city : cities) {
            urls.add("http://localhost:8085/city/" + city.getCity());
        }
        return urls;
    }

}
