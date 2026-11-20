package ru.netology.testing.uiautomator; // Теперь пакет правильный

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UIAutomatorTest {

    private UiDevice mDevice;
    private static final String PACKAGE_NAME = "ru.netology.testing.uiautomator";

    // Это правило само открывает приложение перед тестом
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Ждем до 10 секунд, пока приложение прогрузится
        mDevice.wait(Until.findObject(By.res(PACKAGE_NAME, "userInput")), 10000);
    }

    @Test
    public void testEmptyString() {
        UiObject2 originalText = mDevice.findObject(By.res(PACKAGE_NAME, "textToBeChanged"));
        assertNotNull("Не найден TextView textToBeChanged", originalText);
        String expectedText = originalText.getText();

        UiObject2 editText = mDevice.findObject(By.res(PACKAGE_NAME, "userInput"));
        assertNotNull("Не найдено поле ввода userInput", editText);
        editText.setText("   ");

        UiObject2 changeButton = mDevice.findObject(By.res(PACKAGE_NAME, "buttonChange"));
        assertNotNull("Не найдена кнопка buttonChange", changeButton);
        changeButton.click();

        mDevice.wait(Until.findObject(By.res(PACKAGE_NAME, "textToBeChanged")), 2000);
        UiObject2 updatedText = mDevice.findObject(By.res(PACKAGE_NAME, "textToBeChanged"));
        assertEquals(expectedText, updatedText.getText());
    }

    @Test
    public void testOpenTextInNewActivity() {
        String testText = "Hello UI Automator";

        UiObject2 editText = mDevice.findObject(By.res(PACKAGE_NAME, "userInput"));
        assertNotNull("Не найдено поле ввода userInput", editText);
        editText.setText(testText);

        UiObject2 openButton = mDevice.findObject(By.res(PACKAGE_NAME, "buttonActivity"));
        assertNotNull("Не найдена кнопка buttonActivity", openButton);
        openButton.click();

        UiObject2 newActivityText = mDevice.wait(
                Until.findObject(By.res(PACKAGE_NAME, "text")), 5000);

        assertNotNull("Не найдена вторая Activity или её TextView", newActivityText);
        assertEquals(testText, newActivityText.getText());
    }
}