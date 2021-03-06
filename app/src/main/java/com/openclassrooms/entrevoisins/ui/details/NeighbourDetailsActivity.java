package com.openclassrooms.entrevoisins.ui.details;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourDetailsActivity extends AppCompatActivity {

    @BindView(R.id.details_neighbour_name_on_pic)
    TextView nameOnPicText;
    @BindView(R.id.details_neighbour_bio_name)
    TextView nameText;
    @BindView(R.id.details_neighbour_address)
    TextView addressText;
    @BindView(R.id.details_neighbour_contact)
    TextView contactText;
    @BindView(R.id.details_neighbour_site)
    TextView websiteText;
    @BindView(R.id.details_neighbour_bio)
    TextView bioText;
    @BindView(R.id.details_favorite_button)
    FloatingActionButton favButton;
    @BindView(R.id.back_arrow)
    ImageButton backArrow;
    @BindView(R.id.details_neighbour_pic)
    ImageView avatarView;

    private NeighbourApiService neighbourApiService;
    private Neighbour neighbour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_details);
        ButterKnife.bind(this);
        neighbourApiService = DI.getNeighbourApiService();

        initBundles();
        initViews();
        setOnClickStar();
        setBackButton();
    }

    /**
     * Get Neighbour to initalize view
     */
    private void initBundles() {
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(getString(R.string.neighbour_title))) {
            neighbour = getIntent().getExtras().getParcelable(getString(R.string.neighbour_title));
        }
    }

    /**
     * Set neighbours info to view
     */
    private void initViews() {
        if (neighbour != null) {
            nameOnPicText.setText(neighbour.getName());
            nameText.setText(neighbour.getName());
            addressText.setText(neighbour.getAddress());
            contactText.setText(neighbour.getPhoneNumber());
            String website = getString(R.string.details_neighbour_site) + neighbour.getName();
            websiteText.setText(website);
            bioText.setText(neighbour.getAboutMe());
            Glide.with(this)
                    .load(neighbour.getAvatarUrl())
                    .into(avatarView);
            setStar();
        }
    }

    /**
     * Add or remove neighbour to from our favorite list & set the corresponding star
     */
    private void setOnClickStar() {
        favButton.setOnClickListener(view -> {
            neighbourApiService.toggleFavorite(neighbour);
            neighbour.setFavorite(!neighbour.isFavorite());
            setStar();
            setToast(neighbour);
        });
    }

    /**
     * Set the corresponding star for neighbour
     */
    private void setStar() {
        int resource = (neighbour.isFavorite() ? R.drawable.ic_star_full : R.drawable.ic_star_not_full);
        favButton.setImageResource(resource);
    }

    /**
     * Set the corresponding toast after click
     */
    private void setToast(Neighbour neighbour) {
        String addedToFav = getString(R.string.addedToFav, neighbour.getName());
        String removedFromFav = getString(R.string.removedFromFav, neighbour.getName());
        String toastText = (neighbour.isFavorite()) ? addedToFav : removedFromFav;
        Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_SHORT).show();
    }

    /**
     * Set the back arrow button
     */
    private void setBackButton() {
        backArrow.setOnClickListener(view -> {
            finish();
        });
    }

}