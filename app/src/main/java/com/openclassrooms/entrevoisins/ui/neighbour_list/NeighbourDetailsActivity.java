package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.Objects;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_details);
        ButterKnife.bind(this);
        neighbourApiService = DI.getNeighbourApiService();

        initViews();
        setOnClickStar();
        setBackButton();
    }

    /**
     * Add or remove neighbour to from our favorite list & set the corresponding star
     */
    private void setOnClickStar() {
        Neighbour neighbour = Objects.requireNonNull(getIntent().getExtras()).getParcelable("neighbour");



        favButton.setOnClickListener(view -> {

            if (!neighbourApiService.isFavorite(neighbour)) {
                favButton.setImageResource(R.drawable.ic_star_full);
                neighbourApiService.addFavorite(neighbour);
            } else {
                favButton.setImageResource(R.drawable.ic_star_not_full);
                neighbourApiService.removeFavorite(neighbour);
            }
            if (neighbour != null) {
                setToast(neighbour);
            }
        });
    }

    /**
     * Set the corresponding toast after click
     */
    private void setToast(Neighbour neighbour) {
        String addedToFav = neighbour.getName() + getString(R.string.addedToFav);
        String removedFromFav = neighbour.getName() + getString(R.string.removedFromFav);
        if (neighbourApiService.isFavorite(neighbour))
            Toast.makeText(getApplicationContext(), addedToFav, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), removedFromFav, Toast.LENGTH_SHORT).show();
    }

    /**
     * Set neighbours info to view
     */
    private void initViews() {
        neighbourApiService.getFavoriteNeighbours();
        Neighbour neighbour = Objects.requireNonNull(getIntent().getExtras()).getParcelable("neighbour");
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

            if (neighbourApiService.isFavorite(neighbour))
                favButton.setImageResource(R.drawable.ic_star_full);
        }
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