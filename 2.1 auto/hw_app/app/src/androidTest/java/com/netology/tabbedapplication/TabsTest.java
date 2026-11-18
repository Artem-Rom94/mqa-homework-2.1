package com.netology.tabbedapplication;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class TabsTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testTabsSwitching() {
        // 1. Проверяем, что на экране есть текст "Page: 1"
        onView(withText("Page: 1")).check(matches(isDisplayed()));

        // 2. Нажимаем на вкладку "TAB 2"
        onView(withText("TAB 2")).perform(click());

        // 3. Проверяем, что появился текст "Page: 2"
        onView(withText("Page: 2")).check(matches(isDisplayed()));
    }
}