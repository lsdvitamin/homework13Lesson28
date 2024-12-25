package ru.professional.otus.homework13.lesson28.entity;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Sergei on 20.12.2024 22:19.
 * @project homework13Lesson28
 */
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_", nullable = false)
    private String name;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<Phone> phones = new HashSet<>();

    @OneToOne(mappedBy = "client", fetch = FetchType.LAZY)
    private Address addresses;

    public Client(String name) {
        this.name = name;
    }

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }


    public Address getAddresses() {
        return addresses;
    }

    public void setAddresses(Address addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phones=" + phones +
                ", addresses=" + addresses +
                '}';
    }
}
