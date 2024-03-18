package vn.smartshop.server.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long ID;

    @Column
    private String name;

    @Column
    private Double price;

    @Column
    private String code;

    @Column
    private String category;

    @Column
    private String brand;

    @Column
    private Double quantity;

    @Column(length = 4000)
    private String note;

}
