package com.missouristate.davis916.shades2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Laura Davis CIS 262-902
 * 8 March 2018
 * This app uses fragments to move back and forth across activities.
 * The app is designed to provide information about specific colors
 * that is displayed in the second activity (information activity) on tap.
 */

public class MyActivity extends Activity implements ListFragment.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        /*
         * Code to get the action bar icon to work
         * Code from FalconHawk @StackOverflow
         * https://stackoverflow.com/questions/26720759/no-app-icon-on-actionbar
         * Instructions from Android Developers
         * https://developer.android.com/studio/write/image-asset-studio.html#create-actionbartab
        */
        getActionBar().setDisplayShowHomeEnabled(true);
        getActionBar().setLogo(R.drawable.ic_action_shades);
        getActionBar().setDisplayUseLogoEnabled(true);

    }//end onCreate()

    @Override
    public void onShadeItemSelected(String link){
        //Check if the info fragment exists in this layout
        InformationFragment informationFragment = (InformationFragment)
                getFragmentManager().findFragmentById(R.id.fragment2);

        //Check if a two-pane config is being displayed
        if (informationFragment != null && informationFragment.isInLayout()){
            informationFragment.setText(link);
        }

        //A single-pane config exists
        else{
            //If the info fragment does not exist in this layout,
            //activate the info activity

            Intent intent = new Intent(this, InformationActivity.class);
            intent.putExtra("Information", link);
            startActivity(intent);
        }
    }//end onShadeItemSelected()

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate the menu
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }//end createOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //Handle action bar item clicks here. The action bar will
        //automatically handle clicks on the Home/Up button,
        //as long as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }//end onOptionsItemSelected
}//end MyActivity class
