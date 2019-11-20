package com.profitsoft.sinelnikov;

import com.profitsoft.sinelnikov.domain.Employee;
import com.profitsoft.sinelnikov.repos.EmployeeRepo;
import com.profitsoft.sinelnikov.repos.EmployeeTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private EmployeeTypeRepo employeeTypeRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        model.put("employees", employeeRepo.findAll());
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String name, @RequestParam String phone, @RequestParam String region, @RequestParam String employeeType, Map<String, Object> model) {
        Employee employee = new Employee(name, phone, region);
        employee.setEmployeeType(employeeTypeRepo.findByType(employeeType));
        employeeRepo.save(employee);
        model.put("employees", employeeRepo.findAll());
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Employee> employees;
        employees = filter == null || filter.isEmpty() ? employeeRepo.findAll() : employeeRepo.findByEmployeeType(filter);
        model.put("employees", employees);
        return "main";
    }
}
