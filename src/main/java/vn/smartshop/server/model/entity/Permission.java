package vn.smartshop.server.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "PERMISSION")
public class Permission extends BaseEntity implements Serializable {
    @Id
    @Column
    private String code;

    @Column
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // Quan hệ n-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)

    @JoinTable(name = "User_Permission", //Tạo ra một join Table tên là "address_person"
            joinColumns = @JoinColumn(name = "permissionID"),  // TRong đó, khóa ngoại chính là address_id trỏ tới class hiện tại (Address)
            inverseJoinColumns = @JoinColumn(name = "user_name") //Khóa ngoại thứ 2 trỏ tới thuộc tính ở dưới (Person)
    )
    private List<User> users;

}
