package crud.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "name")
    @NotEmpty(message = "Имя  не должно быть пустым")
    @Size(min = 2, max = 20, message = "Имя должно содержать от 2 до 20 символов")
    private String name;

    @Column(name = "last_name")
    @NotEmpty(message = "Имя  не должно быть пустым")
    @Size(min = 2, max = 20, message = "Имя должно содержать от 2 до 20 символов")
    private String lastName;

    @Column(name = "age")
    @Min(value = 0, message = "Возраст должен быть больше 0")
    private byte age;


    public User() {
    }

    public User(String name, String lastName, byte age) {
        this.age = age;
        this.name = name;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }
}
