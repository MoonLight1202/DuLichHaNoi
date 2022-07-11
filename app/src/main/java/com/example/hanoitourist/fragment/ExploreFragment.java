package com.example.hanoitourist.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.hanoitourist.activities.MainActivity;
import com.example.hanoitourist.model.Places;
import com.example.hanoitourist.viewmodel.PlacesVM;
import com.example.hanoitourist.R;
import com.example.hanoitourist.model.User_Fav;
import com.example.hanoitourist.viewmodel.User_fav_VM;
import com.example.hanoitourist.activities.WebViewWiki;
import com.example.hanoitourist.adapter.ExploreRecycleAdapter;
import com.example.hanoitourist.adapter.RecyclerTouchListener;
import com.example.hanoitourist.database.Database;
import com.example.hanoitourist.slider.Photo;
import com.example.hanoitourist.slider.PhotoAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;


public class ExploreFragment extends Fragment {
    public static ViewPager2 mViewPager2;
    private CircleIndicator3 mCircleIndicator3;
    RecyclerView PlacesRecyclerView;
    private List<Photo> photoList;
    Database database;
    User_fav_VM user_fav_vm = new User_fav_VM();
    User_Fav user_favor;
    PlacesVM placesVM = new PlacesVM();
    public static ArrayList<User_Fav> user_favArrayList;
    public static ExploreRecycleAdapter adapter;
    int vitri;
    int id_place;
    boolean isLike = false;
    public View view;
    public ArrayList<Places> list;
    public ImageButton imgButtonGoogleMap,imgButtonWiki,btnCloseBottomSheetDialogBuy,imgButtonLike;
    public ImageView imgPlace;
    public TextView txtPlaceInfo,txtPlaceDesc;
    private Handler handler = new Handler( Looper.getMainLooper());
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int currentPos = mViewPager2.getCurrentItem();
            if(currentPos == photoList.size()-1){
                mViewPager2.setCurrentItem( 0 );
            } else {
                mViewPager2.setCurrentItem( currentPos + 1 );
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate( R.layout.fragment_explore, container, false);
        mViewPager2 = view.findViewById( R.id.view_pager2 );
        mCircleIndicator3 = view.findViewById( R.id.cir_indicator3 );

        photoList = getListPhoto();
        PhotoAdapter photoAdapter = new PhotoAdapter( this.getActivity(),photoList );
        mViewPager2.setAdapter( photoAdapter );
        mCircleIndicator3.setViewPager( mViewPager2 );
        mViewPager2.registerOnPageChangeCallback( new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected( position );
                handler.removeCallbacks( runnable );
                handler.postDelayed( runnable,2500 );
                vitri = position;
            }
        } );
        mViewPager2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( view.getContext(),"hello",Toast.LENGTH_SHORT ).show();
            }
        } );
        PlacesRecyclerView=view.findViewById(R.id.grid_all_cities);
        list=new ArrayList<>();
        Places places = new Places(0,"Bảo tàng Hà Nội",
                "https://static.tuoitre.vn/tto/i/s626//2015/08/19/bea7ee8b.jpg",
                "Bảo tàng Hà Nội, trước đây nằm ở số 5B phố Hàm Long, quận Hoàn Kiếm, Hà Nội, là nơi trưng bày giới thiệu về thủ đô Hà Nội từ khi dựng nước đến nay. Được thành lập từ năm 1982, nên số lượng hiện vật của bảo tàng lên tới hàng chục ngàn, trong đó riêng kho cổ vật quý hiếm đã chiếm tới hơn 7 ngàn",
                21.1133192, 105.7368026,
                "https://vi.wikipedia.org/wiki/B%E1%BA%A3o_t%C3%A0ng_H%C3%A0_N%E1%BB%99i"  );
        list.add(places);
        Places places1 = new Places( 2,"Bảo tàng phòng không",
                "https://www.google.com/maps/uv?pb=!1s0x3135ac7d5f4350c9%3A0xb6729d0a4dc85e58!3m1!7e115!4shttps%3A%2F%2Flh5.googleusercontent.com%2Fp%2FAF1QipPBHecymLamYbU24Hw_XM7DD1XzSiifbGPeOneS%3Dw525-h352-k-no!5zYuG6o28gdMOgbmcgcGjDsm5nIGtow7RuZyBraMO0bmcgcXXDom4gLSBUw6xtIHRyw6puIEdvb2dsZQ!15sCgIgAQ&imagekey=!1e10!2sAF1QipPBHecymLamYbU24Hw_XM7DD1XzSiifbGPeOneS&hl=vi&sa=X&ved=2ahUKEwiqgviz0ar4AhXcm1YBHcvBBEsQoip6BAhjEAM",
                "Bảo tàng Phòng không – Không quân trực thuộc Cục Chính trị, Quân chủng Phòng không – Không quân, Quân đội nhân dân Việt Nam, tiền thân là Phòng Truyền thống Bộ đội Phòng không thành lập năm 1958. Bảo tàng được xếp hạng Hai trong hệ thống bảo tàng tại Việt Nam.",
                21.003466576860497, 105.8295299632838,
                "https://vi.wikipedia.org/wiki/B%E1%BA%A3o_t%C3%A0ng_Ph%C3%B2ng_kh%C3%B4ng_-_Kh%C3%B4ng_qu%C3%A2n_(Vi%E1%BB%87t_Nam)" );
        list.add(places1);
        Places places2 = new Places( 2,"Văn Miếu – Quốc Tử Giám",
                "https://www.google.com/maps/uv?pb=!1s0x3135ab9926e7bd67:0x580e078874d5df1e!3m1!7e115!4shttps://lh5.googleusercontent.com/p/AF1QipN85oIxl3LNRlKEmBU48xyj23RtCVusjjDmmkaN%3Dw468-h352-k-no!5squ%E1%BB%91c+t%E1%BB%AD+gi%C3%A1m+-+T%C3%ACm+tr%C3%AAn+Google!15zQ2dJZ0FRPT0&imagekey=!1e10!2sAF1QipN85oIxl3LNRlKEmBU48xyj23RtCVusjjDmmkaN&hl=vi&sa=X&ved=2ahUKEwjBq6_-q6_4AhXNpVYBHe7PBd4Qoip6BAhXEAM",
                "Văn Miếu – Quốc Tử Giám là quần thể di tích đa dạng và phong phú hàng đầu của thành phố Hà Nội, nằm ở phía Đông kinh thành Thăng Long.",
                21.02843995702061, 105.83532320583933,
                "https://vi.wikipedia.org/wiki/V%C4%83n_Mi%E1%BA%BFu_%E2%80%93_Qu%E1%BB%91c_T%E1%BB%AD_Gi%C3%A1m" );
        list.add(places2);
        Places places3 = new Places( 3,"Nhà Tù Hỏa Lò",
                "https://www.google.com/maps/uv?pb=!1s0x3135ab96aa51de95:0x9701e10bee31af54!3m1!7e115!4s//lh6.googleusercontent.com/proxy/G1EyrVu4GKsmbjndkD7EBYvyauKzZU8TJfgNZCRAxgEeHRHlk2bRP-3ktkV1-afTyO87Ozu_rcXBs9ChyqhtvPqcklr0kVJQNUp93aIskiAWpQIaMXdsDEblEsBlhneuy0YJdTqvqfWpXg4b8SYwLNmWrtCkbDM%3Dw539-h352-k-no!5zTkjDgCBUw5kgSE_huqIgTMOSIC0gVMOsbSB0csOqbiBHb29nbGU!15zQ2dJZ0FRPT0&imagekey=!1e1!2shttp://t3.gstatic.com/images?q%3Dtbn:ANd9GcTs7cRqixLcb3T0E_BBDiqTjH1Pm2uuXjTky6gGYfxYw7XTNN-S&hl=vi&sa=X&ved=2ahUKEwjpg7rTrK_4AhVs6jgGHVTlAaoQoip6BAhUEAM",
                "Nhà tù Hỏa Lò hay nhà pha Hỏa Lò là một nhà tù do thực dân Pháp xây dựng trên khu đất xưa thuộc làng Hoả Lò, nay có địa chỉ: số 1 phố Hoả Lò, phường Trần Hưng Đạo, quận Hoàn Kiếm, Hà Nội.",
                21.026025133859143, 105.84637184220662,
                "https://vi.wikipedia.org/wiki/Nh%C3%A0_t%C3%B9_H%E1%BB%8Fa_L%C3%B2" );
        list.add(places3);
        adapter=new ExploreRecycleAdapter(list,getContext(),1);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        PlacesRecyclerView.setLayoutManager(gridLayoutManager);
        PlacesRecyclerView.setAdapter(adapter);
        PlacesRecyclerView.addOnItemTouchListener( new RecyclerTouchListener(view.getContext(), PlacesRecyclerView, new RecyclerTouchListener.ClickListener(){

            @Override
            public void onClick(View view, int position) {
                id_place = position;
                clickOpenBottomDialogBuy();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        } ));
        return view;
    }

    private List<Photo> getListPhoto() {
        photoList = new ArrayList<>();
        photoList.add( new Photo( "https://kienviet.net/wp-content/uploads/2011/03/hanoi-museum-1-1440x721.jpg" ) );
        photoList.add( new Photo( "https://leadtravel.com.vn/wp-content/uploads/2017/05/tour-du-lich-bao-tang-phong-khong-khong-quan-xem-xiec-nua-ngay-gia-re-1-668x366.jpg" ) );
        photoList.add( new Photo( "https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/08/nha-tu-hoa-lo.jpg" ) );
        photoList.add( new Photo( "https://nucuoimekong.com/wp-content/uploads/van-mieu-quoc-tu-giam.jpg") );
        return photoList;
    }
    @Override
    public void onPause() {
        handler.removeCallbacks( runnable );
        super.onPause();
    }

    @Override
    public void onResume() {
        handler.postDelayed( runnable,2500 );
        super.onResume();
    }


    private void clickOpenBottomDialogBuy(){
        View viewDialogBuy = getLayoutInflater().inflate( R.layout.bottom_sheet_buy,null );
        user_favArrayList = new ArrayList<>();
        imgButtonLike = viewDialogBuy.findViewById( R.id.btnLike );
        user_favArrayList = user_fav_vm.getALl();
        for (int i = 0; i < user_favArrayList.size(); i++) {
            Log.d("id người dùng đang đăng nhập",MainActivity.id_user+"--"+id_place);
            Log.d("user_favList.163",user_favArrayList.get( i ).getId_user()+"---"+user_favArrayList.get( i ).getId_place()+"==="+user_favArrayList.get( i ).getIs_like());
            if (user_favArrayList.get( i ).getId_place() == id_place && user_favArrayList.get( i ).getId_user() == MainActivity.id_user) {
                Log.d("check yêu thích",user_favArrayList.get( i ).getIs_like()+"");
                if (user_favArrayList.get( i ).getIs_like() == 1) {
                    imgButtonLike.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.liked_background ) );
                    isLike = true;
                    break;
                } else {
                    imgButtonLike.setBackground( ContextCompat.getDrawable( view.getContext(), R.drawable.unlike_background ) );
                    isLike = false;
                    break;
                }
            }
        }
        adapter.notifyDataSetChanged();
        BottomSheetDialog bottomSheetDialogBuy = new BottomSheetDialog( view.getContext() );
        bottomSheetDialogBuy.setContentView( viewDialogBuy );
        bottomSheetDialogBuy.show();
        txtPlaceInfo = viewDialogBuy.findViewById( R.id.txtInfoPlace );
        imgPlace = viewDialogBuy.findViewById( R.id.imgPhoto_Place );
        imgButtonGoogleMap = viewDialogBuy.findViewById( R.id.btnGoogleMap );
        imgButtonWiki = viewDialogBuy.findViewById( R.id.btnWiki );
        txtPlaceDesc = viewDialogBuy.findViewById( R.id.txtDesPlace );
        btnCloseBottomSheetDialogBuy = viewDialogBuy.findViewById( R.id.btnCloseBottomSheetDialog );

        btnCloseBottomSheetDialogBuy.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialogBuy.dismiss();
            }
        } );
        Picasso.get().load( list.get( id_place ).getAvt() ).into( imgPlace );
        txtPlaceInfo.setText( list.get( id_place ).getName() );
        txtPlaceDesc.setText( list.get( id_place ).getInfo() );
        imgButtonWiki.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), WebViewWiki.class );
                intent.putExtra( "linkBai",list.get( id_place ).getUrlwiki() );
                startActivity(intent);
            }
        } );
        imgButtonGoogleMap.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("http://maps.google.co.in/maps?q=" + list.get( id_place ).getName());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        } );

        imgButtonLike.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLike==false)
                {
                    imgButtonLike.setBackground( ContextCompat.getDrawable(view.getContext(), R.drawable.liked_background));
                    isLike = true;
                    Log.d("id người dùng",MainActivity.id_user+"");
                    Log.d("id địa điểm",id_place+"");
                    for (int i = 0; i < user_favArrayList.size(); i++) {
                        Log.d("user_favList.idUser_203",user_favArrayList.get( i ).getId_user()+"");
                        Log.d("user_favList.idPlace_203",user_favArrayList.get( i ).getId_place()+"");
                        if (user_favArrayList.get( i ).getId_place() == id_place && user_favArrayList.get( i ).getId_user() == MainActivity.id_user) {
                            user_favor = new User_Fav( i+1, MainActivity.id_user, id_place,1 );
                            Log.d("id user fav",i+"");
                            if (user_fav_vm.update(user_favor) > -1){
                                Toast.makeText(viewDialogBuy.getContext(), "Yêu thích", Toast.LENGTH_LONG).show();
                                break;
                            }else{
                                Toast.makeText(viewDialogBuy.getContext(), "faile", Toast.LENGTH_LONG).show();
                                break;
                            }
                        }
                    }
                }
                else
                {
                    imgButtonLike.setBackground( ContextCompat.getDrawable(view.getContext(), R.drawable.unlike_background));
                    isLike = false;
                    for (int i = 0; i < user_favArrayList.size(); i++) {
                        Log.d("user_favList.idPlace_224",user_favArrayList.get( i ).getId_place()+"");
                        if (user_favArrayList.get( i ).getId_place() == id_place && user_favArrayList.get( i ).getId_user() == MainActivity.id_user) {
                            user_favor = new User_Fav( i+1, MainActivity.id_user,id_place, 0 );
                            if (user_fav_vm.update(user_favor) > -1){
                                Toast.makeText(viewDialogBuy.getContext(), "Bỏ yêu thích", Toast.LENGTH_LONG).show();
                                break;
                            }else{
                                Toast.makeText(viewDialogBuy.getContext(), "faile", Toast.LENGTH_LONG).show();
                                break;
                            }
                        }
                    }

                }

            }
        } );
    }
}