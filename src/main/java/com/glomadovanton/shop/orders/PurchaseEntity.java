package com.glomadovanton.shop.orders;

import com.glomadovanton.shop.goods.CakeEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "PURCHASE")
public class PurchaseEntity {
    @Setter(AccessLevel.NONE)
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    @Setter(AccessLevel.PRIVATE)
    @ManyToOne (fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(nullable = false)
    private CakeEntity cake;

    @Setter(AccessLevel.PRIVATE)
    @ManyToOne (fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(nullable = false)
    private OrderEntity order;

    @Setter(AccessLevel.PROTECTED)
    private Integer number;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PurchaseEntity that = (PurchaseEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
