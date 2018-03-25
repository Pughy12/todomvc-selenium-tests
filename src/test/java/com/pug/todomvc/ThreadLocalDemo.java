package com.pug.todomvc;

import com.pug.todomvc.driver.LocalDriverManager;
import com.pug.todomvc.page_objects.TodoList;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ThreadLocalDemo {

    @DataProvider(name = "data", parallel = true)
    public Object[][] getData() {
        return new Object[][]{
                {"1"}, {"2"}, {"3"}, {"4"}, {"5"}, {"6"}, {"7"}, {"8"}, {"9"}, {"10"}
        };
    }

    @Test(dataProvider = "data")
    public void testHelloWorld(String text) {
        TodoList todoList = new TodoList(LocalDriverManager.getDriver());

        System.out.println("Adding item to list with text " + text);

        todoList.addItem(text);

        assertTrue(todoList.hasItem(text));
    }

    @Test
    public void testRetryByAlwaysFailing() {
        assertTrue(1 == 2);
    }
}
