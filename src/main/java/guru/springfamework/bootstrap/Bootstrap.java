package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Data loaded. " + categoryRepository.count() + " categories.");


        Customer customer = new Customer();
        customer.setFirstname("Mat");
        customer.setLastname("Lub");

        Customer customer2 = new Customer();
        customer2.setFirstname("Kar");
        customer2.setLastname("Lub");

        Customer customer3 = new Customer();
        customer3.setFirstname("Jos");
        customer3.setLastname("Lub");

        customerRepository.save(customer);
        customerRepository.save(customer2);
        customerRepository.save(customer3);

        System.out.println("Data loaded. " + customerRepository.count() + " customers.");
    }
}
