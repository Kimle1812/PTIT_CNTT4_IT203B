package PTIT_CNTT4_IT203B_Session05.miniProject.test;

import PTIT_CNTT4_IT203B_Session05.miniProject.model.Food;
import PTIT_CNTT4_IT203B_Session05.miniProject.model.MenuItem;
import PTIT_CNTT4_IT203B_Session05.miniProject.service.MenuService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MenuServiceTest {
    private MenuService menuService;

    @BeforeEach
    public void setUp() {
        menuService = new MenuService();
        menuService.addItem(new Food("F1", "Burger", 5.0, 5));
        menuService.addItem(new Food("F2", "Pizza", 8.0, 2));
    }

    @Test
    public void testSearchByName() {
        List<MenuItem> result = menuService.searchByName("bug");
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    public void testFilterByPrice() {
        List<MenuItem> list = menuService.filterByPrice(4.0, 6.0);
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals("F1", list.get(0).getId());
    }
}
