package org.example.districtdesignerservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Demo {
    @Id
    int id;
    String name;
}
