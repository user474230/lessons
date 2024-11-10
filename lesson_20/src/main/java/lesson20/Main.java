package lesson20;

import lesson20.configs.Config;
import lesson20.service.CustomerService;
import lesson20.service.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Config.class);
        var customerService = context.getBean(CustomerService.class);
        var productService = context.getBean(ProductService.class);
//
//        customerService.create(new Customer("qwer", 12));
//        customerService.create(new Customer("asdf", 22));
//
//        productService.create(new Product("q22wer", 102));
//        productService.create(new Product("asd33f", 220));

        System.out.println(customerService.getAll());
        System.out.println(productService.getAll());

        System.out.println(customerService.getById(1L));
        System.out.println(productService.getById(1L));
    }
}
