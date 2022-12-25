package mk.ukim.finki.backend.web;

import mk.ukim.finki.backend.model.Exceptions.InvalidArgumentsException;
import mk.ukim.finki.backend.model.Restaurant;
import mk.ukim.finki.backend.service.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final RestaurantService restaurantService;

    public AdminController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public String getAdminPanel(){
        return "admin-panel";
    }

    @PostMapping
    public String addRestaurant(@RequestParam String name, @RequestParam String suburb, @RequestParam String street,
                                @RequestParam String website, @RequestParam String phone, @RequestParam String lon,
                                @RequestParam String lat, @RequestParam String opens, @RequestParam String closes, Model model){
        try{
            restaurantService.saveRestaurant(name, suburb, street, lat, lon, opens, closes, website, phone);
            return "redirect:/home";
        } catch (InvalidArgumentsException e){
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());
            return "admin-panel";
        }
    }

    //Ako e popolneta bazata, izbrisi go metodot
    /*@PostMapping
    public String fillDatabase(){

        File file = new File("database/RestourantsInSkopje.csv");
        if(file.exists()){
            try {
                BufferedReader bf = new BufferedReader(new FileReader(file));
                String line = bf.readLine();
                while ((line = bf.readLine()) != null) {
                    System.out.println(line);

                    String[] parts = line.split(",");
                    String name = parts[0];
                    String suburb = parts[1];
                    String street = parts[2];
                    Double lon = Double.parseDouble(parts[3]);
                    Double lat = Double.parseDouble(parts[4]);
                    String opens = parts[5];
                    String closes = parts[6];
                    String website = parts[7];
                    String phone = parts[8];
                    restaurantService.saveRestaurant(name, suburb, street, lat, lon, opens, closes, website, phone);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "admin-panel";
    }*/
}
