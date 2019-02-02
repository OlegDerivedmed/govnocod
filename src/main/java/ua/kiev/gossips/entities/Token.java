package ua.kiev.gossips.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;
    private LocalDateTime expires;
    @Enumerated(value = EnumType.STRING)
    private TokenState state;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private GossipUser gossipUser;
}
