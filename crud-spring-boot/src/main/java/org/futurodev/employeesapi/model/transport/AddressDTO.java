package org.futurodev.employeesapi.model.transport;

import org.futurodev.employeesapi.model.Address;

public record AddressDTO(String street, Integer number, String district, String city) {

    public AddressDTO(Address address) {
        this(address.getStreet(), address.getNumber(), address.getDistrict(), address.getCity());
    }
}
