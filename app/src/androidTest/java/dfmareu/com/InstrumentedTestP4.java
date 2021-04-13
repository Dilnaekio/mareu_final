package dfmareu.com;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import dfmareu.com.ui.create.CreateReunion;
import dfmareu.com.ui.main.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static dfmareu.com.Matchers.RecyclerViewItemAtPositionAssertion.atPosition;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class InstrumentedTestP4 {
    @Rule
    public ActivityScenarioRule<MainActivity> activityActivityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void testAddReunion() {
        //GO TO CREATE REUNION ACTIVITY
        onView(withId(R.id.fab)).perform(click());
        intended(hasComponent(CreateReunion.class.getName()));

        //SUBJECT (Create Reunion)
        onView(withId(R.id.activity_create_reunion_SubjectEditTxt))
                .perform(typeText("Sujet test"));

        //PARTICIPANTS (Create Reunion)
        onView(withId(R.id.activity_create_reunion_ParticipantEditTxt))
                .perform(typeText("test@gmail.com"));
        onView(withId(R.id.activity_create_reunion_participants_AcceptBtn))
                .perform(click());

        //DATE SELECTION (Create Reunion)
        onView(withId(R.id.activity_create_reunion_DateBtn))
                .perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2021, 4, 15));
        onView(withText("OK")).perform(click());

        //TIME SELECTION (Create Reunion)
        onView(withId(R.id.activity_create_reunion_TimeBtn))
                .perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(15, 15));
        onView(withText("OK")).perform(click());

        //RETURN ON THE MAIN ACTIVITY
        onView(withId(R.id.fabValidate))
                .perform(click());

        //CHECK IF THE ONLY (and new) REUNION WITH "test@gmail.com" is visible in the main recyclerview
        onView(withId(R.id.activity_main_recycler_view))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("test@gmail.com"))));
    }

    @Test
    public void testDeleteReunion() {
        //TEST TO DELETE ONE REUNION
        onView(allOf(withId(R.id.activity_main_item_garbage),
                withParent(
                        allOf(withId(R.id.activity_main_item_first_Linear), hasDescendant(withText("Salle B"))))))
                .perform(click());
        onView(withId(R.id.activity_main_recycler_view))
                .check(matches(atPosition(1, hasDescendant(withText("Salle C")))));
    }

    @Test
    public void testRoomFilterSuccessfully() {
        onView(withId(R.id.action_search))
                .perform(click());
        onView(withId(R.id.search_src_text))
                .perform(typeText("Salle C"));
        onView(withId(R.id.activity_main_item_Room))
                .check(matches(withText("Salle C")));
    }

    @Test
    public void testDateFilterSuccessfully() {
        onView(withId(R.id.action_search))
                .perform(click());
        onView(withId(R.id.search_src_text))
                .perform(typeText("29/04/2021"));
        onView(withId(R.id.activity_main_item_Room))
                .check(matches(withText("Salle E")));
    }
}