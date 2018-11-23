package com.shop.dev.repository;

import com.shop.dev.entity.Item;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {


//    List<Item> findByItemIdOrTitle(Item item);
}
