package ua.kiev.gossips.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GossipUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private State state;
    @OneToMany(mappedBy = "gossipUser")
    private List<Token> tokens;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GossipUser gossipUser = (GossipUser) o;
        return Objects.equals(id, gossipUser.id) &&
                Objects.equals(email, gossipUser.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, email);
    }
}
