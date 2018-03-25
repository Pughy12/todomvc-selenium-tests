package com.pug.todomvc;

import com.pug.todomvc.driver.LocalDriverManager;
import com.pug.todomvc.page_objects.TodoList;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ThreadLocalDemo {

//    @DataProvider(name = "data", parallel = true)
//    public Object[][] getData() {
//        return new Object[][]{
//                {"1"}, {"2"}, {"3"}, {"4"}, {"5"}, {"6"}, {"7"}, {"8"}, {"9"}, {"10"}
//        };
//    }
//
//    @Test(dataProvider = "data")
//    public void testHelloWorld(String text) {
//        TodoList todoList = new TodoList(LocalDriverManager.getDriver());
//
//        System.out.println("Adding item to list with text " + text);
//
//        todoList.addItem(text);
//
//        assertTrue(todoList.hasItem(text));
//    }
//
//    @Test
//    public void testRetryByAlwaysFailing() {
//        assertTrue(1 == 2);
//    }

    @Test
    public void testItemCanBeAddedToList() {
        final String todoItemName = "Test-Item-1";
        final String anotherTodoItemName = "Test-Item-99999";
        final TodoList todo = new TodoList(LocalDriverManager.getDriver());

        // Add an item to the list, and make sure it's there
        todo.addItem(todoItemName);
        Assert.assertTrue(todo.hasItem(todoItemName));

        // Do the same for another item with a different name
        todo.addItem(anotherTodoItemName);
        Assert.assertTrue(todo.hasItem(anotherTodoItemName));
    }

    @Test
    public void testItemCanBeDeleted() {
        final String todoItemName = "Test-Item-2";
        final TodoList todo = new TodoList(LocalDriverManager.getDriver());

        // Add an item to the list, and make sure it's there
        todo.addItem(todoItemName);

        // Delete the item from the list, and assert it's now gone from the list
        todo.deleteItem(todoItemName);
        Assert.assertFalse(todo.hasItem(todoItemName));
    }

    @Test
    public void testItemCanBeEdited() {
        final String todoItemName = "Test-Item-3";
        final String todoItemNameRenamed = todoItemName + "-renamed";
        final TodoList todo = new TodoList(LocalDriverManager.getDriver());

        // Add one item to the list
        todo.addItem(todoItemName);

        // Rename the item
        todo.renameItem(todoItemName, todoItemNameRenamed);

        // Assert that no item by the old name is on the list and one with the new name is on the list
        Assert.assertFalse(todo.hasItem(todoItemName));
        Assert.assertTrue(todo.hasItem(todoItemNameRenamed));
    }

    @Test
    public void testItemCanBeCompleted() {
        final String todoItemName = "Test-Item-4";
        final TodoList todo = new TodoList(LocalDriverManager.getDriver());

        // Add one item to the list and mark it as completed
        todo.addItem(todoItemName);
        todo.completeItem(todoItemName);

        // Assert it is completed
        boolean completed = todo.isItemCompleted(todoItemName);
        Assert.assertTrue(completed);
    }

    @Test
    public void testListCanBeFiltered() {
        final String todoItemName = "Test-Item-5";
        final TodoList todo = new TodoList(LocalDriverManager.getDriver());

        // Add an item to the list
        todo.addItem(todoItemName);

        // Filter the list by completed and check the one we added is be hidden because it's not completed
        todo.filterByCompleted();
        Assert.assertFalse(todo.hasItem(todoItemName));

        // Filter the list by active and check the item is there because it's not marked as completed
        todo.filterByActive();
        Assert.assertTrue(todo.hasItem(todoItemName));

        // Filter by all, again the item should remain because this shows items of all states
        todo.filterByAll();
        Assert.assertTrue(todo.hasItem(todoItemName));
    }
}
