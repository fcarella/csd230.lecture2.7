package csd230.lecture27.respositories;

import csd230.lecture27.entities.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {
    Cart findById(long id);
}
