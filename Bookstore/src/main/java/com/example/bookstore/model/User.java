package com.example.bookstore.model;

import com.example.bookstore.constraints.Rol;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String password;
    private Rol rol;
     @OneToOne
     private Cart cart;
     @OneToMany
     private List<OrderHistory> historyList;

  /*  public boolean isAdmin() {
        return this.rol == Rol.ADMIN;
    }
*/


}
