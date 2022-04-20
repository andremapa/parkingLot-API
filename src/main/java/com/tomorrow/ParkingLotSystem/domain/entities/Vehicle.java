package com.tomorrow.ParkingLotSystem.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_vehicle")
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    @NotBlank
    @Pattern(regexp = "[A-Z]{3}-\\d{3}")
    private String plate;
    @NotBlank
    private String model;
    @NotBlank
    private String brand;
    @ManyToOne
    @JoinColumn(name = "owner_uuid")
    private Owner owner;

    public Owner getOwner() {
        return owner;
    }

    public Vehicle() {
    }

    public Vehicle(String plate, String model, String brand, Owner owner) {
        this.plate = plate;
        this.model = model;
        this.brand = brand;
        this.owner = owner;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getPlate() {
        return plate;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }
}
