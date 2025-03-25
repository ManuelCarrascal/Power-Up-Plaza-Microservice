package com.pragma.powerup.domain.model;

import java.util.List;

public class Restaurant {
    private Long id;
    private String name;
    private String nit;
    private String address;
    private String phone;
    private String urlLogo;
    private Long idOwner;
    private List<Category> categories;

    private Restaurant(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.nit = builder.nit;
        this.address = builder.address;
        this.phone = builder.phone;
        this.urlLogo = builder.urlLogo;
        this.idOwner = builder.idOwner;
        this.categories = builder.categories;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String nit;
        private String address;
        private String phone;
        private String urlLogo;
        private Long idOwner;
        private List<Category> categories;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder nit(String nit) {
            this.nit = nit;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder urlLogo(String urlLogo) {
            this.urlLogo = urlLogo;
            return this;
        }

        public Builder idOwner(Long idOwner) {
            this.idOwner = idOwner;
            return this;
        }

        public Builder categories(List<Category> categories) {
            this.categories = categories;
            return this;
        }

        public Restaurant build() {
            return new Restaurant(this);
        }
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

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public Long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Restaurant() {
    }
}