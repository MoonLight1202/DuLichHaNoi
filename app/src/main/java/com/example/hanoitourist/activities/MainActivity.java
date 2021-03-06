package com.example.hanoitourist.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hanoitourist.R;
import com.example.hanoitourist.database.Database;
import com.example.hanoitourist.fragment.ExploreFragment;
import com.example.hanoitourist.fragment.LikesFragment;
import com.example.hanoitourist.fragment.MapsFragment;
import com.example.hanoitourist.fragment.ProfileFragment;
import com.example.hanoitourist.model.Places;
import com.example.hanoitourist.model.User;
import com.example.hanoitourist.model.User_Fav;
import com.example.hanoitourist.viewmodel.PlacesVM;
import com.example.hanoitourist.viewmodel.UserVM;
import com.example.hanoitourist.viewmodel.User_fav_VM;
import com.google.android.material.navigation.NavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    public NavigationView navigationView;
    public UserVM userVM = new UserVM();
    PlacesVM placesVM = new PlacesVM();
    User_fav_VM user_fav_vm = new User_fav_VM();
    public static int id_user;
    public Database database;
    private static final int FRAGMENT_EXPLORE = 0;
    private static final int FRAGMENT_PROFILE = 1;
    private static final int FRAGMENT_LIKES = 2;
    private static final int FRAGMENT_MAP = 3;

    private int currentFragment = FRAGMENT_EXPLORE;
    private ChipNavigationBar chipNavigationBar;
    TextView navUsername;
    ArrayList<User> userArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        database = new Database( this );

        toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        chipNavigationBar = findViewById( R.id.bottom_nav );
        drawerLayout = findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle( this, drawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close );
        drawerLayout.addDrawerListener( actionBarDrawerToggle );
        actionBarDrawerToggle.syncState();
        navigationView = findViewById( R.id.navigationView );
        View headerView = navigationView.getHeaderView( 0 );
        navUsername = (TextView) headerView.findViewById( R.id.txtUserName );
        userArrayList = userVM.getALl();
        for (int i = 0; i < userArrayList.size(); i++) {
            if (userArrayList.get( i ).getId() == id_user) {
                navUsername.setText( userArrayList.get( i ).getName() );
            }
        }
        TextView txtUserPhone = headerView.findViewById( R.id.txtUserSdt );
        Intent intent = getIntent();
        String phoneNum = intent.getStringExtra( "phone_number" );
        txtUserPhone.setText( "Phone: " + phoneNum );
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.luu_user = PreferenceManager.getDefaultSharedPreferences( this );
        SharedPreferences.Editor editor = loginScreen.luu_user.edit();
        int sttUser = loginScreen.luu_user.getInt( "soluong", 1 );
        User user = new User( sttUser, "User " + sttUser, phoneNum, "null", 0, null );
        if (database.countUserSQL() < 1) {
            if (userVM.insert( user ) > -1) {
                id_user = user.getId();
            } else {
                Toast.makeText( this, "Th??m m???i sql user th???t b???i", Toast.LENGTH_LONG ).show();
            }
            Places places = new Places( 0, "B???o t??ng H?? N???i",
                    "https://static.tuoitre.vn/tto/i/s626//2015/08/19/bea7ee8b.jpg",
                    "B???o t??ng H?? N???i, tr?????c ????y n???m ??? s??? 5B ph??? H??m Long, qu???n Ho??n Ki???m, H?? N???i, l?? n??i tr??ng b??y gi???i thi???u v??? th??? ???? H?? N???i t??? khi d???ng n?????c ?????n nay. ???????c th??nh l???p t??? n??m 1982, n??n s??? l?????ng hi???n v???t c???a b???o t??ng l??n t???i h??ng ch???c ng??n, trong ???? ri??ng kho c??? v???t qu?? hi???m ???? chi???m t???i h??n 7 ng??n",
                    21.1133192, 105.7368026,
                    "https://vi.wikipedia.org/wiki/B%E1%BA%A3o_t%C3%A0ng_H%C3%A0_N%E1%BB%99i" );
            Places places1 = new Places( 1, "B???o t??ng ph??ng kh??ng",
                    "https://www.google.com/maps/uv?pb=!1s0x3135ac7d5f4350c9%3A0xb6729d0a4dc85e58!3m1!7e115!4shttps%3A%2F%2Flh5.googleusercontent.com%2Fp%2FAF1QipPBHecymLamYbU24Hw_XM7DD1XzSiifbGPeOneS%3Dw525-h352-k-no!5zYuG6o28gdMOgbmcgcGjDsm5nIGtow7RuZyBraMO0bmcgcXXDom4gLSBUw6xtIHRyw6puIEdvb2dsZQ!15sCgIgAQ&imagekey=!1e10!2sAF1QipPBHecymLamYbU24Hw_XM7DD1XzSiifbGPeOneS&hl=vi&sa=X&ved=2ahUKEwiqgviz0ar4AhXcm1YBHcvBBEsQoip6BAhjEAM",
                    "B???o t??ng Ph??ng kh??ng ??? Kh??ng qu??n tr???c thu???c C???c Ch??nh tr???, Qu??n ch???ng Ph??ng kh??ng ??? Kh??ng qu??n, Qu??n ?????i nh??n d??n Vi???t Nam, ti???n th??n l?? Ph??ng Truy???n th???ng B??? ?????i Ph??ng kh??ng th??nh l???p n??m 1958. B???o t??ng ???????c x???p h???ng Hai trong h??? th???ng b???o t??ng t???i Vi???t Nam.",
                    21.003466576860497, 105.8295299632838,
                    "https://vi.wikipedia.org/wiki/B%E1%BA%A3o_t%C3%A0ng_Ph%C3%B2ng_kh%C3%B4ng_-_Kh%C3%B4ng_qu%C3%A2n_(Vi%E1%BB%87t_Nam)" );
            Places places2 = new Places( 2, "V??n Mi????u ??? Qu????c T???? Gia??m",
                    "https://www.google.com/maps/uv?pb=!1s0x3135ab9926e7bd67:0x580e078874d5df1e!3m1!7e115!4shttps://lh5.googleusercontent.com/p/AF1QipN85oIxl3LNRlKEmBU48xyj23RtCVusjjDmmkaN%3Dw468-h352-k-no!5squ%E1%BB%91c+t%E1%BB%AD+gi%C3%A1m+-+T%C3%ACm+tr%C3%AAn+Google!15zQ2dJZ0FRPT0&imagekey=!1e10!2sAF1QipN85oIxl3LNRlKEmBU48xyj23RtCVusjjDmmkaN&hl=vi&sa=X&ved=2ahUKEwjBq6_-q6_4AhXNpVYBHe7PBd4Qoip6BAhXEAM",
                    "V??n Mi???u ??? Qu???c T??? Gi??m l?? qu???n th??? di t??ch ??a d???ng v?? phong ph?? h??ng ?????u c???a th??nh ph??? H?? N???i, n???m ??? ph??a ????ng kinh th??nh Th??ng Long.",
                    21.02843995702061, 105.83532320583933,
                    "https://vi.wikipedia.org/wiki/V%C4%83n_Mi%E1%BA%BFu_%E2%80%93_Qu%E1%BB%91c_T%E1%BB%AD_Gi%C3%A1m" );
            Places places3 = new Places( 3, "Nh?? T?? H???a L??",
                    "https://www.google.com/maps/uv?pb=!1s0x3135ab96aa51de95:0x9701e10bee31af54!3m1!7e115!4s//lh6.googleusercontent.com/proxy/G1EyrVu4GKsmbjndkD7EBYvyauKzZU8TJfgNZCRAxgEeHRHlk2bRP-3ktkV1-afTyO87Ozu_rcXBs9ChyqhtvPqcklr0kVJQNUp93aIskiAWpQIaMXdsDEblEsBlhneuy0YJdTqvqfWpXg4b8SYwLNmWrtCkbDM%3Dw539-h352-k-no!5zTkjDgCBUw5kgSE_huqIgTMOSIC0gVMOsbSB0csOqbiBHb29nbGU!15zQ2dJZ0FRPT0&imagekey=!1e1!2shttp://t3.gstatic.com/images?q%3Dtbn:ANd9GcTs7cRqixLcb3T0E_BBDiqTjH1Pm2uuXjTky6gGYfxYw7XTNN-S&hl=vi&sa=X&ved=2ahUKEwjpg7rTrK_4AhVs6jgGHVTlAaoQoip6BAhUEAM",
                    "Nh?? t?? H???a L?? hay nh?? pha H???a L?? l?? m???t nh?? t?? do th???c d??n Ph??p x??y d???ng tr??n khu ?????t x??a thu???c l??ng Ho??? L??, nay c?? ?????a ch???: s??? 1 ph??? Ho??? L??, ph?????ng Tr???n H??ng ?????o, qu???n Ho??n Ki???m, H?? N???i.",
                    21.026025133859143, 105.84637184220662,
                    "https://vi.wikipedia.org/wiki/Nh%C3%A0_t%C3%B9_H%E1%BB%8Fa_L%C3%B2" );
            if (database.countPlaceSQL() < 1) {
                if (placesVM.insert( places ) > -1) {
                } else {
                    Toast.makeText( this, "Th??m m???i ?????a ??i???m 1 th???t b???i", Toast.LENGTH_LONG ).show();
                }
                if (placesVM.insert( places1 ) > -1) {
                } else {
                    Toast.makeText( this, "Th??m m???i ?????a ??i???m 2 th???t b???i", Toast.LENGTH_LONG ).show();
                }
                if (placesVM.insert( places2 ) > -1) {
                } else {
                    Toast.makeText( this, "Th??m m???i ?????a ??i???m 3 th???t b???i", Toast.LENGTH_LONG ).show();
                }
                if (placesVM.insert( places3 ) > -1) {
                } else {
                    Toast.makeText( this, "Th??m m???i ?????a ??i???m 4 th???t b???i", Toast.LENGTH_LONG ).show();
                }
            }
            for (int j = 0; j < database.countPlaceSQL(); j++) {
                User_Fav user_fav = new User_Fav( 0, sttUser, j, 0 );
                user_fav_vm.insert( user_fav );
            }
            editor.putInt( "soluong", sttUser + 1 );
            editor.apply();

        } else {
            userArrayList = new ArrayList<>();
            userArrayList = userVM.getALl();
            Boolean isExist = false;
            for (int i = 0; i < userArrayList.size(); i++) {
                if (userArrayList.get( i ).getPhoneNumOrGmail().equals( phoneNum )) {
                    isExist = true;
                    id_user = userArrayList.get( i ).getId();
                }
            }
            if (isExist) {
                // Toast.makeText(this, "Tr??ng sdt", Toast.LENGTH_LONG).show();
            } else {
                if (userVM.insert( user ) > -1) {
                    id_user = user.getId();
                    //Toast.makeText(this, "Th??m m???i sql user oke", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText( this, "Th??m m???i sql user th???t b???i", Toast.LENGTH_LONG ).show();
                }
                Places places = new Places( 0, "B???o t??ng H?? N???i",
                        "https://static.tuoitre.vn/tto/i/s626//2015/08/19/bea7ee8b.jpg",
                        "B???o t??ng H?? N???i, tr?????c ????y n???m ??? s??? 5B ph??? H??m Long, qu???n Ho??n Ki???m, H?? N???i, l?? n??i tr??ng b??y gi???i thi???u v??? th??? ???? H?? N???i t??? khi d???ng n?????c ?????n nay. ???????c th??nh l???p t??? n??m 1982, n??n s??? l?????ng hi???n v???t c???a b???o t??ng l??n t???i h??ng ch???c ng??n, trong ???? ri??ng kho c??? v???t qu?? hi???m ???? chi???m t???i h??n 7 ng??n",
                        21.1133192, 105.7368026,
                        "https://vi.wikipedia.org/wiki/B%E1%BA%A3o_t%C3%A0ng_H%C3%A0_N%E1%BB%99i" );

                Places places1 = new Places( 1, "B???o t??ng ph??ng kh??ng",
                        "https://www.google.com/maps/uv?pb=!1s0x3135ac7d5f4350c9%3A0xb6729d0a4dc85e58!3m1!7e115!4shttps%3A%2F%2Flh5.googleusercontent.com%2Fp%2FAF1QipPBHecymLamYbU24Hw_XM7DD1XzSiifbGPeOneS%3Dw525-h352-k-no!5zYuG6o28gdMOgbmcgcGjDsm5nIGtow7RuZyBraMO0bmcgcXXDom4gLSBUw6xtIHRyw6puIEdvb2dsZQ!15sCgIgAQ&imagekey=!1e10!2sAF1QipPBHecymLamYbU24Hw_XM7DD1XzSiifbGPeOneS&hl=vi&sa=X&ved=2ahUKEwiqgviz0ar4AhXcm1YBHcvBBEsQoip6BAhjEAM",
                        "B???o t??ng Ph??ng kh??ng ??? Kh??ng qu??n tr???c thu???c C???c Ch??nh tr???, Qu??n ch???ng Ph??ng kh??ng ??? Kh??ng qu??n, Qu??n ?????i nh??n d??n Vi???t Nam, ti???n th??n l?? Ph??ng Truy???n th???ng B??? ?????i Ph??ng kh??ng th??nh l???p n??m 1958. B???o t??ng ???????c x???p h???ng Hai trong h??? th???ng b???o t??ng t???i Vi???t Nam.",
                        21.003466576860497, 105.8295299632838,
                        "https://vi.wikipedia.org/wiki/B%E1%BA%A3o_t%C3%A0ng_Ph%C3%B2ng_kh%C3%B4ng_-_Kh%C3%B4ng_qu%C3%A2n_(Vi%E1%BB%87t_Nam)" );
                Places places2 = new Places( 2, "V??n Mi????u ??? Qu????c T???? Gia??m",
                        "https://www.google.com/maps/uv?pb=!1s0x3135ab9926e7bd67:0x580e078874d5df1e!3m1!7e115!4shttps://lh5.googleusercontent.com/p/AF1QipN85oIxl3LNRlKEmBU48xyj23RtCVusjjDmmkaN%3Dw468-h352-k-no!5squ%E1%BB%91c+t%E1%BB%AD+gi%C3%A1m+-+T%C3%ACm+tr%C3%AAn+Google!15zQ2dJZ0FRPT0&imagekey=!1e10!2sAF1QipN85oIxl3LNRlKEmBU48xyj23RtCVusjjDmmkaN&hl=vi&sa=X&ved=2ahUKEwjBq6_-q6_4AhXNpVYBHe7PBd4Qoip6BAhXEAM",
                        "V??n Mi???u ??? Qu???c T??? Gi??m l?? qu???n th??? di t??ch ??a d???ng v?? phong ph?? h??ng ?????u c???a th??nh ph??? H?? N???i, n???m ??? ph??a ????ng kinh th??nh Th??ng Long.",
                        21.02843995702061, 105.83532320583933,
                        "https://vi.wikipedia.org/wiki/V%C4%83n_Mi%E1%BA%BFu_%E2%80%93_Qu%E1%BB%91c_T%E1%BB%AD_Gi%C3%A1m" );
                Places places3 = new Places( 3, "Nh?? T?? H???a L??",
                        "https://www.google.com/maps/uv?pb=!1s0x3135ab96aa51de95:0x9701e10bee31af54!3m1!7e115!4s//lh6.googleusercontent.com/proxy/G1EyrVu4GKsmbjndkD7EBYvyauKzZU8TJfgNZCRAxgEeHRHlk2bRP-3ktkV1-afTyO87Ozu_rcXBs9ChyqhtvPqcklr0kVJQNUp93aIskiAWpQIaMXdsDEblEsBlhneuy0YJdTqvqfWpXg4b8SYwLNmWrtCkbDM%3Dw539-h352-k-no!5zTkjDgCBUw5kgSE_huqIgTMOSIC0gVMOsbSB0csOqbiBHb29nbGU!15zQ2dJZ0FRPT0&imagekey=!1e1!2shttp://t3.gstatic.com/images?q%3Dtbn:ANd9GcTs7cRqixLcb3T0E_BBDiqTjH1Pm2uuXjTky6gGYfxYw7XTNN-S&hl=vi&sa=X&ved=2ahUKEwjpg7rTrK_4AhVs6jgGHVTlAaoQoip6BAhUEAM",
                        "Nh?? t?? H???a L?? hay nh?? pha H???a L?? l?? m???t nh?? t?? do th???c d??n Ph??p x??y d???ng tr??n khu ?????t x??a thu???c l??ng Ho??? L??, nay c?? ?????a ch???: s??? 1 ph??? Ho??? L??, ph?????ng Tr???n H??ng ?????o, qu???n Ho??n Ki???m, H?? N???i.",
                        21.026025133859143, 105.84637184220662,
                        "https://vi.wikipedia.org/wiki/Nh%C3%A0_t%C3%B9_H%E1%BB%8Fa_L%C3%B2" );
                if (database.countPlaceSQL() < 1) {
                    if (placesVM.insert( places ) > -1) {
                        // Toast.makeText(this, "Th??m m???i sql oke", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText( this, "Th??m m???i sql th???t b???i", Toast.LENGTH_LONG ).show();
                    }
                    if (placesVM.insert( places1 ) > -1) {
                        //  Toast.makeText(this, "Th??m m???i sql oke", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText( this, "Th??m m???i sql th???t b???i", Toast.LENGTH_LONG ).show();
                    }
                    if (placesVM.insert( places2 ) > -1) {
                        // Toast.makeText(this, "Th??m m???i sql oke", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText( this, "Th??m m???i sql th???t b???i", Toast.LENGTH_LONG ).show();
                    }
                    if (placesVM.insert( places3 ) > -1) {
                        //  Toast.makeText(this, "Th??m m???i sql oke", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText( this, "Th??m m???i sql th???t b???i", Toast.LENGTH_LONG ).show();
                    }
                }
                for (int j = 0; j < database.countPlaceSQL(); j++) {
                    User_Fav user_fav = new User_Fav( 0, sttUser, j, 0 );
                    user_fav_vm.insert( user_fav );
                }
                editor.putInt( "soluong", sttUser + 1 );
                editor.apply();
            }
        }

        navigationView.setNavigationItemSelectedListener( this );
        replaceFragment( new ExploreFragment() );
        navigationView.getMenu().findItem( R.id.nav_explore ).setChecked( true );
        chipNavigationBar.setOnItemSelectedListener( new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.bot_explore:
                        openExploreFragment();
                        navigationView.getMenu().findItem( R.id.nav_explore ).setChecked( true );
                        break;
                    case R.id.bot_profile:
                        openProfileFragment();
                        navigationView.getMenu().findItem( R.id.nav_profile ).setChecked( true );
                        break;
                    case R.id.bot_likes:
                        openLikesFragment();
                        navigationView.getMenu().findItem( R.id.nav_fav ).setChecked( true );
                        break;
                    case R.id.bot_map:
                        openMapFragment();
                        navigationView.getMenu().findItem( R.id.nav_map ).setChecked( true );
                        break;
                }
            }
        } );
        chipNavigationBar.setItemSelected( R.id.bot_explore, true );
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_explore) {
            openExploreFragment();
            chipNavigationBar.setItemSelected( R.id.bot_explore, true );
        } else if (id == R.id.nav_profile) {
            openProfileFragment();
            chipNavigationBar.setItemSelected( R.id.bot_profile, true );
        } else if (id == R.id.nav_fav) {
            openLikesFragment();
            chipNavigationBar.setItemSelected( R.id.bot_likes, true );
        } else if (id == R.id.nav_map) {
            openMapFragment();
            chipNavigationBar.setItemSelected( R.id.bot_map, true );
        } else if (id == R.id.nav_privacy) {
            Intent intent = new Intent( MainActivity.this, PolicyActivity.class );
            startActivity( intent );
        } else if (id == R.id.nav_version) {
            Intent intent = new Intent( MainActivity.this, AboutApp.class );
            startActivity( intent );
        } else if (id == R.id.logout) {
            Intent intent = new Intent( MainActivity.this, LoginScreen.class );
            startActivity( intent );
        } else if (id == R.id.nav_rateus) {
            Dialog dialog = new Dialog( this );
            dialog.requestWindowFeature( Window.FEATURE_NO_TITLE );
            dialog.setContentView( R.layout.activity_rate_app );
            dialog.setCanceledOnTouchOutside( false );
            ImageView oneStar, twoStar, threeStar, fourStar, fiveStar;
            Button btnHUy, btnGui;
            oneStar = dialog.findViewById( R.id.oneStar );
            twoStar = dialog.findViewById( R.id.twoStar );
            threeStar = dialog.findViewById( R.id.threeStar );
            fourStar = dialog.findViewById( R.id.fourStar );
            fiveStar = dialog.findViewById( R.id.fiveStar );
            btnHUy = dialog.findViewById( R.id.btnHuy );
            btnGui = dialog.findViewById( R.id.btnGui );
            oneStar.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    oneStar.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.star_rated ) );
                    twoStar.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.ic_star_rate ) );
                    threeStar.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.ic_star_rate ) );
                    fourStar.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.ic_star_rate ) );
                    fiveStar.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.ic_star_rate ) );
                }
            } );
            twoStar.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    oneStar.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.star_rated ) );
                    twoStar.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.star_rated ) );
                    threeStar.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.ic_star_rate ) );
                    fourStar.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.ic_star_rate ) );
                    fiveStar.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.ic_star_rate ) );
                }
            } );
            threeStar.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    oneStar.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.star_rated ) );
                    twoStar.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.star_rated ) );
                    threeStar.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.star_rated ) );
                    fourStar.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.ic_star_rate ) );
                    fiveStar.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.ic_star_rate ) );
                }
            } );
            fourStar.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    oneStar.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.star_rated ) );
                    twoStar.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.star_rated ) );
                    threeStar.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.star_rated ) );
                    fourStar.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.star_rated ) );
                    fiveStar.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.ic_star_rate ) );

                }
            } );
            fiveStar.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    oneStar.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.star_rated ) );
                    twoStar.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.star_rated ) );
                    threeStar.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.star_rated ) );
                    fourStar.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.star_rated ) );
                    fiveStar.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.star_rated ) );
                }
            } );
            btnHUy.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            } );
            btnGui.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText( dialog.getContext(), "C??m ??n b???n ???? ????nh gi??!", Toast.LENGTH_SHORT ).show();
                    dialog.dismiss();
                }
            } );
            dialog.show();
        }
        drawerLayout.closeDrawer( GravityCompat.START );
        return true;
    }

    private void openExploreFragment() {
        if (currentFragment != FRAGMENT_EXPLORE) {
            replaceFragment( new ExploreFragment() );
            currentFragment = FRAGMENT_EXPLORE;
        }
    }

    public void openProfileFragment() {
        if (currentFragment != FRAGMENT_PROFILE) {
            replaceFragment( new ProfileFragment() );
            currentFragment = FRAGMENT_PROFILE;
        }
    }

    private void openLikesFragment() {
        if (currentFragment != FRAGMENT_LIKES) {
            replaceFragment( new LikesFragment() );
            currentFragment = FRAGMENT_LIKES;
        }
    }

    private void openMapFragment() {
        if (currentFragment != FRAGMENT_MAP) {
            replaceFragment( new MapsFragment() );
            currentFragment = FRAGMENT_MAP;
        }
    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen( GravityCompat.START )){
            drawerLayout.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace( R.id.content_frame,fragment );
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        userArrayList = userVM.getALl();
        for (int i = 0; i < userArrayList.size(); i++) {
            if(userArrayList.get( i ).getId() == id_user){
                navUsername.setText(userArrayList.get( i ).getName());
            }
        }
    }
}