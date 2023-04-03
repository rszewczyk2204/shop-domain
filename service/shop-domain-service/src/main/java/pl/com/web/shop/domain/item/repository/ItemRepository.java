package pl.com.web.shop.domain.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import pl.com.web.shop.domain.item.model.Item;

import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID>, QuerydslPredicateExecutor<Item> {
}
