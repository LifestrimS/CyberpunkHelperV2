package com.example.cyberpunkhelperv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.cyberpunkhelperv2.fragments.character_fragment;
import com.example.cyberpunkhelperv2.fragments.mob_fragment;
import com.google.android.material.bottomappbar.BottomAppBar;

public class MainActivity extends AppCompatActivity {

    character_fragment characterFragment;
    mob_fragment mobFragment;
    FragmentTransaction mFragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        characterFragment = new character_fragment();
        mobFragment = new mob_fragment();

        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        final BottomAppBar bottomAppBar = findViewById(R.id.bottomAppBar);
        bottomAppBar.setNavigationOnClickListener(v -> {
            NavigationDrawerFragment bottomNavFragment = new NavigationDrawerFragment();
            bottomNavFragment.show(getSupportFragmentManager(), "TAG");
        });
        bottomAppBar.replaceMenu(R.menu.bottom_menu);
        bottomAppBar.setOnMenuItemClickListener(item -> {
            changeFragment(item.getItemId());

            /*int id = item.getItemId();
            switch (id) {
                case R.id.app_bar_battle:
                    Toast.makeText(MainActivity.this, "app_bar_battle", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.app_bar_add_character:
                    Toast.makeText(MainActivity.this, "app_bar_add_character", Toast.LENGTH_SHORT).show();
                    changeFragment(id);
                    break;
                case R.id.app_bar_delete_character:
                    Toast.makeText(MainActivity.this, "app_bar_delete_character", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.app_bar_add_mob:
                    Toast.makeText(MainActivity.this, "app_bar_add_mob", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.app_bar_delete_mob:
                    Toast.makeText(MainActivity.this, "app_bar_delete_mob", Toast.LENGTH_SHORT).show();
                    break;
            }*/
            return false;
        });
    }

    private void changeFragment(int id) {
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();

        switch (id) {
            case R.id.app_bar_add_character:
                mFragmentTransaction.replace(R.id.container, characterFragment);
                break;
            case R.id.app_bar_delete_character:
                mFragmentTransaction.replace(R.id.container, mobFragment);
                break;
        }
        mFragmentTransaction.commit();
    }
}
