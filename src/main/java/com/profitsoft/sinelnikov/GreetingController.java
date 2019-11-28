package com.profitsoft.sinelnikov;

import com.profitsoft.sinelnikov.domain.Employee;
import com.profitsoft.sinelnikov.domain.EmployeeType;
import com.profitsoft.sinelnikov.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        model.put("employees", employeeService.getAll());
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String name, @RequestParam String phone, @RequestParam String region, @RequestParam String type, Map<String, Object> model) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setPhone(phone);
        employee.setRegion(region);
        //EmployeeType employeeType = (EmployeeType)CONTEXT.getBean("employeeType");
        EmployeeType employeeType = new EmployeeType();
        employeeType.setType(type);
        //employeeType.setEmployees();
        employee.setType(employeeType);
        employeeService.add(employee);
        model.put("employees", employeeService.getAll());
        return "main";
    }

//    @PostMapping("filter")
//    public String filter(@RequestParam String filter, Map<String, Object> model) {
//        Iterable<Employee> employees;
//        employees = filter == null || filter.isEmpty() ? employeeRepo.findAll() : employeeRepo.findByEmployeeType(filter);
//        model.put("employees", employees);
//        return "main";
//    }
}
