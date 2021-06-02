package com.customertimes.model;

public class Product {

        private String title;
        private String description;
        private String price;

        public String getTitle() { return title; }

        public void setTitle(String email) { this.title = email; }

        public String getDescription() { return description; }

        public void setDescription(String password) { this.description = password; }

        public String getPrice() { return  price; }

        public void setPrice(String price) { this.price = price;}

    public static Builder newBuilder() { return new Builder(); }

    public Product() {
    }

    public Product(final Builder builder) {
            title = builder.title;
            description = builder.description;
            price = builder.price;
        }

        public static final class Builder {
            private String title;
            private String description;
            private String price;

            private Builder() {
            }

            public Builder withTitle(final String val) {
                title = val;
                return this;
            }

            public Builder withDescription(final String val) {
                description = val;
                return this;
            }

            public Builder withPrice(final String val) {
                price = val;
                return this;
            }

            public Product build() { return new Product(this); }
        }


}
