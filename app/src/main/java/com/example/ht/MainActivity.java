package com.example.ht;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ht.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ht.databinding.ActivityMainBinding;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    ListView movieField;
    ArrayList<String> movieList;
    ListView ratingsField;
    ArrayList<String> ratingsList;

    ArrayList names;
    ArrayList stars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_login)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        movieField = (ListView)findViewById(R.id.listView);
        movieList=new ArrayList<>();
        ratingsField = (ListView)findViewById(R.id.listRatings);
        ratingsList=new ArrayList<>();
    }

    @Override
    public void onStart() {
        super.onStart();
        names = getIntent().getParcelableArrayListExtra("names");
        stars = getIntent().getParcelableArrayListExtra("stars");
    }

    public void sendToFragment(ArrayList names, ArrayList stars) {
        Intent intent = new Intent(getApplicationContext(), NotificationsFragment.class);
        intent.putExtra("names", names);
        intent.putExtra("stars", stars);
        startActivity(intent);
    }

    public void readXML (View v) {
        DocumentBuilder builder = null;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        String urlString = "https://www.finnkino.fi/xml/Events/";
        try {
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();
            //System.out.print("Root element: " + doc.getDocumentElement().getNodeName());

            NodeList nlist = doc.getDocumentElement().getElementsByTagName("Event");

            for (int i = 0; i < nlist.getLength(); i++) {

                try {
                    Node node = nlist.item(i);

                    //System.out.println("Movie element: " + node.getNodeName());

                    if (node.getNodeType() == Node.ELEMENT_NODE) {

                        Element element = (Element) node;

                        //System.out.println("Movie name: " + element.getElementsByTagName("Title").item(0).getTextContent());
                        movieList.add("Movie name: " + element.getElementsByTagName("Title").item(0).getTextContent());

                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } finally {
            ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,movieList);
            movieField.setAdapter(arrayAdapter);
            System.out.println("\nValmis");
        }
    }
}