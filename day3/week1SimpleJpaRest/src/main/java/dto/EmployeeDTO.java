/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Employee;

/**
 *
 * @author alexa
 */
public class EmployeeDTO {
    
    Long id;
    String name;
    String address;

    public EmployeeDTO(Employee e) {
        this.id = e.getId();
        this.name = e.getName();
        this.address = e.getAddress();
    }

    public EmployeeDTO() {
    }
    
}
