package com.pug.todomvc;

import com.pug.todomvc.page_objects.TodoList;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * A suite of functional tests for the to-do mvc angular app
 *
 * http://todomvc.com/examples/angularjs/#/
 */
public class TodoListTests extends BaseTest {

    @Test
    public void testItemCanBeAddedToList() {
        final String todoItemName = "Test-Item-1";
        final String anotherTodoItemName = "Test-Item-99999";
        final TodoList todo = new TodoList(this.driver());

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
        final TodoList todo = new TodoList(this.driver());

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
        final TodoList todo = new TodoList(this.driver());

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
        final TodoList todo = new TodoList(this.driver());

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
        final TodoList todo = new TodoList(this.driver());

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

    /*
     * OTHER TESTS THAT COULD BE ADDED:
     *
     * - Adding an item to the list while it's filtered to Completed and making sure it doesn't show up
     * - Marking an item as completed while it's being filtered by Active and making sure it disappears
     * - Testing the css properties of grey text and strikethrough are applied to completed items
     * - Testing the item count in the bottom left corner to make sure the number is always correct
     * - Clicking the arrow that marks all items as completed and make sure they are all completed
     * - Clicking the "clear completed" button and making sure all completed items are gone and all active ones remain
     * - Attempting to put an empty item on the list (e.g. press space, then submit)
     * - Seeing what characters are valid and seeing whether they show up properly
     * - Responsive tests (e.g. shrinking the viewport width to ~400px and seeing if the app works in the same way)
     */
}
