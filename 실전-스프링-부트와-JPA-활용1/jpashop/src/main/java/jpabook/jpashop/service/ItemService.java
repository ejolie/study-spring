package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional // 없으면 readOnly 이므로 저장이 되지 않는다.
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long id) {
        return itemRepository.findOne(id);
    }

    /*
        변경 감지 기능 사용 (권장)
        - 선택적으로 필드를 업데이트 할 수 있다.
     */
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Book findBook = (Book) itemRepository.findOne(itemId);
        // TODO: Book.change(price, name, stockQuantity) 등 의미있는 메서드를 만들어 사용하자.
        // setter를 써서 하지 말고 변경 지점을 entity 레벨에 두자.
        findBook.setPrice(price);
        findBook.setName(name);
        findBook.setStockQuantity(stockQuantity);
    }
}
