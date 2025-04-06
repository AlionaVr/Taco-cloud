package org.tacocloud.repository;


import org.springframework.data.repository.CrudRepository;
import org.tacocloud.entity.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
