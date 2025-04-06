package org.tacocloud.repository;

import org.tacocloud.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
