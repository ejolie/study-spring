package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/items/new")
    public String create(BookForm form) {
        // setter 모두 제거하고 Book.createBook() 메서드를 만드는 것이 좋은 설계
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/items";
    }

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Book book = (Book) itemService.findOne(itemId);

        BookForm form = new BookForm();
        form.setId(book.getId());
        form.setName(book.getName());
        form.setAuthor(book.getAuthor());
        form.setIsbn(book.getIsbn());
        form.setPrice(book.getPrice());
        form.setStockQuantity(book.getStockQuantity());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute("form") BookForm form) {
        /*
            병합 사용
            - 모든 필드를 바꿔치기한다.
         */

        // TODO: 해당 id를 가진 객체가 존재하는지 확인 필요

//        Book book = new Book();
//        book.setId(form.getId());
//        book.setName(form.getName());
//        book.setAuthor(form.getAuthor());
//        book.setIsbn(form.getIsbn());
//        book.setPrice(form.getPrice());
//        book.setStockQuantity(form.getStockQuantity());
//        itemService.saveItem(book);

        /*
            변경 감지 기능 사용
         */
        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());
        return "redirect:items";
    }
}
