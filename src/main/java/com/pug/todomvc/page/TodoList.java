package com.pug.todomvc.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * PageObject representing the To-Do List webapp found here: http://todomvc.com/examples/angularjs/#/
 *
 * This could definitely be broken into multiple classes. Possibly separating out the individual items for the app
 * such as the list items, the filters, and more as separate classes. This would be it easier to do things like:
 *
 * boolean isItemCompleted = getListItem(listItemText).isCompleted();
 *
 * Currently that's not possible, unless you break this class into multiple page objects
 */
public class TodoList extends PageObject {

    @FindBy(css = "input#new-todo")
    private WebElement newTodoInput;

    @FindBy(css = "ul#filters li a[href*='#/']")
    private WebElement filterByAllLink;

    @FindBy(css = "ul#filters li a[href*='#/active']")
    private WebElement filterByActiveLink;

    @FindBy(css = "ul#filters li a[href*='#/completed']")
    private WebElement filterByCompletedLink;

    public TodoList(WebDriver driver) {
        super(driver);

        // Sleep for a second because this is a webapp and the HTML takes a bit to load in properly
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addItem(String text) {
        newTodoInput.sendKeys(text);
        newTodoInput.submit();
    }

    public boolean hasItem(String itemName) {

        for (WebElement item : getItemsInList()) {
            String itemText = getItemName(item);

            if (itemText.equals(itemName)) {
                return true;
            }
        }

        return false;
    }

    public void deleteItem(String text) {

        for (WebElement listItem : getItemsInList()) {
            if (getItemName(listItem).equals(text)) {
                WebDriverWait wait = new WebDriverWait(driver(), 3);

                // Hover over the list item so it displays the delete button
                Actions actions = new Actions(driver());
                actions.moveToElement(listItem).perform();

                By byDeleteButton = By.cssSelector("button.destroy");

                // Wait until the delete button is there
                wait.until(ExpectedConditions.elementToBeClickable(byDeleteButton));
                WebElement deleteButton = listItem.findElement(byDeleteButton);

                deleteButton.click();
            }
        }
    }

    public List<WebElement> getItemsInList() {
        return this.driver().findElements(By.cssSelector("ul#todo-list li"));
    }

    public void completeItem(String todoItemName) {

        for (WebElement listItem : getItemsInList()) {
            if (getItemName(listItem).equals(todoItemName)) {
                listItem.findElement(By.cssSelector("input.toggle")).click();
            }
        }
    }

    private String getItemName(WebElement listItem) {
        return listItem.findElement(By.cssSelector("label")).getText();
    }

    public boolean isItemCompleted(String todoItemName) {

        for (WebElement listItem : getItemsInList()) {
            if (getItemName(listItem).equals(todoItemName)) {
                if (listItem.getAttribute("class").contains("completed")) {
                    return true;
                }
            }
        }

        return false;
    }

    public void renameItem(String text, String newText) {
        for (WebElement listItem : getItemsInList()) {
            if (getItemName(listItem).equals(text)) {

                // Double click the list item
                Actions actions = new Actions(this.driver());
                actions.doubleClick(listItem).perform();

                // Enter new text for the item and submit
                WebElement editInput = listItem.findElement(By.cssSelector("input.edit"));
                editInput.clear();
                editInput.sendKeys(newText, Keys.ENTER);
            }
        }
    }

    public void filterByAll() {
        this.filterByAllLink.click();
    }

    public void filterByCompleted() {
        this.filterByCompletedLink.click();
    }

    public void filterByActive() {
        this.filterByActiveLink.click();
    }
}
