package com.example.bangchangbae.friendlyapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bangchangbae.friendlyapplication.common.GlobalData;
import com.example.bangchangbae.friendlyapplication.common.HistoryMoveViewPager;
import com.example.bangchangbae.friendlyapplication.common.RecyclerItemClickListener;
import com.example.bangchangbae.friendlyapplication.common.Util;
import com.example.bangchangbae.friendlyapplication.content.ContentActivity;
import com.example.bangchangbae.friendlyapplication.content.WriteContentActivity;
import com.example.bangchangbae.friendlyapplication.data.MyProfile;
import com.example.bangchangbae.friendlyapplication.data.MySearchResult;

import java.util.List;


public class MainActivity extends AppCompatActivity {


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private HistoryMoveViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getApplicationContext(), getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (HistoryMoveViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        int[] iconList ={R.drawable.ic_home_black_24dp,
                R.drawable.ic_find_in_page_black_24dp,
                R.drawable.ic_event_black_24dp,
                R.drawable.ic_favorite_border_black_24dp,
                R.drawable.ic_perm_identity_black_24dp
        };
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setIcon(iconList[i]);
        }

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabPosition = tab.getPosition();
                //mViewPager.setCurrentItem(tab.getPosition());
                mViewPager.Go(tabPosition);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


      FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
      fab.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                      .setAction("Action", null).show();

              Context context = view.getContext();
              Intent intent = new Intent(context, WriteContentActivity.class);
              context.startActivity(intent);
          }
      });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(!mViewPager.Back()) {
            super.onBackPressed();
        }
    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private Context mContext;
        public SectionsPagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            this.mContext = context;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 5 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "HOME";
                case 1:
                    return "FIND";
                case 2:
                    return "SCHEDULE";
                case 3:
                    return "FAVORITE";
                case 4:
                    return "MY";
            }
            return null;
        }

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */

        private RecyclerView mRecyclerView;
        private RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager mLayoutManager;
        private GridView mGridView;
        private ImageAdapter mImageAdapter;


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
            View rootView;

            if(sectionNumber == 1){
                rootView = inflater.inflate(R.layout.feed_list, container, false);
                if (rootView instanceof RecyclerView) {
                    Context context = rootView.getContext();
                    mRecyclerView = (RecyclerView) rootView;
                    mRecyclerView.setHasFixedSize(true);

                    mLayoutManager = new LinearLayoutManager(context);
                    mRecyclerView.setLayoutManager(mLayoutManager);

                    mAdapter = new MyFeedListAdapter(context);
                    mRecyclerView.setAdapter(mAdapter);

                    mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Context context = view.getContext();
                            Intent intent = new Intent(context, ContentActivity.class);
                            MyFeedListAdapter adapter = (MyFeedListAdapter)mRecyclerView.getAdapter();
                            int pictureId = adapter.getPictureId(position);
                            intent.putExtra(Util.PICTURE_ID, pictureId);
                            context.startActivity(intent);
                        }

                        @Override
                        public void onLongItemClick(View view, int position) {

                        }
                    }));

                }
            }
            else if(sectionNumber == 2){
                rootView = inflater.inflate(R.layout.search_layout, container, false);
                final Context context = rootView.getContext();

                ListView searchResult = (ListView)rootView.findViewById(R.id.search_result_list);
                final ResultAdapter adapter = new ResultAdapter(rootView.getContext());
                searchResult.setAdapter(adapter);
                final SearchView searchView = (SearchView)rootView.findViewById(R.id.favorite_search);
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        List<MySearchResult> results = ((GlobalData)context.getApplicationContext()).getSearchResult(query);
                        searchView.setQuery("", false);
                        searchView.setIconified(true);
                        adapter.updateResults(results);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                });

            }
            else if (sectionNumber == 3){
                rootView = inflater.inflate(R.layout.item_select_cam, container, false);
            }
            else if(sectionNumber == 4){
                rootView = inflater.inflate(R.layout.favorite_layout, container, false);
                final Context context = rootView.getContext();
                GridView infoGrid = (GridView)rootView.findViewById(R.id.gridview);
                final ImageAdapter imageAdapter = new ImageAdapter(GlobalData.GRID_TYPE.FAVORITE, context);
                infoGrid.setAdapter(imageAdapter);
                infoGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Context context = view.getContext();
                        Intent intent = new Intent(context, ContentActivity.class);
                        int pictureId = imageAdapter.getPictureId(position);
                        intent.putExtra(Util.PICTURE_ID, pictureId);
                        context.startActivity(intent);
                    }
                });
                final SearchView searchView = (SearchView)rootView.findViewById(R.id.favorite_search);
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        List<Integer> results = ((GlobalData)context.getApplicationContext()).getMyThumbnailFavoriteImageList(query);
                        searchView.setQuery("", false);
                        searchView.setIconified(true);
                        imageAdapter.updateResults(results);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                });
            }
            else if(sectionNumber == 5){
                rootView = inflater.inflate(R.layout.my_info_layout, container, false);
                Context context = rootView.getContext();

                MyProfile profileData = ((GlobalData)context.getApplicationContext()).getMyProfile();
                ImageView ivUserProfileImageView = (ImageView) rootView.findViewById(R.id.ivUserProfile);

                int ivUserProfileImageViewWidth = ivUserProfileImageView.getLayoutParams().width;
                int ivUserProfileImageViewHeight = ivUserProfileImageView.getLayoutParams().height;
                Bitmap ivUserProfileImageBitmap = Util.decodeSampledBitmapFromResource(getResources(), profileData.profile_img_src, ivUserProfileImageViewWidth, ivUserProfileImageViewHeight);
                ivUserProfileImageView.setImageBitmap(ivUserProfileImageBitmap);
                TextView useridView = (TextView) rootView.findViewById(R.id.userid);
                useridView.setText(profileData.user_name);
                TextView userDescriptionView = (TextView) rootView.findViewById(R.id.userDescription);
                userDescriptionView.setText(profileData.user_description);


                GridView infoGrid = (GridView)rootView.findViewById(R.id.gridview);
                final ImageAdapter imageAdapter = new ImageAdapter(GlobalData.GRID_TYPE.UPLOADED, context);
                infoGrid.setAdapter(imageAdapter);

                infoGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Context context = view.getContext();
                        Intent intent = new Intent(context, ContentActivity.class);
                        int pictureId = imageAdapter.getPictureId(position);
                        intent.putExtra(Util.PICTURE_ID, pictureId);
                        context.startActivity(intent);
                    }
                });
            }
            else{
                Log.e("page error", "out of pages");
                rootView = null;
            }

            return rootView;
        }
    }
}
