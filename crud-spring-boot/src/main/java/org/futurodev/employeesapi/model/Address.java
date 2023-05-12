package org.futurodev.employeesapi.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.futurodev.employeesapi.model.transport.AddressDTO;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String street;
    private Integer number;
    private String district;
    private String city;

    public Address(AddressDTO addressDTO) {
        this.street = addressDTO.street();
        this.number = addressDTO.number();
        this.district = addressDTO.district();
        this.city = addressDTO.city();
    }
}
