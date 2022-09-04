package org.hothyojas.mogayobackend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(unique = true)
    private String token;

    @Column(unique = true)
    private String username;

    @JsonProperty(access = Access.WRITE_ONLY)
    @Column
    private String password;

    @Column(unique = true)
    private String nickname;

    @Column
    private float heartTemperature = 36.5f;

    @Column
    private boolean available = true;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Parent parent;
}
