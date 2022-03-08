package btvn.controller;

import btvn.model.Car;
import btvn.model.CarCompany;
import btvn.service.ICarCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/car-company")
public class CarCompanyController {
    @Autowired
    private ICarCompanyService iCarCompanyService;

    @GetMapping
    public ModelAndView showCarCompany() {
        ModelAndView modelAndView = new ModelAndView("car-company/list");
        modelAndView.addObject("carCompanys", iCarCompanyService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("car-company/create");
        modelAndView.addObject("carCompany", new CarCompany());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Optional<CarCompany> carCompany) {
        carCompany.ifPresent(company -> iCarCompanyService.save(company));
        return "redirect:/car";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("car-company/edit");
        Optional<CarCompany> carCompany = iCarCompanyService.findOne(id);
        carCompany.ifPresent(value -> modelAndView.addObject("carCompany", value));
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public String edit(@ModelAttribute Optional<CarCompany> carCompany, @PathVariable Long id) {
        if (carCompany.isPresent()) {
            CarCompany carCompanyEdit = carCompany.get();
            carCompanyEdit.setId(id);
            iCarCompanyService.save(carCompanyEdit);
        }
        return "redirect:/car";
    }
}
