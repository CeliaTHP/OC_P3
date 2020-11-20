
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.ViewPagerActions.scrollRight;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;


/*
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private static int ITEMS_POS = 1;

    private ListNeighbourActivity mActivity;
    private List<Neighbour> neighbourList = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void checkIfDeleteButtonRemoveNeighbour() {
        /*○ test vérifiant qu’au clic sur le bouton de suppression, la liste d’utilisateurs compte
        bien un utilisateur en moins ;*/

        //click on delete button
        onView(withId(R.id.item_list_delete_button)).perform(click());
        //check if item is removed from list
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void detailsActivityDisplaysNeighbourName() {
/*○ test vérifiant qu’au démarrage de ce nouvel écran, le TextView indiquant
        le nom de l’utilisateur en question est bien rempli ;*/


        Neighbour neighbour = this.neighbourList.get(0);
        //click on item at position 0
        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //check if displayed name matches with neighbour's name
        onView(withId(R.id.details_neighbour_bio_name)).check(matches(withText(neighbour.getName())));
    }


    @Test
    public void detailsActivityDisplays() {
             /*○ test vérifiant que lorsqu’on clique sur un élément de la liste, l’écran de
        détails est bien lancé ;*/

        //click on item at position 0
        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //check if details screen is launched
        onView(withId(R.id.neighbour_details)).check(matches(isDisplayed()));
    }

    @Test
    public void favoriteTabDisplaysFavoriteNeighbours() {
        //click on item at posiiton 0
        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //check if details screen is launched
        onView(withId(R.id.neighbour_details)).check(matches(isDisplayed()));
        //click on favorite button to add neighbour to favorites
        onView(withId(R.id.details_favorite_button)).perform(click());
        //go back to list activity
        onView(withId(R.id.back_arrow)).perform(click());
        //go to favorite tab
        onView(withId(R.id.container)).perform(scrollRight());
        //check if favoritelist is displayed
        onView(withId(R.id.list_neighbours)).check(matches(isDisplayed()));
        //check if our neighbour was added to favorite list
        onView(withId(R.id.list_neighbours)).check(withItemCount(1));
    }

    @Test
    public void backButton() {
        //click on item at position 0
        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //check if details screen is launched
        onView(withId(R.id.neighbour_details)).check(matches(isDisplayed()));
        //click on back button
        onView(withId(R.id.details_favorite_button)).perform(click());
        //check if back button brings us to list
        onView(withId(R.id.list_neighbours)).check(matches(isDisplayed()));

    }


}