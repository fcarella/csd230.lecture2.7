package csd230.lecture27.respositories;


import csd230.lecture27.entities.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    void removeById(Long id);
}
