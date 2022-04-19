package com.tomorrow.ParkingLotSystem.dto;

import com.tomorrow.ParkingLotSystem.domain.entities.Owner;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class OwnerDto implements Serializable {

    private String name;
    private String email;
    private String cpf;
    private String phone;

    public OwnerDto(Owner owner) {
        this.name = owner.getName();
        this.email = owner.getEmail();
        this.cpf = owner.getCpf();
        this.phone = owner.getPhone();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPhone() {
        return phone;
    }

    public static List<OwnerDto> converter(List<Owner> ownerList){
        return ownerList.stream().map(OwnerDto::new).collect(Collectors.toList());
    }
}
