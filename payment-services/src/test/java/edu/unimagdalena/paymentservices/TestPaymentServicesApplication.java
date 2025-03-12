package edu.unimagdalena.paymentservices;

import org.springframework.boot.SpringApplication;

public class TestPaymentServicesApplication {

    public static void main(String[] args) {
        SpringApplication.from(PaymentServicesApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
