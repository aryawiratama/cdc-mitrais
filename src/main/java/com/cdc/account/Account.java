package com.cdc.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "pin", length = 6,nullable = false)
    private String pin;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name="account_number", length = 6, nullable = false)
    private String accountNumber;
}
