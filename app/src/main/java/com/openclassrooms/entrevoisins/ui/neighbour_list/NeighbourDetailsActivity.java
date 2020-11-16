package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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

    private Neighbour myNeighbour;

    private NeighbourApiService neighbourApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_details);
        ButterKnife.bind(this);
        initViews();
        setStar();


        neighbourApiService = DI.getNeighbourApiService();

        /* Add Neighbour in favorites*/

        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addedToFav = myNeighbour.getName() + " a été ajouté aux favoris";
                String removedFromFav = myNeighbour.getName() + " a été retiré des favoris";

                if (!myNeighbour.isFavorite())
                    Toast.makeText(getApplicationContext(), addedToFav, Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), removedFromFav, Toast.LENGTH_SHORT).show();
                neighbourApiService.toggleFavorite(myNeighbour);
                setStar();
            }
        });


        //ONCLICK BACK
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    private void initViews() {

        Neighbour neighbour = getIntent().getExtras().getParcelable("neighbour");

        myNeighbour = neighbour;
        Log.d("TAG",myNeighbour.toString());

        nameOnPicText.setText(neighbour.getName());
        nameText.setText(neighbour.getName());
        addressText.setText(neighbour.getAddress());
        contactText.setText(neighbour.getPhoneNumber());
        websiteText.setText(getString(R.string.details_neighbour_site) + neighbour.getName()); //add www/+name
        bioText.setText(neighbour.getAboutMe());


        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .into(avatarView);

    }

    private void setStar() {
        if (myNeighbour.isFavorite())
            favButton.setImageResource(R.drawable.ic_star_yellow);
        else
            favButton.setImageResource(R.drawable.ic_star_not_full);
    }


}