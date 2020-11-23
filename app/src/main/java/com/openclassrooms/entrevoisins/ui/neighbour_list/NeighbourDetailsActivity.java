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


    //ADD OR REMOVE NEIGHBOUR FROM FAVORITELIST & SET THE CORRESPONDING STAR
    private void setOnClickStar() {
        Neighbour neighbour = getIntent().getExtras().getParcelable("neighbour");

        if (neighbourApiService.getFavoriteNeighbours().contains(neighbour)) //isfavoris
            favButton.setImageResource(R.drawable.ic_star_full);

        favButton.setOnClickListener(view -> {
            String addedToFav = neighbour.getName() + " a été ajouté aux favoris"; //strings.xml
            String removedFromFav = neighbour.getName() + " a été retiré des favoris"; //strings.xml
            if (!neighbourApiService.getFavoriteNeighbours().contains(neighbour)) { //isFavoris
                Toast.makeText(getApplicationContext(), addedToFav, Toast.LENGTH_SHORT).show();
                favButton.setImageResource(R.drawable.ic_star_full);
                neighbourApiService.addFavorite(neighbour);
                neighbour.setFavorite(true);
            } else {
                Toast.makeText(getApplicationContext(), removedFromFav, Toast.LENGTH_SHORT).show();
                favButton.setImageResource(R.drawable.ic_star_not_full);
                neighbourApiService.removeFavorite(neighbour);
                neighbour.setFavorite(false);
                //split en deux méthodes
            }
        });

    }

    /**
     * Set neighbours info to view
     */
    private void initViews() {

        neighbourApiService.getFavoriteNeighbours();
        Neighbour neighbour = getIntent().getExtras().getParcelable("neighbour");
        nameOnPicText.setText(neighbour.getName());
        nameText.setText(neighbour.getName());
        addressText.setText(neighbour.getAddress());
        contactText.setText(neighbour.getPhoneNumber());
        websiteText.setText(getString(R.string.details_neighbour_site) + neighbour.getName()); //WARNING
        bioText.setText(neighbour.getAboutMe());

        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .into(avatarView);
    }

    //BACK TO PREVIOUS ACTIVITY
    private void setBackButton() {
        backArrow.setOnClickListener(view -> {
            finish();
        });
    }


}