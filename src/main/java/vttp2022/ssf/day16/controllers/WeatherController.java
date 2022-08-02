package vttp2022.ssf.day16.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp2022.ssf.day16.models.Weather;
import vttp2022.ssf.day16.services.WeatherService;

@Controller
@RequestMapping(path = {"/weather"})
public class WeatherController {
    @Autowired
    private WeatherService weatherSvc;
    
    @GetMapping
    public String getWeather(@RequestParam String city, Model model) {
        List<Weather> weather = weatherSvc.getWeather(city);
        // String main = weather.get(0).getMain();
        // String description = weather.get(0).getDescription();
        // String icon = weather.get(0).getIcon();

        model.addAttribute("city", city.toUpperCase());
        // model.addAttribute("main", main);
        // model.addAttribute("description", description);
        // model.addAttribute("icon", icon);
        model.addAttribute("weather", weather);

        return "weather";
    }
}
