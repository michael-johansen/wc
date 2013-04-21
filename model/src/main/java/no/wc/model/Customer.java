package no.wc.model;

import javax.persistence.*;
import java.util.Set;

/**
 * User: MicJoh
 * Date: 10.04.13
 * Time: 23:22
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "all", query = "from Customer c")
})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "customer")
    private Set<CustomerOrder> orderHistory;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<CustomerOrder> getOrderHistory() {
        return orderHistory;
    }
}
