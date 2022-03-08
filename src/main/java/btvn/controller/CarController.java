package btvn.controller;

import btvn.model.Car;
import btvn.model.CarCompany;
import btvn.service.ICarCompanyService;
import btvn.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/car")
public class CarController {
    @Autowired
    private ICarCompanyService iCarCompanyService;

    @Autowired
    private ICarService iCarService;

    @ModelAttribute(name = "carCompany")
    private Iterable<CarCompany> showCompany() {
        return iCarCompanyService.findAll();
    }

    @GetMapping
    public ModelAndView showCar() {
        ModelAndView modelAndView = new ModelAndView("car/list");
        Iterable<Car> cars = iCarService.findAll();
        modelAndView.addObject("cars", cars);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("car/create");
        modelAndView.addObject("car", new Car());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Optional<Car> car) {
        car.ifPresent(c -> iCarService.save(c));
        return "redirect:/car";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("car/edit");
        Optional<Car> car = iCarService.findOne(id);
        car.ifPresent(value -> modelAndView.addObject("car", value));
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public String edit(@ModelAttribute Optional<Car> car, @PathVariable Long id) {
        if (car.isPresent()) {
            Car carEdit = car.get();
            carEdit.setId(id);
            iCarService.save(carEdit);
        }
        return "redirect:/car";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Optional<Car> car = iCarService.findOne(id);
        car.ifPresent(value -> iCarService.delete(id));
        return "redirect:/car";
    }

    @PostMapping("/search-name")
    public ModelAndView searchName(@RequestParam("search") String name) {
        ModelAndView modelAndView = new ModelAndView("car/list");
        Iterable<Car> cars = iCarService.findByName(name);
        modelAndView.addObject("cars", cars);
        return modelAndView;
    }

    @PostMapping("/search-price")
    public ModelAndView searchPrice(@RequestParam("min") Double min,@RequestParam("max") Double max) {
        ModelAndView modelAndView = new ModelAndView("car/list");
        Iterable<Car> cars = iCarService.findByPriceBetween(min, max);
        modelAndView.addObject("cars", cars);
        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView search(@RequestParam("search") Optional<String> name,
                               @RequestParam("min") Optional<Double> min,
                               @RequestParam("max") Optional<Double> max) {
        ModelAndView modelAndView = new ModelAndView("car/list");
        Iterable<Car> cars;
        if (name.isPresent()) {
            cars = iCarService.findByNameAndPriceBetween(name.get(), min.orElse(0D), max.orElse(0D));
        } else {
            cars = iCarService.findAll();
        }
        modelAndView.addObject("cars", cars);
        return modelAndView;
    }
}
