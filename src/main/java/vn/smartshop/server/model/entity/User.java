package vn.smartshop.server.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "USER")
public class User extends BaseEntity implements Serializable {
    @Id
    @Column
    private String userName;

    @Column
    private String password;

    @Column
    private String refreshToken;

    @Column
    private String role;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "User_Permission", //Tạo ra một join Table tên là "address_person"
            joinColumns = @JoinColumn(name = "user_name"),  // TRong đó, khóa ngoại chính là address_id trỏ tới class hiện tại (Address)
            inverseJoinColumns = @JoinColumn(name = "permissionID") //Khóa ngoại thứ 2 trỏ tới thuộc tính ở dưới (Person)
    )
    private List<Permission> permissions;

}
