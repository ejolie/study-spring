package spring.tutorials.nonrest.payroll;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.*;

@RestController
class EmployeeController {
    private final EmployeeRepository repository;
    private final EmployeeResourceAssembler assembler;

    EmployeeController(EmployeeRepository repository, EmployeeResourceAssembler aseembler) {
        this.repository = repository;
        this.assembler = aseembler;
    }

    // Aggregate root
    @GetMapping("/employees")
    CollectionModel<EntityModel<Employee>> all() {
        List<EntityModel<Employee>> employees = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return new EntityModel<>(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    @PostMapping("/employees")
    ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) throws URISyntaxException {
        EntityModel<Employee> entityModel = assembler.toModel(repository.save(newEmployee));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    // Single item
    @GetMapping("/employees/{id}")
    EntityModel<Employee> one(@PathVariable Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return assembler.toModel(employee);
    }

    @PutMapping("/employees/{id}")
    ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) throws URISyntaxException {
        Employee updatedEmployee = repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });

        EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
    @DeleteMapping("/employees/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
